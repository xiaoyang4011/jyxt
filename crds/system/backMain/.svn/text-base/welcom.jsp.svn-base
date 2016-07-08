<%@ page language="java" import="java.util.*,crds.system.backMain.web.*", pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>welcom</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <a></a>
  <li><a href="<%=path%>/login.do"    >学生页面</a></li>
     <form id="form1" name="form1" method="post" action="<%=path%>/login.do">
  <table width="30%" border="1" align="center">
    <tr>
      <td width="51%"><div align="right">用户名：</div></td>
      <td width="49%"><label>
        <input type="text" name="userid" id="userid" />
      </label></td>
    </tr>
    <tr>
      <td><div align="right">密码：</div></td>
      <td><label>
        <input type="text" name="pw" id="pw" />
      </label></td>
    </tr>
    <tr>
      <td><label>
        <div align="right">
          <input type="reset" name="button" id="button" value="重置" />
          </div>
      </label></td>
      <td><div align="right">
        <input type="submit" name="save" id="save" value="提交" />
      </div></td>
    </tr>
  </table>
</form>
  </body>
</html>
