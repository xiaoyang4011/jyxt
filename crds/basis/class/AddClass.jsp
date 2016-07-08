<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>

<%String path = request.getContextPath();%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<html>
		<head>
			<title>班级管理</title>
			<link href="../../pub1/css/common.css" rel="stylesheet"
				type="text/css">
			<link href="../../pub1/css/default.css" rel="stylesheet"
				type="text/css">

			<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

			<link href="<%=path%>/res/jeecms/css/admin.css" rel="stylesheet"
				type="text/css" />
			<link href="<%=path%>/res/common/css/theme.css" rel="stylesheet"
				type="text/css" />
			<link href="<%=path%>/res/common/css/jquery.validate.css"
				rel="stylesheet" type="text/css" />
			<link href="<%=path%>/res/common/css/jquery.treeview.css"
				rel="stylesheet" type="text/css" />
			<link href="<%=path%>/res/common/css/jquery.ui.css" rel="stylesheet"
				type="text/css" />
			<script src="<%=path%>/res/common/js/jquery.js"
				type="text/javascript"></script>
			<script src="<%=path%>/res/common/js/jquery.ext.js"
				type="text/javascript"></script>
			<script src="<%=path%>/res/common/js/pony.js" type="text/javascript"></script>
			<script src="<%=path%>/res/jeecms/js/admin.js" type="text/javascript"></script>

			<script type='text/javascript' src='<%=path%>/dwr/engine.js'></script>
			<script type='text/javascript' src='<%=path%>/dwr/util.js'></script>
			<script type='text/javascript'
				src='<%=path%>/dwr/interface/SublibThreeLevel.js'></script>
<script language="javascript">    
                 var jqy = jQuery.noConflict();    
</script> 
			<script type="text/javascript"> 
function getTableForm() {
	return document.getElementById('formClass');
}
function optDelete() {
	if(Pn.checkedCount('ids')<=0) {
		alert("请选择您要操作的数据");
		return;
	}
	if(!confirm("您确定删除吗？")) {
		return;
	}
	var f = getTableForm();
	f.action="/system/role/deleteRole.do";
	f.submit();
}
function optCheck() {
	if(Pn.checkedCount('ids')<=0) {
		alert("请选择您要操作的数据");
		return;
	}
	var f = getTableForm();
	f.action="o_check.do";
	f.submit();
}



function chgStatus() {
	var queryStatus = $("input[name=queryStatus]:checked").val();
	location.href="v_list.do?cid=43&queryStatus=" + queryStatus;
	
}

jqy(function() {
		dwr.engine.setAsync(false);
		getDeptList();
		getSpecList();
		getSublibList();
		getCourseList();		
	});

	function getDeptList() {
		SublibThreeLevel.findDepartment({
			callback : function(deptlist) {
				dwr.util.removeAllOptions("dept");
				dwr.util.addOptions("dept", [ {
					name : "-请选择-",
					value : "0"
				} ], "value", "name");
				dwr.util.addOptions("dept", deptlist, "dep_code",
						"dep_name");
			}
			
		});
	}

	function getSpecList() {
		SublibThreeLevel.findSpeciality(dwr.util.getValue("dept"), {
			callback : function(speclist) {
				dwr.util.removeAllOptions("spec");
				dwr.util.addOptions("spec", [ {
					name : "-请选择-",
					value : "0"
				} ], "value", "name");
				dwr.util.addOptions("spec_code", speclist, "spec_code", "spec_name");
			}
			
		});
	}
	

	function getCourseList() {
		SublibThreeLevel.findCourse(dwr.util.getValue("spec"), {
			callback : function(courselist) {
				dwr.util.removeAllOptions("allcourse");			
				dwr.util.addOptions("allcourse", courselist, "course_code", "course_name");
			}
			
		});
	}
	//获取复选框的value
function getValueOfCheckbox()
{

    //获取当前页所有IUPUT控件集合
     var col=document.getElementsByName("ids");
    
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

  
    //将获取的checkbox的值传递到页面
   if(str=="")
   {
   alert("请选择要删除的选项 ");
    return;
   }
  
   //return window.confirm('是否真要删除这个学生？');
   	var url = "deleteall.do?strinfo=" +str; 
   	window.location=url;  

}
</script>
		</head>
		<body>
			<div class="box-positon">
				<div class="rpos">
					当前位置: 班级管理 - 添加
				</div>
				<form class="ropt">
					<input type="submit" value="返回列表"
						onclick="this.form.action='<%=path%>/Class.do';"
						class="return-button" />
				</form>
				<div class="clear"></div>
			</div>
			<div align="center">&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">${message}&nbsp;</font></div>
			<div class="body-box">
			
				<form method="post" action="<%=path%>/addClassForm.do" onsubmit="return checkform(this)">
					<table align="center" width="80%" class="pn-ftable" cellpadding="2"
						cellspacing="1" border="0">
						<tr>
							<td width="30%" class="pn-flabel pn-flabel-h">
								<span class="pn-frequired">*</span>班级编号：
							</td>
							<td colspan="1" width="70%" class="pn-fcontent">
								<input type="text" name="class_code" id="class_code" /><span class="pn-fhelp">  请添加班级编码，不允许为空，不允许重复。</span>
							</td>
						</tr>
						<tr>
							<td width="30%" class="pn-flabel pn-flabel-h">
								<span class="pn-frequired">*</span>专业代码：
							</td>
							<div>
								<td colspan="1" width="70%" class="pn-fcontent">
									系别：
									<select name="dept" id="dept" onchange="getSpecList()">
									</select>
									专业：
									<select name="spec_code" id="spec_code"
										property="query_spec_name" onchange="getCourseList()">
										<option value="0">
											-请选择-
										</option>
									</select>
								</td>
							</div>
						</tr>
						<tr>
							<td width="30%" class="pn-flabel pn-flabel-h">
								<span class="pn-frequired">*</span>班级名称：
							</td>
							<td colspan="1" width="70%" class="pn-fcontent">
								<input type="text" name="class_name" id="class_name" /><span class="pn-fhelp">  请添加班级名称，不允许为空，不允许重复。</span>
							</td>
						</tr>
						<tr>
							<td colspan="2" class="pn-fbutton">
								<input type="submit" class="submit" class="submit" value="提交"
									 />
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="reset" name="button2" id="button2" value="重置"
									class="reset" class="reset" />
								
							</td>
						</tr>
					</table>
				</form>
			</div>
			<script>
	function checkform(sf){
		var message = "";
		var i = 1;		
		if(sf.class_code.value==""){
			message += i+") 班级编号不能为空！ ";
			i++;
		}	
		if(sf.class_name.value==""||sf.class_name.value.length>40){
			message += i+") 班级名称不能为空且不超过40个字符\n";
			i++;
		}	
		if(message!=""){
			alert(message);
			return false;
		}else{
			return true;
		}
											
	}
	//校验用户名：只能输入1-20个字母、数字、下划线
	function isRolename(s){
		var patrn=/^(\w){1,20}$/;
		if (!patrn.exec(s)) 
			return false;
		return true;
	}
	//校验用户id：只能输入1-2个字母、数字、下划线
	function isRoleid(s){
		var patrn=/^(\w){1,2}$/;
		if (!patrn.exec(s)) 
			return false;
		return true;
	}	
</script> 
		</body>
	</html>