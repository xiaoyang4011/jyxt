package crds.system.user.web.form;

public class Specialty {


	private int id;

	
	private String dep_code;
	
	private String spec_code;

	private String spec_name;
	
	private String spec_state;

	private String spec_description;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getSpec_name() {
		return spec_name;
	}

	public void setSpec_name(String specName) {
		spec_name = specName;
	}

	public String getSpec_state() {
		return spec_state;
	}

	public void setSpec_state(String specState) {
		spec_state = specState;
	}

	public String getSpec_description() {
		return spec_description;
	}

	public void setSpec_description(String specDescription) {
		spec_description = specDescription;
	}

}
