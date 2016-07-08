

/*-----------���¼�¼---------------------------------
 * 2005-04-21    Add: reportJsError() js���Է���
 * 2005-02-01    Add: ���뱾��cookie�Ķ�/д/ɾ�ӿ� set_cookie()
 * 2005-01-05    Add: ����ϵͳ������ ����getClickLoading()
 * 2004-11-09    Add: �Զ����һ��˵�����setRightMenu()
					  ע�� ϵͳռ�� document.oncontextmenu
 * 2004-10-08    Add: InitLoadingImage()
 * 2004-09-03    Add: checkExplorer(temp) 
			  Update: update_style()
 * 2004-08-18 Liuz ��ԭ���ϸ���
		1. ʹ��ȫ�ֱ���golobal_image_path����ͼƬ·��
		2. ��ȡ�ļ����ڵ�Ŀ¼����get_filepath()�滻ΪgetContextPath()
 * 
 ---------------------------------------------*/

// ����ϵͳ������ʹ�õ� ����
var global_guest_name="ϣ����˾";
var global_system_name="У��ϵͳ"; 
var global_webmaster_name="webmaster";
var global_webmaster_mail="http://career.buaa.edu.cn/website/script/heer@heerit.com";

//���Դ���JS����
function reportJsError(msg,url,line){
	var win = window.open("","error"+new Date().getTime(),"resizable,status,width=600,height=400");
	var str = '';
	str += '<div><h3 face="helvetica">ҳ�� JavaScript ���󱨸棺</h3><hr>';
	str += '<br>������Ϣ��'+msg;
	str += '<p>ҳ��·����<a href="'+url+'" target="_blank" title="����鿴����">'+url+'</a>';
	str += '<p>����������<b>'+line+'</b>';
	str += '<p>������ţ�'+navigator.userAgent;
	win.document.write(str);
	win.document.close();
	return false;
}
//window.onerror = reportJsError ;

// --��ʾLoading--�÷����Ѿ�����,ʹ���½���������
// function InitLoadingImage(objstr,url){}

 //// ������汾 ��� tempΪ����Ҫ�жϵİ汾.ĿǰֻΪIE,�Ժ󲹳� Netscape
function checkExplorer(temp)
{
	var BrowserInfo = new Object() ;
	var reg = navigator.appVersion.match(/MSIE (.)/)
	if(reg){
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
	}else
		return false;
}


/** ���� 
 *  �򱾵�д��cookie
 *  ע�⣺д���cookie���ݸ�ʽ�� name0=value0;name1=value1;
 *  @param aname   ��������  
 *  @param avalue  ֵ����
 *  @param cookie_expire
 *  @param cookie_path
 */
function set_cookie(aname,avalue,cookie_expire,cookie_path){
	var cookie_string=aname + "=" + avalue;
	if(cookie_expire){
		var expire_date = new Date();
		var ms_from_now=cookie_expire*24*60*60*1000;
		expire_date.setTime(expire_date.getTime() + ms_from_now);
		var expire_string = expire_date.toGMTString();
		cookie_string += "; expires =" + expire_string;
	}
	if(cookie_path){
		cookie_string += "; path =" + cookie_path;
	}
	document.cookie=cookie_string;
}

/** ���� 
 *  ��ȡcookie��cname��ֵ
 *  @param cname
 *  @param main_name = "ZsuSysStyle_cookie" / "ZsuSysSize_cookie"  
 */
function get_cookie(cname){
	var cookie_pair;
	var temp=document.cookie.split(";");
	for(i=0;i<temp.length;i++){
			for(var j=0;j<temp.length;j++){
				cookie_pair = temp[j].split("=");
				var cookie_name = cookie_pair[0].replace(/(^\s*)|(\s*$)/g, '');
				if(cookie_name == cname){
					return unescape(cookie_pair[1]);
				}
			}
	}
	return 0;
}

/** ���cookie 
 *  @param dname ������cookie����
 */
function del_cookie(dname,path){
	if(get_cookie(dname))
		set_cookie(dname,"",-1,path);
}


