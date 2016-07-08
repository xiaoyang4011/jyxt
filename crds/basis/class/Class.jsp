<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>

<%
	String path = request.getContextPath();
%>
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
				dwr.util.addOptions("spec", speclist, "spec_code", "spec_name");
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
					当前位置: 班级管理 - 列表
					
				</div>

				<form class="ropt" action="<%=path%>/addClass.do" method="post">
					<input class="add" type="submit" value="添加" />
				</form>
				<div class="clear"></div>
			</div>
			<div align="center">&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">${message}&nbsp;</font></div>
			<!-- 《《》MM<>MMLML -->
			<html:form styleId="formClass" action="/Class.do" method="post"
				style="padding-top:5px;">

				<div>
					<td class=altbg2>
						系别：
						<select name="dept" id="dept" onchange="getSpecList()">
						</select>
						专业：
						<select name="spec" id="spec" property="query_spec_name"
							onchange="getCourseList()">
							<option value="0">
								-请选择-
							</option>
						</select>
					</td>


					<input class="query" type="submit" value="查询" />
				</div>

				<div class="body-box">



					<table class="pn-ltable" style="" width="100%" cellspacing="1"
						cellpadding="0" border="0">
						<thead class="pn-lthead">
							<tr>

								<th>
									班级编号
								</th>
								<th>
									专业名称
								</th>
								<th>
									班级名称
								</th>
								<th>
									操&nbsp;&nbsp;&nbsp;&nbsp;作
								</th>
							</tr>
						</thead>
						<tbody class="pn-ltbody">
							<c:forEach items="${student}" var="map" varStatus="cnt">
								<tr onmouseover="this.bgColor='#eeeeee'"
									onmouseout="this.bgColor='#ffffff'">

									<td align="center">
										${map.class_code}
									</td>
									<td align="center">
										${map.spec_name}
									</td>
									<td align="center">
										${map.class_name}
									</td>

									<td align="center">
										<html:link
											page="/UpdateClass.do?class_code=${map.class_code}&spec_code=${map.spec_code}&class_name=${map.class_name}">修改</html:link>
										|
										<html:link page="/delete.do?class_code=${map.class_code}"
											onclick="return window.confirm('是否真要删除这条班级信息？')">删除</html:link>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<!--					-->
					<input type="hidden" name="pageNo" value="">
					<c:if test="${formClass.rowsCount!=0}">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td align="center" class="pn-sp">
									共 ${formClass.rowsCount} 条&nbsp; 每页
									<input type="text" name=rowsPerPage
										value="${formClass.rowsPerPage}" style="width: 30px"
										onfocus="this.select();"
										onblur="jqy.cookie('_cookie_page_size',this.value,{expires:3650});"
										onkeypress="if(event.keyCode==13){jqy(this).blur();return false;}" />
									条&nbsp;
									<input class="first-page" type="button" value="首 页"
										onclick="_gotoPage('1');" ${formClass.pageNo==1?
										"disabled='disabled' ":""}/>
									<input class="pre-page" type="button" value="上一页"
										onclick="_gotoPage(' ${formClass.pageNo-1}');"
										${formClass.pageNo==1? "disabled='disabled' ":""}/>
									<input class="next-page" type="button" value="下一页"
										onclick="_gotoPage('${formClass.pageNo+1}');"
										${formClass.pageNo==formClass.pageCount? "disabled='disabled' ":""}/>
									<input class="last-page" type="button" value="尾 页"
										onclick="_gotoPage('${formClass.pageCount}');"
										${formClass.pageNo==formClass.pageCount? "disabled='disabled' ":""}/>
									&nbsp; 当前 ${formClass.pageNo}/ ${formClass.pageCount} 页
									&nbsp;转到第
									<input type="text" id="_goPs" style="width: 50px"
										onfocus="this.select();"
										onkeypress="if(event.keyCode==13){jqy('#_goPage').click();return false;}" />
									页
									<input class="go" id="_goPage" type="button" value="转"
										onclick=_gotoPage(jqy(
										'#_goPs').val());;
${(formClass.pageCount==1)?
										"disabled='disabled' ":""} />
								</td>
							</tr>
						</table>

					</c:if>
				</div>
				<script type="text/javascript">
	function _gotoPage(pageNo) {
		try {
			var tableForm = getTableForm();
			jqy("input[name=pageNo]").val(pageNo);
			tableForm.onsubmit = null;
			tableForm.submit();
		} catch (e) {
			alert('_gotoPage(pageNo)方法出错');
		}
	}
</script>
				<!--  
				<div style="margin-top: 15px;">
					<input type="button" value="删除" onclick="getValueOfCheckbox();"
						class="del-button" />-->
				</div>
			</html:form>


		</body>
	</html>