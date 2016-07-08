<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<head>
</head>
<BODY  bgColor=#eeeeee leftMargin=0 topMargin=0>
<center>
<table bgColor=#eeeeee width="675" border="1" cellspacing="0" cellpadding="0">
<tr> 
   <td width="675" background="../image/line_banner.gif"><img src="../image2/spacer.gif" width="1" height="2"></td>
</tr>
</table><br>&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">${message}&nbsp;</font>
<html:form action="/system/user/queryUser.do" method="post">
姓名：<html:text property="query_user_name"/>&nbsp;&nbsp;&nbsp;<input type="hidden" name="pageno" value="">
<html:submit value="查询"/>
</html:form>
<table width="675"  border="0" align="center" cellpadding="4" cellspacing="1" bgcolor="#3D7BA3">
 <tr bgcolor="94C9E7" align="center">
      <td>ID号</td>
      <td>用户名</td>     
      <td>姓名</td>
      <td>院系</td>
      <td>会员类型</td>
      <td>用户类型</td>
      <td>操&nbsp;&nbsp;&nbsp;&nbsp;作</td>
    </tr>
    <logic:present name="userlist">
 	<logic:iterate name="userlist" id="s">
 	<tr bgcolor="ffffff" align="center">
 	
      <td>${s.user_id}&nbsp;</td>
      <td>${s.user_name}&nbsp;</td>     
      <td>${s.real_name}&nbsp;</td>
       <td>${s.dep_code}&nbsp;</td>
      <td>${s.member_type}&nbsp;</td>      
      <td>${s.user_type}&nbsp;</td>
      <td><html:link page="/system/user/updateUser.do?userid=${s.user_id}">修改</html:link>|<html:link page="/teacher/student/delete.do?studentid=${s.user_id}" onclick="return window.confirm('是否真要删除这个学生？')">删除</html:link></td>
    </tr>
	</logic:iterate>
	</logic:present>
</table>  	<br>
<div align="right" >
		<html:link page="/system/user/addUser.do">增加用户</html:link>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;		
</div>
</center>
</BODY>
<script>
	function ggo(t){
		document.queryStudentForm.pageno.value=t;
		document.queryStudentForm.submit();
	}
</script>