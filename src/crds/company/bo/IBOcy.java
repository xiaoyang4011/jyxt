package crds.company.bo;

import java.util.List;

import crds.company.web.form.Formcy;

public interface IBOcy {
	public int Add_cy(Formcy form) throws Exception;
	@SuppressWarnings("unchecked")
	public List Bro_cy(Formcy form) throws Exception;
	public int Update_cy(Formcy form) throws Exception;
	@SuppressWarnings("unchecked")
	public List Jump_cy(Formcy form) throws Exception;
	public int Del_cy(Formcy form) throws Exception;
}
