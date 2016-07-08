package crds.company.dao.impl;

import java.util.*;

import crds.company.dao.IDAOcy;
import crds.company.web.form.Formcy;

import crds.pub.util.JdbcTemplateExtend;

public  class DAOcy implements IDAOcy {
	// 重写的JdbcTemplateExtend
	private JdbcTemplateExtend jdbcTemplate;
	public void setJdbcTemplate(JdbcTemplateExtend jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}	

	public int Add_cy(Formcy form) throws Exception
	{	
		TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
		Calendar ca = Calendar.getInstance();
	    int year = ca.get(Calendar.YEAR);//获取年份
	    int month=ca.get(Calendar.MONTH);//获取月份
	    int day=ca.get(Calendar.DATE);//获取日
	    int hour=ca.get(Calendar.HOUR_OF_DAY);//小时
	    int minute=ca.get(Calendar.MINUTE);//分
	    int second=ca.get(Calendar.SECOND);//秒
	    String date = year+"-"+(month + 1 )+ "-" + day + "  "+ hour + "：" + minute + "：" + second;
		
		String id  = form.getCy_id();
		String name = form.getCy_name();
		String zhaosheng = form.getCy_zs();
		String jianjie = form.getCy_summary();
		String  lead = form.getCy_lead();
		String remark =form.getCy_remark();
		String sql = "insert into company(cy_id,cy_name,cy_lead,cy_zs,cy_summary,cy_date,cy_remark)values(?,?,?,?,?,?)";
  	    int list=jdbcTemplate.insertToDB(sql);		
	    return list;
	}

	@SuppressWarnings("unchecked")
	public List Bro_cy(Formcy form) throws Exception
	{
		StringBuffer  countsql = new StringBuffer();
		countsql=new StringBuffer("select count(*) from company");
		String sql="select * from company";	
		form.setRowsCount(jdbcTemplate.queryForInt(countsql.toString()));
		return jdbcTemplate.queryForListPage(sql.toString(),form.getStartPosition(),form.getRowsPerPage());	
		
		
	}


	public int Update_cy(Formcy form) throws Exception
	{
		
		TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
		Calendar ca = Calendar.getInstance();
	    int year = ca.get(Calendar.YEAR);//获取年份
	    int month=ca.get(Calendar.MONTH);//获取月份
	    int day=ca.get(Calendar.DATE);//获取日
	    int hour=ca.get(Calendar.HOUR_OF_DAY);//小时
	    int minute=ca.get(Calendar.MINUTE);//分
	    int second=ca.get(Calendar.SECOND);//秒
	    String date = year+"-"+(month + 1 )+ "-" + day + "  "+ hour + "：" + minute + "：" + second;
	    String id  = form.getCy_id();
		String name = form.getCy_name();
		String zhaosheng = form.getCy_zs();
		String jianjie = form.getCy_summary();
		String  lead = form.getCy_lead();
		String remark =form.getCy_remark();
		String sql="update news set ";
		int list =jdbcTemplate.insertToDB(sql);
		return list;	
	}

	@SuppressWarnings("unchecked")
	public List Jump_cy(Formcy form) throws Exception
	{
		String id = form.getCy_id();
		String sql="select * from company where cy_id='"+id+"'";	
		List list = jdbcTemplate.queryForList(sql);
		return list;
	}
	
	public int Del_cy(Formcy form) throws Exception
	{
		String id = form.getCy_id();
		String sql="delete from company where cy_id='"+id+"'";
		int list=jdbcTemplate.update(sql);
		return list;
		
	}


		
}