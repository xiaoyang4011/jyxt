<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%String path = request.getContextPath();%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>机构添加</title>
<link href="<%=path %>/res/jeecms/css/admin.css" rel="stylesheet" type="text/css"/>
<link href="<%=path %>/res/common/css/theme.css" rel="stylesheet" type="text/css"/>
<link href="<%=path %>/res/common/css/jquery.validate.css" rel="stylesheet" type="text/css"/>
<link href="<%=path %>/res/common/css/jquery.treeview.css" rel="stylesheet" type="text/css"/>
<link href="<%=path %>/res/common/css/jquery.ui.css" rel="stylesheet" type="text/css"/>
 
<script src="/cms/thirdparty/fckeditor/fckeditor.js" type="text/javascript"></script>
<script src="/cms/thirdparty/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="/cms/res/common/js/jquery.js" type="text/javascript"></script>
<script src="/cms/res/common/js/jquery.ext.js" type="text/javascript"></script>
<script src="/cms/res/common/js/pony.js" type="text/javascript"></script>
<script src="/cms/res/jeecms/js/admin.js" type="text/javascript"></script>
</head>

<body>
<div class="box-positon">
	<div class="rpos">当前位置: 机构管理 - 添加</div>
	<form class="ropt">
		<input type="submit" value="返回列表" onclick="this.form.action='<%=path%>/dep_browse.do';" class="return-button"/>
	</form>
	<div class="clear"></div>
</div>
<div class="body-box">
<c:forEach items="${list}" var="map" varStatus="cnt">
<form id="form1" name="form1" method="post" action="<%=path %>/dep_update.do" id="jvForm" onsubmit="return checkstudentform(this)">
<table align="center" width="80%" class="pn-ftable" cellpadding="2" cellspacing="1" border="0">
<tr>
<td width="30%" class="pn-flabel pn-flabel-h"><span class="pn-frequired">*</span>机构编号</td>
<td colspan="1" width="70%" class="pn-fcontent">
	<input type="text" maxlength="100" name="dep_code" value="${map.dep_code}" maxlength="100" readonly="readonly"/> <span class="pn-fhelp">由不超过10位的字符组成</span>
</td>
</tr>
<tr>
<td width="30%" class="pn-flabel pn-flabel-h"><span class="pn-frequired">*</span>机构名称</td>
<td colspan="1" width="70%" class="pn-fcontent">
	<input type="text" maxlength="100" name="dep_name" maxlength="100" onkeypress="isIncSym(this)" value="${map.dep_name}"/>
</td>
</tr>
<tr><td colspan="2" class="pn-fbutton">	
<input type="submit" value="提交" class="submit" class="submit"/> &nbsp; <input type="reset" value="重置" class="reset" class="reset"/></td>
</tr></table>
</form>
 </c:forEach>
</div>
<script>

	function checkstudentform(sf){
		var message = "";
		var i = 1;	
		
		if(sf.name.value==""||sf.name.value.length>15){
			message += i+") 角色ID不能为空且小于15字\n";
			i++;
		}
		if(sf.address.value==""||sf.address.value.length>20){
			message += i+") 角色名不能为空且小于20字\n";
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
	function isRolename(s){
		var patrn=/^(\w){6,30}$/;
		if (!patrn.exec(s)) 
			return false;
		return true;
	}
	function checkComments() {
		if ((event.keyCode > 32 && event.keyCode < 48)
				|| (event.keyCode > 57 && event.keyCode < 65)
				|| (event.keyCode > 90 && event.keyCode < 97)) {
			alert("不可以输入非法字符");
			event.returnValue = false;
		}
	}	
</script>  
</body>
</html>

