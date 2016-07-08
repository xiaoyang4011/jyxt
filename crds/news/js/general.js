
/*--
 * 2004-11-15 弹出在线帮助窗口getOnlineHelp
 * 2004/11/01 更新 isNotNull ,str = str.trim()而不是 trim(str) （这是很久以前错误，不知哪个还原的）
 *            在isNumber 加上 return true;
 * 2005-02-17 zhoujf 增加整理注释
--*/

//-----效验-------------------------------------------------------------------------------------------------------
/** 
 * 功能：　判断是否日期函数
 * 输入：　srcStr 欲判断字符串, sFieldName 对象名称
 * 输出：　true 日期，　false 非日期
 */
function isDateByValue(srcStr, sFieldName)
{	
	if (srcStr.length != 10) 
	{
		alert("输入的 [ " + sFieldName + " ]  格式错误! 请修正为 yyyy-mm-nn 格式!");
		return false;
	}
	
	var year = srcStr.substring(0,4);
	var month = srcStr.substring(5,7);
	var day = srcStr.substring(8,10);
	var d_t1 = srcStr.substring(4,5);
	var d_t2 = srcStr.substring(7,8);	

	if (isNaN(day) || isNaN(month) || isNaN(year) || d_t1 != "-" || d_t2 != "-")
	{
		alert("输入的 [ " + sFieldName + " ]  格式错误! 请修正为 yyyy-mm-nn 格式!");
		return false;
	}
	if (day > 31 || day < 1 || month > 12 )
	{
		alert("输入的 [ " + sFieldName + " ]  数据有错误!");
		return false;
	}
	if (month == 4 || month == 6 || month == 9 || month == 11)
	{
		if (day > 30)
		{
			alert("输入的 [ " + sFieldName + " ]  数据有错误!");
			return false;
		}	
	}
	if (month == 2)
	{
		if(year % 4 !=0 || (year % 400 != 0 && year % 100 == 0))
		{
			if(day > 28)
			{
				alert("输入的 [ " + sFieldName + " ]  数据有错误!");
				return false;
			}
		}
		else
		{ 
			if( day > 29)
			{
				alert("输入的 [ " + sFieldName + " ]  数据有错误!");
				return false;
			}
		}
	}

	return true;	
}

/** 
 * 功能：　比较日期函数
 * 输入：　strDate1 日期1, strDate2 日起2
 * 输出：　-1 : date1 早于 date2, 0 : date1 等于 date2, 1 : date1 迟于 date2
 */
function CompareDate(strDate1, strDate2)
{
	
	if('' == strDate1.trim() || '' == strDate2.trim())
		return false;
	
	if(false == isDateByValue(strDate1, '日期1') || false == isDateByValue(strDate2, '日期2'))
		return false;
	
	var yearOfDate1 = strDate1.substring(0, 4);
	var monthOfDate1 = strDate1.substring(5, 7);
	var dayOfDate1 = strDate1.substring(8);
	
	var yearOfDate2 = strDate2.substring(0, 4);
	var monthOfDate2 = strDate2.substring(5, 7);
	var dayOfDate2 = strDate2.substring(8);
	
	date1 =new Date(new Number(yearOfDate1),new Number(monthOfDate1)-1,new Number(dayOfDate1));
	date2 =new Date(new Number(yearOfDate2),new Number(monthOfDate2)-1,new Number(dayOfDate2));
	
	var ret;

	if(date1.getTime() < date2.getTime())
		ret = -1;
	else if(date1.getTime() > date2.getTime())
		ret = 1;
	else 
		ret = 0;

	return ret;
}

/**
 * 功能： 判断是否数字
 * 输入： obj 欲判断的Object reference, paramName 提示中欲显示的名称
 * 输出： 如果是整数 返回true, 否则 返回false
 */
function isNumber(obj)
{		
	 if((obj.value==null)||(obj.value=="")) return true; //默认为0
	if(isNaN(obj.value))
	{
   		alert("请输入数字");            
		obj.select();
		return false;
	}else
		return true;
}
//用在输入框onkeyup=""中只允许输入数字
function isInputNumber(obj){
	var reg4= '0123456789';
	var str = obj.value;
	var ret = '';
	var inum= str.length;
	for (i = 0; i < str.length; i++){ 
		var c = str.charAt(i);
		if( reg4.indexOf(c) == -1) {
				str = str.substr(0,i)+""+str.substr(i+1);
				i--;
				continue;				
		}
	}
	obj.value = str
	getElementRight(inum);	
}

/**
 * 功能： 判断是否正整数
 * 输入： obj 欲判断的Object reference, paramName 提示中欲显示的名称
 * 输出： 如果是整数 返回true, 否则 返回false
 */
function isInt(obj)
{		
	 if((obj.value==null)||(obj.value=="")) return true; //默认为0
	if(isNaN(obj.value) || (obj.value.indexOf('.') !=-1) || (obj.value.lastIndexOf('-') !=-1))
	{
   		alert("请输入一个整数");            
		obj.select();
		return false;
	}//end if
	return true;
}//end function

