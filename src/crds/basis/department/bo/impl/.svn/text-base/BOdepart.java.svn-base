package crds.basis.department.bo.impl;

import java.util.List;
import crds.basis.department.bo.IBOdepart;
import crds.basis.department.dao.IDAOdepart;
import crds.basis.department.web.FormDepart;

public class BOdepart implements IBOdepart{
	private IDAOdepart dao;
	public void setDAOdepart(IDAOdepart dao) {
		this.dao = dao;
	}

	public boolean addDepart(FormDepart form)throws Exception{
		return this.dao.addDepart(form);
	}
	@SuppressWarnings("unchecked")
	public List DepartmentInfo() throws Exception {
		return dao.DepartmentInfo();
	}
	public boolean checkExists(FormDepart form) throws Exception 
	{
		return(dao.checkExists(form));
	}
	@SuppressWarnings("unchecked")
	public List updateJump(FormDepart form) throws Exception {
		return dao.updateJump(form);
	}
	public boolean updatedepart(FormDepart form) throws Exception 
	{
		return(dao.updatedepart(form));
	}
	public boolean delete(FormDepart form) throws Exception{
		return(dao.delete(form));
	}
	
}
