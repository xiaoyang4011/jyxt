<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<%String path = request.getContextPath();%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<head>
<title>用户管理</title>
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

<script type='text/javascript' src='<%=path%>/dwr/engine.js'></script>
	<script type='text/javascript' src='<%=path%>/dwr/util.js'></script>
	<script type='text/javascript' src='<%=path%>/dwr/interface/DepartThreeManage.js'></script>
<script language="javascript">    
                 var jqy = jQuery.noConflict();    
</script>  
<script type="text/javascript"> 
function getTableForm() {
	return document.getElementById('adduserForm');
}
//获取复选框的value
function getValueOfCheckbox()
{

    //获取当前页所有IUPUT控件集合
     var col=document.getElementsByName("chkAll");
    
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
  var s=confirm('是否真要删除这个学生？');
    if(s){
   	   var url = "userdeleteall.do?strinfo=" +str; 
   	    window.location=url;  
     }
}
function chgStatus() {
	var queryStatus = $("input[name=queryStatus]:checked").val();
	location.href="v_list.do?cid=43&queryStatus=" + queryStatus;
}
//////////////////////
//批量审核用户
function optCheck(){
    //获取当前页所有IUPUT控件集合
     var col=document.getElementsByName("chkAll");

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
        }
    }
    //将获取的checkbox的值传递到页面
   if(str=="")
   {
   alert("请选择要审核的选项 ");
    return;
   }
   	var url = "plshenhe.do?strinfo=" +str; 
   	window.location=url;  
}


//批量审核用户
function stop(){
    //获取当前页所有IUPUT控件集合
   
     var col=document.getElementsByName("chkAll");

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
        }
    }
    //将获取的checkbox的值传递到页面
   if(str=="")
   {
   alert("请选择要审核的选项 ");
    return;
   }
   	var url = "stopuser.do?strinfo=" +str; 
   	window.location=url;  
}