/**
 * 功能： 判断是否正整数
 * 输入： obj 欲判断的Object reference, paramName 提示中欲显示的名称
 * 输出： 如果是整数 返回true, 否则 返回false 
 */
function isInteger(obj)
{		

    if((obj.value==null)||(obj.value=="")) return true; //默认为0
	if(isNaN(obj.value) || (obj.value.indexOf('.') !=-1) || (obj.value.lastIndexOf('-') !=-1))
	{   		
	    alert("请输入一个整数");            
		obj.select();
		return false;
	}else{
		return true;
	}
}//end function


/**
 * 功能： Email较验函数
 * 输入： obj 欲校验的Object reference， msg 欲提示信息
 * 输出： true 或 false 
 */
function isEmail(obj, msg)
{
	if ('' == obj.value.trim())
		return true;
	
	var reg = /^([\.a-z0-9_\-]){1,}([a-z0-9]){1,}@([a-z0-9_-]){3,}(\.([a-z0-9]){2,4}){1,2}$/gi; 
	
	if (reg.test(obj.value))
	{ 
		return true;
	}
	else 
	{
		alert((msg?msg:'请输入标准格式Email'));
		obj.select();
		return false;
	}
}
/**
 * 功能： Email较验函数 liuz 2005-03-08 because of guanj
 * 输入： str 欲校验的str
 * 输出： true 或 false 
 */
function isEmail2(str)
{
	if ('' == str.trim())
		return true;	
	var reg = /^([a-z]){1,}([\.a-z0-9_\-]){1,}([a-z0-9]){1,}@([a-z0-9_-]){3,}(\.([a-z0-9]){2,4}){1,2}$/gi; 	
	if (reg.test(str))
		return true;
	else 
		return false;
}

/**电话格式效验
* @param obj 欲校验的Object reference
* @param msg 欲提示信息
* return false
*/
function isTelephone(obj,msg)
{
	if ('' == obj.value.trim())
		return true;
	var reg=/(^[0-9]{3,4}\-[0-9]{3,8}$)|(^[0-9]{3,8}$)|(^\([0-9]{3,4}\)[0-9]{3,8}$)|(^0{0,1}13[0-9]{9}$)/
	if(reg.test(obj.value)) return true;
	else {
		alert((msg?msg:'输入电话有错误！'));obj.select();return false;
		}	
}

/**
 * 功能： 英文字母较验函数
 * 输入： obj 欲校验的Object reference， msg 欲提示信息
 * 输出： true 或 false 
 */
function isEnglish(obj, msg)
{
	var reg = /^[a-z]*$/gi;
	if(reg.test(obj.value))
	{
		return true;
	}
	else 
	{
		alert((msg?msg:'该栏只能输入英文, 请改正'));
		obj.select();
		return false;
	}
}

/**
 * 功能： 中文较验函数
 * 输入： obj 欲校验的Object reference， msg 欲提示信息
 * 输出： true 或 false 
 */
function isChinese(obj, msg)
{
	var reg = /[^\u4E00-\u9FA5]/g;
	if (reg.test(obj.value))
	{
		alert((msg?msg:'该栏只能输入中文， 请改正'));
		obj.select();		
		return false;
	}
	return true;
}

/**
 * 功能： 中文及全角字符较验函数
 * 输入： obj 欲校验的Object reference， msg 欲提示信息
 * 输出： true 或 false 
 */
function isNoChinese(obj, msg)
{
	var reg = /[\u4E00-\u9FA5]|[\uFE30-\uFFA0]/gi;
	if (reg.test(obj.value))
	{
		alert((msg?msg:'该栏不能输入中文及全角字符， 请改正'));
		obj.select();		
		return false;
	}
	return true;
}

/** 新的 身份证判断 
 * 2005-1-24 
 * @param obj  输入框对象
 * @param msg  显示的提示信息
 * return false
 */
function isCardNumber(obj,msg){
	if(!isEN(obj,msg)){
		return false;
	}
	var s = obj.value;
	if (s.length > 14 && s.length < 21){
		return true;
	}else{
		if(msg)
			alert(msg);	//alert("输入的字符不符合身份证长度！");
		return false;
	}
}

/** 身份证判断
* @param s 传入的字符串
* return true or false
*/
function isCardNum(s){
	/*if (isEmpty(s)){ 
		alert("必须输入数字")
		return false;
	}*/
	if(!isCharsInBag (s, "0123456789")){
		alert("请检查一下您输入的是否为数字?");
		return false;
	}
	if (s.length==15 || s.length==18){
		return true;
	}else{
		alert("输入的数字长度为15位或者18位！");
		return false;
	}
}


