/*
 * 显示标签页
 * 需要在style.css加入上面后三个样式，按照image里的目录引入图片，可随框架变色
 * 动态获取图片路径
 */
var global_catalog_image = "/"+ getContextPath() +"/";
if(typeof golobal_image_path == 'undefined')
	var golobal_image_path = global_catalog_image +'image/skin/'+get_cookie("ZsuSysStyle_cookie")

/** 新的 显示选项卡接口
 *  2005-03-07 liuz   because of zhangsh
 *  arr    显示选项卡名字/方法的二维数组  arr[[name1,function1],[name2,function2],[...]];
 *  selid  选中的编号
 *  return str 生成选项卡代码
 */
function displayPanel(arr,selid){
	var str = "<table  height='20'  border='0' cellpadding='0' cellspacing='0'>";
	str += "<tr align='center' style='cursor:hand'>";
	for(var i=0;i<arr.length;i++){
		str += " <td onclick='"+arr[i][1]+"'><scr"+"ipt>showTab("+i+", '"+arr[i][0]+"',"+arr.length
		if(i == selid)
			str += ",true";
		str += ")</scr"+"ipt></td>";
	}
	str += "</tr>";
	str += "</table>";
	return str;
}

/** 为防止加载图片时候导致异常，加入缓存*/
function getImage(){

}
getImage();

/*---------------------------------
 * 调用函数 参数：
 * index       为编号 
 * str         为显示内容 
 * inum        为编号总的数目 
 * isSelected  为初始化时是否选中
 ----------------------------------*/
function showTab(index, str, inum,isSelected) {

	var tab = showTabStr(index, str, inum,isSelected);
	document.write(tab);
	document.close();
}
/*---------------------------------
 * 返回html，不直接输出
 * 调用函数 参数：
 * index       为编号 
 * str         为显示内容 
 * inum        为编号总的数目 
 * isSelected  为初始化时是否选中
 ----------------------------------*/
function showTabStr(index, str, inum,isSelected) {

	var tab = "";
	if(isSelected) {
		tab += "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\">";
		tab += "<tr id=\"tabTrId"+index+"\" style=\"cursor:hand\" onclick=\"clickTab("+index+","+inum+")\">";
		tab += "<td background = '"+golobal_image_path+"/tab_01.gif'  height=\"19\" width=\"6\">&nbsp;</td>";//class=\"tab_selected_panel1\"
		tab += "<td background = '"+golobal_image_path+"/tab_02.gif'  class='article_timeStyle3'>"+str+"</td>";
		tab += "<td background = '"+golobal_image_path+"/tab_03.gif' width=\"25\">&nbsp;</td>";
		tab += "</tr>";
		tab += "</table>";
	}
	else {
		tab += "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\">";
		tab += "<tr id=\"tabTrId"+index+"\" style=\"cursor:hand\" onclick=\"clickTab("+index+","+inum+")\">";
		tab += "<td background=\""+global_catalog_image+"image/tab_01.gif\" height=\"19\" width=\"6\">&nbsp;</td>";
		tab += "<td background=\""+global_catalog_image+"image/tab_02.gif\">"+str+"</td>";
		tab += "<td background=\""+global_catalog_image+"image/tab_03.gif\" width=\"25\">&nbsp;</td>";
		tab += "</tr>";
		tab += "</table>";
	}
	return tab;
}
/*
 * 点击事件，可以自己加入其它函数
 */
function clickTab(index,inum) {
	var tdList;

	 (new Image()).src = global_catalog_image+"image/tab_01.gif";
	 (new Image()).src = global_catalog_image+"image/tab_02.gif";
	 (new Image()).src = global_catalog_image+"image/tab_03.gif";
	 (new Image()).src = golobal_image_path+'/tab_01.gif';
	 (new Image()).src = golobal_image_path+'/tab_02.gif';
	 (new Image()).src = golobal_image_path+'/tab_03.gif';

    /*注意动态的识别路径 get_filepath()包含在sel_style.js中*/

	for(var i = 0; i < inum; i++) {
		if(i==index || document.getElementById("tabTrId"+i)==null) /*如果inum数目不正确，就 break*/
			continue;
		tdList = document.getElementById("tabTrId"+i).children;
		tdList[0].background = global_catalog_image+"image/tab_01.gif";
		tdList[1].className  = "";
		tdList[1].background = global_catalog_image+"image/tab_02.gif";
		tdList[2].background = global_catalog_image+"image/tab_03.gif";
	}
	tdList = document.getElementById("tabTrId"+index).children;
	tdList[0].background = golobal_image_path+'/tab_01.gif';
	tdList[1].className  = "article_timeStyle3";
	tdList[1].background = golobal_image_path+'/tab_02.gif';
	tdList[2].background = golobal_image_path+'/tab_03.gif';
}
