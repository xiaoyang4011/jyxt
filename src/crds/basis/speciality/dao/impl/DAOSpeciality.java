package crds.basis.speciality.dao.impl;

import java.util.ArrayList;
import java.util.List;

import crds.basis.speciality.dao.IDAOSpeciality;
import crds.basis.speciality.web.form.FormSpeciality;
import crds.pub.util.JdbcTemplateExtend;

public class DAOSpeciality implements IDAOSpeciality {
	
	 JdbcTemplateExtend jdbcTemplate;

	 public void setJdbcTemplate(JdbcTemplateExtend jdbcTemplate) {
			this.jdbcTemplate = jdbcTemplate;
		}
	
	 //查询部门（系部）信息
    @SuppressWarnings("unchecked")
	public List  depart_query() throws Exception  {
		String sql="select dep_code, dep_name from department";		
		List list=jdbcTemplate.queryForList(sql);		
		return list;
	}
    
   //专业查询 
    @SuppressWarnings("unchecked")
	public List   speciality_query(FormSpeciality formspeciality) throws Exception  {
		    	 
		String dep_code = formspeciality.getDep_code();   //部门编号
		
		StringBuffer  countsql = new StringBuffer();
		StringBuffer  sql = new StringBuffer();
		
		 if(("0").equals(dep_code)){	
		     countsql = new StringBuffer("select count(*) from View_spec");
		     sql = new StringBuffer("select * from View_spec");		     
		     }
		 else
		 {
			 countsql = new StringBuffer("select count(*) from View_spec where dep_code='"+dep_code+"'");
			 sql = new StringBuffer("select * from View_spec where dep_code='"+dep_code+"'"); 
		 }		
		formspeciality.setRowsCount(jdbcTemplate.queryForInt(countsql.toString()));		  
		return  jdbcTemplate.queryForListPage(sql.toString(), formspeciality.getStartPosition(), formspeciality.getRowsPerPage());
    	
	}
    
    //查询 一条专业
    @SuppressWarnings("unchecked")
	public List  speciality_query1(FormSpeciality formspeciality) throws Exception  {
	    	 
		String spec_code = formspeciality.getSpec_code();   //专业编号			 
		StringBuffer  sql = new StringBuffer();	
		 sql = new StringBuffer("select * from specialty where spec_code='"+spec_code+"'"); 
		return jdbcTemplate.queryForList(sql.toString());    	
	}
    
  //专业全部查询 
    @SuppressWarnings("unchecked")
	public List  speciality_queryall(FormSpeciality formspeciality) throws Exception  {
    	StringBuffer  countsql = new StringBuffer();
		StringBuffer  sql = new StringBuffer();			 	 
		countsql = new StringBuffer("select count(*) from specialty");
		sql = new StringBuffer("select * from specialty");  
		formspeciality.setRowsCount(jdbcTemplate.queryForInt(countsql.toString()));		  
		return jdbcTemplate.queryForListPage(sql.toString(),  formspeciality.getStartPosition(), formspeciality.getRowsPerPage());
    }
    
    //查询专业编号是否存在 
      @SuppressWarnings("unchecked")
  	public int speciality_queryCount(FormSpeciality formspeciality) throws Exception  {    	  
    	int returnValue = -1;
  		List param = new ArrayList();
  		param.add(formspeciality.getSpec_code().trim());
  		returnValue = jdbcTemplate.queryForInt(" select count(*) from specialty a where a.spec_code= ? ",param.toArray());
  		return returnValue; 
      }
    
  //专业信息添加
    @SuppressWarnings("unchecked")
	public int  specialityadd(FormSpeciality formspeciality) throws Exception  {
    	String spec_code=formspeciality.getSpec_code();
    	String spec_name=formspeciality.getSpec_name();
    	String spec_state="1".equals(formspeciality.getSpec_state())?"打开":"关闭";
    	String spec_description=formspeciality.getSpec_description();
    	String dep_code=formspeciality.getDep_code();
    	String showtype=formspeciality.getShowtype();
    	StringBuffer  sql = new StringBuffer();
    	List params = new ArrayList();//参数
    	sql = new StringBuffer("insert into specialty(spec_code,spec_name,spec_state,spec_description,dep_code,showtype) values(?,?,?,?,?,?)"); 
    	params.add(spec_code.trim());
    	params.add(spec_name.trim());
    	params.add(spec_state);
    	params.add(spec_description.trim());
    	params.add(dep_code);
    	params.add(showtype);    	
    	return jdbcTemplate.insertToDB(sql.toString(), params.toArray());
    }
     
