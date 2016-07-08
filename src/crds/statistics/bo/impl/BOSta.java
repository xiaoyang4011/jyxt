package crds.statistics.bo.impl;
import java.util.List;

import crds.statistics.bo.IBOSta;
import crds.statistics.dao.IDAOSta;
import crds.statistics.web.form.FormSta;


public class BOSta implements IBOSta{
	private IDAOSta dao;
	public void setDAOSta(IDAOSta dao) {
		this.dao = dao;
	}
		
@SuppressWarnings("unchecked")
public List cy_Bro(FormSta form) throws Exception 
	{
		return (dao.cy_Bro(form));
	}
@SuppressWarnings("unchecked")
public List cy_spec(FormSta form) throws Exception 
	{
		return (dao.cy_sepc(form));
	}
}
