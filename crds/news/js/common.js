 
/*------------------------------------------------
 * ͨ�÷�������
 * 2005-08-24  Build ������ϣ����˾ǰ�˿ؼ�ʹ��˵���� -Liuz
 *        ��ԭ����general.js �Ľ��ó����Ժ�ֻ�Ը��ļ�������ά��
 -------------------------------------------------*/


/*********************************
 * ͨ�� ��������
 **********************************/

 /**
 * �������ҳ�棺 ���ƿ����ʽ����ʾdataFrame��listingFrame��url�ύ��ָ������
 * params[0] url  Ϊ�ύ��ʾ��ҳ��, �޲�����ʾ�հ�
 * params[1] targ �������֣���ѡ dataFrame(Ĭ��)/listingFrame
 * params[2] flag ���ڳߴ磬60%(Ĭ��)/100%(����dataFrame)/0(����listingFrame)
 * ����� 
 */
function showFullFrame(url,targ,flag)
{
	targ = targ ? targ : "dataFrame";
	flag = flag ? flag : "60%";
	parent.document.body.rows = '*,8,'+flag;
	if(arguments.length > 0)
	{
		document.forms[0].method="post"
		document.forms[0].target=targ;
		document.forms[0].action=arguments[0];
		document.forms[0].submit();
	}
	else
	{
		document.forms[0].method="post"
		document.forms[0].target=targ;
		document.forms[0].action='about:blank';
		document.forms[0].submit();
	}
}


/*	
 * ������� ��ʾ&���ر��id=sidЧ����ͬʱҲ�л�ͼƬid=imgbtn��
 * @param sid  ��������ID
 * @param img  ����ͼƬID
 * @param flag ���ڴ�С��Ĭ��60%�����Ϊ0�������¿�ܣ����Ϊ100%�������Ͽ��
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

/*
 * ʵ�� �϶������ Ч��
 * ʹ�ã�style="cursor:e-resize" onmousedown="setCapture()" onmouseup="releaseCapture();" 
              onmousemove="general_dragWidth(tdId_1)"
 *@param  tabid  ���ID
 */
function general_dragWidth(tabid,num){
	if(event.button ==1){
		var magin = (num + '' == 'undefined')?0:num;
		var xx = (event.x-magin < 0)?0:event.x-magin;
		tabid.style.width = xx;
	}
}
/*
 * ʵ�� �϶����߶� Ч��
 * ʹ�ã�style="cursor:n-resize" onmousedown="setCapture()" onmouseup="releaseCapture();" 
		onmousemove="general_dragHeight(tdId_1)"
 * �϶����߶�
 */
function general_dragHeight(tabid,num){
	if(event.button ==1){
		var magin = (num + '' == 'undefined')?0:num;
		var yy = (event.y-magin < 0)?0:event.y-magin;
		tabid.style.height = yy
	}
}



/* ͨ�� ȷ��ִ���¼�
 * str ��ʾ��Ϣ ��Ĭ��Ϊ��ִ�иò����󽫲��ܻ�ԭ�ò�����ȷ��Ҫִ���𣿡���
 * return  ȷ��true/false
 */
function getConfirm(str)
{
	str = str ? str : "ִ�иò����󽫲��ܻ�ԭ�ò�����ȷ��Ҫִ����?"
	if( confirm(str) ){
		return (true);
	}else{
		return (false);			
	}
}
/*
 * �������ڣ� ��������
 * params[0] ��ַ 
 * params[1] ������ Ĭ��Ϊ"_blank"
 * params[2] �� Ĭ��Ϊ��Ļ��1/2
 * params[3] �� Ĭ��Ϊ��Ļ��1/2
 * return ������
 */
function getWindow(url,winName,wwidth,wheight)
{
	winName = winName ? winName : "_blank";
	wwidth = wwidth ? wwidth : window.screen.width /2
	wheight= wheight?wheight : window.screen.height/2
	window.open(url, tagname, 'height='+wheight+', width='+wwidth+', scrollbars=auto, center=1, resizable=1');
	return tagname;
}

/*
 * �������ڣ� ����ģ̬����
 * params[0] ��ַ 
 * params[1] �� Ĭ��Ϊ��Ļ��1/2
 * params[2] �� Ĭ��Ϊ��Ļ��1/2
 * return ѡ�񴰿ڷ���ֵ
 */
