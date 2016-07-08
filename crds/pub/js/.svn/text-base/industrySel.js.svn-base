var xmlhttp;
function buildXmlHttp() {
	try {
		if (!xmlhttp) {
			xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
		}
	}
	catch (e) {
		try {
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		catch (e) {
			try {
				xmlhttp = new XMLHttpRequest();
				if (xmlhttp.overrideMimeType) {
					xmlhttp.overrideMimeType("text/xml");
				}
			} catch (e) {
			}
		}
	}
}
//根据选择的行业,填充下级行业,selfLayer选择的当前层,nextLayer下层,被填充对象,thisCode当前层选择的行业代码,lastLayer总的层次数
//selfLayer:已改为 默认选择的行业
function fillSubIndustry(selfLayer, nextLayer, path, thisCode,lastLayer,isQuery) {
	buildXmlHttp();
	var url = path + "/to_modif_set_industry.do?layer=" + nextLayer + "&lastLayerCode=" + thisCode;
	if (!xmlhttp) {
		return false;
	} else {
		xmlhttp.open("POST", url, false);
		xmlhttp.send(null);
		var selObj = document.getElementById("In" + nextLayer);
		var selText = isQuery=="query"?["ALL","全部"]:["NA","请选择"];
		selObj.options.length = 0;
		selObj.options[selObj.options.length] = new Option(selText[1], selText[0]);
		var result = xmlhttp.responseText;
		if(null != result && result.length>0){
			result = result.split("!");
			for (var i = 0; i < result.length; i++) {
				var aaa = result[i].split(",");
				var op=	new Option(aaa[1], aaa[0]);
				if(selfLayer.length==8){//默认选择的行业项
					if(aaa[0]==selfLayer){
						op.selected=true;
					}
				}
				selObj.options[selObj.options.length] = op;
			}
		}
		for (var i = nextLayer+1; i <= lastLayer; i++) {//清空下级
			var sel = document.getElementById("In" + i);
			sel.options.length = 0;
			sel.options[sel.options.length] = new Option(selText[1], selText[0]);
		}
	}
}
//债项类型下拉列表(当前级别，下一级别，发布名称，类型代码，联动菜单总级数，查询类型(0：全查 1：只查债券 2：只查债项),源(即在哪里调用的 EV：信用评级模块调用 CO：组合管理模块调用)
function fillSubDebtType(selfLayer, nextLayer, path, thisCode,lastLayer,queryType,source,isQuery) {
	//下拉框名称
	var selname="d_type";
	var selText = isQuery=="query"?["NA","全部"]:["","请选择"];
	if(nextLayer==2){
		selname="debt_type"
	}
	if(thisCode==-1){//未选择
		var sel = document.getElementById(selname);
		sel.options.length = 0;
		sel.options[0] = new Option(selText[1], selText[0]);
		return false;
	}

	buildXmlHttp();
	var url = path + "/queryDebtType.do?layer=" + nextLayer + "&lastLayerCode=" + thisCode+"&queryType="+queryType;
	if (!xmlhttp) {
		return false;
	} else {
		xmlhttp.open("POST", url, false);
		xmlhttp.send(null);
		var result = xmlhttp.responseText.split("!");
		
		var selObj = document.getElementById(selname);
		selObj.options.length = 0;
		//selObj.style.width = "";
		selObj.options[selObj.options.length] = new Option(selText[1], "-1");
		for (var i = 0; i < result.length; i++) {
			var aaa = result[i].split(",");
			var op=	new Option(aaa[1], aaa[0]);
			selObj.options[selObj.options.length] = op;
		}
	}
}
