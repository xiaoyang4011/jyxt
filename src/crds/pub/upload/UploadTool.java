package crds.pub.upload;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.util.FileCopyUtils;

import crds.pub.util.BusinessID;
import crds.pub.util.CommonMethod;
import crds.pub.util.SpringBeanServerUtils;

public final class UploadTool {
	private static Logger logger = Logger.getLogger(UploadTool.class);
	//================================
	private static UploadFileInfo ufi;
	private static boolean isCanUpload = true;//是否可以上传,true-可以上传,false-不可以上传
	//===========================
	/** 系统内部评级文档目录,系统外部评级文档目录,系统同业比较文档目录,系统目录,模板文件目录 */
	public final static String[] DIRECTORYS = new String[]{"internal","external","system","temp","template"};
	public static String webappsRootPath = "." + File.separator + "webapps"+File.separator;
	public static String RootPathInternal = DIRECTORYS[0] + File.separator;
	public static String RootPathExternal = DIRECTORYS[1] + File.separator;
	public static String RootPathExternalHelp = RootPathExternal + "Help" + File.separator;
	public static String RootPathExternalRatingReport = RootPathExternal + "RatingReport" + File.separator;
	//
	public static String webRootPathInternal = webappsRootPath+RootPathInternal;
	public static String webRootPathExternal = webappsRootPath+RootPathExternal;
	/** 模板文件目录 */
	public static String RootPathTemplate = DIRECTORYS[4] + File.separator;
	/** 信用评级一般企业财务模板文件路径 */
	public static String CreditRatiingModelET = RootPathTemplate + "common.xls";
	/** 信用评级商业银行财务模板文件路径 */
	public static String CreditRatiingModelBT = RootPathTemplate + "bank.xls";
	/** 同业比较数据导入模板文件路径 */
	public static String ComparisonModel = RootPathTemplate + "downLoad_comparison.xls";
	/** 公司同业比较文件名称 */
	public static String companyComparisonFileName = "同业比较.xls";
	/** 财务数据导入模板文件路径 */
	public static String FinModel = "_downLoad_Fin_Model.xls";
	private UploadTool(){}
	
