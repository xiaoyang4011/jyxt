 
/*------------------------------------------------
 * 通用方法汇总
 * 2005-08-24  Build 《北京希洱公司前端控件使用说明》 -Liuz
 *        由原来的general.js 改进得出，以后只对该文件做公用维护
 -------------------------------------------------*/


/*********************************
 * 通用 操作方法
 **********************************/

 /**
 * 操作框架页面： 控制框架形式，显示dataFrame或listingFrame，url提交给指定窗口
 * params[0] url  为提交显示的页面, 无参数显示空白
 * params[1] targ 窗口名字，可选 dataFrame(默认)/listingFrame
 * params[2] flag 窗口尺寸，60%(默认)/100%(隐藏dataFrame)/0(隐藏listingFrame)
 * 输出： 
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
 * 操作表格： 显示&隐藏表格id=sid效果，同时也切换图片id=imgbtn：
 * @param sid  操作对象ID
 * @param img  操作图片ID
 * @param flag 窗口大小，默认60%，如果为0则隐藏下框架，如果为100%则隐藏上框架
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

/*
 * 实现 拖动表格宽度 效果
 * 使用：style="cursor:e-resize" onmousedown="setCapture()" onmouseup="releaseCapture();" 
              onmousemove="general_dragWidth(tdId_1)"
 *@param  tabid  表格ID
 */
function general_dragWidth(tabid,num){
	if(event.button ==1){
		var magin = (num + '' == 'undefined')?0:num;
		var xx = (event.x-magin < 0)?0:event.x-magin;
		tabid.style.width = xx;
	}
}
/*
 * 实现 拖动表格高度 效果
 * 使用：style="cursor:n-resize" onmousedown="setCapture()" onmouseup="releaseCapture();" 
		onmousemove="general_dragHeight(tdId_1)"
 * 拖动表格高度
 */
function general_dragHeight(tabid,num){
	if(event.button ==1){
		var magin = (num + '' == 'undefined')?0:num;
		var yy = (event.y-magin < 0)?0:event.y-magin;
		tabid.style.height = yy
	}
}



/* 通用 确认执行事件
 * str 提示信息 （默认为“执行该操作后将不能还原该操作，确定要执行吗？”）
 * return  确认true/false
 */
function getConfirm(str)
{
	str = str ? str : "执行该操作后将不能还原该操作，确定要执行吗?"
	if( confirm(str) ){
		return (true);
	}else{
		return (false);			
	}
}
/*
 * 操作窗口： 弹出窗口
 * params[0] 地址 
 * params[1] 窗口名 默认为"_blank"
 * params[2] 宽 默认为屏幕宽1/2
 * params[3] 高 默认为屏幕高1/2
 * return 窗口名
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
 * 操作窗口： 弹出模态窗口
 * params[0] 地址 
 * params[1] 宽 默认为屏幕宽1/2
 * params[2] 高 默认为屏幕高1/2
 * return 选择窗口返回值
 */
function getDialog(url,wwidth,wheight)
{
	wwidth = wwidth ? wwidth : window.screen.width /2
	wheight= wheight?wheight : window.screen.height/2
	var returnValue = showModalDialog(url,window,'dialogWidth:'+wwidth+'px;dialogHeight:'+wheight+'px;status:no;scroll:Auto;help:no;');	
	return returnValue
}

/**
 * 操作窗口：：打开一个新Frame窗口, Frame中有关闭按钮
 * @param url  		要打开的地址
 * @param winName   窗口名
 * @param wwidth	宽 默认为屏幕宽1/2
 * @param wheight 	高 默认为屏幕高1/2
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
 * 操作窗口：：在一个模态窗口中打开公用Frame页面
 * @param url  		要打开的地址
 * @param wwidth		宽度, 如果不填, 默认为700
 * @param wheight 	高度, 如果不填, 默认为460
 * @return 			对话框返回值 
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
 * 弹出在线帮助窗口
 * @param url 默认本页面location，赋值为指定的url获取对应的弹出窗口的helpUrl
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
 * 操作列表：获取选中的列表数据
 * 根据列表中选中所在行的所有数据，赋值为对象数组,支持单选 / 多选
 * @param ftm  列表首列的checkbox对象：document.forms[0].id
 * return 对象数组:单选的取值 fellows[0].xm。多选是fellow[indexNum].xm
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
	return fellows 
}

/*
 * 操作列表：建立对象保存取到的数据。根据实际的表动态加载，与listing中数据字段对应地在页面上调用
 * param  列表的htmldw.rows二维数组
 * return 对象
 */
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


/*********************************
 * 通用 处理 方法
 **********************************/

/*  浏览器版本 检测
 * @param temp为你需要判断的版本.目前只为IE,(不对Netscape版本判断)
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
 * 显示时间的地方 格式：yyyy-mm-dd
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

function String.prototype.trim()
{
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

/*
 * 数字变换成千分符表示的金额 
 * param ftmvalue 字符串
 * return 格式化后数据 ￥1,000,000.00
 */
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
/*
 * 进行统一为字符串
 * param ftmvalue 任意字符 
 * return 格式化为字符:1000.00
 */
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
