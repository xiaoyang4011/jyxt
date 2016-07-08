
/* -- 可输入下拉控件
 * -- 使用方法参考使用文档
 * -- 2005-02-23 建立独立显示下拉菜单方法
 * -- 2005-02-03 加入同步数据用的方法和接口 liuz & liuj
 * -- 2004-12-31 增加自定义数据集体对id字段的支持 guanj --
 * -- 2004-12-11 优化多选功能，整理代码
 * -- 2004-11-25 使用createPopup，使得控件可以浮在框架上
 * -- 2004-11-22 加入 班级级连
 * -- 2004-10-20 加入 院/系 国家/城市 级连
 * -- 2004-10-01 建立对象数组obj_DataArray 保存已经取过的数据，下次不再重复从服务器取数据
 * -- 2004-09-30 增加多选功能 liuz --
 * -- 2005-02-17 zhoujf 增加完善注释
 * -- 2005.5.17  增加检查 大洲/国家/城市 数据合法 
 **/

/*--全局变量--*/
var global_object_input;				// 输入框对象
var global_object_hidden;				// 隐藏对象
var global_object_button;				// 按钮对象
var global_selectListData = '';			// 中转变量
var global_selectArray    = '';			//保存多选的值
var obj_DataArray         = new Object()// 数组缓存--第一次加载后重复利用
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

/*---构造控件函数--公用方法-----------------------------------------------------------------------------*/

/** 公用显示下拉菜单方法
 *  tabString			显示的内容(string)
 *  temp_obj			输入控件对象，用于定位 
 *  temp_btn			按钮对象
 *  local_page_width    显示宽度
 *  local_page_height   显示高度
 *  isPageCount			根据是否有滚动条而设置高度、宽度	
 *  flag 是否不采用creatpop
 */
