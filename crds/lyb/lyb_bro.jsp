<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%String path = request.getContextPath();%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <html>
<head>
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
<script type="text/javascript"> 

function getTableForm() {
	return document.getElementById('formlyb');
}
</script>
</head>

<body>
<div class="box-positon">
	<div class="rpos">当前位置: 留言 -列表</div>
	<form class="ropt" action="jump.do"  method="post">
		<input class="add" type="submit" value="添加" />		
	</form>
	<div class="clear"></div>
</div>
<div class="body-box">
<html:form  styleId="formlyb" action="/browse.do" method="post" style="padding-top:5px;">
  <table class="pn-ltable" style="" width="100%" cellspacing="1" cellpadding="0" border="0">
	<thead class="pn-lthead">
	    <tr align="center">
		  <th>编号</th>
	      <th>新闻标题</th>
	      <th>新闻作者</th>
	      <th>发布时间</th>
	      
	      <th>操&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;作</th>
	      
		</tr>
	</thead>
	<tbody  class="pn-ltbody">
	  <c:if test="${not empty listmessage}">
	  <%--如果不为空进行循环 显示直到为空--%>
	  <c:forEach items="${listmessage}" var="map" varStatus="cnt">
	    <tr onmouseover="this.bgColor='#eeeeee'" onmouseout="this.bgColor='#ffffff'">
		   <td align="center">${map.new_id}&nbsp;</td>
	       <td align="center">${map.new_title}&nbsp;</td>     
	       <td align="center">${map.new_author}&nbsp;</td>
	       <td align="center">${map.new_date}&nbsp;</td>
	       <td align="center">
	          <html:link action="jump2.do?new_id=${map.new_id}">修改</html:link>|          
	          <html:link action="del.do?new_id=${map.new_id}" onclick="return window.confirm('确定要删除此留言吗？')">删除</html:link>
           </td>
	       
	     
          
	    </tr>
	  </c:forEach>
	  </c:if>	
	</tbody>
  </table>
<!--分页显示-->
<input type="hidden" name="pageNo" value="">
<c:if test="${formlyb.rowsCount!=0}">
<table width="100%" border="0" cellpadding="0" cellspacing="0"><tr><td align="center" class="pn-sp">
	共 ${formlyb.rowsCount} 条&nbsp;
	每页<input type="text" name=rowsPerPage value="${formlyb.rowsPerPage}" style="width:30px" onfocus="this.select();" onblur="$.cookie('_cookie_page_size',this.value,{expires:3650});" onkeypress="if(event.keyCode==13){$(this).blur();return false;}"/>条&nbsp;
	<input class="first-page" type="button" value="首 页" onclick="_gotoPage('1');" ${formlyb.pageNo==1?"disabled='disabled'":""}/>
	<input class="pre-page" type="button" value="上一页" onclick="_gotoPage('${formlyb.pageNo-1}');" ${formlyb.pageNo==1?"disabled='disabled'":""}/>
	<input class="next-page" type="button" value="下一页" onclick="_gotoPage('${formlyb.pageNo+1}');" ${formlyb.pageNo==formlyb.pageCount?"disabled='disabled'":""}/>
	<input class="last-page" type="button" value="尾 页" onclick="_gotoPage('${formlyb.pageCount}');" ${formlyb.pageNo==formlyb.pageCount?"disabled='disabled'":""}/>&nbsp;
	当前 ${formlyb.pageNo}/ ${formlyb.pageCount} 页 &nbsp;转到第<input type="text" id="_goPs" style="width:50px" onfocus="this.select();" onkeypress="if(event.keyCode==13){$('#_goPage').click();return false;}"/>页
	<input class="go" id="_goPage" type="button" value="转" onclick="_gotoPage($('#_goPs').val());" ${(formlyb.pageCount==1)?"disabled='disabled'":""} />
</td></tr></table>
</c:if>
<br/>
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
</body>