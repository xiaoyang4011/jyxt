/*==================================================================
 * 时间控件
 * 2005-03-16 add 可选时间,支持日期+时间 和日期/时间 分开放置
 * 2005-1-28  add setShortFormatDate() /setLongFormatDate() 
 * 2004-11-08 整理代码，变量统一命名，加入帮助,年份范围前后60年
 * 2004-08-03 改掉版权信息，补充document.onclick 关闭其他层
 *     调用方式： <input type="text" name="gradute" onblur="checkDay(this)"><input name="button" type="button" class="button" onclick="setday(this,gradute)" value="..."/>
 *
 ***/

//== 参数设定部分 ===

var DatePopWin            = ''; 
var global_date_isMove    = true;	   //设置日历是否可以拖动 bMoveable
var global_date_Info      = ""	       //版本信息
var global_date_input;                 //outObject
var global_date_button;		           //点击的按钮 outButton
var lastObjectValue;
var global_outDate        = "";		   //存放对象的日期 outDate
var global_date_linecolor = "#b9cbf7"; //global_date_linecolor
var global_date_bgcolor   = "#eaeffd"; //global_date_bgcolor
var global_date_defcolor  = "#ffffff"; //global_date_defcolor
var global_date_timeStr   = "";        //临时时间参数
var global_date_getTime   = '' ;     //存放时间obj
var odatelayer = null;				 //存放日历对象 
var global_explorer_ie6_date   = false;
var globalFormat;

document.writeln('<iframe id=meizzDateLayer name="meizzDateLayer" Author=wayx frameborder=0 style="position: absolute; width: 145px; height: 225px; z-index: 9998; display: none;" scrolling="no"></iframe>');

//由于xp中IE新版本导致 select选择域扩大，导致在日期控件的外部选择失效,与createPopup冲突，因此只使用iframe效果
//if(typeof checkExplorer != 'undefined' && checkExplorer(5.5)){
//	global_explorer_ie6_date   = true;
//	DatePopWin = window.createPopup(); //
//	odatelayer=DatePopWin.document;	
//}else{
	odatelayer=window.frames.meizzDateLayer.document;
//}

//== the CSS of Date Page
try{  
	if(typeof document.styleSheets != 'undefined')
	{
		global_date_linecolor = document.styleSheets[0].rules[26].style.backgroundColor;//
		global_date_bgcolor   = document.styleSheets[0].rules[28].style.backgroundColor;//	
	}
}catch(e)
{}



//== WEB 页面显示部分
function displayDate(){
	var strFrame='';		//存放日历层的HTML代码
	strFrame='<style>';
	strFrame+='INPUT.button{BORDER-RIGHT: '+global_date_defcolor+' 1px solid;BORDER-TOP: '+global_date_defcolor+' 1px solid;BORDER-LEFT: '+global_date_defcolor+' 1px solid;';
	strFrame+='BORDER-BOTTOM: '+global_date_defcolor+' 1px solid;BACKGROUND-COLOR: '+global_date_linecolor+';font-family:宋体;}';//#fff8ec
	strFrame+='TD{FONT-SIZE: 9pt;font-family:宋体;}a{FONT-SIZE: 9pt;color:#000000}';
	strFrame+='select{FONT-SIZE: 9pt;font-family:Arial;}';
	strFrame+='DayCss {font-size:12px;color:#000000;FONT-WEIGHT: bold}';
	strFrame+='</style>';
	strFrame+='\n<scr' + 'ipt>\n';
	strFrame+='var datelayerx,datelayery;	/*存放日历控件的鼠标位置*/\n';
	strFrame+='var bDrag;	/*标记是否开始拖动*/\n';
	
	strFrame+='\n</scr' + 'ipt>\n';
	strFrame+='<div style="z-index:9999;position: absolute; left:0; top:0;overflow:hidden;" onselectstart="return false" ><span id=tmpSelectYearLayer Author=wayx style="z-index: 9999;position: absolute;top: 3; left: 19;display: none"></span>';
	strFrame+='<span id=tmpSelectMonthLayer Author=wayx style="z-index: 9999;position: absolute;top: 3; left: 78;display: none"></span>';
	strFrame+='<span id=tmpSelectHourLayer Author=wayx style="z-index: 9999;position: absolute;top: 28; left: 35;display: none"></span>';
	strFrame+='<span id=tmpSelectMiniLayer Author=wayx style="z-index: 9999;position: absolute;top: 28; left: 70;display: none"></span>';
	strFrame+='<span id=tmpSelectSecoLayer Author=wayx style="z-index: 9999;position: absolute;top: 28; left: 105;display: none"></span>';
	strFrame+='<table border=1 cellspacing=0 cellpadding=0 width=100% height=100% bordercolor='+global_date_linecolor+' bgcolor='+global_date_linecolor+' >';//#ff9900
	strFrame+='  <tr ><td width=100% height=23  bgcolor=#FFFFFF><table border=0 cellspacing=1 cellpadding=0 height=23>';
	strFrame+='      <tr align=center ><td width=16 align=center bgcolor='+global_date_linecolor+' style="font-size:12px;cursor: hand;color: #ffffff" ';
	strFrame+='        onclick="parent.meizzPrevM()" title="向前翻 1 月" Author=meizz><b Author=meizz>&lt;</b>';
	strFrame+='        </td><td width=60 align=center style="font-size:12px;cursor:default" Author=meizz id="tabId_inneryear"';
	strFrame+='  onmouseover="style.backgroundColor=\''+global_date_bgcolor+'\'" onmouseout="style.backgroundColor=\''+global_date_linecolor+'\'" ';
	strFrame+='  onclick="parent.tmpSelectYearInnerHTML(this.innerText.substring(0,4))" ';
	strFrame+='  title="点击这里选择年份"><span Author=meizz id=meizzYearHead></span></td>';
	strFrame+='<td width=48 align=center style="font-size:12px;cursor:default" Author=meizz onmouseover="style.backgroundColor=\''+global_date_bgcolor+'\'" ';//#FFD700
	strFrame+=' onmouseout="style.backgroundColor=\''+global_date_linecolor+'\'" id="tabId_innnermonth"';
	strFrame+=' onclick="parent.tmpSelectMonthInnerHTML(this.innerText.length==3?this.innerText.substring(0,1):this.innerText.substring(0,2))"';
	strFrame+=' title="点击这里选择月份"><span id=meizzMonthHead Author=meizz></span></td>';
	strFrame+='        <td width=16 bgcolor='+global_date_linecolor+' align=center style="font-size:12px;cursor: hand;color: #ffffff" ';
	strFrame+='         onclick="parent.meizzNextM()" title="向后翻 1 月" Author=meizz><b Author=meizz>&gt;</b></td></tr>';
	strFrame+='    </table></td></tr>';
	
	var temp = getCurrentTime(global_date_timeStr);
	strFrame+='      <tr><td title="点击这里选择时间">';//
	strFrame+='			<table height="22" border=0 cellspacing=1 cellpadding=0 width=100% id="tab_stayTime" bgcolor='+global_date_bgcolor+'><tr bgcolor='+global_date_linecolor+'><td width="25%" align="center"> 时间</td>';
	strFrame+='			<td id="tdId_hour" width="24%" align="right" ';
	strFrame+='  onmouseover="style.backgroundColor=\''+global_date_defcolor+'\'" onmouseout="style.backgroundColor=\'\'" ';
	strFrame+='  onclick="parent.tmpSelectHourHTML(this.innerText.substring(0,2))" title="点击这里选择小时">'+temp[0]+' ：</td>';
	strFrame+='			<td id="tdId_mini" width="28%" align="center" ';
	strFrame+='  onmouseover="style.backgroundColor=\''+global_date_defcolor+'\'" onmouseout="style.backgroundColor=\'\'" ';
	strFrame+='  onclick="parent.tmpSelectMiniHTML(this.innerText.substring(0,2))" title="点击这里选择分钟">'+temp[1]+' ：</td>';
	strFrame+='			<td id="tdId_seco" width="23%" ';
	strFrame+='  onmouseover="style.backgroundColor=\''+global_date_defcolor+'\'" onmouseout="style.backgroundColor=\'\'" ';
	strFrame+='  onclick="parent.tmpSelectSecoHTML(this.innerText.substring(0,2))" title="点击这里选择秒钟">'+temp[2]+'</td>';
	strFrame+='		</tr></td></table></td></tr>';

	strFrame+='  <tr ><td width=100% height=18 >';
	strFrame+='<table border=1 cellspacing=0 cellpadding=0 bgcolor='+global_date_linecolor+' ' //+ (global_date_isMove? 'onmousedown="DragStart()" onmouseup="DragEnd()"':'');
	strFrame+=' BORDERCOLORLIGHT='+global_date_linecolor+' BORDERCOLORDARK=#FFFFFF width=140 height=20  style="cursor:' + (global_date_isMove ? 'move':'default') + '">';
	strFrame+='<tr  align=center valign=bottom class="DayCss"><td >日</td>';
	strFrame+='<td >一</td> <td >二</td>';
	strFrame+='<td >三</td> <td >四</td>';
	strFrame+='<td >五</td> <td >六</td></tr>';
	strFrame+='</table></td></tr><!-- Author:F.R.Huang(meizz) http://www.meizz.com/ mail: meizz@hzcnc.com 2002-10-8 -->';
	strFrame+='  <tr ><td width=142 height=120 >';
	strFrame+='    <table border=1 cellspacing=0 cellpadding=0 BORDERCOLORLIGHT='+global_date_linecolor+' BORDERCOLORDARK='+global_date_defcolor+' bgcolor='+global_date_bgcolor+' width=140 height=120 >';
	var n=0; for (j=0;j<5;j++){ strFrame+= ' <tr align=center >'; for (i=0;i<7;i++){
	strFrame+='<td width=20 height=20 id=meizzDay'+n+' style="font-size:12px" Author=meizz onclick=parent.meizzDayClick(this.innerText,0)></td>';n++;}
	strFrame+='</tr>';}
	strFrame+='      <tr align=center >';
	for (i=35;i<39;i++)strFrame+='<td width=20 height=20 id=meizzDay'+i+' style="font-size:12px" Author=wayx onclick="parent.meizzDayClick(this.innerText,0)"></td>';
	strFrame+='        <td colspan=3  Author=meizz><a href="#" onclick="parent.getWinHelp();return false" title=\'点击这里查看时间控件帮助\'>帮助</a>&nbsp;<a href="#" onclick="parent.global_date_input.value=\'\'" ';//parent.closeLayer()
	strFrame+='          title="点击这里关闭控件">重置</a></td></tr>';
	strFrame+='        </table></td></tr>';
		
	strFrame+='        <tr><td>';
	strFrame+='      <table border=0 cellspacing=1 cellpadding=0 width=100%  bgcolor='+global_date_bgcolor+'>';
	strFrame+='         <tr ><td Author=meizz align=left><input Author=meizz type=button class=button value="<<" title="向前翻 1 年" onclick="parent.meizzPrevY()" ';
	strFrame+='             onfocus="this.blur()" style="font-size: 12px; height: 20px"><input Author=meizz class=button title="向前翻 1 月" type=button ';
	strFrame+='             value="< " onclick="parent.meizzPrevM()" onfocus="this.blur()" style="font-size: 12px; height: 20px"></td><td ';
	strFrame+='             Author=meizz align=center><input Author=meizz type=button class=button value="今天" onclick="parent.meizzToday()" ';
	strFrame+='             onfocus="this.blur()" title="当前日期" style="font-size: 12px; height: 20px; cursor:hand"></td><td ';
	strFrame+='             Author=meizz align=right><input Author=meizz type=button class=button value=" >" onclick="parent.meizzNextM()" ';
	strFrame+='             onfocus="this.blur()" title="向后翻 1 月" class=button style="font-size: 12px; height: 20px"><input ';
	strFrame+='             Author=meizz type=button class=button value=">>" title="向后翻 1 年" onclick="parent.meizzNextY()"';
	strFrame+='             onfocus="this.blur()" style="font-size: 12px; height: 20px"></td>';
	strFrame+='		</tr></table></td></tr></table></div>';

	if(global_explorer_ie6_date){
		DatePopWin.document.open();
		DatePopWin.document.writeln(strFrame); 
		DatePopWin.document.close();
		DatePopWin.document.body.style.overflow = 'hidden'
		DatePopWin.document.body.style.border   = '0';
	}else{
		window.frames.meizzDateLayer.document.open();	
		window.frames.meizzDateLayer.document.writeln(strFrame);
		window.frames.meizzDateLayer.document.close();		//解决ie进度条不结束的问题	
	}
}

