package crds.system.user.web.form;


import org.apache.struts.upload.FormFile;

import crds.pub.util.AbstractForm;

public class AddUser extends AbstractForm {
    private static final long serialVersionUID = 1L;
	private FormFile file;
	public FormFile getFile() {
		return file;
	}
	public void setFile(FormFile file) {
		this.file = file;
	}
	//班级编码
	private String class_code;
	//班级名称
	private String class_name;
	//机构编码
	private String dep_code;
	//会员类型
	private int member_type;
	//密码
	private String password;


	//真实姓名
	private String real_name;
	//用户角色ID
	private String role_id;
	//专业编码
	private String spec_code;
	//用户ID
	private String user_ID="0";
	//用户名
	private String user_name;


	//用户积分
	private int user_point;
	//用户类型
	private String user_type;
	private String userid;
	//用户状态
	private String user_state;
	//添加用户的角色数组
	private String[] roleList;
	public String[] getRoleList() {
		return roleList;
	}
	public void setRoleList(String[] roleList) {
		this.roleList = roleList;
	}
	public String getUser_state() {
		return user_state;
	}
	public void setUser_state(String userState) {
		user_state = userState;
	}
	////////////////////////联动
	//xibu
	private String dept;
	//zhuanye
	private String spec;

	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	///////////////////
	public String getClass_code() {
		return class_code;
	}
	public String getClass_name() {
		return class_name;
	}
	public String getDep_code() {
		return dep_code;
	}
	private String getISO(String iso){
		   String userNames="";
			try{
			userNames=new String(iso.getBytes("UTF-8"),"UTF-8");
			}catch(Exception e){
				userNames="null";
			}
			return userNames;
	 }
	public int getMember_type() {
		return member_type;
	}
	
	public String getPassword() {
		return password;
	}
	public String getReal_name() {
		return real_name;
	}
	public String getRole_id() {
		return role_id;
	}
	public String getSpec_code() {
		return spec_code;
	}
	public String getUser_ID() {
		return user_ID;
	}
	public String getUser_name() {
		return user_name;
	}
	public int getUser_point() {
		return user_point;
	}
	public String getUser_type() {
		return user_type;
	}
	public String getUserid() {
		return getISO(userid);
	}
	public void setClass_code(String classCode) {
		class_code =getISO(classCode);
	}
	public void setClass_name(String className) {
		class_name = getISO(className);
	}
	public void setDep_code(String depCode) {
		dep_code = getISO(depCode);
	}
	public void setMember_type(int memberType) {
		member_type = memberType;
	}
	public void setPassword(String password) {
		this.password =getISO(password);
	}
	
	public void setReal_name(String realName) {
		real_name =getISO(realName);
	}
	public void setRole_id(String roleId) {
		role_id = roleId;
	}
	public void setSpec_code(String specCode) {
		spec_code = getISO(specCode);
	}
	public void setUser_ID(String userID) {
		user_ID =getISO(userID);
	}
	public void setUser_name(String userName) {
		user_name=getISO(userName);
	}
	public void setUser_point(int userPoint) {
		user_point = userPoint;
	}
	public void setUser_type(String userType) {
		user_type =getISO(userType);
	}
	
	
   public void setUserid(String userid) {
	this.userid =getISO(userid);
}

}
