package crds.stud.dao.impl;
import java.util.List;

import crds.stud.dao.IDAOStud;
import crds.stud.web.form.FormStud;

import crds.pub.util.JdbcTemplateExtend;


public class DAOStud implements IDAOStud {
	// 重写的JdbcTemplateExtend
	private JdbcTemplateExtend jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplateExtend jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	public boolean Add_stud(FormStud form) throws Exception{
	
		String sql="insert ";
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
	@SuppressWarnings("unchecked")
	public List Bro_stud(FormStud form) throws Exception
	{
		StringBuffer  countsql = new StringBuffer();
		countsql=new StringBuffer("select COUNT(*) from student");
		String sql="select * from student";	
		form.setRowsCount(jdbcTemplate.queryForInt(countsql.toString()));
		return jdbcTemplate.queryForListPage(sql.toString(),form.getStartPosition(),form.getRowsPerPage());	
		
	}

}
