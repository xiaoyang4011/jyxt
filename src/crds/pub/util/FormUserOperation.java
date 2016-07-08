/**
 * product name :CRDS(Credit Risk Decision System)
 */
package crds.pub.util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;

/**
 * @specification :登陆用户相关信息
 * @version : 1.0
 * @auther : LuGuohui
 * @date : Aug 20, 2008 1:58:25 PM
 * @email : luguohui99@gmail.com
 */
public class FormUserOperation extends ActionForm {
	private static final long serialVersionUID = 1L;
	// 用户编号
	private String user_id;
	// 定位公司时此公司的分析师ID
	private String analyse_person_id;
	// 用户姓名
	private String user_name;
	// 角色编号
	private String role_id;
	// 角色名称
	private String role_name;
	// 发行人编码
	private String company_code;
	private String switchBeforeCompanyCode;
	// 发行人名称
	private String company_name;
	// 任务ID
	private String task_id;
	// 公司类别(IT-保险公司；ST-证券公司；BT-商业银行；ET-一般企业)
	private String company_type;
	// 公司所属行业
	private String industry_class;
	private String industry_name;
	// 用户简称
	// private String user_suoxie;
	// 企业性质
	private String company_prop;
	// 是否跟踪
	private String is_trace;
	// 项目适用类型
	private String systype = null;
	// 部门ID
	private String dept_id = null;
	// 部门名称
	private String dept_name = null;
	// 是否属于流程
	private String is_flow = null;
	//分析师操作权限 C：上报提交 S：可自己完成
	private String analyst_rights=null;
	//抵押担保分配方式W：完全分配 S：共享分配
	//private String assgin_mothod=null;
	
	public String getAnalyst_rights() {
		return analyst_rights;
	}

	public void setAnalyst_rights(String analyst_rights) {
		this.analyst_rights = analyst_rights;
	}

	public String getIs_flow() {
		return is_flow;
	}

	public void setIs_flow(String is_flow) {
		this.is_flow = is_flow;
	}

	public String getCompany_prop() {
		return company_prop;
	}

	public void setCompany_prop(String company_prop) {
		this.company_prop = company_prop;
	}

	public String getIndustry_class() {
		return industry_class;
	}

	public void setIndustry_class(String industry_class) {
		this.industry_class = industry_class;
	}
	public String getIndustry_name() {
		return this.industry_name!=null && this.industry_name.length()>0?this.industry_name:CommonMethod.getIndustryName(this.industry_class, 2);
	}
	public void setIndustry_name(String industry_name) {
		this.industry_name = industry_name;
	}

	public String getTask_id() {
		return task_id;
	}

