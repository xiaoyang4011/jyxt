var spryPanelMang = new JSClassMap();
/**
 * 初始化spryPanelDiv
 * @param spryPanelID 键,必选项 在一个页面中引用时不能重复
 * @param spryPanelParentDivID 可选项 spryPanelDiv集合的父Div的id,如果不传则获取页面的所有div
 * @param initAfterOperator 可选项,初始化之后的操作,此参数为一个对象并且有两个属性[operator,index],具体详见方法spryPanelShow(operator,index)
 */
function initSpryPanel(spryPanelID,spryPanelParentDivID,initAfterOperator){
	if(null==spryPanelID || typeof spryPanelID != "string"){
		alert("参数spryPanelID为null或不是字符串")
		return;
	}
	var spryPanelDivs = null;
	if(null != spryPanelParentDivID && typeof spryPanelParentDivID == "string") {
		spryPanelDivs = document.getElementById(spryPanelParentDivID).getElementsByTagName("div");
	} else {
		spryPanelDivs = document.getElementsByTagName("div");
	}
	if(null != spryPanelDivs) {
		var spryPanels = new Array();
		for (var i=0; i<spryPanelDivs.length;i++) {
		    if ((spryPanelDivs[i].className == "CollapsiblePanel") && (spryPanelDivs[i].id)) {
			    var cp = new Spry.Widget.CollapsiblePanel(spryPanelDivs[i].id);
			    spryPanels.push(cp);
		    }
		}
		spryPanelMang.push(spryPanelID,spryPanels);
		if(null != initAfterOperator){//默认为全部打开状态,所以这里先关闭再进行相应的操作
			if(null != initAfterOperator.operator && typeof initAfterOperator.operator =="string" && initAfterOperator.operator=="close") {
				spryPanelShow(spryPanelID,initAfterOperator.operator,initAfterOperator.index);
			} else {
				spryPanelShow(spryPanelID,"closeall");
				spryPanelShow(spryPanelID,initAfterOperator.operator,initAfterOperator.index);
			}
		}
	}
}

/**
 * 关闭和展开div列表,当operator为openall/closeall时忽略参数index,
 * @param spryPanelID 键,必选项 在一个页面中引用时不能重复
 * @param operator 操作选项,必选项 四个值[open close openall closeall] 不是这四个值则不以处理
 * @param index    操作列表索引,可选项,当参数operator为open/close时为必选项,主要是针对operator为open/close时有效,即打开/关闭某一个div
 */
function spryPanelShow(spryPanelID,operator,index){
	if(null==spryPanelID || typeof spryPanelID != "string"){
		alert("参数spryPanelID为null或不是字符串")
		return;
	}
	if(typeof operator == "string") {
		var spryPanels = spryPanelMang.get(spryPanelID);
		if(null != spryPanels) {
			if(operator=="open" && typeof index == "number" && index >= 0 && index < spryPanels.length){
				spryPanels[index].open();
			} else if(operator=="close" && typeof index == "number" && index >= 0  && index < spryPanels.length){
				spryPanels[index].close();
			} else if(operator=="openall") {
				for(var i=0; i<spryPanels.length; i++)
					spryPanels[i].open()
			}else if(operator=="closeall") {
				for(var i=0; i<spryPanels.length; i++)
					spryPanels[i].close()
			}
		}
	}
}