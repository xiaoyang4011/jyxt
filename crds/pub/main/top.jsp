<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="crds.pub.util.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD><TITLE>hg</TITLE>
<META http-equiv=Content-Type content="text/html; charset=utf-8"><LINK 
href="images/default.css" type=text/css rel=stylesheet>
<META content="MSHTML 6.00.3790.259" name=GENERATOR>
<style type="text/css">
<!--
.style1 {color: #FFFFFF}
-->
</style>
<script>
<%String path = request.getContextPath();%>
function closeWinClearSession(event) {
	if(event.clientX<=0 && event.clientY<0) {
		var xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		xmlhttp.open("GET","<%=path%>/pub/main/0000closeWindow.jsp", false);
		xmlhttp.send();
	} else {
		//alert("刷新或离开");
	}
}
</script>

</HEAD>
<BODY leftMargin=0 topMargin=0 onunload="closeWinClearSession(event)">
<TABLE cellSpacing=0 cellPadding=0 width="100%" 
background="images/top_bg.png" border=0>
  <TBODY>
  <TR>
    <TD align="center" height=49 width=80  align=center ><font color="#ffffff" size="3" face="楷体"><img src="xb.png" width="45" height="45" /></font></TD>
     <TD height=49 width=150 align=center ><font color="#ffffff" size="3" face="楷体"> 胜利学院就业系统</font></TD>
    <TD style="PADDING-RIGHT: 20px">
      <TABLE width="800" align=right class=wht>
        <TBODY>
       
        <TR>
          <TD width="480" align=center class=wht>


</TD>

            <TD width="80" align=right class=wht>
            <img hspace="5" height="7" width="6" src="images/arrow_white.gif" algin="absmiddle"><a href="<%=path%>/front/index.do" target="_top">返回主页</a></font> </TD>
             <TD width="80" align=right class=wht>
            <img hspace="5" height="7" width="6" src="images/arrow_white.gif" algin="absmiddle"><a href="<%=path%>/logout.do" target="_top">退出系统</a></font> </TD>
      </TR></TBODY></TABLE></TD></TR></TBODY></TABLE></BODY></HTML>
