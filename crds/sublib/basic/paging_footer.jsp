<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<center>
	<form method="post" name="formt"
		action="<%=request.getContextPath()%>/selectA.do">
		<span>输入ID：</span>
		<input type=text name="sublib_id" id="sublib_id">
		<input type=submit name="submit" id="submit" value="查询">
	</form>
</center>