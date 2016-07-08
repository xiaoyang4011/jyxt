package crds.lyb.bo.impl;

import java.util.List;
import crds.lyb.bo.IBOlyb;
import crds.lyb.dao.IDAOlyb;
import crds.lyb.web.form.Formlyb;
import crds.lyb.bo.IBOlyb;
import crds.lyb.dao.IDAOlyb;

@SuppressWarnings("unused")
public class BOlyb implements IBOlyb{	
	private IDAOlyb dao;
	public void setDAOlyb(IDAOlyb dao) {
		this.dao = dao;
	}
	
	@SuppressWarnings("unchecked")
	public int Add(Formlyb form) throws Exception 
	{
		return (dao.Add(form));
	}

	@SuppressWarnings("unchecked")
	public List Bro(Formlyb form) throws Exception 
	{
		return (dao.Bro(form));
	}

	

	@SuppressWarnings("unchecked")
	public int Update(Formlyb form) throws Exception 
	{
		return (dao.Update(form));
	}
	
	@SuppressWarnings("unchecked")
	public List Jump(Formlyb form) throws Exception 
	{
		return (dao.Jump(form));
	}
	//调用dao进行删除
	public int Del(Formlyb form) throws Exception
	{
		return (dao.Del(form));
	}
	@SuppressWarnings("unchecked")
	public List Bro1(Formlyb form) throws Exception 
	{
		return (dao.Bro1(form));
	}
	@SuppressWarnings("unchecked")
	public List zxdt(Formlyb form) throws Exception 
	{
		return (dao.zxdt(form));
	}
	@SuppressWarnings("unchecked")
	public List xnzph(Formlyb form) throws Exception 
	{
		return (dao.xnzph(form));
	}
	@SuppressWarnings("unchecked")
	public List xwzph(Formlyb form) throws Exception 
	{
		return (dao.xwzph(form));
	}
	@SuppressWarnings("unchecked")
	public List jyzc(Formlyb form) throws Exception 
	{
		return (dao.jyzc(form));
	}
	@SuppressWarnings("unchecked")
	public List jyzd(Formlyb form) throws Exception 
	{
		return (dao.jyzd(form));
	}
}
