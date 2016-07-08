package crds.stud.bo.impl;
import java.util.List;

import crds.stud.bo.IBOStud;
import crds.stud.dao.IDAOStud;
import crds.stud.web.form.FormStud;

public class BOStud implements IBOStud {

	private IDAOStud dao;

	public void setDAOStud(IDAOStud dao) {
		this.dao = dao;
	}
	public boolean Add_stud(FormStud form) throws Exception {
		return (dao.Add_stud(form));
	}
	@SuppressWarnings("unchecked")
	public List Bro_stud(FormStud form) throws Exception 
	{
		return (dao.Bro_stud(form));
	}
	
}
