package crds.zhaosheng.bo.impl;

import java.util.List;

import crds.zhaosheng.bo.IBOzs;
import crds.zhaosheng.dao.IDAOzs;
import crds.zhaosheng.web.form.FormZs;

public class BOzs implements IBOzs{

	private IDAOzs dao;
	public void setDAOzs(IDAOzs dao) {
		this.dao = dao;
	}
	@SuppressWarnings("unchecked")
	public List Bro_stud(FormZs form) throws Exception 
	{
		return (dao.Bro_stud(form));
	}
	@SuppressWarnings("unchecked")
	public List Bro_cy(FormZs form) throws Exception 
	{
		return (dao.Bro_cy(form));
	}
	public int Update_jiuye(FormZs form) throws Exception 
	{
		return (dao.Update_jiuye(form));
	}
	public int del_jiuye(FormZs form) throws Exception 
	{
		return (dao.del_jiuye(form));
	}
}
