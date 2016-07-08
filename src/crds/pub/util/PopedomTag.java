package crds.pub.util;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;


/**
 * @specification :页面控件权限标签
 * @version : 1.0
 * @author : liuxx
 * @date : Oct 6, 2008 11:24:34 AM
 * @email : liuxx.adam@gmail.com
 */
public class PopedomTag extends TagSupport {
	static Logger logger = Logger.getLogger(PopedomTag.class);

	private static final long serialVersionUID = 1L;

	private String name;// 对应的实际名称
	private String on;// 对应的事件
	private String disabled;// 是否可用
	private String display;// 是否可见,赋值为style="display='none'"
	private String LINE_SEPARATOR;//系统换行符
	private StringBuffer results;//结果
	
	/**
	 * @specification :权限标签构造函数,设定属性初始值  
	 * @return :void
	 * @exception :Exception
	 */
	public PopedomTag() {

		this.name = "buto";

		this.display = "style=\"display='none'\"";

		this.LINE_SEPARATOR = System.getProperty("line.separator");

		this.results = new StringBuffer(64000);
	}
	
	/**
	 * @specification :权限标签构造函数
	 * @param :String name,名称
	 * @param :String disabled,是否可用
	 * @exception :NAN
	 */
	public PopedomTag(String name, String disabled) {
		this.name = name;

		this.disabled = disabled;
		
		this.display = "style=\"display='none'\"";

		this.LINE_SEPARATOR = System.getProperty("line.separator");

		this.results = new StringBuffer(64000);
	}
	
	/**
	 * @specification :权限标签执行体
	 * @param :NAN
	 * @return :int 1 成功标志
	 * @exception :JspException
	 */
	@SuppressWarnings("unchecked")
	public int doStartTag() throws JspException {
		this.results.setLength(0);

		HttpSession session = (HttpSession) this.pageContext.getSession();
		//需要控制权限的控件list
		List listButton = (List) session.getAttribute("listButton");

		if (listButton != null) {
			for (int i = 0; i < listButton.size(); i++) {
				Map map = (Map) listButton.get(i);
				if (map != null) {
					if(this.name.equals((String) map.get("component_id"))){//确定当前标签的权限
						this.writeLine("<input ");
						this.writeLine("name=\"" + this.name + "\" ");//控件名称
						
						this.writeLine("type=\"" + (String) map.get("component_type")
								+ "\" ");//控件类型
						
						if("button".equals(map.get("component_type"))){
							this.writeLine("class=\"" + "btn2" + "\" ");//按钮时,确定样式
						}
						this.writeLine("value=\"" + (String) map.get("component_name")
								+ "\" ");//控件值
						if(this.disabled != null && !"".equals(this.disabled)){//控件是否可用
							this.writeLine("disabled=\"" + this.disabled + "\" ");
						}
						this.writeLine(" " + this.on + " ");//控件的触发事件

						String temp = (String) map.get("is_display");
						if ("N".equals(temp)) {//控件不可见
							this.writeLine(" " + this.display + " ");
						}
						this.writeLine(" />");
					}
				}
			}
		}

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
		this.display = "false";
		this.name = "buto";

	}

	private void writeLine(String value) {
		this.results.append(value);
		this.results.append(this.LINE_SEPARATOR);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	public StringBuffer getResults() {
		return results;
	}

	public void setResults(StringBuffer results) {
		this.results = results;
	}

	public String getOn() {
		return on;
	}

	public void setOn(String on) {
		this.on = on;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}
}
