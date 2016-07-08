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
<script type='text/javascript' src='<%=path%>/dwr/engine.js'></script>
	<script type='text/javascript' src='<%=path%>/dwr/util.js'></script>
	<script type='text/javascript' src='<%=path%>/dwr/interface/DepartThreeManage.js'></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/system/user/jquery/jquery.1.4.2.js"></script>
<script type="text/javascript">

//ajax西部专业班级查询
$(function() {
		dwr.engine.setAsync(false);
		getDeptList();
		getSpecList();
		getClassList();
	juese();
	});

	function getDeptList() {
		DepartThreeManage.findDepartment({
			callback : function(deptlist) {
				dwr.util.removeAllOptions("dept");
				dwr.util.addOptions("dept", [ {
					name : "-系部-",
					value : "0"
				} ], "value", "name");
				dwr.util.addOptions("dept", deptlist, "dep_code",
						"dep_name");
			}
			
		});
	}

	function getSpecList() {
		DepartThreeManage.findSpeciality(dwr.util.getValue("dept"), {
			callback : function(speclist) {
				dwr.util.removeAllOptions("spec");
				dwr.util.addOptions("spec", [ {
					name : "-专业-",
					value : "0"
				} ], "value", "name");
				dwr.util.addOptions("spec", speclist, "spec_code", "spec_name");
			}
			
		});
	}
	
	function getClassList() {
		DepartThreeManage.findClass(dwr.util.getValue("spec"), {
			callback : function(subliblist) {
				dwr.util.removeAllOptions("class_code");
				dwr.util.addOptions("class_code", [ {
					name : "-班级-",
					value : "0"
				} ], "value", "name");
				dwr.util.addOptions("class_code", subliblist, "class_code", "class_name");
			}
			
		});
	}
	
//查询用户角色的信息
function juese(){
   //定义我需要的数组   
   var myrole = new Array()
   
	$.ajax({type:"GET",url: "<%=request.getContextPath()%>/juese.do",cache:false,
	    success: function(msg){
	    
	     var array = eval("("+msg+")");
	    // var content = "<option value=''>请选择</option>";
	     var content = "";
	     var content = "<option value=''>-角色-</option>";
	     $.each(array,function(k,v){
	       // alert(k);
	        //alert(v['name']+"值"+v['code']);
	        
	    	// content += "<option value='"+v['code']+"'>"+v['name']+"</option>";
		 });
		// $("#role_id").html(content);
	   }
	});
}

</script>
<script type="text/javascript">
//验证用户名是否为空
function yanzusername(){
    if (document.getElementById("user_name").value.length == 0) {
			alert("用户名不能为空，请填写");
			//document.form1.user_ID.focus();
			return;
		}
}
//验证用户积分是否为数字
function userJIFen(){
   if(isNaN(document.getElementById("user_point").value)){
		alert("用户积分只能为数字,请重新填写！");
		return false;
		}
}

//验证密码是否一致
function yanzPs(){
if(document.getElementById("password").value!=document.getElementById("password2").value){
	  alert("密码不一样，请重新输入");
      return;
	  
	}
}
//验证ID是否为空
function temp(){
  if (document.getElementById("user_ID").value.length == 0) {
			alert("用户ID不能为空，请填写");
			//document.form1.user_ID.focus();
			return;
		}
 var temp=$('#user_ID').val();
   $.get("ajax.do?user_ID="+temp,null,huidiao)
   function huidiao(data){
    if(data!=""){
      alert(data);
    }
   //  $("#userid").html(data);
   }
}
//////////测试提交时的验证
function yanzheng(){
    
     if (document.getElementById("roleList").value.length <1) {
			alert("用户角色不能为空，请选择");
			return false;
		}
    if (document.getElementById("user_ID").value.length == 0) {
			alert("用户ID不能为空，请选择");
			return false;
		}
	if (document.getElementById("user_name").value.length == 0) {
			alert("用户名不能为空，请选择");
			return false;
		}
			if (document.getElementById("password").value.length == 0) {
			alert("密码不能为空，请选择");
			return false;
		}
			if (document.getElementById("real_name").value.length == 0) {
			alert("用户真实名不能为空，请选择");
			return false;
		}
			if (document.getElementById("user_point").value.length == 0) {
			alert("用户积分不能为空，请选择");
			return false;
		}
		
    return true;
}

