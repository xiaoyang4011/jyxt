<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="news/css/style.css" />
<title>最新动态</title>
<style type="text/css">
.gengduo {font-size: 12px;
	color: #333;
	font-family: "微软雅黑";
}
</style>
</head>

<body leftmargin="0" topmargin="0">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td><table width="100%" height="245" border="0" bgcolor="#DCF1FC" cellspacing="1">
      <tr>
      <div 
			style="PADDING-RIGHT: 0px; PADDING-LEFT: 0px; FLOAT: left; PADDING-BOTTOM: 0px; WIDTH: 206px; PADDING-TOP: 0px bgcolor:;">
      			
				
			 <c:if test="${not empty listmessage}">
			   <c:forEach items="${listmessage}" var="map" varStatus="cnt">   
				<tr align="left">
							<td bgcolor="#ffffff" valign="bottom">

								
									
										<table height="100%" valign="top" width="100%" border="0"
											cellpadding="0" cellspacing="0"  bgcolor="#ffffff">
											<tr>
												<td>
													&nbsp;
													<img src="news/pic/b2.gif">
													<a href="<%=path%>/browse2.do?new_id=${map.new_id}" target="_blank">
														&nbsp; <font color="000000"> ${map.new_title} </font> </a>
												</td>
											</tr>
										</table>
									
								
							</td>
						</tr>	
				 </c:forEach>	
				  </c:if>		
			
			</div>
      </tr>
      <tr>
        <td height="20" align="right" valign="middle" bgcolor="#ffffff" class="gengduo"><a href="../xwgg.html" target="_new"><img src="show/im/anniu1.jpg" alt="" width="75" height="20" border="0" /></a></td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>
