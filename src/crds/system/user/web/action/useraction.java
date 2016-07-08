package crds.system.user.web.action;

import java.awt.image.BufferedImage;
import crds.pub.util.Constant;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import org.springframework.web.struts.MappingDispatchActionSupport;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import crds.pub.util.FormUserOperation;
import crds.stud.bo.IBOStud;
import crds.system.sysSite.bo.IBOSysSite;
import crds.system.sysSite.web.form.PointRulerForm;
import crds.system.user.bo.IBOUser;
import crds.system.user.bo.impl.BOUser;
import crds.system.user.web.form.AddUser;
import crds.system.user.web.form.DClass;
import crds.system.user.web.form.Department;
import crds.system.user.web.form.InputUser;
import crds.system.user.web.form.PLAddUserPoint;
import crds.system.user.web.form.Role;
import crds.system.user.web.form.Specialty;
import crds.upload.web.form.UploadForm;

public class useraction extends MappingDispatchActionSupport{
	//private Map<String, BufferedImage> map = null;
//	private LinkedHashMap<String, LinkedHashMap<String, String>> map_data=null;
	//public IBOUser user;
	public IBOUser getBO() {
		return (IBOUser) this.getWebApplicationContext().getBean("BOUser");
	}
	
	
  //初始积分的获取
	 public IBOSysSite getTwoBO(){
		  return (IBOSysSite)this.getWebApplicationContext().getBean("BOSysSite");
	 }
	///
	
	 
	 
	 
	//全部查询
	public ActionForward usershowPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		AddUser formStud=(AddUser)form;;
		
		String role_id=formStud.getRole_id();
		String userState=formStud.getUser_state();
		String spec=formStud.getSpec();
		String dept=formStud.getDept();
		String class_code=formStud.getClass_code();
		
		// 设置每页行数
		if (formStud.getRowsPerPage() == 0) {
			formStud.setRowsPerPage(10);
		}
		List  resultList = null;
		
		
		List list=null;
           list=getBO().getSelectUserinfo(formStud);
			//list=getBO().userInfo(formStud);

		
		List RoleInfo=getBO().getRoleInfo();
		
		request.setAttribute("rolelist", RoleInfo);
		
		
		request.setAttribute("role_id", role_id);
		request.setAttribute("user_state", userState);
		request.setAttribute("spec", spec);
		request.setAttribute("dept", dept);
		request.setAttribute("class_code", class_code);
		
