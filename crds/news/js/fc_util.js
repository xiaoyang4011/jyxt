
/* -- �����������ؼ�
 * -- ʹ�÷����ο�ʹ���ĵ�
 * -- 2005-02-23 ����������ʾ�����˵�����
 * -- 2005-02-03 ����ͬ�������õķ����ͽӿ� liuz & liuj
 * -- 2004-12-31 �����Զ������ݼ����id�ֶε�֧�� guanj --
 * -- 2004-12-11 �Ż���ѡ���ܣ��������
 * -- 2004-11-25 ʹ��createPopup��ʹ�ÿؼ����Ը��ڿ����
 * -- 2004-11-22 ���� �༶����
 * -- 2004-10-20 ���� Ժ/ϵ ����/���� ����
 * -- 2004-10-01 ������������obj_DataArray �����Ѿ�ȡ�������ݣ��´β����ظ��ӷ�����ȡ����
 * -- 2004-09-30 ���Ӷ�ѡ���� liuz --
 * -- 2005-02-17 zhoujf ��������ע��
 * -- 2005.5.17  ���Ӽ�� ����/����/���� ���ݺϷ� 
 **/

/*--ȫ�ֱ���--*/
var global_object_input;				// ��������
var global_object_hidden;				// ���ض���
var global_object_button;				// ��ť����
var global_selectListData = '';			// ��ת����
var global_selectArray    = '';			//�����ѡ��ֵ
var obj_DataArray         = new Object()// ���黺��--��һ�μ��غ��ظ�����
var global_InputContent   = '';

var SelectInputWin            = ''; 
var global_explorer_ie6   = false;
if(typeof checkExplorer != 'undefined' && checkExplorer(5.5)){
	global_explorer_ie6   = true;
	SelectInputWin = window.createPopup(); //
}

if(typeof displaySelectLayer != 'object'){
	document.writeln('<iframe id=displaySelectLayer Author=heerit frameborder=0 style="position: absolute; width: 160px; height: 0px; z-index: 9998; display: none;" scrolling=yes></iframe>');
	window.frames.displaySelectLayer.document.close();	
}

/*---����ؼ�����--���÷���-----------------------------------------------------------------------------*/

/** ������ʾ�����˵�����
 *  tabString			��ʾ������(string)
 *  temp_obj			����ؼ��������ڶ�λ 
 *  temp_btn			��ť����
 *  local_page_width    ��ʾ���
 *  local_page_height   ��ʾ�߶�
 *  isPageCount			�����Ƿ��й����������ø߶ȡ����	
 *  flag �Ƿ񲻲���creatpop
 */
function displaySelectMenu(tabString,temp_obj,temp_btn,local_page_width,local_page_height,isPageCount,flag){
	var ttop  = temp_obj.offsetTop;     //TT�ؼ��Ķ�λ���
	var thei  = temp_obj.clientHeight;  //TT�ؼ�����ĸ�
	var tleft = temp_obj.offsetLeft;    //TT�ؼ��Ķ�λ���
	var ttyp  = temp_btn.type;          //TT�ؼ�������
	while (temp_btn = temp_btn.offsetParent){ttop+=temp_btn.offsetTop; tleft+=temp_btn.offsetLeft;}
	
	var pageMainColor  = "#dddddd";
	var pageLineColor  = "#666666";
	var pageClickColor = "#ffffff";
	if(typeof document.styleSheets != 'undefined' && document.styleSheets[0].rules.length>40){		
		pageLineColor=document.styleSheets[0].rules[26].style.backgroundColor;//
		pageMainColor=document.styleSheets[0].rules[29].style.backgroundColor;//	
	}
	tabString = tabString.replace(/pageMainColor/gi,pageMainColor);
	tabString = tabString.replace(/pageLineColor/gi,pageLineColor);
	tabString = tabString.replace(/pageClickColor/gi,pageClickColor);

	if(!global_explorer_ie6 || flag){
		var dads  = document.all.displaySelectLayer.style;
		dads.top  = (ttyp=="image")? ttop+thei : ttop+thei+4;
		dads.left = tleft;
		dads.display = '';
		window.frames.displaySelectLayer.document.close()
		window.frames.displaySelectLayer.document.writeln(tabString)
		window.frames.displaySelectLayer.document.close();	
		document.all.displaySelectLayer.style.width = "160";
		if(isPageCount){
			var twidth  = window.frames.displaySelectLayer.document.all.tableId_selectList.clientWidth+2
			local_page_width  = (twidth > local_page_width)?twidth : local_page_width ;			
		}
		document.all.displaySelectLayer.style.width = local_page_width;
		if(isPageCount){
			var tempHeight = window.frames.displaySelectLayer.document.all.tableId_selectList.offsetHeight+2;
			local_page_height = local_page_height < tempHeight? tempHeight : local_page_height;	
		}	
		document.all.displaySelectLayer.style.height= local_page_height;			
	}else{		
		SelectInputWin.document.body.style.border   = '0';
		SelectInputWin.document.writeln(tabString);
		SelectInputWin.document.close();
		SelectInputWin.show(0,(temp_obj.clientHeight+3),local_page_width, local_page_height,temp_obj);

		var twidth  = SelectInputWin.document.all.tableId_selectList.clientWidth+2
		var theight = SelectInputWin.document.all.tableId_selectList.clientHeight+2
		local_page_width  = (twidth > local_page_width)?twidth : local_page_width ;			
		if(isPageCount){	
			SelectInputWin.show(0,(temp_obj.clientHeight+3), local_page_width, theight,temp_obj);
		}
	}
}

/**
 * ��ȡ�������鵯��������ʾ����
 * @param temp_obj  	�������ı����������, ����Name		
 * @param temp_btn  	�ı����ť��������		
 * @param optionInfos	�ӷ�����ȡ�û���ǰ�˹��������
 * @param nextPage      ��ҳ��ʼ��Ĭ��Ϊ0	
 ������չ��
 * @param arguments[4]  ���Id��hidden������
 * @param arguments[5]  ��ʾ������Ŀ��Ĭ��Ϊ10���ַ�ҳ����������Ϊ���ֹ�����
 * @param arguments[6]  ���ڶ�ѡ�У������Ѿ�ѡ�е��ַ������ԡ� ; ����������Ĭ��Ϊ�����this.value
 * @param arguments[7]  �Ƿ����Ρ��ÿա� Ĭ��Ϊfalse /  true �򲻳��֡��ÿա�
 */
