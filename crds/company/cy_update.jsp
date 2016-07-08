<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%String path = request.getContextPath();%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="../../pub1/css/common.css" rel="stylesheet" type="text/css">
<link href="../../pub1/css/default.css" rel="stylesheet" type="text/css">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link href="<%=path%>/res/jeecms/css/admin.css" rel="stylesheet" type="text/css"/>
<link href="<%=path%>/res/common/css/theme.css" rel="stylesheet" type="text/css"/>
<link href="<%=path%>/res/common/css/jquery.validate.css" rel="stylesheet" type="text/css"/>
<link href="<%=path%>/res/common/css/jquery.treeview.css" rel="stylesheet" type="text/css"/>
<link href="<%=path%>/res/common/css/jquery.ui.css" rel="stylesheet" type="text/css"/>
<script src="<%=path%>/res/common/js/jquery.js" type="text/javascript"></script>
<script src="<%=path%>/res/common/js/jquery.ext.js" type="text/javascript"></script>
<script src="<%=path%>/res/common/js/pony.js" type="text/javascript"></script>
<script src="<%=path%>/res/jeecms/js/admin.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=path%>/pub/fckeditor/fckeditor.js"></script>
</head> 
<body>
<div class="box-positon">
	<div class="rpos">当前位置:留言修改页</div>
    <form class="ropt">
		<input type="submit" value="返回列表" onclick="this.form.action='browse.do';" class="return-button"/>
	</form>
	<div class="clear"></div>
</div>
<c:forEach items="${listmessage}" var="map" varStatus="cnt">
<html:form action="/update.do" method="post" onsubmit="return checkform()">
<input type="hidden" name="message_id" value="${map.new_id}"/>
<table class="pn-ltable" style="" width="80%" cellspacing="1" cellpadding="0" border="0">
<thead class="pn-lthead">
	<tr>
	    <td class="pn-flabel pn-flabel-h" width="40%"><div align="right"><span class="pn-frequired">*</span>企业ID：：</div></td>
	    <td bgcolor="#FFFFFF">&nbsp;<input name="cy_id" type="text" value="${map.cy_id}"/></td>
	</tr>
	<tr>
	    <td class="pn-flabel pn-flabel-h" width="40%"><div align="right"><span class="pn-frequired">*</span>企业名：</div></td>
	    <td bgcolor="#FFFFFF">&nbsp;<input name="cy_name" type="text" value="${map.cy_name}"/></td>
	</tr>
		<tr>
	    <td class="pn-flabel pn-flabel-h" width="40%"><div align="right"><span class="pn-frequired">*</span>企业负责人：</div></td>
	    <td bgcolor="#FFFFFF">&nbsp;<input name="cy_lead" type="text" value="${map.cy_lead}"/></td>
	</tr>
		<tr>
	    <td class="pn-flabel pn-flabel-h" width="40%"><div align="right"><span class="pn-frequired">*</span>招聘计划：</div></td>
	    <td bgcolor="#FFFFFF">&nbsp;<input name="cy_zs" type="text" value="${map.cy_zs}"/></td>
	</tr>

	
	</thead>
	<tbody  class="pn-ltbody">
    <tr>
	       <td class="pn-flabel pn-flabel-h" colspan="2" class="pn-fbutton"><div align="center"><span class="pn-frequired">*</span>企业简介：</div></td>
	</tr>
	<tr> 
	        <td colspan="2" class="pn-fbutton">
	        <textarea name="news" id="description" style="width: 100%;height: 300px;">${map.cy_summary}</textarea>
			 <script type="text/javascript">
				var oFCKeditor = new FCKeditor('cy_summary');
				oFCKeditor.BasePath	= "<%=path%>/pub/fckeditor/" ;
				oFCKeditor.ToolbarSet = "ItcastBBS" ;
				oFCKeditor.ReplaceTextarea() ;
			 </script>
	         </td>
	</tr>
    <tr> 
         <td colspan="2" class="pn-fbutton">
              <input type="submit" name="submit" class="submit" value="保存"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              <input type="button" class="submit" value="取消" onclick="history.back(-1)"/>
          </td>
       </tr>  
   </table>
</html:form>
 </c:forEach>
</body>

</html>