function getCurrentTime(str){
	var temp = new Array();
	if(str != '')
		temp = setFormatTimeStr(str).split(":");
	else{
		temp[0]= new Date().getHours()<10?'0'+new Date().getHours():new Date().getHours();
		temp[1]= new Date().getMinutes()<10?'0'+new Date().getMinutes():new Date().getMinutes();
		temp[2]= new Date().getSeconds()<10?'0'+new Date().getSeconds():new Date().getSeconds();
		global_date_timeStr = temp[0]+"："+temp[1]+"："+temp[2];
	}
	return temp
}

//== 格式转换 ====

this.DateFormat = "<yyyy>-<mm>-<dd>";
this.MonthName = new Array();
  this.MonthName[0] = "一月";
  this.MonthName[1] = "二月";
  this.MonthName[2] = "三月";
  this.MonthName[3] = "四月";
  this.MonthName[4] = "五月";
  this.MonthName[5] = "六月";
  this.MonthName[6] = "七月";
  this.MonthName[7] = "八月";
  this.MonthName[8] = "九月";
  this.MonthName[9] = "十月";
  this.MonthName[10] = "十一月";
  this.MonthName[11] = "十二月";

var MonHead       = new Array(12);               //定义阳历中每个月的最大天数
    MonHead[0] = 31; MonHead[1] = 28; MonHead[2] = 31; MonHead[3] = 30; MonHead[4]  = 31; MonHead[5]  = 30;
    MonHead[6] = 31; MonHead[7] = 31; MonHead[8] = 30; MonHead[9] = 31; MonHead[10] = 30; MonHead[11] = 31;

var meizzTheYear  = new Date().getFullYear();    //定义年的变量的初始值
var meizzTheMonth = new Date().getMonth()+1;     //定义月的变量的初始值
var meizzWDay     = new Array(39);               //定义写日期的数组

this.GetFormatYear = function(theYear){    //format theYear to 4 digit
    var tmpYear = theYear;
    if (tmpYear < 100){
      tmpYear += 1900;
      if (tmpYear < 1970){
     tmpYear += 100;
      }
    }
	 if (tmpYear < this.MinYear){
	   tmpYear = this.MinYear;
	 }
	 if (tmpYear > this.MaxYear){
	   tmpYear = this.MaxYear;
	 }
		return(tmpYear);
}
this.GetMonthDays = function(theYear, theMonth){ //get theYear and theMonth days number
	var theDays = new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
	var theMonthDay = 0, tmpYear = this.GetFormatYear(theYear);
	theMonthDay = theDays[theMonth];
	if (theMonth == 1){ //theMonth is February
		if(((tmpYear % 4 == 0) && (tmpYear % 100 != 0)) || (tmpYear % 400 == 0)){
			theMonthDay++;
		}
	}
	return(theMonthDay);
}
//------------------对特定时间格式的转换------------------------------------------------
//<yy> or <yyyy> is year, <m> or <mm> is digital format month, <MMM> or <MMMMMM> is character format month, <d> or <dd> is day, other char unchanged
//this function setting year, month and day sequence
	  //example:
	  //  <yyyy>-<mm>-<dd> : 2002-04-01
	  //  <yy>.<m>.<d> : 02.4.1
	  //  <yyyy> Year <MMMMMM> Month <d> Day : 2002 Year April Month 1 Day
	  //  <m>/<d>/<yy> : 4/1/02
	  //  <MMM> <dd>, <yyyy> : Apr 01, 2002
	  //  <MMMMMM> <d>, <yyyy> : April 1, 2002
	  //  <dd> <MMM> <yyyy> : 01 Apr 2002
	  //  <dd>/<mm>/<yyyy> : 01/04/2002
	  //**  <yyyy><mm><dd>:20040322
	  //**  <yy><mm><dd>:040322
  
