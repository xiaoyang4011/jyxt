/**
 * 要应用此文件必须要引用jQuery1.3.2,此crdsTabs是针对jQuery1.3.2做的一个插件,网站上有很多实例,但是缺少这样的功能(ul标签脱离div标签).也就是说ul标签必须在div标签内,否则不可用,增加此插件的功能目的是让ul脱离div.
 * <div id="jQueryTabs">
 * 		<ul id="jQueryTabsUL">
 * 			<li id="#jQueryTabsContents1"></li>
 * 			<li id="#jQueryTabsContents2"></li>
 * 		</ul>
 * 		<div id="jQueryTabsContents">
 * 			<div id="jQueryTabsContents1">content1</div>
 * 			<div id="jQueryTabsContents2">content2</div>
 * 		</div>
 * </div>
 * 增加此插件功能之后可以这样用,请注意ul以及div的id的命名规则,详情可以参考下面实例.
 * <ul id="jQueryTabsUL">
 * 		<li id="li_jQueryTabsContents_1"></li>
 * 		<li id="li_jQueryTabsContents_2"></li>
 * </ul>
 * <div id="jQueryTabs">
 * 		<div id="jQueryTabsContents">
 * 			<div id="jQueryTabsContents_1">content1</div>
 * 			<div id="jQueryTabsContents_2">content2</div>
 * 		</div>
 * </div>
 * 调用实例:
 * 1. jQuery("#jQueryTabs").crdsTabs();
 * 2. jQuery("#jQueryTabs").crdsTabs({titleItem:"#jQueryTabsUL"});
 * 3. jQuery("#jQueryTabs").crdsTabs({titleItem:"#jQueryTabsUL",show:1});
 * 详解 jQuery(divid).crdsTabs(opts);
 * divid: div标签的ID
 * opts={
 * 		titleItem:"",//UL标签的id
 * 		show:"",//显示第几个,默认为第1个
 * 		event:"",//触发事件,目前支持mouseover和click,默认为click
 * 		ulCss:"",//UL标签的样式CSS
 * 		liSelectCss:"",//LI标签被选择的样式CSS
 * 		liUnSelectCss:""//LI标签未被选择的样式CSS
 * }
 * 
 * 
 * 参数是一个选项对象
 * 
 */
