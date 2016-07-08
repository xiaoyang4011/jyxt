<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		
	</head>
	<body>
		<div
			style="PADDING-RIGHT: 0px; PADDING-LEFT: 0px; FLOAT: left; PADDING-BOTTOM: 0px; WIDTH: 206px; PADDING-TOP: 0px">
			<table width="495" height="200" border="0" align="center"
				cellpadding="0" cellspacing="0" style="border: 1px solid #c1e1f9;">
				<tr align="center">
					<td height="25" align="left" 
						class="style1">
						&nbsp;
						<img src="news/pic/b1.gif">
						<a href="." target="_self" class="style1" title="点击查看更多"> 重要通知
						</a>
					</td>
				</tr>
			 <c:if test="${not empty listmessage}">
			   <c:forEach items="${listmessage}" var="map" varStatus="cnt">   
				<tr align="left">
							<td>

								<ul>
									<li class="dotted_line">
										<table height="100%" valign="top" width="100%" border="0"
											cellpadding="0" cellspacing="0">
											<tr>
												<td>
													&nbsp;
													<img src="news/pic/b2.gif">
													<a href="." target="_blank" title=" ${map.new_title}">
														&nbsp; <font color="ff0000"> ${map.new_title} </font> </a>
												</td>
											</tr>
										</table>
									</li>
								</ul>
							</td>
						</tr>	
				 </c:forEach>	
				  </c:if>		
			</table>
		</div>
	</body>
</html>
