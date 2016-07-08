/** Gobal bCancel; */
var bCancel = false;
var DATE_DELIMITER = "-";
var DB_INT_LENGTH = 64; //Database Length.defalut64
var MAX_INTEGER = Math.pow(2, DB_INT_LENGTH - 1) - 1;
var MIN_INTEGER = -Math.pow(2, DB_INT_LENGTH - 1);
var MAX_SMALLINT = Math.pow(2, DB_INT_LENGTH / 4 - 1) - 1;
var MIN_SMALLINT = -Math.pow(2, DB_INT_LENGTH / 4 - 1);
var VERBOSE = false;
var FIELD_SEPARATOR = "_FIELD_SEPARATOR_";
var GROUP_SEPARATOR = "_GROUP_SEPARATOR_";
//页面加载时的等待界面
var waitdiv="<div id='waitdiv' style='position: absolute; width: 100%; height: 100%; left: 0px; "
	+"top: 0px; background-color: #ffffff; filter: alpha (opacity =100 ); z-index: 50000'>"
	+"<div style='text-align: center; padding-top: 200px'><table align='center' border='1' width='37%' "
	+"cellspacing='0' cellpadding='4' style='border-collapse: collapse' bgcolor='#FFFFEC' height='87'> "  
    +"<tr><td bgcolor='#3399FF' style='font-size: 12px;' height='24' align='center'> </td></tr>"   
    +"<tr><td style='font-size: 12px; line-height: 200%' align='center'>正在载入...</td></tr>"   
    +"</table> </div></div> ";
function setDateDelimiter(delimiter) {
    DATE_DELIMITER = delimiter;
}
function setDBIntLength(len) {
    DB_INT_LENGTH = len;
    MAX_INTEGER = Math.pow(2, DB_INT_LENGTH - 1) - 1;
    MIN_INTEGER = -Math.pow(2, DB_INT_LENGTH - 1);
    MAX_SMALLINT = Math.pow(2, DB_INT_LENGTH / 4 - 1) - 1;
    MIN_SMALLINT = -Math.pow(2, DB_INT_LENGTH / 4 - 1);
}
function setVerbose(verbose) {
    VERBOSE = verbose;
}
function isVerbose() {
    return VERBOSE;
}
function log(value) {
    if (isVerbose()) {
        window.status = value;
    }
}
/**
*去掉左右空格
*/
function trim(value) {
	return value.replace(/(^(\s)*)|((\s)*$)/g, "");
}
/** 删除逗号 */
function removeComma(value){
    return typeof value == "string" ? (value.indexOf(",")!=-1?trim(value).replace(/,/g,""):trim(value)) : value;
}

/** 添加逗号 */
function addComma(value){
	value = removeComma(value);
    var reg = /((\+|-){0,1}\d{1,})(\d{3})/, pos=value.indexOf(".");
    pos = pos!=-1?pos:value.length;
    while(reg.test(value)&&value.search(reg)<pos){
        value = value.replace(reg,"$1,$3")
    }
    return value;
}
/**
 * 内部行业选择
 * @param inputTextObj 触发此方法的text框
 * @param path         项目根路劲,默认为crds
 * @param flag         查询或选择标志,默认为:query查询,否则为:select选择
 */
function internalIndustroySelect(inputTextObj,path,flag){
	inputTextObj = jQuery(inputTextObj);
	var codeHide = inputTextObj.next();//紧跟在inputTextObj后面的隐藏框[此隐藏框放的就是内部行业代码]
	var selIndus = window.showModalDialog(path+"/pub/InternalIndustrySelect.html",{code:codeHide.val(),isQuery:flag,rootPath:path},"status:no;resizable:no;dialogHeight:15;dialogWidth:15;help:no");
	if(selIndus && selIndus.code!=null && selIndus.code!=""){
		inputTextObj.val(selIndus.name);
		codeHide.val(selIndus.code);
	}
}

/**
 * 调节Frame的宽度和高度适应Frame中文档的宽度和高度,调节完之后定位当前文档的位置;当然也可以单独定位某个文档的滚动条的位置,如adjustFrameWH(null,null,1);adjustFrameWH(null,null,{left:20,top:100});adjustFrameWH(null,null,function(){})
 * @param frameID 必选参数 框架Frame的ID.
 * @param adjustDirection 可选参数 调节方向,0-横向,1-纵向,其它-横向和纵向.
 * @param currDocScrollPosition 可选参数 当前文档滚动条定位.0-横向靠左,1-横向靠右,2-纵向靠上,3-纵向靠下,4-横向靠左和纵向靠上,5-横向靠左和纵向靠下,6-横向靠右纵向靠上,7-横向靠右和纵向靠下,或者为对象{left:0;top:2},或者函数.
 */
function adjustFrameWH(frameID, adjustDirection, currDocScrollPosition){
	if(frameID && Object.prototype.toString.call(frameID) === "[object String]"){
		var wh = adjustDirection==0?[["width","scrollWidth"]]:(adjustDirection==1?[["height","scrollHeight"]]:[["width","scrollWidth"],["height","scrollHeight"]]);
		for(var i = 0; i < wh.length; i++)
			document.getElementById(frameID)[wh[i][0]] = document.getElementById(frameID).contentWindow.document.body[wh[i][1]];
	}
	if(currDocScrollPosition){//当前文档滚动条定位
		if(Object.prototype.toString.call(currDocScrollPosition) === "[object Function]"){
			currDocScrollPosition();
		}else{
			var scrollPos = currDocScrollPosition==0?[["scrollLeft",0]]:currDocScrollPosition==1?[["scrollLeft",document.body.scrollWidth]]:
				currDocScrollPosition==2?[["scrollTop",0]]:currDocScrollPosition==3?[["scrollTop",document.body.scrollHeight]]:
				currDocScrollPosition==4?[["scrollLeft",0],["scrollTop",0]]:currDocScrollPosition==5?[["scrollLeft",0],["scrollTop",document.body.scrollHeight]]:
				currDocScrollPosition==6?[["scrollLeft",document.body.scrollWidth],["scrollTop",0]]:currDocScrollPosition==7?[["scrollLeft",document.body.scrollWidth],["scrollTop",document.body.scrollHeight]]:
				Object.prototype.toString.call(currDocScrollPosition) === "[object Object]"?(function(){
					return (currDocScrollPosition.left && currDocScrollPosition.top) ? [["scrollLeft",currDocScrollPosition.left],["scrollTop",currDocScrollPosition.top]]:
						currDocScrollPosition.left ? [["scrollLeft",currDocScrollPosition.left]] : currDocScrollPosition.top ? [["scrollTop",currDocScrollPosition.top]]:[];
				})():[];
			for(var i = 0; i < scrollPos.length; i++)
				document.body[scrollPos[i][0]] = scrollPos[i][1];
		}
	}
}
/**
 * 对指定的对象输入字符数进行控制,并将输入字符数的限制回显给另一个对象.
 * @param controlIdObjs 输入的控制对象,页面中有多个输入控制对象时请用#进行分隔.
 * 注:输入对象要有mxSize和showid属性,mxSize就是最大显示字符数;showid就是要显示字符数的标签ID
 * @return
 */
