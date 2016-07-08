/*
 * JSClass JavaScript WEB framework, version 1.0.0.0
 * (c) 2008 MaCi Hotesion
 *
 * JSClass is MaCi Hotesion Development.JSObjectClass
 */
var JSClass = {
	Version: '1.0.0.0',
	About: "MaCi Hotesion Developer",
	//浏览器
	Browser: {
		IE:     !!(window.attachEvent && !window.opera),
		Opera:  !!window.opera,
		WebKit: navigator.userAgent.indexOf('AppleWebKit/') > -1,
		Gecko:  navigator.userAgent.indexOf('Gecko') > -1 && navigator.userAgent.indexOf('KHTML') == -1,
		MobileSafari: !!navigator.userAgent.match(/Apple.*Mobile.*Safari/)
	},
	//浏览器特性
	BrowserFeatures: {
		XPath: !!document.evaluate,
		ElementExtensions: !!window.HTMLElement,
		SpecificElementExtensions: document.createElement('div').prototies && document.createElement('div').prototies !== document.createElement('form').prototies
	},
	//脚本片段
	ScriptFragment: '<script[^>]*>([\\S\\s]*?)<\/script>',
	JSONFilter: /^\/\*-secure-([\s\S]*)\*\/\s*$/,

	emptyFunction: function() {},
	K: function(x) {return x;}
};

/**********************************************************************************************************************************************************/
//给Object对象添加一个新的静态方法，其作用是创建一个新的类对象构造函数
Object.createClass=function(){
	var parent = null, properties = $array(arguments);
	if (Object.isFunction(properties[0]))
		parent = properties.shift();
	//create a Object constructor function. call own initialization method
	function createConstructor() {
		this.initialize.apply(this, arguments);
	}

	Object.extendClass(createConstructor, Object.Methods);
	createConstructor.superclass = parent;
	createConstructor.subclasses = [];

	if (parent) {
		var subclass = function() {};
		subclass.prototype = parent.prototype;
		createConstructor.prototype = new subclass;
		parent.subclasses.push(createConstructor);
	}

	for (var i = 0; i < properties.length; i++)
		createConstructor.addMethods(properties[i]);

	if (!createConstructor.prototype.initialize)
		createConstructor.prototype.initialize = JSClass.emptyFunction;

	createConstructor.prototype.constructor = createConstructor;

	return createConstructor;
};

//给Object对象添加一个新的静态方法，其作用是把属性从一个对象复制到另一个对象中
Object.extendClass=function(destination,source){
	for(property in source){//遍历所有要扩展的属性
		destination[property]=source[property];//然后将他们添加到目标对象中
	}
	return destination;//返回修改后的对象
};

Object.Methods = {
  addMethods: function(source) {
    var ancestor   = this.superclass && this.superclass.prototype;
    var properties = Object.keys(source);

    if (!Object.keys({ toString: true }).length)
      properties.push("toString", "valueOf");

    for (var i = 0, length = properties.length; i < length; i++) {
      var property = properties[i], value = source[property];
      if (ancestor && Object.isFunction(value) &&
          value.argumentNames().first() == "$super") {
        var method = value, value = Object.extendClass((function(m) {
          return function() { return ancestor[m].apply(this, arguments) };
        })(property).wrap(method), {
          valueOf:  function() { return method },
          toString: function() { return method.toString() }
        });
      }
      this.prototype[property] = value;
    }
    return this;
  }
};
/**********************************************************************************************************************************************************/

