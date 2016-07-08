<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

  <head>
 
    <base href="<%=basePath%>">
   <script type="text/javascript">
     alert("积分增加完成！");
    var url = "<%=path %>/userindoPage.do"; 
   	window.location=url;  
   </script>
  </head>
  
</html>
