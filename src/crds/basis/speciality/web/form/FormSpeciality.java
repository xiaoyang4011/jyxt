/**
 * product name :media
 */
package crds.basis.speciality.web.form;

import java.awt.image.BufferedImage;
import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;

import crds.pub.util.AbstractForm;

/**
 * 
 * @specification :趋势分析Form
 * @version : 1.0
 * @author : maogf
 * @date : May 11, 2009 5:20:05 PM
 * @email : maogenfeng@gmail.com
 */
public final class FormSpeciality extends AbstractForm {

	/**
	 * @field : serialVersionUID
	 * @fieldType : long
	 */
	private static final long serialVersionUID = 1L;
	
	
	 
	String dep_code;
	String dep_name;
	
	 String spec_code;
	 String spec_name;
	 String spec_state;
	 String spec_description;
	 String flag;
	String showtype;

	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getSpec_code() {
		return spec_code;
	}
	public void setSpec_code(String specCode) {
		spec_code = filter(specCode);
	}
	public String getSpec_name() {
		return spec_name;
	}
	public void setSpec_name(String specName) {
		spec_name = filter(specName);
	}
	public String getSpec_state() {
		return spec_state;
	}
	public void setSpec_state(String specState) {
		spec_state =  filter(specState);
	}
	public String getSpec_description() {
		return spec_description;
	}
	public void setSpec_description(String specDescription) {
		spec_description =  filter(specDescription);
	}
	public String getDep_code() {
		return dep_code;
	}
	public void setDep_code(String depCode) {
		dep_code =  filter(depCode);
	}
	

	public String getDep_name() {
		return dep_name;
	}
	public void setDep_name(String depName) {
		dep_name =  filter(depName);
	}

	public String getShowtype() {
		return showtype;
	}
	public void setShowtype(String showtype) {
		this.showtype = showtype;
	}
	//过滤对乱码转换成中文
	public String filter(String word)
	{
		String newWord=null;
		try {
			newWord = new String(word.getBytes("UTF-8"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newWord;
	}
	
}
