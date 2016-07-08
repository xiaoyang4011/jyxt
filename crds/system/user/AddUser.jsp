<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<center>
<table width="675" border="0" cellspacing="0" cellpadding="0">
<tr> 
   <td width="675" background="../image/line_banner.gif"><img src="../image2/spacer.gif" width="1" height="2"></td>
</tr>
</table><br>&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">${message}&nbsp;</font>
<table width="300"  border="0" align="center" cellpadding="4" cellspacing="1" bgcolor="#3D7BA3">
<html:form action="/system/user/addUserDo" method="post" onsubmit="return checkstudentform(this)">
 	<tr>
      <td align="center" bgcolor="94C9E7">用户ID</td>
      <td align="left" bgcolor="FFFFFF"><html:text property="user_id"  style="width:100%"/></td>
    </tr>
 	<tr>
      <td align="center" bgcolor="94C9E7">用户名</td>
      <td align="left" bgcolor="FFFFFF"><html:text property="user_name" style="width:100%"/></td>
    </tr>
 	<tr>
      <td align="center" bgcolor="94C9E7">&nbsp;&nbsp;姓名 </td>
      <td align="left" bgcolor="FFFFFF"><html:text property="real_name"  style="width:100%"/></td>
    </tr> 	   
 	<tr>
      <td align="center" bgcolor="94C9E7">&nbsp;&nbsp;院系</td>
      	<td align="left" bgcolor="ffffff" colspan="2"><html:select property="dep_code" style="width:100%">
      		<html:options collection="deptlist" property="dep_code" labelProperty="dep_name" />
      	</html:select>
      	</td>
    </tr>
 	<tr>
      <td align="right" colspan="2" bgcolor="FFFFFF"><html:reset value="重置"/>&nbsp;&nbsp;&nbsp;&nbsp;<html:submit value="增加"/>&nbsp;&nbsp;&nbsp;</td>
    </tr>      
</html:form>   
          	
</table></center>  
<script>
if(!document.studentForm.sex[0].checked&&!document.studentForm.sex[1].checked){
	document.studentForm.sex[0].checked = true;
}
	function checkstudentform(sf){
		var message = "";
		var i = 1;
		if(!isUsername(sf.username.value)){
			message += i+") 用户名只能输入6-30个字母、数字、下划线\n";
			i++;
		}
		if(!isUsername(sf.oldpassword.value)){
			message += i+") 密码只能输入6-30个字母、数字、下划线\n";
			i++;
		}
		if(sf.oldpassword.value!=sf.newpassword.value){
			message += i+") 两次密码不一致\n";
			i++;
		}
		if(sf.name.value==""||sf.name.value.length>15){
			message += i+") 姓名不能为空且小于15字\n";
			i++;
		}
		if(sf.address.value==""||sf.address.value.length>20){
			message += i+") 地址不能为空且小于20字\n";
			i++;
		}
		if(sf.email.value==""||sf.email.value.length>40){
			message += i+") Email不能为空且小于40字母\n";
			i++;
		}			
		if(message!=""){
			alert(message);
			return false;
		}else{
			return true;
		}
											
	}
	//校验用户名：只能输入6-20个字母、数字、下划线
	function isUsername(s){
		var patrn=/^(\w){6,30}$/;
		if (!patrn.exec(s)) 
			return false;
		return true;
	}	
</script> 
