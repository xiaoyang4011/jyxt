package crds.system.user.web.form;


public class DClass {
	

	private int id;
	

	private String spec_code;
	
	private String class_code;
	
	public String getClass_code() {
		return class_code;
	}

	public void setClass_code(String classCode) {
		class_code = classCode;
	}


	private String class_name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSpec_code() {
		return spec_code;
	}

	public void setSpec_code(String specCode) {
		spec_code = specCode;
	}

	public String getClass_name() {
		return class_name;
	}

	public void setClass_name(String className) {
		class_name = className;
	}

}
