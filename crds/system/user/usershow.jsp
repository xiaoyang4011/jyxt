<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/app.tld" prefix="app"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*,crds.pub.util.*,java.lang.Math"%>
<%
	String path = request.getContextPath();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>山东水利职业学院---教学资源库管理平台</title>

<link href="<%=path %>/resource/upload/images/admincp/admincp.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=path%>/fckeditor/fckeditor.js"></script>
<script src="<%=path%>/resource/upload/include/javascript/common.js" type="text/javascript"></script>
	<script type='text/javascript' src='<%=path%>/dwr/engine.js'></script>
	<script type='text/javascript' src='<%=path%>/dwr/util.js'></script>
	<script type='text/javascript' src='<%=path%>/dwr/interface/SublibThreeLevel.js'></script>

<script type="text/javascript">


$(function() {
		dwr.engine.setAsync(false);
		getDeptList();
		getSpecList();
		getSublibList();
		getCourseList();		
	});

	function getDeptList() {
		SublibThreeLevel.findDepartment({
			callback : function(deptlist) {
				dwr.util.removeAllOptions("dept");
				dwr.util.addOptions("dept", [ {
					name : "-请选择-",
					value : "0"
				} ], "value", "name");
				dwr.util.addOptions("dept", deptlist, "dep_code",
						"dep_name");
			},
			
		});
	}

	function getSpecList() {
		SublibThreeLevel.findSpeciality(dwr.util.getValue("dept"), {
			callback : function(speclist) {
				dwr.util.removeAllOptions("spec");
				dwr.util.addOptions("spec", [ {
					name : "-请选择-",
					value : "0"
				} ], "value", "name");
				dwr.util.addOptions("spec", speclist, "spec_code", "spec_name");
			},
			
		});
	}
	
	function getSublibList() {
		SublibThreeLevel.findSublib(dwr.util.getValue("spec"), {
			callback : function(subliblist) {
				dwr.util.removeAllOptions("sublib_id");
				dwr.util.addOptions("sublib_id", [ {
					name : "-请选择-",
					value : "0"
				} ], "value", "name");
				dwr.util.addOptions("sublib_id", subliblist, "sublib_id", "sublib_title");
			},
			
		});
	}
	function getCourseList() {
		SublibThreeLevel.findCourse(dwr.util.getValue("spec"), {
			callback : function(courselist) {
				dwr.util.removeAllOptions("allcourse");			
				dwr.util.addOptions("allcourse", courselist, "course_code", "course_name");
			},
			
		});
	}
	


</script>
</head>
<body leftmargin="10" topmargin="10" onload="getDeptList()" style="display: yes;background: #F7F7F7;">
	     
				
		<html:form action="/userindoPage.do?listP=1" method="post">
			<html:hidden name="adduserForm" property="pageNo" />
			<html:hidden name="adduserForm" property="rowsPerPage" />
			<center>
			</center>


           <tr>
      <td class=altbg1 width="40%">
      	<b>三级联动:</b>
      	<br /><span class=smalltxt>请依次选择系部、专业、子库。
      	[必选项]</span>
      </td>
      <td class=altbg2>
      <select name="dept" id="dept" onchange="getSpecList()">								
				</select>
	 <select name="spec" id="spec" onchange="getSublibList();getCourseList();">					
				</select>
	 <select name="sublib_id" id="sublib_id" onchange="getFileType()">									
				</select>
      
      </td>
      <td width="80" bgcolor="#0000FF" width="95"><a href="<%=path%>/userindoPage.do">查看全部</a></td>
	<td width="80" bgcolor="#0000FF" width="127"><a href="<%=path%>/addUserPackage.do">添加用户</a> </td>
   </tr>  



			<table border="0" align="center" cellpadding="4"
				cellspacing="1" bgcolor="#3D7BA3">
				<tr align="center" bgcolor="#0000FF" class="usershow">
				  <td bgcolor="#0000FF">机构名称：</td>
				  <td>
                   <select id="dep_code" name="dep_code" style="width: 80">
								<option value="0">
									全部
								</option>
								<c:forEach items="${deplist}" var="map1" varStatus="cnt">
									<option value="${map1.dep_code}">
										${map1.dep_name}
									</option>								 
								</c:forEach>
					</select>
                  </td>
				  <td>角色：</td>
				  <td>
                  <select id="role_id" name="role_id" style="width: 80">
								<option value="0">
									全部
								</option>
								<c:forEach items="${rolelist}" var="map1" varStatus="cnt">
									<option value="${map1.role_id}">
										${map1.role_name}
									</option>								 
								</c:forEach>
					</select>
                  </td>
                  <td>真实姓名：</td>
				  <td><input type="text" name="real_name" id="real_name" size="10" /></td>
				  <td>用户ID：</td>
				  <td><input type="text" name="user_ID" id="user_ID" size="10" /></td>
				  <td><input type="submit" name="button" id="button" value="查询" /></td>
				  <!-- <td><a href="<%=path%>/userindoPage.do">查看全部用户</a></td>
				  <td><a href="<%=path%>/addUserPackage.do">添加用户</a></td> -->
				  
				  <td>&nbsp;</td>
			  </tr>
			  <tr></tr>
			  
				<tr align="center" bgcolor="94C9E7" class="usershow">
				   <td width="80">
										</td>
					<td width="80">
						用户ID					</td>
					<td width="80">
						机构编码					</td>
					<td width="80">
						专业编码					</td>
					<td width="80">
						班级编码					</td>
					<td width="80">
						用户名					</td>
					<td width="80">
						密码					</td>
					<td width="80">
						真实名					</td>
					<td width="80">
						用户类型					</td>
					<td width="80">
						角色ID					</td>
					<td width="80">
						会员类型					</td>
					<td width="80">
						用户积分					</td>

					<td width="80">
						修改|删除用户					</td>
				</tr>
				<logic:present name="student">
					<logic:iterate name="student" id="map">
						<tr align="center" bgcolor="ffffff" class="usershow">
						<td>
							<input type="checkbox" name="1" id="1" value="${map.user_ID}"/>
						</td>
							<td>
								${map.user_ID}							</td>
							<td>
								${map.dep_name}							</td>
							<td>
								${map.spec_name}							</td>
							<td>
								${map.class_name}							</td>
							<td>
								${map.user_name}							</td>
							<td>
								${map.password}							</td>
							<td>
								${map.real_name}							</td>
							<td>
								${map.user_type}							</td>
							<td>
								${map.role_name}							</td>
							<td>
								${map.member_type}							</td>
							<td>
								${map.user_point}							</td>
							<td>
								<html:link
									page="/update.do?user_ID=${map.user_ID}&user_name=${map.user_name}&password=${map.password}&real_name=${map.real_name}&membertype=${map.member_type}&user_point=${map.user_point}">修改</html:link>
								|
								<html:link page="/delete.do?userid=${map.user_ID}"
									onclick="return window.confirm('是否真要删除这个学生？')">删除</html:link>							</td>
						</tr>
					</logic:iterate>
				</logic:present>
			</table>
			<table border="0" cellpadding="0" cellspacing="0" width="70%"
				align="center" id="navigate">
				<tr>
					<td align="right">
						<app:navigate objectName="adduserForm" />
					</td>
				</tr>
			</table>
		</html:form>
		<br>
		<br>
</body>


</html>
