//菜单导航页(0000menu.jsp)ajax操作--begin
var XMLHttpReq = false;

function createXMLHttpRequest() {//创建request对象
	if (window.XMLHttpRequest) { //Mozilla 
		XMLHttpReq = new XMLHttpRequest();
	} else {					 //Windows IE
		if (window.ActiveXObject) {
			try {
				XMLHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
			}catch (e) {
				try {
					XMLHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
				}
				catch (e) {
				}
			}
		}
	}
}

function showChild(path,operationID,treeLevel,x,flag) {//显示子菜单(文根，父级操作id，菜单级别,菜单位置)
	var url = path+'/queryNavigation.do?operationID='+operationID+'&treeLevel='+treeLevel+'&path='+path;
	var operID = document.getElementById("2").operationID;
	if(operID != operationID){
		createXMLHttpRequest();
		XMLHttpReq.open("GET", url, true);
		XMLHttpReq.onreadystatechange = function () {
			if (XMLHttpReq.readyState == 4) {
				if (XMLHttpReq.status == 200) {
					var responseData = XMLHttpReq.responseText;
					if(responseData==null || responseData==undefined || responseData==""){
						alert("会话过时,请重新登录!");
						window.location.replace(path);
					} else if(treeLevel==2){
						document.getElementById('3').style.display='none';
						document.getElementById('crdsMenuID').style.height=62;
						document.getElementById('2').style.left = x-11;
					} else if(treeLevel==3){
						document.getElementById('3').style.display='none';
						document.getElementById('crdsMenuID').style.height=88;
						document.getElementById('3').style.left = x-10;
					}
					document.getElementById(treeLevel).innerHTML = responseData;	
					document.getElementById(treeLevel).style.display='block';
					changeTwoMenuFirst(treeLevel,flag);//触发下一级菜单的第一个选项
				} else {
					window.alert("系统错误，错误代码为-" + XMLHttpReq.status);
				}
			}
		};
		XMLHttpReq.setRequestHeader("If-Modified-Since","0");
		XMLHttpReq.send(null);
		document.getElementById("2").operationID = operationID;
	} else {
		document.getElementById("2").style.display='block';
		changeTwoMenuFirst(treeLevel,flag);//触发下一级菜单的第一个选项
	}
}

//单击一级菜单时触发二级菜单的第一个选项，然后跳转到第一个选项对应的页面
function changeTwoMenuFirst(treelevel,flag){
	var link = "";
	jQuery("div[id=2] a").each(function(){
		if(this.url && this.url != "" && link==""){
			link = this.url;
			jQuery(this).css("color","#A42423");
		}else{
			jQuery(this).css("color","");
		}
	});
	openPage(treelevel,link, flag);
}

//打开新页面
function openPage(treeLevel,link, flag){
	closeMenu(treeLevel);
	if(link!="" && flag != "noLink"){
		var flag=link.substring(link.length-3)!='.do' ? '&' : "?";
		link = link+flag+"temp="+Date();
		window.open(link,'mainFrame');
	}
}
//根据菜单级别关闭不相关的菜单
function closeMenu(treeLevel){
	if(treeLevel==1){
		document.getElementById('2').style.display='none';
		document.getElementById('3').style.display='none';
		document.getElementById('crdsMenuID').style.height=36;
	} else if(treeLevel==2){
		document.getElementById('3').style.display='none';
		document.getElementById('crdsMenuID').style.height=65;
	}
}
//菜单导航页(0000menu.jsp)ajax操作--end
