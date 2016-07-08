 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
　
 <%@ include file="/pub/include/common.jsp"%>
    <script language="javascript">
    <c:if test="${not empty message}">alert('${message}');</c:if>
    </script>
 
<body> 
 <html:form  action="/dep_spec_update_do.do" method="post"  onsubmit="return checksubjectform(this)">
 <html:hidden name="formSpeciality" property="dep_code" /> 
<c:forEach items="${specialitylist1}" var="map" varStatus="cnt">	
<table width="500"  border="0" align="center" cellpadding="4" cellspacing="1"  bgcolor="#3D7BA3" >
	<tr>
	 <td colspan="2" bgcolor="#3D7BA3" height="35" align="center">
	修改专业
	 <input type="hidden" id="dep_code" name="dep_code"  value="${map.dep_code}"/>
		
	 </td>
	</tr>
	  
	<tr bgcolor="ffffff">
		<td align="center">
	专业代码	
		</td>
		<td  >
	<html:text property="spec_code" readonly="true"/> 	
		</td>
	</tr>
	 <tr bgcolor="ffffff">
		<td align="center">
	专业名称	
		</td>	
		<td >
		<input type="text" id="spec_name" name="spec_name"  value="${map.spec_name}"/>
		 
		</td>
	</tr>
	<tr bgcolor="ffffff">
		<td align="center">
	专业状态
	<input type="hidden" id="spec_state1" name="spec_state1"  value="${map.spec_state}" />
		</td>
		<td >
		<html:radio property="spec_state" value="打开"/>打开&nbsp;&nbsp;<html:radio property="spec_state" value="关闭"/>关闭	
	<!--   <input type="radio" name="spec_state" id="spec_state" value="1">打开
	 <input type="radio" name="spec_state" id="spec_state" value="0">关闭
	 -->
	</td>
	</tr>
 
	<tr bgcolor="ffffff">
		<td align="center">
	描&nbsp;&nbsp;&nbsp;&nbsp;述
	
		</td>
		<td >
		 
	 <input type="text" id="spec_description" name="spec_description" value="${map.spec_description}" style="width:400px;height:60px;"/>
		 
		</td>
	</tr>
	<tr bgcolor="ffffff" align="center">
		<td colspan="2">
	 
    <html:submit value="修　改"/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;			 
 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;		
	<input type="button" onclick="history.back()" value="返　回"/>
    </td>
	</tr>

</table>
	 </c:forEach>
	 
 <script> 
 //设置专业状态按钮的默认值  
     // var state="${map.spec_state}".value;
     
      var state = document.getElementById("spec_state1").value;
      //alert(state);
      
      if(state=="打开")
        { 
         document.formSpeciality.spec_state[0].checked=true;//第一个radio选中                
        }
      else
         {        
         document.formSpeciality.spec_state[1].checked=true;//第一个radio选中           
         }
</script>  
	 
 </html:form>
</body>
<script>
 
	function checksubjectform(sf){
		var message = "";
		var i = 1;
		　
		var spec_name = document.getElementById("spec_name").value;
		var spec_description = document.getElementById("spec_description").value;
		　
		　
		if(spec_name.length==0||spec_name.length>20){
			message += i+") 专业名称只能为1-20个字符\n";
			i++;
		}
				
		if(spec_description==""||spec_description.length>200){
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

	function ggo(t){
		document.queryStudentForm.pageno.value=t;
		document.queryStudentForm.submit();
	}	 
	
</script>