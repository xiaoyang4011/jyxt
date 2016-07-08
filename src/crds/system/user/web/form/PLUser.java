package crds.system.user.web.form;

public class PLUser {

	String class_code;
	String dep_code;
	int member_type;
	public String getControl_type() {
		return control_type;
	}
	public void setControl_type(String controlType) {
		control_type = controlType;
	}
	public String getUser_state() {
		return user_state;
	}
	public void setUser_state(String userState) {
		user_state = userState;
	}
	String password; 
	String real_name; 
	String role_id; 
	String spec_code;
	String user_ID;
	int user_point;
	String user_type;
	String control_type;
	String user_state;
	public String getClass_code() {
		return class_code;
	}
	public String getDep_code() {
		return dep_code;
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
	public int getUser_point() {
		return user_point;
	}
	public String getUser_type() {
		return user_type;
	}
	public void setClass_code(String classCode) {
		class_code = classCode;
	}
	public void setDep_code(String depCode) {
		dep_code = depCode;
	}
	public void setMember_type(String memberType) {
		member_type = Integer.parseInt(memberType);
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setReal_name(String realName) {
		real_name = realName;
	}
	public void setRole_id(String roleId) {
		role_id = roleId;
	}
	public void setSpec_code(String specCode) {
		spec_code = specCode;
	} 
	public void setUser_ID(String userID) {
		user_ID = userID;
	} 
	public void setUser_point(String userPoint) {
		user_point = Integer.parseInt(userPoint);
	} 
	public void setUser_type(String userType) {
		user_type = userType;
	} 

}
