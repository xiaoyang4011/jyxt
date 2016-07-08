package crds.statistics.web.form;

import crds.pub.util.AbstractForm;

public class FormSta extends AbstractForm{
	private static final long serialVersionUID = 1L;
	String cy_name;
	String num09;
	String jyl;
	String dep_name;
	public String getDep_name() {
		return dep_name;
	}
	public void setDep_name(String depName) {
		dep_name = depName;
	}
	public String getCy_name() {
		return cy_name;
	}
	public void setCy_name(String cyName) {
		cy_name = cyName;
	}
	public String getNum09() {
		return num09;
	}
	public void setNum09(String num09) {
		this.num09 = num09;
	}
	public String getJyl() {
		return jyl;
	}
	public void setJyl(String jyl) {
		this.jyl = jyl;
	}
}