/////////////////////////
jqy(function() {
	});

	function getDeptList() {
		DepartThreeManage.findDepartment({
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
	DWRUtil.setValue("id1",dwr.util.getValue("dept"));
		DepartThreeManage.findSpeciality(dwr.util.getValue("dept"), {
			callback : function(speclist) {
				dwr.util.removeAllOptions("spec");
				dwr.util.addOptions("spec", [ {
					name : "-请选择-",
					value : "0"
				} ], "value", "name");
				dwr.util.addOptions("spec", speclist, "spec_code", "spec_name");
			}
			
		});
	}
	
	function getSublibList() {
	DWRUtil.setValue("id2",dwr.util.getValue("spec"));
		DepartThreeManage.findClass(dwr.util.getValue("spec"), {
			callback : function(subliblist) {
				dwr.util.removeAllOptions("class_code");
				dwr.util.addOptions("class_code", [ {
					name : "-请选择-",
					value : "0"
				} ], "value", "name");
				dwr.util.addOptions("class_code", subliblist, "class_code", "class_name");
			}
			
		});
	}
   function getFileType(){
     DWRUtil.setValue("id3",dwr.util.getValue("class_code"));
  
   }
   function clear1(){
   DWRUtil.setValue("id2","0");
     DWRUtil.setValue("id3","0");
  
   }
    function clear2(){
 
     DWRUtil.setValue("id3","0");
  
   }
////////////////////////

</script>
<script type="text/javascript">
  function pldaoru(){
  	var url = "system/user/importBatchUser.jsp"; 
   	window.location=url; 
  }
</script>
</head>
<body onload="getDeptList()">
<div class="box-positon">
	<div class="rpos">当前位置:用户管理 - 列表</div>
	
	<form class="ropt" action="<%=path%>/addUserPackage.do"  method="post">
		<input class="add" type="submit" value="添加" />		
	</form>
	<form class="ropt">
		<input type="submit" value="刷新列表" onclick="this.form.action='<%=path%>/userindoPage.do';" class="return-button"/>
	</form>
	
	<div class="clear"></div>
</div>
<div align="center">&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">${message}&nbsp;</font></div>
<div class="body-box">
<html:form styleId="adduserForm" action="/userindoPage.do" method="post" style="padding-top:5px;"> 

<div>
系部：<select  id="dept" onchange="getSpecList();clear1();"><option value="0">-请选择-</option></select>
专业：<select  id="spec" onchange="getSublibList();getCourseList();clear2();"><option value="0">-请选择-</option></select>
班级：<select  id="class_code"  onchange="getFileType();"><option value="0">-请选择-</option></select>
状态：<select name="user_state" id="user_state"><option value="${user_state}">-请选择-</option><option value="2">待审核</option><option value="0">已审核</option><option value="1">停用</option></select>
角色：<select id="role_id" name="role_id" style="width: 80">
                               <option value="${role_id}">-请选择-</option>
								<c:forEach items="${rolelist}" var="map1" varStatus="cnt">
									<option value="${map1.role_id}">
										${map1.role_name}
									</option>								 
								</c:forEach>
					</select>

用户ID：
<input type="text" id="user_ID" name="user_ID"/>
<input type="hidden" id="id1" name="dept" value="${dept}"/>
<input type="hidden" id="id2" name="spec" value="${spec}"/>
<input type="hidden" id="id3" name="class_code" value="${class_code}"/>

<input class="query" type="submit" value="查询"/>

</div>



<table class="pn-ltable" style="" width="100%" cellspacing="1" cellpadding="0" border="0">
<thead class="pn-lthead"><tr>
	<th width="20"><input type='checkbox' onclick='Pn.checkbox("chkAll",this.checked)'/></th>
	<th>用户ID</th>
	<th>机构名称	</th>	
	<th>专业名称	</th>
	<th>班级名称	</th>
	<th>状态</th>
	<th>真实名</th>
	<th>用户类型	</th>
	<th>角色</th>

	<th>修改|删除|审核</th>
	</tr></thead>
	<tbody  class="pn-ltbody">
	<logic:present name="student">
	<logic:iterate name="student" id="map">
 		<tr onmouseover="this.bgColor='#eeeeee'" onmouseout="this.bgColor='#ffffff'">
		<td><input type='checkbox' name="chkAll" id="chkAll" value="${map.user_ID}"/></td>
		<td align="center">${map.user_ID}</td>
		<td align="center">${map.dep_name}</td> 
		<td align="center">${map.spec_name}</td> 
		<td align="center">${map.class_name}</td> 
		<td align="center">${map.user_states}</td> 
		<td align="center">${map.real_name}</td> 
		<td align="center">${map.user_type}</td> 
		<td align="center">${map.role_name}</td> 
 
	   	<td align="center"> <html:link page="/updateuser.do?password=${map.password}&user_ID=${map.user_ID}&user_name=${map.user_name}&real_name=${map.real_name}&membertype=${map.member_type}&user_point=${map.user_point}">修改</html:link>
	   	 | <html:link page="/userdelete.do?userid=${map.user_ID}" onclick="return window.confirm('确定执行删除操作？')">删除</html:link> 
	   	|<html:link page="/shenhe.do?userid=${map.user_ID}">审核</html:link> </td>
	    </tr>
	 	</logic:iterate>
	</logic:present>
</tbody>
</table>
<input type="hidden" name="pageNo" value="">
<c:if test="${formStud.rowsCount!=0}">
<table width="100%" border="0" cellpadding="0" cellspacing="0"><tr><td align="center" class="pn-sp">
	共 ${formStud.rowsCount} 条&nbsp;
	每页<input type="text" name=rowsPerPage value="${formStud.rowsPerPage}" style="width:30px" onfocus="this.select();" onblur="jqy.cookie('_cookie_page_size',this.value,{expires:3650});" onkeypress="if(event.keyCode==13){jqy(this).blur();return false;}"/>条&nbsp;
	<input class="first-page" type="button" value="首 页" onclick="_gotoPage('1');" ${formStud.pageNo==1?"disabled='disabled'":""}/>
	<input class="pre-page" type="button" value="上一页" onclick="_gotoPage('${formStud.pageNo-1}');" ${formStud.pageNo==1?"disabled='disabled'":""}/>
	<input class="next-page" type="button" value="下一页" onclick="_gotoPage('${formStud.pageNo+1}');" ${formStud.pageNo==formStud.pageCount?"disabled='disabled'":""}/>
	<input class="last-page" type="button" value="尾 页" onclick="_gotoPage('${formStud.pageCount}');" ${formStud.pageNo==formStud.pageCount?"disabled='disabled'":""}/>&nbsp;
	当前 ${formStud.pageNo}/ ${formStud.pageCount} 页 &nbsp;转到第<input type="text" id="_goPs" style="width:50px" onfocus="this.select();" onkeypress="if(event.keyCode==13){jqy('#_goPage').click();return false;}"/>页
	<input class="go" id="_goPage" type="button" value="转" onclick="_gotoPage(jqy('#_goPs').val());" ${(formStud.pageCount==1)?"disabled='disabled'":""} />
</td></tr></table>
</c:if>
<script type="text/javascript"> 
function _gotoPage(pageNo) {
	try{
		var tableForm = getTableForm();
		
		jqy("input[name=pageNo]").val(pageNo);		
		tableForm.onsubmit=null;
		tableForm.submit();
	} catch(e) {
	   alert(e);
	}
}
</script>
	
<div style="margin-top:15px;">
	<input type="button" value="删除" onclick="getValueOfCheckbox()" class="del-button"/>
	<input type="button" value="审核" onclick="optCheck();" class="check"/>
	<input type="button" value="停用" onclick="stop();" class="check"/>
	<input type="button" value="批量管理" onclick="pldaoru();" class="return-button"/>
	
</div>

</html:form>
</div>

</BODY>

</html>