		request.setAttribute("student", list);
		request.setAttribute("formStud", formStud);
		return mapping.findForward("succ");
		
	}
	//添加用户前，显示页面
	@SuppressWarnings("unchecked")
	public ActionForward addUserPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        
		List classList=getBO().getAddRoleInfo();
		
		request.setAttribute("student", classList);
			this.saveToken(request);
		return mapping.findForward("addUserpage");
	}
	//添加用户
	public ActionForward addUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    
		
      //------------------
		AddUser adduser=(AddUser)form;
	
		boolean addresult=getBO().getAddUserinfo(adduser);
		if(addresult==true){
			request.setAttribute("message", "添加成功");
			return mapping.findForward("true");
			}
		else{
	    	return mapping.findForward("flase");
			}
	
	}
	//删除用户
	public ActionForward deleteUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//form表单
		AddUser deleteuser=(AddUser)form;
		String userid=deleteuser.getUserid();
		
		boolean deleteUser=getBO().getDeleteUser(userid);
		if(deleteUser){
			request.setAttribute("message", "删除成功");
			return mapping.findForward("true");
		}
		else{
			request.setAttribute("message", "删除成功");
			return mapping.findForward("flase");
		}
	}

	//修改页面前的数据准备
	@SuppressWarnings("unchecked")
	public ActionForward updateUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		 AddUser updateUser=(AddUser)form;
		 String id=request.getParameter("user_ID");

		 //List roleinfo=getBO().getAddRoleInfo();
		
         List userinfo=getBO().selectUpdateUser(id);
         
         List roleinfo=getBO().getCheckRoleInfo(id);
        
         
         Iterator it1= userinfo.iterator();	
         while (it1.hasNext()) {
         			Map map1=(Map) it1.next();	
         			
         			String dep_code=(String)map1.get("dep_code");
         			String spec_code=(String)map1.get("spec_code");
         			String class_code=(String)map1.get("class_code");
         			String dep_name=(String)map1.get("dep_name");
         			String spec_name=(String)map1.get("spec_name");
         			String class_name=(String)map1.get("class_name");
         			System.out.println(dep_code+","+spec_code+","+class_code);
         			 request.setAttribute("dept", dep_code);
         			 request.setAttribute("spec", spec_code);
         			 request.setAttribute("class_code", class_code);
         			 
         			 request.setAttribute("dep_name", dep_name);
         			 request.setAttribute("spec_name", spec_name);
         			 request.setAttribute("class_name", class_name);
         			
         			
         			if(((String)map1.get("user_type")).equals("学生"))
         			{
         			    request.setAttribute("usertytea", "");
         			    request.setAttribute("usertystu", "checked");
         			  
         			}else if(((String)map1.get("user_type")).equals("教师")){
         				 request.setAttribute("usertytea", "checked");
          			    request.setAttribute("usertystu", "");
          			  
         			}
         			
         			if(((Integer)map1.get("member_type"))==2)
         			{
         				request.setAttribute("putong", "checked");
         			    request.setAttribute("gaoji", "");
         			   request.setAttribute("vip", "");
         			}else if(((Integer)map1.get("member_type"))==3){
         				
         				request.setAttribute("putong", "");
         			    request.setAttribute("gaoji", "checked");
         			   request.setAttribute("vip", "");
         			}
         			else if(((Integer)map1.get("member_type"))==4){
         				
         				request.setAttribute("putong", "");
         			    request.setAttribute("gaoji", "");
         			   request.setAttribute("vip", "checked");
        			}
         			
         }
        
         
		request.setAttribute("roleinfo", roleinfo);
		request.setAttribute("userinfo", userinfo);
        
		/* String id=request.getParameter("user_ID");
		 String name=getISO(request.getParameter("user_name"));
		// String reaname=getISO(request.getParameter("real_name"));
		 String usertye=getISO(request.getParameter("user_type"));
		 String membertype=request.getParameter("membertype");
		 String password=request.getParameter("password");
		 String userpoint=request.getParameter("user_point");
		
		 request.setAttribute("password", password);
		 request.setAttribute("user_ID", id);
		 request.setAttribute("name", name);
		// request.setAttribute("reaname", reaname);
		 request.setAttribute("usertye", usertye);
		 request.setAttribute("membertype", membertype);
		 request.setAttribute("userpoint", userpoint);
		*/
		 
		
		
		return mapping.findForward("update");
	}
    
	//执行修改
	public ActionForward updatePackage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AddUser updateUser=(AddUser)form;
		 boolean updateResult=getBO().getUPdateInfo(updateUser);
		 if(updateResult){
			 request.setAttribute("message", "修改成功");
			 return mapping.findForward("updatePackage");
		 }
		 else{
			 request.setAttribute("message", "修改失败");
			 return mapping.findForward("updatePackageNO");
		 }
	}

	//第一次查询机构结果
	@SuppressWarnings("unchecked")
	public ActionForward first(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Department> listjigou=getBO().getJiGouInfo();
		JSONArray array = new JSONArray();
		for(Department d : listjigou){
			JSONObject obj = new JSONObject();
			obj.put("code", d.getDep_code());
			obj.put("name", d.getDep_name());
			array.add(obj);
		}
		ajaxResponse(response, array.toString());
		return null;
	}
	//第2次查专业结果
	public ActionForward second(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String dep_code = request.getParameter("dep_code");
		List<Specialty> list=getBO().getZhuanye(dep_code);
		JSONArray array = new JSONArray();
		for(Specialty d : list){
			JSONObject obj = new JSONObject();
			obj.put("code", d.getSpec_code());
			obj.put("name", d.getSpec_name());
			array.add(obj);
		}
		
		ajaxResponse(response, array.toString());
		return null;
	}
	//第3次查班级构结果
	public ActionForward third(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String spec_code = request.getParameter("spec_code");
		
		List<DClass> list=getBO().getBanJi(spec_code);
		JSONArray array = new JSONArray();
		for(DClass d : list){
			JSONObject obj = new JSONObject();
			obj.put("name", d.getClass_name());
			obj.put("code", d.getClass_code());
			array.add(obj);
		}
		
	
		
		ajaxResponse(response, array.toString());
		return null;
	}
	//查询用户角色的信息 
	public ActionForward juese(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		List<Role> list=getBO().getRoleInfo();
		JSONArray array = new JSONArray();
		for(Role d : list){
			JSONObject obj = new JSONObject();
			
			obj.put("name",d.getRole_name());
		    obj.put("code",d.getRole_id());
			array.add(obj);
		}
		ajaxResponse(response, array.toString());
		return null;
	}
   //供Ajax查询调用
	public void ajaxResponse(HttpServletResponse response, String text){
		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(text);
		} catch (IOException e) {
		}
	}
	/*
	 * 添加用户啊Ajax验证用户ＩＤ
	 * */
	public ActionForward ajax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		/*
		 * 在Action中操作session
		 * */
		response.setContentType("text/html;charset=utf-8");
		String temp =request.getParameter("user_ID");
		boolean b=getBO().getCheckID(temp);
		
		if(b){
			//response.getWriter().write("√");
		}else{
			response.getWriter().write("该ID已经存在请换个ID");
		}
		
 
		return null;
	}
	
	//批量删除用户
	public ActionForward deleteAll(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String str=request.getParameter("strinfo");
		
		 boolean deleteinfo=getBO().getDeleteMore(str);
	     if(deleteinfo){
	       request.setAttribute("message", "批量删除成功");
	     }else{
	   
	     }
	     return mapping.findForward("userdeleteall");
	}
	
	//审核用户     
	public ActionForward shenhe(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String userid=request.getParameter("userid");
	
		boolean deleteinfo=getBO().getshenhe(userid);
	     if(deleteinfo){
	       request.setAttribute("message", "审核成功");
	     }else{
	  
	     }
	     return mapping.findForward("shenhe");
	}
	
	//批量审核用户
	
	public ActionForward plshenhe(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String str=request.getParameter("strinfo");
		
		
		 boolean deleteinfo=getBO().getPlshenhe(str);
	
		if(deleteinfo){
	       request.setAttribute("message", "批量审核成功");
	     }else{
	  
	     }
	     return mapping.findForward("plshenhe");
	}
	
	 private String getISO(String iso){
		   String userNames="";
			try{
			userNames=new String(iso.getBytes("UTF-8"),"UTF-8");
			}catch(Exception e){
				userNames="null";
			}
			return userNames;
	    }
	
