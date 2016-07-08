<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%String path = request.getContextPath();%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>角色管理</title>

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
<script type="text/javascript"> 

function getTableForm() {
	return document.getElementById('querysublibform');
}
function optDelete() {
	if(Pn.checkedCount('ids')<=0) {
		alert("请选择您要操作的数据");
		return;
	}
	if(!confirm("您确定删除吗？")) {
		return;
	}
	var f = getTableForm();
	f.action="/system/role/deleteRole.do";
	f.submit();
}
function optCheck() {
	if(Pn.checkedCount('ids')<=0) {
		alert("请选择您要操作的数据");
		return;
	}
	var f = getTableForm();
	f.action="o_check.do";
	f.submit();
}



function chgStatus() {
	var queryStatus = $("input[name=queryStatus]:checked").val();
	location.href="v_list.do?cid=43&queryStatus=" + queryStatus;
}
</script>
</head>
<body>
<div class="box-positon">
	<div class="rpos">当前位置: 负责的子库 - 子库列表</div>	
	<div class="clear"></div>
</div>
<div align="center">&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">${message}&nbsp;</font></div>
<div class="body-box">
<html:form styleId="querysublibform" action="/system/role/queryRole.do" method="post" style="padding-top:5px;"> 


<table class="pn-ltable" style="" width="100%" cellspacing="1" cellpadding="0" border="0">
<thead class="pn-lthead"><tr>
	<th width="20"><input type='checkbox' onclick='Pn.checkbox("ids",this.checked)'/></th>
	<th>子库ID</th>
	<th>专业名称</th>
	<th>子库名称</th>	
	<th>操作选项</th></tr></thead>
	<tbody  class="pn-ltbody">
	<logic:present name="subliblist">
 	<logic:iterate name="subliblist" id="s">
 		<tr onmouseover="this.bgColor='#eeeeee'" onmouseout="this.bgColor='#ffffff'">
		<td><input type='checkbox' name='ids' value='271'/></td>
		<td align="center">${s.sublib_id}</td>
		<td align="center">${s.spec_name}</td>
		<td align="center">${s.sublib_title}</td>
	   	<td align="center"> <html:link page="/sublibquery.do?sublib_id=${s.sublib_id}">资源管理</html:link>|<html:link page="/pub/tree/classmain.do?sublib_id=${s.sublib_id}&spec_code=${s.spec_code}">子库分类</html:link>   </td>
	    </tr>
	  </logic:iterate>
	</logic:present> 
</tbody>
</table>
<input type="hidden" name="pageNo" value="">

<script type="text/javascript"> 
function _gotoPage(pageNo) {
	try{
		var tableForm = getTableForm();
		$("input[name=pageNo]").val(pageNo);		
		tableForm.onsubmit=null;
		tableForm.submit();
	} catch(e) {
		alert('_gotoPage(pageNo)方法出错');
	}
}
</script>

</html:form>
</div>

</BODY>

</html>