//��ȡ�������� context ������zsucommon
function getContextPath(){
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
   return url;
}

//ȫ�� ��ΪskinͼƬ·��
var golobal_image_path='/'+getContextPath()+'/image/skin/';  
	golobal_image_path=golobal_image_path+get_cookie("ZsuSysStyle_cookie");

//���� �任���	
function update_style(temp){      
	var colorSetting=new Array();
	var tempTimeout;
	var cssQueue	= ['body','article_title','article_subtitle','article_subhead','article_subhead1','article_content','article_note'
					  ,'article_subnote','article_timestyle1','article_timestyle3','article_help','article_master','font_color'
					  ,'tab_title','tab_title_bottom','tab_listing_head','tab_listing_bottom','tab_img'
					  ,'tab_bg','tab_0','tab_1','tab_2','tab_3','tab_5','button1'
					  ,'a','a_hover','link2','link2_hover'];
					  //,'tab_selected_panel1','tab_selected_panel2','tab_selected_panel3'
        
	golobal_image_path='/'+getContextPath()+'/image/skin/'+temp;
	
	//begin_css_filer();
	clearTimeout(tempTimeout);
	var oldBody=document.body;
	if(oldBody!=null) {
		var newBody=oldBody.cloneNode();//������Ӧ���˾�ǰ���У���ȷ��body�����Ǹɾ���
		var tempScroll	= [oldBody.scrollLeft,oldBody.scrollTop];
		oldBody.style.filter='blendTrans(duration=1)';
		oldBody.filters[0].apply();
	}
	colorSetting[0]="#ffffff;#284224;#3E6437;#000066;#000099;#0000CC;#D2D2FF;#8BD04D;#D2D2FF;#ffffff;#4E7CE9;#0000ff;#003366;0;0;0;0;0;#477DF5;#9eb8f3;#b9cbf7;#d3dffa;#eaeffd;#ffffff;#eaeffd;#000099;#225CE3;#64A057;#64A057";//;0;0;0		
	colorSetting[1]="#ffffff;#000066;#000099;#223C37;#3A6560;#42736C;#BFCECB;#86A6F0;#BFCECB;#ffffff;#63A7A0;#6BB89B;#38615C;1;1;1;1;1;#36897F;#75B0A9;#9EC4BC;#cbdedb;#F0F7F5;#ffffff;#e6efee;#3A6560;#519187;#225CE3;#225CE3";//;1;1;1
	colorSetting[2]="#ffffff;#666400;#9C9900;#22371E;#32512D;#44701D;#BBD0BD;#FEF889;#BBD0BD;#666666;#72BA30;#67AA2B;#005500;2;2;2;2;2;#9BD766;#a7db77;#c1e7a0;#D9EFC5;#edf7e3;#ffffff;#edf7e3;#32512D;#599126;#C7C300;#C7C300";//;2;2;2
	colorSetting[3]="#ffffff;#005539;#007952;#014D7A;#016AA9;#0292E8;#B4D1D0;#00EC9F;#B4D1D0;#ffffff;#64C4FD;#1AD6FD;#0C5578;3;3;3;3;3;#00A8B0;#6ACAFD;#99DAFD;#D3EDFE;#F1F8FF;#ffffff;#F1F8FF;#016AA9;#31B0FD;#009F68;#009F68";//;3;3;3
	colorSetting[4]="#ffffff;#211551;#2D1E69;#4203C2;#6C28FB;#8146FB;#C9C1D0;#A095C4;#C9C1D0;#ffffff;#AE88FD;#B46AFD;#6403A5;4;4;4;4;4;#ABAAE3;#CAB2FD;#D1B8FD;#E1CDFE;#F2ECFF;#ffffff;#f0e0f5;#7438A9;#9968FD;#3A2885;#3A2885";//;4;4;4
	colorSetting[5]="#ffffff;#64004B;#222222;#333333;#5D5D5D;#7C7C7C;#CAD2B5;#E8D3E3;#CAD2B5;#555555;#ACAEAC;#333333;#000000;5;5;5;5;5;#D4D0C7;#CDCFCD;#DDDDDD;#F3F3F3;#F7F7F7;#ffffff;#F3F3F3;#5D5D5D;#979B97;#A2007C;#A2007C";//;5;5;5
	colorSetting[6]="#ffffff;#72181B;#8E1E20;#463C00;#665700;#826F00;#C7BFA0;#F19373;#C7BFA0;#555555;#C4A600;#EABB00;#6D5801;6;6;6;6;6;#E1D889;#FFDB0B;#FFEE8C;#FFFBC5;#FFFEE1;#ffffff;#FFFEE1;#665700;#AA9100;#72181B;#B6292B";//;6;6;6
	colorSetting[7]="#ffffff;#714004;#945305;#4E1801;#6D2201;#BE3901;#CCB5B0;#F5B16D;#CCB5B0;#ffffff;#FE5E18;#FF0000;#660000;7;7;7;7;7;#8D2200;#FFB395;#FFC8B3;#FFE3D9;#FFF4F0;#ffffff;#FFF4F0;#6D2201;#E94601;#714004;#BD6B09";//;7;7;7

	
	var settings=colorSetting[temp];		
	var colors=new Object();
	for(var i=0;i<cssQueue.length;i++) {
		colors[cssQueue[i]]=settings.split(';')[i];
	}
	//alert(document.styleSheets[0].rules[26].style.backgroundColor);	
	if(get_cookie('ZsuSysSize_cookie')!='')
		update_size(get_cookie('ZsuSysSize_cookie'));
	for(i=0;i<document.styleSheets[0].rules.length;i++) {
		with(document.styleSheets[0].rules[i]) {
			var tempText=selectorText.toLowerCase().replace(/[^\.]*\./i,'').replace(/:\w*/i,'');
			switch(tempText) {
				case 'css_scroll':
					if(checkExplorer(6))//ie6.0
					{
						style.scrollbar3dLightColor=colors['tab_1'];
						style.scrollbarHighlightColor=colors['tab_2'];
						style.scrollbarFaceColor=colors['tab_3'];
						style.scrollbarArrowColor=colors['tab_0'];
						style.scrollbarShadowColor=colors['tab_1'];
						style.scrollbarDarkShadowColor=colors['tab_1'];
						style.scrollbarBaseColor=colors['tab_3'];
					}
					break;
				case 'a':
					if(selectorText.match(/:hover/)!=null)
						style.color=colors['a_hover'];
					else 
						style.color=colors[tempText];
					//alert(tempText + "=" + style.color)
					break;
				case 'link2':
					if(selectorText.match(/:hover/)!=null)style.color=colors['link2_hover'];
					else style.color=colors[tempText];
					break;
				case 'link3':
					if(selectorText.match(/:hover/)!=null)style.color=colors['link3_hover'];
					else style.color=colors[tempText];
					break;
				case 'td':
					style.color=colors['font_color'];
					break;	

				case 'tab_img':
					//(new Image()).src = '/'+getContextPath()+'/image/skin/'+colors[tempText]+'/page_bg.gif';
					//style.backgroundImage='url(../image/skin/'+colors[tempText]+'/page_bg.gif)';
					break;
				
				case 'tab_title':
					(new Image()).src = '/'+getContextPath()+'/image/skin/'+colors[tempText]+'/title_head.gif';
					style.backgroundImage='url(../image/skin/'+colors[tempText]+'/title_head.gif)';
					break;
				case 'tab_title_bottom':
					(new Image()).src = '/'+getContextPath()+'/image/skin/'+colors[tempText]+'/title_bottom.gif';
					style.backgroundImage='url(../image/skin/'+colors[tempText]+'/title_bottom.gif)';
					//alert(style.backgroundImage);
					break;
				case 'tab_listing_head':
					(new Image()).src = '/'+getContextPath()+'/image/skin/'+colors[tempText]+'/tiao.gif';
					style.backgroundImage='url(../image/skin/'+colors[tempText]+'/tiao.gif)';
					break;
				case 'tab_listing_bottom':
					(new Image()).src = '/'+getContextPath()+'/image/skin/'+colors[tempText]+'/main_13.gif';
					style.backgroundImage='url(../image/skin/'+colors[tempText]+'/main_13.gif)';
					break;								
				/*case 'tab_selected_panel1':
					(new Image()).src = '/'+getContextPath()+'/image/skin/'+colors[tempText]+'/tab_01.gif';
					style.backgroundImage='url(../image/skin/'+colors[tempText]+'/tab_01.gif)';
					break;								
				case 'tab_selected_panel2':
					(new Image()).src = '/'+getContextPath()+'/image/skin/'+colors[tempText]+'/tab_02.gif';
					style.backgroundImage='url(../image/skin/'+colors[tempText]+'/tab_02.gif)';
					break;								
				case 'tab_selected_panel3':
					(new Image()).src = '/'+getContextPath()+'/image/skin/'+colors[tempText]+'/tab_03.gif';
					style.backgroundImage='url(../image/skin/'+colors[tempText]+'/tab_03.gif)';
					break;
				*/
				default:
					if(colors[tempText]) 
					{
						var str="'body','tab_bg','tab_0','tab_1','tab_2','tab_3','tab_5','button1'";
						var noStr = "'article_note','article_subnote'"
						if(str.indexOf(tempText)>=0)
							style.backgroundColor=colors[tempText];
						else if(noStr.indexOf(tempText)>=0)
							{}
						else 
						  style.color=colors[tempText];
						///alert(tempText)
					}
					break;
					}
				}
			}
	if(oldBody!=null) {
	document.body.filters[0].play();
	tempTimeout=setTimeout(function() {
				if(oldBody!=null) {
					//oldBody.className='';
					if(oldBody!=null) {
						oldBody.applyElement(newBody, "inside")
						oldBody.swapNode(newBody);
						oldBody.removeNode(true);
					}
					document.body.scrollLeft	= tempScroll[0];
					document.body.scrollTop		= tempScroll[1];
					}
				},1500);
	}
}
///����任
function update_size(temp)
{
	if(temp!=null){	
		for(i=0;i<document.styleSheets[0].rules.length;i++) {
		with(document.styleSheets[0].rules[i]) {
			var tempText=selectorText.toLowerCase().replace(/[^\.]*\./i,'').replace(/:\w*/i,'');			
			switch(tempText) {
				case 'td':style.fontSize=temp;break;
				default:break;
			}
		}
		}
	}
	//alert(temp);
}