function inputCharacterControl(controlIdObjs){
	controlIdObjs = (controlIdObjs || "inputControlObj").split("#");
	for(var i = 0; i < controlIdObjs.length; i++) {
		jQuery("#"+controlIdObjs[i]).each(function(){
			jQuery(this).keydown(function(){
				var mxSize = jQuery(this).attr("mxSize");
				if(this.value.length>mxSize)
					this.value = this.value.substr(0,mxSize);
			});
			jQuery(this).keyup(function(){
				var mxSize = jQuery(this).attr("mxSize");
				if(this.value.length>mxSize)
					this.value = this.value.substr(0,mxSize);
				jQuery("#"+jQuery(this).attr("showid")).text(mxSize - this.value.length);
			});
			jQuery(this).keyup();
		});
	}
}
/**
 * 数字验证
 * @param inputObj input输入框控件或字符串,但传递的参数为字符串时,返回true或false
 * @param isInt 验证标识,true-整数类型验证,false-浮点类型验证,null-不对是否为整数或浮点数验证
 * @param minData 最小范围
 * @param maxData 最大范围
 * @param subDecimalPoint 输入之后截取的小数点位数
 * @return
 */
function inputDecimalCheck(inputObj,isInt,minData,maxData,subDecimalPoint){
	if(typeof(inputObj)==="string"){
		if(inputObj.length>0){
			var errMsg = "", demcial = inputObj.match(/(\.\d+)?$/g)[0];
			if(isInt===true){//整数类型验证
				if(!inputObj.isFloat(0))
					errMsg = "输入有误,只能输入整数类型的数据!";
			} else if(isInt===false){//浮点类型验证
				if(!inputObj.isFloat())
					errMsg = "输入有误,只能输入浮点类型的数据!";
			}
			if(errMsg === "" && demcial.length>0 && demcial.length>16){//保证小数点在javascript的有效范围内
				errMsg = "输入有误,最多只能输入15位的小数位数!";
			} else if(!isNaN(parseFloat(minData)) && parseFloat(inputObj) < parseFloat(minData)){//最小值验证
				errMsg = "输入有误,输入的值超出允许的最小范围:"+minData+(!isNaN(parseFloat(maxData))?(",取值范围只能在"+minData+"和"+maxData+"之间"):"");
				if(subDecimalPoint && subDecimalPoint.nodeName && subDecimalPoint.type && subDecimalPoint.type.toLowerCase()=="text")
					subDecimalPoint.value = minData;
			} else if(!isNaN(parseFloat(maxData)) && parseFloat(inputObj) > parseFloat(maxData)){//最大值验证
				errMsg = "输入有误,输入的值超出允许的最大范围:"+maxData+(!isNaN(parseFloat(minData))?(",取值范围只能在"+minData+"和"+maxData+"之间"):"");
				if(subDecimalPoint && subDecimalPoint.nodeName && subDecimalPoint.type && subDecimalPoint.type.toLowerCase()=="text")
					subDecimalPoint.value = maxData;
			}
			if(errMsg!=""){
				alert(errMsg);
				return false;
			}
		}
		return true;
	} else {
		if(!inputDecimalCheck(inputObj.value,isInt)){
			inputObj.value = "";
			inputObj.focus();
		} else if(inputDecimalCheck(inputObj.value,null,minData,maxData,inputObj)){
			if(subDecimalPoint != null && subDecimalPoint != "") {
				inputObj.value = inputObj.value.floatFormat(false,subDecimalPoint)
			}
		}
	}
}
/**
 * 文件下载.为了让下载文件不弹出新的窗口,特作此方法
 * 注意:
 * 1. 模态窗口下下载文件时,如果还是弹出新窗口,请在页面头部加上:<base target="_self"/>,
 * 2. 加上<base target="_self"/>之后如果对你的需求有限制,而又想让下载的文件不弹出新的窗口,此时采用另一种方案,如下面的代码:
 * <a href="crds/downloadFile.do?filePath=2&fileFlag=1" title="点击下载此模板文件." target="fileManageFrame">下载文件</a>
 * <iframe name="fileManageFrame" height="0px" width="0px" comment="用于下载文件,使用此Frame目的是使下载文件时不要弹出新窗口" style="display: none;"></iframe>
 * @param path 项目根路径
 * @param filePath 模板文件ID或公司代码或文件ID
 * @param fileFlag 文件标识,1-[模板文件下载],2-[公司内部评级文件],3-[文件路径保存在表EVALUATION_EXTERNAL_FILE_INFO中的文件,默认]
 * @param paramOptionObj 参数选项对象
 * @return
 */
