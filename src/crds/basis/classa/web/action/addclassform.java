package crds.basis.classa.web.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class addclassform extends ActionForm{
	private static final long serialVersionUID = 1L;
	private String action;//分页Action
	private int pageNo;//当前页数
	private int rowsCount;//总的记录数�
	private int rowsPerPage;//每页的记录数
	
	
	public  addclassform() {
		this.pageNo = 1;
		this.rowsCount = 0;
		this.rowsPerPage = 0;
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
