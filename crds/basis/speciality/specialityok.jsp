 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
　
 <%@ include file="/pub/include/common.jsp"%>
 
<body><br>
<html:form    action="spec_querynew.do" method="post">
  <html:hidden name="formSpeciality" property="pageNo" />
  <html:hidden name="formSpeciality" property="rowsPerPage" />
  <html:hidden name="formSpeciality" property="dep_code" /> 
 
 </html:form>
　
 <%int para=Integer.parseInt(request.getParameter("para")); 
switch(para){
	case 1:
	%>
		<script language="javascript">	//添加专业成功的处理	 
		 var depcode=document.getElementById("dep_code").value;	  
		// window.location.href="speciality_query.do?listpage=1&dep_code="+depcode;
		//window.location.href="specialityadmin.do";
		  window.location.href="query1.do?dep_code="+depcode;	
		</script>	
	<%break;
	case 2:
	%>
		<script language="javascript">
		alert("专业信息修改成功!");
		var depcode=document.getElementById("dep_code").value;	
		
        // window.location.href="speciality_query.do?listpage=1&dep_code="+depcode;
	   //window.open("speciality_query.do?listpage=1&dep_code="+depcode,"listPage");
	   window.location.href="query1.do?dep_code="+depcode;
	
		</script>		
	<%	break;
	case 3:
	%>
		<script language="javascript">
		alert("专业信息删除成功!");		
		var depcode=document.getElementById("dep_code").value;	  
		 window.location.href="speciality_query.do?listpage=1&dep_code="+depcode;
		</script>		
	<%	break;
	case 4:
	%>
	   <script language="javascript">
		<c:if test="${not empty message}">alert('${message}');</c:if>  // 专业信息删除失败的处理
		var depcode=document.getElementById("dep_code").value;	  
		window.location.href="speciality_query.do?listpage=1&dep_code="+depcode;
		</script>		
	<%	break;
	case 5:
	%>
		<script language="javascript">	//系部添加专业成功的处理	 
		 var depcode=document.getElementById("dep_code").value;	  
		// window.location.href="speciality_query.do?listpage=1&dep_code="+depcode;
		//window.location.href="specialityadmin.do";
		  window.location.href="query2.do?dep_code="+depcode;	
		</script>	
	<%break;
	case 6:
	%>
		<script language="javascript">//系部修改专业信息的处理
		alert("专业信息修改成功!");
		var depcode=document.getElementById("dep_code").value;	
		
        // window.location.href="speciality_query.do?listpage=1&dep_code="+depcode;
	   //window.open("speciality_query.do?listpage=1&dep_code="+depcode,"listPage");
	   window.location.href="query2.do?dep_code="+depcode;
	
		</script>		
	<%	break;
}
%>
</body>
<script>
	 
	
</script>