this.SetDateFormat = function(theYear, theMonth, theDay){//format a date to this.DateFormat           
	 var theDate = this.DateFormat;
	 var tmpYear = this.GetFormatYear(theYear);
	 var tmpMonth = theMonth;
	 if (tmpMonth < 0){
	   tmpMonth = 0;
	 }
	 if (tmpMonth > 11){
	   tmpMonth = 11;
	 }
	 var tmpDay = theDay;
	 if (tmpDay < 1){
	   tmpDay = 1;
	 }else{
		  tmpDay = this.GetMonthDays(tmpYear, tmpMonth);
		  if (theDay < tmpDay){
		  tmpDay = theDay;
	   }
	 }

    theDate = theDate.replace(/<yyyy>/g, tmpYear.toString());
    theDate = theDate.replace(/<yy>/g, tmpYear.toString().substr(2,2));
    theDate = theDate.replace(/<MMMMMM>/g, this.MonthName[tmpMonth]);
    theDate = theDate.replace(/<MMM>/g, this.MonthName[tmpMonth].substr(0,3));
    if (theMonth < 9){
		theDate = theDate.replace(/<mm>/g, "0" + (tmpMonth + 1).toString());
    }else{
		theDate = theDate.replace(/<mm>/g, (tmpMonth + 1).toString());
    }
	theDate = theDate.replace(/<m>/g, (tmpMonth + 1).toString());
    if (theDay < 10){
		theDate = theDate.replace(/<dd>/g, "0" + tmpDay.toString());
    }else{
		theDate = theDate.replace(/<dd>/g, tmpDay.toString());
    }
    theDate = theDate.replace(/<d>/g, tmpDay.toString());
    return(theDate);
  }

  //--------------------------------------------------------------
  this.GetTextDate = function(theString){ //convert a string to a date, if the string is not a date, return a empty string
    var i = 0, tmpChar = "", find_tag = "";
    var start_at = 0, end_at = 0, year_at = 0, month_at = 0, day_at = 0;
    var tmp_at = 0, one_at = 0, two_at = 0, one_days = 0, two_days = 0;
    var aryDate = new Array();
    var tmpYear = -1, tmpMonth = -1, tmpDay = -1;
    var tmpDate = theString.toLowerCase();
 var defDate = "";
 end_at = tmpDate.length;
 
 //----------------------------格式：080904 , 20040809
 var theyear,themon,thday;
 if(end_at==8)
  { theyear=tmpDate.substr(0,4);
    themon=tmpDate.substr(4,2);
    theday=tmpDate.substr(6,2);
    tmpDate=theyear+"-"+themon+"-"+theday;
  }
  /*else if(end_at==7)//---2004-08-06 yingxin业务要求 YYYY-MM
  {
	theyear=tmpDate.substr(0,4);
    themon=tmpDate.substr(5,2);
    theday="00";
    tmpDate=theyear+"-"+themon+"-"+theday;
  }
  else if(end_at==6)//-----YYYY-M
  { theyear=tmpDate.substr(0,4);
    themon=tmpDate.substr(5,1);
    theday="00";
    tmpDate=theyear+"-"+themon+"-"+theday;
  }*/  
  else if(end_at==6)
  { theyear=tmpDate.substr(0,2);
    themon=tmpDate.substr(2,2);
    theday=tmpDate.substr(4,2);
    tmpDate=theyear+"-"+themon+"-"+theday;
  } 
 //-------------------------------
 
 for (i=1;i<end_at;i++){
   if (tmpDate.charAt(i)=="0"){
     tmpChar = tmpDate.charAt(i-1);
     if (tmpChar<"0" || tmpChar>"9"){
    tmpDate = tmpDate.substr(0,i-1) + "-" + tmpDate.substr(i+1);
  }
   }
 }
    for (i=0;i<9;i++){
      tmpDate = tmpDate.replace(this.MonthName[i].toLowerCase().substr(0,3), "-00" + (i+1).toString() + "-");
    }
    for (i=9;i<12;i++){
      tmpDate = tmpDate.replace(this.MonthName[i].toLowerCase().substr(0,3), "-0" + (i+1).toString() + "-");
    }
    tmpDate = tmpDate.replace(/jan/g, "-001-");
    tmpDate = tmpDate.replace(/feb/g, "-002-");
    tmpDate = tmpDate.replace(/mar/g, "-003-");
    tmpDate = tmpDate.replace(/apr/g, "-004-");
    tmpDate = tmpDate.replace(/may/g, "-005-");
    tmpDate = tmpDate.replace(/jun/g, "-006-");
    tmpDate = tmpDate.replace(/jul/g, "-007-");
    tmpDate = tmpDate.replace(/aug/g, "-008-");
    tmpDate = tmpDate.replace(/sep/g, "-009-");
    tmpDate = tmpDate.replace(/oct/g, "-010-");
    tmpDate = tmpDate.replace(/nov/g, "-011-");
    tmpDate = tmpDate.replace(/dec/g, "-012-");
    for (i=0;i<tmpDate.length;i++){
      tmpChar = tmpDate.charAt(i);
      if ((tmpChar<"0" || tmpChar>"9") && (tmpChar != "-")){
			tmpDate = tmpDate.replace(tmpChar,"-")
		}
    }
    while(tmpDate.indexOf("--") != -1){
      tmpDate = tmpDate.replace(/--/g,"-");
    }
    start_at = 0;
    end_at = tmpDate.length-1;
    while (tmpDate.charAt(start_at)=="-"){
      start_at++;
    }
    while (tmpDate.charAt(end_at)=="-"){
      end_at--;
    }
    if (start_at < end_at+1){
      tmpDate = tmpDate.substring(start_at,end_at+1);
    }else{
      tmpDate = "";
    }
    aryDate = tmpDate.split("-");//-----------分离

    if (aryDate.length != 3){
      return(defDate);
    }
    for (i=0;i<3;i++){
      if (parseInt(aryDate[i],10)<0){////对小于 1(0) 的赋值为1
		 aryDate[i] = "1";
      }
    }
    find_tag="000";
    for (i=2;i>=0;i--){
      if (aryDate[i].length==3){
        if (aryDate[i]>="001" && aryDate[i]<="012"){
			tmpMonth = parseInt(aryDate[i],10)-1;
			switch (i){
			case 0:
				find_tag = "100";
				one_at = parseInt(aryDate[1],10);
				two_at = parseInt(aryDate[2],10);
				break;
			case 1:
				find_tag = "010";
				one_at = parseInt(aryDate[0],10);
				two_at = parseInt(aryDate[2],10);
				break;
		  case 2:
				find_tag = "001";
				one_at = parseInt(aryDate[0],10);
				two_at = parseInt(aryDate[1],10);
				break;
			}
		}
		}
    }

   if (find_tag!="000"){
		one_days = this.GetMonthDays(two_at,tmpMonth);
		two_days = this.GetMonthDays(one_at,tmpMonth);
	   if ((one_at>one_days)&&(two_at>two_days)){
		 return(defDate);
	   }
		  if ((one_at<=one_days)&&(two_at>two_days)){
		 tmpYear = this.GetFormatYear(two_at);
		 tmpDay = one_at;
	   }
	   if ((one_at>one_days)&&(two_at<=two_days)){
		 tmpYear = this.GetFormatYear(one_at);
		 tmpDay = two_at;
	   }
	   if ((one_at<=one_days)&&(two_at<=two_days)){
			 tmpYear = this.GetFormatYear(one_at);
			 tmpDay = two_at;
			 tmpDate = this.DateFormat;
			 year_at = tmpDate.indexOf("<yyyy>");
			 if (year_at == -1){
				year_at = tmpDate.indexOf("<yy>");
			}
			day_at = tmpDate.indexOf("<dd>");
			 if (day_at == -1){
				day_at = tmpDate.indexOf("<d>");
			 }
			 if (year_at >= day_at){
				tmpYear = this.GetFormatYear(two_at);
				tmpDay = one_at;
			 }
		}
		return(new Date(tmpYear, tmpMonth, tmpDay));
    }

    find_tag = "000";
    for (i=2;i>=0;i--){
      if (parseInt(aryDate[i],10)>31){
		 tmpYear = this.GetFormatYear(parseInt(aryDate[i],10));
		 switch (i){
		   case 0:
			  find_tag = "100";
			  one_at = parseInt(aryDate[1],10);
			  two_at = parseInt(aryDate[2],10);
			  break;
		   case 1:
			  find_tag = "010";
			  one_at = parseInt(aryDate[0],10);
			  two_at = parseInt(aryDate[2],10);
			  break;
		   case 2:
			  find_tag = "001";
			  one_at = parseInt(aryDate[0],10);
			  two_at = parseInt(aryDate[1],10);
			  break;
		   }
		}
    }


   if (find_tag=="000"){
		tmpDate = this.DateFormat;
	    year_at = tmpDate.indexOf("<yyyy>");
	   if (year_at == -1){
		 year_at = tmpDate.indexOf("<yy>");
	   }
	   month_at = tmpDate.indexOf("<MMMMMM>");
	   if (month_at == -1){
		 month_at = tmpDate.indexOf("<MMM>");
	   }
	   if (month_at == -1){
		 month_at = tmpDate.indexOf("<mm>");
	   }
	   if (month_at == -1){
		 month_at = tmpDate.indexOf("<m>");
	   }
	   day_at = tmpDate.indexOf("<dd>");
	   if (day_at == -1){
		 day_at = tmpDate.indexOf("<d>");
	   }
	   if ((year_at>month_at)&&(year_at>day_at)){
		 find_tag="001"
	   }
	   if ((year_at>month_at)&&(year_at<=day_at)){
		 find_tag="010";
	   }
	   if ((year_at<=month_at)&&(year_at>day_at)){
		 find_tag="010";
	   }
	   if ((year_at<=month_at)&&(year_at<=day_at)){
		 find_tag="100";
	   }
	   switch (find_tag){
			case "100":
				tmpYear = parseInt(aryDate[0],10);
				one_at = parseInt(aryDate[1],10);
				two_at = parseInt(aryDate[2],10);
				break;
			 case "010":
				one_at = parseInt(aryDate[0],10);
				tmpYear = parseInt(aryDate[1],10);
				two_at = parseInt(aryDate[2],10);
				break;
			case "001":
				one_at = parseInt(aryDate[0],10);
				two_at = parseInt(aryDate[1],10);
				tmpYear = parseInt(aryDate[2],10);
				break;
	   }
	   tmpYear = this.GetFormatYear(tmpYear);
   }
	if (find_tag!="000"){
	  if ((one_at>12)&&(two_at>12)){
		return(defDate);
	}

	//alert(one_at+"|"+two_at)
	/*--依照mm-dd 加入日期大小验证*/
	if((one_at >13 || two_at >31))
		return '';

   if (one_at<=12){
     if (two_at > this.GetMonthDays(tmpYear,one_at-1)){
       return(new Date(tmpYear, one_at-1, this.GetMonthDays(tmpYear,one_at-1)));
     }
     if (two_at>12){
       return(new Date(tmpYear, one_at-1, two_at));
     }
   }
   if (two_at<=12){
     if (one_at > this.GetMonthDays(tmpYear,two_at-1)){
       return(new Date(tmpYear, two_at-1, this.GetMonthDays(tmpYear,two_at-1)));
     }
     if (one_at>12){
       return(new Date(tmpYear, two_at-1, one_at));
     }
   }

   if ((one_at<=12)&&(two_at<=12)){
     tmpMonth = one_at-1;
     tmpDay = two_at;
     tmpDate = this.DateFormat;
     month_at = tmpDate.indexOf("<MMMMMM>");
     if (month_at == -1){
       month_at = tmpDate.indexOf("<MMM>");
     }
     if (month_at == -1){
       month_at = tmpDate.indexOf("<mm>");
     }
     if (month_at == -1){
       month_at = tmpDate.indexOf("<m>");
     }
     day_at = tmpDate.indexOf("<dd>");
     if (day_at == -1){
       day_at = tmpDate.indexOf("<d>");
     }
     if (month_at >= day_at){
		tmpMonth = two_at-1;
		tmpDay = one_at;
     }
		//alert(end_at+"|"+tmpYear+"-"+(tmpMonth+1))
		//if(theString.length==7||theString.length==6)// 适合YYYY-MM、YYYY-M
			//return(tmpYear+"-"+(tmpMonth+1));
		//else
		return(new Date(tmpYear, tmpMonth, tmpDay));
    }}
 }

