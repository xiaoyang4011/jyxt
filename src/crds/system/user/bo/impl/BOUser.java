package crds.system.user.bo.impl;



import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;

import crds.system.user.bo.IBOUser;
import crds.system.user.dao.IDAOUser;
import crds.system.user.web.form.AddUser;
import crds.system.user.web.form.FormUser;
import crds.system.user.web.form.PLAddUserPoint;
import crds.system.user.web.form.PLUser;
//import org.apache.taglibs.standard.lang.jstl.Logger;


 

/**
 * 
 * @version : 1.0
 * @author : dongl
 * @date : 
 * @email : 
 */
public class BOUser implements IBOUser{
	
	private IDAOUser dao;

	public void setDAOUser(IDAOUser dao) {
		this.dao = dao;
	}

	
	/**  查询院系*/
	@SuppressWarnings("unchecked")
	public List findAllDepts() throws Exception 
	{
		return(dao.findAllDepts());
	}
	/**  查询用户*/
	@SuppressWarnings("unchecked")
	public List queryUser(FormUser formuser) throws Exception 
	{
		return(dao.queryUser(formuser));
	}
	/**  增加用户*/
	@SuppressWarnings("unchecked")
	public boolean addUser(FormUser formuser ) throws Exception{
		return dao.addUser(formuser);
	}
	/**  修改用户*/
	@SuppressWarnings("unchecked")
	public boolean updateUser(FormUser formuser ) throws Exception{
		return true;
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
		return(dao.checkUserExists(formuser));
	}

	
	/*
	 * 
	 * 以下为用户管理的代码
	 * **/
	

	//用户信息
	public List userInfo(AddUser adduser) throws Exception {
		
		return dao.userInfo(adduser);
	}
	
    //添加信息
	public boolean getAddUserinfo(AddUser adduser) throws Exception{
		 
		return dao.getAddUserInfo(adduser);
	}
    //删除用户
	public boolean getDeleteUser(String userid) throws Exception {
		
		boolean result=false;
		if(dao.getDeleteUserRes(userid)==2){
			result=true;
		}else{
			result=false;
		}
		return result;
	}
  //根据条件查询
	public List getSelectUserinfo(AddUser adduser) throws Exception {
	
		return dao.getSelectUserinfo(adduser);
	}
   //修改用户
	public List getUpdateUserList(AddUser user) throws Exception {

         String userid=user.getUser_ID();
		return dao.getSelectUserinfo(user);
	}
    //验证用户的ID是否应经存在
	public boolean getCheckID(String id) throws Exception {
		
		List getid=dao.getCheckID(id);
		
		if(getid.size()==0){
			return true;
		}else{
			return false;
		}
	}

	//修改用户前的数据显示
	public boolean getUPdateInfo(AddUser user) throws Exception {
		if(dao.getUpdateInfo(user)==1){
			return true;
		}else{
			return false;
		}
	}

	//查询班级信息，为用户添加提供数据

	public List getClassInfo() throws Exception {
		return dao.getClassInfo();
	}


	//first下拉列表查出机构信息
	public List getJiGouInfo() throws Exception {
		// TODO Auto-generated method stub
		return dao.getJiGouInfo();
	}

	//第二次下拉列表的查询专业查询
	
	public List getZhuanye(String id) throws Exception {
		// TODO Auto-generated method stub
		return dao.getZhuanye(id);
	}

	//第三次查询下拉列表查询班级
	
	public List getBanJi(String id) throws Exception {
		// TODO Auto-generated method stub
		return dao.getBanJi(id);
	}

	//查询用户角色的信息

	public List getRoleInfo() throws Exception {
		// TODO Auto-generated method stub
		return dao.getRoleInfo();
	}

	//执行删除多条的记录
	
	public boolean getDeleteMore(String str) throws Exception {
		// TODO Auto-generated method stub
		return dao.getDeleteMore(str);
	}

   //审核
	
	public boolean getshenhe(String str) throws Exception {
		
		return dao.getshenhe(str);
	}

   //批量审核用户

