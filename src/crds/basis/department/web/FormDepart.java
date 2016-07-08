package crds.basis.department.web;

import crds.pub.util.AbstractForm;
@SuppressWarnings("serial")
public class FormDepart extends AbstractForm{
	private String dep_code=null;//机构编码
	public String getDep_code() {
		return dep_code;
	}
	public void setDep_code(String depCode) {
		dep_code = depCode;
	}
	private String dep_name=null;//机构名称
	public String getDep_name() {
		return dep_name;
	}
	public void setDep_name(String depName) {
		dep_name = depName;
	}

}