//== 往 head 中写入当前的年与月
function meizzWriteHead(yy,mm)  //
{
	odatelayer.getElementById("meizzYearHead").innerText  = yy + " 年";
	odatelayer.getElementById("meizzMonthHead").innerText = mm + " 月";
}

//== 年份的下拉框
function tmpSelectYearInnerHTML(strYear) 
{
	  if (strYear.match(/\D/)!=null){alert("年份输入参数不是数字！");return;}
	  var m = (strYear) ? strYear : new Date().getFullYear();
	  if (m < 1000 || m > 9999) {alert("年份值不在 1000 到 9999 之间！");return;}
	  var n = m - 80; //pre year
	  if (n < 1000) n = 1000;
	  if (n + 26 > 9999) n = 9974;
	  var s = "<select Author=meizz name=tmpSelectYear style='font-size: 12px' "
		 s += "onblur='document.all.tmpSelectYearLayer.style.display=\"none\"' "
		 s += "onchange='document.all.tmpSelectYearLayer.style.display=\"none\";"
		 s += "parent.meizzTheYear = this.value; parent.meizzSetDay(parent.meizzTheYear,parent.meizzTheMonth)'>\r\n";
	  var selectInnerHTML = s;
	  for (var i = n; i < n + 161; i++) //next year
	  {
		if (i == m)
		   {selectInnerHTML += "<option Author=wayx value='" + i + "' selected>" + i + "年" + "</option>\r\n";}
		else {selectInnerHTML += "<option Author=wayx value='" + i + "'>" + i + "年" + "</option>\r\n";}
	  }
	  selectInnerHTML += "</select>";
	  odatelayer.getElementById("tmpSelectYearLayer").style.display="";
	  odatelayer.getElementById("tmpSelectYearLayer").innerHTML = selectInnerHTML;
	  if(!global_explorer_ie6_date)
			odatelayer.getElementById("tmpSelectYear").focus();
}

