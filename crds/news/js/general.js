
/*--
 * 2004-11-15 �������߰�������getOnlineHelp
 * 2004/11/01 ���� isNotNull ,str = str.trim()������ trim(str) �����Ǻܾ���ǰ���󣬲�֪�ĸ���ԭ�ģ�
 *            ��isNumber ���� return true;
 * 2005-02-17 zhoujf ��������ע��
--*/

//-----Ч��-------------------------------------------------------------------------------------------------------
/** 
 * ���ܣ����ж��Ƿ����ں���
 * ���룺��srcStr ���ж��ַ���, sFieldName ��������
 * �������true ���ڣ���false ������
 */
function isDateByValue(srcStr, sFieldName)
{	
	if (srcStr.length != 10) 
	{
		alert("����� [ " + sFieldName + " ]  ��ʽ����! ������Ϊ yyyy-mm-nn ��ʽ!");
		return false;
	}
	
	var year = srcStr.substring(0,4);
	var month = srcStr.substring(5,7);
	var day = srcStr.substring(8,10);
	var d_t1 = srcStr.substring(4,5);
	var d_t2 = srcStr.substring(7,8);	

	if (isNaN(day) || isNaN(month) || isNaN(year) || d_t1 != "-" || d_t2 != "-")
	{
		alert("����� [ " + sFieldName + " ]  ��ʽ����! ������Ϊ yyyy-mm-nn ��ʽ!");
		return false;
	}
	if (day > 31 || day < 1 || month > 12 )
	{
		alert("����� [ " + sFieldName + " ]  �����д���!");
		return false;
	}
	if (month == 4 || month == 6 || month == 9 || month == 11)
	{
		if (day > 30)
		{
			alert("����� [ " + sFieldName + " ]  �����д���!");
			return false;
		}	
	}
	if (month == 2)
	{
		if(year % 4 !=0 || (year % 400 != 0 && year % 100 == 0))
		{
			if(day > 28)
			{
				alert("����� [ " + sFieldName + " ]  �����д���!");
				return false;
			}
		}
		else
		{ 
			if( day > 29)
			{
				alert("����� [ " + sFieldName + " ]  �����д���!");
				return false;
			}
		}
	}

	return true;	
}

/** 
 * ���ܣ����Ƚ����ں���
 * ���룺��strDate1 ����1, strDate2 ����2
 * �������-1 : date1 ���� date2, 0 : date1 ���� date2, 1 : date1 ���� date2
 */
