/**
 * product name :CRDS(Credit Risk Decision System)
 */
package crds.pub.util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

/**
 * @specification :分页标签
 * @version : 1.0
 * @auther : LuGuohui
 * @date : Aug 20, 2008 2:03:22 PM
 * @email : luguohui99@gmail.com
 */
public class NavigateTag extends TagSupport {
	static Logger	logger	= Logger.getLogger(NavigateTag.class);	
	private static final long serialVersionUID = 1L;
	protected String page;//保留属性
	public static final String MODE_LONG = "long";//分页的模式:long
	public static final String MODE_SHORT = "short";//分页的模式:short
	private String name;//分页的名称
	private String objectName;//分页的Form名称
	private String iFrameName = "";//分页的Iframe名称
	private String mode;//分页的模式:long和short
	private String LINE_SEPARATOR;//行分隔符
	private StringBuffer results;//输出结果

	public NavigateTag() {
		this.page = null;

		this.name = "fm";

		this.objectName = "fm";

		this.mode = "long";

		this.LINE_SEPARATOR = System.getProperty("line.separator");

		this.results = new StringBuffer(64000);
	}
	
	/**
	 * @specification :分页标签构造函数
	 * @param :String name Form名称
	 * @return :String objectName Form设置的对象名称
	 * @exception :NAN
	 */
	public NavigateTag(String name,String objectName) {
		this.page = null;

		this.name = name;

		this.objectName = objectName;

		this.mode = "long";

		this.LINE_SEPARATOR = System.getProperty("line.separator");

		this.results = new StringBuffer(64000);
	}

