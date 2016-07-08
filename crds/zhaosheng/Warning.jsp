<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>
<html>
<body>
<%--获取struts中的id的值并使用switch...case语句判断id的值并显示提示信息--%>
<%int para=Integer.parseInt(request.getParameter("id"));
switch(para){
	case 1:
	%>
		<script language="javascript">
		alert("删除失败!");
		window.location.href="browse.do";
		</script>	
	<%	break;
	case 2:
	%>
		<script language="javascript">
		alert("删除成功!");
		window.location.href="browse.do";
		</script>			
	<%	break;
	case 3:
	%>
		<script language="javascript">
		alert("保存成功!");
		window.location.href="browse.do";
		</script>	
	<%	break;
	case 4:
	%>
		<script language="javascript">
		alert("保存失败!");
		window.location.href="browse.do";
		</script>			
	<%	break;
		case 5:
	%>
		<script language="javascript">
		alert("修改保存成功!");
		window.location.href="bro_stud.do";
		</script>			
	<%	break;
		case 6:
	%>
		<script language="javascript">
		alert("修改保存失败!");
		window.location.href="bro_stud.do";
		</script>			
	<%	break;
}
%>
</body>
</html>