<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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

</head>

<body>
<form id="mediaForm" action="<%=path %>/fileupload.do" method="post" enctype="multipart/form-data" target="media_iframe" style="display:none;width:0px;height:0px;">
<span id="mediaContent"></span>
<input type="hidden" id="mediaFilename" name="filename"/>
<input   type= "hidden"   name= "mediafiletype" id= "mediafiletype" value="xls" />
<input   type= "hidden"   name= "sublib_id" id= "hide_sublib_id" value="9999"/>
<input   type= "hidden"   name= "spec_code" id= "hide_spec_code" value="9999" />
<input   type= "hidden"   name= "isuploadimage"  value="no"/>
</form>
<iframe name="media_iframe" frameborder="0" border="0" style="display:none;width:0px;height:0px;"></iframe>
<div class="box-positon">
	<div class="rpos">当前位置: 用户管理 - 批量增加积分</div>
	<form class="ropt">
		<input type="submit" value="返回列表" onclick="this.form.action='<%=path%>/userindoPage.do';" class="return-button"/>
	</form>
	<div class="clear"></div>
</div>
<div align="center">
    
</div>
<div class="body-box">
<html:form method="post" action="/pladduserpointT" onsubmit="return TTT()">
<table align="center" width="80%" class="pn-ftable" cellpadding="2" cellspacing="1" border="0">

<tr>
<td width="30%" class="pn-flabel pn-flabel-h"><span class="pn-frequired">*</span>用户角色：</td>
<td colspan="1" width="70%" class="pn-fcontent">
	<select id="role_id" name="role_id" style="width: 80">
                               <option value="0">-全部-</option>
								<c:forEach items="${rolelist}" var="map1" varStatus="cnt">
									<option value="${map1.role_id}">
										${map1.role_name}
									</option>								 
								</c:forEach>
					</select>(当不选择此项时，是为所有的用户增加积分)
</td>
</tr>
<tr>
<td width="30%" class="pn-flabel pn-flabel-h"><span class="pn-frequired">*</span>请输入要增加的积分</td>
<td colspan="1" width="70%" class="pn-fcontent">
	<input type="text" id="userpoint" name="userpoint" style="width:265px"/>
</td>
</tr>
<tr><td colspan="2" class="pn-fbutton">	
<input type="submit" value="提交" class="submit" /> &nbsp; <input type="reset" value="重置"  class="reset"/></td>
</tr>

</table>
</html:form>
</div>
</body>
<script type="text/javascript">
  function TTT(){
  
    var pathuser=document.getElementById("userpoint").value;
    if(pathuser==""){
        alert("请输入要增加的积分数");
       return false;
    }
    return true;;
  }   
</script>
</html>


