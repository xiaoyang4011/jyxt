package crds.company.dao;

import java.util.List;

import crds.company.web.form.Formcy;

public interface IDAOcy {
	public int Add_cy(Formcy form) throws Exception;
	@SuppressWarnings("unchecked")
	public List Bro_cy(Formcy form) throws Exception;
	public int Update_cy(Formcy form) throws Exception;
	@SuppressWarnings("unchecked")
	public List Jump_cy(Formcy form) throws Exception;
	public int Del_cy(Formcy form) throws Exception;
}