function downloadFile(path,filePath,fileFlag,paramOptionObj){
	if(filePath==null || filePath.length==0)
		return;
	fileFlag = null==fileFlag ? "3":fileFlag;
	var url = path+"/downloadFile.do?filePath="+filePath+"&fileFlag="+fileFlag, frameId="downloadFileFrameId", downloadFrame = jQuery("#"+frameId);
	if(paramOptionObj && Object.prototype.toString.call(paramOptionObj) === "[object Object]"){
		for(var key in paramOptionObj){
			url += "&"+key+"="+paramOptionObj[key];
		}
	}
	if(downloadFrame.length==0){
		jQuery("body").append("<iframe id=\""+frameId+"\" name=\"downloadFileFrame\" height=\"0px\" width=\"0px\" comment=\"用于下载文件,使用此Frame目的是使下载文件时不要弹出新窗口\" style=\"display: none;\"></iframe>");
	}
	document.getElementById(frameId).contentWindow.location = url;
}
/**
*获得对应表格的元素数组
*/
function getTableElements(tableId) {
    var i = 0;
    var elements = new Array();
    var tempElements = null;
    var tbody;
    var index = 0;
    var tbodies = document.getElementById(tableId).tBodies;
    var tbodiesCount = tbodies.length;
    var tempElementsCount = 0;
    for (i = 0; i < tbodiesCount; i++) {
        tbody = tbodies.item(i);
        tempElements = tbody.getElementsByTagName("INPUT");
        tempElementsCount = tempElements.length;
        for (var j = 0; j < tempElementsCount; j++) {
            elements[index++] = tempElements[j];
        }
        tempElements = tbody.getElementsByTagName("SELECT");
        tempElementsCount = tempElements.length;
        for (var j = 0; j < tempElementsCount; j++) {
            elements[index++] = tempElements[j];
        }
        tempElements = tbody.getElementsByTagName("TEXTAREA");
        tempElementsCount = tempElements.length;
        for (var j = 0; j < tempElementsCount; j++) {
            elements[index++] = tempElements[j];
        }
    }
    return elements;
}
/**
*字符取代
*/
function replace(str, strFind, strReplaceWith) {
    var strReturn;
    var re = new RegExp(strFind, "g");
    if (str == null) {
        return null;
    }
    strReturn = str.replace(re, strReplaceWith);
    return strReturn;
}
/**
*判断是否为空
*/
function isEmptyField(field) {
    if (field.value == null || trim(field.value) == "") {
        return true;
    }
    return false;
}
/**
*格式化数字转为字符
*/
function formatNumberToString(strValue) {
    strValue = trim(strValue);
    strValue = replace(strValue, ",", "");
    return strValue;
}
/**
*格式化数字转为双精度
*/
function formatNumberToDouble(strValue) {
    var dblValue = 0;
    strValue = formatNumberToString(strValue);
    dblValue = parseFloat(strValue);
    if (isNaN(dblValue)) {
        dblValue = 0;
    }
    return dblValue;
}
/**
*格式化数字
*/
function fixNumber(cellStr) {
    if (parseFloat(cellStr) == 0) {
        return "0";
    }
    if (cellStr.indexOf(".") == -1) {
        return cellStr;
    }
    var x = parseFloat(cellStr);
    x += (parseFloat(cellStr) < 0) ? -5e-9 : 5e-9;

	// Chop the string to 4 decimal places.
    cellStr = "" + x;
    cellStr = cellStr.substring(0, 5 + cellStr.indexOf("."));

	// Remove trailing zeros beyond the decimal point.
    cellStrArr = cellStr.split("");
    for (k = cellStrArr.length - 1; k > 0 && cellStrArr[k] == "0"; --k) {
        cellStrArr[k] = "X";
    }
    cellStr = "";
    for (k = 0; k < cellStrArr.length && cellStrArr[k] != "X"; ++k) {
        cellStr += cellStrArr[k];
    }
    return cellStr;
}
/**
*弹出提示信息
*/
function showMessage(message) {
    alert(message);
}
/**
*判断是否为IE6
*/
function isIE6() {
    if (navigator.appVersion.indexOf("MSIE 6") > -1) {
        return true;
    } else {
        return false;
    }
}
/**
*判断是否为IE7
*/
function isIE7() {
    if (navigator.appVersion.indexOf("MSIE 7") > -1) {
        return true;
    } else {
        return false;
    }
}
/**
*打印异常
*/
function printExcept(obj) {
    obj.style.display = "none";
    window.print();
    obj.style.display = "";
}
/**
*读取Cookie
*/
function readCookie(name) {
    var cookieValue = "";
    var search = name + "=";
    if (document.cookie.length > 0) {
        offset = document.cookie.indexOf(search);
        if (offset != -1) {
            offset += search.length;
            end = document.cookie.indexOf(";", offset);
            if (end == -1) {
                end = document.cookie.length;
            }
            cookieValue = unescape(document.cookie.substring(offset, end));
        }
    }
    return cookieValue;
}
/**
*写入Cookie
*/
function writeCookie(name, value, hours) {
    var expire = "";
    if (hours != null) {
        expire = new Date((new Date()).getTime() + hours * 3600000);
        expire = ";  expires=" + expire.toGMTString();
    }
    document.cookie = name + "=" + escape(value) + expire;
}
/**
* Cross-browser XMLHttpRequest instantiation.
*/
if (typeof XMLHttpRequest == "undefined") {
    XMLHttpRequest = function () {
        var msxmls = ["MSXML3", "MSXML2", "Microsoft"];
        for (var i = 0; i < msxmls.length; i++) {
            try {
                return new ActiveXObject(msxmls[i] + ".XMLHTTP");
            }
            catch (e) {
            }
        }
        throw new Error("No XML component installed!");
    };
}
/**
*创建读取XMLHttpRequest
*/
function createXMLHttpRequest() {
    try {
    // Attempt to create it "the Mozilla way" 
        if (window.XMLHttpRequest) {
            return new XMLHttpRequest();
        }
    // Guess not - now the IE way
        if (window.ActiveXObject) {
            return new ActiveXObject(getXMLPrefix() + ".XmlHttp");
        }
    }
    catch (ex) {
    }
    return false;
} 
/**
 * unformat number数字转换
 * example:123,222.23 ==> 123322.23
 * @since 2006-09-01
 */
function unformatNumber(value){
  var retValue = replace(value+"", ",", "");
  var valueArray = retValue.split(".");
  if(valueArray.length>1 && valueArray[1]=="00"){
    retValue = valueArray[0];
  }
  return retValue;
}

/**
 * format number数字转换
 * example:123222.23 ==> 123,322.23
 * if has precision,the return value will have at least precision number after dot.
 * @since 2006-09-01
 */
function formatNumber(value,precision){
  value  =  value+"";
  var pos = value.indexOf('.');
  if(pos>-1){
    var firstValue=value.substring(0,pos);
    var lastValue=value.substring(pos+1);
    var  re=/(-?\d+)(\d{3})/
    while(re.test(firstValue)){
      firstValue=firstValue.replace(re,"$1,$2")
    }
    /*
    re=/(\d{3})(\d+)/
    while(re.test(lastValue)){
      lastValue=lastValue.replace(re,"$1,$2")
    }
    */
    value = firstValue + "." + lastValue;
  }else{
    var re=/(-?\d+)(\d{3})/
    while(re.test(value)){
      value=value.replace(re,"$1,$2")
    } 
  } 
  if(precision!=undefined && !isNaN(precision)){
  	var pos = value.indexOf('.'); 
  	if(pos==-1){
  		value+="."; 
  		pos = value.indexOf('.'); 
  	}
  	
  	var len = value.length-pos-1;
  	
		for(var i=len;i<precision;i++){
			value+="0";
		}  	
  }
  return  value;
}

/**
*复制Select
*/
function copyOptions(targetSelect,sourceSelect){

  for(var i=0; i<sourceSelect.length; i++){
 
    targetSelect.options[i]=new Option(sourceSelect.options[i].text,sourceSelect.options[i].value);
  }
}

/**
*定位到相应页
*/
function locate(pageNo,isFrame,iframeName){
    var inputpageNo = $("pageNo");
    inputpageNo.value=pageNo; 
    if(isFrame == 0){
         var inputRowsPerPage = $("rowsPerPage");
         inputRowsPerPage.value=document.frames("listPage").document.getElementById("rowsPerPage").value; 
    }
    var form = window.document.forms[0];
    if(iframeName!=null && iframeName.length>0)
    	form.target=iframeName;
    form.submit(); 
}
/**
*定位到相应页
*/
function locateTo(pageNo,pagesCount,iframeName){
    if(pageNo > 0 && pageNo <= pagesCount){
        var inputpageNo = $("pageNo");
        inputpageNo.value=pageNo;
        var form = window.document.forms[0];
        if(iframeName!=null && iframeName.length>0)
        	form.target=iframeName;
        form.submit(); 
    }else{
        alert("超出了页码范围,请重新输入!");
    }    
}
/**
*按每页相应记录数定位到第一页
*/
function locateForRowsPerPage(rowsPerPage,iframeName){
    if(rowsPerPage > 0 && rowsPerPage <= 100){
        var inputpageNo = $("pageNo");
        inputpageNo.value=1;
        var inputRowsPerPage = $("rowsPerPage");
        inputRowsPerPage.value=rowsPerPage;
        var form = window.document.forms[0];
        if(iframeName!=null && iframeName.length>0)
        	form.target=iframeName;
        form.submit(); 
    }else{
        alert("超出了条数范围,请重新输入!");
    }
}
/**
*数据校验
*/




//ReplaceAll方法
String.prototype.replaceAll = stringReplaceAll; 

function  stringReplaceAll(AFindText,ARepText){
  raRegExp = new RegExp(AFindText,"g");
  return this.replace(raRegExp,ARepText);
}
/**
 * 校验必输项是否输入,格式是否正确
 * 数据类型：
 *	int, 
 *	string，
 *	email，
 * 
 * allField格式：this.aa = new Array("txtDate","成交日期","date",1);
 * allFields参数："txtDate" -- 表单域名称
 *               "date"    -- 数据类型
 *               1         -- 是否必输项(1，是；0，否)
 * 例：
 *    将该方法放到具体的页面上 
 * function allFields()
 * {
 *		this.aa = new Array("txtName","名称","string",1);
 *		this.ab = new Array("txtDate","录入日期：","date",0);
 *		this.ad = new Array("txtNumber","数量","int",1);
 * }   
 */ 