//== 月份的下拉框
function tmpSelectMonthInnerHTML(strMonth) 
{
	  if (strMonth.match(/\D/)!=null){alert("月份输入参数不是数字！");return;}
	  var m = (strMonth) ? strMonth : new Date().getMonth() + 1;
	  var s = "<select Author=meizz name=tmpSelectMonth style='font-size: 12px' "
		 s += "onblur='document.all.tmpSelectMonthLayer.style.display=\"none\"' "
		 s += "onchange='document.all.tmpSelectMonthLayer.style.display=\"none\";"
		 s += "parent.meizzTheMonth = this.value; parent.meizzSetDay(parent.meizzTheYear,parent.meizzTheMonth)'>\r\n";
	  var selectInnerHTML = s;
	  for (var i = 1; i < 13; i++)
	  {
		if (i == m)
		   {selectInnerHTML += "<option Author=wayx value='"+i+"' selected>"+i+"月"+"</option>\r\n";}
		else {selectInnerHTML += "<option Author=wayx value='"+i+"'>"+i+"月"+"</option>\r\n";}
	  }
	  selectInnerHTML += "</select>";
	  odatelayer.getElementById("tmpSelectMonthLayer").style.display="";
	  odatelayer.getElementById("tmpSelectMonthLayer").innerHTML = selectInnerHTML;
	  if(!global_explorer_ie6_date)
			odatelayer.getElementById("tmpSelectMonth").focus();
}

//--
function getSelectTimeStr(str,flag){
	if(global_date_timeStr == "")
		return;
	var temp = setFormatTimeStr(global_date_timeStr).split(":");
	temp[flag] = str;
	global_date_timeStr = temp[0]+"："+temp[1]+"："+temp[2];
}

function tmpSelectHourHTML(str){
	var h = (str!=' ')?parseInt(str,10):new Date().getHour();
	var s = '<select name=tmpSelectHour Author=meizz ';
		 s += 'onblur = "document.all.tmpSelectHourLayer.style.display=\'none\';" ';
		 s += 'onchange="document.all.tmpSelectHourLayer.style.display=\'none\';';
		 s += 'document.all.tdId_hour.innerText =this.value+\'：\';parent.getSelectTimeStr(this.value,0)">\r\n';
		for(j=0;j<24;j++){
		    var temp = j< 10 ? "0"+j:j;
			if(h == j)
				s+= '<option value="'+temp+'" selected>'+temp+'</option>\n';
			else
			    s+='<option value="'+temp+'">'+temp+'</option>\n';
		}
	   s+= '<select>';	
	  odatelayer.getElementById("tmpSelectHourLayer").style.display="";
	 odatelayer.getElementById("tmpSelectHourLayer").innerHTML = s;
	  if(!global_explorer_ie6_date)
		odatelayer.getElementById("tmpSelectHour").focus();
}
function tmpSelectMiniHTML(str){
	var h = (str!=' ')?parseInt(str,10):new Date().getHour();
	var s = '<select name=tmpSelectMini  Author=meizz ';
		 s += 'onblur = "document.all.tmpSelectMiniLayer.style.display=\'none\'" ';
		 s += 'onchange="document.all.tmpSelectMiniLayer.style.display=\'none\';';
		 s += 'document.all.tdId_mini.innerText =this.value+\'：\';parent.getSelectTimeStr(this.value,1)">\r\n';
		for(j=0;j<60;j++){
		    var temp = j< 10 ? "0"+j:j;
			if(h == j)
				s+= '<option value="'+temp+'" selected>'+temp+'</option>\n';
			else
			    s+='<option value="'+temp+'">'+temp+'</option>\n';
		}
	   s+= '<select>';	
	  odatelayer.getElementById("tmpSelectMiniLayer").style.display="";
	  odatelayer.getElementById("tmpSelectMiniLayer").innerHTML = s;
	  if(!global_explorer_ie6_date)
		odatelayer.getElementById("tmpSelectMini").focus();
}
function tmpSelectSecoHTML(str){
	var h = (str!=' ')?parseInt(str,10):new Date().getHour();
	var s = '<select name=tmpSelectSeco Author=meizz ';
		 s += 'onblur = "document.all.tmpSelectSecoLayer.style.display=\'none\'" ';
		 s += 'onchange="document.all.tmpSelectSecoLayer.style.display=\'none\';';
		 s += 'document.all.tdId_seco.innerText =this.value;parent.getSelectTimeStr(this.value,2);">\r\n';
		for(j=0;j<60;j++){
		    var temp = j< 10 ? "0"+j:j;
			if(h == j)
				s+= '<option value="'+temp+'" selected>'+temp+'</option>\n';
			else
			    s+='<option value="'+temp+'">'+temp+'</option>\n';
		}
	   s+= '<select>';	
	  odatelayer.getElementById("tmpSelectSecoLayer").style.display="";
	  odatelayer.getElementById("tmpSelectSecoLayer").innerHTML = s;
	  if(!global_explorer_ie6_date)
		odatelayer.getElementById("tmpSelectSeco").focus();
}

//== 判断是否闰平年
function IsPinYear(year)            
{
    if (0==year%4&&((year%100!=0)||(year%400==0))) return true;else return false;
}

//== 闰年二月为29天
function GetMonthCount(year,month)  
{
    var c=MonHead[month-1];if((month==2)&&IsPinYear(year)) c++;return c;
}
//== 求某天的星期几
function GetDOW(day,month,year)     
{
    var dt=new Date(year,month-1,day).getDay()/7; return dt;
}

