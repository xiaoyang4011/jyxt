package crds.basis.classa.dao;

import java.util.List;

import crds.basis.classa.web.form.AddClassForm;


public interface IDAOClass {
	public List  queryTest( )throws Exception ;
	
	public List  Classinfo() throws Exception;
	
	public boolean getaddClassForm(AddClassForm addClassFrom) throws Exception;
	
	public boolean delete(AddClassForm addClassFrom) throws Exception;
	public List updateClass(AddClassForm From) throws Exception;
	public boolean updateinfo(AddClassForm From) throws Exception;
	public List Class(AddClassForm Form) throws Exception;
	//查找所有机构系部
	 @SuppressWarnings("unchecked")
	public List  findDepartment() throws Exception;
	 
	//查找所选机构系部下的专业信息
		@SuppressWarnings("unchecked")
		public List findSpeciality(String dep_code ) throws Exception;
		
		 //查找所选专业下的课程信息
		@SuppressWarnings("unchecked")
		public List findCourse(String spec_code ) throws Exception;
		//查找所选专业下的子库信息
		@SuppressWarnings("unchecked")
		public List findSublib(String spec_code ) throws Exception;
		//查找子库资源类型信息
		@SuppressWarnings("unchecked")
		public String findResType(String sublib_id ) throws Exception;
		//删除多条班级记录
		@SuppressWarnings("unchecked")
		public boolean getDeleteMore(String str) throws Exception;

		public boolean checkform(AddClassForm addform) throws Exception;
}
