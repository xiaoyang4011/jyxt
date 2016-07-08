package crds.zhaosheng.web.form;

import crds.pub.util.AbstractForm;

public final class FormZs extends AbstractForm{
	private static final long serialVersionUID = 1L;
	String user_ID;
	String real_name;
	String spec_name;
	String dep_name;
	String class_name;
	String user_type;
	String user_states;
	String cy_name;
	String cy_id;
	public String getCy_id() {
		return cy_id;
	}
	public void setCy_id(String cyId) {
		cy_id = cyId;
	}
	public String getCy_lead() {
		return cy_lead;
	}
	public void setCy_lead(String cyLead) {
		cy_lead = cyLead;
	}
	public String getCy_zs() {
		return cy_zs;
	}
	public void setCy_zs(String cyZs) {
		cy_zs = cyZs;
	}
	String cy_lead;
	String cy_zs;
	public String getUser_ID() {
		return user_ID;
	}
	public void setUser_ID(String userID) {
		user_ID = userID;
	}
	public String getReal_name() {
		return real_name;
	}
	public void setReal_name(String realName) {
		real_name = realName;
	}
	public String getSpec_name() {
		return spec_name;
	}
	public void setSpec_name(String specName) {
		spec_name = specName;
	}
	public String getDep_name() {
		return dep_name;
	}
	public void setDep_name(String depName) {
		dep_name = depName;
	}
	public String getClass_name() {
		return class_name;
	}
	public void setClass_name(String className) {
		class_name = className;
	}
	public String getUser_type() {
		return user_type;
	}
	public void setUser_type(String userType) {
		user_type = userType;
	}
	public String getUser_states() {
		return user_states;
	}
	public void setUser_states(String userStates) {
		user_states = userStates;
	}
	public String getCy_name() {
		return cy_name;
	}
	public void setCy_name(String cyName) {
		cy_name = cyName;
	}

}
