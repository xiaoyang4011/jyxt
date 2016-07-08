package crds.basis.classa.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.validator.Form;

import crds.basis.classa.dao.IDAOClass;
import crds.basis.classa.web.form.AddClassForm;
import crds.pub.util.JdbcTemplateExtend;


public class DAOClass implements IDAOClass {
	private JdbcTemplateExtend jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplateExtend jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List Classinfo() throws Exception {
		String sql = "select class_code,spec_code,class_name from class order by class_code";
		List list = jdbcTemplate.queryForList(sql);
		return list;

	}

	public boolean delete(AddClassForm addClassFrom) throws Exception {
		// TODO Auto-generated method stub
		String sql = "delete from class where class_code='"
				+ addClassFrom.getClass_code() + "'";
		int boo = jdbcTemplate.update(sql);
		sql = "delete from class where spec_code='"
				+ addClassFrom.getSpec_code() + "'";
		boo = jdbcTemplate.update(sql);
		sql = "delete from class where spec_code='"
				+ addClassFrom.getClass_name() + "'";
		boo = jdbcTemplate.update(sql);
		return true;
	}

	public boolean getaddClassForm(AddClassForm addClassFrom) throws Exception {
		// TODO Auto-generated method stub
		String sql = "insert into class (class_code,spec_code,class_name) values ('"
				+ addClassFrom.getClass_code()
				+ "','"
				+ addClassFrom.getSpec_code()
				+ "','"
				+ addClassFrom.getClass_name() + "')";

		int boo = jdbcTemplate.update(sql);

		return true;
	}

	public List queryTest() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List updateClass(AddClassForm Form) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select * from class where class_code='"
				+ Form.getClass_code() + "'";
		List list = jdbcTemplate.queryForList(sql);
		// System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@2"+list);
		return list;
	}

	public boolean updateinfo(AddClassForm Form) throws Exception {
		// TODO Auto-generated method stub
		String sql = "update class set class_name='"+Form.getClass_name()+"' where class_code='"+Form.getClass_code()+"'";
		int boo = jdbcTemplate.update(sql);
		if (boo == 1) {
			return true;
		} else {
			return false;
		}
	}

	public List ClassInfo() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List Class(AddClassForm Form) throws Exception {
		// TODO Auto-generated method stub
		String spec_name = Form.getQuery_spec_name();
		String spec = Form.getSpec();
		String sql;
		String sqlcount;
		boolean all = false;
		if (spec == null||spec=="0") {
			all = true;
		} else if (spec.equals("")||spec.equals("0")) {
			all = true;
		} else {
			spec_name = Form.getQuery_spec_name();
		}
		if (all == true) {
			sql = "select * from class,specialty where class.spec_code=specialty.spec_code order by class.class_code";
			sqlcount = "select count(*) from class,specialty where class.spec_code=specialty.spec_code";
		} else {
			sql = "select * from class,specialty where class.spec_code=specialty.spec_code and specialty.spec_code='"
					+ Form.getSpec() + "'";
			sqlcount = "select count(*) from class,specialty where class.spec_code=specialty.spec_code and specialty.spec_code='"
					+ Form.getSpec() + "'";
		}
		// List list=jdbcTemplate.queryForList(sql);
		Form.setRowsCount(jdbcTemplate.queryForInt(sqlcount));
		List list = jdbcTemplate.queryForListPage(sql, Form.getStartPosition(),Form.getRowsPerPage());
		return list;
	}

	// 查找所有的机构（系部）信息并返回
	@SuppressWarnings("unchecked")
	public List findDepartment() throws Exception {
		String sql = "select dep_code, dep_name from department";
		List list = jdbcTemplate.queryForList(sql);
		return list;
	}

	// 查找所选机构系部下的专业信息
	@SuppressWarnings("unchecked")
	public List findSpeciality(String dep_code) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql = new StringBuffer(
				"select spec_code,spec_name from specialty where dep_code='"
						+ dep_code + "'");
		List list = jdbcTemplate.queryForList(sql.toString());
		return list;

	}

	// 查找所选专业下的课程信息
	@SuppressWarnings("unchecked")
	public List findCourse(String spec_code) throws Exception {
		StringBuffer sql = new StringBuffer();
		String a = spec_code;
		sql = new StringBuffer(
				"select course_code,course_name from course where spec_code='"
						+ spec_code + "'");
		List list = jdbcTemplate.queryForList(sql.toString());
		return list;

	}

	// 查找所选专业下的子库信息
	@SuppressWarnings("unchecked")
	public List findSublib(String spec_code) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql = new StringBuffer(
				"select sublib_id,sublib_title from sublibrary where spec_code='"
						+ spec_code + "'");
		List list = jdbcTemplate.queryForList(sql.toString());
		return list;
	}

	// 查找子库资源类型信息
	@SuppressWarnings("unchecked")
	public String findResType(String sublib_id) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql = new StringBuffer(
				"select resource_type.restype_id,resource_type.file_type from sublibrary,resource_type where sublibrary.restype_id=resource_type.restype_id and sublib_id='"
						+ sublib_id + "'");
		List list = jdbcTemplate.queryForList(sql.toString());
		if (list.size() > 0) {
			HashMap<String, String> map = (HashMap<String, String>) list.get(0);
			String filetype = map.get("file_type");
			if (filetype != null) {
				return (filetype);
			} else {
				return "";
			}
		}
		return "";
	}

	
	//执行删除多条的记录
	public boolean getDeleteMore(String str) throws Exception {
		String getStr=""; 
		String[] s=str.split(",");
		 for(int i=0;i<s.length;i++){
			 getStr+="','"+s[i];
			 
		 }
		 getStr="'"+getStr+"'";
		 
		 getStr=getStr.substring(3);
		 
		 String deleteSql="delete from class where class_code in("+getStr+")";
		 System.out.println(deleteSql);
		 int DeleteMore=jdbcTemplate.update(deleteSql);
		
		 
		 if(DeleteMore==1){
			 System.out.print("true");
			 return true;
		 
		 }else{
			 System.out.print("false");
		    return false;
		 }
	}
	//检查唯一性
	public boolean checkform(AddClassForm addform) throws Exception {
		// TODO Auto-generated method stub
		StringBuffer sql=new StringBuffer(32);
		String class_code=addform.getClass_code();
		sql.append("select count(*) from class where class_code='"+class_code+"'");
		//List params = new ArrayList();
		//params.add(addform.getClass_code());
		
		int re=jdbcTemplate.queryForInt(sql.toString());	
//		System.out.println("测试"+re);
		if(re>0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