function validateFields(form)
{
	var bValid = true;
	var focusField = null;
	var i = 0;
	var fields = new Array();
	oAllFields = new allFields();
	for (x in oAllFields) 
	{
	 	var bIsFormControl = false;
		if(!form[oAllFields[x][0]].type){
	 		var hj = form[oAllFields[x][0]];
	 		if (i == 0) 
	 			focusField = hj[0];
	 		for(var j=0;j<hj.length;j++){
	          if(hj[j].checked ){
	          	bIsFormControl = true ;
	          	break;
	          }
	       }
	     if(!bIsFormControl){
			fields[i++] = oAllFields[x][1]+"不能为空！";
			bValid = false;
	     	}
	    }
		if (form[oAllFields[x][0]].type == 'text' ||
				form[oAllFields[x][0]].type == 'textarea' ||
				form[oAllFields[x][0]].type == 'select-one' ||
				form[oAllFields[x][0]].type == 'radio' ||
				form[oAllFields[x][0]].type == 'password')
		{
			bIsFormControl = true;
		} 
		
		//alert (form[oAllFields[x][0]]);
		//alert (oAllFields[x][1]);
		//alert (oAllFields[x][2]);
		//alert (oAllFields[x][3]);
		
		//判断是否允许为空
		if ((bIsFormControl == true && oAllFields[x][3] == 1 && trim(form[oAllFields[x][0]].value) == '') ||
			(bIsFormControl == true && oAllFields[x][3] == 1 && oAllFields[x][2] == "list" && form[oAllFields[x][0]].value <= 0))
		{
			//alert (oAllFields[x][3]);
			if (i == 0) 
			{
				focusField = form[oAllFields[x][0]];
			}
			fields[i++] = oAllFields[x][1]+"不能为空！";
			bValid = false;
		}
		
		//如果是数字类型，则判断是否正确的数字类型;
		if (bIsFormControl == true && oAllFields[x][2] == 'int' && form[oAllFields[x][0]].value != '') 
		{
			//判断是否正确的数字类型
			if (!isInteger(form[oAllFields[x][0]].value))
			{
				if (i == 0) 
				{
					focusField = form[oAllFields[x][0]];
				}
				fields[i++] = oAllFields[x][1]+"必须为数字！";
				bValid = false;			
			}
		}
				
		//如果是Email类型，则判断是否正确的email类型
		if (bIsFormControl == true && oAllFields[x][2] == 'email' && form[oAllFields[x][0]].value != '') 
		{
			if (!isEmail(form[oAllFields[x][0]].value))
			{
				if (i == 0) 
				{
					focusField = form[oAllFields[x][0]];
				}
				fields[i++] = oAllFields[x][1]+"必须为Email格式！";
				bValid = false;			
			}
		}

		//如果是日期，则判断是否是正确的日期格式
		if (bIsFormControl == true && oAllFields[x][2] == 'date' && form[oAllFields[x][0]].value != '') 
		{
			if (!isDate(form[oAllFields[x][0]].value))
			{
				if (i == 0) 
				{
					focusField = form[oAllFields[x][0]];
				}
				fields[i++] = oAllFields[x][1]+"格式不正确，请重新录入！";
				bValid = false;			
			}
		}
		
		if (bIsFormControl == true && oAllFields[x][2] == 'phone' && form[oAllFields[x][0]].value != '') 
		{
			if (!CheckPhoneNum(form.getAttribute('name'),oAllFields[x][0],oAllFields[x][1]))
			{
				if (i == 0) 
				{
					focusField = form[oAllFields[x][0]];
				}
				fields[i++] = oAllFields[x][1]+"格式不正确，请重新录入！";
				bValid = false;			
			}
		}
		if (bIsFormControl == true && oAllFields[x][2] == 'minlength' && form[oAllFields[x][0]].value != '') 
		{
			if (!CheckLength(form.getAttribute('name'),oAllFields[x][0],oAllFields[x][1],oAllFields[x][4],999999))
			{
				if (i == 0) 
				{
					focusField = form[oAllFields[x][0]];
				}
				fields[i++] = oAllFields[x][1]+"长度控制不小于"+oAllFields[x][4]+"位";
				bValid = false;			
			}
		}
		if (bIsFormControl == true && oAllFields[x][2] == 'minlength' && form[oAllFields[x][0]].value != '') 
		{
			if (!CheckLength(form.getAttribute('name'),oAllFields[x][0],oAllFields[x][1],0,oAllFields[x][4]))
			{
				if (i == 0) 
				{
					focusField = form[oAllFields[x][0]];
				}
				fields[i++] = oAllFields[x][1]+"长度控制不大于"+oAllFields[x][4]+"位";
				bValid = false;			
			}
		}	
		

	}
	if (fields.length > 0) 
	{  
		focusField.focus();
		alert(fields.join('\n'));
	}
	return bValid;
} 

// 校验Email格式
function isEmail(S){
    var pass=0; 
    if (window.RegExp){
        var tempS="a"; 
        var tempReg=new RegExp(tempS);
        if (tempReg.test(tempS)) pass=1;
    }
    if(!pass)return(S.indexOf(".")>2) && (S.indexOf("@")>0);
    var r1=new RegExp("(@.*@)|(\\.\\.)|(@\\.)|(^\\.)");
    var r2=new RegExp("^[a-zA-Z0-9\\.\\!\\#\\$\\%\\&\\’\\*\\+\\-\\/\\=\\?\\^\\_\\`\\{\\}\\~]*[a-zA-Z0-9\\!\\#\\$\\%\\&\\’\\*\\+\\-\\/\\=\\?\\^\\_\\`\\{\\}\\~]\\@(\\[?)[a-zA-Z0-9\\-\\.]+\\.([a-zA-Z]{2,3}|[0-9]{1,3})(\\]?)$");
    return (!r1.test(S) && r2.test(S)); 
}


/**
* 校验是否电邮地址
* 
*/
function IsEmail( d_email)
{		
	var checkStr = d_email;
	var emailtag = false;
	var emaildot=0
	var emailat=0
	if (checkStr.length<7) return (false);
	for (i = 0;  i < checkStr.length;  i++)
	{
		ch = checkStr.charAt(i);
		if (ch=='@') emailat++;	
		if (ch=='.') emaildot++;	
	}				
	if (( emailat==1 ) ) 
	{
		emailtag = true;
	}
	return (emailtag);  	
}

//是否是float型
function isInnerFloat( d_float)
{
	if(isNaN(parseFloat(d_float)))
	  return false;
	else
	  return true;
}

//检查email
function CheckEmail(frmName,txtName,txtLab)
{
	var frmTemp,temp;
	frmTemp=document.forms[frmName];
	temp=frmTemp.elements[txtName].value;
	
	if(temp=="")
	{
		alert("请在" + txtLab +"中输入正确的e-Mail地址!")
		frmTemp.elements[txtName].focus();
		return false;
	}
	var i = temp.indexOf("@"); 
	var j = temp.indexOf(".");
	if(parseInt(i)>1 )
		return true;
	else
	{
		alert("请在" + txtLab +"中输入正确的e-Mail地址!")
		frmTemp.elements[txtName].focus();
		return false;
	}
}

