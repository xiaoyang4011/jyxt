package crds.basis.classa.web.action;

import java.awt.image.BufferedImage;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.struts.MappingDispatchActionSupport;

import crds.basis.classa.bo.IBOClass;
import crds.basis.classa.web.form.AddClassForm;


public class ActionClass extends MappingDispatchActionSupport {
	private Map<String, BufferedImage> map = null;
	private LinkedHashMap<String, LinkedHashMap<String, String>> map_data = null;

	public IBOClass getBO() {
		return (IBOClass) this.getWebApplicationContext().getBean("BOClass");
	}

	public ActionForward Class(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// System.out.println("----------------------------------------------------------------");
		AddClassForm form1 = (AddClassForm) form;
		// 设置每页行数

		if (form1.getRowsPerPage() == 0) {
			form1.setRowsPerPage(20);
		}
		List list = getBO().Class(form1);

		// 判断是到列表页面还是查询页面

		request.setAttribute("formClass", form1);
		request.setAttribute("student", list);
		return mapping.findForward("succ");
	}

	public ActionForward addClass(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("succ");
	}
//添加班级
	@SuppressWarnings("unchecked")
	public ActionForward addClassForm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
        
		AddClassForm addclassform = (AddClassForm) form;
		ActionForward forward = null;
		try{
			//保存到数据库中
		if(getBO().checkform(addclassform)){
			//编号存在
			 request.setAttribute("message","班级代码已存在，请重新录入!");
			 forward= mapping.findForward("ansucc");//返回添加班级页
		}
		else {
		
			boolean list = getBO().getaddClassForm(addclassform);
			request.setAttribute("message","操作成功!");
			request.setAttribute("student", list);
			forward = mapping.findForward("succ");//到班级列表		
		}
		}catch(Exception e){
			request.setAttribute("message","保存失败,系统发生异常!");
			  forward= mapping.findForward("ansucc");//返回添加专业页
			}		
		 return forward;
		
	
	}

	
	
	
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AddClassForm addClassFrom = (AddClassForm) form;

		// 设置每页行数

		if (addClassFrom.getRowsPerPage() == 0) {
			addClassFrom.setRowsPerPage(20);
		}

		addClassFrom.getClass_code();
		boolean boo = getBO().delete(addClassFrom);
		List list = getBO().Class(addClassFrom);
		request.setAttribute("student", list);
		return mapping.findForward("succ");
	}

	public ActionForward ClassInfo1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AddClassForm addClassFrom = (AddClassForm) form;
		// 设置每页行数
		if (addClassFrom.getRowsPerPage() == 0) {
			addClassFrom.setRowsPerPage(20);
		}

		List list = null;
		list = getBO().ClassInfo();
		request.getSession().setAttribute("Classlist", list);
		return mapping.findForward("succ");
	}

	public ActionForward updateCourse(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AddClassForm From = (AddClassForm) form;

		// 设置每页行数

		if (From.getRowsPerPage() == 0) {
			From.setRowsPerPage(20);
		}

		List list = getBO().updateClass(From);
		request.setAttribute("student", list);
		return mapping.findForward("update");
	}

	// 跳转到修改页面
	public ActionForward updateClassdojsp(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return mapping.findForward("update");
	}

	public ActionForward updateClassdo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AddClassForm From = (AddClassForm) form;

		// 设置每页行数

		if (From.getRowsPerPage() == 0) {
			From.setRowsPerPage(20);
		}

		boolean bool = getBO().updateInfo(From);
		if (bool) {
			AddClassForm form1 = (AddClassForm) form;
			// 设置每页行数

			if (form1.getRowsPerPage() == 0) {
				form1.setRowsPerPage(20);
			}
			List list = null;
            // 执行查询列表
			list = getBO().Class(form1);
			//查询全部专业信息
			
			request.setAttribute("AddClassForm", form1);
			request.setAttribute("student", list);

			return mapping.findForward("update");
		} else {
			return mapping.findForward("succ");
		}
	}
	//课程信息列表
	//批量删除用户
	public ActionForward deleteAll1(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String str=request.getParameter("strinfo");
		//System.out.print("测试"+str);
		 boolean deleteinfo=getBO().getDeleteMore(str);
	     if(deleteinfo){
	       request.setAttribute("message", "批量删除成功");
	     }else{
	    //	 request.setAttribute("message", "批量删除失败");
	     }
	     return mapping.findForward("deleteall");
	}

}
