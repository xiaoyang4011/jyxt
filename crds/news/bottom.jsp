<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <body>
    <marquee onMouseOver="this.stop()"  onMouseOut="this.start()" direction=right scrollamount=1 
          scrolldelay=3  behavior="scroll"> 
	<a href="http://rencai.dongying.gov.cn/zhaopin/zhaopinbu.html" alt="东营市人才网"><img border="0" src="pic/1.jpg"></a>
	<a href="http://www.chsi.com.cn/" alt="中国高等教育学生信息网"><img border="0" src="pic/2.jpg"></a>
	<a href="http://www.sdlss.gov.cn/index.aspx" alt="山东就业信息网"><img border="0" src="pic/3.jpg"></a>
	<a href="http://www.slof.com/" alt="胜利油田"><img border="0" src="pic/4.jpg"></a>
	<a href="http://www.chsi.com.cn/" alt="中国高等教育学生信息网"><img border="0" src="pic/5.jpg"></a>
	<a href="http://www.myjob.edu.cn/" alt="中国高校毕业生就业服务信息网"><img border="0" src="pic/6.jpg"></a>
</marquee> 
  </body>
</html>
