 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
　
 <%@ include file="/pub/include/common.jsp"%>
 
<body><br>
  <html:form  action="specialityadmin.do" method="post"  >
  <html:hidden name="formSpeciality" property="pageNo" />
  <html:hidden name="formSpeciality" property="rowsPerPage" />
  <html:hidden name="formSpeciality" property="dep_code" /> 
<table width="675"  border="0" align="center" cellpadding="4" cellspacing="1" bgcolor="#3D7BA3">
<tr>
<td colspan="4">请选择系部
 <html:select   property="dep_code"  value="0" style="width:100"> 　
 <html:option value="0" >全部</html:option>
 <html:options property="dep_code" collection="departlist"  labelProperty="dep_name" ></html:options>
  
 </html:select>
  <input type="submit" name="button11" class=""   value="查询">
 </td>
 <td align="right"><input type="button" class="" onclick='window.open("./basicinfo/speciality/specialityadd.jsp")'  value="添加专业"></td>
</tr>
</table>
</html:form>
<table width="675"  border="0" align="center" cellpadding="4" cellspacing="1" bgcolor="#3D7BA3">
 <tr bgcolor="94C9E7" align="center">
      <td>专业编号</td>
      <td>专业名称</td>
      <td>专业状态</td>
      <td>描述</td>     
      <td>操&nbsp;&nbsp;&nbsp;&nbsp;作</td>
    </tr>
    <c:forEach items="${specialitylist}" var="map" varStatus="cnt">
	<tr bgcolor="ffffff" align="center">
		<td >${map.spec_code}</td>
		<td >${map.spec_name}</td>
		<td >${map.spec_state}</td>
		<td >${map.spec_description}</td>
		<td><html:link page="/teacher/student/update.do?studentid=1">修改</html:link>|<html:link page="/teacher/student/delete.do?studentid=1" onclick="return window.confirm('是否真要删除这个学生？')">删除</html:link></td>
    
	</tr>
	</c:forEach>
 	
	 
</table> 
<table width="100%" id="navigate">
		<tr><td align="right" ><app:navigate objectName="form" /></td></tr>
	</table>
 
</body>
<script>
	function ggo(t){
		document.queryStudentForm.pageno.value=t;
		document.queryStudentForm.submit();
	}
	
	
</script>