package crds.basis.classa.web.form;



import crds.pub.util.AbstractForm;

public class AddClassForm extends AbstractForm{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String class_code;//课程编码
	private String spec_code;//专业编码
	private String class_name;//课程名称
	private String spec_name;//专业名称
	private String query_spec_name; 
	private String spec;
	private String flag;
	
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public void setClass_code(String class_code) {
		this.class_code = class_code;
	}
	public String getClass_code() {
		return class_code;
	}
	public void setSpec_code(String spec_code) {
		this.spec_code = spec_code;
	}
	public String getSpec_code() {
		return spec_code;
	}
	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}
	public String getClass_name() {
		return class_name;
	}
	public void setSpec_name(String spec_name) {
		this.spec_name = spec_name;
	}
	public String getSpec_name() {
		return spec_name;
	}
	public void setQuery_spec_name(String query_spec_name) {
		this.query_spec_name = query_spec_name;
	}
	public String getQuery_spec_name() {
		return query_spec_name;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getFlag() {
		return flag;
	}	
}