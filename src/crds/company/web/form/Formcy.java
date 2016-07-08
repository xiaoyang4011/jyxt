package crds.company.web.form;

import crds.pub.util.AbstractForm;

public final class Formcy extends AbstractForm{
	private static final long serialVersionUID = 1L;
	String cy_id;
	String cy_name;
	String cy_lead;
	String cy_zs;
	String cy_summary;
	String cy_remark;
	String cy_date;
	String user_ID;
	public String getUser_ID() {
		return user_ID;
	}
	public void setUser_ID(String userID) {
		user_ID = userID;
	}
	public String getCy_date() {
		return cy_date;
	}
	public void setCy_date(String cyDate) {
		cy_date = cyDate;
	}
	public String getCy_id() {
		return cy_id;
	}
	public void setCy_id(String cyId) {
		cy_id = cyId;
	}
	public String getCy_name() {
		return cy_name;
	}
	public void setCy_name(String cyName) {
		cy_name = cyName;
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
	public String getCy_summary() {
		return cy_summary;
	}
	public void setCy_summary(String cySummary) {
		cy_summary = cySummary;
	}
	public String getCy_remark() {
		return cy_remark;
	}
	public void setCy_remark(String cyRemark) {
		cy_remark = cyRemark;
	}
}
