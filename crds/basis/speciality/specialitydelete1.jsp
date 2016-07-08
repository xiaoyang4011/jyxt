<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
　
 <%@ include file="/pub/include/common.jsp"%>
<head>
<style>
a {text-decoration: none;}
</style>

</head>

<body>
 <html:form    action="spec_querynew.do" method="post">
  <html:hidden name="formSpeciality" property="pageNo" />
  <html:hidden name="formSpeciality" property="rowsPerPage" />
  <html:hidden name="formSpeciality" property="dep_code" /> 
 
 </html:form>
 
</body>
<script language="javascript">
         alert("专业信息删除成功!");
         // var dep_code = document.getElementById("dep_code").value;
         // var form1 = document.frames("listPage").document.forms[0];
         // form1.dep_code.value=dep_code;
         //form1.submit();
        //  var depcode = document.getElementById("dep_code").value;
           
	    // window.location.href="spec_querynew.do?dep_code="01";
	   // alert(document.getElementById("dep_code").value);
	    var depcode=document.getElementById("dep_code").value;
	    window.location.href="speciality_query.do?listpage=1&dep_code="+depcode;
</script>
  

