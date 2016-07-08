<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%String path = request.getContextPath();%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>班级管理</title>
<link href="../../pub1/css/common.css" rel="stylesheet" type="text/css">
<link href="../../pub1/css/default.css" rel="stylesheet" type="text/css">

<link href="/cms/res/jeecms/css/admin.css" rel="stylesheet" type="text/css"/>
<link href="/cms/res/common/css/theme.css" rel="stylesheet" type="text/css"/>
<link href="/cms/res/common/css/jquery.validate.css" rel="stylesheet" type="text/css"/>
<link href="/cms/res/common/css/jquery.treeview.css" rel="stylesheet" type="text/css"/>
<link href="/cms/res/common/css/jquery.ui.css" rel="stylesheet" type="text/css"/>
 
<script src="/cms/thirdparty/fckeditor/fckeditor.js" type="text/javascript"></script>
<script src="/cms/thirdparty/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="/cms/res/common/js/jquery.js" type="text/javascript"></script>
<script src="/cms/res/common/js/jquery.ext.js" type="text/javascript"></script>
<script src="/cms/res/common/js/pony.js" type="text/javascript"></script>
<script src="/cms/res/jeecms/js/admin.js" type="text/javascript"></script>
</head>
<body>
<div class="box-positon">
	<div class="rpos">当前位置: 班级管理 - 添加</div>
	<form class="ropt">
		<input type="submit" value="返回列表" onclick="this.form.action='<%=path%>/Class.do';" class="return-button"/>
	</form>
	<div class="clear"></div>
</div>
<div class="body-box">
<form method="post" action="<%=path%>/addClassForm.do" >
<table align="center" width="80%" class="pn-ftable" cellpadding="2" cellspacing="1" border="0">
 <tr>
<td width="30%" class="pn-flabel pn-flabel-h"><span class="pn-frequired">*</span>班级编号：</td>
<td colspan="1" width="70%" class="pn-fcontent">
        <input type="text" name="class_code" id="class_code" />    
    </td>
  </tr>
 <tr>
<td width="30%" class="pn-flabel pn-flabel-h"><span class="pn-frequired">*</span>专业代码：</td>




<td colspan="1" width="70%" class="pn-fcontent">
        <input type="text" name="spec_code" id="spec_code" />        
    </td>
  </tr>
  <tr>
<td width="30%" class="pn-flabel pn-flabel-h"><span class="pn-frequired">*</span>班级名称：</td>
<td colspan="1" width="70%" class="pn-fcontent">
        <input type="text" name="class_name" id="class_name" />    
    </td>
  </tr>
<tr><td colspan="2" class="pn-fbutton">	
      <input type="reset" name="button2" id="button2" value="重置" class="reset" class="reset" />
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="submit" class="submit" class="submit"  value="提交" onclick="return window.confirm('添加课程成功！')"/>
       </td>
  </tr>
</table>
</form>
</div>
</body>
</html>