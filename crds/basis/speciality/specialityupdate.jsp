<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%String path = request.getContextPath();%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>角色管理</title>
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


 <logic:present name="editsuccess" scope="request">
    <logic:match name="editsuccess" value="success" scope="request">
     <script language="javascript">
      alert("修改成功！");
     <!--window.opener.document.forms[0].action="spec_list2.do?listpage=1"
      window.opener.document.forms[0].submit();
      window.close(); 
      -->
     </script>
    </logic:match>
   </logic:present>
<body>
<div class="box-positon">
	<div class="rpos">当前位置: 专业管理 - 修改</div>
	<form class="ropt">
		<input type="submit" value="返回列表" onclick="this.form.action='<%=path%>/system/role/queryRole.do';" class="return-button"/>
	</form>
	<div class="clear"></div>
</div>
<div align="center">&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">${message}&nbsp;</font></div>
<div class="body-box">
 <html:form action="/specialityupdatedo.do" method="post" onsubmit="return checksubjectform(this)">
 <html:hidden property="spec_code"/>
 <html:hidden property="dep_code"/>
<table align="center" width="80%" class="pn-ftable" cellpadding="2" cellspacing="1" border="0">
	<tr>
	 <td colspan="2" bgcolor="#3D7BA3" height="35" align="center">
	修改专业
		</td>
	</tr>	 
	<tr>
		<td width="30%" class="pn-flabel pn-flabel-h"><span class="pn-frequired">*</span>
	专业代码	
		</td>
		<td colspan="1" width="70%" class="pn-fcontent">
	<html:text property="spec_code" readonly="true"/> 	
		</td>
	</tr>
	 <tr>
		<td width="30%" class="pn-flabel pn-flabel-h"><span class="pn-frequired">*</span>
	专业名称	
		</td>
		<td colspan="1" width="70%" class="pn-fcontent"><html:text property="spec_name"  /> 	
		 
		</td>
	</tr>
	<tr>
		<td width="30%" class="pn-flabel pn-flabel-h">
	专业状态
	
		</td>
		<td colspan="1" width="70%" class="pn-fcontent">
	<html:radio property="spec_state"  value="1"/>开放&nbsp;&nbsp;<html:radio property="spec_state" value="0"/>关闭
	
		</td>
	</tr>
 
	<tr>
		<td width="30%" class="pn-flabel pn-flabel-h">
	描&nbsp;&nbsp;&nbsp;&nbsp;述
	
		</td>
		<td colspan="1" width="70%" class="pn-fcontent">
	 <html:textarea property="spec_description"  style="width:400px;height:60px;"/> 	
		</td>
	</tr>
	<tr>
		<td colspan="2" class="pn-fbutton">	
	<html:submit value="提　交" class="submit" />
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;			 
 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;		
	<input type="button" onclick="history.back()" value="返　回" class="reset"/>
    </td>
	</tr>
</table>

 
 <script>  
     if((String)request.getAttribute(spec_state)="打开")
     document.all("spec_state")[0].checked=true;//第一个radio选中  
     else
     document.all("spec_state")[1].checked=true;
</script> 
</html:form>
<center>
<br>&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">${message}&nbsp;</font>
</center>
</body>
<script>

	function checksubjectform(sf){
		var message = "";
		var i = 1;
		if(sf.spec_name.value.length==0||sf.name.value.length20){
			message += i+") 专业名称只能为1-20个字符\n";
			i++;
		}
				
		if(sf.spec_description.value==""||sf.intro.value.length>200){
			message += i+") 描述不能为空且小于200字\n";
			i++;
		}
		
		if(message!=""){
			alert(message);
			return false;
		}else{
			return true;
		}
											
	}
	
	//function returnlist()
	//{　
  	  //window.location.action="spec_list2.do"
  	 //  location.href("spec_list2.do");
     // window.opener.document.forms[0].submit();
     //window.close();
	// history.go(-1);//需要点两次返回按钮　？？？？？
     //window.location.reload();//返回后刷新？？？？？， 需重试
   
	//}
	
	function query(){
	location.href("spec_list2.do?listpage=1");
	//var dep_code = document.getElementById("dep_code").value;
   // var form1 = document.frames("listPage").document.forms[0];
   // form1.dep_code.value=dep_code;
   // form1.submit();
	}
</script> 
  