// 检查表单是否为空
function CheckNull(frmName,txtName,txtLab)
{
	var frmTemp,temp;
	frmTemp=document.forms[frmName];
	temp=frmTemp.elements[txtName].value;
	
	if(temp=="")
	{
		alert("请输入" + txtLab + "！");
		frmTemp.elements[txtName].focus();
		return false;
	}
	return true;
}

// 检查输入框输入字符的长度，
function CheckLength(frmName,txtName,txtLab,minLen,maxLen)
{
	var temp,lCount=0;
	frmTemp=document.forms[frmName];
	temp=new String(frmTemp.elements[txtName].value);
	for(var i =0;i<temp.length;i++)
	{
		if(temp.charCodeAt(i)>255)
			lCount +=2;
		else
			lCount +=1;
	}
	if(minLen>0 && lCount==0)
	{
		//alert("请输入"+txtLab);
		//frmTemp.elements[txtName].focus();
		return false;
	}
	if(lCount<minLen)
	{
		//alert(txtLab +"至少需要输入"+minLen+"个字符");
		//frmTemp.elements[txtName].focus();
		return false;
	}
	if(lCount>maxLen)
	{
		//alert(txtLab +"过长，请删减");
		//frmTemp.elements[txtName].focus();
		return false;
	}
	return true;
}

// 检查输入框输入的类型
function CheckPhoneNum(frmName,txtName,txtLab)
{
	var frmTemp,temp;
	frmTemp=document.forms[frmName];
	temp=frmTemp.elements[txtName].value;
	
	if(temp=="")
	{
		//alert("请在" + txtLab +"中输入数据!")
		//frmTemp.elements[txtName].focus();
		return false;
	}
	
	var regTextPhone = /^(\(\d+\))*(\d)+(-(\d)+)*$/;
	return regTextPhone.test(temp.replace(' ',''));
}

// 检查下拉列表框是否选中值
function CheckSelect(frmName,txtName,txtLab,intIllegue)
{
	var frmTemp,temp;
	frmTemp=document.forms[frmName];
	temp=frmTemp.elements[txtName].value;
	if (temp==intIllegue)
	{
		alert("请在" + txtLab +"中选择");
		frmTemp.elements[txtName].focus();
		return false;
	}
	return true;
}

//isEmpty     : 校验输入的参数是否为NULL
function isEmpty(inputStr) {
	if (inputStr == null || inputStr == '') return true;
	return false;
}

//isInteger   : 包括0的整数
function isInteger(inputVal) {
	inputStr = inputVal.toString();
	if (isEmpty(inputStr)) return false;
	for (var i = 0; i < inputVal.length; i ++ ) {
		var oneChar = inputVal.charAt(i);
		if (i == 0 && (oneChar == "+" || oneChar == "-") )
			if (inputVal.length == 1 ) 	return false;
			else continue;
		if (oneChar < "0" || oneChar > "9")
			return false;
	}
	return true;
}


//isNature    : 自然数：大于零的整数
function isNature(inputVal) {
	if (isInteger(inputVal)) {
		if (parseInt(inputVal.toString()) < 1 ) return false;
	}
	else	return false;
	return true;
}

//isNumberOrNull  : 数字型：仅仅是数据或为空
function isNumberOrNull(inputVal) {
	oneDecimal = false;
	inputStr = inputVal.toString();
	if (isEmpty(inputStr)) return true;
	for (var i = 0; i < inputVal.length; i ++ ) {
		var oneChar = inputVal.charAt(i);
		if (i == 0 && (oneChar == "+" || oneChar == "-") )
			if (inputVal.length == 1 ) 	return false;
			else continue;
		if (oneChar == "." && !oneDecimal) {
			oneDecimal = true;
			continue;
		}
		if (oneChar < "0" || oneChar > "9")
			return false;
	}
	return true;
}
//isNumberNotNull  : 数字型：仅仅是数据
function isNumberNotNull(input) {
	oneDecimal = false;
	inputVal = trim(input.value);
	if (isEmpty(inputVal)) 
	{
		alert("请输入数字!");
		//input.focus();
		return false;
	}
	for (var i = 0; i < inputVal.length; i ++ ) {
		var oneChar = inputVal.charAt(i);
		if (i == 0 && (oneChar == "+" || oneChar == "-") )
			if (inputVal.length == 1 )
			{
				alert("请输入数字!");
				//input.focus();
			 	return false;
			}
			else continue;
		if (oneChar == "." && !oneDecimal) {
			oneDecimal = true;
			continue;
		}
		if (oneChar < "0" || oneChar > "9")
		{
			alert("请输入数字!");
			//input.focus();
		 	return false;
		}
	}
	return true;
}
//isNumber    : 正整数
function isNumber(input) {
	oneDecimal = false;
	inputVal = trim(input.value);
	if (isEmpty(inputVal)) 
	{
		alert("请输入正整数!");
		input.select();
		input.focus();
		return false;
	}
	for (var i = 0; i < inputVal.length; i ++ ) {
		var oneChar = inputVal.charAt(i);
		if (i == 0 && (oneChar == "+" || oneChar == "-") )
			if (inputVal.length == 1 )
			{
				alert("请输入正整数!");
				input.select();
				input.focus();
			 	return false;
			}
			else continue;
		if (oneChar == "." && !oneDecimal) {
			oneDecimal = true;
			continue;
		}
		if (oneChar < "0" || oneChar > "9")
		{
			alert("请输入正整数!");
			input.select();
			input.focus();
		 	return false;
		}
	}
	//xuancrystal modify
	
	  var re = /^[1-9]+[0-9]*]*$/; 　　//正整数 
            
        if(!re.test(inputVal))
           {
			 alert("请输入正整数");
			 input.select();
			 input.focus();
			 return false;
	        }
	return true;
}

//isInteger    : 由数字组成的字串
function isInteger(frmName,txtName,txtLab) 
{
	var frmTemp,temp;
	frmTemp=document.forms[frmName];
	temp=frmTemp.elements[txtName].value;
	if (temp=="")
	{
		alert("请在" + txtLab +"中输入数字!");
		frmTemp.elements[txtName].focus();
		return false;
	}
	temp= Math.abs(temp);
	if(temp.toString()=="NaN")
	{
		alert("请在" + txtLab +"中输入数字!");
		frmTemp.elements[txtName].focus();
		return false;
	}
	if(parseInt(temp)<0)
	{
		alert("在" + txtLab +"中输入正整数");
		frmTemp.elements[txtName].focus();
		return false; 
	}
	var re,p;
	re = /\./i;  
	temp=temp.toString();
	p=temp.search(re);
	if(p==-1)
		return true;
	else
	{
		alert("在" + txtLab +"中输入正整数");
		frmTemp.elements[txtName].focus();
		return false; 
	}
	return true;
}

//isArabic    : 由数字组成的字串
function isArabic(inputVal) {
	var checkOK = "0123456789";
	var checkStr = inputVal.toString();
	if (isEmpty(checkStr)) return false;
	for (i = 0;  i < checkStr.length;  i++){
		ch = checkStr.charAt(i);
		if (checkOK.indexOf(ch) == -1)
			return (false);
	}
	return true;
}