//== 往前翻 Year
function meizzPrevY()  
{
    if(meizzTheYear > 999 && meizzTheYear <10000){meizzTheYear--;}
    else{alert("年份超出范围（1000-9999）！");}
    meizzSetDay(meizzTheYear,meizzTheMonth);
}
//== 往后翻 Year
function meizzNextY()  
{
    if(meizzTheYear > 999 && meizzTheYear <10000){meizzTheYear++;}
    else{alert("年份超出范围（1000-9999）！");}
    meizzSetDay(meizzTheYear,meizzTheMonth);
}
//== Today Button
function meizzToday()  
{
	var today;
    meizzTheYear = new Date().getFullYear();
    meizzTheMonth = new Date().getMonth()+1;
    today=new Date().getDate();
    //meizzSetDay(meizzTheYear,meizzTheMonth);
    if(global_date_input){
		global_date_input.value=meizzTheYear + "-" + (meizzTheMonth > 9 ? meizzTheMonth + "" : "0" + meizzTheMonth) + "-" + (today > 9 ? today + "" : "0" + today);
    }
    closeLayer();
}
//== 往前翻月份
function meizzPrevM()  
{
    if(meizzTheMonth>1){meizzTheMonth--}else{meizzTheYear--;meizzTheMonth=12;}
    meizzSetDay(meizzTheYear,meizzTheMonth);
}
//== 往后翻月份
function meizzNextM()  
{
    if(meizzTheMonth==12){meizzTheYear++;meizzTheMonth=1}else{meizzTheMonth++}
    meizzSetDay(meizzTheYear,meizzTheMonth);
}
//== 主要的写程序**********
function meizzSetDay(yy,mm)   
{
	  meizzWriteHead(yy,mm);
	  //设置当前年月的公共变量为传入值
	  meizzTheYear=yy;
	  meizzTheMonth=mm;

	  for (var i = 0; i < 39; i++){meizzWDay[i]=""};  //将显示框的内容全部清空

	  var day1 = 1,day2=1,firstday = new Date(yy,mm-1,1).getDay();  //某月第一天的星期几

	  for (i=0;i<firstday;i++)meizzWDay[i]=GetMonthCount(mm==1?yy-1:yy,mm==1?12:mm-1)-firstday+i+1	//上个月的最后几天

	  for (i = firstday; day1 < GetMonthCount(yy,mm)+1; i++){meizzWDay[i]=day1;day1++;}
	  for (i=firstday+GetMonthCount(yy,mm);i<39;i++){meizzWDay[i]=day2;day2++}
	  for (i = 0; i < 39; i++)
	  { var da = odatelayer.getElementById("meizzDay"+i)     //书写新的一个月的日期星期排列

		if (meizzWDay[i]!="")
		  {
			//初始化边框

			da.borderColorLight=global_date_linecolor;
			da.borderColorDark="#FFFFFF";
			if(i<firstday)		//上个月的部分
			{
				da.innerHTML="<font color=gray>" + meizzWDay[i] + "</font>";
				da.title=(mm==1?12:mm-1) +"月" + meizzWDay[i] + "日";
				da.onclick=Function("meizzDayClick(this.innerText,-1)");
				if(!global_outDate)
					da.style.backgroundColor = ((mm==1?yy-1:yy) == new Date().getFullYear() &&
						(mm==1?12:mm-1) == new Date().getMonth()+1 && meizzWDay[i] == new Date().getDate()) ?
						 global_date_linecolor:global_date_bgcolor;
				else
				{
					da.style.backgroundColor =((mm==1?yy-1:yy)==global_outDate.getFullYear() && (mm==1?12:mm-1)== global_outDate.getMonth() + 1 &&
					meizzWDay[i]==global_outDate.getDate())? global_date_defcolor :
					(((mm==1?yy-1:yy) == new Date().getFullYear() && (mm==1?12:mm-1) == new Date().getMonth()+1 &&
					meizzWDay[i] == new Date().getDate()) ? global_date_linecolor:global_date_bgcolor);
					//将选中的日期显示为凹下去

					if((mm==1?yy-1:yy)==global_outDate.getFullYear() && (mm==1?12:mm-1)== global_outDate.getMonth() + 1 &&
					meizzWDay[i]==global_outDate.getDate())
					{
						da.borderColorLight="#FFFFff";
						da.borderColorDark=global_date_linecolor;
					}
				}
			}
			else if (i>=firstday+GetMonthCount(yy,mm))		//下个月的部分
			{
				da.innerHTML="<font color=gray>" + meizzWDay[i] + "</font>";
				da.title=(mm==12?1:mm+1) +"月" + meizzWDay[i] + "日";
				da.onclick=Function("meizzDayClick(this.innerText,1)");
				if(!global_outDate)
					da.style.backgroundColor = ((mm==12?yy+1:yy) == new Date().getFullYear() &&
						(mm==12?1:mm+1) == new Date().getMonth()+1 && meizzWDay[i] == new Date().getDate()) ?
						 global_date_linecolor:global_date_bgcolor;
				else
				{
					da.style.backgroundColor =((mm==12?yy+1:yy)==global_outDate.getFullYear() && (mm==12?1:mm+1)== global_outDate.getMonth() + 1 &&
					meizzWDay[i]==global_outDate.getDate())? global_date_defcolor :
					(((mm==12?yy+1:yy) == new Date().getFullYear() && (mm==12?1:mm+1) == new Date().getMonth()+1 &&
					meizzWDay[i] == new Date().getDate()) ? global_date_linecolor:global_date_bgcolor);
					//将选中的日期显示为凹下去

					if((mm==12?yy+1:yy)==global_outDate.getFullYear() && (mm==12?1:mm+1)== global_outDate.getMonth() + 1 &&
					meizzWDay[i]==global_outDate.getDate())
					{
						da.borderColorLight="#FFFFFF";
						da.borderColorDark=global_date_linecolor;
					}
				}
			}
			else		//本月的部分

			{
				da.innerHTML="" + meizzWDay[i] + "";
				da.title=mm +"月" + meizzWDay[i] + "日";
				da.onclick=Function("meizzDayClick(this.innerText,0)");		//给td赋予onclick事件的处理

				//如果是当前选择的日期，则显示亮蓝色的背景；如果是当前日期，则显示暗黄色背景
				if(!global_outDate)
					da.style.backgroundColor = (yy == new Date().getFullYear() && mm == new Date().getMonth()+1 && meizzWDay[i] == new Date().getDate())?
						global_date_linecolor:global_date_bgcolor;
				else
				{
					da.style.backgroundColor =(yy==global_outDate.getFullYear() && mm== global_outDate.getMonth() + 1 && meizzWDay[i]==global_outDate.getDate())?
						global_date_defcolor:((yy == new Date().getFullYear() && mm == new Date().getMonth()+1 && meizzWDay[i] == new Date().getDate())?
						global_date_linecolor:global_date_bgcolor);
					//将选中的日期显示为凹下去

					if(yy==global_outDate.getFullYear() && mm== global_outDate.getMonth() + 1 && meizzWDay[i]==global_outDate.getDate())
					{
						da.borderColorLight="#FFFFFF";
						da.borderColorDark=global_date_linecolor;
					}
				}
			}
			da.style.cursor="hand"
		  }
		else{da.innerHTML="";da.style.backgroundColor="";da.style.cursor="default"}
	  }
}

//== 点击显示框选取日期，主输入函数*************
function meizzDayClick(n,ex)  
{
	  var yy=meizzTheYear;
	  var mm = parseInt(meizzTheMonth)+ex;	//ex表示偏移量，用于选择上个月份和下个月份的日期
		//判断月份，并进行对应的处理

		if(mm<1){
			yy--;
			mm=12+mm;
		}
		else if(mm>12){
			yy++;
			mm=mm-12;
		}

	  if (mm < 10){mm = "0" + mm;}
	  if (global_date_input)
	  {
		if (!n) {//global_date_input.value="";
		  return;}
		if ( n < 10)
			n = "0" + n;
		
		setDateInputValue(yy + "-" + mm + "-" + n); //注：在这里你可以输出改成你想要的格式
		//global_date_input.value= yy + "-" + mm + "-" + n   ; //注：在这里你可以输出改成你想要的格式
		//if(global_date_getTime == 'object')
		//	global_date_getTime.value = setFormatTimeStr(global_date_timeStr)
		//else if(global_date_getTime != null)
		//	global_date_input.value += " "+ setFormatTimeStr(global_date_timeStr)
		//global_date_input.focus();
		closeLayer();
	  }
	  else {closeLayer(); alert("您所要输出的控件对象并不存在！");}
}
function setFormatTimeStr(str){
	//if(global_date_getTime != null)
	str = str.replace(/：/gi,":");
	return str 
	//else
		//return "";
}
//向输入框赋值
function setDateInputValue(str){
	if(globalFormat == 6){
		global_date_input.value = str.substring(0,4)+str.substring(5,7);
	}else if(globalFormat == 7){
		global_date_input.value = str.substring(0,7);
	}else if(globalFormat == 8){
		global_date_input.value = str.substring(0,4)+str.substring(5,7)+str.substring(8,10);
	}else{
		global_date_input.value = str;
	}
	if(typeof global_date_getTime == 'object')
		global_date_getTime.value = setFormatTimeStr(global_date_timeStr)
	else if(global_date_getTime != ''){
		global_date_input.value += " "+ setFormatTimeStr(global_date_timeStr)
	}
	//global_date_input.focus();	
}

//===== 向外接口 =============================================================================================
//--------------检查输出的格式 调用方式：onblur=checkDay(this)
function checkDay(obj,timeobj) 
{
	var rrr,rrr_tmp;
	global_date_input = obj;
	////global_date_input =obj; //(arguments.length == 1) ? tt : obj;
	//alert(global_date_input);
	if(typeof timeobj == 'object'){
		global_date_getTime = timeobj;
		global_date_timeStr = timeobj.value = checkFormatTime2(timeobj.value)
	}else if (arguments.length > 1){
		global_date_getTime = timeobj
		global_date_timeStr = checkFormatTime2(global_date_input.value.substring(11))
	}else{
		global_date_getTime = '';
		global_date_timeStr = checkFormatTime2(global_date_input.value.substring(11))
	}
	var str = global_date_input.value.substring(0,10)	

	if(str!='' && str != ' ')
	{ 
		/*if(global_date_input.value.length==7||global_date_input.value.length==6)
			global_date_input.value=this.GetTextDate(global_date_input.value)
		else*/
		{
			//if(global_date_getTime != null)
				//return checkDay(global_date_input)&&global_date_timeStr
			var revalue=this.GetTextDate(str)
			var rrr    =new Date(revalue);
			if ((revalue ==''&&str!='')|| isNaN(rrr)){			
				global_date_input.value=global_date_input.value;
				global_date_input.select();
				alert('输入日期格式有错误！');
				return false;
			}else
			{ 
				rrr_tmp=this.SetDateFormat(rrr.getFullYear(),rrr.getMonth(),rrr.getDate());
				//if(global_date_getTime != null)
					//rrr_tmp += " "+global_date_timStr;
				//global_date_input.value=rrr_tmp;
				setDateInputValue(rrr_tmp)
				return true;
			}
		}
	}
	return false;
}