function setSelectMenu(temp_obj,optionInfos,nextPage, temp_btn){
	if(isNaN(nextPage))
		return false;
	if('button3' == temp_obj.className) 
		return false;
	if(!optionInfos)
		return false;
	global_selectListData = optionInfos;	
	var th = temp_btn;	
	global_object_input = (arguments.length == 1) ? th : temp_obj
	global_object_button = (arguments.length == 1) ? null : th;	//�趨�ⲿ����İ�ť
	global_object_hidden = (arguments.length >= 5 && typeof arguments[4] == 'object') ? arguments[4] : null;
		
	var inteval     = 10;   //ÿҳ��
	var isPageCount = true; //�Ƿ��ҳ
	var resertBtn   = (arguments.length >= 8 )?true:false;
	//-- ���Ӳ���6,arg5 !=10 ����ʾ������Ϣ,���ֹ�����,�Զ����ڿ�� --����5Ԥ����system 2004-08-20 liuz
	var arg5        = (arguments.length >= 6)?parseInt(arguments[5]):10
	if(arg5 != 10)
		isPageCount = false;
	var selBox      = false; //�Ƿ��ѡ
	//-- ����7 ��ѡ,�����ѡ��string  2004-09-30 liuz
	var arg6        = (arguments.length == 7)?arguments[6]:'null';
	if(arg6 != 'null' ){
		selBox      = true;
		if(nextPage == 0){
			global_selectArray = arg6;
			if(global_object_hidden){
				global_selectArray = '';
				var hid_temp = global_object_hidden.value.split(';');
				var txt_temp = global_object_input.value.split(';');
					for(var i=0;i<hid_temp.length;i++)
						if(hid_temp[i]!='')
							global_selectArray += '**'+hid_temp[i]+'|||'+txt_temp[i];
			}else
				global_selectArray = global_selectArray.replace(/\;/gi,'**');
		}
	}			
	var local_page_width  = (temp_obj == th )? temp_obj.clientWidth+20 : temp_obj.clientWidth + th.clientWidth+5;
	var slen			  =  5  //ҳ����
	var shei			  =  25 //ҳ��߶�
	var local_page_scroll = 'hidden';
	var local_page_height =  210;
	var table_width       = '100%';   
	if(!isPageCount){		
		local_page_scroll = 'auto'
		table_width       = '100%'
	}
	
	var showNum   = 4;     //��ҳ
	var allCount  = optionInfos.length;
	var reg =/[\u4E00-\u9FA5]|[\uFE30-\uFFA0]/gi
	if(!isPageCount)
		inteval   = allCount 
	var pageCount = parseInt(allCount / inteval, 10) + 1;
	if(allCount % inteval == 0) 
		pageCount = pageCount -1 ;
	if(nextPage < 0)
		nextPage = 0;
	if(nextPage >= pageCount)
		nextPage = pageCount - 1;		
	var begin_list = nextPage * inteval;
	var end_list = begin_list + inteval;
	if(end_list > allCount)
		end_list = allCount;
	var tabTableString  = '';
	var tabSelectString = '';
	var tabHtmlString   = '<html><head><link href="style-2.css"/*tpa=http://career.buaa.edu.cn/website/script/'+get_filepath1()+'css/style.css*/ rel="stylesheet" type="text/css">';
	tabHtmlString += ' <meta http-equiv="Content-Type" content="text/html; charset=GBK">';
	tabHtmlString += '<style>body{margin-left:1px;margin-top:1px;margin-right:1px;margin-bottom:0px}';
	tabHtmlString += '</style></head><body bgColor="pageLineColor" style="overflow:hidden">';//style="overflow-x:hidden;overflow-y:"+local_page_scroll+"'
	
	tabTableString += '<table cellpadding="1" cellspacing="1" width="'+table_width+'" bgcolor="pageLineColor" id="tableId_selectList"> \n'; // 100% -> 160		
	if(0 != allCount){				
		for(var i = begin_list; i < end_list; i++){
			var filter_str = (optionInfos[i]+"").replace(/\<[^>]*>/gi,"");
			filter_str = escape(filter_str);
			var  dmName = global_Dm_Filter_Id(optionInfos[i])
			var  dmName1 = dmName.replace(/\<[^>]*>/gi,"");
			if(selBox){	
				if(!reg.test(dmName1))
					slen = (dmName1.length/2 +1 <= slen)?slen:(dmName1.length/2.5 +1)
				else
					slen = ((dmName1.length+2)<= slen)?slen:(dmName1.length+2)
				var txtvalue = ';'+temp_obj.value+';'; //��ѡ
				if(txtvalue.indexOf(';'+dmName+';')>=0){
					tabTableString += '<tr id='+i+' bgcolor="pageMainColor"><td  style="background-color:pageClickColor" id="td_class_'+i+'" onclick="parent.fc_setBoxCheck(getElementById(\'box_'+i+'\'),this)"  style="cursor:hand" height="22" >';
					tabTableString += '<input type="checkbox" name="box_item" value="'+optionInfos[i]+'" checked onclick="parent.fc_setBoxCheck(this,td_class_'+i+')" id="box_'+i+'">';
				}else{
					tabTableString += '<tr  bgcolor="pageMainColor"><td  id="td_class_'+i+'" onclick="parent.fc_setBoxCheck(getElementById(\'box_'+i+'\'),this)" style="cursor:hand" height="22">';//title="'+optionInfos[i]+'"
					tabTableString += '<input type="checkbox" name="box_item" value="'+optionInfos[i]+'"  onclick="parent.fc_setBoxCheck(this,td_class_'+i+')" id="box_'+i+'">';
				}
				tabTableString +=  dmName;
				tabTableString += '</td></tr>';
			}else{						
				if(!reg.test(dmName1))
					slen = (dmName1.length/2 <= slen)?slen:(dmName1.length/2.5)
				else						
					slen = (dmName1.length <= slen)?slen:(dmName1.length)
				if(temp_obj.value == dmName)
					tabTableString += "<tr id="+i+" height=\"22\" bgcolor='pageClickColor' ";
				else
					tabTableString += "<tr id="+i+" height=\"22\" bgcolor= 'pageMainColor' onMouseOver=\"this.bgColor=\'pageClickColor\'\" onMouseOut=\"this.bgColor='pageMainColor'\"";
				tabTableString += " style=\"cursor:hand;\">";
				tabTableString += "<td onclick=\"parent.getSelectValue(\'"+filter_str+"\',document.getElementById('spanId_"+i+"').innerText);\">&nbsp;<span id='spanId_"+i+"'>"+dmName +"</span><\/td>";
				tabTableString+="</tr> \n ";
			}
		}
		shei = (end_list - begin_list)*23 + 28
	}		
	tabSelectString += "<tr id="+i+" height=\"22\" bgcolor='pageClickColor' align='right'>";	
	tabSelectString += "	<td style=\"cursor:hand;\" title=\"��������ÿ�\" class='article_copyright'>";//<img src='"+get_filepath1()+"image/shenhe_wei1.gif' align='absmiddle' border=0> <img src='"+get_filepath1()+"image/shenhe_no.gif' align='absmiddle' border=0>
	if(selBox)
		tabSelectString +=   "<a href='#' onclick='parent.getSelectValue(parent.global_selectArray)'>ѡȡ</a> ";
	if(!resertBtn)
		tabSelectString +=   "<a href='#' onclick='parent.getSelectValue(\"\")'>�ÿ�</a> ";
	if(local_page_width > 100)
		tabSelectString +=   "<a href='#' onclick='parent.closeSelectMenu()'>�ر�</a>&nbsp;";
	tabSelectString +="<\/td></tr>";
	
	var tabString = '';	
	if(pageCount > 1){
		tabString+="<tr  bgcolor='pageClickColor'><td align='right' height=\"22\">";
		tabString+="<img src='"+get_filepath1()+"image/main/scroll_left.gif' onClick='parent.setSelectMenu(parent.global_object_input,parent.global_selectListData," + (nextPage - 2*showNum ) + ", parent.global_object_button,parent.global_object_hidden,"+arg5+",\""+arg6+"\");' style=\"cursor:hand;\" align=\"absmiddle\">";
		var beginPage=nextPage - showNum;
		var maxPage = nextPage + showNum
		if(beginPage <= 0){					
			maxPage -= beginPage;
			beginPage = 0;
		}
		if(maxPage > pageCount){					  
			beginPage-=maxPage-pageCount						
			maxPage = pageCount;
		}
		if(beginPage < 0)
			beginPage=0		
		for(var i = beginPage; i < maxPage; i++ ){
			if(i == nextPage)
				tabString+="&nbsp;<a href='#' onclick='parent.setSelectMenu(parent.global_object_input,parent.global_selectListData," + i + ", parent.global_object_button,parent.global_object_hidden,"+arg5+",\""+arg6+"\")'><font color=red>" + (i + 1) + "</font></a>";
			else
				tabString+="&nbsp;<a href='#' onclick='parent.setSelectMenu(parent.global_object_input,parent.global_selectListData," + i + ", parent.global_object_button,parent.global_object_hidden,"+arg5+",\""+arg6+"\")'>" + (i + 1) + "</a>";
		}				
		var temp_nextPage=nextPage+showNum*2 //(parseInt(nextPage))<!---->
		if(temp_nextPage>pageCount) temp_nextPage=pageCount;
		if(temp_nextPage<0)         temp_nextPage=0;
		tabString+="&nbsp;<input type='text' value='"+temp_nextPage+"' name='txt_page_num' style='width:20px'><img src='"+get_filepath1()+"image/main/scroll_right.gif' onClick='parent.setSelectMenu(parent.global_object_input,parent.global_selectListData,txt_page_num.value-1, parent.global_object_button,parent.global_object_hidden,"+arg5+",\""+arg6+"\")' style=\"cursor:hand;\" align=\"absmiddle\">";
		tabString+="</td>";	
		tabString+="</tr>";
		shei += 22
	}	
	
	if(!isPageCount){
			local_page_height = (shei < local_page_height)?shei:local_page_height;	
			tabString = tabHtmlString + '<div style="overflow-y:auto;height:'+(local_page_height-26)+'px">'+tabTableString+
					          '</table></div><table cellpadding=\"1\" cellspacing=\"1\" width=\"'+table_width+'\" bgcolor="pageLineColor">'+tabSelectString+tabString;
	}else{			
		tabString = tabHtmlString + tabTableString + tabSelectString+tabString;
	  local_page_height = shei;
	}
	tabString+="</table></body></html>"

	local_page_width  = (local_page_width >(slen * 12 + 20))?local_page_width : (slen * 12 + 20);
	if(local_page_width>400){
		local_page_width=400;
	}
	displaySelectMenu(tabString,temp_obj,temp_btn,local_page_width,local_page_height,isPageCount)
}

/**����ѡȡ��ֵ
 * @param content ���������
 */