///��ʽ���ʼ��
function initializeUI() { 
	if(get_cookie('ZsuSysStyle_cookie')!=0 && document.styleSheets && document.styleSheets[0].rules) {
		document.styleSheets[0].disabled=true;
		////update_size(get_cookie('ZsuSysSize_cookie'));
		update_style(get_cookie('ZsuSysStyle_cookie'));		
		document.styleSheets[0].disabled=false;			
	}
}
//������ʼ����ʽ
if(typeof MakeDefaultStyle == 'undefined')
	initializeUI();


///07-28 ����б����ڲ�ͬĿ¼�����µļ������⡣
function getHtcStyle()
{
	var str='';
	var url_path="\\"+getContextPath()+"\\";
	if(checkExplorer(6))//ie5��ͼƬ���·����ͬ
		url_path = "/"+getContextPath()+"/";
	//
	(new Image()).src = url_path+'image/blank.gif';
	(new Image()).src = url_path+'image/sortUp.gif';
	(new Image()).src = url_path+'image/sortDown.gif';
	(new Image()).src = url_path+'image/uparrow.gif';
	(new Image()).src = url_path+'image/downarrow.gif';
	(new Image()).src = url_path+'image/list_bg.gif';
	str+='<style>'+
		'.coolGrid{'+
		'behavior: url('+url_path+'script/coolGrid.htc);'+
		'sortNoneImageUrl: '+url_path+'image/blank.gif;'+
		'sortUpImageUrl: '+url_path+'image/sortUp.gif;'+
		'sortDownImageUrl: '+url_path+'image/sortDown.gif;'+
		'posUpImageUrl: '+url_path+'image/uparrow.gif;'+
		'posDownImageUrl: '+url_path+'image/downarrow.gif;'+
		//'transparentImageUrl: '+url_path+'image/transparent.gif;'+
		//'sortAscImageUrl: '+url_path+'image/sort_ascending.gif;'+
		//'sortDesImageUrl: '+url_path+'image/sort_descending.gif;'+
		//'fieldChooserImageUrl: '+url_path+'images/fieldchooser.gif;'+
		'table-layout: fixed;'+
		'position: relative;'+
		'margin: 0px;'+
		'width: 100%;'+
		'highlightBackgroundColor: #eeeeee;'+
		'highlightBorderColor: #cccccc;'+
    	'cursor: default;}'+
		'</style>';
	str += '<scr'+'ipt>'+
		'		if(top.document.getElementById("displaySelectLayer")) '+
		'			top.document.getElementById("displaySelectLayer").style.display="none";'+
		'	</scr'+'ipt>';
	return str;
}
//���css��.coolGrid�Ķ�̬·���������⡣
//document.write(getHtcStyle());


