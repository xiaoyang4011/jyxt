package crds.zhaosheng.bo;

import java.util.List;
import crds.zhaosheng.web.form.FormZs;

public interface IBOzs {
	@SuppressWarnings("unchecked")
	public List Bro_stud(FormZs form) throws Exception;
	@SuppressWarnings("unchecked")
	public List Bro_cy(FormZs form) throws Exception;
	public int Update_jiuye(FormZs form) throws Exception;
	public int del_jiuye(FormZs form) throws Exception;

}
