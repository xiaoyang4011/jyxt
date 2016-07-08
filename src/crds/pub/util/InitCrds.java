package crds.pub.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class InitCrds {
	static Logger logger = Logger.getLogger(InitCrds.class);
	private String username;//用户名
	private String password;//密码
	private String menuid;//菜单ID
	private String systype;//系统应用类型,B-银行,F-基金公司
	private String analyst_rights;//分析师操作权限 C：上报提交 S：可自己完成
	private String assgin_mothod;//抵押担保分配方式W：完全分配 S：共享分配
	@SuppressWarnings("unchecked")
	private List serverList;//web服务器列表
	@SuppressWarnings("unchecked")
	public List getServerList() {
		return serverList;
	}
	@SuppressWarnings("unchecked")
	public void setServerList(List serverList) {
		this.serverList = serverList;
	}
	/**
	 * 
	 * @specification : 验证系统默认管理用户sys密码
	 * @param :
	 * @return :
	 * @exception :
	 */
	public String validateSysUser(HttpServletRequest request,String passwd) {
		String enpass = passwd;
		try {
			manageXML(request, 0, passwd);
			enpass = Encryption.encode(passwd);
		} catch (Exception e) {
			logger.error("InitCrds.validateSysUser()密码验证错误。");
		}
		if (enpass.equals(password)) {
			return menuid;
		}
		return "";
	}
	/**
	 * 
	 * @specification :修改系统默认管理用户sys密码
	 * @param :
	 * @return :
	 * @exception :
	 */
	public boolean updateSysUser(HttpServletRequest request,String passwd){
		String enpass = passwd;
		try {
			enpass = Encryption.encode(passwd);
			manageXML(request, 1, enpass);
		} catch (Exception e) {
			logger.error("InitCrds.updateSysUser()密码修改错误。");
			return false;
		}

		return true;
	}
	/**
	 * 
	 * @specification :管理XML文件中系统默认用户sys信息
	 * @param :operateType:操作类型0：查询；1修改
	 * @return :
	 * @exception :
	 */
	@SuppressWarnings("unchecked")
	public void manageXML(HttpServletRequest request,int operateType,String password) throws DocumentException,FileNotFoundException,IOException,Exception {
		try{
			XMLWriter writer = null;// 声明写XML的对象
			SAXReader reader = new SAXReader();

			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("utf-8");// 设置XML文件的编码格式
			String realPath = request.getSession().getServletContext().getRealPath("/");// 获得应用的绝对路径
			String filePath = realPath+"WEB-INF/config/pub/p_pub_spring.xml";
			File file = new File(filePath);
			if (file.exists()) {
				Document document= reader.read(file);
				// 读取XML文件
				Element root = document.getRootElement();// 得到根节点
				for (Iterator i = root.elementIterator("bean"); i.hasNext();) {
					Element bean = (Element) i.next();
					if (bean.attributeValue("id").equals("initCrds")) {
						for (Iterator j = bean.elementIterator("property"); j.hasNext();) {
							Element property = (Element) j.next();
							if(operateType==1){//修改密码
								if (property.attributeValue("name").equals("password")) {
									property.addAttribute("value", password);//修改密码
									//写入XML
									writer = new XMLWriter(new FileOutputStream(filePath), format);
									writer.write(document);
									writer.close();
									break;
								}
							}else{//读取默认用户信息
								this.username="sys";
								if (property.attributeValue("name").equals("password")) {
									this.password=property.attributeValue("value");//密码
								}
								if (property.attributeValue("name").equals("menuid")) {
									this.menuid=property.attributeValue("value");//菜单ID
								}
							}
						}
						break;
					}
				}
			}
		}
		catch (DocumentException e) {
			logger.error("InitCrds.manageXML():读取XML有误。"+e.getMessage());
			throw e;
		}
		catch (FileNotFoundException e) {
			logger.error("InitCrds.manageXML():XML文件未找到。"+e.getMessage());
			throw e;
		}
		catch (IOException e) {
			logger.error("InitCrds.manageXML():写入XML有误。"+e.getMessage());
			throw e;
		}
		catch (Exception e) {
			logger.error("InitCrds.manageXML():XML操作有误。"+e.getMessage());
			throw e;
		}
	}


	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getMenuid() {
		return menuid;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}
	public String getSystype() {
		return systype;
	}
	public void setSystype(String systype) {
		this.systype = systype;
	}
	public String getAnalyst_rights() {
		return analyst_rights;
	}
	public void setAnalyst_rights(String analyst_rights) {
		this.analyst_rights = analyst_rights;
	}
	public String getAssgin_mothod() {
		return assgin_mothod;
	}
	public void setAssgin_mothod(String assgin_mothod) {
		this.assgin_mothod = assgin_mothod;
	}
	
	/**
	 * 调整评级报告时间
	 * reportType 0:主体评级报告 1：债项评级报告
	 * company_code:公司代码
	 * debt_code：债券内部联合代码（union_code）
	 * task_id:任务编号
	 * change_date:调整时间
	 */
	public void changeReportFileDate(HttpServletRequest request,int reportType,String company_code,String debt_code,String task_id,String change_date){
		//文件路径
		String filePath=request.getSession().getServletContext().getRealPath("/")+ ".."+File.separator+"internal"+File.separator+company_code+File.separator;
		if(reportType==0){
			filePath+=task_id+File.separator+"report.html";
		}else{
			filePath+=debt_code+File.separator+task_id+File.separator+"report.html";
		}
		//调整日期
		String d[]=change_date.split("-");
		Calendar cal_s = Calendar.getInstance();
		cal_s.set(Integer.parseInt(d[0]),Integer.parseInt(d[1])-1,Integer.parseInt(d[2]));

		//读取文件
		String content=CommonMethod.readFileToString(filePath, "utf-8");
		//报告日期
		int hr_index=content. indexOf("<hr");
		String date_str_1=content.substring(hr_index-53, hr_index-42);
		String a= new SimpleDateFormat("yyyy年MM月dd日").format(cal_s.getTime());
	    content=content.replaceAll(date_str_1, a);
	    System.out.println("报告时间 "+date_str_1);
		                     
		//评级日期
		int mr_index=content.indexOf("评级情况");
		if(mr_index!=-1){
			String date_str_2=content.substring(mr_index+88, mr_index+98);
			content=content.replaceAll(date_str_2, change_date);
			System.out.println("评级时间 "+date_str_2);
		}
		
		//上次评级日期，如小于调整日期，则在调整日期基础上减一年
		int pmr_index=content.indexOf("上次内部主体评级");
		if(pmr_index!=-1){
			String date_str_3=content.substring(pmr_index+92, pmr_index+102);
			if(date_str_3.indexOf("-")!=-1){
				String d3[]=date_str_3.split("-");
				
				Calendar cal0 = Calendar.getInstance();
				cal0.set(Integer.parseInt(d3[0]),Integer.parseInt(d3[1])-1,Integer.parseInt(d3[2]));
				
				Calendar cal1 = Calendar.getInstance();
				cal1.set(Integer.parseInt(d[0]),Integer.parseInt(d[1])-1,Integer.parseInt(d[2]));

				if(cal0.after(cal1)){
					cal1.add(Calendar.YEAR, -1);
					String b=new SimpleDateFormat("yyyy-MM-dd").format(cal1.getTime());
					content=content.replaceAll(date_str_3, b);
					System.out.println("上次内部主体评级 "+date_str_3);
				}
			}
		}
		
		//其他处理
		content=content.replaceAll("00:00:00", "");
		if(content.indexOf("(最近)")==-1){
			content=content.replaceAll("外部评级日期", "外部评级日期(最近)");
		}
		//重写报告文件
		CommonMethod.writeFile(filePath, content, "utf-8");
		System.out.println(content);

	}
public static void main(String[] args) {
//	File f1=new File("");
//	String path=this.getServlet().getServletContext().getRealPath("/")+ ".."+File.separator+"internal"+File.separator+company_code+File.separator+task_id;
//	String content3=CommonMethod.readFileToString(path+File.separator+"content3.html", "utf-8");
//	CommonMethod.writeFile(path+File.separator+"content1.html", charset+request.getParameter("content1").replaceAll(temp_task_id, task_id).replaceAll(url, "Image/"), "utf-8");
	
	String date="2009-10-10";
	String d[]=date.split("-");
	Calendar cal_s = Calendar.getInstance();
	cal_s.set(Integer.parseInt(d[0]),Integer.parseInt(d[1])-1,Integer.parseInt(d[2]));

	String filepath="E:/T20110518001/report.html";
//	String filepath="E:/D00000000122/T20110420025/report.html";
	
	String content=CommonMethod.readFileToString(filepath, "utf-8");
	int hr_index=content. indexOf("<hr");
	String date_str_1=content.substring(hr_index-53, hr_index-42);
	String a= new SimpleDateFormat("yyyy年MM月dd日").format(cal_s.getTime());
    System.out.println(a);
    content=content.replaceAll(date_str_1, a);
//	String r_d=d[0]+""+d[1];
	                     
	
	int mr_index=content.indexOf("评级情况");
	if(mr_index!=-1){
		String date_str_2=content.substring(mr_index+88, mr_index+98);
		content=content.replaceAll(date_str_2, date);
		System.out.println(date_str_2);
	}
	
	
	int pmr_index=content.indexOf("上次内部主体评级");
	if(pmr_index!=-1){
		String date_str_3=content.substring(pmr_index+92, pmr_index+102);
		if(date_str_3.indexOf("-")!=-1){
			String d3[]=date_str_3.split("-");
			
			Calendar cal0 = Calendar.getInstance();
			cal0.set(Integer.parseInt(d3[0]),Integer.parseInt(d3[1])-1,Integer.parseInt(d3[2]));
			
			Calendar cal1 = Calendar.getInstance();
			cal1.set(Integer.parseInt(d[0]),Integer.parseInt(d[1])-1,Integer.parseInt(d[2]));

			if(cal0.after(cal1)){
				cal1.add(Calendar.YEAR, -1);
				String b=new SimpleDateFormat("yyyy-MM-dd").format(cal1.getTime());
				content=content.replaceAll(date_str_3, b);
				System.out.println(date_str_3);
			}
			
		
		}
	}
	
	System.out.println(date_str_1);
//	System.out.println(date_str_2);
//	System.out.println(date_str_3);
	
	content=content.replaceAll("00:00:00", "");
	if(content.indexOf("(最近)")==-1){
		content=content.replaceAll("外部评级日期", "外部评级日期(最近)");
	}
	CommonMethod.writeFile(filepath, content, "utf-8");
	System.out.println(content);
}
}
