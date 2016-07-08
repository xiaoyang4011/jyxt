package crds.basis.speciality.dao;

import java.util.List;

import crds.basis.speciality.web.form.FormSpeciality;


public interface IDAOSpeciality {
	
	
	@SuppressWarnings("unchecked")
	public List  depart_query() throws Exception ;
	
	 public List  speciality_query(FormSpeciality formspeciality) throws Exception ;
	 public List speciality_query1(FormSpeciality formspeciality) throws Exception ;
	
	 //专业全部查询 
	    @SuppressWarnings("unchecked")
		public List  speciality_queryall(FormSpeciality formspeciality) throws Exception ;
	    
	  //专业信息添加	 　
		public int specialityadd(FormSpeciality formspeciality) throws Exception; 
		
	    //专业信息修改
	    public int  specialityupdatedo(FormSpeciality formspeciality) throws Exception ;
	    
	  //专业信息删除
		public int  specialitydelete(FormSpeciality formspeciality) throws Exception;
		
		//查询专业编号是否存在 
	  	public int speciality_queryCount(FormSpeciality formspeciality) throws Exception;
	  	
	   //查询该专业下是否有各种信息  
	   	public int speciality_SubInfo(FormSpeciality formspeciality) throws Exception ;
	   	
	  //查询指定部门的专业 
	    @SuppressWarnings("unchecked")
		public List   dep_spec_query(FormSpeciality formspeciality) throws Exception;

}