function dateAdapterFunc() {
	//var cp = getContextPath() ;
	var url = location.href;
	   url = url.substring(url.indexOf('://') + 3);
	   if(url.indexOf('/') < 0)
	   		return "./";
	   url = url.substring(url.indexOf('/') + 1);
	   var pos;
	   if(url.indexOf('/') >= 0) 
			pos = url.indexOf('/');
	   else pos = url.length;
	   		url = url.substring(0, pos);
	var cp = url ; 
	var str = "/"+cp+'/thirdparty/My97DatePicker/adapter.jsp';
	document.write('<iframe id="date_ad" name="date_ad" style="position:absolute;top: 0; left: 0; height:0;width:0; background-color:beige"  src="'+str+'"></iframe>') ;
}

/*
 * add by yangjk 2009-1-6
 * 日期空间折中方法 
 */
//dateAdapterFunc() ;


function loadDatePic() {
	try {
		window.frames['date_ad'].document.getElementById('dpdpdpd').innerHTML = 'ffff';
		var dad = document.getElementsByName('date_ad');
		var url = location.href;
		   url = url.substring(url.indexOf('://') + 3);
		   if(url.indexOf('/') < 0)
		   		return "./";
		   url = url.substring(url.indexOf('/') + 1);
		   var pos;
		   if(url.indexOf('/') >= 0) 
				pos = url.indexOf('/');
		   else pos = url.length;
		   		url = url.substring(0, pos);
		var cp = url ; 
		var str = "/"+cp+'/thirdparty/My97DatePicker/adapter.jsp';
		document.body.onclick = setRealVid();
		dad[0].src = str ;
	}catch(e) {
		setTimeout( "loadDatePic()", 0 );
	}
}
//loadDatePic();

/**
 * 在jsp中引入date.js,如果不想引入WdatePicker.js,可以通过datePicker()这个方法来使用该控件
 * @param ag json
 * @return
 */
function datePicker(ag) {
	//window.frames['date_ad'].
	WdatePicker(ag);
}

//== 主调函数
/* tt      点击按钮
 * obj     保存日期obj
 * format  显示格式，"6" : yyyymm,"7" : yyyy-mm,"8" : yyyymmdd,"10": yyyy-mm-dd
 * timeobj 保存时间obj 如果不是对象则时间和日期在一块 
 */
var gga_2009_5_13 ; 
function setRealVid() {
	if(window.event.srcElement != gga_2009_5_13) {
		//$dp.hide();
	}
}
function setday(tt,obj,format,timeobj) 
{
	gga_2009_5_13 = window.event.srcElement;
 	try {
	  var da = 'yyyy-MM-dd';
	  if(typeof format != 'undefined') {
	   if(format==6) {
	    da = 'yyyyMM';
	   }
	   else if(format==7) {
	    da = 'yyyy-MM';
	   }
	   else if(format==10) {
	    da = 'yyyyMMdd';
	   }
	  }
	  var ymd = da ;
	  if(typeof timeobj != 'undefined') {
	   da = da + ' HH:mm:ss' ;
	  }
	  var ag = {el:obj,dateFmt:da};
	  ag.skin = 'simple';
	  
	  if(typeof timeobj != 'undefined') {
	   ag.onpicked = function() {
	    obj.value=$dp.cal.getDateStr(ymd);
	    if(timeobj.name == obj.name)
	    	timeobj.value = obj.value + ' ' + $dp.cal.getP('H','HH')  + ':'+ $dp.cal.getP('m','mm') + ':'+ $dp.cal.getP('s','ss');
	    else 
	    	timeobj.value = $dp.cal.getP('H','HH')  + ':'+ $dp.cal.getP('m','mm') + ':'+ $dp.cal.getP('s','ss');
	   };
	  }
	  //window.frames['date_ad'].WdatePicker(ag);
	  WdatePicker(ag);
		
	}catch(e) {
 
		//if (arguments.length >  2){alert("对不起！传入本控件的参数太多！");return;}
		if (arguments.length == 0){alert("对不起！您没有传回本控件任何参数！");return;}
		if (arguments.length > 3)
			global_date_getTime = (typeof timeobj == 'object')?timeobj:1;
		else
			global_date_getTime = '';
		globalFormat = format;
		var th = tt;
		var ttop  = obj.offsetTop;     //TT控件的定位点高
		var thei  = obj.clientHeight;  //TT控件本身的高
		var tleft = obj.offsetLeft;    //TT控件的定位点宽
		var ttyp  = tt.type;          //TT控件的类型
		while (tt = tt.offsetParent){ttop+=tt.offsetTop; tleft+=tt.offsetLeft;}
	
		if(!global_explorer_ie6_date){
			var dads  = document.all.meizzDateLayer.style;
			dads.top  = (ttyp=="image")? ttop+thei : ttop+thei+6;
			dads.left = tleft;
			dads.display = '';
		}
	
		global_date_input = (arguments.length == 1) ? th : obj;
		lastObjectValue = global_date_input.value;
		global_date_button = (arguments.length == 1) ? null : th;	//设定外部点击的按钮
		//根据当前输入框的日期显示日历的年月
		//var reg /^(\d+)-(\d{1,2})-(\d{1,2})$/;
		var tmpYear,tmpMonth,tmpDay,rrr_tmp;
		var tmpDate = global_date_input.value.substring(0,10)
			global_date_timeStr = "";
	
		if(typeof global_date_getTime == 'object')
			global_date_timeStr = global_date_getTime.value
		else if(global_date_getTime!='')	
			global_date_timeStr = global_date_input.value.substring(11)
		global_date_timeStr = (global_date_timeStr!="")?checkFormatTime2(global_date_timeStr):global_date_timeStr;
		displayDate();
	
		//var r = global_date_input.value.match(reg);
		if(global_date_input.value!=null){
			var rrr=new Date(this.GetTextDate(tmpDate));         
			if (isNaN(rrr)){
				global_outDate="";
				rrr_tmp=this.SetDateFormat(new Date().getFullYear(), new Date().getMonth(),new Date().getDate());
				meizzSetDay(new Date().getFullYear(), new Date().getMonth() + 1);
			}else{ 
				rrr_tmp=this.SetDateFormat(rrr.getFullYear(),rrr.getMonth(),rrr.getDate());
				tmpYear=rrr.getFullYear();
				tmpMonth=rrr.getMonth();
				tmpDay=rrr.getDate();
				//tmpMonth=tmpMonth-1;
				var d= new Date(tmpYear,tmpMonth,tmpDay);
				if(d.getFullYear()==tmpYear && d.getMonth()==tmpMonth&& d.getDate()==tmpDay){
				   global_outDate=d;		//保存外部传入的日期	
				}else{ 			
					global_outDate="";
				}
				meizzSetDay(tmpYear,tmpMonth+1);	
				//global_date_input.focus()
			}	
		} 
		if(global_date_getTime != ''){
			var arr = getCurrentTime(global_date_timeStr);
			//rrr_tmp += " "+setFormatTimeStr(global_date_timeStr)
		}
		//global_date_input.value=rrr_tmp;
		//global_date_input.select();
		setDateInputValue(rrr_tmp);
	
		if(global_explorer_ie6_date)
			DatePopWin.show(0,(obj.clientHeight+6), 145, 220,obj);
		event.returnValue=false;
	}
	
}

