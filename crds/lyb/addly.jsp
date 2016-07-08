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
	<div class="rpos">当前位置:留言添加</div>
	<form class="ropt">
		<input type="submit" value="返回列表" onclick="this.form.action='browse.do';" class="return-button"/>
	</form>
	<div class="clear"></div>
</div>
<html:form action="/add.do" method="post" onsubmit="return checkform()">
<table class="pn-ltable" style="" width="80%" cellspacing="1" cellpadding="0" border="0">
<thead class="pn-lthead">
	<tr>
	    <td class="pn-flabel pn-flabel-h" width="40%"><div align="right"><span class="pn-frequired">*</span>新闻标题：</div></td>
	    <td bgcolor="#FFFFFF">&nbsp;<input name="new_title" type="text"/></td>
	</tr>
	<tr>
	    <td class="pn-flabel pn-flabel-h" width="40%"><div align="right"><span class="pn-frequired">*</span>新闻作者：</div></td>
	    <td bgcolor="#FFFFFF">&nbsp;<input name="new_author" type="text"/>
	</td>
	</tr>
		<tr>
	    <td class="pn-flabel pn-flabel-h" width="40%"><div align="right"><span class="pn-frequired">*</span>新闻类型：</div></td>
	    <td bgcolor="#FFFFFF">&nbsp;	<select name="new_type" id="new_type">
										<option value="0">-请选择-</option>
										<option value="1">-最新动态-</option>
										<option value="2">-校内招聘会-</option>
										<option value="3">-校外招聘会-</option>
										<option value="4">-就业政策-</option>
										<option value="5">-就业指导-</option>
									</select>
	</td>
	</tr>
	</thead>
	<tbody  class="pn-ltbody">
    <tr>
	       <td class="pn-flabel pn-flabel-h" colspan="2" class="pn-fbutton"><div align="center"><span class="pn-frequired">*</span>新闻内容：</div></td>
	</tr>
	<tr> 
	        <td colspan="2" class="pn-fbutton">
	        <textarea name="news" id="description" style="width: 100%;height: 300px;"></textarea>
			 <script type="text/javascript">
				var oFCKeditor = new FCKeditor('news');
				oFCKeditor.BasePath	= "<%=path%>/pub/fckeditor/" ;
				oFCKeditor.ToolbarSet = "ItcastBBS" ;
				oFCKeditor.ReplaceTextarea() ;
			 </script>
	         </td>
	</tr>
    <tr> 
         <td colspan="2" class="pn-fbutton">
              <input type="submit" name="submit" class="submit" value="添加"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              <input type="button" class="submit" value="取消" onclick="history.back(-1)"/>
          </td>
       </tr>  
   </table>
</html:form>
</body>

</html>