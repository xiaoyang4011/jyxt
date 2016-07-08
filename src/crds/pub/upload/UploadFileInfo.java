package crds.pub.upload;

import java.io.Serializable;

import org.apache.struts.upload.FormFile;

import crds.pub.util.Constant;

public class UploadFileInfo implements Serializable {
	private static final long serialVersionUID = 5461421914441082673L;
	//============================================
	private String company_code;//文件所属公司
	private String debt_code;//债券代码(内部代码,TM 填入公司代码,TD填入债券代码)
	private String file_id;//文件ID
	private String file_name;//文件名称(不包括后缀)
	private String file_desc;//文件描述
	private String upload_date;//文件上传日期
	private String file_path;//保存到服务器上的文件路径
	private String upload_user;//上传文件的用户名称

	//============================================
	private int file_size;//文件大小
	private String file_nametype;//文件名称(包括后缀)
	private String file_type;//文件类型,即文件后缀(扩展名)
	private String content_type;//文件MIME
	private String uploadInfo;//上传之后的信息
	private boolean uploadResult = false;// 上传结果.true-成功,false-失败
	private String uploadDirectoryPath;
	private FormFile fileItem;
	private String upload_file_type;//文件归类
	public String getUpload_file_type() {
		return upload_file_type;
	}

	public void setUpload_file_type(String upload_file_type) {
		this.upload_file_type = upload_file_type;
	}

	public UploadFileInfo() {
	}
	
	public UploadFileInfo(FormFile fileItem) {
		this(fileItem,null);
	}
	/**
	 * @param fileItem
	 * @param uploadToServiceDirectoryPath 上传到服务器的目录路径
	 */
	public UploadFileInfo(FormFile fileItem,String uploadToServiceDirectoryPath) {
		uploadToServiceDirectoryPath = Constant.trimEmptyDefault(uploadToServiceDirectoryPath, "");
		if(null != fileItem){
			this.fileItem = fileItem;
			this.content_type = fileItem.getContentType();
			this.file_size = fileItem.getFileSize();
			this.file_nametype = fileItem.getFileName();
			int fileNamePointIndex = this.file_nametype.lastIndexOf(".");
			this.file_name = fileNamePointIndex>0?this.file_nametype.substring(0,fileNamePointIndex):this.file_nametype;
			this.file_type = fileNamePointIndex>0?this.file_nametype.substring(fileNamePointIndex+1):"";
			this.uploadDirectoryPath = uploadToServiceDirectoryPath;
			this.file_path = this.uploadDirectoryPath+this.file_nametype;
		}
	}
	
	public String getFile_id() {
		return file_id;
	}

	public void setFile_id(String file_id) {
		this.file_id = file_id;
	}

	public String getCompany_code() {
		return company_code;
	}

	public void setCompany_code(String company_code) {
		this.company_code = company_code;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getFile_desc() {
		return file_desc;
	}

	public void setFile_desc(String file_desc) {
		this.file_desc = file_desc;
	}

	public String getUpload_date() {
		return upload_date;
	}

	public void setUpload_date(String upload_date) {
		this.upload_date = upload_date;
	}

	public String getFile_path() {
		return file_path;
	}

	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}

	public String getUpload_user() {
		return upload_user;
	}

	public void setUpload_user(String upload_user) {
		this.upload_user = upload_user;
	}

	public String getDebt_code() {
		return debt_code;
	}

	public void setDebt_code(String debt_code) {
		this.debt_code = debt_code;
	}

	public int getFile_size() {
		return file_size;
	}

	public void setFile_size(int file_size) {
		this.file_size = file_size;
	}
	public String getFile_nametype() {
		return file_nametype;
	}
	public void setFile_nametype(String file_nametype) {
		this.file_nametype = file_nametype;
	}

	public String getFile_type() {
		return file_type;
	}

	public void setFile_type(String file_type) {
		this.file_type = file_type;
	}

	public String getContent_type() {
		return content_type;
	}

	public void setContent_type(String content_type) {
		this.content_type = content_type;
	}

	public String getUploadInfo() {
		return uploadInfo;
	}

	public void setUploadInfo(String uploadInfo) {
		this.uploadInfo = uploadInfo;
	}

	public boolean getUploadResult() {
		return uploadResult;
	}

	public void setUploadResult(boolean uploadResult) {
		this.uploadResult = uploadResult;
	}
	public String getUploadDirectoryPath() {
		return uploadDirectoryPath;
	}
	public void setUploadDirectoryPath(String uploadDirectoryPath) {
		this.uploadDirectoryPath = uploadDirectoryPath;
	}

	public FormFile getFileItem() {
		return fileItem;
	}

	public void setFileItem(FormFile fileItem) {
		this.fileItem = fileItem;
	}
}