/******************************************************************[ public function start ]***************************************************************/
function $array(iterable) {
	if (!iterable) return [];
	if (iterable.toArray) return iterable.toArray();
	var length = iterable.length || 0, results = new Array(length);
	while (length--) results[length] = iterable[length];
	return results;
};
/******************************************************************[ public function end ]*****************************************************************/
/******************************************************************[ Object Extend start ]*****************************************************************/
//static extend
Object.extendClass(Object,{
	JSCLASS: JSClass,

	equals: function (obj){
		if(this == obj)
			return true;
		if(typeof(obj)=="undefined"||obj==null||typeof(obj)!="object")
			return false;
		var length = 0; 
		var length1=0;
		
		for(var ele in this)
			length++;
		for(var ele in obj)
			length1++;
		if(length!=length1)
			return false;
		if(obj.constructor==this.constructor) {
			for(var ele in this) {
				if(typeof(this[ele])=="object") {
					if(!this[ele].equals(obj[ele]))
						return false;
				} else if(typeof(this[ele])=="function") {
					if(!this[ele].toString().equals(obj[ele].toString())) 
						return false;
				} else if(this[ele]!=obj[ele])
					return false;
			}
			return true;
		}
		return false;
	},

	clone: function(){
		var newObj = new Object();
		for(elements in this)
		   newObj[elements] = this[elements];
		return newObj;
	},

	cloneAll: function(){
		function clonePrototype(){};
		clonePrototype.prototype = this;
		var obj = new clonePrototype();
		for(var ele in obj) {
		   if(typeof(obj[ele])=="object")
			   obj[ele] = obj[ele].cloneAll();
		}
		return obj;
	},

	isElement: function(object){
		return object && object.nodeType == 1;
	},

	//Hash有待封装
	isHash: function(object){
		return object instanceof Hash;
	},

	isObject: function(object){
		return typeof object == "object";
	},

	isArray: function(object){
		return object != null && typeof object == "object" && 'splice' in object && 'join' in object;
	},

	isFunction: function(object){
		return typeof object == "function";
	},

	isString: function(object){
		return typeof object == "string";
	},

	isNumber: function(object){
		return typeof object == "number";
	},

	isBoolean: function(object){
		return typeof object == "boolean";
	},

	isUndefined: function(object){
		return typeof object == "undefined";
	},
	
	isEmpty: function(object) {
		return null==object?true:!isUndefined(object);
	}
});
//dynamic extend
Object.extendClass(Object.prototype,{
	//To be developed
});
/******************************************************************[ Object Extend end ]*****************************************************************/
/******************************************************************[ String Extend start ]***************************************************************/
Object.extendClass(String.prototype,{
	//字符串是否为空
	isEmpty:function(){
		return this=="";
	},	
	//判断两个值是否相等
	equals: function(str){
		return this==str;
	},
	//清除左右两边的空格,注意有一个空格是特殊的，智能ABC输入法v1产生的空格
	trim: function(ABCBoolean){
		//return (ABCBoolean)?this.replace(/(^(\s|　)*)|((\s|　)*$)/g, ""):this.replace(/(^(\s)*)|((\s)*$)/g, "");
		return this.lTrim(ABCBoolean).rTrim(ABCBoolean);
	},

	lTrim: function(ABCBoolean){
		return (ABCBoolean==true)?this.replace(/(^(\s|　)*)/g, ""):this.replace(/(^(\s)*)/g, "");
	},

	rTrim: function(ABCBoolean){
		return (ABCBoolean==true)?this.replace(/((\s|　)*$)/g, ""):this.replace(/((\s)*$)/g, "");
	},
	
	//计算字符串的字符长度(双字节字符长度为2)，和length不一样
	charLen: function(len) {
		var length = (len == null || len < 2) ? 2 : (len > 5) ? 5 : len;
		var str = "";
		for(var i = 0; i < length; i++)
			str += "*";
		return this.replace (/[^\x00-\xff]/g,str).length;
		//return (this.length + this.match(/[^\x00-\xff]/g).length;
	},

	//字符串是否全是数字,返回：true[是]、false[不是]
	isNumber: function(){
		return /^\d+$/g.test(this);
		//return (this.length>0)?!isNaN(this):false;
	},

	//获取字符串中的所有数字
	getNumber: function(){
		return this.replace(/\D/g,"");
	},
	
	//字符串中是否有数字,true[有]、false[没有]
	hasNumber: function(){
		return /^\d+$/g.test(this.replace(/\D/g,""));
	},
	
	//是否是汉字
	isChinese: function(){
		return (escape(this).indexOf("%u")!=-1);
		//return /^[\u0391-\uFFE5]+$/.test(this);
	},
	
	//是否有汉字
	hasChinese: function(){
		return /^[\u4e00-\u9fa5]+$/g.test(this);
	},
	
	//是否是双字节字符,包括汉字
	isDoubleChar: function(){
		return /^[^\x00-\xff]+$/g.test(this);
	},
	
	//是否有全角字符
	hasSBCCase: function(){
		return /^[^\uFF00-\uFFFF]+$/g.test(this);
	},
	
	//字符串是否全是字母,返回：true[是]、false[不是]
	isLetter: function(){
		return /^[a-zA-Z]+$/g.test(this);
	},

	//字符串是否全是小写字母,返回：true[是]、false[不是]
	isLowerCase: function(){
		return /^[a-z]+$/g.test(this);
	},

	//字符串是否全是大写字母,返回：true[是]、false[不是]
	isUpperCase: function(){
		return /^[A-Z]+$/g.test(this);
	},

	//获取字符串中的所有字母
	getLetter: function(){
		return this.replace(/[^a-zA-Z]/g,"");
	},

	//获取字符串中的所有大写字母
	getLowerCase: function(){
		return this.replace(/[^A-Z]/g,"");
	},

	//获取字符串中的所有小写字母
	getUpperCase: function(){
		return this.replace(/[^a-z]/g,"");
	},

	//字符串中是否有字母,true[有]、false[没有]
	hasLetter: function(){
		return /^[a-zA-Z]+$/g.test(this.replace(/[^a-zA-Z]/g,""));
	},

	//字符串中是否有大写字母,true[有]、false[没有]
	hasLowerCase: function(){
		return /^[A-Z]+$/g.test(this.replace(/[^A-Z]/g,""));
	},

	//字符串中是否有小写字母,true[有]、false[没有]
	hasUpperCase: function(){
		return /^[a-z]+$/g.test(this.replace(/[^a-z]/g,""));
	},
	
	//清除字符串的最后一个字符.
	delLastChar: function(){
		return this.substring(0,this.length-1);
	},
	
	//URL
	isURL: function(){
		return /^http:\/\/[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^<>\"\"])*$/.test(this.trim(true).toLowerCase());
		//return /^http:\/\/([\w-]+\.)+[\w-]+(/[\w-./?%&=]*)?$/g.test(this);
	},
	
	//将这个字符串输出到文档中，不换行。
	print: function(){
		document.write(this);
	},

	//将这个字符串输出到文档中，并换行。
	println: function(){
		document.writeln(this);
	},

	//将HTML转换为字符串
	escapeHTML: function(){
		return this.replace(/&/g,'&amp;').replace(/</g,'&lt;').replace(/>/g,'&gt;').replace(/'/g,'’');
	},

	//将字符串转换为HTML
	unescapeHTML: function() {
		return this.replace(/&gt;/g,'>').replace(/&lt;/g,'<').replace(/&amp;/g,'&');
	},
	
	//将字符串中的HTML标记清除
	clearHTML: function(){
		return this.replace(/\<[^\>]*\>/g,"");
	},
	
	//连接地址转意
	urlEncode: function(){
		return this.replace(new RegExp("%","gm"),"%25").replace(new RegExp("\\?","gm"),"%3f").replace(new RegExp("&","gm"),"%26").replace(new RegExp("=","gm"),"%3d").replace(new RegExp("\\|","gm"),"%7c");
	},
	
	//连接地址转意
	urlDeEncode: function(){
		return this.replace(new RegExp("%25","gm"),"%").replace(new RegExp("%3f","gm"),"?").replace(new RegExp("%26","gm"),"&").replace(new RegExp("%3d","gm"),"=").replace(new RegExp("%7c","gm"),"|");
	},
	
	//检查某一个字符串是否存在特指的一系列字符串
	isExistStr: function(checkStr){//有待完善
		return (checkStr.constructor == RegExp)?checkStr.test(this):(typeof checkStr=="string" || checkStr.constructor == String)? new RegExp(checkStr,"gm").test(this):false;
	},
	
	//是否是邮箱
	chekEmail: function (){
		return /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-]+\.)+[a-zA-Z0-9_-]+$/.test(this); 
	},

	//是否是手机号码
	checkMobile: function (){
		var regYT=/^((13[4-9]{1})|(15[089]{1}))\d{8}$/;  //移动
		var regLT=/^((13[0-3]{1})|(15[36]{1}))\d{8}$/;  //联通
		var regWT=/^18[89]{1}\d{8}$/;  //网通(3G)
		var regXLT=/^...$/;  //小灵通
		return regYT.test(this) || regLT.test(this) || regWT.test(this);
	},
	
	//是否是电话号码。
	isPhone: function (){
		var phone = /^((\(\d{3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}$/;
		var phoneAreaCode = /^[0-9]{2,3}-[0-9]{3,4}-[0-9]{7,8}$/; //这个是带 国家代码和区号的
		return phone.test(this) || phoneAreaCode.test(this);
	},
	
	//身份证
	isCard: function(){
		return /^\d{15}(\d{2}[xX0-9])?$/.test(this);
	},
	
	//限定长度,用以判断字符串长度是否在特定的范围之内
	limitLen: function(min,max){
		return this.length>=min && this.length<=max;
	},

	//English;
	isEnglish: function (){
		return /^[A-Za-z .]+$/.test(this);
	},
	/**
	 * 浮点类型校验
	 * @param decimalDigit 小数点位数 必须大于0,验证才有效,否则对小数位数不做验证
	 * @param isSeparated 整数部分分割验证,如:12,345,456.123,true[按照分割方式验证] false[按照不分割方式验证] null[即true或false验证方式均可]
	 * @return {}
	 */
	isFloat: function(decimalDigit,isSeparated){
		return new RegExp("^[+-]?(0|([1-9]\\d"+(isSeparated==true ? "{0,2}(\\,\\d{3})*" : isSeparated==false ? "*" : "*(\\,\\d{3})*")+"))(\\.\\d"+(decimalDigit>0 ? "{"+decimalDigit+"}" : "+")+")"+(decimalDigit===0?"{0}":"?")+"$","").test(this);
	},
	/**
	 * 浮点类型数据格式化,格式化的字符串必须符合浮点类型的数据字符串,否则不进行格式化,返回原来的字符串.格式化成功之后将返回格式化后的字符串.
	 * @param isSeparated 整数部分是否进行千分位分割处理,如:12,456,789, true[分割] false[不分割,默认]
	 * @param decimalDigit 保留的小数点位数;<0则会将整数部分的数据舍弃.如:"1123".fmtFixed(1,-1)返回"1,120"
	 * @param isFillZero 小数点位数不足指定的位数fractionDigits时,是否在后面追加0,true[追加],false[不追加,默认];如:"1123".fmtFixed(1,5,1)返回"1,123.00000"
	 * @return String
	 */
	floatFormat: function(isSeparated,decimalDigit,isFillZero){
		if(this.isFloat()){
			//加1处理函数
			function roundAdd1(vIntStr){
				var len = vIntStr.length, zeroStr = "";
				for(var i = len-1, lastStr=""; i >= 0; i--){
					if((lastStr = vIntStr.substr(i,1)) != "9"){vIntStr = vIntStr.substr(0,i)+(parseInt(lastStr)+1)+zeroStr; break; } else { zeroStr += "0"; }
				}
				return zeroStr.length == len ? ("1"+zeroStr):vIntStr;
			}
			var sigin = this.match(/^[+-]?/)[0];
			var demcial = this.match(/(\.\d+)?$/g)[0];
			//截取整数部分的数据并清除1,234,567之类的格式数字的逗号
			var intStr = this.substring(sigin.length,this.length-demcial.length).replace(/,/g,"");
			demcial = demcial.replace(/^\./, "");
			//小数点处理
			if(decimalDigit===0){//保留整数
				if(demcial.length > 0 && demcial.substr(0,1) > 4){//小数点第一位数>4则进行舍入操作
					intStr = roundAdd1(intStr);
				}
			} else if(decimalDigit > 0){
				if(decimalDigit < demcial.length) {
					var round = parseInt(demcial.substr(decimalDigit,1));
					demcial = demcial.substr(0,decimalDigit);
					if(round > 4) {//小数位数的后一位>4则进行舍入操作
						if(/^9+$/g.test(demcial)) {//小数部分0.9999999,舍入时整数部分要加1,小数部分均为0
							demcial = "";
							intStr = roundAdd1(intStr);
						} else {//小数部分最后一个非9的数字加1
							demcial = roundAdd1(demcial);
						}
					}
				}
			} else if(decimalDigit<0) {//舍弃整数
				intStr = (-decimalDigit)>=intStr.length ? "0" : intStr.substr(0,intStr.length+decimalDigit).append("0",-decimalDigit);
			}
			if(isSeparated === true && intStr.length>3){//整数部分千分位分割处理
				var l = intStr.length%3?intStr.length%3:3;
				intStr = intStr.slice(0,l)+intStr.slice(l).replace(/(\d{3})/g,",$1");
			}
			if(decimalDigit===0)
				return sigin+intStr;
			demcial = isFillZero === true ? demcial.append("0",decimalDigit-demcial.length) : demcial.replace(/0+$/,"");
			return sigin + intStr + (demcial.length>0 ? ("."+demcial):demcial);
		}
		return this;
	},
	/**
	 * 给String追加指定个数的字符串
	 * @param addStr 要追加的字符串
	 * @param addNum 追加的个数
	 * @param isBeForeAfter 在前面追加,还是在后面追加,true[前面追加] false[后面追加],默认为false;
	 * @return 返回追加之后的字符串
	 */
	append:function(addStr,addNum,isBeForeAfter){
		var reStr = this;
		for(var i=0;i<addNum;i++)
			reStr = isBeForeAfter == true ? addStr+reStr : reStr+addStr;
		return reStr;
	},

	isIP: function(){
		return /(([1-9]?\d|1\d{2}|2[0-4]\d|25[0-5])\.){3}([1-9]?\d|1\d{2}|2[0-4]\d|25[0-5])/g.test(this);
	},

	file:{
		//有待封装
	},
	
	//getter File name
	fileName: function(){
		return this.substring(this.lastIndexOf("\\")+1,this.lastIndexOf("."));
	},
	
	//getter File type
	fileType: function(){
		return this.substring(this.lastIndexOf(".")+1);
	},
	
	/**
	 * Validate File Type, param fileType is Array or String. param separator is String and length is 1,default value is |
	 * for example:
	 * "D:\\ico\button.gif".fileTypeValidate(["zip","rar","gif","jpg"],"|"); return true
	 * "D:\\ico\button.gif".fileTypeValidate(["zip","rar","mp3","jpg"]); return false
	 * "D:\\ico\button.gif".fileTypeValidate("zip|rar|gif|jpg","|"); return true
	 * "D:\\ico\button.gif".fileTypeValidate("zip|rar|mp3|jpg","|"); return false
	 */
	fileTypeVerify: function(fileType,separator){
		if(Object.isArray(fileType))
			return new RegExp("^("+fileType.join("|")+")$","gi").test(this.fileType());
		else if (Object.isString(fileType) && Object.isString(separator))
			return new RegExp("^("+(Object.isString(separator)?fileType.split(separator):fileType.split("|")).join("|")+")$","gi").test(this.fileType());
		else
			return false;
	},

	// 截取固定长度的子字符串，遇到双字节字符按照两个长度计算
	subChar: function (startLen,endLen){
		var endLenTemp = this.replace(/[^\x00-\xff]/g,"  ").length;
		endLen = (endLen>endLenTemp)?endLenTemp:endLen;
		startLen = (startLen >= endLen || startLen < 0)?0:startLen;
		var reStr = "", reLen = startLen, schar;
		for(var i=startLen; schar=this.charAt(i); i++) {
			reLen += (/[^\x00-\xff]/.test(schar) ? 2 : 1);
			if(reLen > endLen)
				break;
			reStr += schar;
		}
		return reStr;
	}
});
/******************************************************************[ String Extend end ]***************************************************************/
/******************************************************************[ Array Extend start ]*************************************************************/
/*请结合JavaScript中的Array对象的内置方法，如:
 * concat 方法 | join 方法 | pop 方法 | push 方法 | reverse 方法 | shift 方法 | slice 方法 | sort 方法 | splice 方法 | toLocaleString 方法 | toString 方法 | unshift 方法 | valueOf 方法
 * 结合这些方法之后可以说是已经够用了，除非你不完全了解上面的这些方法。
 */
Object.extendClass(Array.prototype,{
	//查找数组中的某一个值的下标,有重复值的时候返回第一次找到的下标,否则返回-1
	indexOf: function(objValue){
		for(var i=0;i<this.length;i++)
			if(this[i]==objValue)
				return i; 
		return -1;
	},

	//查找数组中是否包含某一个值
	contain: function(objValue){
		return this.indexOf(objValue) > -1;
	},

	//清除一个数组中的所有数据
	clear: function(){
		this.length = 0;
	},

	// 删除数组中存在的某一个值，bool(true[删除数组中所有存在的objValue值]、false[删除数组中第一次找到的objValue值])
	remove: function(objValue,bool){
		var indexof = this.indexOf(objValue);
		if(indexof > -1) {
			this.splice(indexof,1);//注意这个方法，调用的是Array的内置方法splice
			if(bool)
				this.remove(objValue,bool);//递归
		}
	},
	
	//移除数组中重复的元素
	removeRepeat: function(){
		if(this.length>1){
			for(var i = 0;i<this.length;i++){
				var temp = this[i];
				for(var j=i+1;j<this.length;j++){
					if(this[j]==temp){
						this.splice(j,1);//移除之后后面的元素会向前顺移
						j--;//目的是保持原位置不动
					}
				}
			}
		}
	},
	
	//比较两个数组的长度是否相同
	matchLen: function(arrObj){
		return arrObj.constructor != Array?false:this.length==arrObj.length;
	}
});
/******************************************************************[ Array Extend end ]***************************************************************/
/******************************************************************[ Date Extend start ]**************************************************************/
Object.extendClass(Date.prototype,{
	//判断日期是否是闰年
	isLeapYear: function(){
		var year=this.getYear()
		return (year%4==0&&year%100!=0) || year%400==0;
	},
	//获取某年某月的最大天数
	getMonthMaxDay: function(){
		var _month = this.getMonth()+1;
		if(_month==4||_month==6||_month==9||_month==11)	return "30";
		if(_month==2)
			return (this.isLeapYear())?"29":"28";
		return "31";
	},
	//日期格式化。
	//ex:var ddd = new Date();document.write (ddd.format('yy-MM-dd hh:mm:ss'));
	format: function(strFormat){
		var dateArr = {
			"M+" : this.getMonth()+1, //month
			"d+" : this.getDate(),    //day
			"h+" : this.getHours(),   //hour
			"m+" : this.getMinutes(), //minute
			"s+" : this.getSeconds(), //second
			"q+" : Math.floor((this.getMonth()+3)/3),  //quarter
			"S" : this.getMilliseconds() //millisecond
		}
		if(/(y+)/.test(strFormat))
			strFormat=strFormat.replace(RegExp.$1,(this.getFullYear()+"").substr(4 - RegExp.$1.length));
		for(var i in dateArr)
			if(new RegExp("("+ i +")").test(strFormat))
				strFormat = strFormat.replace(RegExp.$1,RegExp.$1.length==1 ? dateArr[i] : ("00"+ dateArr[i]).substr((""+ dateArr[i]).length));
		return strFormat;
	},
	//比较两个日期之间的大小差距,返回的float型，以秒为单位。
	//如果传入的参数不是时间类型，则返回字符串"传入的参数不是Date类型"
	//如:
	//var date1 = new Date();
	//var date2 = new Date(2008,04,01,22,43,0,1000);
	//var _str = date1.comparison(date2);
	comparison: function(date){
		return (date instanceof Date)?(this-date)/1000:"传入的参数不是Date类型";
	},
	//比较两个时间之间的范围,返回一个String型的范围差距
	//如:
	//var date1 = new Date();
	//var date2 = new Date(2008,04,01,22,43,0,1000);
	//var _str = date1.comparisonTimeRange(date2);
	comparisonTimeRange: function (date){
		var secondNum = this.comparison(date);
		if(parseInt(secondNum/(60*60*24*7)) > 4)
			return date.format("yyyy-MM-dd hh:mm:ss");
		else if(parseInt(secondNum/(60*60*24*7)) > 0 && parseInt(secondNum/(60*60*24*7)) <= 4)
			return parseInt(secondNum/(60*60*24*7))+" 周前";
		else if (parseInt(secondNum/(60*60*24)) > 0)
			return parseInt(secondNum/(60*60*24))+" 天前";
		else if(parseInt(secondNum/(60*60)) > 0)
			return parseInt(secondNum/(60*60))+" 小时以前";
		else if(parseInt(secondNum/60) > 0)
			return parseInt(secondNum/60)+" 分钟前";
		else
			return parseInt(secondNum)+" 秒钟前";
	},
	compareTo:function(otherDate){
	}
});

/******************************************************************[ Date Extend end ]*****************************************************************/
/******************************************************************[ Math Extend start ]***************************************************************/
//static extend
Object.extendClass(Math,{
	//求某一个正整数的阶乘
	factorial: function(n){
		return (n <= 0)?1:n * arguments.callee(n - 1);//callee是对函数自身的引用
	},
	/** 求多个数字的和 */
	sum: function(){
		var count = 0;
		for(var i = 0; i < arguments.length; i++){
			if(Object.isArray(arguments[i])){
				for(var j = 0; j < arguments[i].length; j++){
					if(typeof(arguments[i][j])=="string" && parseFloat(arguments[i][j])!="NaN") {
						count += parseFloat(arguments[i][j]);
					} else if(typeof(arguments[i][j])=="number") {
						count += arguments[i][j];
					}
				}
			} else if(typeof(arguments[i])=="string" && parseFloat(arguments[i])!="NaN") {
				count += parseFloat(arguments[i]);
			} else if(typeof(arguments[i])=="number") {
				count += arguments[i];
			}
		}
		return count;
	},
	avg: function(){
		
	},
	stddev: function(){
		
	}
});

//dynamic extend
Object.extendClass(Math.prototype,{
	//To be developed
});
/******************************************************************[ Math Extend end ]*****************************************************************/
/******************************************************************[ Number Extend start ]***************************************************************/
//static extend
Object.extendClass(Number,{
	//To be developed
});

//dynamic extend
Object.extendClass(Number.prototype,{
	fixDecimal: function(num){
		with(Math) return round(this*pow(10,num))/pow(10,num);
	}
});
/******************************************************************[ Number Extend end ]*****************************************************************/
/******************************************************************[ Function Extend start ]***********************************************************/
//static extend
Object.extendClass(Function,{
	//To be developed
});

//dynamic extend
Object.extendClass(Function.prototype,{
	
	equals: function(func){
		return this.toString().equals(func.toString());
	}
});
/******************************************************************[ Function Extend end ]*************************************************************/
/******************************************************************[ Boolean Extend start ]************************************************************/
//static extend
Object.extendClass(Boolean,{
	//To be developed
});

//dynamic extend
Object.extendClass(Boolean.prototype,{	
	equals: function(bool){
		return (this==bool)?true:(bool instanceof Boolean)? this.toString().equals(bool.toString()):false;
	}
});
/******************************************************************[ Boolean Extend end ]**************************************************************/

JSClassMap = function(){
	var mangArray = new Array();
	/**
	 * @param key 键 必选项
	 * @param object 值 必选项
	 * @param overlay 覆盖标记 可选项 true[覆盖],false[不覆盖],不传此参数或非boolean值则默认为true
	 */
	this.push=function(key,object,overlay){
		var overlayFlag = arguments[2];
		overlayFlag = (null == overlayFlag || typeof overlayFlag == "undefined" || typeof overlayFlag != "boolean" ) ? true : overlayFlag;
		var keys = this.getKeys();//获取所有的key并检测是否存在此key
		for(var i = 0;i<keys.length;i++){
			if(keys[i]==key){
				if(overlayFlag==true){
					mangArray.splice(i,1,{keyID: key,objectValue:object});//覆盖原来存在的并保持顺序不变
				}
				return;
			}
		}
		mangArray.push({keyID: key,objectValue:object});
	};
	/**
	 * 根据key获取一个具体的值,如果key不存在则返回null;
	 */
	this.get=function(key){
		for(var i=0;i<mangArray.length;i++){
			var keyValueObj = mangArray[i];
			if(keyValueObj.keyID==key)
				return keyValueObj.objectValue;
		}
		return null;
	};
	/**
	 * 获取所有的key
	 */
	this.getKeys=function(){
		var array = new Array();
		for(var i=0;i<mangArray.length;i++){
			array.push(mangArray[i].keyID);
		}
		return array;
	};
	/**
	 * 获取所有的value
	 */
	this.getValues=function(){
		var array = new Array();
		for(var i=0;i<mangArray.length;i++){
			array.push(mangArray[i].objectValue);
		}
		return array;
	};
	/**
	 * 移除Map中的某一个key/value
	 */
	this.remove=function(key){
		var delFlag = false;
		for(var i=0;i<mangArray.length;i++){
			var keyValueObj = mangArray[i];
			if(keyValueObj.keyID==key){
				mangArray.splice(i,1);
				delFlag = true;
			}
		}
		return delFlag;
	};
	/**
	 * 移除Map中所有的key/value
	 */
	this.removeAll=function(){
		mangArray.splice(0,mangArray.length);
	}
}






/**
 * 根据sourceArray二维数组在页面中的select标签内打印option标签
 * @param sourceArray 二维数组 必选项, 必须是二维数组,[[optionValue,optionText],[optionValue,optionText]]
 * @param isUnWriteValue 打印选项 必选项,一维数组,即sourceArray的optionValue集合, 传null时打印sourceArray的全部数据,否则从sourceArray中挑选出非unWriteValue数组中的数据进行打印
 * @param writeFlag isUnWriteValue是否为非打印选项,1[是-不打印符合isUnWriteValue中的内容],0[不是-打印符合isUnWriteValue中的内容];
 * @param selectValue option默认选择项,其值必须与sourceArray中的optionValue一直,否则无效
 */
function writeSelectOption(sourceArray,isUnWriteValue,writeFlag,selectValue){
	if(arguments.length >= 1 && arguments.length <= 4 && Object.isArray(arguments[0]) && arguments[0].length > 0 && Object.isArray(arguments[0][0]) && arguments[0][0].length==2){//参数验证,必须为1-4个参数,第一个参数必须为二维数组(此二维数组中的第一维也必须是数组,并且长度为2)
		if(null != isUnWriteValue && Object.isArray(isUnWriteValue) && isUnWriteValue.length>0 && (writeFlag==0 || writeFlag==1)){//第二个参数是数组并且长度>0,并且第三个参数为0或1
			for(var i = 0; i < sourceArray.length; i++){
				if(writeFlag==0 && isUnWriteValue.contain(sourceArray[i][0])){//打印存在于数组isUnWriteValue中的选项sourceArray[i][0]
					document.write("<option value=\""+sourceArray[i][0]+"\"" + (sourceArray[i][0] == selectValue ? " selected ":"") + ">"+sourceArray[i][1]+"</option>");
				} else if(writeFlag==1 && !isUnWriteValue.contain(sourceArray[i][0])){//打印不存在于数组isUnWriteValue中的选项sourceArray[i][0]
					document.write("<option value=\""+sourceArray[i][0]+"\"" + (sourceArray[i][0] == selectValue ? " selected ":"") + ">"+sourceArray[i][1]+"</option>");
				}
			}
		} else {
			for(var i = 0; i < sourceArray.length; i++)
				document.write("<option value=\""+sourceArray[i][0]+"\"" + (sourceArray[i][0] == selectValue ? " selected ":"") + ">"+sourceArray[i][1]+"</option>");
		}
	} else {
		alert("调用writeSelectOption()时,参数传递错误,参数个数为:1 <= param <=4\n第一个参数必须为二维数组\n第二个参数必须为一维数组(第一个参数的第一维的集合)\n第三个参数为数字(0或1)\n第四个参数为默认选中项.\n第二、第三、第四个参数为可选项(传第二个参数时必须传第三个参数).");
	}
}

/**
 * 根据sourceArray二维数组在页面中打印相应的字符
 * @param sourceArray 二维数组 必选项, 必须是二维数组,[[optionValue,optionText],[optionValue,optionText]]
 * @param optionValue 操作选项集合 必选项,一维数组,即sourceArray的optionValue集合, 传null时打印sourceArray的全部数据,否则从sourceArray中挑选出符合optionValue数组中的数据来进行操作
 * @param operFlag 操作标识,必选项,其值必须0或1,否则无效,0-直接打印,1-返回optionValue集合所对应的optionText
 * @param symbol 分隔符,可选项 即打印或返回多个值时,值与值之间的分隔符,不传则默认为"";
 * @example:
 * getSelectTextOperator([["0001","中国"],["0002","美国"],["0003","英国"]],["0001","0002"],1,"#"); return "中国#美国"<br>
 * getSelectTextOperator([["0001","中国"],["0002","美国"],["0003","英国"]],["0001"]); return "中国"<br>
 * getSelectTextOperator([["0001","中国"],["0002","美国"],["0003","英国"]],["0001"],0); document.write("中国")<br>
 * getSelectTextOperator([["0001","中国"],["0002","美国"],["0003","英国"]],["0004"]); return "0004"<br>
 * getSelectTextOperator([["0001","中国"],["0002","美国"],["0003","英国"]],["0004","0001"]); return "中国"<br>
 * getSelectTextOperator([["0001","中国"],["0002","美国"],["0003","英国"]],["0004","0010"]); return "00040010"<br>
 * getSelectTextOperator([["0001","中国"],["0002","美国"],["0003","英国"]],["0004","0010"],1,"#"); return "0004#0010"<br>
 */
function getSelectTextOperator(sourceArray,optionValue,operFlag,symbol){
	if(arguments.length >= 2 && arguments.length <= 4 && Object.isArray(arguments[0]) && arguments[0].length>0 && Object.isArray(arguments[0][0]) && arguments[0][0].length==2 && Object.isArray(arguments[1]) && arguments[1].length>0){//参数验证,必须为2-4个参数,第一个参数必须为二维数组(此二维数组中的第一维也必须是数组,并且长度为2),第二个参数必须为数组并且长度要大于0
		symbol = arguments.length==4 ? ((null !=symbol && typeof symbol=="string")?symbol:"") : "";
		var operStr = "";
		for(var i = 0; i < sourceArray.length; i++){
			if(optionValue.contain(sourceArray[i][0])){
				operStr = symbol+operStr+sourceArray[i][1];
			}
		}
		operStr = operStr == "" ? optionValue.join(symbol) : operStr.substring(symbol.length);
		if(operFlag==0){document.write(operStr);} else {return operStr;}
	} else {
		alert("调用getSelectTextOperator()时,参数传递错误,参数个数必须为:2 <= param <=4\n第一个参数必须为二维数组\n第二个参数必须为一维数组(第一个参数的第一维的集合)\n第三个参数为数字(0[直接打印]或1[返回])\n第四个参数为字符串分隔符(用于打印或返回多个值时每个值之间的分隔符)\n第三、第四个参数为可选项.");
	}
}

/**
 * 动态添加select的option或在页面打印option
 * @param datasourceArray 二维或四维数组
 * @param selectObj select标签的ID或select标签对象.
 * @param defaultSelCode 默认选择项
 * @param firstCode 一级代码,针对参数datasourceArray为四维数组时有效.
 * @return
 */
function selectInsertOption(datasourceArray,defaultSelCode,firstCode,selectObj){
	if(Object.prototype.toString.call(datasourceArray)==="[object Array]" && datasourceArray.length>0 && Object.prototype.toString.call(datasourceArray[0])==="[object Array]"){
		var defaultSel, dimensions = datasourceArray[0].length;
		selectObj = typeof selectObj === "string" ? document.getElementById(selectObj) : selectObj;
		if(selectObj!=null && selectObj.nodeName && selectObj.nodeName.toUpperCase()=="SELECT"){
			if(dimensions==2){//2维
				for(var i=0; i<datasourceArray.length; i++) {
					selectObj.add(new Option(datasourceArray[i][1],datasourceArray[i][0],true,datasourceArray[i][0]==defaultSelCode));
				}
			} else if(dimensions==4){//4维
				if(typeof firstCode === "string") {
					for(var i=0; i<datasourceArray.length; i++) {
						if(datasourceArray[i][0]==firstCode)
							selectObj.add(new Option(datasourceArray[i][4],datasourceArray[i][3],true,datasourceArray[i][3]==defaultSelCode));
					}
				} else {
					for(var i=0; i<datasourceArray.length; i++) {
						selectObj.add(new Option(datasourceArray[i][4],datasourceArray[i][3],true,datasourceArray[i][3]==defaultSelCode));
					}
				}
			}
		} else {
			if(dimensions==2){//2维
				for(var i=0; i<datasourceArray.length; i++) {
					document.write("<option value="+datasourceArray[i][0]+(datasourceArray[i][0]==defaultSelCode ? "selected" : "")+">"+datasourceArray[i][1]+"</option>");
				}
			} else if(dimensions==4){//4维
				if(typeof firstCode === "string") {
					for(var i=0; i<datasourceArray.length; i++) {
						if(datasourceArray[i][0]==firstCode)
							document.write("<option value="+datasourceArray[i][2]+(datasourceArray[i][2]==defaultSelCode ? "selected" : "")+">"+datasourceArray[i][3]+"</option>");
					}
				} else {
					for(var i=0; i<datasourceArray.length; i++) {
						document.write("<option value="+datasourceArray[i][2]+(datasourceArray[i][2]==defaultSelCode ? "selected" : "")+">"+datasourceArray[i][3]+"</option>");
					}
				}
			}
		}
	}
}

/**
 * 
 */
function selectOption(insertArray,selFlag,writeItem,selectObj,index){
	if(null != insertArray && Object.isArray(insertArray) && insertArray.length > 0 && Object.isArray(insertArray[0]) && insertArray[0].length==2) {
		for(var i = 0; i < insertArray.length; i++)
			selectObj.add(new Option(insertArray[i][1],insertArray[i][0]), index++);
		//默认选中设置
		if (typeof selFlag == "number" && selFlag < selectObj.options.length && selFlag>=0) {
			selectObj.options[selFlag].selected=true;
		} else if (typeof selFlag == "string"){
			for(i=0;i<selectObj.options.length;i++){
				if(selectObj.options[i].value==selFlag){
					selectObj.options[i].selected=true;
					break;
				}
			}
		}
	}
	var optionArray = new Array();
	if(Object.isArray(writeItem) && writeItem.length > 1 && Object.isArray(writeItem[0]) && writeItem[0].length > 0){//必须符合这种形式:[[""],0]或者[[""]]
		if(writeItem.length < 2 || (writeItem[1] != 0 && writeItem[1] != 1))
			writeItem[1] = 0;
		for(var i = 0; i < insertArray.length; i++){
			if((writeItem[0].contain(insertArray[i][0]) && writeItem[1]==1) || (!writeItem[0].contain(insertArray[i][0]) && writeItem[1]==0)){
				optionArray.push(insertArray[i]);
			}
		}
	} else {
		optionArray = insertArray;
	}
	
	if (null != selectObj && selectObj.nodeType==1 && selectObj.nodeName.toUpperCase()=="SELECT"){
		index = (typeof index == "number" && index>=0 && index<selectObj.options.length) ? index : selectObj.options.length;
		//option(text,value,defaultSelected(true or false),selected(true or false))
		for(var i = 0; i < optionArray.length; i++)
			selectObj.add(new Option(optionArray[i][1],optionArray[i][0]), index++);
		
	} else {
		
	}
}

/**
 * 删除select标签下的option
 * @param selectObj 必选项 select标签对象
 * @param delOption 必选项 三种可选值,[数字,字符串,数组(元素为数字或字符串均可,数字时按照options的下标索引值删除,字符串时按照option的value值对应删除)],为null或不传则删除全部
 * @param endOption 可选项 数字,只能为数字时才有效,此参数匹配第二个参数delOptin为数字时区间删除对应的options
 * @example
 * <select id=selectObj>
 * 	<option value=A>1</option>
 * 	<option value=B>2</option>
 * 	<option value=C>3</option>
 * 	<option value=D>4</option>
 * 	<option value=E>5</option>
 * 	<option value=F>6</option>
 * </select>
 * removeSelectOption(selectObj);//删除全部
 * removeSelectOption(selectObj,2,5);//删除<option value=C>3</option><option value=D>4</option><option value=E>5</option><option value=F>6</option>
 * removeSelectOption(selectObj,'D'); //删除<option value=D>4</option>
 * removeSelectOption(selectObj,[1,'C','E',5]);//删除<option value=B>2</option><option value=F>6</option><option value=C>3</option><option value=D>4</option>
 */
function selectRemoveOption(selectObj,delOption,endOption){
	if(null != selectObj && selectObj.nodeType==1 && selectObj.nodeName.toUpperCase()=="SELECT" && selectObj.opgions.length > 0){
		if(null == delOption){
			for(var i = 0; i < selectObj.options.length;)
				selectObj.remove(i); //selectObj.options[i] = null也可以
		} else if(typeof delOption == "number"){
			if(typeof endOption == "number"){
				if(endOption < selectObj.options.length) //区间删除对应的option
					for(var i = endOption; i <= delOption && i >= 0; i--)
						selectObj.remove(i);
			} else {//删除某个对应的option
				selectObj.remove(delOption);//selectObj.options[delOption] = null也可以
			}
		} else if(typeof delOption == "string"){
			for(var i = 0; i < selectObj.options.length;){
				if(selectObj.options[i].value==delOption){
					selectObj.remove(i);
					break;
				}
			}
		} else if (delOption instanceof Array){
			//数组中有数字,则按照下标删除
			for(var i = 0; i < delOption.length; i++);{
				if(typeof deOption[i] == "number"){
					selectObj.remove(delOption[i]);delOption.slice(i); i--;
				}
			}
			//再按照数组中的字符串对应select的values的值对应删除
			for(var i = 0; i < selectObj.options.length; i++){
				for(var j = 0; j < delOption.length; j++){
					if(typeof delOption[j]=="string" && deloption[j] == selectObj.options[i].value){
						selectObj.options[i] = null;
					}
				}
			}
		}
	}
}

/**
 * 将某一个select元素转换为数组,返回这个数组,其值的形式为:[[optionValue,optionText],[optionValue,optionText]]
 * @param selectObj select对象
 */
function selectOptionToArray(selectObj,getOption,endOption){
	var reArray = new Array();
	if(null != selectObj && selectObj.nodeType==1 && selectObj.nodeName.toUpperCase()=="SELECT"){
		if(null == getOption) {
			for(var i = 0; i < selectObj.options.length; i++)
				reArray.push([selectObj.options[i].value,selectObj.options[i].text]);
		} else if(Object.isNumber(getOption)){
			if(Object.isNumber(endOption)){
				for(var i = getOption; i < endOption; i++)
					reArray.push([selectObj.options[i].value,selectObj.options[i].text]);
			} else {
				reArray.push([selectObj.options[getOption].value,selectObj.options[getOption].text]);
			}
		} else if(Object.isString(getOption)){
			for(var i = 0; i < selectObj.options.length; i++) {
				if(selectObj.options[i].value == getOption){
					reArray.push([selectObj.options[i].value,selectObj.options[i].text]);
					break;
				}
			}
		} else if(Object.isArray(getOption)){
			//数组中有数字,则按照下标删除
			for(var i = 0; i < getOption.length; i++);{
				if(Object.isNumber(getOption[i])){
					reArray.push([selectObj.options[i].value,selectObj.options[i].text]); getOption.slice(i); i--;
				}
			}
			//再按照数组中的字符串对应select的values的值对应删除
			for(var i = 0; i < selectObj.options.length; i++){
				for(var j = 0; j < getOption.length; j++){
					if(Object.isString(getOption[i]) && getOption[j] == selectObj.options[i].value){
						reArray.push([selectObj.options[i].value,selectObj.options[i].text]);
					}
				}
			}
		}
	}
	return reArray;
}

/**
 *==========================================================================================================================
 *
 *                                                       << 其它扩充 >>
 *    此文件是专门写一些特定的方法以供使用
 *
 *==========================================================================================================================
 */
//获取DOM中ID为:propID的一个标签对象,如果DOM中有多个相同的ID，则只返回首先找到的第一个标签。
function $id(propId) {
	if(document.getElementById) 
		return document.getElementById(propId);
	else if(document.all)
		return document.all[propId];
	else if(document.layers)
		return document.layers[propId];
}

//获取DOM中标签名称为:tagName的一组标签对象
function $tag(tagName){
	if(document.getElementsByTagName) 
		return document.getElementsByTagName(tagName);
	else if(document.all)
		return document.all[tagName];
	else if(document.layers)
		return document.layers[tagName];
}

//获取DOM中name名称为:propName的一组标签对象，此方法只适用于标签本身内置的name属性，自己手动添加的name属性不具备此功能。
/*如:
 * <input name="username"/> 有效
 * <div name="username"></div> 无效，因为此标签本身并不具备name此属性，是人为添加的属性。
 */
function $name(propName){
	if(document.getElementsByName) 
		return document.getElementsByName(propName);
	else if(document.all)
		return document.all[propName];
	else if(document.layers)
		return document.layers[propName];
}
//获取一个iFrame中的标签ID为strTagID的标签对象
function $iframeID(iframeID,strTagID) {
	if(document.getElementById){
		return $iframe(iframeID).getElementById(strTagID);}
	else if(document.all)
		return $iframe(iframeID).all[strTagID];
	else if(document.layers)
		return $iframe(iframeID).layers[strTagID];
}
//获取一个iFrame中的标签名称为strTagName的一组标签对象
function $iframeName(iframeID,strTagName) {
	if(document.getElementsByName) 
		return $iframe(iframeID).getElementsByName(strTagName);
	else if(document.all)
		return $iframe(iframeID).all[strTagName];
	else if(document.layers)
		return $iframe(iframeID).layers[strTagName];
}
//获取一个iFrame中的标签为strTag的一组标签对象
function $iframeTag(iframeID,strTag) {
	if(document.getElementsByTagName) 
		return $iframe(iframeID).getElementsByTagName(strTag);
	else if(document.all)
		return $iframe(iframeID).all[strTag];
	else if(document.layers)
		return $iframe(iframeID).layers[strTag];
}
//获取一个iFrame中的DOM对象
function $iframe(iframeID) {
	var iframeObj = $id(iframeID);
	if (iframeObj.contentDocument) // For NS6
        return iframeObj.contentDocument; 
    else if (iframeObj.contentWindow) // For IE5.5 and IE6
        return iframeObj.contentWindow.document;
    else if (iframeObj.document)  // For IE5
        return iframeObj.document;
}

//获取页面中所有的标签名称为strTagName的属性为strVar的值，并以smbol作为分割符
function getTagNameVarValues(strTagName,strVar,smbol){
	var reStrValue="";
	var tagNameArray=$name(strTagName);
	var len = tagNameArray.length;
	for(var i=0; i<len; i++)
		reStrValue+=tagNameArray[i].getAttribute(strVar)+smbol;
	return reStrValue.delLastChar();
}

//设置选择框的显示标题，当鼠标移到checkbox控件上时显示，应用于onmousemove事件
function setCheckboxChooseTitle(childCheckboxName,checkboxObj){
	var isChildCheckbox=$name(childCheckboxName);
	if(isChildCheckbox.length==0)
		checkboxObj.title = "没有可选项";
	else if(checkboxObj.checked)
		checkboxObj.title = "点击取消选中记录";
	else
		checkboxObj.title = "点击选取全部记录";
}

//通过一个布尔值或checkbox对象来设置所有名称为checkboxName的checkbox处于选中或未选中状态
//ex: checkboxChoose('msgName',true); checkboxChoose('msgName','false'); <input type='checkbox' onckick='checkboxChoose('msgName',this)'/>
function setCheckboxChoose(checkboxName,objValue){
	var bool = objValue.constructor == Boolean ? objValue:(objValue=='true') ? true:(objValue=='false') ? false:objValue.checked;
	var checkNameArr=$name(checkboxName);
	var len=checkNameArr.length;
	for (var i=0; i<len; i++)
		checkNameArr[i].checked=bool;
}

//获取页面中所有的checkbox标签的名称为strTagName的属性为strVar的值，并以smbol作为分割符,boolValue(true[获取选中的]、false[获取未选中的]、其它[无论选中或未选中都获取相应的属性值])
function getCheckboxVarValues(strTagName,strVar,smbol,boolValue){
	var reStrValue="";
	var checkboxArray=$name(strTagName);
	var len = checkboxArray.length;
	for(var i=0; i<len; i++){
		if(Object.isBoolean(boolValue)){
			if(checkboxArray[i].checked==boolValue)
				reStrValue+=checkboxArray[i].getAttribute(strVar)+smbol;
		}else
			reStrValue+=checkboxArray[i].getAttribute(strVar)+smbol;
	}
	return reStrValue.delLastChar();
}

/**
 * 查找页面中所有的checkbox标签被选中项,或未被选中项的个数
 * @param checkboxName checkbox对象的名称name
 * @param bool 要查找的是选中项还是未选中项
 */
function checkboxChoose(checkboxName,bool){
	var checkboxArray=$name(checkboxName);
	var returnCount = 0;
	var len = checkboxArray.length;
	for(var i=0; i<len; i++){
		if(checkboxArray[i].checked == bool){
			returnCount++;
		}
	}
	return returnCount;
}

//获取页面中所有的select标签的名称为strTagName的属性为strVar的值，并以smbol作为分割符,如果strVar==""|| strVar=null则获取标签select的值，而不是属性值.
function getSelectValues(strTagName,strVar,smbol){
	var reStrValue="";
	var selectArray=$name(strTagName);
	var len = selectArray.length;
	for(var i=0; i<len; i++){
		if(null !== strVar && strVar != "")
			reStrValue += selectArray[i].getAttribute(strVar)+smbol;
		else
			reStrValue += selectArray[i].value+smbol;
	}
	return reStrValue.delLastChar();
}

//设置页面中table标签的奇偶数行的样式
function tableTrClass(tableId, oddClassName, evenClassName,start){
	var trArr=$id(tableId)?$id(tableId).getElementsByTagName("tr"):[];
	for (var i=start;i<trArr.length;i++)
		trArr[i].className = (i%2>0)?oddClassName:evenClassName;
}

//打开一个窗口
function winopen(url,target){
	window.open(url,target,"");
}
//按照模式打开一个窗口
function winopenmodel(httpUrl,target,winWidth,winHeight,winModel) {
	if(winModel == "0" || winModel == 0)
		window.showModalDialog(httpUrl,target,"scroll:1;status:0;resizable:0;dialogWidth:"+winWidth+"px;dialogHeight:"+winHeight+"px;help:no")
	else if(winModel == "1" || winModel == 1)
		window.showModelessDialog(httpUrl,target,"scroll:1;status:0;resizable:0;dialogWidth:"+winWidth+"px;dialogHeight:"+winHeight+"px;help:no")
	else
		window.open(httpUrl,target,"width:"+winWidth+"height:"+winHeight+"alwaysLowered:no;alwaysRaised:yes;depended:no;directories:yes;hotkeys:yes;innerHeight:;innerWidth:;location:no;menubar:no;outerHeight:;outerWidth:;resizable:no;screenX:;screenY:;scrollbars:yes;titlebar:yes;toolbar:no;z-look:yes;");
}
//返回上一个窗口
function winback(){
	window.history.back();
}
//提交某一个表单
function formSubmit(formID){
	$id(formID).submit();
}
//交换两个对象之间里面的HTML
function exchangeHTML(objOne,objTwo){
	var objHTML=objOne.innerHTML;
	objOne.innerHTML=objTwo.innerHTML;
	objTwo.innerHTML=objHTML;
}

//交换两个对象之间里面的属性名称为:propertyName的值
function exchangePropertyValue(objOne,objTwo,propertyName){
	var propertyValue=objOne.getAttribute(propertyName);
	objOne.setAttribute(propertyName,objTwo.getAttribute(propertyName));
	objTwo.setAttribute(propertyName,propertyValue);
}

//交换两个节点，包括节点内的内容
function exchangeNode(firstNode,secondNode){
	if(firstNode.parentNode!=null || secondNode.parentNode!=null){
		firstNode.parentNode.replaceChild(secondNode.cloneNode(true),firstNode);
		secondNode.parentNode.replaceChild(firstNode.cloneNode(true),secondNode);
	} else {
		alert("Node into the parameters must have the father node!");//提示:传入的节点参数必须有父节点
	}
}

//比较两个日期的大小，startIntStrDate<=endIntStrDate,则返回true，否则返回false;
function compareDate(startDate,endDate){
	return parseInt(startDate.getNumber()) <= parseInt(endDate.getNumber());
}

//JavaScript---10行代码实现导出成Excel
function preview() { 
	window.clipboardData.setData("Text",document.all('table1').outerHTML);
	try{
	var ExApp = new ActiveXObject("Excel.Application")
	var ExWBk = ExApp.workbooks.add()
	var ExWSh = ExWBk.worksheets(1)
	ExApp.DisplayAlerts = false
	ExApp.visible = true
	}catch(e){
	    alert("您的电脑没有安装Microsoft Excel软件！")
	    return false
	} 
	ExWBk.worksheets(1).Paste;    
}

/*Object.extendClass(JSClass,{
	function win(content,potions){
		this.winPotion = {
			iframeScroll: "no",//iframe滚动条设置
			draggable: true,//是否设置拖动
			width: 300,//宽度设置,偏差55
			height: 200,//高度设置,偏差8
			top: 50,
			left: 200,
			zIndex: 1000//设置显示Z坐标的位置
		};
		
		this.contOption = {
			title: "",
			content: null,
			close: true,//是否显示关闭按钮
			stateBar: true,//是否显示状态栏
			contFlag: true//true[iframe],false[DOM标签对象]
		};
		
		var JSClassWinDIV = document.createElement("div");
		with(JSClassWinDIV){
			position = "absolute";
			left = winPotion.left;
			top = winPotion.top;
			width = winPotion.width + 55;
			height = winPotion.height + 8;
			zIndex = winPotion.zIndex;
			border = "1px solid #999999";
		}
		
		this.init = function(){
			//初始化winPotion
			winPotion.iframeScroll = (potions.iframeScroll) ? potions.iframeScroll : winPotion.iframeScroll;
			winPotion.draggable = (potions.draggable) ? potions.draggable : winPotion.draggable;
			winPotion.width = (potions.width) ? potions.width : winPotion.width;
			winPotion.height = (potions.height) ? potions.height : winPotion.height;
			winPotion.top = (potions.top) ? potions.top : winPotion.top;
			winPotion.left = (potions.left) ? potions.left : winPotion.left;
			winPotion.zIndex = (potions.zIndex) ? potions.zIndex : winPotion.zIndex;
			//初始化contOption
			contOption.title = (content.title) ? content.title : contOption.title;
			contOption.content = (content.content) ? content.content : contOption.content;
			contOption.stateBar = (content.stateBar) ? content.stateBar : contOption.stateBar;
			contOption.contFlag = (content.contFlag) ? content.contFlag : contOption.contFlag;
		};
		
		
		this.initPage = function(){
			var domTableHTML = "<table width='100%' height='100%' border='0' cellpadding='0' cellspacing='0'>";
			domTableHTML += "<tr>";
			domTableHTML += "	<td height='20' valign='middle'>";
			domTableHTML += "		<div style='float:left;background:#6699FF; width:100%;'>";
			domTableHTML += "			<span id='JSClassWinTitle'  style='float:left; height:10px; padding:5px; text-align:center; margin:3px;font-size:13px;'>" + this.title + "</span>";
			domTableHTML += "			<span style='float:right; width:10px; height:10px; padding:5px; background:#CCCCCC; cursor:pointer; text-align:center; margin:3px;font-size:13px;'>X</span>";
			domTableHTML += "		</div>";
			domTableHTML += "	</td";
			domTableHTML += "</tr>";
			
			var domIframeHTML = "<tr><td><div style='padding:2px;'><iframe  id='JSClassWinIframe' width='" + winPotion.width + "' height='" + winPotion.height +" scrolling='no' src='" + this.url +"'></iframe></div></td></tr>";
			
			var winStatusHTML = "<tr>"; 
			winStatusHTML += "	<td height='20' valign='middle'>"; 
			winStatusHTML += "		<div style='float:left; background:#CCCCCC;width:100%;'><span style='float:left; font-size:13px; margin:3px;'>状态栏</span></div>"; 
			winStatusHTML += "	</td>";
			winStatusHTML += "<tr>"; 
		};
	}
//});*/