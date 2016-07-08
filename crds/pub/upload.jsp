<%@page pageEncoding = "UTF-8"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>

<html>
 <html:form action="/upload.do" method="POST" enctype="multipart/form-data">
  <html:file property="uploadfile" size="80%" />
  <input type="button" value="导 入" onclick="upload(this.form)" class="buttonGray">
 </html:form>
</html>

<script language="javascript">
function upload(obj)
{
 if(confirm("您现在选择的是XXX,您确定要导入吗？"))
 {
  var uploadfile = document.all.uploadfile.value;
  if((null == uploadfile) ||( "" == uploadfile))
  {
   alert("上传文件没有指定！");
   return false;
  }
     obj.action = '<html:rewrite page="/upload.do"/>';
     obj.submit();
 }
}
</script>