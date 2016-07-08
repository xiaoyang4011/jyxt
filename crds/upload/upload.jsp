<%@page pageEncoding = "UTF-8"%>  
<%String path = request.getContextPath();%>
      
    <h1>  
        文件上传  
    </h1>  
    <hr />  
    <form action="<%=path%>/upload.do" method="post" enctype="multipart/form-data">  
      
        upload:  
        <input type="file" name="file" />  
        <br />  
        <input type="submit" value="上传" />  
    </form>  