//== 任意点击时关闭该控件	//ie6的情况可以由下面的切换焦点处理代替
document.onclick = function() 
{
  with(window.event)
  {  
	 if (srcElement.getAttribute("Author")==null && srcElement != global_date_input && srcElement != global_date_button)
		 closeLayer();
  	 if (typeof global_object_input == 'object')//2004-08-03 关闭其他层
	 {	 if(srcElement !=global_object_input && srcElement !=global_object_button  && displaySelectLayer )
			document.all.displaySelectLayer.style.display="none";
	 }
  }
}


//== 按Esc键关闭，切换焦点关闭
function closeLayer()               //这个层的关闭
{
    if(global_explorer_ie6_date)
		DatePopWin.hide();
	else
		document.all.meizzDateLayer.style.display="none";	
}

//== 使用快捷键 切换 
function simulate()		   
{ 
    if (window.event.keyCode==27){
		if(global_date_input) global_date_input.value=lastObjectValue;
		global_date_input.blur();
		closeLayer();
	}
	else if (13==event.keyCode||32==event.keyCode)  //enter键 空格键 转移焦点到下一个控件
    {
		if(document.activeElement)
       {        event.keyCode = 9;
                //document.activeElement.blur();
                //alert( event .keyCode);
        	//if(document.activeElement.getAttribute("Author")==null && document.activeElement != global_date_input && document.activeElement != global_date_button)
		//{
		closeLayer();
		//}
      }}
}

//== 弹出帮助窗口
function getWinHelp(){
	var server_path = '../';
	//防止 getContextPath() 未定义
	if(typeof getContextPath != 'undefined')
		server_path = "/"+getContextPath()
	if(typeof openWindowInFrame !='undefined')
		openWindowInFrame(server_path+'/script/date_help.htm',550,450);
	else
		window.open(server_path+'/script/date_help.htm','','width=550px,height=450px');
}


//--根据 class="inputMonth" onkeyup 来验证 时间格式 hh:mm:ss
// obj 为输入框对象
function setHourFormatDate(obj){
	var str = obj.value
	var inum= str.length
	var arr = checkFormatTime1(str)
	str = arr[0];
	inum= arr[1];
	if(str.length > 7){
		obj.className = 'input_textNormal1';
		str = str.substr(0,8)
	}else 
		obj.className = 'input_textHour';
	obj.value = str
	getElementRight(inum);
}
//验证格式
//return array [str 数据,inum定位]
function checkFormatTime1(str){
	var reg4= '0123456789';
	var inum = str.length;
	for (i = 0; i < str.length; i++){ 
		var c = str.charAt(i);
		var p = '';
		if(i>0) p = str.charAt(i-1);
		if(i==2){
			if( c != ':'){
				str = str.substr(0,2)+':'+str.substr(2);
				inum = i+2;
			}
		}else if(i==5){
			if( c != ':'){
				str = str.substr(0,5)+':'+str.substr(5);
				inum = i+2;
			}
		}else if( reg4.indexOf(c) == -1) {
				str = str.substr(0,i-1)+""+str.substr(i+1);
				inum = i;
				break;				
		}else if(i==0 && parseInt(c)>2){//<24
			str = str.substr(0,0)+""+str.substr(1);
			inum = i+1;
		}else if(i==1 && parseInt(p)==2&&parseInt(c)>3){
			str = str.substr(0,1)+""+str.substr(2);
			inum = i+1;			
		}else if(i==3 && parseInt(c)>5){//<60
			str = str.substr(0,3)+""+str.substr(4);
			inum = i+1;
		}else if(i==6 && parseInt(c)>5){//<60
			str = str.substr(0,6)+""+str.substr(7);
			inum = i+1;
		}
	}
	return [str,inum]
}
//验证格式
function checkFormatTime2(str){
	var reg = /^([0-9]){2}\:([0-9]){2}\:([0-9]){2}$/gi;
	var mid_num = str.split(":");
	if(!reg.test(str)&&mid_num.length<3)
		return "00:00:00"
	else{
		for(i=0;i<mid_num.length;i++)
			if(isNaN(mid_num[i])){
				mid_num[i] = "00";
			}
		/*
		var reg1 = /^([0]{1}[0-9]){1}$/gi;
		if(reg1.test(mid_num[0]))
			mid_num[0] = parseInt(mid_num[0].replace(/0/,''));
		if(reg1.test(mid_num[1]))
			mid_num[1] = parseInt(mid_num[1].replace(/0/,''));
		if(reg1.test(mid_num[2]))
			mid_num[2] = parseInt(mid_num[2].replace(/0/,''));
		*/
		if(parseInt(mid_num[0],10)>23)
			mid_num[0] = "23";
		if(parseInt(mid_num[1],10)>59)
			mid_num[1] = "00";
		if(parseInt(mid_num[2],10)>59)
			mid_num[2] = "00";
		return  mid_num[0]+":"+mid_num[1]+":"+mid_num[2]
	}
}

//--根据 class="inputMonth" onkeyup 来验证 年月格式 yyyy-mm
// obj 为输入框对象
function setShortFormatDate(obj){
	var reg4= '0123456789';
	var str = obj.value
	var inum= str.length
	for (i = 0; i < str.length; i++){ 
		var p = '';
		if(i>0) p = str.charAt(i-1);
		var c = str.charAt(i);
		if(i==4){
			if( c != '-'){
				str = str.substr(0,4)+'-'+str.substr(4);
				inum = i+2;
			}
		}else if( reg4.indexOf(c) == -1) {
				str = str.substr(0,i-1)+""+str.substr(i+1);
				inum = i;
				break;				
		}else if(i==5 && parseInt(c)>1){
			str = str.substr(0,5)+""+str.substr(6);
			inum = i+1;
		}else if(i==6 && parseInt(p)==1 && parseInt(c)>2){//<12
			str = str.substr(0,6)+""+str.substr(7);
			inum = i+1;
		}
	}
	if(str.length > 6){
		obj.className = 'input_textNormal1';
		str = str.substr(0,7)
	}else 
		obj.className = 'input_textMonth';
	obj.value = str
	getElementRight(inum);
}

//--根据 class="inputDate" onkeyup 来验证 年月日格式 yyyy-mm-nn
// obj 为输入框对象
function setLongFormatDate(obj){
	var reg4= '0123456789';
	var str = obj.value
	var inum= str.length
	for (i = 0; i < str.length; i++){ 
		var p = '';
		if(i>0) p = str.charAt(i-1);
		var c = str.charAt(i);
		if(i==4){
			if( c != '-'){
				str = str.substr(0,4)+'-'+str.substr(4);
				inum = i+2;
			}
		}else if(i==7){
			if( c != '-'){
				str = str.substr(0,7)+'-'+str.substr(7);
				inum = i+2;
			}
		}else if( reg4.indexOf(c) == -1) {
				str = str.substr(0,i-1)+""+str.substr(i+1);
				inum = i;
				break;				
		}else if(i==5 && parseInt(c)>1){
			str = str.substr(0,5)+""+str.substr(6);
			inum = i+1;
		}else if(i==6 && parseInt(p)==1 && parseInt(c)>2){//<12
			str = str.substr(0,6)+""+str.substr(7);
			inum = i+1;
		}else if(i==8 && parseInt(c)>3 ){
			str = str.substr(0,8)+str.substr(9);
			inum = i+1;
		}else if(i==9 && parseInt(p)==3 && parseInt(c)>1)//<31
			str = str.substr(0,9);
		;
	}
	if(str.length > 9){
		obj.className = 'input_textNormal1';
		str = str.substr(0,10)
	}else 
		obj.className = 'input_textDate'
	obj.value = str
	getElementRight(inum);
}

//将鼠标位置定位 inum 为输入框中位置
//重载于 general.js
function getElementRight(inum){  
	  inum = (inum + "" == "undefined")?e.value.length:inum;
	  var e = event.srcElement;  
	  var r =e.createTextRange();  
	  r.moveStart('character',inum);  
	  r.collapse(true);  
	  r.select();  
}  