/*----------------------------------------------------
 * ���� ����ʹ��iframe ���� createpopup
 */
var SelectInputWin        = ''; 
var global_explorer_ie6   = false;
if(checkExplorer(5.5)){
	global_explorer_ie6   = true;
	SelectInputWin = window.createPopup(); //
}
try{
	if(typeof displaySelectLayer != 'object'){
		document.writeln('<iframe id=displaySelectLayer Author=heerit frameborder=0 style="position: absolute; width: 160px; height: 0px; z-index: 9998; display: none;" scrolling=yes></iframe>');
		//window.frames.displaySelectLayer.document.close();	
	}
}catch(e){}



/* ���õ����� ����
 * str Ҫ��ʾhtml 
 * pleft ��߾� ptop �ϱ߾� pwidth ��� pheight �߶�
 * flag �Ƿ����creatPopu
 */
function loadCommonPage(str,pleft,ptop,pwidth,pheight,obj,flag){
	var url = "/"+getContextPath()+"/";	
	var tabString = '';
	//-- the CSS of  Page 
	var pageMainColor  = "#dddddd";
	var pageLineColor  = "#666666";
	var pageClickColor = "#ffffff";
	if(typeof document.styleSheets[0].rules != 'undefined' && document.styleSheets[0].rules.length>40){			
		//pageLineColor=document.styleSheets[0].rules[26].style.backgroundColor;//
		pageLineColor=document.styleSheets[0].rules[26].style.backgroundColor;//
		pageMainColor=document.styleSheets[0].rules[29].style.backgroundColor;//	
	}
	tabString += "<html><head><title>loading</title><meta http-equiv=\"Content-Type\" content=\"text/html; charset=GBK\"><link href=\""+url+"css/style.css\" rel=\"stylesheet\" type=\"text/css\">";
	tabString += '<style>body{margin-left:1px;margin-top:1px;margin-right:1px;margin-bottom:0px}</style>';
	tabString += '<body style="overflow:hidden" oncontextmenu="self.event.returnValue=false">';
	tabString += str;
	tabString += '</body></html>';
	
	tabString = tabString.replace(/pageMainColor/gi,pageMainColor);
	tabString = tabString.replace(/pageLineColor/gi,pageLineColor);
	tabString = tabString.replace(/pageClickColor/gi,pageClickColor);

	if(global_explorer_ie6 && flag){
			SelectInputWin.document.body.style.border   = '0';
			SelectInputWin.document.writeln(tabString);
			SelectInputWin.document.close();
			SelectInputWin.show(pleft,ptop, pwidth, pheight,obj);
	}else{
		var pobj = document.getElementById("displaySelectLayer").style
		pobj.display = '';
		window.frames.displaySelectLayer.document.writeln(tabString)
		window.frames.displaySelectLayer.document.close();
		pobj.top    = ptop;
		pobj.left   = pleft;
		pobj.width  = pwidth;
		pobj.height = pheight;
	}
}

