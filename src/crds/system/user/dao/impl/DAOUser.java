/**
 * product name :CRDS
 */
package crds.system.user.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import java.util.List;


import crds.system.user.dao.IDAOUser;

import crds.system.user.web.form.AddRole;
import crds.system.user.web.form.AddUser;
import crds.system.user.web.form.DClass;
import crds.system.user.web.form.Department;
import crds.system.user.web.form.FormUser;
import crds.system.user.web.form.PLAddUserPoint;
import crds.system.user.web.form.PLUser;
import crds.system.user.web.form.Role;
import crds.system.user.web.form.Specialty;
import crds.upload.web.form.UploadForm;
import crds.pub.util.JdbcTemplateExtend;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;

/**
 * @specification :登录操作DAO实现
 * @version : 1.0
 * @author : maogf
 * @date : Apr 29, 2009 3:14:17 PM
 * @email : maogenfeng@gmail.com
 */
public class DAOUser implements IDAOUser {
	// 重写的JdbcTemplateExtend
	private JdbcTemplateExtend jdbcTemplate;
    
	public void setJdbcTemplate(JdbcTemplateExtend jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}	
	/**  查询用户*/
	@SuppressWarnings("unchecked")
	public List queryUser(FormUser formuser) throws Exception 
	{		
		String sql="select user_id, dep_code,spec_code,class_code,password,real_name,user_type,member_type,user_point from [user]";		
		List list=jdbcTemplate.queryForList(sql);		
		return list;
	}
	/**  增加用户*/
	@SuppressWarnings("unchecked")
	public boolean addUser(FormUser formuser ) throws Exception{
		StringBuffer sql=new StringBuffer(32);
		sql.append("insert into [user](user_id,user_name,real_name,password) values(?,?,?,?)");
		List params = new ArrayList();
		params.add(formuser.getUser_id());
		params.add(formuser.getUser_name());
		params.add(formuser.getReal_name());
		params.add("123");
		int re=jdbcTemplate.insertToDB(sql.toString(),params.toArray());	
		if(re>0)
		{
			return true;
		}
		else
		{
			return false;
		}		
	}
	/**  修改用户*/
	@SuppressWarnings("unchecked")
	public boolean updateUser(FormUser formuser ) throws Exception{
		return false;
	}
	/**  删除用户*/
	@SuppressWarnings("unchecked")
	public boolean delUser( ) throws Exception{
		return true;
	}
	/**  判断用户是否存在*/
	@SuppressWarnings("unchecked")
	public boolean checkUserExists(FormUser formuser) throws Exception 
	{		
		
		StringBuffer sql=new StringBuffer(32);
		sql.append("select count(*) from [user] where user_id= ?  or user_name= ? ");
		List params = new ArrayList();
		params.add(formuser.getUser_id());
		params.add(formuser.getUser_name());
		int re=jdbcTemplate.queryForInt(sql.toString(),params.toArray());	
		if(re>0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	/**  查询院系*/
	@SuppressWarnings("unchecked")
	public List findAllDepts() throws Exception
	{
	String sql="select dep_code,dep_name from department";		
	List list=jdbcTemplate.queryForList(sql);		
	return list;
	}
	
	/**
	 *以下为用户管理代码 
	 * */
	public List queryTest() throws Exception {
		return null;
	}
   
	
	public List userInfo(AddUser adduser) throws Exception {	
		 StringBuffer  countsql = new StringBuffer();
		    countsql = new StringBuffer("select count(*) from userT");
			
			String studentSql="select * from userT";	
			
			adduser.setRowsCount(jdbcTemplate.queryForInt(countsql.toString()));		  
			return jdbcTemplate.queryForListPage(studentSql.toString(),  adduser.getStartPosition(), adduser.getRowsPerPage());
	}
    //添加信息
	public boolean getAddUserInfo(AddUser adduser) throws Exception {
		
		String userid= adduser.getUser_ID();
		String depcode=adduser.getDept();
		String speccode=adduser.getSpec();
		String classcode=adduser.getClass_code();
		String ps=adduser.getPassword();
		String realname=adduser.getReal_name();
		String usertype=adduser.getUser_type();
		String[] roleid=adduser.getRoleList();
		String addUserSql="insert into userinfo (user_ID,dep_code,spec_code,class_code,user_name,password,real_name,user_type,member_type,user_point,user_state) values ('"+userid+"','"+depcode+"','"+speccode+"','"+classcode+"','"+userid+"','"+ps+"','"+realname+"','"+usertype+"','0','0','2')";
		int boo1=jdbcTemplate.update(addUserSql);
		for(int i=0;i<roleid.length;i++){
			//添加到用户——角色表
			String addusertoleSql="insert into user_role (user_ID,role_id) values ('"+userid+"','"+roleid[i]+"')";
			  jdbcTemplate.update(addusertoleSql);
			}
		boolean temp=false;
	       // System.out.print(boo1+"   "+boo2);
			if(boo1==1){
				temp=true;
			}
			else{
				temp=false;
			}

			return temp;	
	}


    //删除用户
	public int getDeleteUserRes(String userid) throws Exception {
		String deleteSql="delete userinfo where USER_ID='"+userid+"'";
		String deleteSql2="delete user_role where USER_ID='"+userid+"'";
		int Delete=jdbcTemplate.update(deleteSql);
		int Delete2=jdbcTemplate.update(deleteSql2);
		Delete=Delete+Delete2;
		return Delete;
	}
	 //根据条件查询用户信息
	public List getSelectUserinfo(AddUser adduser) throws Exception {
	
		List list;
		//系部
		String depcode=adduser.getDept();
		//专业
		String speccode=adduser.getSpec();
		//班级
		String classcode=adduser.getClass_code();
		//角色
		String roleid=adduser.getRole_id();
		//状态
		String userstate=adduser.getUser_state();
		
		String role_name=adduser.getUser_ID();
		String sql;
		String sqlcount;
		
		boolean all=false;
		if (role_name==""||role_name=="0")
		{
			all=true;
			
		}
		else if(role_name.equals("")||role_name.equals("0"))
		{
			all=true;
			
		}
		else
		{
			role_name=adduser.getUser_ID();
		}
		if (all==true)
		{
              
			sql="SELECT DISTINCT userinfo.user_ID,specialty.spec_name,department.dep_name,class.class_name,userinfo.real_name,userinfo.user_type,userinfo.user_point,case user_state when 0 then '已审核' when 1 then '停用' when 2 then '待审核' end as user_states,case member_type when 2 then 'lxy' when 3 then 'lxy' when 4 then 'lxy' end as member_types from user_role left join userinfo ON user_role.user_ID = userinfo.user_ID left join class ON userinfo.class_code = class.class_code left join department ON userinfo.dep_code = department.dep_code left join specialty ON userinfo.spec_code = specialty.spec_code left join role ON dbo.user_role.role_id = role.role_id";
            sqlcount="select count(*) from user_role left join userinfo ON user_role.user_ID = userinfo.user_ID left join class ON userinfo.class_code = class.class_code left join department ON userinfo.dep_code = department.dep_code left join specialty ON userinfo.spec_code = specialty.spec_code left join role ON dbo.user_role.role_id = role.role_id";
            
             //System.out.println("第一次"+sql.length());
            ///////////////////////////////////////////
			if(classcode!=null){
			   if(classcode.length()>=2){
				   sql+=" where userinfo.class_code='"+classcode+"'";
				   sqlcount+=" where userinfo.class_code='"+classcode+"'";
				   if(roleid.length()>=2){
					   sql+=" and user_role.role_id='"+roleid+"'";
					   sqlcount+=" and user_role.role_id='"+roleid+"'";
				   }
			    }else if(speccode.length()>=2){
				   sql+=" where userinfo.spec_code='"+speccode+"'";
				   sqlcount+=" where userinfo.spec_code='"+speccode+"'";
				   if(roleid.length()>=2){
					   sql+=" and user_role.role_id='"+roleid+"'";
					   sqlcount+=" and user_role.role_id='"+roleid+"'";
				   }
			    }else if(depcode.length()>=2){
				   sql+=" where userinfo.dep_code='"+depcode+"'";
				   sqlcount+=" where userinfo.dep_code='"+depcode+"'";
				   if(roleid.length()>=2){
					   sql+=" and user_role.role_id='"+roleid+"'";
					   sqlcount+=" and user_role.role_id='"+roleid+"'";
				   }
			    }
			}
			//System.out.println("!!!!!!!!!!"+sql.length());
			//状态
			if(userstate!=null){
				if(userstate!=""){
					if(sql.length()>645){
						sql+=" and userinfo.user_state='"+userstate+"'";
						sqlcount+=" and userinfo.user_state='"+userstate+"'";
					}else{
						sql+=" where userinfo.user_state='"+userstate+"'";
						sqlcount+=" where userinfo.user_state='"+userstate+"'";
					}
				}
			}
			//角色
			if(roleid!=null){
				if(roleid!=""){
					if(sql.length()>645){
						sql+=" and user_role.role_id='"+roleid+"'";
						sqlcount+=" and user_role.role_id='"+roleid+"'";
					}else{
						sql+=" where user_role.role_id='"+roleid+"'";
						sqlcount+=" where user_role.role_id='"+roleid+"'";
					}
				}
			}
			
			////
		}
		else
		{
			//////////////////////////////////////////////

			//sql="select *,case user_state when 0 then '已审核' when 1 then '停用' when 2 then '待审核' end as user_states,case member_type when 2 then '普通会员' when 3 then '高级会员' when 4 then 'VIP会员' end as member_types  from userT  where user_ID='"+role_name+"'";
			//sqlcount="select count(*) from userT where user_ID='"+role_name+"'";
			//////////////////////////////////////////////////
			sql="SELECT DISTINCT userinfo.user_ID,specialty.spec_name,department.dep_name,class.class_name,userinfo.real_name,userinfo.user_type,userinfo.user_point,case user_state when 0 then '已审核' when 1 then '停用' when 2 then '待审核' end as user_states,case member_type when 2 then '普通会员' when 3 then '高级会员' when 4 then 'VIP会员' end as member_types from user_role left join userinfo ON user_role.user_ID = userinfo.user_ID left join class ON userinfo.class_code = class.class_code left join department ON userinfo.dep_code = department.dep_code left join specialty ON userinfo.spec_code = specialty.spec_code left join role ON dbo.user_role.role_id = role.role_id where userinfo.user_ID='"+role_name+"'";
			sqlcount="select count(*) from user_role left join userinfo ON user_role.user_ID = userinfo.user_ID left join class ON userinfo.class_code = class.class_code left join department ON userinfo.dep_code = department.dep_code left join specialty ON userinfo.spec_code = specialty.spec_code left join role ON dbo.user_role.role_id = role.role_id where userinfo.user_ID='"+role_name+"'";
			//////////////////////////////////////////////
             
			if(classcode!=null){
				if(classcode.length()>=2){
				sql+=" and userinfo.class_code='"+classcode+"'";
				sqlcount+=" and userinfo.class_code='"+classcode+"'";
				 if(roleid!=null){             // if(roleid.length()>=2){
					   sql+=" and user_role.role_id='"+roleid+"'";
					   sqlcount+=" and user_role.role_id='"+roleid+"'";
				   }
				}
			}else if(speccode!=null){
				if(speccode.length()>=2){
				sql+=" and userinfo.spec_code='"+speccode+"'";
				sqlcount+=" and userinfo.spec_code='"+speccode+"'";
				 if(roleid!=null){
					   sql+=" and user_role.role_id='"+roleid+"'";
					   sqlcount+=" and user_role.role_id='"+roleid+"'";
				   }
				}
			}else if(depcode!=null){
				if(depcode.length()>=2){
				sql+=" and userinfo.dep_code='"+depcode+"'";
				sqlcount+=" and userinfo.dep_code='"+depcode+"'";
				 if(roleid!=null){
					   sql+=" and user_role.role_id='"+roleid+"'";
					   sqlcount+=" and user_role.role_id='"+roleid+"'";
				   }
				}
			}
			//状态
			if(userstate!=null){
				if(userstate!=""){
					if(sql.length()>645){
						sql+=" and userinfo.user_state='"+userstate+"'";
						sqlcount+=" and userinfo.user_state='"+userstate+"'";
					}else{
						sql+=" where userinfo.user_state='"+userstate+"'";
						sqlcount+=" where userinfo.user_state='"+userstate+"'";
					}
				}
			}
			//角色
			if(roleid!=null){
				if(roleid!=""){
					if(sql.length()>645){
						sql+=" and user_role.role_id='"+roleid+"'";
						sqlcount+=" and user_role.role_id='"+roleid+"'";
					}else{
						sql+=" where user_role.role_id='"+roleid+"'";
						sqlcount+=" where user_role.role_id='"+roleid+"'";
					}
				}
			}
			/////
		}
		
		  //System.out.println(sqlcount);
		 String  begin="SELECT *FROM(";
          String end=")A OUTER APPLY(SELECT [role_name]= STUFF(REPLACE(REPLACE((SELECT role_name FROM role,user_role WHERE user_role.user_ID=A.user_ID and role.role_id=user_role.role_id FOR XML AUTO), '<role role_name=\"', ','), '\"/>', ''), 1, 1, ''))M";
   
		
		 sql=begin+sql+end;
        // sqlcount=begin+sqlcount+end;
         //System.out.println(sql);
		adduser.setRowsCount(jdbcTemplate.queryForInt(sqlcount));	
		
		List list1=jdbcTemplate.queryForListPage(sql, adduser.getStartPosition(), adduser.getRowsPerPage());
//		try{
//		 list=convertList(list1);
//		}catch(Exception e){
//			list=list1;
//		}
		return list1;
	}
	//去掉list重复用户，进行角色合并
	public List convertList(List list1) throws Exception
	{	
		String preUserid="";
		Map premap=null;
		Iterator it1= list1.iterator();
		
		while (it1.hasNext()) {	
			Map map1=(Map) it1.next();
			String userid = (String)map1.get("user_ID");
			//是重复
			if(userid.equals(preUserid))
			{
				String rolename = (String)map1.get("role_name");
				String prerolename = (String)premap.get("role_name");
				premap.put("role_name", prerolename+","+rolename);
				it1.remove();
			}
			preUserid=userid;
			premap=map1;
		}
		return list1;	
	}
   //根据传入的ID号查看数据库中是否已经存在该ID号
	
	public List getCheckID(String id) throws Exception {
		String selectID="select user_ID from userinfo where user_ID='"+id+"'";
		List selectid=jdbcTemplate.queryForList(selectID);	
        
		return selectid;
	}
   
  //执行数据的修改
	public int getUpdateInfo(AddUser user) throws Exception {
		
		int res=0;
		String userid= user.getUser_ID();
		String depcode=user.getDept();
		String speccode=user.getSpec();
		String classcode=user.getClass_code();
		
		String ps=user.getPassword();
		String realname=user.getReal_name();
		String usertype=user.getUser_type();
		int membertype=user.getMember_type();
		int userpoint=user.getUser_point();
		String[] roleid=user.getRoleList();
		String FristRoleid=roleid[0];
      //  System.out.print("第一个角色修改："+roleid[0]);
       //修改userinfo的表的内容
		String Update="update userinfo set dep_code='"+depcode+"',role_id='"+FristRoleid+"',spec_code='"+speccode+"',class_code='"+classcode+"',password='"+ps+"',real_name='"+realname+"',user_type='"+usertype+"',member_type="+membertype+",user_point="+userpoint+" where user_ID='"+userid+"'";
		int updateResult=jdbcTemplate.update(Update);
	   //删除对应userid的user_role的内容
		jdbcTemplate.update("delete user_role where user_ID='"+userid+"'");
		//将对应的用户角色添加到user_role表中
		for(int i=0;i<roleid.length;i++){
			
			String addusertoleSql="insert into user_role (user_ID,role_id) values ('"+userid+"','"+roleid[i]+"')";
			  jdbcTemplate.update(addusertoleSql);
			}
		return updateResult;
		//return 1;
	}
	//查询班级信息，为用户添加提供数据

	public List getClassInfo() throws Exception {
		String selectClass="select class_code,class_name from class";
		List list=jdbcTemplate.queryForList(selectClass);	
		return list;
	}
	//first下拉列表查出机构信息

	public List getJiGouInfo() throws Exception {
		String jigouinfo="select dep_code,dep_name from department";
		final List<Department> studentList = new ArrayList<Department>();
		jdbcTemplate.query(jigouinfo, new RowCallbackHandler() {

		
			public void processRow(ResultSet rs) throws SQLException {
				Department user = new Department();
				user.setDep_code(rs.getString("dep_code"));
				user.setDep_name(rs.getString("dep_name"));
				studentList.add(user);
			}
		});
		
		return studentList;
		
		
	}
	//第二次下拉列表的查询专业查询

	public List getZhuanye(String id) throws Exception {
		String selectID="select spec_code,spec_name from specialty where dep_code='"+id+"'";
        final List<Specialty> studentList = new ArrayList<Specialty>();
		
		jdbcTemplate.query(selectID, new RowCallbackHandler() {

		
			public void processRow(ResultSet rs) throws SQLException {
				Specialty user = new Specialty();
				user.setSpec_code(rs.getString("spec_code"));
				user.setSpec_name(rs.getString("spec_name"));
				studentList.add(user);
			}
		});
		
		return studentList;
	}
	//第三次查询下拉列表查询班级

	public List getBanJi(String id) throws Exception {
		String selectID="select class_code,class_name from class where spec_code='"+id+"'";
      final List<DClass> studentList = new ArrayList<DClass>();
		
		jdbcTemplate.query(selectID, new RowCallbackHandler() {

		
			public void processRow(ResultSet rs) throws SQLException {
				DClass user = new DClass();
				user.setClass_code(rs.getString("class_code"));
		
				user.setClass_name(rs.getString("class_name"));
				
				studentList.add(user);
			}
		});
		
		return studentList;
	}
	//查询用户角色的信息！

	public List getRoleInfo() throws Exception {
		String selectID="select role_id,role_name from role";
	      final List<Role> roleList = new ArrayList<Role>();
			
			jdbcTemplate.query(selectID, new RowCallbackHandler() {

			
				public void processRow(ResultSet rs) throws SQLException {
					Role role = new Role();
					role.setRole_id(rs.getString("role_id"));
					
					role.setRole_name(rs.getString("role_name"));
					
					roleList.add(role);
				}
			});
			
			return roleList;
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
		 
		 String deleteSql="delete from UserInfo where User_ID in("+getStr+")";
		 String deleteSql2="delete from user_role where User_ID in("+getStr+")";
		 int DeleteMore=jdbcTemplate.update(deleteSql);
		 int DeleteMore2=jdbcTemplate.update(deleteSql2);
		 
		 if((DeleteMore+DeleteMore2)==2){
			
			 return true;
		
		 }else{
		
		    return false;
		 }
		 
	
	}
	//审核

	public boolean getshenhe(String str) throws Exception {
	
		String sql="update userinfo set user_state='0' where user_ID='"+str+"'";
		int updateuserstate=jdbcTemplate.update(sql);
		if(updateuserstate==0){
			return false;
		}else{
			return true;
		}
	}
	//批量审核用户

	public boolean getPlshenhe(String str) throws Exception {
		String getStr=""; 
		String[] s=str.split(",");
		int res=0;
		for(int i=0;i<s.length;i++){
			
			
			res=jdbcTemplate.update("update userinfo set control_type='0',user_state='0' where user_ID='"+s[i]+"'");
		    res+=1;
		}
		if(res==s.length){
			return true;
		}else{
			return false;
		}
		
	}
	//添加用户时的查询用户角色
	public List getAddRoleInfo() throws Exception {
		String RoleList="select role_id,role_name from role";
		return jdbcTemplate.queryForList(RoleList);		
		
	}
	
	//批量导入用户的操作
	public int[] insetUser(final List actors) throws Exception {
		
		
		
		int[] updateCounts = jdbcTemplate.batchUpdate(
				
				"insert into userinfo (user_id,dep_code,spec_code,class_code,password,real_name,user_type,member_type,user_point,role_id,control_type,user_state ) values(?,?,?,?,?,?,?,?,?,?,?,?)",
                new BatchPreparedStatementSetter() {
					public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setString(1, ((PLUser)actors.get(i)).getUser_ID());
                        ps.setString(2, ((PLUser)actors.get(i)).getDep_code());
                        ps.setString(3, ((PLUser)actors.get(i)).getSpec_code());
                        ps.setString(4, ((PLUser)actors.get(i)).getClass_code());
                        ps.setString(5, ((PLUser)actors.get(i)).getPassword());
                        ps.setString(6, ((PLUser)actors.get(i)).getReal_name());
                        ps.setString(7, ((PLUser)actors.get(i)).getUser_type());
                        ps.setInt(8, ((PLUser)actors.get(i)).getMember_type());
                        ps.setInt(9, ((PLUser)actors.get(i)).getUser_point());
                        ps.setString(10, ((PLUser)actors.get(i)).getRole_id());
                        ps.setString(11, ((PLUser)actors.get(i)).getControl_type());
                        ps.setString(12, ((PLUser)actors.get(i)).getUser_state());
                        
                    }
                    public int getBatchSize() {
                        return actors.size();
                    }
                 
					
                } );
        return updateCounts;
    }
	
	public int[] insetUser_role(final List actors) throws Exception {

		int[] updateCounts = jdbcTemplate.batchUpdate(
				
				"insert into user_role (user_id,role_id) values(?,?)",
                new BatchPreparedStatementSetter() {
					
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setString(1, ((PLUser)actors.get(i)).getUser_ID());
                       
                        ps.setString(2, ((PLUser)actors.get(i)).getRole_id());
                       
                        
                    }
                   
                    public int getBatchSize() {
                        return actors.size();
                    }
                 
					
                } );
		
        return updateCounts;
	}
	