	public void setTask_id(String task_id) {
		this.task_id = task_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public FormUserOperation() {

	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getCompany_code() {
		return company_code;
	}

	public void setCompany_code(String company_code) {
		this.company_code = company_code;
	}
	public String getSwitchBeforeCompanyCode() {
		return switchBeforeCompanyCode;
	}
	public void setSwitchBeforeCompanyCode(String switchBeforeCompanyCode) {
		this.switchBeforeCompanyCode = switchBeforeCompanyCode;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getCompany_type() {
		return company_type;
	}

	public void setCompany_type(String company_type) {
		this.company_type = company_type;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getAnalyse_person_id() {
		return analyse_person_id;
	}

	public void setAnalyse_person_id(String analyse_person_id) {
		this.analyse_person_id = analyse_person_id;
	}

	public String getRole_id() {
		return role_id;
	}

	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	// public String getUser_suoxie() {
	// return user_suoxie;
	// }
	//
	// public void setUser_suoxie(String user_suoxie) {
	// this.user_suoxie = user_suoxie;
	// }

	public String getIs_trace() {
		return is_trace;
	}

	public void setIs_trace(String is_trace) {
		this.is_trace = is_trace;
	}

	public String getDept_id() {
		return dept_id;
	}

	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	public String getSystype() {
		return systype;
	}

	public void setSystype(String systype) {
		this.systype = systype;
	}
	
	/**
	 * 判断必须项是否为空
	 * @param request 页面请求,当此方法的返回值为false时,此请求对象中会置入值:mustPartForwardURL(公司定位必须部分的转发地址)和companyPosMustPartMessage(公司定位必须部分的提示信息)
	 * @param response 针对参数isAutoForward=true是有效
	 * @param isAutoForward 是否自动构造转发地址
	 * @return boolean 必须项是否有空值,true-没有,false-有
	 * @throws IOException 
	 */
	public boolean judgeMustPart(HttpServletRequest request,HttpServletResponse response, boolean isAutoForward) throws IOException{
		String R00Name = "分析师";
		boolean isR00 = Constant.ROLE_R00.equals(role_id);//是否是(分析师/客户经理)角色的用户
		boolean isNotPosR00 = (analyse_person_id == null || "".equals(analyse_person_id) || "NA".equals(analyse_person_id)); //定位的公司未指定(分析师/客户经理)
		boolean isSelfCompany = user_id.equals(analyse_person_id);//是否是自己管理的公司
		//
		boolean success = true;
		String companyPosMustPartMessage = "";//公司定位必须部分的提示信息
		if (company_code == null || "".equals(company_code) || "NA".equals(company_code)) {//未定位发行人
			companyPosMustPartMessage = "请首先定位公司!";
			success = false;
		}
//		else if (isNotPosR00) {//指定的公司未定位(分析师/客户经理)
//			companyPosMustPartMessage = isR00 ? "请具有"+R00Name+"角色的人员先指定该公司的"+R00Name+"!" : "该公司未指定"+R00Name+",请重新定位公司!";
//			success = false;
//		} 
		else if (industry_class == null || "".equals(industry_class) || "NA".equals(industry_class)) {
			companyPosMustPartMessage = isSelfCompany ? "请指定该公司的行业类别!" : "该公司未指定行业类别,请重新定位公司!";
			success = false;
//		} else if(industry_class.length() == 8 && industry_class.startsWith("00",6)){
//			companyPosMustPartMessage = isSelfCompany ? "该公司的行业类别非四级行业,请重新指定!" : "该公司指定的行业类别非四级行业,请重新定位公司!";
//			success = false;
		} else if (company_type == null || "".equals(company_type) || "NA".equals(company_type)) {
			companyPosMustPartMessage = isSelfCompany ? "请指定该公司的公司类别!" : "该公司未指定公司类别,请重新定位公司!";
			success = false;
//		} else if (company_prop == null || "".equals(company_prop) || "NA".equals(company_prop)) {
//			companyPosMustPartMessage = isSelfCompany ? "请指定该公司的企业性质!" : "该公司未指定企业性质,请重新定位公司!";
//			success = false;
		}
		if(!success){
			//1. 自己管的公司则跳到[公司基本信息]修改页面
			//2. 未定位(分析师/客户经理)并且登录用户是(分析师/客户经理)角色用户则跳转到[公司概况]页面,
			//3. 其它想象则跳转到[首页]
			String mustPartForwardURL = isSelfCompany ? Constant.doURLCompanyInfoRedirect:(isNotPosR00 && isR00 ? Constant.doURLCompanyDetailRedirect:Constant.doURLUnCompanyCodeRedirect);
			request.setAttribute("mustPartForwardURL", mustPartForwardURL);
			request.setAttribute("companyPosMustPartMessage", companyPosMustPartMessage);
			if(isAutoForward){
				response.setContentType("text/html;charset=UTF-8");
				StringBuffer html = new StringBuffer();
				html.append("<html>");
				html.append("<script>");
				html.append("alert('" + companyPosMustPartMessage + "');");
				html.append("window.location.href = '" + request.getContextPath() + mustPartForwardURL + "';");
				html.append("</script>");
				html.append("</html>");
				response.getWriter().write(html.toString());
			}
		}
		return success;
	}

	/*public String getAssgin_mothod() {
		return assgin_mothod;
	}

	public void setAssgin_mothod(String assgin_mothod) {
		this.assgin_mothod = assgin_mothod;
	}*/
}