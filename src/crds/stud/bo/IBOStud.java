
package crds.stud.bo;

import java.util.List;

import crds.stud.web.form.FormStud;



public interface IBOStud {

	public boolean Add_stud(FormStud form) throws Exception;

	@SuppressWarnings("unchecked")
	public List Bro_stud(FormStud form) throws Exception;
}