function getSelectValue(content,dmstring){
	content = unescape(content);
	content = content.indexOf("###")>0?content.substring(0,content.indexOf("###")):content;
	if(content != ""){	
		if(-1 == content.indexOf("|||")){
			//content = dmstring;
			if(global_selectArray){
				content = content.replace(/\*\*/gi,';');
				while(content.indexOf(';;')>=0)
					content = content.replace(/\;\;/gi,';');
				while(content.indexOf(';')==0)
					content = content.substr(1);
				while(content.lastIndexOf(';')==content.length -1)
					content = content.substr(0,content.length-1);
			}
			fc_onSelect(global_object_input,content);
			global_object_input.value = content;
		}else{
			var arrayTemp = content.split("|||");
			var dmId   = '';
			var dmName = '';
			if(content.indexOf('**')>=0){ //��ѡ��ֵ
				var temp = content.split('**');
				for(var i=0;i<temp.length;i++){
					if(temp[i].trim() != ''){
						var data = temp[i].split('|||');						
						if(data.length >1){
							dmId   += data[0]+';';
							dmName += data[1]+';';		
						}else
							dmName += data[0]+';';
					}else
					{}
				}
				if(dmName.lastIndexOf(';') == dmName.length -1){
					dmId   = dmId.substr(0,dmId.length-1);
					dmName = dmName.substr(0,dmName.length-1)
				}
			}else{
				dmId   = arrayTemp[0];
				dmName = dmstring;//arrayTemp[1]
			}
			global_InputContent = dmName;
			if(global_object_hidden){//���û�иı�ֵ�����ټ���				
				if(global_object_hidden.value == dmId && global_object_input.value == dmName) 
					return closeSelectMenu();
				else
					global_object_hidden.value = dmId;
			}else{
				if(global_object_input.value == dmName)
					return closeSelectMenu();
			}
			global_object_input.value = dmName;	
			fc_onSelect(global_object_input,content);
			if(!global_InputContent){
				global_object_hidden.value = '';
				global_object_input.value = '';	
			}
		}
		global_object_input.select();
		
	}else{
		global_object_input.value = '' ;
		if(global_object_hidden)
			global_object_hidden.value = '';
		fc_onSelect(global_object_input,content);
	}
	closeSelectMenu();
}

/*-- ����ѡ�е�ֵ 
 * �ع��÷���,����ʹ���� �����й��������
 * obj ѡ���������� 
 * str ѡ�з���ֵ��������ѡ��
 */
function fc_onSelect(obj,str){}

//--�����Ĺر�
function closeSelectMenu(){	
	global_selectArray = '' ;//��ն�ѡֵ
	if(global_explorer_ie6)
		SelectInputWin.hide();
	else
		document.all.displaySelectLayer.style.display="none";
}

//--ͼƬ·��
function get_filepath1(){
	return "/" + getContextPath() + "/";
}

//--����xmlhttpȡ���ݵĴ��� �� ID ��Ӧ
function getArrayMap(){
	var arrayMapping = new Array();	
	arrayMapping.add(new codeMapping('xb',   20001));//�Ա�
	arrayMapping.add(new codeMapping('mz',   20002));//����
	arrayMapping.add(new codeMapping('xl',   10013));//10046));ѧ��
	arrayMapping.add(new codeMapping('jg',   30012));//����
	arrayMapping.add(new codeMapping('hyzk', 20003));//����״��
	arrayMapping.add(new codeMapping('zzmm', 20011));//������ò
	arrayMapping.add(new codeMapping('gj',   30009));//����
	arrayMapping.add(new codeMapping('bdlb', 10023));//�䶯���
	arrayMapping.add(new codeMapping('bdyy', 10024));//�䶯ԭ��
	//arrayMapping.add(new codeMapping('xueqi',96013));//ѧ��
	arrayMapping.add(new codeMapping('dz',   96008));//���� ���� ����
	//arrayMapping.add(new codeMapping('xy', 6));    //ѧԺ
	//arrayMapping.add(new codeMapping('zy', 0));	   //רҵ
	arrayMapping.add(new codeMapping('xq',   30011)) //У��
	/** heercareer */
	arrayMapping.add(new codeMapping('jjlx',80005)); //��������
	arrayMapping.add(new codeMapping('DWHY',80006)); //��λ��ҵ
	arrayMapping.add(new codeMapping('ZWMC',20025)); //ְλ
	arrayMapping.add(new codeMapping('YY',  20074)); //����
	arrayMapping.add(new codeMapping('kclb',10036)); //�γ����
	//dug add on 2006-03-07
	arrayMapping.add(new codeMapping('jzglb',20049)); //��ְ�����
	arrayMapping.add(new codeMapping('zc',200250001)); //��ְ��ְ��
	//�Զ������
	//arrayMapping.add(new codeMapping('kclb',923000009)); //�γ����
	arrayMapping.add(new codeMapping('xueqi',923000015));//ѧ��
	arrayMapping.add(new codeMapping('pjzblx',93800)); //����ָ������
	arrayMapping.add(new codeMapping('pjzbys',93801)); //����ָ��Ҫ��
	return arrayMapping ;
}

//--�Ӻ�˻�ȡ����
function getXmlhttpString(itemName,level,flag){
	var kcmlData = '';	
	if(obj_DataArray && obj_DataArray[itemName])
		kcmlData = obj_DataArray[itemName];
	else{
		var fatherId;
		if(!isNaN(itemName) || 'xy' == itemName || 'zy' == itemName)
			fatherId = itemName;
		else
			fatherId = getFatherId(getArrayMap(), itemName);		
		if(-1 == fatherId){
			alert('û����ѡ�����Ŀ');
			return '';
		}	
		if(flag)
			var url = getURL(fatherId, level); 
		else
			var url = getURL(fatherId, level, 'withId');
		if(false == url)
			return;
		else{
			var allContent = httpGet(url);
			//if("".trim() == allContent.trim()){
				//alert('û���ҵ������ҵ���Ŀ!');
				//return '';
			//}
			kcmlData = getArray(allContent);			
		}
		obj_DataArray[itemName]=kcmlData;
	}
	return kcmlData;
}

/** �����ֶ�
* @param src �ַ���
* @param num ˳���
*/
function global_Dm_Filter_Id(src,num){
	var ret = '';	
	src = src + "";
	if(-1 == src.indexOf("|||"))
		ret = src.trim();
	else{
		var i   = (typeof num != 'undefined')?num:1;
		var temp = src.split("|||");
		ret = temp[i];
	}
	return ret;
}

/** ���Level�Ƿ���ȷ */
function checkLevel(level){	
	var intLevel = parseInt(level, 10);	
	var arrayLevel = new Array();
	arrayLevel.add(0);
	arrayLevel.add(1);
	arrayLevel.add(2);
	arrayLevel.add(3);
	arrayLevel.add(4);
	arrayLevel.add(5);
	arrayLevel.add(6);
	var isValid = true;
	if(-1 == arrayLevel.indexOf(intLevel))
		isValid = false;
	return isValid;
}

/***
 * �õ������͵�url
 * @param fatherId   ��ID
 */
function getURL(fatherId, level){	
	var baseURL = "/" + getContextPath() + "/getdata";
	var url = baseURL + '?table=dmcode&fatherId=' + fatherId + '&level='+ level + '&time=' + new Date().getTime();
	if('xy' == fatherId)
		url = baseURL +'?table=department' + '&level='+ level + '&time=' + new Date().getTime();
	else if('zy' == fatherId)
		url = baseURL +'?table=major'+ '&level='+ level + '&time=' + new Date().getTime();	
	if(arguments[2])
		url += '&withId=true';
	return url;	
}

/** ��ȡͬ�����ݵ�url
 *  add liuz from liuj
 *  @param tablename   ���ݱ�����
 *  @param fieldname   �ֶ�����
 *  return url ��ַ
 */
function getSyncURL(tablename,fieldname){	
	var baseURL = "/" + getContextPath() + "/getSyncData";
	var url = baseURL + '?tablename=' + tablename + '&fieldname='+ fieldname + '&time=' + new Date().getTime();	 
	return url;	
}

/** Ժϵ���� �Ĺ���xmlhttp·��
 *  hidname Ϊ jlxy,jlxs,jlzy ���ڿ�����չΪ�Զ�������ݼ���־��
 *  2004-10-14  Liuz
 */
function getXmlhttpMajor(hidname){
	var kcmlData = '';
	if(obj_DataArray && obj_DataArray[hidname])
		kcmlData = obj_DataArray[hidname];
	else{
		var url = '/'+getContextPath() +'/getdata?table=';
			url += hidname +'&withId=1&fathid=';
		var str = httpGet(url)
		kcmlData = getArray(str);
		obj_DataArray[hidname] = kcmlData;
	}	
	return kcmlData ;
}

/** ����ֱ�Ӵ����ݱ���ĳ���ֶλ�ȡ����
 * @param tabname �������
 * @param colname �ֶ�����
 * return Array
 */
function getXmlhttpTable(tabname,colname){
	var kcmlData = '';
	if(obj_DataArray && obj_DataArray[tabname])
		kcmlData = obj_DataArray[tabname];
	else{
		var url = '/'+getContextPath() +'/getdata?table=';
			url += tabname +'&column='+colname;
		var str = httpGet(url)
		if(str =='')
			return '';
		kcmlData = getArray(str);
		obj_DataArray[tabname] = kcmlData;
	}	
	return kcmlData ;
}

/** ����ֱ�Ӵ����ݱ���ĳ���ֶλ�ȡ����
 * @param tabname �������
 * @param colname �ֶ�����
 * @param colid   id����
 * return Array
 */
