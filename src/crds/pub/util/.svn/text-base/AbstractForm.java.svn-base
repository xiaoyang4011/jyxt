/**
 * product name :CRDS(Credit Risk Decision System)
 */
package crds.pub.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * @specification :分页Form,实现分页功能的Form必须继承于此Form
 * @version : 1.0
 * @auther : LuGuohui
 * @date : Aug 20, 2008 1:58:25 PM
 * @email : luguohui99@gmail.com
 * 2012年2月23donglin增加pageCount;//页数
 */
public class AbstractForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String action;//分页Action
	private int pageNo;//当前页数
	private int rowsCount;//总的记录数�
	private int rowsPerPage;//每页的记录数
	private int pageCount;//页数
	
	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public AbstractForm() {
		this.pageNo = 1;
		this.rowsCount = 0;
		this.rowsPerPage = 0;
		this.pageCount = 0;
	}

	public int getStartPosition() {
		this.pageNo=this.pageNo==0?1:this.pageNo;
		return ((this.pageNo - 1) * this.rowsPerPage)+1;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getAction() {
		return this.action;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageNo() {
		return this.pageNo;
	}

	public void setRowsCount(int rowsCount) {
		this.rowsCount = rowsCount;
		//int tt=rowsCount/rowsPerPage;
		//int cc=rowsCount%rowsPerPage;
		if(rowsPerPage!= 0)
		{
			this.pageCount=rowsCount/rowsPerPage+((rowsCount%rowsPerPage)==0?0:1);
		}
		else
		{
			this.pageCount=0;
		}
	}

	public int getRowsCount() {
		return this.rowsCount;
	}

	public void setRowsPerPage(int rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
	}

	public int getRowsPerPage() {
		return this.rowsPerPage;
	}

	public ActionErrors validate(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		return null;
	}

	public void reset(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		this.action = "";
		this.pageNo = 1;
		this.rowsCount = 0;
		this.rowsPerPage = 0;		
	}

}