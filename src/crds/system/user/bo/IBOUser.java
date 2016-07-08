package crds.system.user.bo;

import java.util.List;

import crds.system.user.web.form.AddUser;
import crds.system.user.web.form.FormUser;
import crds.system.user.web.form.PLAddUserPoint;

public interface IBOUser {
	/**  查询院系*/
	@SuppressWarnings("unchecked")
	public List findAllDepts() throws Exception;
	/**  查询用户*/
	@SuppressWarnings("unchecked")
	public List queryUser(FormUser formuser ) throws Exception ;

	/**  增加用户*/
	@SuppressWarnings("unchecked")
	public boolean addUser(FormUser formuser) throws Exception;
	/**  修改用户*/
	@SuppressWarnings("unchecked")
	public boolean updateUser(FormUser formuser ) throws Exception;
	/**  删除用户*/
	@SuppressWarnings("unchecked")
	public boolean delUser( ) throws Exception;
	/**  查询用户*/
	@SuppressWarnings("unchecked")
	public boolean checkUserExists(FormUser formuser) throws Exception; 
	
	/*
	 * 
	 * 以下为用户管理的代码
	 * **/
	
	public List userInfo(AddUser adduser ) throws Exception;
	//添加用户信息
	public boolean getAddUserinfo(AddUser adduser) throws Exception;

	//删除用户信息
	public boolean getDeleteUser(String userid) throws Exception;

	//根据条件查询
	public List getSelectUserinfo(AddUser adduser) throws Exception ;
	//修改用户信息
	public List getUpdateUserList(AddUser user) throws Exception;

	//验证用户ID是否存在
	public boolean getCheckID(String id) throws Exception;
	
	//执行修改用户的操作
	public boolean getUPdateInfo(AddUser user) throws Exception;
	
	//查询班级信息，为用户添加提供数据
	public List getClassInfo() throws Exception;
	
	//first下拉列表查出机构信息
	public List getJiGouInfo()throws Exception;
	
	//第二次下拉列表的查询专业查询
    public List getZhuanye(String id) throws Exception;
	
    
  //第三次查询下拉列表查询班级
    public List getBanJi(String id) throws Exception;
	
  //查询用户角色的信息
    public List getRoleInfo() throws Exception;
  //执行删除多条的记录
    public boolean getDeleteMore(String str) throws Exception;
   //审核用户
    public boolean getshenhe(String str) throws Exception;
   //批量审核用户
    public boolean getPlshenhe(String str) throws Exception;
    
   //获取添加用户时的角色的信息
    public List getAddRoleInfo() throws Exception;
    
  //批量导入用户
    public boolean PLDaoRu(String str) throws Exception;
  //批量导入Excel删除用户
    public boolean PLshanchu(String str) throws Exception;
    //修改用户查询出修改信息
    public List selectUpdateUser(String userid) throws Exception;
  //修改时用得已选的用户角色
	public List getCheckRoleInfo(String userid) throws Exception ;
  //批量增加积分
	public void addUserPoint(PLAddUserPoint plAddUserPoint) throws Exception;
	  //批量审核用户
    public boolean getstopuser(String str) throws Exception;
    
	public boolean Add_user(AddUser user) throws Exception;
}  