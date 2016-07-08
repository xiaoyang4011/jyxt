package crds.statistics.bo;

import java.util.List;

import crds.statistics.web.form.FormSta;

public interface IBOSta {
	@SuppressWarnings("unchecked")
	public List cy_Bro(FormSta form) throws Exception;
	@SuppressWarnings("unchecked")
	public List cy_spec(FormSta form) throws Exception;
}