/** 邮政编码判断
* @param s 传入的字符串
* return true or false
*/
function isZipCode(s){
	if (s != ''){
		if(!isCharsInBag (s, "0123456789")){
			alert("请检查一下您输入的是否为数字！");
			return false;
		}
		if (s.length==6){
			return true;
		}else{
			alert("输入的邮政编码长度应该为6！");
			return false;
		}
	} else {
		return true;
	}
}


/** 除S以外的字符
* @param s 字符串
* @param bag 欲比较的字符串
* return true or false
*/
function isCharsInBagEx (s, bag)
{ 
	var i,c;
	// Search through string's characters one by one.
	// If character is in bag, append to returnString.
	for (i = 0; i < s.length; i++)
	{ 
		c = s.charAt(i);
		if (bag.indexOf(c) > -1) 
			return true;
	}
	return false;
}


/** 字符是否在S中
* @param s 字符串
* @param bag 欲比较的字符串
* return true or false
*/
function isCharsInBag (s, bag)
{ 
	var i;
	// Search through string's characters one by one.
	// If character is in bag, append to returnString.
	for (i = 0; i < s.length; i++)
	{ 
		// Check that current character isn't whitespace.
		var c = s.charAt(i);
		if (bag.indexOf(c) == -1) return false;
	}
	return true;
}


/** 输入的数据是否小于等于指定的长度
* @param str 字符串
* @param size 指定大小
* @param field 字段名称
* return true or false
*/
function isNotOverSize(str, size, field){
	if(str.length <= size)
		return true;
	else {
		alert(field + "的输入长度大于指定的值!(" + size +")");
		return false;
	}

}


/** 输入的数据是否具有特定的长度
* @param str 字符串
* @param size 指定大小
* @param field 字段名称
* return true or false
*/
function isSize(str, size, field) {
	if (str.length == size)
		return true;
	else{
		alert(field + "长度不符合要求！长度应该为：" + size);
		return false;
	}
}

/**
 * 功能： 字母及数字较验函数
 * 输入： obj 欲校验的Object reference， msg 欲提示信息
 * 输出： true 或 false 
 */
function isEN(obj, msg)
{
	var reg=/^[a-z0-9.]*$/gi;
	if(reg.test(obj.value))
	{
		return true;
	}
	else 
	{
		alert((msg?msg:'该栏只能输入英文字母及数字， 请改正'));
		obj.select();
		return false;
	}
}

/** notNull函数察看文本框中是否有东西输入,输入框不为空（必填字符）
* @param str 字符串
* @param field 字段名称
* return true or false
*/
function isNotNull(str, field) {
	str = str.trim();
    if (str.length == 0 ){
		alert(field + "不能为空！");
		return false;
    }else
		return true;
}

/** 浏览器版本 检测
* @param temp为你需要判断的版本.目前只为IE,以后补充 Netscape
* return true or false
*/
function checkExplorer(temp)
{
	var BrowserInfo = new Object() ;
	BrowserInfo.MajorVer = navigator.appVersion.match(/MSIE (.)/)[1] ;
	BrowserInfo.MinorVer = navigator.appVersion.match(/MSIE .\.(.)/)[1] ;
	BrowserInfo.IsIEgoon = false;
	switch (temp)
	{
		case "6":
			BrowserInfo.IsIEgoon = BrowserInfo.MajorVer >= 6;break;
		case "5.5":
			BrowserInfo.IsIEgoon = BrowserInfo.MajorVer >= 6 || ( BrowserInfo.MajorVer >= 5 && BrowserInfo.MinorVer >= 5 ) ;break;
		case "4":
			BrowserInfo.IsIEgoon = BrowserInfo.MajorVer >= 4;break;
		default:
			BrowserInfo.IsIEgoon = BrowserInfo.MajorVer >= temp;break;
	}
	if (BrowserInfo.IsIEgoon)
		return true;
	else
		return false;
}

//-----取值函数----------------------------------------------------------------------------------------------------

//取得日期（见date.js）

//---放在需要显示时间的地方(补充格式：****年**月**日 hh:mm:ss) 
function getCurDate(){
    var pdate = new Date();
    var curdate = pdate.getYear();
    curdate +="-";
    if(pdate.getMonth()+1<10)
        curdate +="0";
    curdate += pdate.getMonth()+1;
    curdate +="-";
    if(pdate.getDate()<10)
        curdate +="0";
    curdate += pdate.getDate();
    return curdate;
}

/**
 * 功能： 复选框函数， 得到复选框选中的个数
 * 输入： checkbox 复选框
 * 输出： 选中个数
 */
function getCheckedNumber(checkbox)
{
	if(!checkbox)
	{
		return 0;
	}
	var count = 0;
	if('checkbox' == checkbox.type)
	{
		if(checkbox.checked)
			count++;
	}
	else
	{
		for(var i = 0; i < checkbox.length; i++)
		{
			if(checkbox[i].checked)
				count++;	
		}
	}
	return count;
}

/**
 * 功能： 单选框函数， 得到单选框选中的索引
 * 输入： radiobutton 单选框
 * 输出： 选中的索引
 */