function displaySelectMenu(tabString,temp_obj,temp_btn,local_page_width,local_page_height,isPageCount,flag){
	var ttop  = temp_obj.offsetTop;     //TT控件的定位点高
	var thei  = temp_obj.clientHeight;  //TT控件本身的高
	var tleft = temp_obj.offsetLeft;    //TT控件的定位点宽
	var ttyp  = temp_btn.type;          //TT控件的类型
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
 * 将取到的数组弹出窗口显示出来
 * @param temp_obj  	欲填充的文本框对象名称, 保存Name		
 * @param temp_btn  	文本框或按钮对象引用		
 * @param optionInfos	从服务器取得或者前端构造的数组
 * @param nextPage      分页开始。默认为0	
 后来扩展：
 * @param arguments[4]  存放Id的hidden对象名
 * @param arguments[5]  显示数据数目，默认为10出现分页，其他数字为出现滚动条
 * @param arguments[6]  用于多选中，保存已经选中的字符串（以‘ ; ’隔开）。默认为输入框this.value
 * @param arguments[7]  是否屏蔽“置空” 默认为false /  true 则不出现“置空”
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
	global_object_button = (arguments.length == 1) ? null : th;	//设定外部点击的按钮
	global_object_hidden = (arguments.length >= 5 && typeof arguments[4] == 'object') ? arguments[4] : null;
		
	var inteval     = 10;   //每页数
	var isPageCount = true; //是否分页
	var resertBtn   = (arguments.length >= 8 )?true:false;
	//-- 增加参数6,arg5 !=10 则显示所有信息,出现滚动条,自动调节宽度 --参数5预留给system 2004-08-20 liuz
	var arg5        = (arguments.length >= 6)?parseInt(arguments[5]):10
	if(arg5 != 10)
		isPageCount = false;
	var selBox      = false; //是否多选
	//-- 参数7 多选,存放已选项string  2004-09-30 liuz
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
	var slen			  =  5  //页面宽度
	var shei			  =  25 //页面高度
	var local_page_scroll = 'hidden';
	var local_page_height =  210;
	var table_width       = '100%';   
	if(!isPageCount){		
		local_page_scroll = 'auto'
		table_width       = '100%'
	}
	
	var showNum   = 4;     //分页
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
				var txtvalue = ';'+temp_obj.value+';'; //回选
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
	tabSelectString += "	<td style=\"cursor:hand;\" title=\"将输入框置空\" class='article_copyright'>";//<img src='"+get_filepath1()+"image/shenhe_wei1.gif' align='absmiddle' border=0> <img src='"+get_filepath1()+"image/shenhe_no.gif' align='absmiddle' border=0>
	if(selBox)
		tabSelectString +=   "<a href='#' onclick='parent.getSelectValue(parent.global_selectArray)'>选取</a> ";
	if(!resertBtn)
		tabSelectString +=   "<a href='#' onclick='parent.getSelectValue(\"\")'>置空</a> ";
	if(local_page_width > 100)
		tabSelectString +=   "<a href='#' onclick='parent.closeSelectMenu()'>关闭</a>&nbsp;";
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

/**返回选取的值
 * @param content 传入的内容
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
			if(content.indexOf('**')>=0){ //多选的值
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
			if(global_object_hidden){//如果没有改变值将不再继续				
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

/*-- 返回选中的值 
 * 重构该方法,具体使用如 级连中构造关连性
 * obj 选择输入框对象 
 * str 选中返回值（包括多选）
 */
function fc_onSelect(obj,str){}

//--这个层的关闭
function closeSelectMenu(){	
	global_selectArray = '' ;//清空多选值
	if(global_explorer_ie6)
		SelectInputWin.hide();
	else
		document.all.displaySelectLayer.style.display="none";
}

//--图片路径
function get_filepath1(){
	return "/" + getContextPath() + "/";
}

//--构造xmlhttp取数据的代码 与 ID 对应
function getArrayMap(){
	var arrayMapping = new Array();	
	arrayMapping.add(new codeMapping('xb',   20001));//性别
	arrayMapping.add(new codeMapping('mz',   20002));//民族
	arrayMapping.add(new codeMapping('xl',   10013));//10046));学历
	arrayMapping.add(new codeMapping('jg',   30012));//籍贯
	arrayMapping.add(new codeMapping('hyzk', 20003));//婚因状况
	arrayMapping.add(new codeMapping('zzmm', 20011));//政治面貌
	arrayMapping.add(new codeMapping('gj',   30009));//籍贯
	arrayMapping.add(new codeMapping('bdlb', 10023));//变动类别
	arrayMapping.add(new codeMapping('bdyy', 10024));//变动原因
	//arrayMapping.add(new codeMapping('xueqi',96013));//学期
	arrayMapping.add(new codeMapping('dz',   96008));//大洲 国家 城市
	//arrayMapping.add(new codeMapping('xy', 6));    //学院
	//arrayMapping.add(new codeMapping('zy', 0));	   //专业
	arrayMapping.add(new codeMapping('xq',   30011)) //校区
	/** heercareer */
	arrayMapping.add(new codeMapping('jjlx',80005)); //经济类型
	arrayMapping.add(new codeMapping('DWHY',80006)); //单位行业
	arrayMapping.add(new codeMapping('ZWMC',20025)); //职位
	arrayMapping.add(new codeMapping('YY',  20074)); //语言
	arrayMapping.add(new codeMapping('kclb',10036)); //课程类别
	//dug add on 2006-03-07
	arrayMapping.add(new codeMapping('jzglb',20049)); //教职工类别
	arrayMapping.add(new codeMapping('zc',200250001)); //教职工职称
	//自定义代码
	//arrayMapping.add(new codeMapping('kclb',923000009)); //课程类别
	arrayMapping.add(new codeMapping('xueqi',923000015));//学期
	arrayMapping.add(new codeMapping('pjzblx',93800)); //评教指标类型
	arrayMapping.add(new codeMapping('pjzbys',93801)); //评教指标要素
	return arrayMapping ;
}

//--从后端获取数据
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
			alert('没有您选择的项目');
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
				//alert('没有找到您查找的项目!');
				//return '';
			//}
			kcmlData = getArray(allContent);			
		}
		obj_DataArray[itemName]=kcmlData;
	}
	return kcmlData;
}

/** 分离字段
* @param src 字符串
* @param num 顺序号
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

/** 检查Level是否正确 */
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
 * 得到欲发送的url
 * @param fatherId   父ID
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

/** 获取同步数据的url
 *  add liuz from liuj
 *  @param tablename   数据表名称
 *  @param fieldname   字段名称
 *  return url 地址
 */
function getSyncURL(tablename,fieldname){	
	var baseURL = "/" + getContextPath() + "/getSyncData";
	var url = baseURL + '?tablename=' + tablename + '&fieldname='+ fieldname + '&time=' + new Date().getTime();	 
	return url;	
}

