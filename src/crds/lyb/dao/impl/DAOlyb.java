package crds.lyb.dao.impl;

import java.util.*;

import crds.lyb.dao.IDAOlyb;
import crds.lyb.web.form.Formlyb;

import crds.pub.util.JdbcTemplateExtend;

public  class DAOlyb implements IDAOlyb {
	// 重写的JdbcTemplateExtend
	private JdbcTemplateExtend jdbcTemplate;
	public void setJdbcTemplate(JdbcTemplateExtend jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}	

	public int Add(Formlyb form) throws Exception
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
		
		String t  = form.getNew_title();
		String n = form.getNews();
		String a = form.getNew_author();
		String p = form.getNew_type();
		String sql = "insert into news (new_title,new_date,new_author,news,new_type) values ('"+t+"','"+date+"','"+a+"','"+n+"','"+p+"')";
  	    int list=jdbcTemplate.insertToDB(sql);		
	    return list;
	}
	@SuppressWarnings("unchecked")
	public List Bro(Formlyb form) throws Exception
	{
		StringBuffer  countsql = new StringBuffer();
		countsql=new StringBuffer("select count(*) from news");
		String sql="select * from news";	
		form.setRowsCount(jdbcTemplate.queryForInt(countsql.toString()));
		return jdbcTemplate.queryForListPage(sql.toString(),form.getStartPosition(),form.getRowsPerPage());	
		
		
	}
	

	public int Update(Formlyb form) throws Exception
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
	    String id = form.getNew_id();
	    String t  = form.getNew_title();
	    String n = form.getNews();
	    String a = form.getNew_author();
		String sql="update news set new_title='"+t+"',new_date='"+date+"',new_author='"+a+"',news='"+n+"' where new_id='"+id+"'";
		int list =jdbcTemplate.insertToDB(sql);
		return list;	
	}
	@SuppressWarnings("unchecked")
	public List Jump(Formlyb form) throws Exception
	{
		String id = form.getNew_id();
		String sql="select * from news where new_id='"+id+"'";	
		List list = jdbcTemplate.queryForList(sql);
		return list;
	}
	public int Del(Formlyb form) throws Exception
	{
		String id = form.getNew_id();
		String sql="delete from news where new_id='"+id+"'";
		int list=jdbcTemplate.update(sql);
		return list;
		
	}
//前台条件查询
	@SuppressWarnings("unchecked")
	public List Bro1(Formlyb form) throws Exception
	{
		String id =form.getNew_id();
		String sql="select * from news where new_id='"+id+"'";	
		return jdbcTemplate.queryForList(sql.toString());
		
	
		
}
	@SuppressWarnings("unchecked")
	public List zxdt(Formlyb form) throws Exception
	{
		StringBuffer  countsql = new StringBuffer();
		countsql=new StringBuffer("select count(*) from news where new_type=1");
		String sql="select * from news where new_type=1";	
		form.setRowsCount(jdbcTemplate.queryForInt(countsql.toString()));
		return jdbcTemplate.queryForListPage(sql.toString(),form.getStartPosition(),form.getRowsPerPage());	
		
		
	}
	@SuppressWarnings("unchecked")
	public List xnzph(Formlyb form) throws Exception
	{
		StringBuffer  countsql = new StringBuffer();
		countsql=new StringBuffer("select count(*) from news where new_type=2");
		String sql="select * from news where new_type=2";	
		form.setRowsCount(jdbcTemplate.queryForInt(countsql.toString()));
		return jdbcTemplate.queryForListPage(sql.toString(),form.getStartPosition(),form.getRowsPerPage());	
	}
	@SuppressWarnings("unchecked")
	public List xwzph(Formlyb form) throws Exception
	{
		StringBuffer  countsql = new StringBuffer();
		countsql=new StringBuffer("select count(*) from news where new_type=3");
		String sql="select * from news where new_type=3";	
		form.setRowsCount(jdbcTemplate.queryForInt(countsql.toString()));
		return jdbcTemplate.queryForListPage(sql.toString(),form.getStartPosition(),form.getRowsPerPage());	
	}
	@SuppressWarnings("unchecked")
	public List jyzc(Formlyb form) throws Exception
	{
		StringBuffer  countsql = new StringBuffer();
		countsql=new StringBuffer("select count(*) from news where new_type=4");
		String sql="select * from news where new_type=4";	
		form.setRowsCount(jdbcTemplate.queryForInt(countsql.toString()));
		return jdbcTemplate.queryForListPage(sql.toString(),form.getStartPosition(),form.getRowsPerPage());	
	}
	@SuppressWarnings("unchecked")
	public List jyzd(Formlyb form) throws Exception
	{
		StringBuffer  countsql = new StringBuffer();
		countsql=new StringBuffer("select count(*) from news where new_type=5");
		String sql="select * from news where new_type=5";	
		form.setRowsCount(jdbcTemplate.queryForInt(countsql.toString()));
		return jdbcTemplate.queryForListPage(sql.toString(),form.getStartPosition(),form.getRowsPerPage());	
	}
}