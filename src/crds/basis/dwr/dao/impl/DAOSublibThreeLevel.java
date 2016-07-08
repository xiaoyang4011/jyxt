package crds.basis.dwr.dao.impl;

import java.util.Iterator;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import crds.basis.dwr.dao.IDAOSublibThreeLevel;
import crds.pub.util.JdbcTemplateExtend;

public class DAOSublibThreeLevel implements IDAOSublibThreeLevel {

	 JdbcTemplateExtend jdbcTemplate;

	 public void setJdbcTemplate(JdbcTemplateExtend jdbcTemplate) {
			this.jdbcTemplate = jdbcTemplate;
		}
	//查找所有的机构（系部）信息并返回
    @SuppressWarnings("unchecked")
	public List  findDepartment() throws Exception  {
		String sql="select dep_code, dep_name from department";		
		List list=jdbcTemplate.queryForList(sql);		
		return list;
	}
    
  //查找所选机构系部下的专业信息
	@SuppressWarnings("unchecked")
	public List findSpeciality(String dep_code ) throws Exception{
		StringBuffer  sql = new StringBuffer();
		sql = new StringBuffer("select spec_code,spec_name from specialty where dep_code='"+dep_code+"'"); 	
		List list=jdbcTemplate.queryForList(sql.toString());		
		return list;
		
	}
}