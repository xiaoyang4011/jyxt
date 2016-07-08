package crds.basis.classa.bo.impl;

import java.util.List;

import crds.basis.classa.bo.IBOClass;
import crds.basis.classa.dao.IDAOClass;
import crds.basis.classa.web.form.AddClassForm;


public class BOClass implements IBOClass{
	private IDAOClass dao;
	public void setDAOClass(IDAOClass dao) {
		this.dao = dao;
	}
	
	
	
	public List Classinfo() throws Exception {
		// TODO Auto-generated method stub
		return dao.Classinfo();
	}

	public boolean checkUserExists(AddClassForm addClassFrom)
			throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean delete(AddClassForm addClassFrom) throws Exception {
		// TODO Auto-generated method stub
		return this.dao.delete(addClassFrom);
	}

	public boolean getaddClassForm(AddClassForm addClassFrom)
			throws Exception {
		// TODO Auto-generated method stub
		return this.dao.getaddClassForm(addClassFrom);
	}

	public List queryTest() throws Exception {
		// TODO Auto-generated method stub
		return dao.queryTest();
	}



	public List updateClass(AddClassForm From) throws Exception {
		// TODO Auto-generated method stub
		return dao.updateClass(From);
	}



	public boolean updateInfo(AddClassForm From) throws Exception {
		// TODO Auto-generated method stub
		return dao.updateinfo(From);
	}



	public List Class(AddClassForm Form) throws Exception {
		// TODO Auto-generated method stub
		return dao.Class(Form);
	}
	//查找所有的机构系部信息
	@SuppressWarnings("unchecked")
	public List findDepartment( ) throws Exception {
		// TODO Auto-generated method stub
		return dao.findDepartment();
	
	}
	
	
	//查找所选机构系部下的专业信息
	@SuppressWarnings("unchecked")
	public List findSpeciality(String dep_code ) throws Exception {
		// TODO Auto-generated method stub
		return dao.findSpeciality(dep_code);
	
	}
	
	//查找所选专业下的课程信息
	@SuppressWarnings("unchecked")
	public List findCourse(String spec_code ) throws Exception {
		// TODO Auto-generated method stub
		return dao.findCourse(spec_code);
	
	}
	//查找所选专业下的子库信息
	@SuppressWarnings("unchecked")
	public List findSublib(String spec_code ) throws Exception {
		// TODO Auto-generated method stub
		return dao.findSublib(spec_code);
	
	}
	//查找子库资源类型信息
	@SuppressWarnings("unchecked")
	public String findResType(String sublib_id ) throws Exception{
		return dao.findResType(sublib_id);	
	}



	public List ClassInfo() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



	
	public List findClass(String specCode) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	//执行删除多条的记录

	public boolean getDeleteMore(String str) throws Exception {
		// TODO Auto-generated method stub
		return dao.getDeleteMore(str);
	}
	public boolean checkform(AddClassForm addform) throws Exception 
	{
		return(dao.checkform(addform));
	}
}
