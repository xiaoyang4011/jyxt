<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<%String path = request.getContextPath();%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<head>
<title>用户管理</title>
<link href="../../pub1/css/common.css" rel="stylesheet" type="text/css">
<link href="../../pub1/css/default.css" rel="stylesheet" type="text/css">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<link href="<%=path%>/res/jeecms/css/admin.css" rel="stylesheet" type="text/css"/>
<link href="<%=path%>/res/common/css/theme.css" rel="stylesheet" type="text/css"/>
<link href="<%=path%>/res/common/css/jquery.validate.css" rel="stylesheet" type="text/css"/>
<link href="<%=path%>/res/common/css/jquery.treeview.css" rel="stylesheet" type="text/css"/>
<link href="<%=path%>/res/common/css/jquery.ui.css" rel="stylesheet" type="text/css"/>
<script src="<%=path%>/res/common/js/jquery.js" type="text/javascript"></script>
<script src="<%=path%>/res/common/js/jquery.ext.js" type="text/javascript"></script>
<script src="<%=path%>/res/common/js/pony.js" type="text/javascript"></script>
<script src="<%=path%>/res/jeecms/js/admin.js" type="text/javascript"></script>

</head>
<body onload="getDeptList()">
<div class="box-positon">
	<div class="rpos">当前位置:学生就业 - 列表</div>
	
	<form class="ropt">
		<input type="submit" value="刷新列表" onclick="this.form.action='<%=path%>/userindoPage.do';" class="return-button"/>
	</form>
	
	<div class="clear"></div>
</div>
<div align="center">&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">${message}&nbsp;</font></div>
<div class="body-box">
<html:form styleId="adduserForm" action="/userindoPage.do" method="post" style="padding-top:5px;"> 

<table class="pn-ltable" style="" width="100%" cellspacing="1" cellpadding="0" border="0">
<thead class="pn-lthead"><tr>
	<th>就业单位</th>
	<th colspan="2">2009年</th>
	<th colspan="2">2010年</th>	
	<th colspan="2">2011年</th>
	<th colspan="2">2012年</th>
	<th colspan="2">2013年</th>
 	<tr>
    <th>&nbsp;</th>
    <th>人数</th>
    <th>比例</th>
    <th>人数</th>
    <th>比例</th>
    <th>人数</th>
    <th>比例</th>
    <th>人数</th>
    <th>比例</th>
    <th>人数</th>
    <th>比例</th>
 	</tr>
	
	</tr></thead>
	<tbody  class="pn-ltbody">
	<logic:present name="list">
	<logic:iterate name="list" id="map">
 		<tr onmouseover="this.bgColor='#eeeeee'" onmouseout="this.bgColor='#ffffff'">
		<td align="center">谷歌</td>
		<td align="center"></td> 
		<td align="center"></td> 
		<td align="center"></td> 
		<td align="center"></td> 
		<td align="center"></td> 
		<td align="center"></td> 
		<td align="center"></td> 
		<td align="center"></td> 
		<td align="center">2</td> 
		<td align="center">5%</td> 

 
        </tr>
	 	</logic:iterate>
	</logic:present>
</tbody>
</table>
<input type="hidden" name="pageNo" value="">
<c:if test="${formzs.rowsCount!=0}">
<table width="100%" border="0" cellpadding="0" cellspacing="0"><tr><td align="center" class="pn-sp">
	共 ${formzs.rowsCount} 条&nbsp;
	每页<input type="text" name=rowsPerPage value="${formzs.rowsPerPage}" style="width:30px" onfocus="this.select();" onblur="jqy.cookie('_cookie_page_size',this.value,{expires:3650});" onkeypress="if(event.keyCode==13){jqy(this).blur();return false;}"/>条&nbsp;
	<input class="first-page" type="button" value="首 页" onclick="_gotoPage('1');" ${formzs.pageNo==1?"disabled='disabled'":""}/>
	<input class="pre-page" type="button" value="上一页" onclick="_gotoPage('${formzs.pageNo-1}');" ${formzs.pageNo==1?"disabled='disabled'":""}/>
	<input class="next-page" type="button" value="下一页" onclick="_gotoPage('${formzs.pageNo+1}');" ${formzs.pageNo==formzs.pageCount?"disabled='disabled'":""}/>
	<input class="last-page" type="button" value="尾 页" onclick="_gotoPage('${formzs.pageCount}');" ${formzs.pageNo==formzs.pageCount?"disabled='disabled'":""}/>&nbsp;
	当前 ${formzs.pageNo}/ ${formzs.pageCount} 页 &nbsp;转到第<input type="text" id="_goPs" style="width:50px" onfocus="this.select();" onkeypress="if(event.keyCode==13){jqy('#_goPage').click();return false;}"/>页
	<input class="go" id="_goPage" type="button" value="转" onclick="_gotoPage(jqy('#_goPs').val());" ${(formzs.pageCount==1)?"disabled='disabled'":""} />
</td></tr></table>
</c:if>
<script type="text/javascript"> 
function _gotoPage(pageNo) {
	try{
		var tableForm = getTableForm();
		
		jqy("input[name=pageNo]").val(pageNo);		
		tableForm.onsubmit=null;
		tableForm.submit();
	} catch(e) {
	   alert(e);
	}
}
</script>
	


</html:form>
</div>

</BODY>

</html>