function getCheckedIndex(radiobutton)
{
	if (! radiobutton)
	{
		return -1;
	}
	
	var checkedIndex = -1;
	if (radiobutton.length) 
	{
		for(var i = 0; i < radiobutton.length; i++)
		{
			if(radiobutton[i].checked)
			{
				checkedIndex = i;	
				break;
			}
		}
	}
	else 
	{
		if (radiobutton.checked)
		{
			checkedIndex = 0;
		}
	}
	
	return checkedIndex;
}

/**
 * 单选框函数， 得到单选框选中的值
 * @param radiobutton 单选框
 * @return 选中的值, 没有选中返回 false
 */
function getCheckedValue(radiobutton)
{
	var checkedIndex = getCheckedIndex(radiobutton);
	if (checkedIndex == -1)
	{
		return false;
	}
	if (radiobutton.length)
	{
		return radiobutton[checkedIndex].value;
	}
	return radiobutton.value;
}

/**
 * 功能： 截去字符串左右的空格
 * 输入： 欲处理字符串
 * 输出： 处理后字符串
 * 补充过滤中文空格  wux
 */
function String.prototype.trim() {
	return this.replace(/(^(\s|\u3000)*)|((\s|\u3000)*$)/g, '');
}

/**
 * 功能： 扩充Array功能函数，返回欲查找元素在Array中的位置
 * 输入： keyWord:欲查找的元素
 * 输出： 如果Array中不含keyWord, 返回-1, 否则返回索引位置
 */
function Array.prototype.indexOf(keyWord)
{
	var pos = -1;
	
	for(var i = 0; i < this.length; i++)
	{
		if(keyWord == this[i])
		{
			pos = i;
			break;
		}
	}

	return pos;
}

/**
 * 功能： 格式化数字到精确位数
 * 输入： srcNumber 预处理数字， decimal 欲精确到的位数
 * 输出： 处理后的数字 
 */
function String.prototype.format(decimal)
{
	var srcNumber = this;
	if(isNaN(this)) srcNumber = '';
		
	var temp = srcNumber * (Math.pow(10, decimal));
	temp = Math.round(temp);
	temp = temp / (Math.pow(10, decimal));
	temp += '';
	
	var suffix = '';
	var pointPosion = temp.lastIndexOf('.');
	var orignalDecimal = temp.length - pointPosion - 1;

	if(-1 == pointPosion)
	{
		suffix += '.';
		for(var i = 0; i < decimal; i++)
			suffix += '0';
	}
	else
	{
		for(var i = 0; i < (decimal - orignalDecimal); i++)
			suffix += '0';
	}
			
	temp += suffix;
	return temp;
}

/**
 * 得到一个字符串的浮点值, 如果字符串中的内容不是数字, 返回 0
 * 2005-1-28 wangz
 * @param string	字符串
 * @return 			经过转换后的浮点值
 */
function String.prototype.getFloatValue()
{
	return this.trim().length == 0 || isNaN(this) ? 0 : parseFloat(this, 10);
}




//判断字符串长度，对于一个汉字则返回按２个字节处理
function getLength(s)
{
	var sLength = 0;
	for (i = 0; i < s.length; i++)
	{ 
		var c = s.charAt(i);
		if (c.charCodeAt(0) < 256)
			sLength += 1;
		else
			sLength += 2
	}
	return sLength;
}

//数字变换成千分符表示的金额 格式是￥1,000,000.00
//--2004-11-18 fixed Bug，被3整除不转换
function NumToCurrency(ftmvalue)
{
	var number_string;
	var insert_position = 0;	
	ftmvalue=CurrencyToNum(ftmvalue);//先统一格式化后在进行转换，格式化后为1000.00
	var temp =ftmvalue.split('.');
	ftmvalue = temp[0];
	number_string=Math.abs(ftmvalue).toString();
	if(parseInt(ftmvalue)>=1000||parseInt(ftmvalue<=-1000))
	{		
		switch(number_string.length %3 )
		{
			case 1:insert_position=1;break;
			case 2:insert_position=2;break;
			case 0:insert_position=3;break;//。
		}
		while(insert_position<number_string.length){
			number_string = number_string.substr(0,insert_position)+','+number_string.substring(insert_position)
			insert_position+=4;
		}
		if(parseInt(ftmvalue)<0)
			number_string="-"+number_string;//负数
	}
	//还原格式化
	if(ftmvalue<0)
		ftmvalue='-￥'+number_string+'.'+temp[1];
	else
		ftmvalue='￥'+number_string+'.'+temp[1];
	return ftmvalue;	
}
////金额进行统一格式化为字符:1000.00
//--2004-11-18 fixed bug 多次替换','
function CurrencyToNum(ftmvalue)
{
	var temp;
	ftmvalue=ftmvalue.replace(' ','')
	if(ftmvalue.indexOf('￥')>=0)
	{
		ftmvalue=ftmvalue.replace(/￥/gi,'');
	}
	if(ftmvalue.indexOf(',')>=0)
	{
		ftmvalue=ftmvalue.replace(/,/gi,'')//== 
	}
	var reg= /^(\-{0,1}[0-9\.]){1,}$/gi;//1000 
	if(!reg.test(ftmvalue))
		ftmvalue = 0.00;
	ftmvalue = parseFloat(ftmvalue);
	ftmvalue = ftmvalue.toFixed(2);
	return ftmvalue
}

