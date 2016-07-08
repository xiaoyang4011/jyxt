<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/pub/include/common.jsp"%>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>修改子库</title>
	</head>
	<body>
		<center>
			<font color="red">${message}&nbsp;</font>
		</center>
		<html:form method="post" action="/altersub.do"
			onsubmit="return checksubjectform(this)">
			<center>
				<font color="red">修改子库</font>
			</center>
			<table width="337" height="197" border="0" align="center" cellpadding="4"
				cellspacing="1" bgcolor="#3D7BA3">
				<logic:present name="student">
					<logic:iterate name="student" id="map">
						<tr>
							<td height="28" bgcolor="#A9DAD3">
								子库ID
							</td>
							<td height="28" bgcolor="ffffff">
								<label>
									<input type="text" name="sublib_id" id="sublib_id"
										readonly="readonly" value="${map.sublib_id}" />
								</label>
							</td>
						</tr>
						<tr>
							<td height="28" bgcolor="#A9DAD3">
								资源类型
							</td>
							<td bgcolor="ffffff">
								<html:select property="restype_id" value="0" style="width:125">
									<html:option value="0">请选择资源类型</html:option>
									<html:options property="restype_id" collection="restypelist"
										labelProperty="restype_name"></html:options>

								</html:select>
							</td>
						</tr>
						<tr>
							<td height="28" bgcolor="#A9DAD3">
								专业编码
							</td>
							<td height="28" bgcolor="ffffff">
								<html:select property="spec_code" value="0" style="width:96">
									<html:option value="0">请选择专业</html:option>
									<html:options property="spec_code" collection="speclist"
										labelProperty="spec_name"></html:options>

								</html:select>
							</td>
						</tr>
						<tr>
							<td height="28" bgcolor="#A9DAD3">
								子库编码
							</td>
							<td height="28" bgcolor="ffffff">
								<label>
									<input type="text" name="sublib_code" id="sublib_code"
										value="${map.sublib_code}" />
								</label>
							</td>
						</tr>
						<tr>
							<td height="28" bgcolor="#A9DAD3">
								子库标题
							</td>
							<td height="28" bgcolor="ffffff">
								<label>
									<input type="text" name="sublib_title" id="sublib_title"
										value="${map.sublib_title}" />
								</label>
							</td>
						</tr>
					</logic:iterate>
				</logic:present>
				<tr>
					<td height="28" bgcolor="#A9DAD3">
						子库状态
					</td>
					<td bgcolor="ffffff">
						<html:radio property="sublib_state" value="1" />
						开放&nbsp;&nbsp;
						<html:radio property="sublib_state" value="0" />
						关闭
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center" bgcolor="#A9DAD3">

						<input type="submit" name="button" id="button" value="提交" />

						<input type="button" name="Submit2" value="放弃" onclick="javascript:history.go(-1);" />
					</td>
				</tr>
			</table>
			<script>  
     document.all("spec_state")[0].checked=true;//第一个radio选中  
</script>
		</html:form>


	</body>
	<script>
	function checksubjectform(sf){
		var message = "";
		var i = 1;
		if(sf.restype_id.value=="0"){
			message += i+") 请选择资源类型\n";
			i++;
		}
		if(sf.spec_code.value=="0"){
			message += i+") 请选择专业\n";
			i++;
		}
		if(sf.sublib_code.value.length==0||sf.sublib_code.value.length20){
			message += i+") 子库编码只能为1-20个字符\n";
			i++;
		}
				
		if(sf.sublib_title.value==""||sf.sublib_title.value.length>200){
			message += i+") 子库标题不能为空且小于50字\n";
			i++;
		}		
		if(message!=""){
			alert(message);
			return false;
		}else{
			return true;
		}
	}
</script>
</html>
