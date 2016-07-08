package crds.basis.classa.bo;

import java.util.List;

import crds.basis.classa.web.form.AddClassForm;



public interface IBOClass  {
	public List queryTest( ) throws Exception ;
	public List ClassInfo() throws Exception;
	public boolean getaddClassForm(AddClassForm addClassFrom) throws Exception;
	public boolean checkUserExists(AddClassForm addClassFrom) throws Exception;
	public boolean delete(AddClassForm addClassFrom) throws Exception; 
	public List updateClass(AddClassForm From ) throws Exception;
	public boolean updateInfo(AddClassForm From) throws Exception;
	public List Class(AddClassForm From ) throws Exception;
	@SuppressWarnings("unchecked")
	public List findDepartment( ) throws Exception;
	
	@SuppressWarnings("unchecked")
	public List findSpeciality(String dep_code ) throws Exception;

	@SuppressWarnings("unchecked")
	public List findClass(String spec_code ) throws Exception ;
	@SuppressWarnings("unchecked")
	public List findSublib(String spec_code ) throws Exception ;
	//查找子库资源类型信息
	@SuppressWarnings("unchecked")
	public String findResType(String sublib_id ) throws Exception;
	//删除多条班级记录
	public boolean getDeleteMore(String str) throws Exception;
	public boolean checkform(AddClassForm addform) throws Exception;
}
