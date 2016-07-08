<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>

<%String path = request.getContextPath();%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<html>
		<head>
			<title>班级管理</title>
			<link href="../../pub1/css/common.css" rel="stylesheet"
				type="text/css">
			<link href="../../pub1/css/default.css" rel="stylesheet"
				type="text/css">

			<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

			<link href="<%=path%>/res/jeecms/css/admin.css" rel="stylesheet"
				type="text/css" />
			<link href="<%=path%>/res/common/css/theme.css" rel="stylesheet"
				type="text/css" />
			<link href="<%=path%>/res/common/css/jquery.validate.css"
				rel="stylesheet" type="text/css" />
			<link href="<%=path%>/res/common/css/jquery.treeview.css"
				rel="stylesheet" type="text/css" />
			<link href="<%=path%>/res/common/css/jquery.ui.css" rel="stylesheet"
				type="text/css" />
			<script src="<%=path%>/res/common/js/jquery.js"
				type="text/javascript"></script>
			<script src="<%=path%>/res/common/js/jquery.ext.js"
				type="text/javascript"></script>
			<script src="<%=path%>/res/common/js/pony.js" type="text/javascript"></script>
			<script src="<%=path%>/res/jeecms/js/admin.js" type="text/javascript"></script>

			<script type='text/javascript' src='<%=path%>/dwr/engine.js'></script>
			<script type='text/javascript' src='<%=path%>/dwr/util.js'></script>
			<script type='text/javascript'
				src='<%=path%>/dwr/interface/SublibThreeLevel.js'></script>



		</head>
		<body onload="getDeptList()">
			<div class="box-positon">
				<div class="rpos">
					当前位置: 班级管理 - 修改
					
				</div>
				<form class="ropt">
					<input type="submit" value="返回列表"
						onclick="this.form.action='<%=path%>/Class.do';"
						class="return-button" />
				</form>
				<div class="clear"></div>
			</div>
			<div class="body-box">
				<form method="post"
					action="<%=request.getContextPath()%>/UpdateClasssucc.do?class_code=<%=request.getParameter("class_code")%>" onsubmit="return checkform(this)">
					<table align="center" width="80%" class="pn-ftable" cellpadding="2"
						cellspacing="1" border="0">
						<tr>
							<td width="30%" class="pn-flabel pn-flabel-h">
								<span class="pn-frequired">*</span>班级编号：
							</td>
							<td colspan="1" width="70%" class="pn-fcontent">
							<%=request.getParameter("class_code")%>
								
							</td>
						</tr>
						<tr>
							<td width="30%" class="pn-flabel pn-flabel-h">
								<span class="pn-frequired">*</span>专业代码：
							</td>
							<div>
								<td colspan="1" width="70%" class="pn-fcontent">
									<%=request.getParameter("spec_code")%>
								</td>
							</div>
						</tr>
						<tr>
							<td width="30%" class="pn-flabel pn-flabel-h">
								<span class="pn-frequired">*</span>班级名称：
							</td>
							<td colspan="1" width="70%" class="pn-fcontent">
								<input type="text" name="class_name" id="class_name"
									value="<%=new String(request.getParameter("class_name").getBytes("iso8859_1"),"UTF-8") %>" />
							</td>
						</tr>
						<tr>
							<td colspan="2" class="pn-fbutton">
							<input type="submit" class="submit" class="submit" value="提交"
									onclick="return window.confirm('修改课程成功！')" />
								
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="reset" name="button2" id="button2" value="重置"
									class="reset" class="reset" />
							</td>
						</tr>
					</table>
				</form>
			</div>
			<script>
	function checkform(sf){
		var message = "";
		var i = 1;		
		if(sf.class_code.value==""){
			message += i+") 课程编不能为空！ ";
			i++;
		}	
		if(sf.class_name.value==""||sf.class_name.value.length>40){
			message += i+") 班级名称不能为空且不超过40个字符\n";
			i++;
		}	
		if(message!=""){
			alert(message);
			return false;
		}else{
			return true;
		}
											
	}
	//校验用户名：只能输入1-20个字母、数字、下划线
	function isRolename(s){
		var patrn=/^(\w){1,20}$/;
		if (!patrn.exec(s)) 
			return false;
		return true;
	}
	//校验用户id：只能输入1-2个字母、数字、下划线
	function isRoleid(s){
		var patrn=/^(\w){1,2}$/;
		if (!patrn.exec(s)) 
			return false;
		return true;
	}	
</script> 
		</body>
	</html>