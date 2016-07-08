<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head><meta http-equiv=Content-Type content='text/html; charset=gbk'>

<title>用户登录 </title>
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<link rel="stylesheet" type="text/css" href="css/style.css" media="all" />
<script type="text/javascript">
function goCorpRegPage() {
	window.open("http://127.0.0.1:8080/Career/index.jsp");
}
</script>
</head>
<body style="background-color:#ffffff;">
<form id="form1" action="<%=path%>/browse1.do" method="post" name="form1" onSubmit="return login();">
<table width="206" height="201" border="0" align="center" cellpadding="0" cellspacing="0" style= "border:1px solid #c1e1f9;">
  <tr align="center">
    <td height="25" align="left" background="pic/title_bg.gif" class="style1">&nbsp;<img src="pic/b1.gif"> 用户登录</td>
  </tr>
  <tr align="center">
    <td height="173">用户名：
      <input type="text" name="j_username" id="j_username" title="用户名不能为空" style="width:120px;height:20px;border:#bacad9 1px solid;"/>
      <br>
      <br>
     	 密　码：
      <input type="password" name="j_password" id="j_password" value="" style="width:120px;height:20px;border:#bacad9 1px solid;"/>
      <br>
      <br>
      	验证码：
      <input type="text" id="j_digitPicture" name="j_digitPicture" maxlength=4 title="请填写验证码" value="" style="width:60px;height:20px;border:#bacad9 1px solid;"/>
      <img src="pic/initDigitPicture.do-Rgb=255-255-255&width=60&height=24.jpg" height="20" width="55" align="middle">
      <p align="right">
      	<input type="image" src="pic/login.gif" name="submit" />
      	<img src="pic/join.gif"  onClick="goCorpRegPage();return false;" style="cursor: pointer;">
      	&nbsp;
      	</p></td>
  </tr>
  <tr>
			<td align="center" ></td>
	</tr>
</table>
</form>
<script language="javascript">
	function login() {
		var u = document.getElementById('j_username') ;
		var p = document.getElementById('j_password') ;
		if(u.value == ''){
			alert("用户名不能为空.");
			u.focus();
			return false;
		}
		if(p.value == ''){
			alert("密码不能为空.");
			p.focus();
			return false;
		}
		var pict = document.getElementById('j_digitPicture').value ;
		if( pict == '' || pict.length != 4 || isNaN(pict)){
		    alert("请输入正确的图片验证码!");
		    document.getElementById('j_digitPicture').focus();
			return false;
		}
		return true ;
	}
</script>
</body>
</html>