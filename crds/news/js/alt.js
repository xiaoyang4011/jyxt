
//-- 2004-12-23 update css �� style.css ��
//-- 2005-02-17 zhoujf ����ע��

//***********Ĭ�����ö���.*********************
alt_tPopWait    = 30;  //ͣ��tWait�������ʾ��ʾ��
alt_tPopShow    = 3000;//��ʾtShow�����ر���ʾ
alt_showPopStep = 10;
alt_popOpacity  = 100;

//***************�ڲ���������*****************
alt_sPop        = null;
alt_curShow     = null;
alt_tFadeOut    = null;
alt_tFadeIn     = null;
alt_tFadeWaiting= null;

//document.write("<style type='text/css'id='defaultPopStyle'>");
//document.write(".cPopText {  background-color: #FFFFFF; border: 1px #000000 solid; font-size: 12px; padding-right: 4px; padding-left: 4px; height: 20px; padding-top: 2px; padding-bottom: 2px; filter: Alpha(Opacity=0)}");
//document.write("</style>");
document.write("<div id='alt_dypopLayer' style='position:absolute;z-index:1000;' class='cPopText'></div>");

/** ��ʾ��ʾ����
 */
function showPopupText(){
	var o=event.srcElement;
	MouseX=event.x;
	MouseY=event.y;
	if(o==null)
		return;
	if(o.alt!=null && o.alt!=""){
		o.dypop=o.alt;
		o.alt=""
	};
    if(o.title!=null && o.title!=""){
		 o.dypop=o.title;
		 o.title=""
	};
	if(o.dypop!=alt_sPop) {
			alt_sPop = o.dypop
			clearTimeout(alt_curShow);
			clearTimeout(alt_tFadeOut);
			clearTimeout(alt_tFadeIn);
			clearTimeout(alt_tFadeWaiting);	
			if(alt_sPop==null || alt_sPop=="") {
				alt_dypopLayer.innerHTML="";
				alt_dypopLayer.style.filter="Alpha()";
				alt_dypopLayer.filters.Alpha.opacity=0;	
				}
			else {
				if(o.dyclass!=null) 
					popStyle=o.dyclass 
				else 
					popStyle="cPopText";
				alt_curShow=setTimeout("showIt()",alt_tPopWait);
			}
			
	}
}

/** ��ʾ��ʾ���� 
 */
function showIt(){
		var rep = "<br>";
		alt_dypopLayer.className=popStyle;
		if(alt_sPop.indexOf('<br>')>=0)
			rep = "";
		alt_sPop = alt_sPop.replace(/\r\n/gi,"<br>");
		alt_sPop = alt_sPop.replace(/\r/gi,"<br>");
		alt_sPop = alt_sPop.replace(/\n/gi,"<br>");
		alt_sPop = alt_sPop.replace(/��/gi,rep+"<span class='article_error'>*</span>");
		alt_sPop = alt_sPop.replace(/\*/gi,rep+"<span class='article_error'>*</span>");
		alt_sPop = alt_sPop.replace(/��/gi,"<span class='article_error'>��</span>");
		alt_sPop = alt_sPop.replace(/��/gi,rep+"<span class='article_error'>��</span>")
		alt_dypopLayer.innerHTML=alt_sPop;
		//��ֹҳ��Խ���߽�
		popWidth=alt_dypopLayer.clientWidth>250?250:alt_dypopLayer.clientWidth;
		popHeight=alt_dypopLayer.clientHeight;
		if(MouseX+12+popWidth>document.body.clientWidth) popLeftAdjust=-popWidth-24
			else popLeftAdjust=0;
		if(MouseY+12+popHeight>document.body.clientHeight) popTopAdjust=-popHeight-24
			else popTopAdjust=0;
		alt_dypopLayer.style.left=MouseX+12+document.body.scrollLeft+popLeftAdjust;
		alt_dypopLayer.style.top=MouseY+12+document.body.scrollTop+popTopAdjust;
		alt_dypopLayer.style.filter="Alpha(Opacity=0)";
		fadeOut();
}

/** ���ڵ��� 
 */
function fadeOut(){
	if(alt_dypopLayer.filters.Alpha.opacity<alt_popOpacity) {
		alt_dypopLayer.filters.Alpha.opacity+=alt_showPopStep;
		alt_tFadeOut=setTimeout("fadeOut()",1);
		}
		else {
			alt_dypopLayer.filters.Alpha.opacity=alt_popOpacity;
			alt_tFadeWaiting=setTimeout("fadeIn()",alt_tPopShow);
			}
}

/** ���ڵ��� 
 */
function fadeIn(){
	if(alt_dypopLayer.filters.Alpha.opacity>0) {
		alt_dypopLayer.filters.Alpha.opacity-=1;
		alt_tFadeIn=setTimeout("fadeIn()",1);
		}
}
document.onmouseover=showPopupText;