//-------对话框---------------------------------------------------------------------------------------------

//确认执行事件
function getConfirm()
{
		if( confirm('执行该操作后将不能还原数据，确定要执行吗?') ){
			return (true);
		}else{
		return (false);
                
		}
}
//弹出窗口（地址，宽，高）
function getWindow(url,tagname,wwidth,wheight)
{
	window.open(url, tagname, 'height='+wheight+', width='+wwidth+', scrollbars=yes, top=200, left=260');
}
///////
function getModelDialog(url)
{
	var returnValue = showModalDialog(url,window,'dialogWidth:600px;dialogHeight:500px;status:no;scroll:Auto;help:no;');	
	return returnValue
}


//-------页面控制---------------------------------------------------------------------------------------------

/**
  隐藏或者显示对应框架页面
  targetFrame： dataFrame / listingFrame
  url： 对应框架加载页面路径，为空则不加载
  scale： '0'   （隐藏listingFrame）  
	  '60%' （正常显示上下框架）
	  '100%'（隐藏dataFrame）
*/
function showFrame(tarfra,url,flag)
{
	if(tarfra=='listingFrame'){
	   	parent.document.body.rows = '*,8,'+flag;
	   	if(url!='') parent.listingFrame.location = url;
	}else {
		parent.document.body.rows = '*,8,'+flag;
		if(url!='') parent.dataFrame.location = url;
	}
}
/**
 * 功能： 页面控制函数， 隐藏dataFrame和subMenuFrame
 * 输入： 
 * 输出： 
 */
function hideFrame()
{
	parent.document.body.rows = '0,0,*';
}

/**	
	显示&隐藏表格id=sid效果，同时也切换图片id=imgbtn：
	switchtable(sid,imgbtn,flag)
	flag: none / display
*/
function switchtable(sid,imgbtn,flag)
{
	if(imgbtn !='')
		var urlpath="/"+getContextPath()+"/"

	var subment=eval(sid);
	var iflag=0;
 	if(flag==''){
  		if(subment.style.display=="none")
  			iflag=1;
		else
			iflag=0;
	}else if(flag=='none')
		iflag=0;
	else if(flag=='display')	
		iflag=1;
		
	if(iflag!=0){
		eval(sid+".style.display=\"\";");
     		if(imgbtn&&eval('document.all.'+imgbtn)) 
			eval('document.all.'+imgbtn+'.src="'+urlpath+'image/lminus.gif"');
	}else{
		eval(sid+".style.display=\"none\";");
     		if(imgbtn&&eval('document.all.'+imgbtn)) 
			eval('document.all.'+imgbtn+'.src="'+urlpath+'image/lplus.gif"');
	}
}

//---------08-19 add 与上面不同图片是 scroll_right1.gif
function switchtable1(sid,imgbtn,flag)
{
	var urlpath="/"+getContextPath()+"/"

	var subment=eval(sid);
	var iflag=0;
 	if(flag==''){
  		if(subment.style.display=="none")
  			iflag=1;
		else
			iflag=0;
	}else if(flag=='none')
		iflag=0;
	else if(flag=='display')	
		iflag=1;
		
	if(iflag!=0){
		eval(sid+".style.display=\"\";");
     		if(imgbtn&&eval('document.all.'+imgbtn)) 
			eval('document.all.'+imgbtn+'.src="'+urlpath+'image/main/scroll_down.gif"');
	}else{
		eval(sid+".style.display=\"none\";");
     		if(imgbtn&&eval('document.all.'+imgbtn)) 
			eval('document.all.'+imgbtn+'.src="'+urlpath+'image/main/scroll_right1.gif"');
	}
}

//-------其他-----------------------------------------------------------------------------------------------

/**
 * 功能：　复选框函数， 点击后选择（或取消选择）所有函数
 * 输入： checkboxAll 总控制复选框， checkbox 其他复选框
 * 输出：
 */
function checkAll(checkboxAll, checkbox)
{
	if(!checkboxAll || !checkbox)
	{
		return;
	}	
	if('checkbox' == checkbox.type)
	{
		checkbox.checked = checkboxAll.checked;
	}
	else
	{	
		for(var i = 0; i < checkbox.length; i++)
		{
			checkbox[i].checked = checkboxAll.checked;
		}
	}
}

