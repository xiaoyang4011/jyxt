package crds.pub.upload;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.struts.MappingDispatchActionSupport;

import crds.pub.util.CommonMethod;
import crds.pub.util.Constant;
import crds.pub.util.FormUserOperation;

public class ActionUploadTool extends MappingDispatchActionSupport {
	private static Logger logger = Logger.getLogger(ActionUploadTool.class);
	
	public ActionForward uploadFile(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return null;
	}
	/***
	 * 下载文件
	 * @param
	 * @return
	 * @exception
	 */
	public ActionForward downloadFile(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FormUserOperation user=Constant.getFormUserOperation(request);
		String fileName = Constant.trimEmptyDefault(request.getParameter("fileName"),"").trim();
		String filePath = Constant.trimEmptyDefault(request.getParameter("filePath"),"").trim();
		String file_flag = Constant.trimEmptyDefault(request.getParameter("filePath"),"").trim();
		//1-系统模板文件,2-公司内部评级文件,3-(默认)文件路径保存在表EVALUATION_EXTERNAL_FILE_INFO中的文件,4-公司同业比较文件导出
		String fileFlag = Constant.trimEmptyDefault(request.getParameter("fileFlag"),"3").trim();
		File file =null;
		if("1".equals(fileFlag)){//系统模板文件,此时filePath只需传模板文件ID
			filePath = UploadTool.getSystemModelFilePath(user.getCompany_type(),filePath);
		} else if("2".equals(fileFlag)){//公司内部评级文件,此时filePath只需公司代码
			filePath = UploadTool.webRootPathInternal + filePath +File.separator+Constant.trimEmptyDefault(request.getParameter("ratingReport"),"");
		} else if("3".equals(fileFlag)){//此时filePath只需文件ID
			Map<String,String> fileInfo = CommonMethod.getUploadFileInfo(filePath);
			if(fileInfo!=null){
				fileName = fileInfo.get("FILE_NAME");
				filePath = fileInfo.get("FILE_PATH");//external\RatingReport\10000001\F20110419003.xlsx
			}
		} else if("4".equals(fileFlag)){//公司同业比较文件导出,此时filePath只需公司代码
			fileName = UploadTool.companyComparisonFileName;
			filePath = UploadTool.webRootPathInternal+filePath+File.separator+fileName;
			int pointIndex = fileName.indexOf(".");
			fileName = fileName.substring(0,pointIndex)+Constant.getCurrDateDefault()+fileName.substring(pointIndex+1);
		}else if("5".equals(fileFlag)){//公司内部评级文件
			String company_code=request.getParameter("company_code");
			String task_id=request.getParameter("task_id");
			String down_flag=request.getParameter("down_flag");//RM:报告主体 RC：报告附体(即观点)
			String file_name="RM".equals(down_flag)?"report":"content1";
			filePath = UploadTool.webRootPathInternal + company_code +File.separator+task_id+File.separator;
			String oldFile_path=filePath+file_name+".html";
			String newFile_path=filePath+file_name+".doc";
			File old_file=new File(oldFile_path);
			if(old_file.exists()){
				FileUtils.copyFile(old_file, new File(newFile_path));
			}
			filePath=newFile_path;
		} 
		if("5".equals(fileFlag)){
			file = new File(filePath);
		}else{
			if("4".equals(file_flag)){
				String rela_path = request.getSession().getServletContext().getRealPath("/")+"..\\"+filePath;
				file = new File(rela_path);
			}else{
				file = new File(UploadTool.webappsRootPath + filePath);
			}
		}
		if(file.exists() && file.isFile()){
			fileName = Constant.trimEmptyDefault(fileName, file.getName()).trim();
			if(fileName.lastIndexOf(".")<0){
				int pointIndex = filePath.lastIndexOf(".");
				if(pointIndex > 0 && filePath.length() > pointIndex)
					fileName += filePath.substring(pointIndex);
			}
			InputStream is = null;
			OutputStream os = null;
			BufferedInputStream bis = null;
			BufferedOutputStream bos = null;
			try{
				is = new FileInputStream(file);
				os = response.getOutputStream();
				bis = new BufferedInputStream(is);
				bos = new BufferedOutputStream(os);
				
				fileName = java.net.URLEncoder.encode(fileName, "UTF-8");// 处理中文文件名的问题 
				fileName = new String(fileName.getBytes("UTF-8"), "GBK");// 处理中文文件名的问题 
				response.reset();
				response.setCharacterEncoding("UTF-8");
				//设置下载文件格式
				response.setContentType("application/vnd.ms-excel");// 不同类型的文件对应不同的MIME类型 
				response.setHeader("Content-Disposition", "attachment; filename="+fileName); 
				int bytesRead = 0;
				byte[] buffer = new byte[1024];
				while ((bytesRead = bis.read(buffer)) != -1){ 
					bos.write(buffer, 0, bytesRead);    // 将文件发送到客户端 
				}
			}catch(Exception e){
				logger.info("下载失败!原因:" + e.getMessage());
				showMessage(response,"下载失败,系统发生异常.");
			} finally{
				try{
					if(bos != null)
						bos.flush();
					if(bis != null)
						bis.close();
					if(bos != null)
						bos.close();
					if(is != null)
						is.close();
					if(os != null)
						os.close();
					//删除doc文件
					if("5".equals(fileFlag)){
						File f= new File(filePath);
						if(f.exists()){
							f.delete();	
						}
					}
				} catch(Exception e) {
					logger.info("文件下载时,输入输出流关闭错误失败!原因:"+e.getMessage());
				}
			}
			return null;
		}
		showMessage(response,"文件不存在 或 所下载的资源不是文件!");
		return null;
	}
	private void showMessage(HttpServletResponse response, String msg) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		StringBuffer html = new StringBuffer();
		html.append("<html>");
		html.append("<script>");
		html.append("alert('" + msg + "');");
		html.append("</script>");
		html.append("</html>");
		response.getWriter().write(html.toString());
	}
}