	/**
	 * @tip : @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
	 * @specification :分页标签执行体
	 * @param :NAN
	 * @return :int 1 成功标志
	 * @exception :JspException
	 */
	public int doStartTag() throws JspException {
		if (this.mode == null) {
			this.mode = "long";
		} else if (!(this.mode.equalsIgnoreCase("short")))
			this.mode = "long";
        //获得分页Form对象
		HttpServletRequest request = (HttpServletRequest) this.pageContext
				.getRequest();
		String objectName = getObjectName();
		AbstractForm abstractForm = null;
		if(request.getAttribute(objectName) == null){
			abstractForm = new AbstractForm();
		}else{
			abstractForm = (AbstractForm) request.getAttribute(objectName);
		}
		//初始化分页属性
		int rowsCount = abstractForm.getRowsCount();		
		int firstRow = 0;
		int lastRow = 0;
		int currentPage = 0;
		int pagesCount = 0;
		int tempCount = rowsCount;
		if (rowsCount > 0) {
			currentPage = abstractForm.getPageNo();
			if (currentPage == 0)
				currentPage = 1;
		}

		while (tempCount > 0) {
			tempCount = tempCount - abstractForm.getRowsPerPage();
			++pagesCount;
		}
		if (currentPage > 0)
			firstRow = abstractForm.getRowsPerPage() * (currentPage - 1) + 1;

		if (currentPage < pagesCount) {
			lastRow = firstRow + abstractForm.getRowsPerPage() - 1;
		} else
			lastRow = abstractForm.getRowsCount();
        //分页输出结果
		this.results.setLength(0);
		writeLine("    <table width=\"100%\" border=0 cellspacing=0 cellpadding=0>");
		writeLine("        <tr valign=\"bottom\">");
		if (this.mode.equalsIgnoreCase("long")) {
			writeLine("          <td width=\"38%\" align=\"right\">");
		} else
			writeLine("          <td width=\"38%\" align=\"right\">");

		writeLine("&nbsp;"
				+ "共"
				+ "&nbsp;"
				+ rowsCount
				+ "条,"
				+ "每页"
				+ "<input type='text' name=setRowsPerPage style='width=20px; border:1px;border-bottom-style:none;border-top-style:none;border-left-style:none;border-right-style:none;' size='2' value='"
				+ abstractForm.getRowsPerPage()
				+ "' onchange=\"return locateForRowsPerPage(this.value,'"+iFrameName+"');\">"+"条");
		int startPosition = rowsCount == 0?0:abstractForm.getStartPosition();
		if (this.mode.equalsIgnoreCase("long")) {
			writeLine("," + "列表" + "&nbsp;"
					+startPosition + "&nbsp;" + "至"
					+ "&nbsp;" + lastRow + ".");
		}

		writeLine("            <input type=hidden name=pagesCount value="
				+ pagesCount + ">");
		writeLine("          </td>");
		writeLine("          <td align=\"center\" width=\"8%\" > ");
		if (currentPage > 1) {
			writeLine("             <a href=# alt='" + "首页"
					+ "' onclick=\"return locate(1,1,'"+iFrameName+"');\">" + "首页" + "</a>");
		} else {
			writeLine("             " + "首页" + "");
		}

		writeLine("          </td>");
		writeLine("          <td align=\"center\" width=\"8%\" >");
		if (currentPage > 1) {
			writeLine("             <a href=# alt='" + "上页"
					+ "' onclick=\"return locate(" + (currentPage - 1) + ",1,'"+iFrameName+"')\">"
					+ "上页" + "</a>");
		} else {
			writeLine("             " + "上页" + "");
		}

		writeLine("          </td>");
		writeLine("          <td align=\"center\" width=\"8%\" >");
		if (currentPage < pagesCount) {
			writeLine("             <a href=# alt='" + "下页"
					+ "' onclick=\"return locate(" + (currentPage + 1) + ",1,'"+iFrameName+"')\">"
					+ "下页" + "</a>");
		} else {
			writeLine("             " + "下页" + "");
		}

		writeLine("          </td>");
		writeLine("          <td align=\"center\" width=\"8%\" >");
		if (currentPage < pagesCount) {
			writeLine("             <a href=# alt='" + "末页"
					+ "' onclick=\"return locate(" + pagesCount + ",1,'"+iFrameName+"')\">" + "末页"
					+ "</a>");
		} else {
			writeLine("             " + "末页" + "");
		}

		writeLine("          </td>");
		if (this.mode.equalsIgnoreCase("long")) {
			writeLine("          <td width=\"30%\" align=\"left\">");
		} else
			writeLine("          <td width=\"30%\" align=\"left\">");

		writeLine("             " + currentPage + "/" + pagesCount + "&nbsp;"
				+ "页" + "");

		if (this.mode.equalsIgnoreCase("long")) {
			writeLine("&nbsp;"
					+ "转向第"
					+ "<input type='text' name=newPageNo style='width=20px; border:1px;border-bottom-style:none;border-top-style:none;border-left-style:none;border-right-style:none;' size='1' onchange=\"return locateTo(this.value"+","+pagesCount+",'"+iFrameName+"')\" value='"+currentPage+"'>"
					+ "页" + "");

			String path = request.getContextPath() + "/pub/images";
			path = StringUtils.replace(path, "//", "/");
			writeLine("             <img src='"
					+ path
					+ "/btnGo.gif' align='middle' style='cursor:hand' border='0' alt='"
					+ "转向" + "' onclick=\"return locateTo(window.document.forms[0].newPageNo.value"+","+pagesCount+",'"+iFrameName+"')\">");

			writeLine("          </td>");
		}
		writeLine("        </tr>");
		writeLine("   </table>");

		JspWriter writer = this.pageContext.getOut();
		try {
			writer.print(this.results.toString());
		} catch (IOException e) {
			throw new JspException(e.toString());
		}

		return 1;
	}

	public int doEndTag() throws JspException {
		return 6;
	}

	public void release() {
		super.release();
		this.page = null;
		this.mode = "long";
		this.name = "fm";
		this.objectName = "fm";
	}

	public String getName() {
		return this.name;
	}

	public String getObjectName() {
		return this.objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}
	public String getIFrameName() {
		return iFrameName;
	}
	public void setIFrameName(String frameName) {
		iFrameName = frameName;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPage() {
		return this.page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	private void writeLine(String value) {
		this.results.append(value);
		this.results.append(this.LINE_SEPARATOR);
	}

	public String getMode() {
		return this.mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}
}