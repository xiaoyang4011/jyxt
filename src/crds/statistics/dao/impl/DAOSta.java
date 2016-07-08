package crds.statistics.dao.impl;

import java.util.List;
import crds.pub.util.JdbcTemplateExtend;
import crds.statistics.dao.IDAOSta;
import crds.statistics.web.form.FormSta;

public class DAOSta implements IDAOSta{
	// 重写的JdbcTemplateExtend
	private JdbcTemplateExtend jdbcTemplate;
	public void setJdbcTemplate(JdbcTemplateExtend jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}	
	@SuppressWarnings("unchecked")
	public List cy_Bro(FormSta form) throws Exception
	{
		StringBuffer  countsql = new StringBuffer();
		countsql=new StringBuffer("select COUNT(*) from userinfo,company where userinfo.cy_id=company.cy_id");
		String sql="select cy_name,COUNT(*) num09,cast(COUNT(*)as float)/100*200as [jyl] from userinfo,company where userinfo.cy_id=company.cy_id GROUP BY cy_name";	
		form.setRowsCount(jdbcTemplate.queryForInt(countsql.toString()));
		return jdbcTemplate.queryForListPage(sql.toString(),form.getStartPosition(),form.getRowsPerPage());	
		
		
	}
	@SuppressWarnings("unchecked")
	public List cy_sepc(FormSta form) throws Exception
	{
		StringBuffer  countsql = new StringBuffer();
		countsql=new StringBuffer("select COUNT(*) from userinfo,company where userinfo.cy_id=company.cy_id");
		String sql="select dep_name,COUNT(*) num09,cast(COUNT(*)as float(1))/(select COUNT(*)from userinfo where user_type='学生')*100 as [jyl] from userinfo,department where userinfo.dep_code=department.dep_code and cy_id!=0 GROUP BY dep_name";	
		form.setRowsCount(jdbcTemplate.queryForInt(countsql.toString()));
		return jdbcTemplate.queryForListPage(sql.toString(),form.getStartPosition(),form.getRowsPerPage());	
		
		
	}
}
