package crds.system.user.web.form;

import crds.pub.util.AbstractForm;

public class PLAddUserPoint extends AbstractForm{
	
	private String role_id;
	private String User_id;
	private int userpoint;
	public String getRole_id() {
		return role_id;
	}
	public String getUser_id() {
		return User_id;
	}
	public int getUserpoint() {
		return userpoint;
	}
	public void setRole_id(String roleId) {
		role_id = roleId;
	}
	public void setUser_id(String userId) {
		User_id = userId;
	}
	public void setUserpoint(int userpoint) {
		this.userpoint = userpoint;
	}

}
