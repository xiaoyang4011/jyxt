 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
　
 <%@ include file="/pub/include/common.jsp"%>
 
<body><br>
  <html:form  action="dep_spec_Manage.do" method="post"  >
  <html:hidden name="formSpeciality" property="pageNo" />
  <html:hidden name="formSpeciality" property="rowsPerPage" />
  <html:hidden name="formSpeciality" property="dep_code" /> 
  <html:hidden name="formSpeciality" property="dep_name" /> 
<table width="675"  border="0" align="center" cellpadding="4" cellspacing="1" bgcolor="#3D7BA3">
<tr> <td align="right">
<input type="button" class="" onclick="dep_spec_add()"  value="添加专业"></td>
</tr>
</table>
<table width="675"  border="0" align="center" cellpadding="4" cellspacing="1" bgcolor="#3D7BA3">
 <tr bgcolor="94C9E7" align="center">
 <td width="15%">序  号</td>
      <td>专业编号</td>
      <td>专业名称</td>
      <td>专业状态</td>
      <td>描  述</td>     
      <td>操&nbsp;&nbsp;&nbsp;&nbsp;作</td>
    </tr>
    <c:forEach items="${specialitylist}" var="map" varStatus="cnt">
	<tr bgcolor="ffffff" class="${cnt.index % 2 == 0 ? 'odd' : 'even'}" align="center">
	    <td>${cnt.index+1}&nbsp;</td>
		<td >${map.spec_code}</td>
		<td >${map.spec_name}</td>
		<td >${map.spec_state}</td>
		<td >${map.spec_description}</td>
		 
		<td><html:link  page="/dep_spec_update.do?spec_code=${map.spec_code}&dep_code=${formSpeciality.dep_code}" target="">修改</html:link>|<html:link page="/dep_spec_delete.do?spec_code=${map.spec_code}&dep_code=${formSpeciality.dep_code}" onclick="return window.confirm('确认删除该专业？')">删除</html:link></td>
    
	</tr> 
	</c:forEach>
</table> 
<table width="100%" id="navigate">
		<tr><td align="right" ><app:navigate objectName="formSpeciality" /></td></tr>
	</table>
 </html:form>
</body>
<script>
	
 function dep_spec_add()
 {
   var depcode=document.getElementById("dep_code").value;
   location.href("dep_spec_add.do?dep_code="+depcode);   
 }	
</script>