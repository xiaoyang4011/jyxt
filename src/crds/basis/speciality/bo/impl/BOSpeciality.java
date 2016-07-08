package crds.basis.speciality.bo.impl;

import java.util.List;

import crds.basis.speciality.bo.IBOSpeciality;
import crds.basis.speciality.dao.IDAOSpeciality;
import crds.basis.speciality.web.form.FormSpeciality;

 

public class BOSpeciality implements IBOSpeciality {
	
	private IDAOSpeciality dao;
	public IDAOSpeciality getDAOSpeciality() {
		return dao;
	}
	public void setDAOSpeciality(IDAOSpeciality dao) {
		this.dao = dao;
	}

	//查询系部信息
	@SuppressWarnings("unchecked")
	public List depart_query( ) throws Exception {
		// TODO Auto-generated method stub
		return dao.depart_query();
	
	}
	
	
	//查询专业信息
	@SuppressWarnings("unchecked")
	public List  speciality_query(FormSpeciality formspeciality) throws Exception {
		// TODO Auto-generated method stub
		return dao.speciality_query(formspeciality);
	
	}
	
	//查询一条专业信息
	@SuppressWarnings("unchecked")
	public List speciality_query1(FormSpeciality formspeciality) throws Exception {
		// TODO Auto-generated method stub
		return dao.speciality_query1(formspeciality);
	
	}
	//查询全部专业信息
	@SuppressWarnings("unchecked")
	public List speciality_queryall(FormSpeciality formspeciality) throws Exception {
		// TODO Auto-generated method stub
		return dao.speciality_queryall(formspeciality);
	
	}
	
	//添加专业信息
	@SuppressWarnings("unchecked")
	public FormSpeciality specialityadd(FormSpeciality formspeciality) throws Exception {
		// TODO Auto-generated method stub
		//查询该专业编号是否存在
		if(this.dao.speciality_queryCount(formspeciality)>0){
			formspeciality.setFlag(null);
			return formspeciality;
		}
		else
		{
		dao.specialityadd(formspeciality);
		formspeciality.setFlag("1");
		return formspeciality;
		}	
	}
	//修改专业信息
	@SuppressWarnings("unchecked")
	public int specialityupdatedo(FormSpeciality formspeciality) throws Exception {
		// TODO Auto-generated method stub
		return dao.specialityupdatedo(formspeciality);
	
	}
	//删除专业信息
	@SuppressWarnings("unchecked")
	public FormSpeciality specialitydelete(FormSpeciality formspeciality) throws Exception {
		// TODO Auto-generated method stub
		//查询该专业下是否有各种信息
		int n=this.dao.speciality_SubInfo(formspeciality);
		if(n>0){
			formspeciality.setFlag("0");
			return formspeciality;
		}
		else
		{
		dao.specialitydelete(formspeciality);
		formspeciality.setFlag("1");
		return formspeciality;
		}			
	}
	
	//查询指定部门的专业信息
	@SuppressWarnings("unchecked")
	public List dep_spec_query(FormSpeciality formspeciality) throws Exception {
		// TODO Auto-generated method stub
		return dao.dep_spec_query(formspeciality);	
	}
	
	
	 
}
