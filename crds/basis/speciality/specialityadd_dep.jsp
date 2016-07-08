<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ page import="crds.pub.util.FormUserOperation"%>
<%@ page import="crds.pub.util.Constant"%>
<%String path = request.getContextPath();%>
<!-- 获取用户所在部门的编号， -->

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>专业管理-添加专业</title>
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

    <script language="javascript">
    <c:if test="${not empty message}">alert('${message}');</c:if>
    </script>

<body>
 <div class="box-positon">
	<div class="rpos">当前位置: 专业管理 - 添加专业</div>
	<form class="ropt">
	<input type="button" value="返回列表" onclick="returnlist()" class="return-button"/>
	</form>
	<div class="clear"></div>
</div>
<div align="center">&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">${message}&nbsp;</font></div>
<div class="body-box">
<br/>
 <html:form action="/specialityadd_dep.do" method="post" onsubmit="return checksubjectform(this)">
<table align="center" width="80%" class="pn-ftable" cellpadding="2" cellspacing="1" border="0">

<tr>
		<td width="30%" class="pn-flabel pn-flabel-h">
	<font size="2">所属系部</font>	
		</td>
		<td colspan="1" width="70%" class="pn-fcontent">

	</td>
	</tr>
	<tr>
		<td width="30%" class="pn-flabel pn-flabel-h"><span class="pn-frequired">*</span>
	<font size="2">专业代码</font>	
		</td>
		<td colspan="1" width="70%" class="pn-fcontent">
	<html:text property="spec_code"/>
	
		</td>
	</tr>
	 <tr>
		<td width="30%" class="pn-flabel pn-flabel-h"><span class="pn-frequired">*</span>
	<font size="2">专业名称</font>	
		</td>
		<td colspan="1" width="70%" class="pn-fcontent"><html:text property="spec_name"/>	
		</td>
	</tr>
	<tr>
		<td width="30%" class="pn-flabel pn-flabel-h"><span class="pn-frequired">*</span>
	<font size="2">专业状态</font>	
		</td>
		<td colspan="1" width="70%" class="pn-fcontent">
	<html:radio property="spec_state" value="1"/><font size="2">打开</font>&nbsp;&nbsp;<html:radio property="spec_state" value="0"/><font size="2">关闭</font>	
		</td>
	</tr>
	 <tr>
		<td width="30%" class="pn-flabel pn-flabel-h">
	<font size="2">级别</font>	
		</td>
		<td colspan="1" width="70%" class="pn-fcontent">
		<select id="showtype" name="showtype">
		<option value="普通" checked="selected">普通</option>
		<option value="省部级">省部级</option>
		<option value="国家级">国家级</option>
		</select>
        </td>
	</tr>
	<tr>
		<td width="30%" class="pn-flabel pn-flabel-h">
	<font size="2">描&nbsp;&nbsp;&nbsp;&nbsp;述</font>	
		</td>
		<td colspan="1" width="70%" class="pn-fcontent"><html:textarea property="spec_description" cols="40" rows="3"/>	
		<br></td>
	</tr>
	
<tr><td colspan="2" class="pn-fbutton">	
<input type="submit" value="提交" class="submit" /> &nbsp;&nbsp;&nbsp;&nbsp; <input type="reset" value="重置"  class="reset"/></td>
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
	window.location.href="query2.do?dep_code="+depcode;
	}
	
	
</script> 
  

