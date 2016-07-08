package crds.basis.speciality.bo;

import java.util.List;

import crds.basis.speciality.web.form.FormSpeciality;


public interface IBOSpeciality {
	
	@SuppressWarnings("unchecked")
	public List depart_query( ) throws Exception;
	public List speciality_query(FormSpeciality formspeciality) throws Exception;
	public List speciality_query1(FormSpeciality formspeciality) throws Exception;
	public List speciality_queryall(FormSpeciality formspeciality) throws Exception;
	//添加专业信息	 
	public FormSpeciality specialityadd(FormSpeciality formspeciality) throws Exception;
	
	//修改专业信息 
	public int specialityupdatedo(FormSpeciality formspeciality) throws Exception;
	
	//删除专业信息 
	public FormSpeciality specialitydelete(FormSpeciality formspeciality) throws Exception;
	
	//查询指定部门的专业信息
	@SuppressWarnings("unchecked")
	public List dep_spec_query(FormSpeciality formspeciality) throws Exception;
}