/*批量导入用户的处理*/
	 
	 public ActionForward pldaoru(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		 String ReadFilePath;
		 try{
		 StringBuffer newpath=new StringBuffer();
		 InputUser inputUser=(InputUser)form;
		 
		
		 //获取tomcat根路径
		 String path = request.getSession().getServletContext().getRealPath("/");
		
		 //路径格式的转换
		 String temp2=path.replace('\\', ',');
		 String[] serverPath=temp2.split(",");
		 for(int i=0;i<serverPath.length-1;i++){
			 newpath.append(serverPath[i]).append("/");
		 }
		 String temp=newpath.toString().substring(0,newpath.toString().length()-1);
		 //拼接为新的Excel文件的读取路径
		 ReadFilePath=temp+inputUser.getMediaPath();
		 }catch(Exception e){
			 System.out.println("tomcat路径转换出错"+e);
			 return mapping.findForward("uploaderr");
		 }
		// System.out.println("新的路径"+ReadFilePath);
		 try{
			 if(!getBO().PLDaoRu(ReadFilePath)){
				 return mapping.findForward("filereaderr");
			 }
		   }catch(Exception e){
			   System.out.println("文件读取异常"+e);
			   //调用后台方法出错为文件读取异常
			   return mapping.findForward("filereaderr");
		   }
		 
		 return mapping.findForward("ok");
	 }

	