//��������¼�
/*function getMouseClick(){
	if(event.button == 1){
		var src = event.srcElement;	
		//alert(src.target +"|"+ src.onclick + "|"+src.parentElement.onclick)
		//if(src.target == '' || (src.onclick == null && src.parentElement.onclick == null )){
			//alert(src.onclick)
			//top.ClickLoading();
			//getClickLoading()
		//}
	}else if(event.button == 2 || event.button == 3)
		disMouseRightHand();
}*/

/* -------------------------------------
 * ������¼� ���ֽ�����
 */
 var loading_flag = -1;
 function ClickLoading(str,inum){
	 	var url = "/"+getContextPath()+"/";	
		str = (str + '' == 'undefined')?'':str;
		inum = ( inum + '' != 'undefined' )?inum:0.010;//0.025-->0.015
		var pleft,ptop, pwidth, pheight,load_flag;
		var tabString = '';			
		if(document.getElementById("displaySelectLayer").style.display == '')
			return;
		loading_flag ++ ;
		tabString += '<table width="398"  border="0" cellpadding="0" cellspacing="1" bordercolorlight="#c0c0c0"  bgcolor="#000000"><tr><td valign="top" bgcolor="#ffffff">'+
					 '<table width="100%" height="146" border="0" cellpadding="0" cellspacing="0" background="'+url+'image/logo/system/system_loading.gif">'+
					 '<tr>'+
						'<td height="92" valign="top" align="right"><img src="'+url+'image/logo/system/stop_loading.gif" onclick="top.closeLoading();if(parent.loading_flag)parent.history.back()" alt="ȡ�����ض���" style="cursor:hand">&nbsp;</td>'+
					 '</tr>'+
					 '<tr>'+
						'<td height="42"><table width="88%"  border="0" align="center" cellpadding="0" cellspacing="0">'+
						  '<tr>'+
							/*'<td>'+
			'<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,29,0" width="350" height="18">'+
            '<param name="movie" value="'+url+'image/logo/system/loading.swf">'+
            '<param name="quality" value="high">'+
            '<embed src="'+url+'image/logo/system/loading.swf" quality="high" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" ></embed>'+
			'</object>'+
							'</td>'+*/
							'<td><img src="'+golobal_image_path+'/loading.gif" height="18" name="imgId_Loading" alt="���ڼ�������......" style="background-color:#477DF5"></td>'+
						  '</tr>'+
						  '<tr>'+
							'<td height="24"><div id="txtId_Loading" style="font-size:12px">���Ժ����ڼ��� '+str+' ......</div></td>'+
						  '</tr>'+
						'</table></td>'+
					'</tr>'+
					'<tr>'+
						'<td></td>'+
					'</tr>'+
					'</table></td></tr></table>';
		tabString +='<scr'+'ipt type="text/javascript">';
		tabString +=' var loading;'+
					' function DisplayLoading(bwidth){'+
					'	if(parent.document.all.displaySelectLayer.style.display=="none"){'+
					'		clearTimeout(loading);return true;}'+
					'	var swidth = 358;'+
					'	if(bwidth<swidth *0.98)'+
					'		bwidth += (swidth - bwidth)*'+inum+';'+
					'	document.imgId_Loading.width = bwidth;'+
					'	if(bwidth > swidth - 12){'+
					'		parent.document.all.displaySelectLayer.style.display="none";return true;}'+
					'	if(bwidth > swidth - 15){'+
					'		document.all.txtId_Loading.innerHTML = "<font color=red>���� '+str+' ��ʱ��  ������......</font>";'+
					'	}'+
					'	loading = setTimeout("DisplayLoading("+bwidth+")",150);'+
					'	window.document.body.style.cursor = "wait";'+
					'	return false;'+
					' }'+
					' DisplayLoading(100)';		
		tabString +='</scr'+'ipt>';
		pleft   = top.document.body.offsetWidth/2 - 200;
		ptop    = top.document.body.offsetHeight/2 - 200;
		pwidth  = 400;
		pheight = 150;	
		loadCommonPage(tabString,pleft,ptop,pwidth,pheight);
		//confirm('���ݻ��ڼ����У���Ҫȡ��������')
 }