function getXmlhttpTableWithId(tabname,colname,colid){
	var kcmlData = '';
	if(obj_DataArray && obj_DataArray[tabname])
		kcmlData = obj_DataArray[tabname];
	else{
		var url = '/'+getContextPath() +'/getdata?table=';
			url += tabname +'&column='+colname+'&colid='+colid;
		var str = httpGet(url)
		if(str =='')
			return '';
		kcmlData = getArray(str);
		obj_DataArray[tabname] = kcmlData;
	}	
	return kcmlData ;
}

/** ���ظ�ID
 * @param arrayMapping ����ӳ������
 * @param itemName ����
 * return fatherId
 */
function getFatherId(arrayMapping, itemName){
	var arrayItem = new Array();
	for(var i = 0; i < arrayMapping.length; i++){
		arrayItem[i] = arrayMapping[i].itemName;
	}
	var pos = arrayItem.indexOf(itemName);	
	if(-1 == pos)
		return -1;
	else
		return arrayMapping[pos].fatherId;
}

/** �������������ؼ���
 * @param keyWord ����Ĺؼ���
 * return pos
 */
function Array.prototype.indexOf(keyWord) {
   var pos = -1;
   for(var i = 0; i < this.length; i++){
      if(keyWord == this[i]){
         pos = i;
         break;
      }
   }
   return pos;
}

/** ����������������
 * @param content ����
 */
function Array.prototype.add(content){
	this[this.length] = content;
}

/** �ַ�������
 */
function String.prototype.trim(){
   return this.replace(/(^(\s|\u3000)*)|((\s|\u3000)*$)/g, '');
}

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

//--ͨ��URL���xml����
function httpGet(url){
   var content = "";
   try{   
	 var xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
	  xmlhttp.open("POST", url, false);
      //xmlhttp.setRequestHeader( null , cookie );
      xmlhttp.send();
      content = xmlhttp.responseText.trim();
	 /** �����֤
	  *  �����ҽ�������com.heer.common.codeservice.CodeServiceImpl()��getFullNames()����throws CodeLenthNotExistException
	  */
	 if("ERRORS_HAPPEND!!!".toString() == content.toString()){
		alert('�Բ���, Level ����>=0 <=6!!!');
		content = "";
	  }
   }catch(e){   
      fc_debug("�ӷ������˻�����ݵĹ����г��ִ���" + e);
   }  
   return content;
}

/** ���ز�ֺ��ַ�������
 * @param allContent ԭʼ�ַ�������
 * return retArray
 */
function getArray(allContent){
	var retArray = new Array()
	var SEPERATOR = ",";
	try{
		//�����ַ�����
		allContent = (allContent+"").replace(/&amp;40;/gi,"(");
		allContent = allContent.replace(/&amp;41;/gi,")");
		allContent = allContent.replace(/&amp;/gi,"&");   		
		if (allContent.indexOf(',,,')>=0){
			SEPERATOR = ",,,"
			retArray = allContent.split(SEPERATOR);
		}else{
			retArray[0] = allContent  ;
		}  
	}catch(e){
		fc_debug("����ַ�������" + e);
	}
	return retArray;	
}

//���¸�ӳ�丳ֵ
function codeMapping(itemName, fatherId){
	this.itemName = itemName;
	this.fatherId = fatherId;
}

/** �ؼ��Ĳ�ѯ���ܣ���ƥ����ú�ɫ��־*/
function fc_queryInput(kcmlData,obj){
	var optionsData = '';
	if(event.srcElement.type != "text" || obj.value =='') {
		optionsData = kcmlData;
	}else{
		var j = 0;
		var findData = new Array();
		for(var i = 0; i < kcmlData.length; i++) {
			var temp =(kcmlData[i]+'').split('|||');
			var str = (temp.length >1)?temp[1].toString():temp[0].toString(); 
			str = str.replace(/\<[^>]*>/gi,""); //������<>���˵�
			str = str.replace(/&amp;40;/gi,"(");
			str = str.replace(/&amp;41;/gi,")");
			//str = str.replace(/&amp;/gi,"&");
			str = str.replace(/&{1}[a-z]{1,};{1}/gi,"");//����html�����ַ�
			if(str.indexOf(obj.value) >= 0) {
				var showData = str;
				showData = showData.replace(obj.value, '<font color = red>' + obj.value + '</font>');	
				showData = kcmlData[i].replace(str,showData)
				findData[j] = showData;
				j++;
			}
		}
		optionsData = findData;
	}
	return optionsData;
}

/*  ��ʾ�ؼ���ѡ����
 *  2004-11-10 update Liuz  
 *  2004-09-30 Add    Liuz
 */
//--ѡ���Ǳ任 �� �ı�ֵ
function fc_setBoxCheck(ftm,tdobj){
	if(ftm.checked){
		tdobj.style.backgroundColor = '';
		ftm.checked     = false;
		fc_delSelectItems(ftm.value);
	}else{
		tdobj.style.backgroundColor = '#ffffff';
		ftm.checked     = true;		
		fc_addSelectItems(ftm.value);
	}
}
//--������ֵ
function fc_addSelectItems(str){
	var flag  = true;
	var temp = global_selectArray.split('**');
	for(var i=0;i<temp.length;i++){
		if(temp[i]&&str.indexOf(temp[i])>=0){
			flag =false;break;
		}
	}
	if(flag)
		global_selectArray  += '**'+str;
}
//--����ֵ
function fc_delSelectItems(str){
	var temp = global_selectArray.split('**');
	for(var i=0;i<temp.length;i++){
		if(temp[i]&&str.indexOf(temp[i])>=0){
			global_selectArray = global_selectArray.replace(temp[i],"");
			break;
		}
	}
}


/*-----------------------
 * 2004-08-05 ����¼�
 * ������ʱ�رոÿؼ�	
 * ie6�����������������л����㴦�����
 ------------------------*/
function document.onclick(){
  with(window.event){  
	 if (typeof global_date_input == 'object'){
		 if (srcElement.getAttribute("Author")==null && srcElement != global_date_input && srcElement != global_date_button)
			document.all.meizzDateLayer.style.display="none";  //�ر�ʱ��ؼ�
	 }
  	 if (typeof global_object_input == 'object'){
	 	 if(srcElement !=global_object_input && srcElement !=global_object_button &&displaySelectLayer )
			document.all.displaySelectLayer.style.display="none";//�ر� input+select �ؼ�
	 }
  }
}

//--����alert����������
function fc_debug( text ){
   alert( text );
}

/*---������������ؼ� �ṩ���з��� �ӿ�------------------------------------------------------------------*/
/**
 * ȡ�ù��ô���������, ֻ����Id��Name
 * @param btn  			�ı����ť����
 * @param obj  			�������ı�������, ����Name
 * @param itemName		��ȡ�õĴ�������, ��ֱ�Ӵ���father_id ���� arrayMapping �����е�����
 * @param level			���ز���, ��ȡ�õĴ������, �������, Ĭ��Ϊȡ�����в���
 * @param arg[4]        =this.value,���ڶ�ѡ�Ļ�ѡ����
 * @param arg[5]        �Ƿ����Ρ��ÿա���ť Ĭ��false(Ϊ��) / true ������ 
 * @return				setҳ���е��ı���
 */
function getOptions(btn, obj, itemName) {
	var kcmlData;
	var optionsData;  //��ת����		
	var level = 0;/**  �������� */
	
	if(arguments[3]){
			level = arguments[3];
			if(!checkLevel(level)){
				alert('�Բ���, Level ����>=0 <=6!!!');
				return;
			}
	}
	//--Ϊ������ǰ�汾 ��xy,xs,zyʹ�ü����ӿ�
	//if(itemName == 'xy' || itemName == 'xs' || itemName == 'zy'){
	//	getOptionsDefined(btn,obj,null,'jl'+itemName)
	//	return;
	//}
	//--ͳһ��ȡ���ݽӿ�
	kcmlData = getXmlhttpString(itemName,level,true)

	//--query the string----
	if(btn != obj || event.srcElement.type != "text" || obj.value =='' || arguments.length == 5) {
		optionsData = kcmlData;
	}else {
		optionsData = fc_queryInput(kcmlData,obj)
	}	
	
	if(arguments.length == 5) 
		setSelectMenu(obj,optionsData,0, btn,null,10,arguments[4])
	else if(arguments.length == 6)
		setSelectMenu(obj,optionsData,0, btn,null,10,'',arguments[5])
	else
		setSelectMenu(obj,optionsData,0, btn)
}

/*-- ������ݺϷ� --*/
/* @params txtname  ���������� 
 * @params codename ��Ӧ��������� 'jlxy','jg'...
 */
function checkGetOptionValue(txtname,codename){
	return checkTextSelected(codename,txtname);
}

/* �ı�ѡ��
 * @params item ��Ӧ���������
 * @params txt     ������ֵ
 */
function checkTextSelected(item,txt){
	if(checkOptionsWithIdValue(document.all(txt),'',item))
		return true;
	else{
		alert('����� " '+txt+' " ���������ݿ��в����ڣ�\n ��ѡ���ʺϵ����ݡ�');
		getOptionsDefined(obj,obj,'',item)
		return false;
	}
}

