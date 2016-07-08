
package crds.stud.dao;

import java.util.List;

import crds.stud.web.form.FormStud;


public interface IDAOStud {
	
	public boolean Add_stud(FormStud form) throws Exception;
	public List Bro_stud(FormStud form) throws Exception;
}