function check(checkboxAll,checkbox)
{
	if(!checkboxAll || !checkbox)
	{
		return;
	}			
	var checked = true;
	if('checkbox' == checkbox.type)
	{
		checked = checkbox.checked;
	}
	else
	{
		for(var i = 0; i < checkbox.length; i++)
		{
			if(false == checkbox[i].checked)
			{
				checked = false;
				break;
			}
		}
	}
	checkboxAll.checked = checked;
}


//---------------------------------------------------------------------------------------------------------------

//显示或者隐藏 table
function showtable(sid)
{
  subment=eval("submenu"+sid);
  if(subment.style.display=="none")
  {eval("submenu"+sid+".style.display=\"\";");}
  else 
  {eval("submenu"+sid+".style.display=\"none\";");}
}

/**
 * 功能： 页面控制函数， 显示dataFrame和subMenuFrame
 * 输入： url 为 dataFrame中欲显示的页面, 无参数显示空白
 * 输出： 
 */
function showFullFrame(url)
{
	parent.document.body.rows = '*,8,60%';
	if(arguments.length > 0)
	{
		document.forms[0].method="post"
		document.forms[0].target='dataFrame';
		document.forms[0].action=arguments[0];
		document.forms[0].submit();
		//parent.dataFrame.location = arguments[0];
	}
	else
	{
		document.forms[0].method="post"
		document.forms[0].target='dataFrame';
		document.forms[0].action='about:blank';
		document.forms[0].submit();
		//parent.dataFrame.location = 'about:blank';
	}
}

//显示列表页面
function showListingFrame(url)
{
	parent.document.body.rows = '*,8,60%';
	if(arguments.length > 0)
	{
		parent.listingFrame.location = arguments[0];
	}
	else
	{
		parent.listingFrame.location = 'about:blank';
	}
}



/**
 * 功能： 查看明细
 * 输入： baseaction: action
 * 输入： id: 欲查看的实体Id
 * 输出： 
 */
function MM_showDetail(baseaction, id)
{		
	showFullFrame();
	document.forms[0].target = 'dataFrame';
	document.forms[0].method = 'post';
	document.forms[0].action = baseaction + '?method=findById&id=' + id + '&actionType=detail';
	document.forms[0].submit();								
}

/**
 * 功能： 查看明细
 * 输入： baseaction: action
 * 输入： id: 欲查看的实体Id
 * 输出： 
 */
function MM_showDetail2(baseaction, id)
{		
	//showFullFrame();
	window.open(baseaction + '?method=findById&id=' + id + '&actionType=detail', 'null', 'height=550, width=700, menubar=no, status=no, toolbar=no, location=no, top=60, left=100');
	//document.forms[0].target = 'about:blank';
	//document.forms[0].method = 'post';
	//document.forms[0].action = baseaction + '?method=findById&id=' + id + '&actionType=detail';
	//document.forms[0].submit();								
}

/**
 * 功能 : disable form中所有元素, 用于查看明细时
 * 输入 : 
 * 输出 :
 */
function MM_disableElements()
{
	var form = document.forms[0];
	for(var i = 0; i < form.length; i++)
	{
		form[i].disabled = true;  
		form[i].className='button3';
		if(form[i].type == 'textarea')
			form[i].style.overflow = 'hidden' ;
	}
}

/**
 * 功能 : 取href中的text
 **/
function MM_filterLink()
{
	var str = arguments[0];
	try{
		if(str.indexOf("<a href") > -1)
			str = str.substring(str.indexOf(">") + 1, str.lastIndexOf("<"));
	}catch(e){}
	return str;
}

/**
 * 隐藏页面中的*号必填
 */
function MM_hiddeImage(forceImage)
{	
	try{
		if(document.all(forceImage).length)
		{
			for(var i = 0; i < document.all(forceImage).length; i++)
			{
				document.all(forceImage)[i].style.display = "none";
			}
		}else
		{
			document.all(forceImage).style.display = "none";
		}
	}catch(e){		
	}
}


/**
 * as数组中对应的表单哉隐藏
 */
function MM_hiddeInput(as)
{	

	for(var i = 0; i < as.length; i++){
		document.all(as[i]).readOnly = true;
		document.all(as[i]).className = "button3";
	}
}
/**
 * begin到end对应的表单哉隐藏
 */

function MM_hiddeFormElements(begin,end)
{	

for(var i = begin;i<(end-begin);i++){

	//document.Forms[0].elements[i]=true;
	document.Forms[0].elements[i].className = "button3";

	}
}

//建立对象保存取到的数据。根据实际的表动态加载，与listing中数据字段对应地在页面上调用
function FellowInfo()
{
	this.id = arguments[0][0];
	for(var j = 1; j < Columns.length; j++){
		//alert("fellowInfo:"+Columns[j].colName+"="+MM_filterLink(arguments[0][j]));
		//eval('this.' + Columns[j].colName + ' = MM_filterLink(arguments[0][j])');
		//以下是修改
		var name = Columns[j].colName;
		var realName = name.split(".");
		eval('this.' + realName[realName.length-1] + ' = MM_filterLink(arguments[0][j])');
	}
}


