/*
 * ��ʾ��ǩҳ
 * ��Ҫ��style.css���������������ʽ������image���Ŀ¼����ͼƬ�������ܱ�ɫ
 * ��̬��ȡͼƬ·��
 */
var global_catalog_image = "/"+ getContextPath() +"/";
if(typeof golobal_image_path == 'undefined')
	var golobal_image_path = global_catalog_image +'image/skin/'+get_cookie("ZsuSysStyle_cookie")

/** �µ� ��ʾѡ��ӿ�
 *  2005-03-07 liuz   because of zhangsh
 *  arr    ��ʾѡ�����/�����Ķ�ά����  arr[[name1,function1],[name2,function2],[...]];
 *  selid  ѡ�еı��
 *  return str ����ѡ�����
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

/** Ϊ��ֹ����ͼƬʱ�����쳣�����뻺��*/
function getImage(){

}
getImage();

/*---------------------------------
 * ���ú��� ������
 * index       Ϊ��� 
 * str         Ϊ��ʾ���� 
 * inum        Ϊ����ܵ���Ŀ 
 * isSelected  Ϊ��ʼ��ʱ�Ƿ�ѡ��
 ----------------------------------*/
function showTab(index, str, inum,isSelected) {

	var tab = showTabStr(index, str, inum,isSelected);
	document.write(tab);
	document.close();
}
/*---------------------------------
 * ����html����ֱ�����
 * ���ú��� ������
 * index       Ϊ��� 
 * str         Ϊ��ʾ���� 
 * inum        Ϊ����ܵ���Ŀ 
 * isSelected  Ϊ��ʼ��ʱ�Ƿ�ѡ��
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
 * ����¼��������Լ�������������
 */
function clickTab(index,inum) {
	var tdList;

	 (new Image()).src = global_catalog_image+"image/tab_01.gif";
	 (new Image()).src = global_catalog_image+"image/tab_02.gif";
	 (new Image()).src = global_catalog_image+"image/tab_03.gif";
	 (new Image()).src = golobal_image_path+'/tab_01.gif';
	 (new Image()).src = golobal_image_path+'/tab_02.gif';
	 (new Image()).src = golobal_image_path+'/tab_03.gif';

    /*ע�⶯̬��ʶ��·�� get_filepath()������sel_style.js��*/

	for(var i = 0; i < inum; i++) {
		if(i==index || document.getElementById("tabTrId"+i)==null) /*���inum��Ŀ����ȷ���� break*/
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