function getDialog(url,wwidth,wheight)
{
	wwidth = wwidth ? wwidth : window.screen.width /2
	wheight= wheight?wheight : window.screen.height/2
	var returnValue = showModalDialog(url,window,'dialogWidth:'+wwidth+'px;dialogHeight:'+wheight+'px;status:no;scroll:Auto;help:no;');	
	return returnValue
}

/**
 * �������ڣ�����һ����Frame����, Frame���йرհ�ť
 * @param url  		Ҫ�򿪵ĵ�ַ
 * @param winName   ������
 * @param wwidth	�� Ĭ��Ϊ��Ļ��1/2
 * @param wheight 	�� Ĭ��Ϊ��Ļ��1/2
 */
function openWindowInFrame(url,winName,wwidth, wheight)
{
	winName = winName ? winName : "_blank";
	wwidth = wwidth ? wwidth : window.screen.width /2
	wheight= wheight?wheight : window.screen.height/2	
	var frameUrl = "/" + getContextPath() + '/application/pages/select_frame.jsp?url=' + escape(url);	
	window.open(frameUrl,winName, 'height='+ wheight +', width='+ wwidth +', menubar=no, status=no, toolbar=no,scrollbars=auto, location=no,center=1, resizable=1');	
	return winName;
}

/**
 * �������ڣ�����һ��ģ̬�����д򿪹���Frameҳ��
 * @param url  		Ҫ�򿪵ĵ�ַ
 * @param wwidth		���, �������, Ĭ��Ϊ700
 * @param wheight 	�߶�, �������, Ĭ��Ϊ460
 * @return 			�Ի��򷵻�ֵ 
 */
function openDialogInFrame(url, wwidth, wheight)
{
	if(!url)
		return;
	wwidth = wwidth ? wwidth : window.screen.width /2
	wheight= wheight?wheight : window.screen.height/2	
	var frameUrl = "/" + getContextPath() + '/application/pages/select_frame.jsp';
	var str = showModalDialog(frameUrl, new Array(window,url), 'dialogWidth:' + wwidth +'px;dialogHeight:' + wheight + 'px;status:no;scroll:auto;help:no;')
	return str
}

/*
 * �������߰�������
 * @param url Ĭ�ϱ�ҳ��location����ֵΪָ����url��ȡ��Ӧ�ĵ������ڵ�helpUrl
 */
function getOnlineHelp(url){
	if(!url){
		if(typeof parent.mainFrame == 'object' && typeof parent.mainFrame.listingFrame == 'object')
			url = parent.mainFrame.listingFrame.location.href
		else if(typeof parent.mainFrame == 'object')
			url = parent.mainFrame.location.href
		else 
			url = location.href
	}
	openWindowInFrame('/'+getContextPath()+'/application/pages/help_default.htm?urltxt='+url,'',800,600)
	//return false; 
}


/*  
 * �����б���ȡѡ�е��б�����
 * �����б���ѡ�������е��������ݣ���ֵΪ��������,֧�ֵ�ѡ / ��ѡ
 * @param ftm  �б����е�checkbox����document.forms[0].id
 * return ��������:��ѡ��ȡֵ fellows[0].xm����ѡ��fellow[indexNum].xm
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
	return fellows 
}

/*
 * �����б��������󱣴�ȡ�������ݡ�����ʵ�ʵı�̬���أ���listing�������ֶζ�Ӧ����ҳ���ϵ���
 * param  �б��htmldw.rows��ά����
 * return ����
 */
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


/*********************************
 * ͨ�� ���� ����
 **********************************/

/*  ������汾 ���
 * @param tempΪ����Ҫ�жϵİ汾.ĿǰֻΪIE,(����Netscape�汾�ж�)
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

/* 
 * ��ʾʱ��ĵط� ��ʽ��yyyy-mm-dd
 */ 
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

function String.prototype.trim()
{
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

/*
 * ���ֱ任��ǧ�ַ���ʾ�Ľ�� 
 * param ftmvalue �ַ���
 * return ��ʽ�������� ��1,000,000.00
 */
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
/*
 * ����ͳһΪ�ַ���
 * param ftmvalue �����ַ� 
 * return ��ʽ��Ϊ�ַ�:1000.00
 */
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
