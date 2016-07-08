<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/pub/include/common.jsp"%>
<%
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<STYLE type=text/css>
.dotline {
	BORDER-BOTTOM-STYLE: dotted;
	BORDER-LEFT-STYLE: dotted;
	BORDER-RIGHT-STYLE: dotted;
	BORDER-TOP-STYLE: dotted
}
</STYLE>

	</head>

	<body>
		<div align="right">
			<a href=sublib/basic/show.jsp>返回首页</a>||
			<a href="<%=request.getContextPath()%>/jump2.do">增加子库</a>||
			<a href="<%=request.getContextPath()%>/test.do">显示全部</a>
		</div>
		<hr class=dotline color=#ce0000 size=1>
		<form action="test.do?listPage=1" method="post" name="form1">
			<html:hidden name="formStud" property="pageNo" />
			<html:hidden name="formStud" property="rowsPerPage" />
			<center>
				<input type="text" name=sublib_id id="sublib_id" />
				<input type="submit" name="button" id="button" value="查询"
					onclick="checksublib_id()" />
			</center>
			<table width="675" border="0" align="center" cellpadding="4"
				cellspacing="1" bgcolor="#3D7BA3">
				<tr align="center" bgcolor="94C9E7" class="showsub">
					<td>
						子库ID
					</td>
					<td>
						资源类型
					</td>
					<td>
						专业编码
					</td>
					<td>
						子库编码
					</td>
					<td>
						子库标题
					</td>
					<td>
						子库状态
					</td>
					<td>
						操作
					</td>
				</tr>
				<logic:present name="student">
					<logic:iterate name="student" id="map">
						<tr align="center" bgcolor="ffffff" class="showsub">
							<td>
								${map.sublib_id}
							</td>
							<td>
								${map.restype_name}
							</td>
							<td>
								${map.spec_name}
							</td>
							<td>
								${map.sublib_code}
							</td>
							<td>
								${map.sublib_title}
							</td>
							<td>
								${map.sublib_states}
							</td>
							<td>
								<html:link page="/jump3.do?sublib_id=${map.sublib_id}">修改</html:link>
								|
								<html:link page="/delsub.do?sublibid=${map.sublib_id}"
									onclick="return window.confirm('是否真要删除这个学生？')">删除</html:link>
									|<html:link page="/system/head/headManage.do?dbType=03&key=${map.sublib_code}">子库负责人</html:link>
							</td>
						</tr>
					</logic:iterate>
				</logic:present>
			</table>
			<table width="675" border="0" align="center" cellpadding="4"
				cellspacing="1" id="navigate">
				<tr>
					<td align="right">
						<app:navigate objectName="formStud" />
					</td>
				</tr>
			</table>
			<hr class=dotline color=#ce0000 size=1>
		</form>

	</body>
	<script type="text/javascript">
		function checksublib_id()
	{
	 var form1 = document.forms[0];	 
     form1.pageNo.value=1;
	}</script>
</html>
