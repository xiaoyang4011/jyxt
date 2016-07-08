package crds.personalSpace.myBrowse.web.form;

import java.util.Date;

import crds.pub.util.AbstractForm;

public class FormMyBrowse extends AbstractForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String resource_name;
	private String resource_state;
	private Date browse_time;
	private String start_time;
	private String end_time;
	public String getResource_name() {
		return resource_name;
	}

	public void setResource_name(String resourceName) {
		resource_name = resourceName;
	}

	public String getResource_state() {
		return resource_state;
	}

	public void setResource_state(String resourceState) {
		resource_state = resourceState;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setBrowse_time(Date browse_time) {
		this.browse_time = browse_time;
	}

	public Date getBrowse_time() {
		return browse_time;
	}

}