/** ������Ϸ��� 2005-04-14  �Ľ����Ϸ���ֱ��ʹ����չ������
 *  obj   ��������
 *  hid   ���������
 *  item  ��������:jg,xb ���ߴ���
 */
function checkOptionsWithIdValue(obj,hid,item){
	if(obj.value != ''){
		if(! obj_DataArray || ! obj_DataArray[item])
			//getOptions(obj,obj,txtname);
			getOptionsDefined(obj,obj,hid,item)
		for(var i = 0; i < obj_DataArray[item].length; i++) {
			var temp = obj_DataArray[item][i].split("|||");
			if(hid && temp.length>1 && temp[1]==obj.value && temp[0]==hid.value)
				return true;
			if(!hid && temp.length>1 && temp[1] == obj.value)
				return true;
			else if(temp.length == 1 && temp[0]==obj.value)
				return true;
		}
	}
	return false;
}

/**
 * ȡ�ù��ô���������, ����Id��Name
 * @param btn  			�ı����ť����
 * @param obj  			�������ı�������, ����Name
 * @param hiddenObj		������hidden������, ����Id
 * @param itemName		��ȡ�õĴ�������, ��ֱ�Ӵ���father_id ���� arrayMapping �����е�����
 * @param level			���ز���, ��ȡ�õĴ������, �������, Ĭ��Ϊȡ�����в���
 * @return				setҳ���е��ı����hidden��
 */
function getOptionsWithId(btn, obj, hiddenObj, itemName) {
	var kcmlData;
	var optionsData;  //��ת����	
	var level   = arguments[4]?arguments[4]:0;	//��������	
	if(arguments[4]){
		level = arguments[4];
		if(!checkLevel(level)){
			alert('�Բ���, Level ����>=0 <=6!!!');
			return;
		}
	}					
	kcmlData = getXmlhttpString(itemName,level);
	optionsData = fc_queryInput(kcmlData,obj);

	if(arguments.length == 6) 
		setSelectMenu(obj,optionsData,0, btn,hiddenObj,10,arguments[5])
	else if(arguments.length == 7)
		setSelectMenu(obj,optionsData,0, btn,hiddenObj,arguments[6])
	else
		setSelectMenu(obj,optionsData,0, btn,hiddenObj)
}


/* ���켶������Ҫ���� ����/����/���� ����������
 * ԭ��һ��ȡ�����ݣ���ǰ�˷��빹���游�������ӽӵ����飬��������ʾ
 * 2004-10-19 liuz
 * @param btn  			�ı����ť����
 * @param obj  			�������ı�������, ����Name
 * @param hiddenObj		������hidden������, ����Id
 * @param itemName		��ȡ�õĴ�����������, �޶�Ϊ arrayMapping �����е�����
 * @param level         ����λ�ã�1Ϊ�游 2Ϊ���� 3Ϊ����
 * @param fathid		���ӵ�id   (string ע�⵱��"���ڵ��ʼ��"��fathid���ʱ��ҪΪobject)
 * @param granid		�游�ӵ�id (string )
 * @param flag          �Ƿ����ǿ���� (Ĭ����false)
 * @param fathname      Ϊ���и��ڵ��ʼ�������常�ڵ�name��
 * @param fathjlname    Ϊ���и��ڵ��ʼ�������常���ݼ�����
 */
function fc_getLinkOptions(btn,obj,hiddenObj,itemName){
	var kcmlData;
	var optionsData;  //��ת����
	var level   = arguments[4]?arguments[4]:0;
	var fathid  = arguments[5]?arguments[5]:'';		
	var granid  = arguments[6]?arguments[6]:0;
	var flag    = arguments[7]?arguments[7]:false;
	var fathname= arguments[8]?arguments[8]:null;
	var fathjlname= arguments[9]?arguments[9]:'';

	kcmlData = getXmlhttpString(itemName,0);
	if(kcmlData == '')
		return;
	//�Ը��ڵ����fathname����ȡfathid
	if(fathname && fathname.value != ''){
		initOptionsValue(fathname,fathid,getXmlhttpString(fathjlname,0));
	}
	var	fathvalue  = typeof fathid == 'object' ? fathid.value : fathid;
	var string_arr = kcmlData;
	var fath_array = new Array();
	var flen = 0;
	var gran_array = new Array();
	var glen = 0;
	var self_array = new Array();
	var slen = 0;
	string_arr = fc_queryInput(string_arr,obj)
	for(var i=0;i<string_arr.length;i++){
		var node_arr = string_arr[i].split('|||');
		if( node_arr[0].length==9){	
			gran_array[flen++] = string_arr[i];
		}else
		if( node_arr[0].length==13){			
			fath_array[glen++] = string_arr[i];
		}else
		if( node_arr[0].length==17){				
			self_array[slen++] = string_arr[i];
		}
	}
	switch(level){
		case 1:
			kcmlData = gran_array;
			break;
		case 2:
			kcmlData = fc_setSelectColor(fath_array,fathvalue,'',flag);			
			break;
		case 3:
			kcmlData = fc_setSelectColor(self_array,fathvalue,granid,flag);
			break;
		default:
			kcmlData = string_arr;
			break;
	}
	optionsData = kcmlData
	setSelectMenu(obj,optionsData,0, btn, hiddenObj)
}

/*--��������--*/
function fc_setSelectColor(self_array,fathid,granid,flag){
	var gran_str = new Array();
	var fath_str = new Array();
	var self_str = new Array();
	var glen = 0;
	var flen = 0;
	var slen = 0;	
	for(var j=0;j<self_array.length;j++){
		var temp = self_array[j].split('|||');
		if(fathid && temp[0].indexOf(fathid)>=0)
			fath_str[flen++] = self_array[j];
		else if(!flag && granid && temp[0].indexOf(granid)>=0)
			gran_str[glen++] = self_array[j].replace(temp[1],'<font color = #444444>' + temp[1] + '</font>');
		else if(!flag)
			self_str[slen++] = self_array[j].replace(temp[1],'<font color = #888888>' + temp[1] + '</font>');;
	}
	for(var i=0;i<gran_str.length;i++)
		fath_str[flen++] = gran_str[i];				
	for(var i=0;i<self_str.length;i++)
		fath_str[flen++] = self_str[i];
	return fath_str;
}


/*--����Ӽ�--*/
function fc_getLinkClear(txt1,hidd1){
	if(document.all(txt1))
		document.all(txt1).value  = '';
	if(document.all(hidd1))
		document.all(hidd1).value = '';
}

/*--�����ı�--
 *
 * @param itemName		��ȡ�õĴ����������ƣ�ע��fatherid���У�, �޶�Ϊ arrayMapping �����е�����
 * @param hiddObj		���ӵ㱣��Id��hidden�������, 
 * @param ftxt			���ӵ㱣�����Ƶ��ı������
 * @param fhidd			���ӵ㱣��ID��hidden�����
 */
function fc_LinkChangeFather(itemName,hiddobj,ftxt,fhidd){
	if(hiddobj.value.indexOf(fhidd.value)<0 || fhidd.value == ''){
		var str ='';
		if(hiddobj.value.length == 17)
			str = hiddobj.value.substr(0,13);
		else
			str = hiddobj.value.substr(0,9);
		if(!obj_DataArray[itemName])
			obj_DataArray[itemName] = getXmlhttpString(itemName,0);
		var data_arr = obj_DataArray[itemName] ;
		for(var i=0;i<data_arr.length;i++){
			var temp = data_arr[i].split('|||');
			if(temp[0] == str){
				ftxt.value  = temp[1];
				fhidd.value = temp[0];
				break;
			}
		}
	}
}

//--��� ����/����/���� ���ݺϷ�(�ɵ�)
//function fc_LinkCheckInput(itemName,str)
//{
//	if(str == '' ) 
//		return true;
//	if(!obj_DataArray[itemName])
//		 obj_DataArray[itemName] = getXmlhttpString(itemName,0);
//	var data_arr = obj_DataArray[itemName] ;
//	for(var i=0;i<data_arr.length;i++){
//		var temp = data_arr[i].split('|||');
//		if(temp[1] == str){
//			return true;
//			break;
//		}
//	}
//	return false;
//}

/** 10-14 ����̶���ѡ�� */
function fc_selectOptions(txt,btn,itype,ibegin,iend)
{
	var year_arr = new Array();
	var j = 0; 	
	iibegin = (ibegin +'' != 'undefined')? ibegin : 1980 ;
	iiend   = (iend   +'' != 'undefined')? iend   : 2010 ;
	if(txt.value !=''){
		if(!ibegin&&ibegin != 0)
			iibegin = parseInt(txt.value) - 5;	
		if(!iend)
			iiend   = parseInt(txt.value) + 5
	}
	for(var i=iibegin ; i<=iiend; i++){
		year_arr[j++] = i+'';
	}
	setSelectMenu(txt,year_arr,0,btn,'',j);
}