//-- ���� �ж��Ƿ����loading��������ھ� ���ٽ�����ȥ��������ֽ����� 
//@param str  ����������ʾ���ص���Ϣ�������ڼ��� XXXXX
//@param inum ���ý����������ٶȣ�Ĭ��0.015,����Խ���ٶ�Խ�죬��֮Խ����
function getClickLoading(str,inum){
	if(typeof top == 'object' && typeof top.ClickLoading == 'function' && top.document.getElementById("displaySelectLayer")){
		if(top.document.getElementById("displaySelectLayer").style.display == ''){
				self.event.returnValue=false;	
				return false;
		}		
		top.ClickLoading(str,inum)
	}else if(document.getElementById("displaySelectLayer")){
		if(document.getElementById("displaySelectLayer").style.display == ''){
				self.event.returnValue=false;
				return false;
		}		
		ClickLoading(str,inum)
	}
	return true;
}

//-- ���� �ж��Ƿ����loading�����û����ɾ� ���ٽ�����һ��.. 
function isFinisthLoading(){
	if(typeof top == 'object' && typeof top.ClickLoading == 'function'){
		if(top.document.getElementById("displaySelectLayer").style.display == ''){
				self.event.returnValue=false;	
				return false;
		}		
	}else{
		if(document.getElementById("displaySelectLayer").style.display == ''){
				self.event.returnValue=false;
				return false;
		}		
	}
	return true;
}