	/**
	 * 文件检验,当types为空则不对文件类型校验,当maxSize<0时不对文件大小校验.
	 * @param types 文件类型,多个文件类型时使用#进行分割,如:zip|JPG|txt,不区分大小写.
	 * @param maxSize 文件大小,以字节B为单位.
	 * @return
	 */
	@SuppressWarnings("unused")
	private boolean fileCheck(UploadFileInfo ufInfo, String checkTypes,int checkMaxSize){
		if(checkMaxSize>0){
			if(ufInfo.getFile_size()>checkMaxSize){
				isCanUpload = false;
				ufInfo.setUploadInfo("上传失败,文件过大,maxSize="+checkMaxSize);
			}
		}
		if(null != checkTypes && (checkTypes=checkTypes.trim()).length()>0 && isCanUpload){
			isCanUpload = false;
			String[] typs = checkTypes.split("\\|");
			for (String typ : typs) {
				if(typ.equals(ufInfo.getFile_type())){
					isCanUpload = true;
					break;
				}
			}
			if(isCanUpload==false){
				ufInfo.setUploadInfo("上传的文件类型:"+ufInfo.getFile_type()+",不符合内容:"+checkTypes);
			}
		}
		ufInfo.setUploadResult(isCanUpload);
		return isCanUpload;
	}
	/**
	 * 上传文件到服务器中
	 * @param ufInfo
	 * @param reFileName
	 * @param isOverlay
	 */
	public static void upload(UploadFileInfo ufInfo,String reFileName,boolean isOverlay){
		ufi=ufInfo;
		upload(ufInfo.getUploadDirectoryPath(),reFileName,isOverlay);
		ufInfo.setUploadResult(ufi.getUploadResult());
		ufInfo.setUploadInfo(ufi.getUploadInfo());
	}
	/**
	 * 重新命名文件并上传到目录directoryPath中.
	 * @param directoryPath 目标目录,目录最后一个字符必须是File.separator
	 * @param reFileName 重命名的文件名称,不包括后缀(扩展名)
	 * @param isOverlay 是否覆盖,如果此文件存在是否覆盖.true-覆盖,false-不允许覆盖
	 * @throws FileUploadException 
	 */
	private static void upload(String directoryPath,String reFileName,boolean isOverlay) {
		if(isCanUpload){
			if(null == reFileName || (reFileName=reFileName.trim()).length()==0){
				reFileName = ufi.getFile_nametype();
			} else {
				reFileName += ufi.getFile_type().length()>0 ? ("."+ufi.getFile_type()):"";
			}
			File f = new File(directoryPath);
			if(!f.isDirectory()) {
				f.mkdirs();
			}
			f = new File(directoryPath+reFileName);
			if(f.exists() && !isOverlay){//存在的文件是否覆盖
				ufi.setUploadInfo("不允许覆盖已存在的文件:"+f.getPath());
				ufi.setUploadResult(false);
			}
			try {
				//文件路径
				ufi.setFile_path(directoryPath+reFileName);
				//上传到服务器
				FileCopyUtils.copy(ufi.getFileItem().getInputStream(), new FileOutputStream(f));
//				ufi.setUploadInfo("文件["+f.getName()+"]上传成功.");
				ufi.setUploadResult(true);
//				logger.info(ufi.getUploadInfo());
			} catch (FileNotFoundException ffe) {
				ufi.setUploadInfo("文件["+f.getName()+"]上传错误: " + ffe.toString());
				ufi.setUploadResult(false);
				logger.error(ufi.getUploadInfo());
			} catch (IOException ioe) {
				ufi.setUploadInfo("文件["+f.getName()+"]上传错误: " + ioe.toString());
				ufi.setUploadResult(false);
				logger.error(ufi.getUploadInfo());
			}
		}
	}
	/**
	 * 上传文件到服务器中,同时将相关信息写入数据库表中
	 * @param ufInfo
	 * @throws Exception
	 */
	public static void uploadDB(UploadFileInfo ufInfo) throws Exception {
		uploadDB(ufInfo,null,true);
	}
	/**
	 * 上传文件到服务器中,同时将相关信息写入数据库表中
	 * @param ufInfo
	 * @param reFileName 重新命名文件名称
	 * @param isOverlay  是否替换上传文件
	 * @throws Exception
	 */
	public static void uploadDB(UploadFileInfo ufInfo,String reFileName,boolean isOverlay) throws Exception {
		if(isOverlay && ufInfo.getFile_id()==null && ufInfo.getFile_id().trim().length()==0){//替换上传则删除原有文件,此时必须有文件ID
			delUploadFile(CommonMethod.getUploadFilePath(ufInfo.getFile_id()));
		} else if(( ufInfo.getFile_id()==null || ufInfo.getFile_id().trim().length()==0)||!isOverlay ){
			ufInfo.setFile_id(((BusinessID)SpringBeanServerUtils.getBean("businessID")).generate("F"));
		}
		ufInfo.setFile_path(ufInfo.getFile_path().replace(ufInfo.getFile_name(), ufInfo.getFile_id()));
		if(reFileName!=null && (reFileName=reFileName.trim()).length()>0){
			ufInfo.setFile_name(reFileName);
		}
		
		int rows = CommonMethod.uploadFileInfoInsertToDB(ufInfo,isOverlay);
		if(rows>0){//信息入库成功则上传物理文件
			ufi = ufInfo;
			upload(ufInfo.getUploadDirectoryPath(),ufInfo.getFile_id(),isOverlay);
			ufInfo.setUploadResult(ufi.getUploadResult());
			ufInfo.setUploadInfo(ufi.getUploadInfo());
		}
	}

