<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
　
 <%@ include file="/pub/include/common.jsp"%>
<head>
<style>
a {text-decoration: none;}
</style>

</head>
    <script language="javascript">
    <c:if test="${not empty message}">alert('${message}');</c:if>
    </script>

<body>
 
 <html:form action="/dep_spec_add_do.do" method="post" onsubmit="return checksubjectform(this)">
 <html:hidden name="formSpeciality" property="dep_code" /> 
 <table width="500"  border="0" align="center" cellpadding="4" cellspacing="1"  bgcolor="#3D7BA3" >
	<tr>
	 <td colspan="2" bgcolor="#3D7BA3" height="35" align="center">
	添加专业	
		</td>
	</tr>	 
	<tr bgcolor="ffffff">
		<td align="center">
	专业代码	
		</td>
		<td  >
	<html:text property="spec_code"/>
	<!-- 
	<a href="">&nbsp;&nbsp;<font size="2">验证重复性</font></a> -->
		</td>
	</tr>
	 <tr bgcolor="ffffff">
		<td align="center">
	专业名称	
		</td>
		<td >
	<html:text property="spec_name"/>	
		</td>
	</tr>
	<tr bgcolor="ffffff">
		<td align="center">
	专业状态	
		</td>
		<td >
	<html:radio property="spec_state" value="1"/>打开&nbsp;&nbsp;<html:radio property="spec_state" value="0"/>关闭	
		</td>
	</tr>
	
	<tr bgcolor="ffffff">
		<td align="center">
	描&nbsp;&nbsp;&nbsp;&nbsp;述	
		</td>
		<td ><html:textarea property="spec_description" cols="40" rows="3"/>	
		</td>
	</tr>
	<tr bgcolor="ffffff" align="center">
		<td colspan="2">
 
	<!--  <input type="button"  value="添　加" onclick="checksubjectform(this)"/>  -->
	<html:submit value="添　加"/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;			 
	 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;		
	<input type="button" onclick="returnlist()" value="返　回"/>
    </td>
	</tr>
</table>
<script>  
     document.all("spec_state")[0].checked=true;//第一个radio选中  
</script> 
</html:form>
<center>
<!-- 
<br>&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">${message}&nbsp;</font>
 -->
</center>
</body>
<script>
	function checksubjectform(sf){
		var message = "";
		var i = 1;
		var spec_code = document.getElementById("spec_code").value;
		var spec_name = document.getElementById("spec_name").value;
		var spec_description = document.getElementById("spec_description").value;
		
		if(spec_code.length==0||spec_code.length>10){
			message += i+") 专业编号只能为1-10个数字字符\n";
			i++;
		}
		if(spec_name.length==0||spec_name.length>20){
			message += i+") 专业名称只能为1-20个字符\n";
			i++;
		}
				
		if(spec_description==""||spec_description.length>200){
			message += i+") 描述不能为空且小于200字\n";
			i++;
		}
		
		if(message!=""){
			alert(message);
			return false;
		}else{
			return true;
		}
											
	}
	
	function returnlist()
	{　
	 //window.history.back();//返回后没有刷新？？？？？，需要点两次返回按钮　？？？？？
	 var depcode=document.getElementById("dep_code").value;	
	 window.location.href="dep_spec_Manage.do?dep_code="+depcode;
	}
	
	
</script> 
  