	public boolean getPlshenhe(String str) throws Exception {
		// TODO Auto-generated method stub
		return dao.getPlshenhe(str);
	}

    
    //获取添加用户时的角色的信息
	public List getAddRoleInfo() throws Exception {
		// TODO Auto-generated method stub
		return dao.getAddRoleInfo();
	}
   
	
	//批量导入用户做的业务逻辑处理
	public boolean PLDaoRu(String user) throws Exception {
		
		String FileString="";
		String temp=user;
		
		String temp2=temp.replace('/', ',');
		String sz[]=temp2.split(",");
		for(int i=0;i<sz.length;i++){
			FileString+=sz[i]+"\\\\";
		}
		if(FileString.length()>2){ 
			FileString=FileString.substring(0,FileString.length()-2); 
		}
		
	 
	   
		//存放Excel内容的bean的List
		List<PLUser> listuser=new ArrayList<PLUser>();
		jxl.Workbook rwb = null;
	    try{
	      InputStream is = new FileInputStream("G:\\5.5\\apache-tomcat-5.5.35\\webapps\\jyxt\\upload\\[322]123.xls");
	    	//InputStream is = new FileInputStream(FileString);
	      rwb = Workbook.getWorkbook(is);
	       Sheet rs = rwb.getSheet(0);
	       //获取Sheet表中所包含的总行数
	       int rsRows = rs.getRows();
	       //获取指定单元格的对象引用  
	       for (int i = 1; i < rsRows; i++) {
	       PLUser pluser=new PLUser();
	       pluser.setUser_ID(rs.getCell(0, i).getContents());
		   pluser.setDep_code(rs.getCell(1, i).getContents());
		   pluser.setSpec_code(rs.getCell(2, i).getContents());
		   pluser.setClass_code(rs.getCell(3, i).getContents());
		   pluser.setPassword(rs.getCell(4, i).getContents());
           pluser.setReal_name(rs.getCell(5, i).getContents());
		   pluser.setUser_type(rs.getCell(6, i).getContents());
		   pluser.setMember_type(rs.getCell(7, i).getContents());
		    // System.out.println(rs.getCell(7, i).getContents());
		   pluser.setUser_point(rs.getCell(8, i).getContents());	
		    // System.out.println(rs.getCell(8, i).getContents());
		   pluser.setRole_id(rs.getCell(9, i).getContents());
		   pluser.setControl_type(rs.getCell(10, i).getContents());
		   pluser.setUser_state(rs.getCell(11, i).getContents());
		   //放入集合中
		   listuser.add(pluser);
	       }
	    }catch(Exception e){
	       e.printStackTrace();
	     //  return false;
	    }
	    finally{
	      //操作完成时，关闭对象，释放占用的内存空间（重要！）
	      rwb.close();
	    }
	   //对javabean中的数据进行遍历，查找关键字段是否为空 
       for (PLUser plUser2 : listuser) {
	    	   if(plUser2.getUser_ID()==""){
	    		   System.out.println("UserID为空值！！");
	    		   return false;
	    	   }
	    	   if(plUser2.getPassword()==""){
	    		   System.out.println("密码不能为空！！");
	    		   return false;
	    	   }
	    	   if(plUser2.getReal_name()==null){
	    		   System.out.println("真实名不能为空！！");
	    		   return false;
	    	   }
	    	   if(plUser2.getRole_id()==null){
	    		   System.out.println("角色不能为空！！");
	    		   return false;
	    	   }
		     	      }
		
	       dao.insetUser(listuser);
	       dao.insetUser_role(listuser);
	       return true;
	       
	}


	
	public List selectUpdateUser(String userid) throws Exception {
		// TODO Auto-generated method stub
		return dao.selectUpdateUser(userid);
	}

	  //修改时用得已选的用户角色
	public List getCheckRoleInfo(String userid) throws Exception {
		return dao.getCheckRoleInfo(userid);
	}
	
	public boolean PLshanchu(String user) throws Exception {
	
		String FileString="";
		String temp=user;
		
		String temp2=temp.replace('/', ',');
		String sz[]=temp2.split(",");
		for(int i=0;i<sz.length;i++){
			FileString+=sz[i]+"\\\\";
		}
		if(FileString.length()>2){ 
			FileString=FileString.substring(0,FileString.length()-2); 
		}
		
	 
	   
		//存放Excel内容的bean的List
		List<PLUser> listuser=new ArrayList<PLUser>();
		jxl.Workbook rwb = null;
	    try{
	      //InputStream is = new FileInputStream("G:\\SSH\\Spring\\Spring学习笔记\\test.xls");
	    	InputStream is = new FileInputStream(FileString);
	      rwb = Workbook.getWorkbook(is);
	       Sheet rs = rwb.getSheet(0);
	       //获取Sheet表中所包含的总行数
	       int rsRows = rs.getRows();
	       //获取指定单元格的对象引用  
	       for (int i = 1; i < rsRows; i++) {
	       PLUser pluser=new PLUser();
	       pluser.setUser_ID(rs.getCell(0, i).getContents());
		  
		   //放入集合中
		   listuser.add(pluser);
	       }
	    }catch(Exception e){
	       System.out.println("读取文件异常"+e);
	       return false;
	    }
	    finally{
	      //操作完成时，关闭对象，释放占用的内存空间（重要！）
	      rwb.close();
	    }
	
	       dao.PLshanchu(listuser);
	       dao.PLshanchuRole(listuser);
	       return true;
	}


	//批量增加积分
	public void addUserPoint(PLAddUserPoint plAddUserPoint) throws Exception {
		 dao.addUserPoint(plAddUserPoint);
	}


	
	public boolean getstopuser(String str) throws Exception {
		return dao.getstopuser(str);
	}
	public boolean Add_user(AddUser user) throws Exception{
		return dao.Add_user(user);
	}
}