  //专业信息修改
    @SuppressWarnings("unchecked")
	public int  specialityupdatedo(FormSpeciality formspeciality) throws Exception  {
    	String spec_code=formspeciality.getSpec_code();
    	String spec_name=formspeciality.getSpec_name();
    	String showtype=formspeciality.getShowtype();
    	String spec_state=formspeciality.getSpec_state();
    	String dep_code=formspeciality.getDep_code();
    	String spec_description=formspeciality.getSpec_description();
    	StringBuffer  sql = new StringBuffer();
    	List params = new ArrayList();//参数
    	sql = new StringBuffer("update specialty set spec_name=?,showtype=?,spec_state=?,dep_code=?,spec_description=? where spec_code=?"); 
    	params.add(spec_name.trim());
    	params.add(showtype);
    	params.add(spec_state);
    	params.add(dep_code);
    	params.add(spec_description.trim());
    	params.add(spec_code.trim());
    	return jdbcTemplate.update(sql.toString(), params.toArray());
    }
    
    
  //查询该专业下是否有各种信息  
   @SuppressWarnings("unchecked")
  	public int speciality_SubInfo(FormSpeciality formspeciality) throws Exception  {    	  
    	int returnValue = -1;    	
  		//List param = new ArrayList();
  		//param.add(formspeciality.getSpec_code().trim());
    	String speccode=formspeciality.getSpec_code().trim();
  		int n1=0; n1= jdbcTemplate.queryForInt(" select count(*) from class a where a.spec_code='"+speccode+"'");
  		int n2=0;n2= jdbcTemplate.queryForInt(" select count(*) from course a where a.spec_code='"+speccode+"'");
  		//int n3=0;n3=jdbcTemplate.queryForInt(" select count(*) from resspec a where a.spec_code= ? ",param.toArray());
  		int n4=0;n4=jdbcTemplate.queryForInt(" select count(*) from specialty_head a where a.spec_code='"+speccode+"'");
   		int n5=0;n5 = jdbcTemplate.queryForInt(" select count(*) from sublibrary a where a.spec_code='"+speccode+"'");
  		int n6=0;n6 = jdbcTemplate.queryForInt(" select count(*) from theme_class a where a.spec_code='"+speccode+"'");
  		int n7=0;n7 = jdbcTemplate.queryForInt(" select count(*) from userinfo a where a.spec_code='"+speccode+"'");
  	    if(n1>0||n2>0||n4>0||n5>0||n6>0||n7>0) {returnValue=1;}
  		return returnValue; 
      }
   
  //专业信息删除
    @SuppressWarnings("unchecked")
	public int  specialitydelete(FormSpeciality formspeciality) throws Exception  {
    	String spec_code=formspeciality.getSpec_code().trim();//专业编号    	 
    	StringBuffer  sql = new StringBuffer();
    	//List params = new ArrayList();//参数
    	sql = new StringBuffer("delete  from specialty where spec_code='"+spec_code+"'");  
    	//params.add(spec_code.trim());
    	return jdbcTemplate.update(sql.toString());
    }
    
    
    //查询指定部门的专业 
    @SuppressWarnings("unchecked")
	public List   dep_spec_query(FormSpeciality formspeciality) throws Exception  {
		    	 
		String dep_code = formspeciality.getDep_code();   //部门编号		
		StringBuffer  countsql = new StringBuffer();
		StringBuffer  sql = new StringBuffer();
		countsql = new StringBuffer("select count(*) from specialty where dep_code='"+dep_code+"'");
		sql = new StringBuffer("select * from specialty where dep_code='"+dep_code+"'"); 				
		formspeciality.setRowsCount(jdbcTemplate.queryForInt(countsql.toString()));		  
		return  jdbcTemplate.queryForListPage(sql.toString(), formspeciality.getStartPosition(), formspeciality.getRowsPerPage());
    }
    
    }
    

