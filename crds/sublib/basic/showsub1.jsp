<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/pub/include/common.jsp"%>
<html:html>
<head>
</head>

<body>
	<div align="right">
		<a href=sublib/basic/show.jsp>返回首页</a>
	</div>
	This is my JSP page.
	<form action="Dephead_Main.do?listP=1" method="post" name="form1">
		<html:hidden name="formStud" property="pageNo" />
		<html:hidden name="formStud" property="rowsPerPage" />
		<table width="675" border="0" align="center" cellpadding="4"
			cellspacing="1" bgcolor="#3D7BA3">
			<tr align="center" bgcolor="94C9E7">
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
			</tr>
			<c:forEach items="${student}" var="map" varStatus="cnt">
				<tr align="center" bgcolor="ffffff">
					<td>
						${map.sublib_id}
					</td>
					<td>
						${map.restype_name}
					</td>
					<td>
						${map.spec_code}
					</td>
					<td>
						${map.sublib_code}
					</td>
					<td>
						${map.sublib_title}
					</td>
					<td>
						${map.sublib_state}
					</td>
					<td>
						<html:link page="/jump3.do?sublib_id=${map.sublib_id}">修改</html:link>
						|
						<html:link page="/delsub.do?sublibid=${map.sublib_id}"
							onclick="return window.confirm('是否真要删除这个学生？')">删除</html:link>
					</td>
				</tr>
			</c:forEach>
		</table>
		<table width="675" border="0" align="center" cellpadding="4"
			cellspacing="1" id="navigate">
			<tr>
				<td align="right">
					<app:navigate objectName="formStud" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html:html>