function CompareDate(strDate1, strDate2)
{
	
	if('' == strDate1.trim() || '' == strDate2.trim())
		return false;
	
	if(false == isDateByValue(strDate1, '����1') || false == isDateByValue(strDate2, '����2'))
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
 * ���ܣ� �ж��Ƿ�����
 * ���룺 obj ���жϵ�Object reference, paramName ��ʾ������ʾ������
 * ����� ��������� ����true, ���� ����false
 */
function isNumber(obj)
{		
	 if((obj.value==null)||(obj.value=="")) return true; //Ĭ��Ϊ0
	if(isNaN(obj.value))
	{
   		alert("����������");            
		obj.select();
		return false;
	}else
		return true;
}
//���������onkeyup=""��ֻ������������
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
 * ���ܣ� �ж��Ƿ�������
 * ���룺 obj ���жϵ�Object reference, paramName ��ʾ������ʾ������
 * ����� ��������� ����true, ���� ����false
 */
function isInt(obj)
{		
	 if((obj.value==null)||(obj.value=="")) return true; //Ĭ��Ϊ0
	if(isNaN(obj.value) || (obj.value.indexOf('.') !=-1) || (obj.value.lastIndexOf('-') !=-1))
	{
   		alert("������һ������");            
		obj.select();
		return false;
	}//end if
	return true;
}//end function

/**
 * ���ܣ� �ж��Ƿ�������
 * ���룺 obj ���жϵ�Object reference, paramName ��ʾ������ʾ������
 * ����� ��������� ����true, ���� ����false 
 */
function isInteger(obj)
{		

    if((obj.value==null)||(obj.value=="")) return true; //Ĭ��Ϊ0
	if(isNaN(obj.value) || (obj.value.indexOf('.') !=-1) || (obj.value.lastIndexOf('-') !=-1))
	{   		
	    alert("������һ������");            
		obj.select();
		return false;
	}else{
		return true;
	}
}//end function


/**
 * ���ܣ� Email���麯��
 * ���룺 obj ��У���Object reference�� msg ����ʾ��Ϣ
 * ����� true �� false 
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
		alert((msg?msg:'�������׼��ʽEmail'));
		obj.select();
		return false;
	}
}
/**
 * ���ܣ� Email���麯�� liuz 2005-03-08 because of guanj
 * ���룺 str ��У���str
 * ����� true �� false 
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

/**�绰��ʽЧ��
* @param obj ��У���Object reference
* @param msg ����ʾ��Ϣ
* return false
*/
function isTelephone(obj,msg)
{
	if ('' == obj.value.trim())
		return true;
	var reg=/(^[0-9]{3,4}\-[0-9]{3,8}$)|(^[0-9]{3,8}$)|(^\([0-9]{3,4}\)[0-9]{3,8}$)|(^0{0,1}13[0-9]{9}$)/
	if(reg.test(obj.value)) return true;
	else {
		alert((msg?msg:'����绰�д���'));obj.select();return false;
		}	
}

/**
 * ���ܣ� Ӣ����ĸ���麯��
 * ���룺 obj ��У���Object reference�� msg ����ʾ��Ϣ
 * ����� true �� false 
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
		alert((msg?msg:'����ֻ������Ӣ��, �����'));
		obj.select();
		return false;
	}
}

/**
 * ���ܣ� ���Ľ��麯��
 * ���룺 obj ��У���Object reference�� msg ����ʾ��Ϣ
 * ����� true �� false 
 */
function isChinese(obj, msg)
{
	var reg = /[^\u4E00-\u9FA5]/g;
	if (reg.test(obj.value))
	{
		alert((msg?msg:'����ֻ���������ģ� �����'));
		obj.select();		
		return false;
	}
	return true;
}

/**
 * ���ܣ� ���ļ�ȫ���ַ����麯��
 * ���룺 obj ��У���Object reference�� msg ����ʾ��Ϣ
 * ����� true �� false 
 */
function isNoChinese(obj, msg)
{
	var reg = /[\u4E00-\u9FA5]|[\uFE30-\uFFA0]/gi;
	if (reg.test(obj.value))
	{
		alert((msg?msg:'���������������ļ�ȫ���ַ��� �����'));
		obj.select();		
		return false;
	}
	return true;
}

/** �µ� ���֤�ж� 
 * 2005-1-24 
 * @param obj  ��������
 * @param msg  ��ʾ����ʾ��Ϣ
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
			alert(msg);	//alert("������ַ����������֤���ȣ�");
		return false;
	}
}

/** ���֤�ж�
* @param s ������ַ���
* return true or false
*/
function isCardNum(s){
	/*if (isEmpty(s)){ 
		alert("������������")
		return false;
	}*/
	if(!isCharsInBag (s, "0123456789")){
		alert("����һ����������Ƿ�Ϊ����?");
		return false;
	}
	if (s.length==15 || s.length==18){
		return true;
	}else{
		alert("��������ֳ���Ϊ15λ����18λ��");
		return false;
	}
}


/** ���������ж�
* @param s ������ַ���
* return true or false
*/
function isZipCode(s){
	if (s != ''){
		if(!isCharsInBag (s, "0123456789")){
			alert("����һ����������Ƿ�Ϊ���֣�");
			return false;
		}
		if (s.length==6){
			return true;
		}else{
			alert("������������볤��Ӧ��Ϊ6��");
			return false;
		}
	} else {
		return true;
	}
}


/** ��S������ַ�
* @param s �ַ���
* @param bag ���Ƚϵ��ַ���
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


/** �ַ��Ƿ���S��
* @param s �ַ���
* @param bag ���Ƚϵ��ַ���
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


/** ����������Ƿ�С�ڵ���ָ���ĳ���
* @param str �ַ���
* @param size ָ����С
* @param field �ֶ�����
* return true or false
*/
function isNotOverSize(str, size, field){
	if(str.length <= size)
		return true;
	else {
		alert(field + "�����볤�ȴ���ָ����ֵ!(" + size +")");
		return false;
	}

}


/** ����������Ƿ�����ض��ĳ���
* @param str �ַ���
* @param size ָ����С
* @param field �ֶ�����
* return true or false
*/
function isSize(str, size, field) {
	if (str.length == size)
		return true;
	else{
		alert(field + "���Ȳ�����Ҫ�󣡳���Ӧ��Ϊ��" + size);
		return false;
	}
}

/**
 * ���ܣ� ��ĸ�����ֽ��麯��
 * ���룺 obj ��У���Object reference�� msg ����ʾ��Ϣ
 * ����� true �� false 
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
		alert((msg?msg:'����ֻ������Ӣ����ĸ�����֣� �����'));
		obj.select();
		return false;
	}
}

/** notNull�����쿴�ı������Ƿ��ж�������,�����Ϊ�գ������ַ���
* @param str �ַ���
* @param field �ֶ�����
* return true or false
*/
function isNotNull(str, field) {
	str = str.trim();
    if (str.length == 0 ){
		alert(field + "����Ϊ�գ�");
		return false;
    }else
		return true;
}

/** ������汾 ���
* @param tempΪ����Ҫ�жϵİ汾.ĿǰֻΪIE,�Ժ󲹳� Netscape
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

//-----ȡֵ����----------------------------------------------------------------------------------------------------

//ȡ�����ڣ���date.js��

//---������Ҫ��ʾʱ��ĵط�(�����ʽ��****��**��**�� hh:mm:ss) 
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
 * ���ܣ� ��ѡ������ �õ���ѡ��ѡ�еĸ���
 * ���룺 checkbox ��ѡ��
 * ����� ѡ�и���
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
 * ���ܣ� ��ѡ������ �õ���ѡ��ѡ�е�����
 * ���룺 radiobutton ��ѡ��
 * ����� ѡ�е�����
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
 * ��ѡ������ �õ���ѡ��ѡ�е�ֵ
 * @param radiobutton ��ѡ��
 * @return ѡ�е�ֵ, û��ѡ�з��� false
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
 * ���ܣ� ��ȥ�ַ������ҵĿո�
 * ���룺 �������ַ���
 * ����� ������ַ���
 * ����������Ŀո�  wux
 */
function String.prototype.trim() {
	return this.replace(/(^(\s|\u3000)*)|((\s|\u3000)*$)/g, '');
}

/**
 * ���ܣ� ����Array���ܺ���������������Ԫ����Array�е�λ��
 * ���룺 keyWord:�����ҵ�Ԫ��
 * ����� ���Array�в���keyWord, ����-1, ���򷵻�����λ��
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
 * ���ܣ� ��ʽ�����ֵ���ȷλ��
 * ���룺 srcNumber Ԥ�������֣� decimal ����ȷ����λ��
 * ����� ���������� 
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
 * �õ�һ���ַ����ĸ���ֵ, ����ַ����е����ݲ�������, ���� 0
 * 2005-1-28 wangz
 * @param string	�ַ���
 * @return 			����ת����ĸ���ֵ
 */
function String.prototype.getFloatValue()
{
	return this.trim().length == 0 || isNaN(this) ? 0 : parseFloat(this, 10);
}




//�ж��ַ������ȣ�����һ�������򷵻ذ������ֽڴ���
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

//���ֱ任��ǧ�ַ���ʾ�Ľ�� ��ʽ�ǣ�1,000,000.00
//--2004-11-18 fixed Bug����3������ת��
function NumToCurrency(ftmvalue)
{
	var number_string;
	var insert_position = 0;	
	ftmvalue=CurrencyToNum(ftmvalue);//��ͳһ��ʽ�����ڽ���ת������ʽ����Ϊ1000.00
	var temp =ftmvalue.split('.');
	ftmvalue = temp[0];
	number_string=Math.abs(ftmvalue).toString();
	if(parseInt(ftmvalue)>=1000||parseInt(ftmvalue<=-1000))
	{		
		switch(number_string.length %3 )
		{
			case 1:insert_position=1;break;
			case 2:insert_position=2;break;
			case 0:insert_position=3;break;//��
		}
		while(insert_position<number_string.length){
			number_string = number_string.substr(0,insert_position)+','+number_string.substring(insert_position)
			insert_position+=4;
		}
		if(parseInt(ftmvalue)<0)
			number_string="-"+number_string;//����
	}
	//��ԭ��ʽ��
	if(ftmvalue<0)
		ftmvalue='-��'+number_string+'.'+temp[1];
	else
		ftmvalue='��'+number_string+'.'+temp[1];
	return ftmvalue;	
}
////������ͳһ��ʽ��Ϊ�ַ�:1000.00
//--2004-11-18 fixed bug ����滻','
function CurrencyToNum(ftmvalue)
{
	var temp;
	ftmvalue=ftmvalue.replace(' ','')
	if(ftmvalue.indexOf('��')>=0)
	{
		ftmvalue=ftmvalue.replace(/��/gi,'');
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

//-------�Ի���---------------------------------------------------------------------------------------------

//ȷ��ִ���¼�
function getConfirm()
{
		if( confirm('ִ�иò����󽫲��ܻ�ԭ���ݣ�ȷ��Ҫִ����?') ){
			return (true);
		}else{
		return (false);
                
		}
}
//�������ڣ���ַ�����ߣ�
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


//-------ҳ�����---------------------------------------------------------------------------------------------

/**
  ���ػ�����ʾ��Ӧ���ҳ��
  targetFrame�� dataFrame / listingFrame
  url�� ��Ӧ��ܼ���ҳ��·����Ϊ���򲻼���
  scale�� '0'   ������listingFrame��  
	  '60%' ��������ʾ���¿�ܣ�
	  '100%'������dataFrame��
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
 * ���ܣ� ҳ����ƺ����� ����dataFrame��subMenuFrame
 * ���룺 
 * ����� 
 */
function hideFrame()
{
	parent.document.body.rows = '0,0,*';
}

/**	
	��ʾ&���ر��id=sidЧ����ͬʱҲ�л�ͼƬid=imgbtn��
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

//---------08-19 add �����治ͬͼƬ�� scroll_right1.gif
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

//-------����-----------------------------------------------------------------------------------------------

/**
 * ���ܣ�����ѡ������ �����ѡ�񣨻�ȡ��ѡ�����к���
 * ���룺 checkboxAll �ܿ��Ƹ�ѡ�� checkbox ������ѡ��
 * �����
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

//��ʾ�������� table
function showtable(sid)
{
  subment=eval("submenu"+sid);
  if(subment.style.display=="none")
  {eval("submenu"+sid+".style.display=\"\";");}
  else 
  {eval("submenu"+sid+".style.display=\"none\";");}
}

/**
 * ���ܣ� ҳ����ƺ����� ��ʾdataFrame��subMenuFrame
 * ���룺 url Ϊ dataFrame������ʾ��ҳ��, �޲�����ʾ�հ�
 * ����� 
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

//��ʾ�б�ҳ��
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
 * ���ܣ� �鿴��ϸ
 * ���룺 baseaction: action
 * ���룺 id: ���鿴��ʵ��Id
 * ����� 
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
 * ���ܣ� �鿴��ϸ
 * ���룺 baseaction: action
 * ���룺 id: ���鿴��ʵ��Id
 * ����� 
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
 * ���� : disable form������Ԫ��, ���ڲ鿴��ϸʱ
 * ���� : 
 * ��� :
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
 * ���� : ȡhref�е�text
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
 * ����ҳ���е�*�ű���
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
 * as�����ж�Ӧ�ı�������
 */
function MM_hiddeInput(as)
{	

	for(var i = 0; i < as.length; i++){
		document.all(as[i]).readOnly = true;
		document.all(as[i]).className = "button3";
	}
}
/**
 * begin��end��Ӧ�ı�������
 */

function MM_hiddeFormElements(begin,end)
{	

for(var i = begin;i<(end-begin);i++){

	//document.Forms[0].elements[i]=true;
	document.Forms[0].elements[i].className = "button3";

	}
}

//�������󱣴�ȡ�������ݡ�����ʵ�ʵı�̬���أ���listing�������ֶζ�Ӧ����ҳ���ϵ���
function FellowInfo()
{
	this.id = arguments[0][0];
	for(var j = 1; j < Columns.length; j++){
		//alert("fellowInfo:"+Columns[j].colName+"="+MM_filterLink(arguments[0][j]));
		//eval('this.' + Columns[j].colName + ' = MM_filterLink(arguments[0][j])');
		//�������޸�
		var name = Columns[j].colName;
		var realName = name.split(".");
		eval('this.' + realName[realName.length-1] + ' = MM_filterLink(arguments[0][j])');
	}
}


/**
 * ����ѡ�еĶ���

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
			alert('��ѡ��!');
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
		alert('ѡ�����'+e);
	}
}
 */

/**
 * ���ص���ѡ�еĶ�������
 */
function returnSingleObject(ftm)
{
	try{
		if(getCheckedNumber(ftm) < 1){		
			alert('��ѡ��');
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
		//�����������ݻ��ҵ�Bug����idֵ������ȡ����Ӧѡ�е����� -- 2005-1-20 liuz
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
		alert('ѡ�����'+e);
	}
}


/**
 * ���ض��ѡ�еĶ������� 
 */
function returnObjects(ftm)
{
    var fellows = new Array();    
    if(!ftm.length){ //�������   
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
				//�����������ݻ��ҵ�Bug����idֵ������ȡ����Ӧѡ�е����� -- 2005-1-20 liuz 
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

/** �� returnObjects ��ͬ
 *  �����б���ѡ�������е��������ݣ���ֵΪ��������
 *  ֧�ֵ�ѡ / ��ѡ
 */
function getSelectListingData(ftm){
    var fellows = new Array();    
    if(!ftm.length){ //�������   
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
				//�����������ݻ��ҵ�Bug����idֵ������ȡ����Ӧѡ�е����� -- 2005-1-20 liuz 
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
	return fellows //�������飬��ѡ��ȡֵ fellows[0].xm����ѡ��fellow[indexNum].xm
}



/**
 * չ�������е��������ض���
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
 * ��һ���´���
 * @param url  		Ҫ�򿪵ĵ�ַ
 * @param width		���, �������, Ĭ��Ϊ700
 * @param height 	�߶�, �������, Ĭ��Ϊ460
 */
function openWindow(url, width, height) 
{
	if(!url) 
	{
		alert('��ָ��Ҫ�򿪵ĵ�ַ!');
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
 * ��һ����Frame����, Frame���йرհ�ť
 * @param url  		Ҫ�򿪵ĵ�ַ
 * @param width		���, �������, Ĭ��Ϊ700
 * @param height 	�߶�, �������, Ĭ��Ϊ460
 */
function openWindowInFrame(url, width, height)
{
	if(!url) 
	{
		alert('��ָ��Ҫ�򿪵ĵ�ַ!');
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
 * ��һ���¶Ի���
 * @param url  		Ҫ�򿪵ĵ�ַ
 * @param width		���, �������, Ĭ��Ϊ700
 * @param height 	�߶�, �������, Ĭ��Ϊ460
 * @return 			�Ի��򷵻�ֵ 
 */
function openDialog(url, width, height)
{
	if(!url) {	
		alert('��ָ��Ҫ�򿪵ĵ�ַ!');
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

//�õ�����·��
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

/** �������߰�������getOnlineHelp
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

/*--ʹ��������Ƶ�����--*/
function getElementRight()  
{  
  var e = event.srcElement;  
  var r =e.createTextRange();  
  r.moveStart('character',e.value.length);  
  r.collapse(true);  
  r.select();  
}  

//ʵ�� �϶������ Ч��
// �ڴ��봦���� style="cursor:e-resize" onmousedown="setCapture()" onmouseup="releaseCapture();" onmousemove="general_dragWidth(tdId_1)"
//@param  tabid  ���ID
function general_dragWidth(tabid,num){
	if(event.button ==1){
		var magin = (num + '' == 'undefined')?0:num;//document.body.style.marginLeft + document.body.style.margetRight;
		var xx = (event.x-magin < 0)?0:event.x-magin;
		tabid.style.width = xx;
	}
}

//style="cursor:n-resize" onmousedown="setCapture()" onmouseup="releaseCapture();" onmousemove="general_dragHeight(tdId_1)"
//�϶����߶�
function general_dragHeight(tabid,num){
	if(event.button ==1){
		var magin = (num + '' == 'undefined')?0:num;
		var yy = (event.y-magin < 0)?0:event.y-magin;
		tabid.style.height = yy
	}
}