/*批量导入Excel删除用户的处理*/
	 
	 public ActionForward plshanchu(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		 String ReadFilePath;
		 try{
		 StringBuffer newpath=new StringBuffer();
		 InputUser inputUser=(InputUser)form;
		 
		// System.out.println(inputUser.getMediaPath());
		 //获取tomcat根路径
		 String path = request.getSession().getServletContext().getRealPath("/");
		 //System.out.println(path);
		 //路径格式的转换
		 String temp2=path.replace('\\', ',');
		 String[] serverPath=temp2.split(",");
		 for(int i=0;i<serverPath.length-1;i++){
			 newpath.append(serverPath[i]).append("/");
		 }
		 String temp=newpath.toString().substring(0,newpath.toString().length()-1);
		 //拼接为新的Excel文件的读取路径
		 ReadFilePath=temp+inputUser.getMediaPath();
		 }catch(Exception e){
			 System.out.println("tomcat路径转换出错"+e);
			 return mapping.findForward("uploaderr");
		 }
		// System.out.println("新的路径"+ReadFilePath);
		 try{
			 if(!getBO().PLshanchu(ReadFilePath)){
				 return mapping.findForward("filereaderr");
			 }
		   }catch(Exception e){
			   System.out.println("文件读取异常"+e);
			   //调用后台方法出错为文件读取异常
			   return mapping.findForward("filereaderr");
		   }
		 
		 return mapping.findForward("ok");
	 }
	 
		//批量审核用户
		
		public ActionForward pladduserpoint(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
			List RoleInfo=getBO().getRoleInfo();
	
			request.setAttribute("rolelist", RoleInfo);
			 request.setAttribute("message", "批量审核成功");
		     return mapping.findForward("ok");
		}
		


		
		//批量停用用户
		
		public ActionForward stopuser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
			String str=request.getParameter("strinfo");
			
			System.out.println(str);
			 boolean deleteinfo=getBO().getstopuser(str);
		
			if(deleteinfo){
		       request.setAttribute("message", "批量停用成功");
		     }else{
		  
		     }
		     return mapping.findForward("plshenhe");
		}
		
	 //---------------------------------用户批量导入2POI-----------------------------------------------------------------------
		
		
	 
		private String getCellValue(Cell c){
			String o =null;
			switch (c.getCellType()){
			case  Cell.CELL_TYPE_BLANK:
			o = "";break;
			case Cell.CELL_TYPE_BOOLEAN:
				o=String.valueOf(c.getBooleanCellValue());break;
			case Cell.CELL_TYPE_FORMULA:
				o=String.valueOf(c.getCellFormula());break;
			case Cell.CELL_TYPE_NUMERIC:
				o=String.valueOf(c.getNumericCellValue());break;
			case Cell.CELL_TYPE_STRING:
				o=String.valueOf(c.getStringCellValue());break;
			default:
				o=null;
				break;
				
			
			}
			return o;
		}
		
		
		public ActionForward Add_user(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception{
			
			AddUser form2=(AddUser)form;
			FormFile file = form2.getFile(); 
			TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
			  Calendar ca = Calendar.getInstance();
			    int year = ca.get(Calendar.YEAR);//获取年份
			    int month=ca.get(Calendar.MONTH);//获取月份
			    int day=ca.get(Calendar.DATE);//获取日
			    int hour=ca.get(Calendar.HOUR_OF_DAY);//小时
			    int minute=ca.get(Calendar.MINUTE);//分
			    int second=ca.get(Calendar.SECOND);//秒
			    String formit = ".xls";
			    String name = year+""+(month + 1 )+ "" + day + ""+ hour + "" + minute + "" + second;
			    String f_name = name+""+formit;//重命名文件
			FileOutputStream fos = new FileOutputStream("d:\\"+f_name+""); //创建输出流  
		    fos.write(file.getFileData()); //写入  
		    fos.flush();//释放  
		    fos.close(); //关闭  
		try {
			Workbook wb =WorkbookFactory.create(new File("d:"+f_name+""));
			Sheet sheet = wb.getSheetAt(0);
			Row row = sheet.getRow(0);
			
			//Cell cell =row.getCell(0);
			//System.out.println(cell.getCellType());
			//System.out.println(cell.getStringCellValue());
			
			for(int i=1;i<=sheet.getLastRowNum();i++)
			{	row = sheet.getRow(i);
				form2.setUser_ID(getCellValue(row.getCell(0)));
				form2.setUser_name(getCellValue(row.getCell(1)));
				form2.setDep_code(getCellValue(row.getCell(2)));
				form2.setSpec_code(getCellValue(row.getCell(3)));
				form2.setClass_code(getCellValue(row.getCell(4)));
				form2.setPassword(getCellValue(row.getCell(5)));
				form2.setReal_name(getCellValue(row.getCell(6)));
				form2.setUser_type(getCellValue(row.getCell(7)));
				form2.setUser_state(getCellValue(row.getCell(8)));
				
				if(form2!=null){
					getBO().Add_user(form2);
				}
			
			}
		} catch (InvalidFormatException e) {
			
			// TODO Auto-generated catch block
			return mapping.findForward("fail");
		} catch (IOException e) {
			return mapping.findForward("fail");
			// TODO Auto-generated catch block
		
		}
		return mapping.findForward("succ");
	}
}