	/**
	 * 删除上传的文件
	 * @param filePath
	 * @return
	 */
	public static boolean delUploadFile(String filePath){
		return delUploadFile(new File(filePath));
	}

	/**
	 * 删除上传的文件
	 * @param file
	 * @return
	 */
	public static boolean delUploadFile(File file){
		if(file.exists()) {
			return file.delete();
		} else {
			logger.info("文件:"+file.getPath()+"不存在.");
			return false;
		}
	}
	
	/**
	 * 重新命名某个文件或文件夹的名称
	 * @param oldFilePath 旧文件或文件夹路径
	 * @param newFileName 新文件路径
	 * @return
	 */
	public static boolean renameFileOrDirectory(String oldFilePath,String newFileName){
		if(null == oldFilePath || (oldFilePath=oldFilePath.trim()).length()==0 || null == newFileName || (newFileName=newFileName.trim()).length()==0){
			logger.warn("文件重命名失败.");
			return false;
		}
		File oldFile = new File(oldFilePath);
		if(oldFile.exists()){
			File newFile = null;
			try {
				newFile = new File(oldFile.getParent()+File.separator+newFileName);
				if(oldFile.getPath().equals(newFile.getPath())){
					logger.warn("文件重命名失败,["+oldFile.getPath()+"]to["+newFile.getPath()+"].\nerror:文件名称不能相同.");
					return false;
				}
				if(oldFile.isDirectory()){//重命名目录
					FileUtils.copyDirectory(oldFile, newFile);
					FileUtils.deleteDirectory(oldFile);
				} else {//重命名文件
					oldFile.renameTo(newFile);
				}
				return true;
			} catch (IOException e) {
				logger.warn("文件重命名失败,["+oldFile.getPath()+"]to["+newFile.getPath()+"].\nerror:"+e.getMessage());
				return false;
			}
		}
		logger.warn("文件重命名失败,文件["+oldFile.getPath()+"]不存在.");
		return false;
	}
	
	/**
	 * @param directoryPath 目录路径
	 * @return
	 */
	public static boolean delDirectory(String directoryPath) {
		return delDirectory(new File(directoryPath));
	}
	/**
	 * @param directoryFile 目录文件对象
	 * @return
	 */
	public static boolean delDirectory(File directoryFile) {
		if(directoryFile != null && directoryFile.exists()){
			try {
				FileUtils.deleteDirectory(directoryFile);
				return true;
			} catch (IOException e) {
				logger.warn("删除目录["+directoryFile.getPath()+"]失败.\nerror:"+e.getMessage());
			}
		}
		return false;
	}
	/**
	 * 判断下载的文件是否存在
	 * @param downloadFilePath 下载的文件路径
	 * @return true-存在,false-不存在
	 */
	public static boolean downloadFileIsExist(String downloadFilePath){
		return downloadFileIsExist(new File(downloadFilePath));
	}
	/**
	 * 判断下载的文件是否存在
	 * @param downloadFile 下载的文件对象
	 * @return true-存在,false-不存在
	 */
	public static boolean downloadFileIsExist(File downloadFile){
		return downloadFile.exists() && downloadFile.isFile();
	}
	
	/**
	 * 获取系统模板文件的路径
	 * @param modelFileId
	 * @return
	 */
	public static String getSystemModelFilePath(String company_type,String modelFileId){
		if("1".equals(modelFileId)) {//信用评级一般企业财务数据模板文件
			return UploadTool.CreditRatiingModelET;
		} else if("2".equals(modelFileId)) {//同业比较数据导入模板文件路径
			return UploadTool.ComparisonModel;
		} else if("3".equals(modelFileId)) {//信用评级商业银行财务数据模板文件
			return UploadTool.CreditRatiingModelBT;
		} else if("4".equals(modelFileId)){
			return RootPathTemplate+company_type+FinModel;
		} else {
			logger.warn("系统模板文件不存在此ID:"+modelFileId);
			return "";
		}
	}
}