	public List selectUpdateUser(String userid) throws Exception {
		String sql="select userinfo.user_ID,department.dep_name,specialty.spec_name,class.class_name,userinfo.dep_code,userinfo.spec_code,userinfo.class_code,userinfo.password,userinfo.real_name,userinfo.user_type,userinfo.user_point,userinfo.member_type,role.role_id,role.role_name from user_role left join userinfo ON user_role.user_ID = userinfo.user_ID left join class ON userinfo.class_code = class.class_code left join department ON userinfo.dep_code = department.dep_code left join specialty ON userinfo.spec_code = specialty.spec_code left join role ON dbo.user_role.role_id = role.role_id where userinfo.user_ID='"+userid+"'";
		return convertupdateList(jdbcTemplate.queryForList(sql));
	}
	
	//去掉list重复用户，进行角色合并
	public List convertupdateList(List list1) throws Exception
	{	
		String preUserid="";
		Map premap=null;
		Iterator it1= list1.iterator();
		
		while (it1.hasNext()) {	
			Map map1=(Map) it1.next();
			String userid = (String)map1.get("user_ID");
			//是重复
			if(userid.equals(preUserid))
			{
				String rolename = (String)map1.get("role_name");
				String prerolename = (String)premap.get("role_name");
				premap.put("role_name", prerolename+","+rolename);
				String roleid = (String)map1.get("role_id");
				String preroleid = (String)premap.get("role_id");
				premap.put("role_id", roleid+","+preroleid);
				it1.remove();
			}
			preUserid=userid;
			premap=map1;
		}
		return list1;	
	}
	
