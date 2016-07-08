

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%String path = request.getContextPath();%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>专业管理</title>

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
 	<script type='text/javascript' src='<%=path%>/dwr/engine.js'></script>
	<script type='text/javascript' src='<%=path%>/dwr/util.js'></script>
	<script type='text/javascript' src='<%=path%>/dwr/interface/DepartThreeManage.js'></script>  <!--dwr.xml配置文件里写的名字-->
	<script type="text/javascript">
		
	$(function() {
		dwr.engine.setAsync(false);//设置是否为异步调用,默认为true,异步调用。一般来说把它设置为false会使浏览器运行变慢。		
		getDepartmentList();
		getSpecialityList();
		getCourseList();
	});

	function getDepartmentList() {
		DepartThreeManage.findDepartment({
			callback : function(departmentlist) {
				dwr.util.removeAllOptions("sel_Dep");
				dwr.util.addOptions("sel_Dep", [ {
					name : "-请选择-",
					value : "0"
				} ], "value", "name");
				dwr.util.addOptions("sel_Dep", departmentlist, "dep_code","dep_name");
			}
		});
	}
	
</script>
<script type="text/javascript"> 

function getTableForm() {
	return document.getElementById('queryroleform');
}
function optDelete() {
	if(Pn.checkedCount('ids')<=0) {
		alert("请选择您要操作的数据");
		return;
	}
	if(!confirm("您确定删除吗？")) {
		return;
	}
	var f = getTableForm();
	f.action="/system/role/deleteRole.do";
	f.submit();
}
function optCheck() {
	if(Pn.checkedCount('ids')<=0) {
		alert("请选择您要操作的数据");
		return;
	}
	var f = getTableForm();
	f.action="o_check.do";
	f.submit();
}



function chgStatus() {
	var queryStatus = $("input[name=queryStatus]:checked").val();
	location.href="v_list.do?cid=43&queryStatus=" + queryStatus;
}
</script>
</head>
<body onload="getDepartmentList()">
<div class="box-positon">
	<div class="rpos">当前位置: 专业管理 - 列表</div>
	<form class="ropt" action="<%=path%>/speciality_add.do"  method="post">
		<input class="add" type="button" onclick="specialityadd()" value="添加" />		
	</form>&nbsp;
	<div class="clear"></div>
</div>
<div align="center">&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">${message}&nbsp;</font></div>
<div class="body-box">
<html:form styleId="queryroleform" action="/speciality_query.do" method="post" style="padding-top:5px;"> 

<div>
&nbsp;所属系部:
 <select id="sel_Dep"  style="width:200">   
</select>
<input class="query" type="button" onclick="checkDepCode()" value="查询"/>
</div>
<div class="clear"></div>

<div align="center">
			<iframe name="listPage" src="<%=path%>/speciality_query.do?listpage=1&dep_code=<%=request.getParameter("dep_code")%>" frameborder="0" scrolling="no" width="100%"
			onload="height = document.frames(this.name).document.body.scrollHeight+30"	 />
			</iframe>
		</div>
</html:form>
</BODY>


<script>
	
	//部门下拉列表的修改事件 
	function checkDepCode()
	{
	 var dep_code = document.getElementById("sel_Dep").value;
	 var form1 = document.frames("listPage").document.forms[0];
     form1.dep_code.value=dep_code;
     form1.pageNo.value=1;
     form1.submit();
	}
	
	
 function specialityadd()
 {
   location.href("speciality_add.do");
   
 }	
</script>