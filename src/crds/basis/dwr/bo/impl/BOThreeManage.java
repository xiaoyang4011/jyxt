package crds.basis.dwr.bo.impl;

import java.util.List;

import crds.basis.dwr.bo.IBOThreeManage;
import crds.basis.dwr.dao.impl.DAOThreeManage;

public class BOThreeManage implements IBOThreeManage{
	DAOThreeManage dao;

	public DAOThreeManage getDao() {
		return dao;
	}

	public void setDao(DAOThreeManage dao) {
		this.dao = dao;
	}

	@SuppressWarnings("unchecked")
	public List findDepartment( ) throws Exception {
		// TODO Auto-generated method stub
		return dao.findDepartment();
	
	}
}