//--�������Ϸ�����
function fc_checkInput(txt,btn,itype,ibegin,iend){
	if(txt.value!=''){
		if(isNaN(txt.value)){
			alert('��ʽ�д���');txt.select();
			return false;
		}
		switch(itype){
			case 'year':
				if(ibegin && parseInt(txt.value)<ibegin)
					return error_msg(txt,'���ݲ��ڷ�Χ֮��');
				if(iend && parseInt(txt.value)>iend)
					return error_msg(txt,'���ݳ�����Χ');
				break;
			case 'number':
				if( parseInt(txt.value)>iend || parseInt(txt.value)<ibegin )
				{	
					alert('�������޶���Χ֮�ڵ���ֵ');txt.select();
					return false;
				}				
				break;
			case '':
				break;
		}
	}
	return true;
}
/** ������Ϣ
* @param obj ����
* @parm  msg ������Ϣ
* return false
*/
function error_msg(obj,msg)
{
	alert('�������\n'+msg);
	obj.select();
	return false;
}

/**
 * ��ʾԺϵ����
 * 
 * @param txtname		�ı����������/ID, ����ΪԺϵ��רҵ������/���߶���
 * @param hidname		����������, ׼������id/���߶���
 * @param btnobj		�ı����ť��������һ��Ϊthis
 * @param fathid		���游�ڵ�id����������/���߶���
 * @param granid		�����游�ڵ�id����������/���߶���
 * @param islinke		�Ƿ���и��ӹ�����Ĭ��Ϊ�񣬶���Ϊ�����ӽڵ㶼�����ڸ��ڵ���
 * @param jlname		��������(jlxy,jlxs,jlzy)�������ָ������ hidname
 * @param ggranid       ���游�ڵ�id����������,�����о���������֧���ļ� -- 2004/11/03
 * @param fathname      ����������(�����޸�ʱ���ʼ���������жϱ�ѧԺ��ϵ��������רҵ)
 * @param fathjlname    ��������ϵ��(jlxy,jlxs,jlzy)
 * @param checkbox      ��ѡ��־����/�񣺷Ƕ�ѡ����ѡ��ֵΪtxtname.value
 * @param jlyear          jlbj�����������������������obj
 */
function getOptionsMajor(txtname,hidname,btnobj,fathid,granid,islink,jlname,ggranid,fathname,fathjlname,checkbox,jlyear){
	var kcmlData = '';
	var txtobj = ( typeof txtname == 'object') ? txtname : document.all(txtname);
	var hidobj = '';
	if( typeof hidname == 'object'){
		var hidtxt = hidname.name;
		hidobj = hidname
	}else if(jlname)
		hidobj = document.all(hidname)
	else {
		var hidtxt = hidname;
		hidobj = document.all(hidname);
	}
	var fatobj = ( typeof fathid == 'object') ?  fathid  : document.all(fathid);
	var graobj = ( typeof granid == 'object') ?  granid  : document.all(granid);

	if(jlname){
		kcmlData = getXmlhttpMajor(jlname);
	}else	
		kcmlData = getXmlhttpMajor(hidtxt);

	if(kcmlData == ''){
		alert('û���ҵ�����Ҫ����Ŀ');
		return;
	}
	//�Ը��ڵ����fathname����ȡfathid
	if(fathjlname && fathname && fathname.value != ''){
		initOptionsValue(fathname,fatobj,getXmlhttpMajor(fathjlname));
	}
	var string_arr = kcmlData;
	var fath_array = new Array();
	var flen = 0;
	var gran_array = new Array();
	var glen = 0;
	var self_array = new Array();
	var slen = 0;
	var fath_sels = new Array();	
	//alert(string_arr)
	if(fathjlname&&granid){
		var arr = getXmlhttpMajor(fathjlname);
		for(var t=0;t<arr.length;t++){
			var temp = arr[t].split("|||");
			if(temp[2] == graobj.value)
				fath_sels.push(temp[0]);
		}
	}	
	string_arr = fc_queryInput(string_arr,txtobj)
	for(var i=0;i<string_arr.length;i++){
		var node_arr = string_arr[i].split('|||');
		var str      = string_arr[i].substr(0);	
		
		var node_arrMult = node_arr[2] && node_arr[2].indexOf("###") > 0 ? node_arr[2].substring(node_arr[2].indexOf("###")+3) : '';
		node_arr[2] =  node_arr[2] && node_arr[2].indexOf("###") > 0 ? node_arr[2].substring(0,node_arr[2].indexOf("###")) : node_arr[2];

		//ֻ�ж������ʱ����";"�����ж�
		if(jlname && node_arr[2] && node_arr[2].indexOf(";") > 0 &&fatobj && fatobj.value!='' && node_arr[2].indexOf(fatobj.value)!=-1){
			fath_array[flen++] = str;
		}else if(jlname  && node_arrMult && fatobj.value && node_arr[2].indexOf(fatobj.value)==0){
			//�Զ����ݿ���רҵ�� YYYY***ZYID:YYYY***ZYID
			if(jlyear){
				var year_arr  = node_arrMult.split(":"); 
				for(var y=0;y<year_arr.length;y++){
					var year_node = year_arr[y].split("***");
					if(jlyear.value == year_node[0]){
						fath_array[flen++] = str;
						break;
					}
				}
				/*if(jlyear.value != node_arrMult){//���� רҵid###���
					str = str.replace('|||'+node_arr[1], '|||<font color = #444444>' + node_arr[1] + '</font>');				
					gran_array[glen++] = str;
				}*/
			}else 
				fath_array[flen++] = str;
		}else if(fathid && node_arr[2] && fatobj.value == node_arr[2]){
			fath_array[flen++] = str
		}
		else if(granid && node_arr[2] ){
			if( graobj.value == node_arr[2]){
				str = str.replace('|||'+node_arr[1], '|||<font color = #444444>' + node_arr[1] + '</font>');				
				gran_array[glen++] = str;
			}else{
				var isGranid = true;
				for(var t=0;t<fath_sels.length;t++) //--add ����ѧԺ������ϵ����רҵȡ��
					if(node_arr[2] == fath_sels[t]){	
						str = str.replace('|||'+node_arr[1], '|||<font color = #444444>' + node_arr[1] + '</font>');	
						gran_array[glen++] = str;
						isGranid = false;
						break;
					}					
				if(isGranid){
					str = str.replace('|||'+node_arr[1], '|||<font color = #888888>' + node_arr[1] + '</font>');
					self_array[slen++] = str;	
				}
			}
		}else{
			if(fathid)
				str = str.replace('|||'+node_arr[1], '|||<font color = #888888>' + node_arr[1] + '</font>');	
			self_array[slen++] = str;
		}
	}

	for(var i=0;i<gran_array.length;i++)
		fath_array[flen++] = gran_array[i];
	if(!islink) //--add the param is or not link
		for(var i=0;i<self_array.length;i++)
			fath_array[flen++] = self_array[i];	
	var box = 'null';
	if(checkbox)
		box = txtobj.value;
	setSelectMenu(txtobj, fath_array,0,btnobj,hidobj,10,box)
}

/*--�Թ����������ı�ֵ--
 *
 * @param itemName      �����������ƣ�     jlxs,jlzy
 * @param faname        ���ڵ㼶�����ƣ�jlxy,jlxs
 * @param hiddobj		��ű���id�����������
 * @param ftxt			���ڵ������Ƶ��ı������
 * @param fhidd			���ڵ���ID�����ؿ����
 * @param gname			�游�ڵ㼶������:jlxy
 * @param gtxt			�游�ڵ������Ƶ��ı������
 * @param ghidd			�游�ڵ���ID�����ؿ����
 * @param jlyear          jlbj�����������������������obj
 */