/**
 * 返回选中的对象

function returnSingleObject(ftm)
{
	try{
		var inum = -1;
		var ivalue=new Array();		
		if(ftm.length){		
			for(var i = 0; i < ftm.length; i++){			
				if(true == ftm[i].checked){ 				  
					inum = i;
					break;
				}
			}
		} else {
			if(ftm.checked)
				inum = 0;
		}		
		if(-1 == inum){		
			alert('请选择!');
			return false;
		}		
		for(var j = 1; j < Columns.length; j++){		
			//alert(Columns[j].colName + ":" + htmldw.rows[inum][j]);
			ivalue[j-1] = htmldw.rows[inum][j];
		}	
		var info = new FellowInfo(ivalue);
		if(parent){			
			parent.returnValue = info;
			parent.close();
		}
		window.close();
	}catch(e){
		alert('选择出错：'+e);
	}
}
 */

/**
 * 返回单个选中的对象数组
 */
function returnSingleObject(ftm)
{
	try{
		if(getCheckedNumber(ftm) < 1){		
			alert('请选择');
			return false;
		}		
		var ftmvalue = ftm.value;
		var ivalue=new Array();		
		if(ftm.length){		
			for(var i = 0; i < ftm.length; i++){			
				if(true == ftm[i].checked){ 				  
					ftmvalue = ftm[i].value;
					break;
				}
			}
		}
		/*		
		for(var j = 1; j < Columns.length; j++){		
			//alert(Columns[j].colName + ":" + htmldw.rows[inum][j]);
			ivalue[j-1] = htmldw.rows[inum][j];
		}*/
		//解决排序后数据混乱的Bug，对id值检索后取到对应选中的数据 -- 2005-1-20 liuz
		for(var i=0;i<htmldw.rows.length;i++)
			if(htmldw.rows[i][1] == ftmvalue){
				for(var j=1;j<= Columns.length;j++){
					ivalue[j-1]=htmldw.rows[i][j];
				}
			}
		var info = new FellowInfo(ivalue);
		if(parent){			
			parent.returnValue = info;
			parent.close();
		}
		window.close();
	}catch(e){
		alert('选择出错：'+e);
	}
}


/**
 * 返回多个选中的对象数组 
 */
function returnObjects(ftm)
{
    var fellows = new Array();    
    if(!ftm.length){ //单个情况   
        var ivalue = new Array();
        for(var j = 1;j <= Columns.length; j++){        
            ivalue[j-1] = htmldw.rows[0][j];
        }
        fellows[0] = new FellowInfo(ivalue);
    }
    else
    {
		var k = 0;
        for(var t = 0; t < ftm.length; t++)
        {
			if(ftm[t].checked){
				/*var ivalue = new Array();			
				for(var j = 1;j < Columns.length; j++)
				{
					ivalue[j-1] = htmldw.rows[i][j];
				}
				fellows[k++] = new FellowInfo(ivalue);
				*/
				//解决排序后数据混乱的Bug，对id值检索后取到对应选中的数据 -- 2005-1-20 liuz 
				var iarray=new Array();				
				for(var i=0;i<htmldw.rows.length;i++)
					if(htmldw.rows[i][1] == ftm[t].value){
						for(var j=1;j<=Columns.length;j++){
							iarray[j-1]=htmldw.rows[i][j];						
						}
					}
				fellows[k++]=new FellowInfo(iarray);
			}
        }
    }    
    parent.returnValue = fellows;
    parent.close();
}

/** 与 returnObjects 类同
 *  根据列表中选中所在行的所有数据，赋值为对象数组
 *  支持单选 / 多选
 */
function getSelectListingData(ftm){
    var fellows = new Array();    
    if(!ftm.length){ //单个情况   
        var ivalue = new Array();
        for(var j = 1;j <= Columns.length; j++){        
            ivalue[j-1] = htmldw.rows[0][j];
        }
        fellows[0] = new FellowInfo(ivalue);
    } else{ 
		var k = 0;
        for(var t = 0; t < ftm.length; t++)
        {
			if(ftm[t].checked){
				//解决排序后数据混乱的Bug，对id值检索后取到对应选中的数据 -- 2005-1-20 liuz 
				var iarray=new Array();				
				for(var i=0;i<htmldw.rows.length;i++)
					if(htmldw.rows[i][1] == ftm[t].value){
						for(var j=1;j<=Columns.length;j++){
							iarray[j-1]=htmldw.rows[i][j];						
						}
					}
				fellows[k++]=new FellowInfo(iarray);
			}
        }
    }  
	return fellows //返回数组，单选的取值 fellows[0].xm。多选是fellow[indexNum].xm
}



/**
 * 展开表单域中的所有隐藏对象
 */
function showAllTable()
{	
	for(var i = 0; i < document.all.length; i++)
	{
		var element = document.all[i];
		if(element.id != null && element.id != '')
		{
			var tempId = element.id + '';
			if(0 == tempId.indexOf("submenu"))
			{   
				switchtable(tempId, '', 'display');
			}
		}
	}
}

