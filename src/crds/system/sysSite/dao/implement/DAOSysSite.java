package crds.system.sysSite.dao.implement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import crds.pub.util.JdbcTemplateExtend;
import crds.system.sysSite.dao.IDAOSysSite;
import crds.system.sysSite.web.form.BaseParasForm;
import crds.system.sysSite.web.form.PointRulerForm;
import crds.system.sysSite.web.form.ResourceTypeForm;

public class DAOSysSite implements IDAOSysSite {
	// 重写的JdbcTemplateExtend
	private JdbcTemplateExtend jdbcTemplate;
	public JdbcTemplateExtend getJdbcTemplate() {
		return jdbcTemplate;
	}
	public void setJdbcTemplate(JdbcTemplateExtend jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	public List getList4SC() throws Exception
	{
		String sql=new String("select * from siteconfige");	
		return jdbcTemplate.queryForList(sql);
	}
	public List getList4PR() throws Exception
	{
		String sql=new String("select * from point_ruler");	
		return jdbcTemplate.queryForList(sql);
	}
	public Map<String, Object> getMap4SC() throws Exception
	{
		String sql=new String("select * from siteconfige");	
		return jdbcTemplate.queryForMap(sql);
	}
	public Map<String,Object>  getMap4PR() throws Exception
	{
		String sql=new String("select * from point_ruler");	
		return jdbcTemplate.queryForMap(sql);
	}
	public boolean updateConfige(String sql,List list) throws Exception
	{
		int retFac=jdbcTemplate.update(sql,list.toArray());	
		if(retFac>0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public boolean insertConfige(String sql,List list) throws Exception
	{
		int retFac=jdbcTemplate.insertToDB(sql,list.toArray());	
		if(retFac>0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public List getList4RT() throws Exception
	{
		String sql=new String("select restype_name,file_type,attachment_type from resource_type");	
		return jdbcTemplate.queryForList(sql);
	}
	public boolean updateResourceType(ArrayList<String> list) throws Exception
	{
	boolean flag=true;
	String sql=null;
	for (int k=0;k<list.size();k++)
	{
		sql=new String(list.get(k).toString());
		try{
		jdbcTemplate.update(sql);
		}catch (Exception e)
		{
			 flag=false;
		}
	}
	return flag;	
	}
	  //获取当前合法IP段址的列表 
	public List getList4IPSections() throws Exception
	{
		String sql="select ipsection_id,from_ip,to_ip from ipsection";		
		List list=jdbcTemplate.queryForList(sql);		
		return list;	
	}
	//将IPSectionForm中的IP段址追加到数据库
	public boolean insertIPIntoTable(String sql4From,String sql4To) throws Exception
	{
		String sql4append=new String("insert into ipsection(from_ip,to_ip) values ('"+sql4From+"','"+sql4To+"')");
		int retFac=jdbcTemplate.insertToDB(sql4append);
		if(retFac>0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public int del_SelectIP(String delIPID) throws Exception
	{
		String sqlState="delete from ipsection where ipsection_id="+delIPID;
		return jdbcTemplate.update(sqlState);
	}
	//get IP contents from db
	public String get_CurIPRange(String fromIP,String toIP) throws Exception
	{
		String res=new String("");
//		String sqlState="select spec_name from specialty where spec_code='"+queryKey+"'";
//		Map <Object,String> map=jdbcTemplate.queryForMap(sqlState);
//		Set keySet=map.keySet();
//		Iterator it=keySet.iterator();
//		while (it.hasNext())
//		{
//			Object key=it.next();
//			res+=map.get(key).toString();
//		}
	    return res;
	}
	//修改IP段数据
	public boolean updateIPSection(String sql,List list) throws Exception
	{
		int retFac=jdbcTemplate.update(sql,list.toArray());
		if(retFac>0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