/**
 * 比较两个日期大小
 * 日期格式：2003-09-18 或 2003/09/18
 * 如果dtStart>dtEnd，返回false,否则返回true
 */
function compareDate(dtStart,dtEnd)
{
	var temp,s;
	temp=new String(dtStart);
	s=new String("");
	for(var i=0;i<=temp.length-1;i++)
	{
		if(temp.charAt(i)=="-" || temp.charAt(i)=="/")
		{
			s=s+"/";
		}
		else
		{
			s=s+temp.charAt(i);
		}
	}
	dtOne=new Date(s);
	temp=new String(dtEnd);
	s=new String("");
	for(var i=0;i<=temp.length-1;i++)
	{
		if(temp.charAt(i)=="-" || temp.charAt(i)=="/")
		{
			s=s+"/";
		}
		else
		{
			s=s+temp.charAt(i);
		}
	}
	dtTwo=new Date(s);
	if(dtOne.valueOf()>dtTwo.valueOf())
	{
		return false;
	}
	return true;	
}

/**
 *对数字的小数位进行四舍五入
 *param number 	需要做四舍五入的数字
 *param deci	需要保留的小数位数,小数位数不能多于6位,大于六位精度有时候会出现问题
 *return 		四舍五入后的数字,如果数字没有小数位,数字精度低于于要求的精度则返回原值,如果输入不是数字型返回0
 */
function round(num, deci){
	if (isNaN(num)) return 0;			//如果不是数字,返回0
	
	var tmp			= "1";						//中间变量
	var returnValue = new String(num);					//返回值
	var index 		= returnValue.indexOf(".");	//小数点位置
	var len 		= index + deci + 1;			//返回数值的长度
	var precision	= 0.0;						//需要的精度,用来计算准确的返回值
	
	for (var n=0;n<deci;n++){					//取得需要的位数
		tmp += "0";	
	}
	precision = parseFloat(tmp); 				//获得数值
	
	if (index != -1 && len<returnValue.length){
		var nextNum = returnValue.charAt(len);
		returnValue = returnValue.substring(0,len);
		if (parseInt(nextNum)>=5) returnValue = (parseFloat(returnValue)*precision+1)/precision;
	}

	return parseFloat(returnValue);
}

/**
 * 判断字符的长度
 */
String.prototype.realLength = function()
{
return this.replace(/[^\\x00-\\xff]/g,"**").length;
}
//退出提示信息。
function onLogout()
{
	if (confirm("您确定要退出系统吗？")) {
		location.href = "login.do?method=logout";
	}
}


///以popup方式打开对话框
var newWindow = null;
var bOpening = false;
function doModal(url,MyWindow,mwidth,mheight, mLeft, mTop)
{
	if(!bOpening && ((newWindow == null) || newWindow.closed))
	{
		bOpening = true;
		if (window.showModelessDialog)
		{
			newWindow = window.showModelessDialog(url,MyWindow,"help:0;resizable:0;status:0;scroll:1;dialogWidth:"+mwidth+"px;dialogHeight:"+mheight+"px;dialogLeft:"+mLeft+"px;dialogTop:"+mTop+"px");
		}
		else
		{
			if(mLeft == null)
				mLeft = 120;

			if(mTop == null)
				mTop = 80;

			newWindow = window.open(url,"","width="+mwidth+"px,height="+mheight+"px,resizable=0,scrollbars=1,statusbar=0,menubar=0,left="+mLeft+"px,top="+mTop+"px");
		}
		newWindow.name = "NewWindow";
		window.setTimeout("bOpening = false;", 1000, "JAVASCRIPT");
		return newWindow;
	}
}

//打开新的页面。
function openWin(url,MyWindow,mwidth,mheight, mLeft, mTop)
{
   window.open(url,MyWindow,"width=" + mwidth + "px,height=" + mheight + "px,resizable=0,scrollbars=1,statusbar=0,menubar=0,left=" + mLeft + "px,top=" + mTop + "px");
}


//检查文本框输入字数限制

function checkMaxInput(form,maxLen) {
maxLen = 300; // max number of characters allowed
if (form.content.value.length > maxLen) // if too long.... trim it!
form.content.value = form.content.value.substring(0, maxLen);
// otherwise, update 'characters left' counter
else form.remLen.value = maxLen - form.content.value.length;
}
//得到当前的时间，flag=time:返回带时间，flag=date 只返回日期
function getNowTime(flag){
	var today=new Date(); 
	var year = today.getYear();
	var month = today.getMonth()+1;
	var d = today.getDate();
	var h=today.getHours(); 
	var m=today.getMinutes(); 
	var s=today.getSeconds(); 
//add a zero in front of number
	var temp = year + "-" + month + "-" + d;
	
	if(flag == "date"){
		return temp;
	}
	if(flag == "time"){
		temp = temp + " " + h + ":" + m + ":" + s;
		return temp;
	}
}

//四舌五入,参数(小数，保留位数)
function round2(x,y){
	return isNaN(x)?"":Math.round(x*(y=Math.pow(10,y)))/y;
}
/*
 * 结果列表移除某列。
 * 		例：<img index="2" src="/pub/images/trash.gif" alt="移除列" onclick="hideTD(this.index);">
 * 		可以通过任何形式调用此方法，index要移除的当前列。注:table的列是由0开始。
 * **/
function hideTD(index,tableId){
	tableId = "#" + (typeof tableId == "string" && tableId.lelngth>0 ? tableId : "list");
	jQuery(tableId+" tr").each(function(i){
		jQuery("td:eq("+index+")",this).hide();
	});
}
//显示结果列表集中隐藏的TD。显示全部。
function showTD(){
	jQuery("#list tr").each(function(i){
		jQuery("td:hidden",this).show();
	});
}
/**
 * li样式转换
 * @param li_id li的ID
 * @return
 * <ul class="crdsTabsUl" id = "crdsTabsUl">
 *		<li id='comList' class="crdsTabsUlLi" tabindex="0" onclick="functionName('<%=path%>',this.id)">&nbsp;公司列表&nbsp;</li>					
 *		<li id='distribute' class="crdsTabsUlLi" tabindex="1" onclick="functionName('<%=path%>',this.id)">&nbsp;组合分布&nbsp;</li>
 *		<li id='transfer' class="crdsTabsUlLi" tabindex="2" onclick="functionName(0,'<%=path%>',this.id)">&nbsp;迁移分布&nbsp;</li>
 *		<li id='compare' class="crdsTabsUlLi" tabindex="3" onclick="functionName('<%=path%>',this.id)">&nbsp;基准比较&nbsp;</li>
 *	</ul>
 *	只需要在调用的函数中把li得传递到此方法就可以进行样式转换。	
 	<ul class="crdsTabsUl" id = "crdsTabsUl">
 		<li id ="1" class="crdsTabsUlLi"></li>
 		<li id ="2 class="crdsTabsUlLi"></li>
 		<li id ="3 class="crdsTabsUlLi"></li>
 	</ul>
 ***/
function liClass(li_id){
	if(li_id!=null && li_id.length>0){
		var selectItem ="crdsTabsUlLiSelect";
		var nonSelect =" ";
		jQuery("#crdsTabsUl li").each(function(i){
			if(this.id == li_id){
				jQuery(this).addClass(selectItem);
			}else{
				jQuery(this).removeClass(selectItem);
				jQuery(this).addClass(nonSelect);
			}
		});
	}
}
/**
*获取元素通过id
*/
function $(id) {return document.getElementById(id);}
/**
*获取元素通过TagName
*/
function $F(name){return document.getElementsByTagName(name);}