/**
 * 打开一个新窗口
 * @param url  		要打开的地址
 * @param width		宽度, 如果不填, 默认为700
 * @param height 	高度, 如果不填, 默认为460
 */
function openWindow(url, width, height) 
{
	if(!url) 
	{
		alert('请指定要打开的地址!');
		return;
	}		
	
	if(!width)
	{
		width = 700;
	}
	
	if(!height)
	{
		height = 460;
	}
	
	var winName = new Date().getTime();
	window.open('about:blank', winName, 'height='+ height +', width='+ width +', menubar=no, status=no, toolbar=no, location=no, top=50, left=100, center=1, resizable=1');	
	document.f.target = winName;
	document.f.method = 'post';	
	document.f.action = url;
	document.f.submit();	
}

/**
 * 打开一个新Frame窗口, Frame中有关闭按钮
 * @param url  		要打开的地址
 * @param width		宽度, 如果不填, 默认为700
 * @param height 	高度, 如果不填, 默认为460
 */
function openWindowInFrame(url, width, height)
{
	if(!url) 
	{
		alert('请指定要打开的地址!');
		return;
	}
	
	if(!width)
	{
		width = 700;
	}
	
	if(!height)
	{
		height = 460;
	}
	
	var frameUrl = "/" + getContextPath() + '/application/pages/select_frame.jsp?url=' + escape(url);	
	var winName = new Date().getTime();
	window.open(frameUrl, winName, 'height='+ height +', width='+ width +', menubar=no, status=no, toolbar=no, location=no, top=50, left=100, center=1, resizable=1');	
	//document.f.target = winName;
	//document.f.method = 'post';	
	//document.f.action = frameUrl;
	//document.f.submit();	
}

/**
 * 打开一个新对话框
 * @param url  		要打开的地址
 * @param width		宽度, 如果不填, 默认为700
 * @param height 	高度, 如果不填, 默认为460
 * @return 			对话框返回值 
 */
function openDialog(url, width, height)
{
	if(!url) {	
		alert('请指定要打开的地址!');
		return;
	}	
	if(!width){	
		width = 700;
	}	
	if(!height){	
		height = 460;
	}	
	var frameUrl = "/" + getContextPath() + '/application/pages/select_frame.jsp';
	var returnValue = showModalDialog(frameUrl, url, 'dialogWidth:' + width +'px;dialogHeight:' + height + 'px;status:no;scroll:auto;help:no;')
	return returnValue
}

//得到工程路径
function getContextPath()
{
   var url = location.href;
   url = url.substring(url.indexOf('://') + 3);
   if(url.indexOf('/') < 0)
   		return "";
   url = url.substring(url.indexOf('/') + 1);
   var pos;
   if(url.indexOf('/') >= 0) 
		pos = url.indexOf('/');
   else pos = url.length;
   		url = url.substring(0, pos);
   return url;
}

/** 弹出在线帮助窗口getOnlineHelp
 */
function getOnlineHelp(str){
	var url = '';
	if(str)
		url = str;
	else if(typeof ifrm_content == 'object')
		url = ifrm_content.location.href;
	else if(typeof ifrm_news == 'object')
		url = ifrm_news.location.href;
	else if(typeof parent.mainFrame == 'object' && typeof parent.mainFrame.listingFrame == 'object')
		url = parent.mainFrame.listingFrame.location.href
	else if(typeof parent.mainFrame == 'object')
		url = parent.mainFrame.location.href
	else 
		url = location.href
	return openWindowInFrame('/'+getContextPath()+'/application/pages/help_default.htm?urltxt='+url,800,600)
}

/*--使得鼠标光标移到最后边--*/
function getElementRight()  
{  
  var e = event.srcElement;  
  var r =e.createTextRange();  
  r.moveStart('character',e.value.length);  
  r.collapse(true);  
  r.select();  
}  

//实现 拖动表格宽度 效果
// 在代码处加入 style="cursor:e-resize" onmousedown="setCapture()" onmouseup="releaseCapture();" onmousemove="general_dragWidth(tdId_1)"
//@param  tabid  表格ID
function general_dragWidth(tabid,num){
	if(event.button ==1){
		var magin = (num + '' == 'undefined')?0:num;//document.body.style.marginLeft + document.body.style.margetRight;
		var xx = (event.x-magin < 0)?0:event.x-magin;
		tabid.style.width = xx;
	}
}

//style="cursor:n-resize" onmousedown="setCapture()" onmouseup="releaseCapture();" onmousemove="general_dragHeight(tdId_1)"
//拖动表格高度
function general_dragHeight(tabid,num){
	if(event.button ==1){
		var magin = (num + '' == 'undefined')?0:num;
		var yy = (event.y-magin < 0)?0:event.y-magin;
		tabid.style.height = yy
	}
}

