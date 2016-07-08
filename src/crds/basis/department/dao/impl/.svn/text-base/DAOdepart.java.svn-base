package crds.basis.department.dao.impl;


import java.util.ArrayList;
import java.util.List;
import crds.basis.department.dao.IDAOdepart;
import crds.basis.department.web.FormDepart;
import crds.pub.util.JdbcTemplateExtend;

public class DAOdepart implements IDAOdepart{
	// JdbcTemplateExtend
	private JdbcTemplateExtend jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplateExtend jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	//初始化机构数据
	@SuppressWarnings("unchecked")
	public List  DepartmentInfo() throws Exception  {
		String sql="select dep_code,dep_name from department";		
		List list=jdbcTemplate.queryForList(sql);	
		return list;
	}
	public boolean addDepart(FormDepart form) throws Exception{
		String sql="insert into department values ('"+form.getDep_code()+"','"+form.getDep_name()+"')";		
		int dao=jdbcTemplate.update(sql);		
		if(dao!=0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	//判断重复
	
	public boolean checkExists(FormDepart form) throws Exception 
	{		
		
		String sql1="select count(*) from department where dep_code= '"+form.getDep_code()+"'";
		int re=jdbcTemplate.queryForInt(sql1.toString());	
		if(re>0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	@SuppressWarnings("unchecked")
	public List updateJump(FormDepart form)throws Exception{
		String id = form.getDep_code();
		String sql="select * from department where dep_code='"+id+"'";	
		List list = jdbcTemplate.queryForList(sql);
		return list;
	}
	//修改机构数据
	public boolean updatedepart(FormDepart form) throws Exception{
		String sql="update department set dep_name='"+form.getDep_name()+"' where dep_code='"+form.getDep_code()+"'";		
		int dao=jdbcTemplate.update(sql);		
		if(dao!=0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	//删除机构
	@SuppressWarnings("unchecked")
	public boolean delete(FormDepart form) throws Exception 
	{		
		String sql="select * from specialty where dep_code='"+form.getDep_code()+"'";
		List list=jdbcTemplate.queryForList(sql);
		if(list.size()==0){
			sql="delete from department where dep_code='"+form.getDep_code()+"'";		
			int boo=jdbcTemplate.update(sql);		
			return true;
			
		}else{
			return false;
		}
	}
}