function changeOptionsMajor(itemName,faname,hiddobj,ftxt,fhidd,gname,gtxt,ghidd,jlyear){
	if(hiddobj.value == '')
		return 
	if(!obj_DataArray[itemName])
		obj_DataArray[itemName] = getXmlhttpMajor(itemName);
	if(!obj_DataArray[faname])
		obj_DataArray[faname] = getXmlhttpMajor(faname);
	var faid = getFatherName(itemName,hiddobj.value);//---
	var faid_str = faid.indexOf("###")>0 ? faid.substring(faid.indexOf("###")+3) : '';
	faid = faid.indexOf("###")>0 ? faid.substring(0,faid.indexOf("###")) : faid;

	if(fhidd.value == '' || fhidd.value != faid){
		var data_arr = obj_DataArray[faname] ;
		var pop_arr  = new Array();
			   var t = 0;
		/* ԭ��ݶ�רҵ����
		if(jlyear && jlyear.value != faid_str){
			if(confirm('��ѡ��� "'+global_InputContent+'" ��������ѡ������� '+jlyear.value+'���Ƿ���ѡ�� ��')){
				jlyear.value = faid_str;
			}else{
				global_InputContent = '';
				return;
			}
		}
		*/
		//�ж��Ƿ���� YYYY***ZYID:YYYY***ZYID �����ݵĻ�д
		if(jlyear){
			var year_arr  = faid_str.split(":"); // YYYY***ZYID:YYYY***ZYID
			var selyear   = false;
			for(var y=0;y<year_arr.length;y++){
				if(year_arr[y].indexOf("***")<0)
					break;
				var year_node = year_arr[y].split("***");
				pop_arr[t++] = year_node[1]+'|||'+year_node[0];
				if(jlyear.value == year_node[0]){
					hiddobj.value = year_node[1];
					selyear = true;
					break;
				}
			}
			if(!selyear && t >0){
				if(t==1){
					jlyear.value   = year_node[0]
					hiddobj.value = year_node[1]
				}else{
					var url = ''+getContextPath()+'script/select_depart.htm';
					var param = new Array(self,pop_arr);
					var returnValue = showModalDialog(url,param,'dialogWidth:400px;dialogHeight:300px;status:no;scroll:no;help:no;');
					if(returnValue){
						var temp = returnValue.split('|||');
						jlyear.value = temp[1];					
						hiddobj.value  = temp[0];
					}else{
						hiddobj.value  = '';
						jlyear.value = '';
					}
				}
			}
			t = 0 ;
		}
		for(var i=0;i<data_arr.length;i++){
			var temp = data_arr[i].split('|||');
			if(faid.indexOf(';')>=0){			//--poss: һ��רҵ���ܶ�Ӧ���Ժϵ
				var arr_faid = faid.split(';'); //--���id��';'����
				for(var j=0;j<arr_faid.length;j++){
					if(arr_faid[j] == temp[0]){
						pop_arr[t++] = data_arr[i];
						break;
					}
				}
			/*}else if(faid_str && temp[0] == faid){//�����༶ ͬʱ��רҵid �� �������

				ftxt.value = temp[1];
				fhidd.value = temp[0];
				global_object_input   = ftxt;				
				global_object_hidden  = fhidd;
				return true;*/
			}else if(temp[0] == faid){
				ftxt.value  = temp[1];
				fhidd.value = temp[0];
				global_object_input   = ftxt;				
				global_object_hidden  = fhidd;
				return true;
				break;
			}
		}
		//�������ڹ�ѡ��
		if(t > 0){
			var url = '/'+getContextPath()+'/script/select_depart.htm';
			var param = new Array(self,pop_arr);
			var returnValue = showModalDialog(url,param,'dialogWidth:400px;dialogHeight:300px;status:no;scroll:no;help:no;');
			if(returnValue){
				var temp = returnValue.split('|||');
				ftxt.value  = temp[1];
				fhidd.value = temp[0];
			}else{
				ftxt.value  = '';
				fhidd.value = '';
			}
		}else
		//if(ftxt.value!=''&&confirm('��ѡ��� "'+global_InputContent+'" �����ڹ������ϼ����ݣ��Ƿ���ѡ�� ��'))//\''+sname.value+' \'
		{
			ftxt.value  = '';// 2004/11/01 ����
			fhidd.value = '';
		}//else{
			//if(ftxt.value!='')
				//global_InputContent = '';
			//global_object_input   = ftxt;				
			//global_object_hidden  = fhidd;
		//}
		if(gname){//������רҵû��ϵ�������
			changeOptionsMajor(itemName,gname,hiddobj,gtxt,ghidd);
		}
	}
}

/*--���� �༶ ��רҵ�ļ����ı�--
function changeOptionClass(itemName,hiddobj,ftxt,fhidd){
	if(hiddobj.value.indexOf(fhidd.value)<0 || fhidd.value == ''){		
		var str ='';
		str = hiddobj.value.substr(0,8);
		if(!obj_DataArray[itemName])
			obj_DataArray[itemName] = getXmlhttpMajor(itemName);
		var data_arr = obj_DataArray[itemName] ;
		for(var i=0;i<data_arr.length;i++){
			var temp = data_arr[i].split('|||');
			if(temp[0] == str){
				ftxt.value  = temp[1];
				fhidd.value = temp[0];
				break;
			}
		}
	}
	if(ftxt.value!=''&&confirm('��ѡ��� "'+global_InputContent+'" �����ڹ������ϼ����ݣ��Ƿ���ѡ�� ��')){
		ftxt.value = '';
		fhidd.value ='';
	}else{
		if(ftxt.value!='')
			global_InputContent = '';
	}
}*/

/*--add �༶ ��רҵ �� ��� �ļ����ı�--
 * 2005-08-20 liuz because of ctbujw
 * itemName  ��������
 * faname    ��������
 * selfaid   ѡ�нڵ�fathid (ע����������ϲ�ͬ������ѡ�нڵ��֪������fathid,����Ҫ���ж�)
 * ftxt      ���ڵ�name �����
 * fhidd     ���ڵ�id hidden����
 */
function changeOptionClassCtbu(faname,selfaid,ftxt,fhidd,jlyear){
	var flag = false;
	var sel_arr = selfaid.split("|||");
	if(!obj_DataArray[faname])
		obj_DataArray[faname] = getXmlhttpMajor(faname);
	var data_arr = obj_DataArray[faname] ;
	for(var i=0;i<data_arr.length;i++){
		var temp = data_arr[i].split('|||');
		//����ݽ���
		var year_arr = temp[2].indexOf("###")>0 ? temp[2].substring(temp[2].indexOf("###")+3) : '';
		year_arr = year_arr.split(":");
		if(data_arr[i].indexOf("***"+sel_arr[2])>0){ //note: YYY***ZYID 
			ftxt.value  = temp[1];
			fhidd.value = temp[0];
			for(var y=0;y<year_arr.length;y++){
				var temp_year = year_arr[y].split("***");
				if(temp_year[1] == sel_arr[2]){
					jlyear.value = temp_year[0];
					fhidd.value = temp_year[1];
					break;
				}
			}
			flag  = true;
			break;
		}
	}
	/*
	if(!flag && ftxt.value!=''&&confirm('��ѡ��� "'+global_InputContent+'" �����ڹ������ϼ����ݣ��Ƿ���ѡ�� ��')){
		ftxt.value = '';
		fhidd.value ='';
	}else{
		if(ftxt.value!='')
			global_InputContent = '';
	}*/
}

/*--��֤�����������ַ��Ƿ���������ݿ���--
 * @param txtid     �ڵ�id
 * @param txtname   ��֤���ַ���
 * @param itemName	�������� jlxy,jlxs,jlzy
 */
function checkGetOptionsMajor(txtid,txtname,itemName)
{
	var obj   = document.all(txtname);
	var obj1  = document.all(txtid);
	if(obj.value == '' ) 
		return true;
	if(!obj_DataArray[itemName])
		 obj_DataArray[itemName] = getXmlhttpMajor(itemName);
	var data_arr = obj_DataArray[itemName] ;
	for(var i=0;i<data_arr.length;i++){
		var temp = data_arr[i].split('|||');
		if(temp[0] == obj1.value && temp[1] == obj.value ){ //--ͬʱ���ƺ�IDͳһ
			return true;
			break;
		}
	}
	return false;
}

/*----��֤�Ƿ����ڸ��ڵ�������
 * @param txtname   ��֤���ַ���
 * @param itemName	�������� jlxy,jlxs,jlzy
 * @param fathid    ���ڵ�id
 */
function checkMajorLink(txtname,fathid,itemName){
	var obj   = document.all(txtname);
	var faobj = document.all(fathid);
	if(obj.value == '' ) 
		return true;
	if(!obj_DataArray[itemName])
		 obj_DataArray[itemName] = getXmlhttpMajor(itemName);
	var data_arr = obj_DataArray[itemName] ;
	for(var i=0;i<data_arr.length;i++){
		var temp = data_arr[i].split('|||');
		if(temp[0] == obj.value && temp[2] == faobj.value ){ //--ͬʱ���ڸ��ڵ��½ڵ�
			return true;
			break;
		}
	}
	return false;
}
//-- old 
/*-- ���Ƽ�ʹ��
 * @param itemName	�������� jlxy,jlxs,jlzy
 * @param str       ��֤���ַ���
 */
function checkOptionsMajor(itemName,str)
{
	if(str == '' ) 
		return true;
	if(!obj_DataArray[itemName])
		 obj_DataArray[itemName] = getXmlhttpMajor(itemName);
	var data_arr = obj_DataArray[itemName] ;
	for(var i=0;i<data_arr.length;i++){
		var temp = data_arr[i].split('|||');
		if(temp[1] == str){
			return true;
			break;
		}
	}
	return false;
}


//���ظ��ڵ�����
function getFatherName(objname,str){
	var flag = "";
	if(str == 'null')
		return flag;
	if(!obj_DataArray[objname])
		return flag;
	for(var i=0;i<obj_DataArray[objname].length;i++){
		var arr = obj_DataArray[objname][i].split('|||');
		if(arr[0]==str || obj_DataArray[objname][i].indexOf("***"+str)>0){
			flag = arr[2];
			break;
		}
	}
	return flag;
}


