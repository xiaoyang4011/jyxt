package crds.zhaosheng.dao.impl;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import crds.pub.util.JdbcTemplateExtend;
import crds.zhaosheng.dao.IDAOzs;
import crds.zhaosheng.web.form.FormZs;

public class DAOzs implements IDAOzs{
	private JdbcTemplateExtend jdbcTemplate;
	public void setJdbcTemplate(JdbcTemplateExtend jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	@SuppressWarnings("unchecked")
	public List Bro_stud(FormZs form) throws Exception
	{
		StringBuffer  countsql = new StringBuffer();
		countsql=new StringBuffer("select count(*) from user_role left join userinfo ON user_role.user_ID = userinfo.user_ID left join class ON userinfo.class_code = class.class_code left join department ON userinfo.dep_code = department.dep_code left join specialty ON userinfo.spec_code = specialty.spec_code left join role ON dbo.user_role.role_id = role.role_id");
		String sql="SELECT DISTINCT userinfo.user_ID,userinfo.real_name,specialty.spec_name,department.dep_name,class.class_name,userinfo.user_type,case user_state when 0 then '已审核' when 1 then '停用' when 2 then '待审核' end as user_states,case when cy_name is NULL then '未就业' else  company.cy_name end as cy_name from user_role left join userinfo ON user_role.user_ID = userinfo.user_ID left join class ON userinfo.class_code = class.class_code left join department ON userinfo.dep_code = department.dep_code left join specialty ON userinfo.spec_code = specialty.spec_code left join role ON dbo.user_role.role_id = role.role_id left join company ON userinfo.cy_id = company.cy_id";	
		form.setRowsCount(jdbcTemplate.queryForInt(countsql.toString()));
		return jdbcTemplate.queryForListPage(sql.toString(),form.getStartPosition(),form.getRowsPerPage());	
		
	}
	@SuppressWarnings("unchecked")
	public List Bro_cy(FormZs form) throws Exception
	{
		StringBuffer  countsql = new StringBuffer();
		countsql=new StringBuffer("select count(*) from company");
		String sql="select * from company";	
		form.setRowsCount(jdbcTemplate.queryForInt(countsql.toString()));
		return jdbcTemplate.queryForListPage(sql.toString(),form.getStartPosition(),form.getRowsPerPage());	
		
		
	}
	public int Update_jiuye(FormZs form) throws Exception
	{
		FormZs formzs=(FormZs)form;
		TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
		Calendar ca = Calendar.getInstance();
	    int year = ca.get(Calendar.YEAR);//获取年份
	    int month=ca.get(Calendar.MONTH);//获取月份
	    int day=ca.get(Calendar.DATE);//获取日
	    int hour=ca.get(Calendar.HOUR_OF_DAY);//小时
	    int minute=ca.get(Calendar.MINUTE);//分
	    int second=ca.get(Calendar.SECOND);//秒
	    String date = year+"-"+(month + 1 )+ "-" + day + "  "+ hour + "：" + minute + "：" + second;
	    String cy_id = formzs.getCy_id();
	    String user_id = formzs.getUser_ID();
		String sql="update userinfo set cy_id = "+cy_id+",jy_date='"+date+"' where user_ID ='"+user_id+"'";
		int list =jdbcTemplate.insertToDB(sql);
		return list;	
	}
	public int del_jiuye(FormZs form) throws Exception
	{
		FormZs formzs=(FormZs)form;
	    String user_id = formzs.getUser_ID();
		String sql="update userinfo set cy_id = '',jy_date='' where user_ID ='"+user_id+"'";
		int list =jdbcTemplate.insertToDB(sql);
		return list;	
	}
	
}
