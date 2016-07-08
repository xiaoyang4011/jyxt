package crds.system.sysSite.web.form;

import crds.pub.util.AbstractForm;

public class BaseParasForm extends AbstractForm {
	String site_name;//网站名称
	String site_description;//网站描述
	String site_open;//网站开放
	String site_key;//网站关键字
	String site_anonymous;//匿名访问
	String copyright;//版权信息
	String words_filter;//脏话过滤
	String script_filter;//脚本过滤
	int timeout;//会话超时时间
	String register_open;//允许注册
	String register_check;//注册审核
	int maxlength;//最长用户名
	int minlength;	//最短用户名
	String deny_name;//禁止用户名
	int file_maxsize;//上传文件最大尺寸
	
	public int getFile_maxsize() {
		return file_maxsize;
	}
	public void setFile_maxsize(int fileMaxsize) {
		file_maxsize = fileMaxsize;
	}
	public String getSite_name() {
		return site_name;
	}
	public void setSite_name(String siteName) {
		site_name = siteName;
	}
	public String getSite_description() {
		return site_description;
	}
	public void setSite_description(String siteDescription) {
		site_description = siteDescription;
	}
	public String getSite_open() {
		return site_open;
	}
	public void setSite_open(String siteOpen) {
		site_open = siteOpen;
	}
	public String getSite_key() {
		return site_key;
	}
	public void setSite_key(String siteKey) {
		site_key = siteKey;
	}
	public String getSite_anonymous() {
		return site_anonymous;
	}
	public void setSite_anonymous(String siteAnonymous) {
		site_anonymous = siteAnonymous;
	}
	public String getCopyright() {
		return copyright;
	}
	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}
	public String getWords_filter() {
		return words_filter;
	}
	public void setWords_filter(String wordsFilter) {
		words_filter = wordsFilter;
	}
	public String getScript_filter() {
		return script_filter;
	}
	public void setScript_filter(String scriptFilter) {
		script_filter = scriptFilter;
	}
	public String getRegister_open() {
		return register_open;
	}
	public void setRegister_open(String registerOpen) {
		register_open = registerOpen;
	}
	public String getRegister_check() {
		return register_check;
	}
	public void setRegister_check(String registerCheck) {
		register_check = registerCheck;
	}
	public String getDeny_name() {
		return deny_name;
	}
	public void setDeny_name(String denyName) {
		deny_name = denyName;
	}
	public int getTimeout() {
		return timeout;
	}
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	public int getMaxlength() {
		return maxlength;
	}
	public void setMaxlength(int maxlength) {
		this.maxlength = maxlength;
	}
	public int getMinlength() {
		return minlength;
	}
	public void setMinlength(int minlength) {
		this.minlength = minlength;
	}

}