/** 院系级连 的构造xmlhttp路径
 *  hidname 为 jlxy,jlxs,jlzy 现在可以扩展为自定义的数据集标志名
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

/** 构造直接从数据表中某个字段获取数据
 * @param tabname 表的名字
 * @param colname 字段名字
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

/** 构造直接从数据表中某个字段获取数据
 * @param tabname 表的名字
 * @param colname 字段名字
 * @param colid   id名字
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

/** 返回父ID
 * @param arrayMapping 排列映射名称
 * @param itemName 列名
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

/** 在排列中索引关键字
 * @param keyWord 传入的关键字
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

/** 在排列中增加内容
 * @param content 内容
 */
function Array.prototype.add(content){
	this[this.length] = content;
}

/** 字符串排序
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

//--通过URL获得xml数据
function httpGet(url){
   var content = "";
   try{   
	 var xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
	  xmlhttp.open("POST", url, false);
      //xmlhttp.setRequestHeader( null , cookie );
      xmlhttp.send();
      content = xmlhttp.responseText.trim();
	 /** 后端验证
	  *  发生且仅发生在com.heer.common.codeservice.CodeServiceImpl()的getFullNames()方法throws CodeLenthNotExistException
	  */
	 if("ERRORS_HAPPEND!!!".toString() == content.toString()){
		alert('对不起, Level 必须>=0 <=6!!!');
		content = "";
	  }
   }catch(e){   
      fc_debug("从服务器端获得数据的过程中出现错误" + e);
   }  
   return content;
}

/** 返回拆分后纯字符串排列
 * @param allContent 原始字符串内容
 * return retArray
 */
function getArray(allContent){
	var retArray = new Array()
	var SEPERATOR = ",";
	try{
		//特殊字符过滤
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
		fc_debug("拆分字符串错误" + e);
	}
	return retArray;	
}

//重新给映射赋值
function codeMapping(itemName, fatherId){
	this.itemName = itemName;
	this.fatherId = fatherId;
}

/** 控件的查询功能，将匹配的用红色标志*/
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
			str = str.replace(/\<[^>]*>/gi,""); //将所有<>过滤掉
			str = str.replace(/&amp;40;/gi,"(");
			str = str.replace(/&amp;41;/gi,")");
			//str = str.replace(/&amp;/gi,"&");
			str = str.replace(/&{1}[a-z]{1,};{1}/gi,"");//过滤html特殊字符
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

/*  显示控件多选功能
 *  2004-11-10 update Liuz  
 *  2004-09-30 Add    Liuz
 */
//--选中是变换 和 改变值
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
//--增加新值
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
//--减少值
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
 * 2004-08-05 鼠标事件
 * 任意点击时关闭该控件	
 * ie6的情况可以由下面的切换焦点处理代替
 ------------------------*/
function document.onclick(){
  with(window.event){  
	 if (typeof global_date_input == 'object'){
		 if (srcElement.getAttribute("Author")==null && srcElement != global_date_input && srcElement != global_date_button)
			document.all.meizzDateLayer.style.display="none";  //关闭时间控件
	 }
  	 if (typeof global_object_input == 'object'){
	 	 if(srcElement !=global_object_input && srcElement !=global_object_button &&displaySelectLayer )
			document.all.displaySelectLayer.style.display="none";//关闭 input+select 控件
	 }
  }
}

//--所有alert从这里屏蔽
function fc_debug( text ){
   alert( text );
}

/*---可输入的下拉控件 提供所有方法 接口------------------------------------------------------------------*/
/**
 * 取得公用代码表的内容, 只包括Id和Name
 * @param btn  			文本框或按钮引用
 * @param obj  			欲填充的文本框名称, 保存Name
 * @param itemName		欲取得的代码类型, 可直接传递father_id 或者 arrayMapping 中已有的内容
 * @param level			隐藏参数, 欲取得的代码层数, 如果不填, 默认为取出所有层数
 * @param arg[4]        =this.value,用于多选的回选内容
 * @param arg[5]        是否屏蔽“置空”按钮 默认false(为空) / true 则屏蔽 
 * @return				set页面中的文本域
 */