</script>
<script type="text/javascript">
function TTT(){

    //获取当前页所有IUPUT控件集合
     var col=document.getElementsByName("roleList");
    
    //var col = document.getElementsByTagName("INPUT");
    var str="";
    var count=0;
    //循环遍历，判断INPUT是否选中
    for(var i=0 ;i<col.length;i++)
    {
        if(col[i].checked==true)
        {
            count++;
            if(count==1)
            {
                str+=col[i].value;
            }
            else
            {
                str+=","+col[i].value;
            }
            //str+=col[i].value+",";
        }
    }
    if(str==""){
  alert("请选择角色");
    }
}
</script>
</head>

<body onload="getDeptList()">
<div class="box-positon">
	<div class="rpos">当前位置: 用戶管理 - 添加</div>
	<form class="ropt">
		<input type="submit" value="返回列表" onclick="this.form.action='<%=path%>/userindoPage.do';" class="return-button"/>
	</form>
	<div class="clear"></div>
</div>
<div align="center">&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">${message}&nbsp;</font></div>
<div class="body-box">
<form action="<%=path%>/addUser1.do" method="post" onSubmit="return yanzheng()">
<table align="center" width="80%" class="pn-ftable" cellpadding="2" cellspacing="1" border="0">
<tr>
   <td width="30%" class="pn-flabel pn-flabel-h"><span class="pn-frequired">*</span>系部-专业-班级:</td>
    <td colspan="1" width="70%" class="pn-fcontent">
	<select name="dept" id="dept" onchange="getSpecList()">
	<option value="0">-请选择-</option>	
	</select>
     <select name="spec" id="spec" onchange="getClassList()"><option value="0">-请选择-</option></select>
      <select name="class_code" id="class_code" ><option value="0">-请选择-</option></select>
	<span class="pn-fhelp">系部为必选项</span>
    </td>
</tr>

<tr>
   <td width="30%" class="pn-flabel pn-flabel-h"><span class="pn-frequired">*</span>用户ID：</td>
    <td colspan="1" width="70%" class="pn-fcontent">
	<input type="text" maxlength="100" name="user_ID" id="user_ID"  onblur="temp()" maxlength="100"/> <span class="pn-fhelp">由数字或字母组成组成（不能为空）</span>
    </td>
</tr>
<tr>
   <td width="30%" class="pn-flabel pn-flabel-h"><span class="pn-frequired">*</span>密码：</td>
    <td colspan="1" width="70%" class="pn-fcontent">
	<input type="password" maxlength="100" name="password" id="password" maxlength="100"/> <span class="pn-fhelp">请输入字母或字母数字组合</span>
    </td>
</tr>
<tr>
   <td width="30%" class="pn-flabel pn-flabel-h"><span class="pn-frequired">*</span>确认密码:</td>
    <td colspan="1" width="70%" class="pn-fcontent">
	<input type="password" maxlength="100" name="password2" id="password2" onblur="yanzPs()" maxlength="100"/> <span class="pn-fhelp">请再次输入密码</span>
    </td>
</tr>

<tr>
   <td width="30%" class="pn-flabel pn-flabel-h"><span class="pn-frequired">*</span>真实姓名：</td>
    <td colspan="1" width="70%" class="pn-fcontent">
	<input type="text" maxlength="100" name="real_name" id="real_name" maxlength="100"/> <span class="pn-fhelp">字符或汉字组成</span>
    </td>
</tr>

<tr>
   <td width="30%" class="pn-flabel pn-flabel-h"><span class="pn-frequired">*</span>用户类型：</td>
    <td colspan="1" width="70%" class="pn-fcontent">
	<input type="radio" name="user_type" id="user_type" value="教师">教师
          <input type="radio" name="user_type" id="user_type" value="学生" checked> 学生
           <input type="radio" name="user_type" id="user_type" value="企业负责人" checked> 企业负责人
	 <span class="pn-fhelp">&nbsp&nbsp&nbsp&nbsp&nbsp请点击 所选的类型</span>
    </td>
</tr>

<tr> <td width="30%" class="pn-flabel pn-flabel-h"><span class="pn-frequired">*</span>角色：</td>
  <td colspan="1" width="70%" class="pn-fcontent">
   <logic:present name="student">
	<logic:iterate name="student" id="map">
      <input type="checkbox" name="roleList" id="roleList" value="${map.role_id}"/> ${map.role_name} 
        </logic:iterate>
	</logic:present>
	<span class="pn-fhelp">&nbsp&nbsp&nbsp请选择角色信息可多选</span>
    </td>
   
</tr>

     <tr><td colspan="2" class="pn-fbutton">	
     <input type="submit" value="提交" class="submit" class="submit" onmousemove="TTT()"/> &nbsp; <input type="reset" value="重置" class="reset" class="reset"/></td>
     </tr></table>
</form>
</div>
<script>

</script> 
</body>
</html>

