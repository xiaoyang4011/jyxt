<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%String path = request.getContextPath();%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>批量管理用户</title>
<link href="<%=path%>/res/jeecms/css/admin.css" rel="stylesheet" type="text/css"/>
<link href="<%=path%>/res/common/css/theme.css" rel="stylesheet" type="text/css"/>
<link href="<%=path%>/res/common/css/jquery.validate.css" rel="stylesheet" type="text/css"/>
<link href="<%=path%>/res/common/css/jquery.treeview.css" rel="stylesheet" type="text/css"/>
<link href="<%=path%>/res/common/css/jquery.ui.css" rel="stylesheet" type="text/css"/>
<script src="<%=path%>/res/common/js/jquery.js" type="text/javascript"></script>
<script src="<%=path%>/res/common/js/jquery.ext.js" type="text/javascript"></script>
<script src="<%=path%>/res/common/js/pony.js" type="text/javascript"></script>
<script src="<%=path%>/res/jeecms/js/admin.js" type="text/javascript"></script>
<script type="text/javascript">

</script>
<script type="text/javascript">
  function daoru(){
 
  	var url = "importBatchUser.jsp"; 
   	window.location=url; 
  }
  function shanchu(){
 
  	var url = "DeleteBatchUser.jsp"; 
   	window.location=url; 
  }
</script>
</head>

<body>

<iframe name="media_iframe" frameborder="0" border="0" style="display:none;width:0px;height:0px;"></iframe>
<div class="box-positon">
	<div class="rpos">当前位置: 用户管理 - 批量导入</div>
	<form class="ropt">
		<input type="submit" value="返回列表" onclick="this.form.action='<%=path%>/userindoPage.do';" class="return-button"/>
	</form>
	<div class="clear"></div>
</div>
<div align="center">
 请选择您要执行的操作：
 <input type="submit" value="导入" class="submit" onclick="daoru()"/>
 <input type="submit" value="删除" class="submit" onclick="shanchu()"/>
</div>
<div class="body-box">
<form action="<%=path%>/pldr_userinfo.do" method="post" enctype="multipart/form-data"> 
<table align="center" width="80%" class="pn-ftable" cellpadding="2" cellspacing="1" border="0">

<tr>
<td width="30%" class="pn-flabel pn-flabel-h"><span class="pn-frequired">*</span>用户批量导入：</td>
<td colspan="1" width="70%" class="pn-fcontent">
	批量导入用户需先上传'Excel'文件，格式为'.xls',上传完成后才能进行导入操作
</td>
</tr>
<tr>  
<td width="30%" class="pn-flabel pn-flabel-h"><span class="pn-frequired">*</span>文件路径</td>
<td colspan="1" width="70%" class="pn-fcontent">
	<span id="mfc" style="position:relative;display:block;width:300px;*width:300px;">
	<input type="file" name="file" />  
	</span>  
</td>
</tr>
<tr><td colspan="2" class="pn-fbutton">	
<input type="submit" value="导入" class="submit" /> &nbsp; <input type="reset" value="放弃"  class="reset"/></td>
</tr>

<tr><td colspan="2">	
 &nbsp;
</td>
</tr>
<tr><td colspan="2">	
 <center><font color="read">批量导入声明</font></center>
</td>
</tr>
<tr><td colspan="2">	
 &nbsp;
</td>
</tr>
<tr><td colspan="2">	
<font color="read">1.导入Excel格式要求</font>
</td>
</tr>
<tr><td colspan="2">	
 &nbsp;
</td>
</tr>

<tr><td colspan="2">	
<font color="read">第一个行为导入字段名称：<br/>【user_ID】【dep_code】【spec_code】【class_code】【password】【real_name】【user_type】【member_type】【real_name】【user_point】【role_id】【control_type】【user_state】</font>
</td>
</tr>
<tr><td colspan="2">	
 &nbsp;
</td>
</tr>
<tr><td colspan="2">	
<font color="read">2.特殊字段要求</font>
</td>
</tr>
<tr><td colspan="2">	
 &nbsp;
</td>
</tr>
<tr><td colspan="2">	
<font color="read">【user_ID】&nbsp;字段为主键不能有重复的字段，【member_type】&nbsp;和【user_point】&nbsp;必须为int类型</font>
</td>
</tr>
</table>
 </form> 
</div>
</body>
<script type="text/javascript">
  function TTT(){
  
    var pathuser=document.getElementById("mediaPath").value;
    if(pathuser==""){
        alert("文件还未上传完,请稍后..........");
       return false;
    }
    return true;;
  }   
</script>
<script language="javascript">
window.onload =function ()
{
    alert("您进入的是批量【导入】用户页面，请仔细阅读导入声明然后执行您的操作");
};
</script>
</html>


