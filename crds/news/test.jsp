<%@ page contentType="text/html;charset=gb2312"%>
<%@ page import="java.sql.*" %>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>

<body>
<%Connection con;
  Statement sql;
  ResultSet rs;

try{
	con = DriverManager.getConnection("jdbc:odbc:Database","sa","123");
	sql = con.createStatement();
	rs = sql.executeQuery("select * from movies");	
	out.print("<table width=495 height=200 border=0 align=center cellpadding=0 cellspacing=0 border=0px solid=#c1e1f9>");
	out.print("<tr align=center>");
	out.print("<td height=25 align=left background=pic/title_bg.gif class=style1>");
	out.print("&nbsp;");	
	out.print("<img src=pic/b1.gif>");
	//out.print("<font FONT-FAMILY=Arial,宋体 font-size:5px margin-left:5px margin-top:5px margin-right:5px margin-bottom:5px background-color:#ffffff border:none>");
	out.print("近期招聘会");	
	//out.print("</font>");									
	out.print("</td>");
	out.print("</tr>");
	out.print("<tr>");
	out.print("<td width=100>MovieID</td>");
	out.print("<td width=100>MovieName</td>");
	out.print("</tr>");//<style>a{TEXT-DECORATION:none}</style> 
	
	while(rs.next()){
		out.print("<tr>");
		out.print("<td><a href=login.jsp target=_black>"+rs.getLong(1)+"</a>"+"</td>");
		out.print("<td>"+rs.getString(2)+"</td>");
		out.print("</tr>");		
	}	
	out.print("</table>");	
}catch(SQLException ex){
	out.print(ex);
}
%>	
</body>
</html>