	public int[] PLshanchu(final List actors) throws Exception {
            int[] updateCounts = jdbcTemplate.batchUpdate(
				
				"delete userinfo where user_ID=?",
                new BatchPreparedStatementSetter() {
					public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setString(1, ((PLUser)actors.get(i)).getUser_ID());
                    }
                    public int getBatchSize() {
                        return actors.size();
                    }
                 
					
                } );
        return updateCounts;
	}
	
	public int[] PLshanchuRole(final List actors) throws Exception {
		 int[] updateCounts = jdbcTemplate.batchUpdate(
					
					"delete user_role where user_ID=?",
	                new BatchPreparedStatementSetter() {
						public void setValues(PreparedStatement ps, int i) throws SQLException {
	                        ps.setString(1, ((PLUser)actors.get(i)).getUser_ID());
	                    }
	                    public int getBatchSize() {
	                        return actors.size();
	                    }
	                 
						
	                } );
	        return updateCounts;
	}
	
	//修改时用得已选的用户角色
	public List getCheckRoleInfo(String userid) throws Exception {
		String sql="select user_ID,role_id from user_role where user_ID='"+userid+"'";		
		List list2=jdbcTemplate.queryForList(sql);	
		List list1=getAddRoleInfo();
		boolean find=false;
		Iterator it1= list1.iterator();		
		while (it1.hasNext()) {
			Map map1=(Map) it1.next();
			Iterator it2= list2.iterator();		
			while (it2.hasNext()) {	
				Map map2=(Map) it2.next();
				String role_id1 = (String)map1.get("role_id");
				String role_id2 = (String)map2.get("role_id");				
				if(role_id1.equals(role_id2))
				{
					map1.put("check", "checked");
					find=true;
					break;
				}
			}
			if(find==false)
			{
				map1.put("check", "");
			}
		}
		return list1;
	}
	//批量增加积分
	public int[] addUserPoint(PLAddUserPoint plAddUserPoint) throws Exception {
		String role=plAddUserPoint.getRole_id();
		int userpoint=plAddUserPoint.getUserpoint();
		
		
        List<AddRole> getList=getjifen(role,userpoint);
        ZXPLAddPoint(getList);
		jdbcTemplate.update("insert into userpoint_change (user_ID,change_time,change_value,change_reson) values ('"+plAddUserPoint.getUser_id()+"',GETDATE(),'"+userpoint+"','批量增加积分')");

		return null;
	}
	//根据角色查询出用户ID和积分，并将查询出的积分+传入的增加积分，和用户ID放入List中
	public List<AddRole> getjifen(String role,final int userpoint) throws Exception {
		String selectID="";
		if(role.equals("0")||role=="0"){
            selectID="select userinfo.user_ID,userinfo.user_point,user_role.role_id from userinfo,user_role,role where userinfo.user_ID=user_role.user_ID and user_role.role_id=role.role_id";
		}else{
		    selectID="select userinfo.user_ID,userinfo.user_point,user_role.role_id from userinfo,user_role,role where userinfo.user_ID=user_role.user_ID and user_role.role_id=role.role_id and user_role.role_id='"+role+"'";
		}
      final List<AddRole> pllist = new ArrayList<AddRole>();
		
		jdbcTemplate.query(selectID, new RowCallbackHandler() {

		
			public void processRow(ResultSet rs) throws SQLException {
				AddRole pladduserpoint=new AddRole();
				//将要增加的积分数加在原始积分上
				pladduserpoint.setUserpoint(rs.getInt("user_point")+userpoint);
				pladduserpoint.setUser_id(rs.getString("User_id"));
				pllist.add(pladduserpoint);
			}
		});
		return pllist;
	}
	//执行积分的修改
	public int[] ZXPLAddPoint(final List<AddRole> actors) throws Exception {
		 int[] updateCounts = jdbcTemplate.batchUpdate(
					
					"update userinfo set user_point=? where user_ID=?",
	                new BatchPreparedStatementSetter() {
						public void setValues(PreparedStatement ps, int i) throws SQLException {
	                        ps.setInt(1,actors.get(i).getUserpoint());
	                        ps.setString(2, (actors.get(i)).getUser_id());
	                    }
	                    public int getBatchSize() {
	                        return actors.size();
	                    }
	                } );
	        return updateCounts;
		
	}
	
	public boolean getstopuser(String str) throws Exception {
		String getStr=""; 
		String[] s=str.split(",");
		int res=0;
		for(int i=0;i<s.length;i++){
			
			
			res=jdbcTemplate.update("update userinfo set user_state='1' where user_ID='"+s[i]+"'");
		    res+=1;
		}
		if(res==s.length){
			return true;
		}else{
			return false;
		}
	}
	//------------------------------------------------------------------------------------------------------
	
	public boolean Add_user(AddUser user) throws Exception{
		String ID = user.getUser_ID();
		String name = user.getUser_name();
		String dep = user.getDep_code();
		String spec = user.getSpec_code();
		String class_code = user.getClass_code();
		String ps = user.getPassword();
		String real_name = user.getReal_name();
		String tpye = user.getUser_type();
		String state = user.getUser_state();
		String sql="insert into userinfo(user_ID,dep_code,spec_code,class_code,user_name,password,real_name,user_type,member_type,user_point,user_state,user_role,cy_id,jy_state,jy_date)values('"+ID+"','"+dep+"','"+spec+"','"+class_code+"','"+name+"','"+ps+"','"+real_name+"','"+tpye+"','0','0','"+state+"','','','','')";
		String addusertoleSql="insert into user_role (user_ID,role_id) values ('"+ID+"','04')";

		int dao=jdbcTemplate.update(sql);
		if(dao!=0)
		{
			jdbcTemplate.update(addusertoleSql);
			return true;
		}
		else
		{
			return false;
		}
	}
}