///table排序begin 调用只需要在列表table中添加 class="sortable title" id="sortTable" 即可。
addEvent(window, "load", sortables_init);
var SORT_COLUMN_INDEX;
var SORT_COLUMN_START_INDEX = 1;
var SORT_COLUMN_END_INDEX = 100;
var ratingN = new Array([1,"AAA"],
					   	 [2,"AA+"],
				    	 [3,"AA"],
				      	 [4,"AA-"],
				      	 [5,"A+"],
					   	 [6,"A"],
				    	 [7,"A-"],
				      	 [8,"BBB+"],
				      	 [9,"BBB"],
					   	 [10,"BBB-"],
				    	 [11,"BB+"],
				      	 [12,"BB"],
				      	 [13,"BB-"],
					   	 [14,"B+"],
				    	 [15,"B"],
				      	 [16,"B-"],
				      	 [17,"CCC+"],
					   	 [18,"CCC"],
				    	 [19,"CCC-"],
				      	 [20,"CC+"],
				      	 [21,"CC"],
				    	 [22,"CC-"],
				      	 [23,"C+"],
				      	 [24,"C"],
				    	 [25,"C-"],
				      	 [26,"D"],
				      	 [27,"A-1"],
					   	 [28,"A-2"],
				    	 [29,"A-3"],
				      	 [30,"无"]);

function sortables_init() {
	// Find all tables with class sortable and make them sortable
	if (!document.getElementsByTagName)
		return;
	tbls = document.getElementsByTagName("table");
	for (ti = 0; ti < tbls.length; ti++) {
		thisTbl = tbls[ti];
		if (((' ' + thisTbl.className + ' ').indexOf("sortable") != -1)
				&& (thisTbl.id)) {
			// initTable(thisTbl.id);
			ts_makeSortable(thisTbl);
		}
	}
}
function ts_makeSortable(table) {
	if (table.rows && table.rows.length > 0) {
		var firstRow = table.rows[0];
	}
	if (!firstRow)
		return;
	// We have a first row: assume it's the header, and make its contents
	// clickable
	// links
	if (!SORT_COLUMN_START_INDEX) {
		SORT_COLUMN_START_INDEX = 0;
	}
	if (!SORT_COLUMN_END_INDEX) {
		SORT_COLUMN_END_INDEX = firstRow.cells.length;
	}
	for (var i = SORT_COLUMN_START_INDEX; i < SORT_COLUMN_END_INDEX; i++) {
		var cell = firstRow.cells[i];
		if (cell == null) {
			continue;
		}
		var txt = ts_getInnerText(cell);
		cell.innerHTML = '<a href="#" class="channel" '
				+ 'onclick="ts_resortTableBySingleCol(getParent(this,\'TABLE\'), '
				+ i + ');return false;">' + txt
				+ '<span class="sortarrow"> </span></a>';
	}

}
function ts_getInnerText(el) {
	if (typeof el == "string")
		return el;
	if (typeof el == "undefined") {
		return el;
	}
	if (el.innerText && trim(el.innerText) != "") {
		return el.innerHTML; // Not needed but it is faster
	}
	if (el.value && trim(el.value) != "") {
		return el.value;
	}
	var str = "";
	var cs = el.childNodes;
	var l = cs.length;
	for (var i = 0; i < l; i++) {
		switch (cs[i].nodeType) {
			case 1 : // ELEMENT_NODE
				str += ts_getInnerText(cs[i]);
				break;
			case 3 : // TEXT_NODE
				str += cs[i].nodeValue;
				break;
		}
	}
	return str;
}
function ts_clearSpan(table) {
	// Delete any other arrows there may be showing
	var allspans = document.getElementsByTagName("span");
	for (var ci = 0; ci < allspans.length; ci++) {
		if (allspans[ci].className == 'sortarrow') {
			if (getParent(allspans[ci], "table") == table) {
				allspans[ci].innerHTML = ' ';
			}
		}
	}
}
function ts_resortTableByMutiCols(table, colIds, sortKeys) {
	if (!table)
		return;
	ts_clearSpan(table);
	for (var i = colIds.length - 1; i >= 0; i--) {
		ts_resortTable(table, colIds[i], sortKeys[i]);
	}
}
function ts_resortTableBySingleCol(table, clid, sortKey) {
	if (!table)
		return;
	ts_clearSpan(table);
	ts_resortTable(table, clid, sortKey);
}
function ts_resortTable(table, clid, sortKey) {
	if (table.rows && table.rows.length > 0) {
		var firstRow = table.rows[0];
	}
	if (!firstRow)
		return;
	var td = firstRow.cells[clid];
	var column = clid || td.cellIndex;
	// get lnk;
	var lnk;
	for (var ci = 0; ci < td.childNodes.length; ci++) {
		if (td.childNodes[ci].tagName
				&& td.childNodes[ci].tagName.toLowerCase() == 'a') {
			lnk = td.childNodes[ci];
		}
	}
	// get the span
	var span;
	for (var ci = 0; ci < lnk.childNodes.length; ci++) {
		if (lnk.childNodes[ci].tagName
				&& lnk.childNodes[ci].tagName.toLowerCase() == 'span') {
			span = lnk.childNodes[ci];
		}
	}
	var spantext = ts_getInnerText(span);

	// Work out a type for the column
	if (table.rows.length <= 1)
		return;
	var itm = trim(ts_getInnerText(table.rows[1].cells[column]));
	sortfn = ts_sort_caseinsensitive;
	if (itm.match(/^\d\d[\/-]\d\d[\/-]\d\d\d\d$/))
		sortfn = ts_sort_date;
	if (itm.match(/^\d\d[\/-]\d\d[\/-]\d\d$/))
		sortfn = ts_sort_date;
	if (itm.match(/^[?]/))
		sortfn = ts_sort_currency;
	if (itm.match(/^[+-]?[0-9.]*$/))
		sortfn = ts_sort_numeric;
	if (itm.match(/^[A-D]*[-]?[1-3]?[+-]?$/)|| itm == '无'){
		sortfn = ts_sort_rating;
	}
		
	SORT_COLUMN_INDEX = column;
	var firstRow = new Array();
	var newRows = new Array();
	for (i = 0; i < table.rows[0].length; i++) {
		firstRow[i] = table.rows[0][i];
	}
	for (j = 1; j < table.rows.length; j++) {
		newRows[j - 1] = table.rows[j];
	}
	newRows.sort(sortfn);
	if (sortKey) {
		if (sortKey == "up") {
			span.setAttribute('sortdir', 'down');
		} else {
			span.setAttribute('sortdir', 'up');
		}
	}
	var sortdir = span.getAttribute("sortdir");
	if (sortdir == 'up') {
		ARROW = ' ↓';
		newRows.reverse();
		span.setAttribute('sortdir', 'down');
	} else {
		ARROW = ' ↑';
		span.setAttribute('sortdir', 'up');
	}
	// We appendChild rows that already exist to the tbody, so it moves them
	// rather
	// than creating new ones
	// don't do sortbottom rows
	for (i = 0; i < newRows.length; i++) {
		if (!newRows[i].className
				|| (newRows[i].className && (newRows[i].className
						.indexOf('sortbottom') == -1))) {
			table.tBodies[0].appendChild(newRows[i]);
		}
	}
	// do sortbottom rows only
	for (i = 0; i < newRows.length; i++) {
		if (newRows[i].className
				&& (newRows[i].className.indexOf('sortbottom') != -1)) {
			table.tBodies[0].appendChild(newRows[i]);
		}
	}
	span.innerHTML = ARROW;
}

