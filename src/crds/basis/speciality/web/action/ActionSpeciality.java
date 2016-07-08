package crds.basis.speciality.web.action;

import java.util.List;
import java.awt.image.BufferedImage;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crds.basis.speciality.bo.IBOSpeciality;
import crds.basis.speciality.web.form.FormSpeciality;
import crds.stud.bo.IBOStud;
import crds.stud.web.form.FormStud;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.taglibs.standard.lang.jstl.Logger;
import org.springframework.web.struts.MappingDispatchActionSupport;



public final class ActionSpeciality extends MappingDispatchActionSupport {
	
	private Map<String, BufferedImage> map = null;
	private LinkedHashMap<String, LinkedHashMap<String, String>> map_data=null;
	

	
	public IBOSpeciality getBO() {
		return (IBOSpeciality) this.getWebApplicationContext().getBean(
		"BOSpeciality");
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward queryTest(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
//		 List list=getBO().queryTest();
//		 request.setAttribute("company", list);
		return mapping.findForward("succ");
	}
	//专业管理
	@SuppressWarnings("unchecked")
	public ActionForward specialityadmin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
	return mapping.findForward("succ");
	}
	
	//到专业添加页面
	public ActionForward speciality_add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("spec_add");
		}
	//到系部的专业添加页面
	public ActionForward speciality_add_dep(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("spec_add");
		}
	
	//专业信息添加页面
	public ActionForward specialityadd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	    FormSpeciality formspeciality=(FormSpeciality)form;	
	    ActionForward forward=null;
	
		try{
			//保存到数据库中
			formspeciality =getBO().specialityadd(formspeciality);
			if (formspeciality.getFlag() == null ) {
				 request.setAttribute("message","专业代码已存在，请重新录入!");
				 forward= mapping.findForward("error");//返回添加专业页
				}
			else {
				request.setAttribute("message","操作成功!");
				String depcode=formspeciality.getDep_code();
				request.setAttribute("dep_code",depcode);
				formspeciality.setDep_code(depcode);
				forward= mapping.findForward("succ");//到专业列表页
			}			
		}catch(Exception e){
			request.setAttribute("message","保存失败,系统发生异常!");
			  forward= mapping.findForward("error");//返回添加专业页
			}		
		 return forward;	
	 }
	
	
	//从专业添加页面返回到专业列表页
	public ActionForward addreturnlist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("addreturnlist");
		}
	
	//到专业信息修改页面
	public ActionForward updateSpeciality(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	  	FormSpeciality formspeciality=(FormSpeciality)form;
		// 设置每页行数
		//String depcode=formspeciality.getDep_code();
	 	//formspeciality.setDep_code(depcode); 
		List  list = null;		  
	     list=getBO().speciality_query1(formspeciality); 	   		
		 request.setAttribute("form", formspeciality);
		 request.setAttribute("specialitylist1", list);	 		 		
		return mapping.findForward("spec_update");
		}
	//到专业信息修改页面
	public ActionForward updateSpeciality_dep(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	  	FormSpeciality formspeciality=(FormSpeciality)form;
		// 设置每页行数
		//String depcode=formspeciality.getDep_code();
	 	//formspeciality.setDep_code(depcode); 
		List  list = null;		  
	     list=getBO().speciality_query1(formspeciality); 	   		
		 request.setAttribute("form", formspeciality);
		 request.setAttribute("specialitylist1", list);	 		 		
		return mapping.findForward("spec_update");
		}
	
	//专业信息修改
	public ActionForward specialityupdatedo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	 
		FormSpeciality formspeciality=(FormSpeciality)form;		
	   String depcode=formspeciality.getDep_code();
	   formspeciality.setDep_code(depcode);
	   int n=getBO().specialityupdatedo(formspeciality);
	 	if(n>0){
		 request.setAttribute("editsuccess", "success"); //标记是否是已经修改成功的页面	
		 request.setAttribute("message","操作成功!");
		 return mapping.findForward("updatedo");
		 }
		 else
		 {
		  request.setAttribute("message","修改专业信息失败！");
	      request.setAttribute("error","修改专业信息失败！");
          return mapping.findForward("error");		//转到错误提示页面
		 }
	}
	
	//专业信息删除
	public ActionForward deleteSpeciality(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	 
		FormSpeciality formspeciality=(FormSpeciality)form;		
	    String depcode=formspeciality.getDep_code();
		//String speccode=request.getParameter("spec_code");
		String speccode=formspeciality.getSpec_code();
	    formspeciality.setDep_code(depcode);
	    formspeciality.setSpec_code(speccode);
	    
	    try{
			//从数据库中删除所选专业
			formspeciality =getBO().specialitydelete(formspeciality);
			if (formspeciality.getFlag() == "0") {
				 request.setAttribute("message","该专业下有资源存在，请先删除该专业下的信息!");
				 
				 return mapping.findForward("deletefail");
				}
			else {
				//request.setAttribute("message","操作成功!");
				return mapping.findForward("delete");
			}			
		}catch(Exception e){
			request.setAttribute("message","删除失败,系统发生异常!");
			return mapping.findForward("deletefail");
			}	
		
		
	   //int n=getBO().specialitydelete(formspeciality);
		//if(n>0){
		//request.setAttribute("deletesuccess", "success"); //标记是否是已经删除成功的页面		
		 //return mapping.findForward("delete");
		//}
		//else
		//{
	  // request.setAttribute("error","删除专业信息失败！");
        // return mapping.findForward("error");		//转到错误提示页面
	//	}
	}
	
	
	//从修改专业信息返回到专业列表页面
	public ActionForward 	spec_list2(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("spec_list2");
		}
	 
	//到专业列表页面
	public ActionForward 	spec_list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("spec_list");
		}
	 
	//从添加专业返回到专业列表页面
	public ActionForward 	spec_list1(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("spec_list1");
		}
	 
 
	//专业查询
	@SuppressWarnings("unchecked")
	public ActionForward speciality_query(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		FormSpeciality formspeciality=(FormSpeciality)form;
		// 设置每页行数
	 
		if (formspeciality.getRowsPerPage() == 0) {
			formspeciality.setRowsPerPage(12);
		}	 
		List  list = null;		 
		//判断是到列表页面还是查询页面		
		if("1".equals(request.getParameter("listpage"))){
			// 执行查询列表
			 list=getBO().speciality_query(formspeciality); 		
		}
		else {
			 list=getBO().speciality_queryall(formspeciality);//查询全部专业信息
		}
	
		
		request.setAttribute("specialitylist", list);
		request.setAttribute("queryroleform", formspeciality);
		return mapping.findForward("succ");			
	}
	
	//部门的专业查询
	@SuppressWarnings("unchecked")
	public ActionForward speciality_query_dep(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FormSpeciality formspeciality=(FormSpeciality)form;
		// 设置每页行数	 
		if (formspeciality.getRowsPerPage() == 0) {
			formspeciality.setRowsPerPage(12);
		}	 
		List  list = null;
		//执行查询列表
		list=getBO().speciality_query(formspeciality); 	
		request.setAttribute("specialitylist", list);
		request.setAttribute("queryroleform", formspeciality);
		return mapping.findForward("succ");			
	}
	
	//专业查询2
	@SuppressWarnings("unchecked")
	public ActionForward speciality_query2(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		FormSpeciality formspeciality=(FormSpeciality)form;
		// 设置每页行数
	  	// String depcode=formspeciality.getDep_code();
		  //String depcode=request.getParameter("dep_code");
	   // formspeciality.setDep_code(depcode);
		if (formspeciality.getRowsPerPage() == 0) {
			formspeciality.setRowsPerPage(10);
		}
	 
		List  list = null;
		//判断是到列表页面还是查询页面
		//if("1".equals(request.getParameter("listpage"))){
			// 执行查询列表
		list=getBO().speciality_query(formspeciality); 		
		//}
	//	else {
			// list=getBO().speciality_queryall(formspeciality);//查询全部专业信息
		//}	
		request.setAttribute("form", formspeciality);
		request.setAttribute("specialitylist", list);
		return mapping.findForward("succ");			
	}
	
	
	
	//部门查询
	@SuppressWarnings("unchecked")
	public ActionForward depart_query(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FormSpeciality formspeciality=(FormSpeciality)form;
		// 设置每页行数
		
		if (formspeciality.getRowsPerPage() == 0) {
			formspeciality.setRowsPerPage(10);
		}
	 List  list = null;
	 list=getBO().depart_query();
	 request.getSession().setAttribute("departlist", list);
	request.setAttribute("departlist", list);
	return mapping.findForward("succ");
	}
	
	
	//部门中专业
	@SuppressWarnings("unchecked")
	public ActionForward depart_spec(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FormSpeciality formspeciality=(FormSpeciality)form;
		// 设置每页行数
		String dep_code=formspeciality.getDep_code();
		if (formspeciality.getRowsPerPage() == 0) {
			formspeciality.setRowsPerPage(10);
		}
	 List  list = null;
	 list=getBO().depart_query();
	 request.getSession().setAttribute("departlist", list);
	request.setAttribute("departlist", list);
	return mapping.findForward("succ");
	}
	
	
	//查询指定部门下的 专业信息
	@SuppressWarnings("unchecked")
	public ActionForward dep_spec_query(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		FormSpeciality formspeciality=(FormSpeciality)form;
		// 设置每页行数
	  	//String depcode=formspeciality.getDep_code();
		//String depcode=request.getParameter("dep_code");
		//formspeciality.setDep_code(depcode);
		if (formspeciality.getRowsPerPage() == 0) {
			formspeciality.setRowsPerPage(10);
		}
	 
		List  list = null;		
		list=getBO().dep_spec_query(formspeciality); 		
	//	request.setAttribute("form", formspeciality);
		request.setAttribute("specialitylist", list);
		return mapping.findForward("succ");			
	}
	
	//到 添加指定部门的专业的页面
	@SuppressWarnings("unchecked")
	public ActionForward dep_spec_add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
	return mapping.findForward("succ");
	}
	//指定部门的专业信息添加
	public ActionForward dep_spec_add_do(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	    FormSpeciality formspeciality=(FormSpeciality)form;		
		try{
			//保存到数据库中
			formspeciality =getBO().specialityadd(formspeciality);
			if (formspeciality.getFlag() == null ) {
				 request.setAttribute("message","专业代码已存在，请重新录入!");
				}
			else {
				request.setAttribute("message","操作成功!");
			}			
		}catch(Exception e){
			request.setAttribute("message","保存失败,系统发生异常!");
			}		
		// int n=getBO().specialityadd(formspeciality);
		// if(n>0){
		// request.setAttribute("addsuccess", "success"); //标记是否是已经添加成功的页面
		// }
		 return mapping.findForward("add");
		// if(n>0){
		//	 request.setAttribute("addsuccess", "success"); //标记是否是已经修改成功的页面		
		//  return mapping.findForward("addsucc");
		//  }
			//  else
			//  {
		     // request.setAttribute("error","添加专业信息失败！");
	         // return mapping.findForward("error");		//转到错误提示页面
			// }		
	 }
	
	//从部门专业列表页到专业修改页
	public ActionForward dep_spec_update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	  FormSpeciality formspeciality=(FormSpeciality)form;
		// 设置每页行数
		//String depcode=formspeciality.getDep_code();
	 	//formspeciality.setDep_code(depcode); 
		List  list = null;		  
	     list=getBO().speciality_query1(formspeciality); 	   		
		 request.setAttribute("form", formspeciality);
		 request.setAttribute("specialitylist1", list);			 		
		return mapping.findForward("succ");
		}
	
	//部门的 专业信息修改
	public ActionForward dep_spec_update_do(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	 
		FormSpeciality formspeciality=(FormSpeciality)form;		
	   String depcode=formspeciality.getDep_code();
	   formspeciality.setDep_code(depcode);
	   int n=getBO().specialityupdatedo(formspeciality);
	 	if(n>0){
		 request.setAttribute("editsuccess", "success"); //标记是否是已经修改成功的页面		
		 return mapping.findForward("updatedo");
		 }
		 else
		 {
	      request.setAttribute("error","修改专业信息失败！");
          return mapping.findForward("error");		//转到错误提示页面
		 }
	}
	
	//专业信息删除
	public ActionForward dep_spec_delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	 
		FormSpeciality formspeciality=(FormSpeciality)form;		
	    String depcode=formspeciality.getDep_code();
		String speccode=request.getParameter("spec_code");
	    formspeciality.setDep_code(depcode);
	    formspeciality.setSpec_code(speccode);	    
	    try{
			//从数据库中删除所选专业
			formspeciality =getBO().specialitydelete(formspeciality);
			if (formspeciality.getFlag() == null ) {
				 request.setAttribute("message","该专业下有资源存在，请先删除该专业的下级信息!");
				 
				 return mapping.findForward("deletefail");
				}
			else {
				//request.setAttribute("message","操作成功!");
				return mapping.findForward("deletesucc");
			}			
		}catch(Exception e){
			request.setAttribute("message","删除失败,系统发生异常!");
			return mapping.findForward("deletefail");
			}
	}
}