/** ǰ�˵��ú��� �����ݱ���ĳ���ֶλ�ȡ����,��������ѡ��
 * @param btn  			�ı����ť����
 * @param obj  			�������ı�������,
 * @param tabname		table name
 * @param colname		colmnu name
 * @param hiddenid      ���Id��hidden����
 * @return				
 */
function getOptionsTable(btn, obj, tabname,colname,hiddenid){
	var kcmlData = '';	
	kcmlData = getXmlhttpTable(tabname,colname);
	if(kcmlData == ''){
		return;
	}	
	kcmlData = fc_queryInput(kcmlData,obj);
	setSelectMenu(obj, kcmlData,0,btn,hiddenid);
}

/** ǰ�˵��ú��� �����ݱ���ĳ���ֶλ�ȡ����,��������ѡ��
 * @param btn  			�ı����ť����
 * @param obj  			�������ı�������,
 * @param tabname		table name
 * @param colname		colmnu name
 * @param colid		id name
 * @param hiddenid      ���Id��hidden����
 * @return				
 */
function getOptionsTableWithId(btn, obj, tabname,colname,colid,hiddenid){
	var kcmlData = '';	
	kcmlData = getXmlhttpTableWithId(tabname,colname,colid);
	if(kcmlData == ''){
		return;
	}	
	kcmlData = fc_queryInput(kcmlData,obj);
	setSelectMenu(obj, kcmlData,0,btn,hiddenid);
}

/** ǰ�˵��ú��� �����ݱ���ĳ��SQL�л�ȡ����,��������ѡ����ݼ����� obj_DataArray['sql_'+sqlKey]
 * @param btn  			�ı����ť����
 * @param obj  			�������ı�������, 
 * @param sqlKey		SQL ��Ӧ��MAP�е�KEY��Ϊ�˰�ȫ���룬������ֱ��ʹ��SQL��䣬��������ע��
 * @param hiddenid      ���Id��hidden����
 * @return				
 */
function getOptionsSQL(btn, obj, sqlKey,hiddenid){  
	var kcmlData = '';	 
	if(obj_DataArray && obj_DataArray['sql_'+sqlKey])
		kcmlData = obj_DataArray['sql_'+sqlKey];
	else{
		var url = "/"+getContextPath() +"/getSQLData?sql="+sqlKey;
	        url = encodeURI(url);
		var str = httpGet(url); 
		if(str =='')
			return '';
		kcmlData = getArray(str); 
		obj_DataArray['sql_'+sqlKey] = kcmlData;
	} 
	if(kcmlData == ''){
		return;
	}	
	kcmlData = fc_queryInput(kcmlData,obj);
	setSelectMenu(obj, kcmlData,0,btn,hiddenid);  
}



/* defined  �Զ����־���Ӻ�̨ȡ���ݵķ���
 * @param btn  			�ı����ť����
 * @param obj  			�������ı�������,
 * @param hiddenid      ���Id��hidden����
 * @param linkstr       �������ӵı�־��.
 * @param sels          ���ڶ�ѡ��һ��obj.value
 * @param 
 */
function getOptionsDefined(btn,obj,hiddobj,linkstr,sels){
    var kcmlData = '';  
    kcmlData = getXmlhttpMajor(linkstr);
	if(!sels)
		kcmlData = fc_queryInput(kcmlData,obj);
	if(typeof sels != 'undefined')
		setSelectMenu(obj, kcmlData,0,btn,hiddobj,10,sels);
	else
		setSelectMenu(obj, kcmlData,0,btn,hiddobj);
}

//--��ǰ�˹���ؼ� ,֧��ǰ������ 
// ����ʹ��ǰ�˹���ؼ� �����ʹ��obj_DataArray['itemName'], ����ʹ�������ṩ���κνӿ�
function getOptionStatic(btn,obj,arr,hidd){
	if(arr == '')
		return;
	arr = fc_queryInput(arr,obj);
	setSelectMenu(obj, arr,0,btn,hidd);
}

/** ���޸ļ���ѡ�� �ĳ�ʼ��ʱ��ֵ����
 * 2005-03-23 liuz & wux 
 * @param txt : object �ı���
 * @param hid : object ������
 * @param arr : array  ���ݲ�ͬ���ݼ��õ����飺getXmlhttpMajor / getXmlhttpString ...
 */
function initOptionsValue(txt,hid,arr){
	if(!arr)
		return false;
	for(var i=0;i<arr.length;i++){
		var temp = arr[i].split('|||');
		if(temp[1] == txt.value){
			hid.value = temp[0];
			return true;
			break;
		}
	}
	return false;
}

/**
 * ͬ������ʱ�� ȡ�ù��ô���������, ����Id��Name
 * add liuz from liuj
 * @param btn  			�ı����ť����
 * @param obj  			�������ı�������, ����Name
 * @param tablename		��ȡ�õĴ����������Դ �������
 * @param fieldname		��ȡ�õĴ����������Դ �е�����
 * @return				setҳ���е��ı��� 
 */
function getOptionSync(btn, obj, tablename,fieldname) {
	var kcmlData;
	var url = getSyncURL(tablename,fieldname);  
	var allContent = httpGet(url);
	if("".trim() == allContent.trim()){
		alert('û���ҵ������ҵ���Ŀ!');
		return;
	}
	kcmlData = getArray(allContent);		 
	kcmlData = fc_queryInput(kcmlData,obj);
    setSelectMenu(obj,kcmlData,0, btn)
}


/** ������ʾ textarea �༭�� from liuz & xial 2005-03-23 *  
 *  txt  �����:obj
 *  btn  ��ť��obj *�ɲ���
 *  col  ��ȣ�int
 *  row  �߶ȣ�int
 *  hidd ������:obj
 */
function setTextareaContext(txt,btn,hidd,col,row){
	var str = '';
	var dis =  txt.value;
	var flag = false;
	col = (col)?col:200;
	row = (row)?row:200;
	var url = "/"+getContextPath()+"/";	 
	var txtname = (txt.id)?txt.id:txt.name;
	global_object_input = txt;
	global_object_button = btn;
	var hidname = '';
	if(hidd){
		hidname = (hidd.id)?hidd.id:hidd.name;
		flag = true;
		if(dis)
			dis = hidd.value;
	}
	str += "<html><head><title>Textarea</title><meta http-equiv=\"Content-Type\" content=\"text/html; charset=GBK\"><link href=\""+url+"css/style.css\" rel=\"stylesheet\" type=\"text/css\">";
	str += '<style>body{margin-left:1px;margin-top:0px;margin-right:1px;margin-bottom:0px}</style>';
	str += '<body style="overflow:hidden" oncontextmenu="self.event.returnValue=false" onload="if(parent.document.all.displaySelectLayer.style.display==\'\')txt_context.focus()">';
	str += '<table width="100%" title="�������������ı�����" border=0 bgcolor="pageLineColor" cellSpacing=1 cellPadding=0 id="tableId_selectList"><tr><td bgcolor="pageClickColor">';
	str += '<textarea name="txt_context" style="width:'+parseInt(col-3)+'px;height:'+row+'px;border:0px;overflow:hidden;overflow-y:auto" wrap="VIRTUAL" ';
	str += ' onkeyup="text_len.innerText=this.value.length;parent.getTextareaValue(txt_context.value,'+flag+',\''+txtname+'\',\''+hidname+'\')">'+dis+'</textarea></td></tr>';
	str += '<tr><td height="22" bgcolor="pageMainColor">';
	str += '<table width="100%"><tr><td width="50%">����:<span id="text_len">'+dis.length+'</span></td><td align="right">';
	str += '<a href="#" onclick="parent.getTextareaValue(txt_context.value,'+flag+',\''+txtname+'\',\''+hidname+'\');parent.document.all.displaySelectLayer.style.display=\'none\'">ȷ��</a> ';
	str += '<a href="#" onclick="parent.getTextareaValue(\'\','+flag+',\''+txtname+'\',\''+hidname+'\');txt_context.value=\'\';return false">����</a> &nbsp; </td></tr><table>';
	str += '</td></tr></table></body></html>';
	displaySelectMenu(str,txt,btn,col,row+27,'',true)
}
function getTextareaValue(str,flag,txt,hidd){
	document.all(txt).value = str;
	if(flag)
		document.all(hidd).value = str;	
}

/*-- end --*/
//--��� ����/����/���� ���ݺϷ� 2005.5.17�����޸�wux
function fc_LinkCheckInput(itemName,str)
{
	if(str == '' ) 
		return true;
	if(!obj_DataArray[itemName])
		 obj_DataArray[itemName] = getXmlhttpString(itemName,0);
	var data_arr = obj_DataArray[itemName] ;
	for(var i=0;i<data_arr.length;i++){
		var temp = data_arr[i].split('|||');
		//if(temp[1] == str){
		//���ܱ�֤ data-arr[i] �������Ƿ���� '|||'�����Բ���ȡ�� 1 �����ݣ�Ӧȡ���һ��
		if(temp[temp.length-1] == str){
			return true;
			break;
		}
	}
	return false;
}