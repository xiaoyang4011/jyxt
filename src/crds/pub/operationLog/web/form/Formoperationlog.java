package crds.pub.operationLog.web.form;

import crds.pub.util.AbstractForm;

/**
 * @specification :操作记录form
 * @version : 1.0
 * @auther : yangy
 * @date : 2008-7-1 下午05:00:49
 * @email : qilaug@gmail.com
 * @author ljy
 * @version: 2.0
 */
public final class Formoperationlog extends AbstractForm {

	private static final long serialVersionUID = 1l;

    /**
     * @field : password
     * @fieldType : String
     */
	
	private String begin_date = null;
	private String end_date = null;
	private String operation_code = null;
    private String user_name = null;
    private String operation_date = null;
    private String company_code = null;
    private String company_name = null;//发行人名称
    private String user_id;
    private String operation_memo;//操作内容
    
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getOperation_code() {
		return operation_code;
	}
	public void setOperation_code(String operation_code) {
		this.operation_code = operation_code;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getOperation_date() {
		return operation_date;
	}
	public void setOperation_date(String operation_date) {
		this.operation_date = operation_date;
	}
	public String getCompany_code() {
		return company_code;
	}
	public void setCompany_code(String company_code) {
		this.company_code = company_code;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public String getBegin_date() {
		return begin_date;
	}
	public void setBegin_date(String begin_date) {
		this.begin_date = begin_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getOperation_memo() {
		return operation_memo;
	}
	public void setOperation_memo(String operation_memo) {
		this.operation_memo = operation_memo;
	}
}
