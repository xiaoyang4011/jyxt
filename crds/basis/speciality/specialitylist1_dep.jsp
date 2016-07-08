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
	return document.getElementById('queryroleform');
}
function chgStatus() {
	var queryStatus = $("input[name=queryStatus]:checked").val();
	location.href="v_list.do?cid=43&queryStatus=" + queryStatus;
}
</script>
</head>
<body>
<div class="body-box">
<html:form styleId="queryroleform" action="speciality_query_dep.do" method="post" style="padding-top:5px;"> 
  <html:hidden name="formSpeciality" property="dep_code" /> 
<table class="pn-ltable" style="" width="100%" cellspacing="1" cellpadding="0" border="0">
<thead class="pn-lthead"><tr>
	<th>序号</th>
	<th>专业编号</th>
      <th>专业名称</th>
      <th>部门名称</th>
       <th>级别</th>
      <th>专业状态</th>
      
      <th>描  述</th>     
      <th>操&nbsp;&nbsp;&nbsp;&nbsp;作</th>
      
	</tr></thead>
<tbody  class="pn-ltbody">
	
 
   <c:forEach items="${specialitylist}" var="s" varStatus="cnt">	 
 		<tr onmouseover="this.bgColor='#eeeeee'" onmouseout="this.bgColor='#ffffff'">
 		<td align="center">${cnt.index+1+ (formSpeciality.pageNo-1)*formSpeciality.rowsPerPage}</td> 
		<td align="center">${s.spec_code}</td> 
		<td align="center">${s.spec_name}</td>
		<td align="center">${s.dep_name}</td>
		<td align="center">${s.showtype}</td>
		<td align="center">${s.spec_state}</td>
		<td align="center">${s.spec_description}</td>		     
	   <td align="center"><html:link page="/updateSpeciality_dep.do?spec_code=${s.spec_code}&dep_code=${s.dep_code}" target="mainFrame">修改</html:link>
	   ||<html:link page="/deleteSpeciality.do?spec_code=${s.spec_code}&dep_code=${formSpeciality.dep_code}" onclick="return window.confirm('确认删除该专业？')">删除</html:link>
	   ||<html:link page="/headManage.do?dbType=02&key=${s.spec_code}&home_title=${s.spec_name}" target="mainFrame">专业负责人管理</html:link></td>
  	    </tr>
	  </c:forEach>  
</tbody>
</table>
<input type="hidden" name="pageNo" value="">
<c:if test="${formSpeciality.rowsCount!=0}">
<table width="100%" border="0" cellpadding="0" cellspacing="0"><tr><td align="center" class="pn-sp">
	共 ${formSpeciality.rowsCount} 条&nbsp;
	每页<input type="text" name=rowsPerPage value="${formSpeciality.rowsPerPage}" style="width:30px" onfocus="this.select();" onblur="$.cookie('_cookie_page_size',this.value,{expires:3650});" onkeypress="if(event.keyCode==13){$(this).blur();return false;}"/>条&nbsp;
	<input class="first-page" type="button" value="首 页" onclick="_gotoPage('1');" ${formSpeciality.pageNo==1?"disabled='disabled'":""}/>
	<input class="pre-page" type="button" value="上一页" onclick="_gotoPage(' ${formSpeciality.pageNo-1}');" ${formSpeciality.pageNo==1?"disabled='disabled'":""}/>
	<input class="next-page" type="button" value="下一页" onclick="_gotoPage('${formSpeciality.pageNo+1}');" ${formSpeciality.pageNo==formSpeciality.pageCount?"disabled='disabled'":""}/>
	<input class="last-page" type="button" value="尾 页" onclick="_gotoPage('${formSpeciality.pageCount}');" ${formSpeciality.pageNo==formSpeciality.pageCount?"disabled='disabled'":""}/>&nbsp;
	当前 ${formSpeciality.pageNo}/${formSpeciality.pageCount} 页 &nbsp;转到第<input type="text" id="_goPs" style="width:50px" onfocus="this.select();" onkeypress="if(event.keyCode==13){$('#_goPage').click();return false;}"/>页
	<input class="go" id="_goPage" type="button" value="转" onclick="_gotoPage($('#_goPs').val());" ${(formSpeciality.pageCount==1)?"disabled='disabled'":""} />
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

 