function getOptions(btn, obj, itemName) {
	var kcmlData;
	var optionsData;  //中转数组		
	var level = 0;/**  代码表层数 */
	
	if(arguments[3]){
			level = arguments[3];
			if(!checkLevel(level)){
				alert('对不起, Level 必须>=0 <=6!!!');
				return;
			}
	}
	//--为兼容以前版本 对xy,xs,zy使用级连接口
	//if(itemName == 'xy' || itemName == 'xs' || itemName == 'zy'){
	//	getOptionsDefined(btn,obj,null,'jl'+itemName)
	//	return;
	//}
	//--统一获取数据接口
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

/*-- 检查数据合法 --*/
/* @params txtname  输入框的名称 
 * @params codename 对应代码的名称 'jlxy','jg'...
 */
function checkGetOptionValue(txtname,codename){
	return checkTextSelected(codename,txtname);
}

/* 文本选择
 * @params item 对应代码的名称
 * @params txt     输入框的值
 */
function checkTextSelected(item,txt){
	if(checkOptionsWithIdValue(document.all(txt),'',item))
		return true;
	else{
		alert('输入的 " '+txt+' " 数据在数据库中不存在！\n 请选择适合的数据。');
		getOptionsDefined(obj,obj,'',item)
		return false;
	}
}

/** 检查代码合法性 2005-04-14  改进以上方法直接使用扩展本方法
 *  obj   输入框对象
 *  hid   隐藏域对象
 *  item  代码名字:jg,xb 或者代码
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
 * 取得公用代码表的内容, 包括Id和Name
 * @param btn  			文本框或按钮引用
 * @param obj  			欲填充的文本框名称, 保存Name
 * @param hiddenObj		欲填充的hidden域名成, 保存Id
 * @param itemName		欲取得的代码类型, 可直接传递father_id 或者 arrayMapping 中已有的内容
 * @param level			隐藏参数, 欲取得的代码层数, 如果不填, 默认为取出所有层数
 * @return				set页面中的文本域和hidden域
 */
function getOptionsWithId(btn, obj, hiddenObj, itemName) {
	var kcmlData;
	var optionsData;  //中转数组	
	var level   = arguments[4]?arguments[4]:0;	//代码表层数	
	if(arguments[4]){
		level = arguments[4];
		if(!checkLevel(level)){
			alert('对不起, Level 必须>=0 <=6!!!');
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


/* 构造级连，主要满足 大洲/国家/城市 三个级连项
 * 原理：一次取到数据，在前端分离构造祖父、父、子接点数组，再排序显示
 * 2004-10-19 liuz
 * @param btn  			文本框或按钮引用
 * @param obj  			欲填充的文本框名称, 保存Name
 * @param hiddenObj		欲填充的hidden框名称, 保存Id
 * @param itemName		欲取得的代码类型名称, 限定为 arrayMapping 中已有的内容
 * @param level         级连位置：1为祖父 2为父亲 3为儿子
 * @param fathid		父接点id   (string 注意当对"父节点初始化"，fathid这个时候要为object)
 * @param granid		祖父接点id (string )
 * @param flag          是否进行强关联 (默认是false)
 * @param fathname      为进行父节点初始化，定义父节点name域
 * @param fathjlname    为进行父节点初始化，定义父数据集名称
 */
function fc_getLinkOptions(btn,obj,hiddenObj,itemName){
	var kcmlData;
	var optionsData;  //中转数组
	var level   = arguments[4]?arguments[4]:0;
	var fathid  = arguments[5]?arguments[5]:'';		
	var granid  = arguments[6]?arguments[6]:0;
	var flag    = arguments[7]?arguments[7]:false;
	var fathname= arguments[8]?arguments[8]:null;
	var fathjlname= arguments[9]?arguments[9]:'';

	kcmlData = getXmlhttpString(itemName,0);
	if(kcmlData == '')
		return;
	//对父节点根据fathname重新取fathid
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

/*--重新排列--*/
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


/*--清空子级--*/
function fc_getLinkClear(txt1,hidd1){
	if(document.all(txt1))
		document.all(txt1).value  = '';
	if(document.all(hidd1))
		document.all(hidd1).value = '';
}

/*--关联改变--
 *
 * @param itemName		欲取得的代码类型名称（注意fatherid不行）, 限定为 arrayMapping 中已有的内容
 * @param hiddObj		本接点保存Id的hidden框对象名, 
 * @param ftxt			父接点保存名称的文本框对象
 * @param fhidd			父接点保存ID的hidden框对象
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

//--检查 大洲/国家/城市 数据合法(旧的)
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

/** 10-14 补充固定的选项 */
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

//--检查输入合法数据
function fc_checkInput(txt,btn,itype,ibegin,iend){
	if(txt.value!=''){
		if(isNaN(txt.value)){
			alert('格式有错误！');txt.select();
			return false;
		}
		switch(itype){
			case 'year':
				if(ibegin && parseInt(txt.value)<ibegin)
					return error_msg(txt,'数据不在范围之内');
				if(iend && parseInt(txt.value)>iend)
					return error_msg(txt,'数据超过范围');
				break;
			case 'number':
				if( parseInt(txt.value)>iend || parseInt(txt.value)<ibegin )
				{	
					alert('请输入限定范围之内的数值');txt.select();
					return false;
				}				
				break;
			case '':
				break;
		}
	}
	return true;
}
/** 错误信息
* @param obj 对象
* @parm  msg 错误信息
* return false
*/
function error_msg(obj,msg)
{
	alert('输入错误：\n'+msg);
	obj.select();
	return false;
}

/**
 * 显示院系级联
 * 
 * @param txtname		文本输入框名字/ID, 内容为院系或专业的名称/或者对象
 * @param hidname		隐藏域名称, 准备保存id/或者对象
 * @param btnobj		文本框或按钮对象名，一般为this
 * @param fathid		保存父节点id的隐藏域名/或者对象
 * @param granid		保存祖父节点id的隐藏域名/或者对象
 * @param islinke		是否进行父子关联。默认为否，定义为是则子节点都包含在父节点下
 * @param jlname		级连名字(jlxy,jlxs,jlzy)，如果不指定就用 hidname
 * @param ggranid       曾祖父节点id的隐藏域名,扩充研究方向级连，支持四级 -- 2004/11/03
 * @param fathname      父输入框对象(用于修改时候初始化级连、判断本学院下系所下所有专业)
 * @param fathjlname    父级连关系名(jlxy,jlxs,jlzy)
 * @param checkbox      多选标志，空/否：非多选，多选传值为txtname.value
 * @param jlyear          jlbj中所在年份条件：年份输入框obj
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
		alert('没有找到你需要的项目');
		return;
	}
	//对父节点根据fathname重新取fathid
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

		//只有多个父亲时候以";"区分判断
		if(jlname && node_arr[2] && node_arr[2].indexOf(";") > 0 &&fatobj && fatobj.value!='' && node_arr[2].indexOf(fatobj.value)!=-1){
			fath_array[flen++] = str;
		}else if(jlname  && node_arrMult && fatobj.value && node_arr[2].indexOf(fatobj.value)==0){
			//对多个年份开设专业： YYYY***ZYID:YYYY***ZYID
			if(jlyear){
				var year_arr  = node_arrMult.split(":"); 
				for(var y=0;y<year_arr.length;y++){
					var year_node = year_arr[y].split("***");
					if(jlyear.value == year_node[0]){
						fath_array[flen++] = str;
						break;
					}
				}
				/*if(jlyear.value != node_arrMult){//解析 专业id###年份
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
				for(var t=0;t<fath_sels.length;t++) //--add 将该学院下所有系部下专业取到
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

/*--对关联的其他改变值--
 *
 * @param itemName      本身级连的名称：     jlxs,jlzy
 * @param faname        父节点级连名称：jlxy,jlxs
 * @param hiddobj		存放本身id的隐藏域对象
 * @param ftxt			父节点存放名称的文本框对象
 * @param fhidd			父节点存放ID的隐藏框对象
 * @param gname			祖父节点级连名称:jlxy
 * @param gtxt			祖父节点存放名称的文本框对象
 * @param ghidd			祖父节点存放ID的隐藏框对象
 * @param jlyear          jlbj中所在年份条件：年份输入框obj
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
		/* 原年份对专业控制
		if(jlyear && jlyear.value != faid_str){
			if(confirm('您选择的 "'+global_InputContent+'" 不属于你选择的数据 '+jlyear.value+'，是否保留选择 ？')){
				jlyear.value = faid_str;
			}else{
				global_InputContent = '';
				return;
			}
		}
		*/
		//判断是否存在 YYYY***ZYID:YYYY***ZYID 后对年份的回写
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
			if(faid.indexOf(';')>=0){			//--poss: 一个专业可能对应多个院系
				var arr_faid = faid.split(';'); //--多个id以';'区分
				for(var j=0;j<arr_faid.length;j++){
					if(arr_faid[j] == temp[0]){
						pop_arr[t++] = data_arr[i];
						break;
					}
				}
			/*}else if(faid_str && temp[0] == faid){//级连班级 同时有专业id 和 年份条件

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
		//弹出窗口供选择
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
		//if(ftxt.value!=''&&confirm('您选择的 "'+global_InputContent+'" 不存在关联的上级数据，是否保留选择 ？'))//\''+sname.value+' \'
		{
			ftxt.value  = '';// 2004/11/01 屏蔽
			fhidd.value = '';
		}//else{
			//if(ftxt.value!='')
				//global_InputContent = '';
			//global_object_input   = ftxt;				
			//global_object_hidden  = fhidd;
		//}
		if(gname){//处理有专业没有系部的情况
			changeOptionsMajor(itemName,gname,hiddobj,gtxt,ghidd);
		}
	}
}

/*--增加 班级 对专业的级连改变--
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
	if(ftxt.value!=''&&confirm('您选择的 "'+global_InputContent+'" 不存在关联的上级数据，是否保留选择 ？')){
		ftxt.value = '';
		fhidd.value ='';
	}else{
		if(ftxt.value!='')
			global_InputContent = '';
	}
}*/

/*--add 班级 对专业 和 年份 的级连改变--
 * 2005-08-20 liuz because of ctbujw
 * itemName  本身级连名
 * faname    父级连名
 * selfaid   选中节点fathid (注意这个与以上不同，由于选中节点就知道了其fathid,不需要再判断)
 * ftxt      父节点name 输入框
 * fhidd     父节点id hidden地域
 */
function changeOptionClassCtbu(faname,selfaid,ftxt,fhidd,jlyear){
	var flag = false;
	var sel_arr = selfaid.split("|||");
	if(!obj_DataArray[faname])
		obj_DataArray[faname] = getXmlhttpMajor(faname);
	var data_arr = obj_DataArray[faname] ;
	for(var i=0;i<data_arr.length;i++){
		var temp = data_arr[i].split('|||');
		//对年份解析
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
	if(!flag && ftxt.value!=''&&confirm('您选择的 "'+global_InputContent+'" 不存在关联的上级数据，是否保留选择 ？')){
		ftxt.value = '';
		fhidd.value ='';
	}else{
		if(ftxt.value!='')
			global_InputContent = '';
	}*/
}

/*--验证关联的输入字符是否存在于数据库中--
 * @param txtid     节点id
 * @param txtname   验证的字符串
 * @param itemName	级连名称 jlxy,jlxs,jlzy
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
		if(temp[0] == obj1.value && temp[1] == obj.value ){ //--同时名称和ID统一
			return true;
			break;
		}
	}
	return false;
}

/*----验证是否属于父节点下数据
 * @param txtname   验证的字符串
 * @param itemName	级连名称 jlxy,jlxs,jlzy
 * @param fathid    父节点id
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
		if(temp[0] == obj.value && temp[2] == faobj.value ){ //--同时属于父节点下节点
			return true;
			break;
		}
	}
	return false;
}
//-- old 
/*-- 不推荐使用
 * @param itemName	级连名称 jlxy,jlxs,jlzy
 * @param str       验证的字符串
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


//返回父节点名称
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


/** 前端调用函数 从数据表中某个字段获取数据,生成下拉选项
 * @param btn  			文本框或按钮引用
 * @param obj  			欲填充的文本框名称,
 * @param tabname		table name
 * @param colname		colmnu name
 * @param hiddenid      存放Id的hidden域名
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

/** 前端调用函数 从数据表中某个字段获取数据,生成下拉选项
 * @param btn  			文本框或按钮引用
 * @param obj  			欲填充的文本框名称,
 * @param tabname		table name
 * @param colname		colmnu name
 * @param colid		id name
 * @param hiddenid      存放Id的hidden域名
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

/** 前端调用函数 从数据表中某个SQL中获取数据,生成下拉选项，数据集名是 obj_DataArray['sql_'+sqlKey]
 * @param btn  			文本框或按钮引用
 * @param obj  			欲填充的文本框名称, 
 * @param sqlKey		SQL 对应在MAP中的KEY，为了安全着想，不允许直接使用SQL语句，必须首先注册
 * @param hiddenid      存放Id的hidden域名
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



/* defined  自定义标志名从后台取数据的方法
 * @param btn  			文本框或按钮引用
 * @param obj  			欲填充的文本框名称,
 * @param hiddenid      存放Id的hidden域名
 * @param linkstr       定义连接的标志名.
 * @param sels          用于多选，一般obj.value
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

//--在前端构造控件 ,支持前端搜索 
// 对于使用前端构造控件 ，如果使用obj_DataArray['itemName'], 可以使用以上提供的任何接口
function getOptionStatic(btn,obj,arr,hidd){
	if(arr == '')
		return;
	arr = fc_queryInput(arr,obj);
	setSelectMenu(obj, arr,0,btn,hidd);
}

/** 对修改级连选项 的初始化时赋值操作
 * 2005-03-23 liuz & wux 
 * @param txt : object 文本框
 * @param hid : object 隐藏域
 * @param arr : array  根据不同数据集得到数组：getXmlhttpMajor / getXmlhttpString ...
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
 * 同步数据时候 取得公用代码表的内容, 包括Id和Name
 * add liuz from liuj
 * @param btn  			文本框或按钮引用
 * @param obj  			欲填充的文本框名称, 保存Name
 * @param tablename		欲取得的代码的数据来源 表的名称
 * @param fieldname		欲取得的代码的数据来源 列的名称
 * @return				set页面中的文本域 
 */
function getOptionSync(btn, obj, tablename,fieldname) {
	var kcmlData;
	var url = getSyncURL(tablename,fieldname);  
	var allContent = httpGet(url);
	if("".trim() == allContent.trim()){
		alert('没有找到您查找的项目!');
		return;
	}
	kcmlData = getArray(allContent);		 
	kcmlData = fc_queryInput(kcmlData,obj);
    setSelectMenu(obj,kcmlData,0, btn)
}


/** 弹出显示 textarea 编辑框 from liuz & xial 2005-03-23 *  
 *  txt  输入框:obj
 *  btn  按钮：obj *可不用
 *  col  宽度：int
 *  row  高度：int
 *  hidd 隐藏域:obj
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
	str += '<table width="100%" title="请在这里输入文本内容" border=0 bgcolor="pageLineColor" cellSpacing=1 cellPadding=0 id="tableId_selectList"><tr><td bgcolor="pageClickColor">';
	str += '<textarea name="txt_context" style="width:'+parseInt(col-3)+'px;height:'+row+'px;border:0px;overflow:hidden;overflow-y:auto" wrap="VIRTUAL" ';
	str += ' onkeyup="text_len.innerText=this.value.length;parent.getTextareaValue(txt_context.value,'+flag+',\''+txtname+'\',\''+hidname+'\')">'+dis+'</textarea></td></tr>';
	str += '<tr><td height="22" bgcolor="pageMainColor">';
	str += '<table width="100%"><tr><td width="50%">字数:<span id="text_len">'+dis.length+'</span></td><td align="right">';
	str += '<a href="#" onclick="parent.getTextareaValue(txt_context.value,'+flag+',\''+txtname+'\',\''+hidname+'\');parent.document.all.displaySelectLayer.style.display=\'none\'">确定</a> ';
	str += '<a href="#" onclick="parent.getTextareaValue(\'\','+flag+',\''+txtname+'\',\''+hidname+'\');txt_context.value=\'\';return false">重置</a> &nbsp; </td></tr><table>';
	str += '</td></tr></table></body></html>';
	displaySelectMenu(str,txt,btn,col,row+27,'',true)
}
function getTextareaValue(str,flag,txt,hidd){
	document.all(txt).value = str;
	if(flag)
		document.all(hidd).value = str;	
}

/*-- end --*/
//--检查 大洲/国家/城市 数据合法 2005.5.17增加修改wux
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
		//因不能保证 data-arr[i] 的内容是否包含 '|||'，所以不能取第 1 个数据，应取最后一个
		if(temp[temp.length-1] == str){
			return true;
			break;
		}
	}
	return false;
}