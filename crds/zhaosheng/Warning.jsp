<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>
<html>
<body>
<%--��ȡstruts�е�id��ֵ��ʹ��switch...case����ж�id��ֵ����ʾ��ʾ��Ϣ--%>
<%int para=Integer.parseInt(request.getParameter("id"));
switch(para){
	case 1:
	%>
		<script language="javascript">
		alert("ɾ��ʧ��!");
		window.location.href="browse.do";
		</script>	
	<%	break;
	case 2:
	%>
		<script language="javascript">
		alert("ɾ���ɹ�!");
		window.location.href="browse.do";
		</script>			
	<%	break;
	case 3:
	%>
		<script language="javascript">
		alert("����ɹ�!");
		window.location.href="browse.do";
		</script>	
	<%	break;
	case 4:
	%>
		<script language="javascript">
		alert("����ʧ��!");
		window.location.href="browse.do";
		</script>			
	<%	break;
		case 5:
	%>
		<script language="javascript">
		alert("�޸ı���ɹ�!");
		window.location.href="bro_stud.do";
		</script>			
	<%	break;
		case 6:
	%>
		<script language="javascript">
		alert("�޸ı���ʧ��!");
		window.location.href="bro_stud.do";
		</script>			
	<%	break;
}
%>
</body>
</html>