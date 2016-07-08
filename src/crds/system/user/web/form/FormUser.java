package crds.system.user.web.form;

import java.awt.image.BufferedImage;
import java.util.LinkedHashMap;

import crds.pub.util.AbstractForm;

/**
 *   
 * @author : maogf
 * @date : May 11, 2009 5:20:05 PM
 * @email : maogenfeng@gmail.com
 */
public final class FormUser extends AbstractForm {

	/**
	 * @field : serialVersionUID
	 * @fieldType : long
	 */
 
	private static final long serialVersionUID = 1L;
	String query_user_name;//查询用得字段
	String user_id;
	String dep_code;
	String dep_name;
	String spec_code;
	String class_code;
	String user_name;
	String password;
	String real_name;
	String user_type;
	int member_type;
	int user_point;
	public String getQuery_user_name() {
		return query_user_name;
	}
	public void setQuery_user_name(String queryUserName) {
		query_user_name = queryUserName;
	}
	
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String userID) {
		user_id = userID;
	}
	public String getDep_code() {
		return dep_code;
	}
	public void setDep_code(String depCode) {
		dep_code = depCode;
	}
	public String getSpec_code() {
		return spec_code;
	}
	public void setSpec_code(String specCode) {
		spec_code = specCode;
	}
	public String getClass_code() {
		return class_code;
	}
	public void setClass_code(String classCode) {
		class_code = classCode;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String userName) {
		user_name = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getReal_name() {
		return real_name;
	}
	public void setReal_name(String realName) {
		real_name = realName;
	}
	public String getUser_type() {
		return user_type;
	}
	public void setUser_type(String userType) {
		user_type = userType;
	}
	public int getMember_type() {
		return member_type;
	}
	public void setMember_type(int memberType) {
		member_type = memberType;
	}
	public int getUser_point() {
		return user_point;
	}
	public void setUser_point(int userPoint) {
		user_point = userPoint;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

	
	
}