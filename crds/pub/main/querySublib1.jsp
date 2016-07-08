<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%String path = request.getContextPath();%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>子库列表</title>
<link href="../../pub1/css/common.css" rel="stylesheet" type="text/css">
<link href="../../pub1/css/default.css" rel="stylesheet" type="text/css">
</head>

<body bgcolor="#FFFFFF" text="#000000">

<TABLE cellSpacing=0 cellPadding=0 width="100%" >
  <TBODY>
  <TR>
    <TD class="head">子库列表</TD>
  </TR>
  </TBODY></TABLE>
<br>
<div align="center">&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">${message}&nbsp;</font></div>
<br>
<br>
<table style="BORDER-RIGHT: #a6a398 1px solid; BORDER-TOP: #a6a398 1px solid; BORDER-LEFT: #a6a398 1px solid; BORDER-BOTTOM: #a6a398 1px solid" cellSpacing="0" cellPadding="3" width="95%" align="center">
  <tbody>
    <tr>
      <td class="thead" style="PADDING-LEFT: 10px" noWrap width="25%">子库ID</td>
      <td class="thead" style="PADDING-LEFT: 10px" noWrap width="25%"><img src="<%=path%>/pub1/images/tl.gif" align="absMiddle" width="10" height="15">专业名称</td>
      <td class="thead" style="PADDING-LEFT: 10px" noWrap width="25%"><img src="<%=path%>/pub1/images/tl.gif" align="absMiddle" width="10" height="15">子库名称</td>
      <td class="thead" noWrap width="25%"><img src="<%=path%>/pub1/images/tl.gif" align="absMiddle" width="10" height="15">操&nbsp;&nbsp;&nbsp;&nbsp;作</td>
      
    </tr>
      <logic:present name="subliblist">
 	<logic:iterate name="subliblist" id="s">
     <tr class="row" style="BACKGROUND-COLOR: #ffffff">
         
      <td style="PADDING-LEFT: 10px">&nbsp; ${s.sublib_id}&nbsp;</td>     
      <td style="PADDING-LEFT: 10px">&nbsp;&nbsp;${s.spec_name}&nbsp;</td>     
      <td style="PADDING-LEFT: 10px">&nbsp;&nbsp;${s.sublib_title}&nbsp;</td>
      
   	<td> [ <html:link page="/sublibquery.do?sublib_id=${s.sublib_id}">资源管理</html:link> ] </td>
    </tr>
   	</logic:iterate>
	</logic:present> 
  </tbody>
</table>
<HR noShade SIZE=1>
    

</BODY>
<script>
	function ggo(t){
		document.queryStudentForm.pageno.value=t;
		document.queryStudentForm.submit();
	}
</script>
</html>