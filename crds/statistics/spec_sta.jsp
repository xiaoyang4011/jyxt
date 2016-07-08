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
	<div class="rpos">当前位置:学生就业 - 列表</div>
	
	<form class="ropt">
		<input type="submit" value="导出" onclick="this.form.action='<%=path%>/userindoPage.do';" class="check"/>
	</form>
	
	<div class="clear"></div>
</div>
<div align="center">&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">${message}&nbsp;</font></div>
<div class="body-box">
<html:form styleId="adduserForm" action="/bro_spec.do" method="post" style="padding-top:5px;"> 

<div>
系部：<select  id="dept" onchange="getSpecList();clear1();"><option value="0">-请选择-</option></select>
专业：<select  id="spec" onchange="getSublibList();getCourseList();clear2();"><option value="0">-请选择-</option></select>
班级：<select  id="class_code"  onchange="getFileType();"><option value="0">-请选择-</option></select>
<input type="hidden" id="id1" name="dept" value="${dept}"/>
<input type="hidden" id="id2" name="spec" value="${spec}"/>
<input type="hidden" id="id3" name="class_code" value="${class_code}"/>
<input class="query" type="submit" value="查询"/>
</div>



<table class="pn-ltable" style="" width="100%" cellspacing="1" cellpadding="0" border="0">
<thead class="pn-lthead"><tr>
	<th>机构名称</th>
	<th colspan="2">2009年</th>
	<th colspan="2">2010年</th>	
	<th colspan="2">2011年</th>
	<th colspan="2">2012年</th>
	<th colspan="2">2013年</th>
 	<tr>
    <th>&nbsp;</th>
    <th>人数</th>
    <th>比例</th>
    <th>人数</th>
    <th>比例</th>
    <th>人数</th>
    <th>比例</th>
    <th>人数</th>
    <th>比例</th>
    <th>人数</th>
    <th>比例</th>
 	</tr>
	
	</tr></thead>
	<tbody  class="pn-ltbody">
	<logic:present name="listmessage">
	<logic:iterate name="listmessage" id="map">
 		<tr onmouseover="this.bgColor='#eeeeee'" onmouseout="this.bgColor='#ffffff'">
		<td align="center">${map.dep_name} </td>
		<td align="center"></td> 
		<td align="center"></td> 
		<td align="center"></td> 
		<td align="center"></td> 
		<td align="center"></td> 
		<td align="center"></td> 
		<td align="center"></td> 
		<td align="center"></td> 
		<td align="center">${map.num09}</td> 
		<td align="center">${map.jyl}</td> 

 
        </tr>
	 	</logic:iterate>
	</logic:present>
</tbody>
</table>
<input type="hidden" name="pageNo" value="">
<c:if test="${formzs.rowsCount!=0}">
<table width="100%" border="0" cellpadding="0" cellspacing="0"><tr><td align="center" class="pn-sp">
	共 ${formzs.rowsCount} 条&nbsp;
	每页<input type="text" name=rowsPerPage value="${formzs.rowsPerPage}" style="width:30px" onfocus="this.select();" onblur="jqy.cookie('_cookie_page_size',this.value,{expires:3650});" onkeypress="if(event.keyCode==13){jqy(this).blur();return false;}"/>条&nbsp;
	<input class="first-page" type="button" value="首 页" onclick="_gotoPage('1');" ${formzs.pageNo==1?"disabled='disabled'":""}/>
	<input class="pre-page" type="button" value="上一页" onclick="_gotoPage('${formzs.pageNo-1}');" ${formzs.pageNo==1?"disabled='disabled'":""}/>
	<input class="next-page" type="button" value="下一页" onclick="_gotoPage('${formzs.pageNo+1}');" ${formzs.pageNo==formzs.pageCount?"disabled='disabled'":""}/>
	<input class="last-page" type="button" value="尾 页" onclick="_gotoPage('${formzs.pageCount}');" ${formzs.pageNo==formzs.pageCount?"disabled='disabled'":""}/>&nbsp;
	当前 ${formzs.pageNo}/ ${formzs.pageCount} 页 &nbsp;转到第<input type="text" id="_goPs" style="width:50px" onfocus="this.select();" onkeypress="if(event.keyCode==13){jqy('#_goPage').click();return false;}"/>页
	<input class="go" id="_goPage" type="button" value="转" onclick="_gotoPage(jqy('#_goPs').val());" ${(formzs.pageCount==1)?"disabled='disabled'":""} />
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
	


</html:form>
</div>

</BODY>

</html>