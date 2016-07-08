package crds.company.bo.impl;

import java.util.List;

import crds.company.bo.IBOcy;
import crds.company.dao.IDAOcy;
import crds.company.web.form.Formcy;


public class BOcy implements IBOcy{
	private IDAOcy dao;
	public void setDAOcy(IDAOcy dao) {
		this.dao = dao;
	}
	public int Add_cy(Formcy form) throws Exception 
	{
		return (dao.Add_cy(form));
	}

	@SuppressWarnings("unchecked")
	public List Bro_cy(Formcy form) throws Exception 
	{
		return (dao.Bro_cy(form));
	}

	public int Update_cy(Formcy form) throws Exception 
	{
		return (dao.Update_cy(form));
	}
	
	@SuppressWarnings("unchecked")
	public List Jump_cy(Formcy form) throws Exception 
	{
		return (dao.Jump_cy(form));
	}
	public int Del_cy(Formcy form) throws Exception
	{
		return (dao.Del_cy(form));
	}

}