//-- ����ǿ�ƹرս�����.. 
function closeLoading(){
	if(typeof top == 'object'){
		top.document.getElementById("displaySelectLayer").style.display = 'none'
	}else{
		document.getElementById("displaySelectLayer").style.display = 'none'
	}
	return true;
}

/*----------------------------------
 * ����һ��¼� ���������˵� 
 * @param 1 ͼƬ·��
 * @param 2 �Զ��庯��/����
 * @param 3 ��ʾ���� 
 */
function RightMenu(img,fun,str,flag){
	this.image    = img;
	this.click    = fun;
	this.name     = str;
	this.invalid  = flag;
}
// �Զ����һ��˵�����
function setRightMenu(){
	var temp = new Array();
	//�ο�RightMenu����
	//temp[0] = new RightMenu('/'+getContextPath()+'/image/main/click_link.gif','alert(\'test!\')','����')
	return temp;	
}
/* 
 * �Ҽ��˵�������
 */
function disMouseRightHand(){
	//common
	var url = "/"+getContextPath()+"/";	
	var pleft,ptop, pwidth, pheight,load_flag;
	var tabString = '';
	//	
	var arr  = new Array();
	var temp = setRightMenu();//�Զ����ݼ��ӿ�
	var i   = 0;
	for(i=0;i<temp.length;i++)
		arr[i] = temp[i];

		arr[i++]= new RightMenu(url+'image/main/click_help.gif'   ,'parent.open(\''+url+'application/pages/help_default.htm\',\'_blank\',\'width=700px,height=500px,resizable=yes\')');
	if(i<3){ //�Զ�����Ϣ���������Ͳ���ʾ��������
		arr[i++]= new RightMenu(url+'image/main/click_goback.gif' ,'parent.history.back()');
		arr[i++]= new RightMenu(url+'image/main/click_forward.gif','parent.history.forward()');
		arr[i++]= new RightMenu(url+'image/main/click_flash.gif'  ,'parent.location.reload()');
		if(document.selection.type != 'None' && document.selection.createRange().text != ''){
			if((clipboardData.getData("Text")!=''||clipboardData.getData("URL")!='')&&(event.srcElement.nodeName == 'INPUT' || event.srcElement.nodeName == 'TEXTArEA'))
				arr[i++]= new RightMenu(url+'image/main/click_cut.gif'    ,'parent.document.execCommand(\'cut\',\'\',1);parent.SelectInputWin.hide()');
			arr[i++]= new RightMenu(url+'image/main/click_copy.gif'   ,'parent.document.execCommand(\'copy\',\'\',1);parent.SelectInputWin.hide()');
		}
		
		if((clipboardData.getData("Text")!=''||clipboardData.getData("URL")!='')&&(event.srcElement.nodeName == 'INPUT' || event.srcElement.nodeName == 'TEXTArEA')) 
			arr[i++]= new RightMenu(url+'image/main/click_v.gif'      ,'parent.document.execCommand(\'paste\',\'\',1);parent.SelectInputWin.hide()');		
		arr[i++]= new RightMenu(url+'image/main/click_print.gif'  ,'parent.print()');
		//arr[i++]= new RightMenu(url+'/image/main/click_see.gif'    ,'parent.location = \'view-source:'+escape(location.href)+'\'','�鿴Դ��'); //--Ϊ���Կ�������--
		arr[i++]= new RightMenu(url+'image/main/click_home.gif'   ,'showModalDialog(\''+url+'application/pages/system_infor.htm\',window, \'dialogWidth:520px;dialogHeight:340px;status:no;scroll:auto;help:no;\')');
	}

	tabString += '<table width="100%" bgcolor="pageLineColor" cellpadding="1" cellspacing="1" border="0">';
	tabString += '	<tr><td rowspan ="'+arr.length+'" width="26" valign="bottom">';
	tabString += '			 <table width="100%" height="'+(arr.length * 25>160?160:arr.length*25)+'" cellpadding="0" cellspacing="0" border="0"><tr><td background="'+url+'image/main/click_bg.gif"></td></tr></table>';
	tabString += '      </td> \n';
	
	for(var j=0;j<arr.length;j++){
		if(!arr[j])
			continue;
		if(j>0)
			tabString += '<tr>';
		var clickstr   = arr[j].click
		var displaystr = '';
		var dhand      = 'hand';
		if(arr[j].invalid){
			clickstr   = '';
			displaystr = 'style="FILTER: gray" alt="�÷������ڲ�����" '
			dhand      = 'default';
		}
		tabString += '<td height="25" bgcolor="pageMainColor" onMouseOver="this.bgColor=\'pageClickColor\'" onMouseOut="this.bgColor=\'pageMainColor\'"';
		tabString += '  style="cursor:'+dhand+'" onclick="'+clickstr+'" >&nbsp;';
		tabString += '	<img src="'+arr[j].image+'" align="absmiddle" '+displaystr+'> ';
		if(arr[j].name)
			tabString += arr[j].name;
		tabString += '</td></tr> \n';
	}		
	tabString += '</table>';

	//--��λ
	ptop   = event.screenY //event.clientY+document.body.scrollTop;
	pleft  = event.screenX //event.clientX+document.body.scrollLeft;
	pwidth = '150';
	pheight= arr.length * 26 + 3;

	loadCommonPage(tabString,pleft,ptop,pwidth,pheight,'',true);

	//var dbody = document.getElementsByTagName("body");
	//dbody[0].oncontextmenu = ClickFalse;
	self.event.returnValue=false
}
//--ȡ������¼�
function ClickFalse(){
	self.event.returnValue=false
}

//document.oncontextmenu = disMouseRightHand;

//ʵ�ֶ�ǰ���ַ�������GET����ĺ���
function heerEncodeURI(s){
	var reg = /^[\u0391-\uFFE5]+$/;
    var urlLength = s.length;
    var newS = "";
    for(var i = 0; i < urlLength; i++) {
        if(reg.test(s.charAt(i))) {
            newS += escape(s.charAt(i));
        }
        else {
            newS += s.charAt(i);
        }
    }
	if(newS.indexOf('&_heer_encoding_tag_=')== -1){
	   newS = newS + '&_heer_encoding_tag_=1';
	}
	return encodeURI(newS);
}