(function(jQuery){
		jQuery.fn.crdsTabs = function(opts) {
			var tabsDivID = this.selector;//这个值为"#tagDivID"
			var tabsTitleItemULTag = null;
			var tabsTitleItemULID = null;
			var tabsTitleItemLI = null;
			var divTabsContentArray = new Array();
			//初始化数据并查找ul标签对象是否存在begin
			if(!(opts)) opts = {};
			if(!(opts.show) || typeof opts.show != "number" || opts.show < 0 ) opts.show = 0;
			if(!(opts.event) || typeof opts.event != "string" || (opts.event!="mouseover" && opts.event!="click")) opts.event="click";
			if(!(opts.ulCss) || typeof opts.ulCss != "string") opts.ulCss="crdsTabsUl";
			if(!(opts.liSelectCss) || typeof opts.liSelectCss != "string") opts.liSelectCss="crdsTabsUlLiSelect";
			if(!(opts.liUnSelectCss) || typeof opts.liUnSelectCss != "string") opts.liUnSelectCss="crdsTabsUlLi";
			//判断是否存在divid
			if(tabsDivID && tabsDivID!="") {
				if(!(opts.titleItem) || null==opts.titleItem) {
					opts.titleItem = tabsDivID+"UL";
				}
				tabsTitleItemULTag = jQuery(opts.titleItem);
				if(!(tabsTitleItemULTag) || tabsTitleItemULTag.size()<=0){
					alert("call jQuery('"+tabsDivID+"').crdsTabs(), not found ul tag,id="+tabsTitleItemULID);//找不到ul标签对象
					return;
				} else {
					jQuery(tabsTitleItemULTag).removeClass();
					jQuery(tabsTitleItemULTag).addClass(opts.ulCss);
					tabsTitleItemULID = opts.titleItem.substring(1);
					jQuery("ul",tabsDivID).attr("id",tabsTitleItemULID);//给ul的属性id赋值
					tabsTitleItemLI = jQuery("li",opts.titleItem);
				}
				//初始化数据并查找ul标签对象是否存在end
				//判断ul中是否有li标签选项
				if(tabsTitleItemLI && tabsTitleItemLI.size()>0){
					opts.show = opts.show > tabsTitleItemLI.size() ? 0 : opts.show;//调整默认的选择项
					//循环遍历LI标签并进行相应的操作
					jQuery.each(tabsTitleItemLI,function(i){
						var li_id = jQuery(tabsTitleItemLI[i]).attr("id");
						if(li_id && li_id.length>3){
							li_id = li_id.substring(3)
						} else {
							li_id = tabsDivID.substring(1)+"Content_"+i;
							jQuery(tabsTitleItemLI[i]).attr("id","li_"+li_id)
						}
						
						//获取相应的div并且存在此div
						var contentDiv = jQuery("#"+li_id);
						if(contentDiv && contentDiv.length>0){
							if(opts.show == i){
								jQuery(contentDiv).show();
								jQuery(tabsTitleItemLI[i]).addClass(opts.liSelectCss)
							} else {
								jQuery(contentDiv).hide();
								jQuery(tabsTitleItemLI[i]).removeClass(opts.liSelectCss);
							}
							divTabsContentArray.push(contentDiv);
							//CSS调整
							jQuery(tabsTitleItemLI[i]).removeClass();
							jQuery(tabsTitleItemLI[i]).addClass(opts.liUnSelectCss);
							if(opts.show==i)
								jQuery(tabsTitleItemLI[i]).addClass(opts.liSelectCss);
							//添加li触发div显示事件
							jQuery(tabsTitleItemLI[i]).bind(opts.event,function(){
								var li_id = this.id.substring(3);
								jQuery.each(divTabsContentArray,function(j){
									if(jQuery(divTabsContentArray[j]).attr("id") == li_id){
										jQuery(divTabsContentArray[j]).show();
										jQuery(tabsTitleItemLI[j]).addClass(opts.liSelectCss)
									} else {
										jQuery(divTabsContentArray[j]).hide();
										jQuery(tabsTitleItemLI[j]).removeClass(opts.liSelectCss);
									}
								});
							});
						}
					});
				} else {
					alert("在ID为:"+opts.titleItem+"的标签中找不到 LI 标签!");
				}
			} else if(opts.titleItem && null != opts.titleItem && typeof opts.titleItem == "string"){
				//给UL标签添加样式
				tabsTitleItemULTag = jQuery(opts.titleItem);
				jQuery(tabsTitleItemULTag[0]).removeClass();
				jQuery(tabsTitleItemULTag[0]).addClass(opts.ulCss);
				//获取所有的LI标签并对其进行操作
				tabsTitleItemLI = jQuery("li",opts.titleItem);
				if(tabsTitleItemLI && tabsTitleItemLI.size()>0){
					opts.show = opts.show > tabsTitleItemLI.size() ? 0 : opts.show;//调整默认的选择项
					jQuery.each(tabsTitleItemLI,function(i){
						jQuery(tabsTitleItemLI[i]).removeClass();
						jQuery(tabsTitleItemLI[i]).addClass(opts.liUnSelectCss);
						if(opts.show==i){
							jQuery(tabsTitleItemLI[i]).addClass(opts.liSelectCss);
							jQuery("a,span",this).css("color","#A42423");
						}
						//给LI标签添加onclick事件
						tabsTitleItemLI[i].onclick = function(){
							jQuery("li",opts.titleItem).each(function(){
								if(this == tabsTitleItemLI[i]){
									jQuery(this).addClass(opts.liSelectCss)
									jQuery("a,span",this).css("color","#A42423");
								} else {
									jQuery(this).removeClass(opts.liSelectCss)
									jQuery("a,span",this).css("color","");
								}
							});
						}
					});
				} else {
					alert("在ID为:"+opts.titleItem+"的标签中找不到 LI 标签!");
				}
			} else {
				alert("参数传递错误,没有找到div标签和ul标签的ID");
			}
		}
	})(jQuery);