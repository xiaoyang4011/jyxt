package crds.statistics.dao;

import java.util.List;

import crds.statistics.web.form.FormSta;

public interface IDAOSta {
	@SuppressWarnings("unchecked")
	public List cy_Bro(FormSta form) throws Exception;
	@SuppressWarnings("unchecked")
	public List cy_sepc(FormSta form) throws Exception;
}