function getParent(el, pTagName) {
	if (el == null) {
		return null;
	} else if (el.nodeType == 1
			&& el.tagName.toLowerCase() == pTagName.toLowerCase()) {
		return el;
	} else {
		return getParent(el.parentNode, pTagName);
	}
}

function ts_sort_date(a, b) {
	// y2k notes: two digit years less than 50 are treated as 20XX, greater than
	// 50
	// are treated as 19XX
	aa = ts_getInnerText(a.cells[SORT_COLUMN_INDEX]);
	bb = ts_getInnerText(b.cells[SORT_COLUMN_INDEX]);
	if (aa.length == 10) {
		dt1 = aa.substr(6, 4) + aa.substr(3, 2) + aa.substr(0, 2);
	} else {
		yr = aa.substr(6, 2);
		if (parseInt(yr) < 50) {
			yr = '20' + yr;
		} else {
			yr = '19' + yr;
		}
		dt1 = yr + aa.substr(3, 2) + aa.substr(0, 2);
	}
	if (bb.length == 10) {
		dt2 = bb.substr(6, 4) + bb.substr(3, 2) + bb.substr(0, 2);
	} else {
		yr = bb.substr(6, 2);
		if (parseInt(yr) < 50) {
			yr = '20' + yr;
		} else {
			yr = '19' + yr;
		}
		dt2 = yr + bb.substr(3, 2) + bb.substr(0, 2);
	}
	if (dt1 == dt2)
		return 0;
	if (dt1 < dt2)
		return -1;
	return 1;

}
function ts_sort_currency(a, b) {
	aa = ts_getInnerText(a.cells[SORT_COLUMN_INDEX]).replace(/[^0-9.]/g, '');
	bb = ts_getInnerText(b.cells[SORT_COLUMN_INDEX]).replace(/[^0-9.]/g, '');
	return parseFloat(aa) - parseFloat(bb);
}
function ts_sort_numeric(a, b) {
	aa = parseFloat(ts_getInnerText(a.cells[SORT_COLUMN_INDEX]));
	if (isNaN(aa))
		aa = 0;
	bb = parseFloat(ts_getInnerText(b.cells[SORT_COLUMN_INDEX]));
	if (isNaN(bb))
		bb = 0;
	return aa - bb;

}
function ts_sort_caseinsensitive(a, b) {
	aa = ts_getInnerText(a.cells[SORT_COLUMN_INDEX]).toLowerCase();
	bb = ts_getInnerText(b.cells[SORT_COLUMN_INDEX]).toLowerCase();
	if (aa == bb)
		return 0;
	if (aa < bb)
		return -1;
	return 1;
}
function ts_sort_default(a, b) {
	aa = ts_getInnerText(a.cells[SORT_COLUMN_INDEX]);
	bb = ts_getInnerText(b.cells[SORT_COLUMN_INDEX]);
	if (aa == bb)
		return 0;
	if (aa < bb)
		return -1;
	return 1;
}

function ts_sort_rating(a, b) {
	aa = trim(ts_getInnerText(a.cells[SORT_COLUMN_INDEX]));
	bb = trim(ts_getInnerText(b.cells[SORT_COLUMN_INDEX]));
	
	var ai = 0;
	var bi = 0;
	for(i=0;i<ratingN.length;i++){
		if(aa == ratingN[i][1]){
			ai = ratingN[i][0];
			break;
		}
	}
	for(i=0;i<ratingN.length;i++){
		if(bb == ratingN[i][1]){
			bi = ratingN[i][0];
			break;
		}
	}

	if (ai == bi)
		return 0;
	if (bi < ai)
		return -1;
	return 1;
}
/**
* js加法运算
*/


function accAdd(arg1,arg2){ 
	var r1,r2,m; 
	try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0} 
	try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0} 
	m=Math.pow(10,Math.max(r1,r2)) 
	return (arg1*m+arg2*m)/m 
} 

function clearContent(param,target) {
	if(param=='请填写!'||param=='无需!' ||param=='要填写的文字!' || param=='无')
	target.value='';
}

// addEvent and removeEvent
// cross-browser event handling for IE5+, NS6 and Mozilla
// By Scott Andrew
function addEvent(elm, evType, fn, useCapture) {
	if (elm.addEventListener) {
		elm.addEventListener(evType, fn, useCapture);
		return true;
	} else if (elm.attachEvent) {
		var r = elm.attachEvent("on" + evType, fn);
		return r;
	} else {
		alert("Handler could not be removed");
	}
}
//设置iframe的高度(frame:需要设置高度的frame;sheight:设置的高度)
function setHeight(frame,sheight){
//	frame.height = document.frames(frame.name).document.body.scrollHeight;
//	if(frame.height>sheight){
		frame.height=sheight;
//	}
}
//startWith方法
String.prototype.startWith = function( value ){
	return ( this.substr( 0, value.length ) == value ) ;
}
//endWith方法
String.prototype.endWith=function(oString){   
	var reg=new RegExp(oString+"$");   
	return reg.test(this);   
}  
//上传校验 ul_id：文件选择框ID  arr_allow_extname:允许上传的后缀名数组 如var imgType=new Array('.jpg','.jpeg','.bmp','.gif','.png');
function checkUpLoad(ul_id,arr_allow_extname){
	if(!arr_allow_extname){
		arr_allow_extname=new Array();
	}

    var arr_unallow_extname=new Array('.exe','.bat');
    var ulFile=jQuery("#"+ul_id).val();
    var limit  = 5 * 1024 * 1024;//文件大小不超过5M
    if(ulFile == ""){
   	alert("请选择要上传的文件。");
      	return false;
    }

	//后缀名校验
    var filePath=ulFile.toLowerCase();
    //允许的后缀名
    var allow_flag=0;
	for(var i=0;i<arr_allow_extname.length;i++){
		if(filePath.endWith(arr_allow_extname[i].toLowerCase())){
			allow_flag++;
		}
	}
	//不允许的后缀名
	if(arr_allow_extname.length==0){
	   var unallow_flag=0;
	   for(var i=0;i<arr_unallow_extname.length;i++){
			if(filePath.endWith(arr_unallow_extname[i].toLowerCase())){
				unallow_flag++;
			}
		}
	    if(unallow_flag>0)
	    {
	    	alert("非法的上传文件。");
	    	return false;
	    }
	}else{
		if(allow_flag==0){
			var ext='';
			for(var i=0;i<arr_allow_extname.length;i++){
				ext+=arr_allow_extname[i].substring(1);
				if(i<arr_allow_extname.length-1){
					ext+="|";
				}
			}
			alert("请上传后缀名为"+ext+"的文件。");
	    	return false;
		}
	}
	//大小校验
	var image=new Image();    
    image.dynsrc=ulFile;
    if (image.fileSize > limit)
    {
        alert("上传文件大小不能超过5M。");
        return false;
    }
    return true;

}