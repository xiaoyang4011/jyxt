<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>新闻内容</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

<style type="text/css">
<!--
.STYLE1 {
	font-size: xx-large;
	font-weight: bold;
}
-->
</style>
  </head>




<body>
    <c:if test="${not empty listmessage}">
	  <%--如果不为空进行循环 显示直到为空--%>
	  <c:forEach items="${listmessage}" var="map" varStatus="cnt">      
	<div align="center" class="STYLE1">${map.new_title}</div>
	<p align="center">发布时间：${map.new_date} 发布人:${map.new_author}</p>
	<p align="center">${map.news} </p>
	  </c:forEach>
	  </c:if>

</body>
</html>
