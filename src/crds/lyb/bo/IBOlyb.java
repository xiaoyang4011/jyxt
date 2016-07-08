package crds.lyb.bo;

import java.util.List;
import crds.lyb.web.form.Formlyb;


public interface IBOlyb {
	@SuppressWarnings("unchecked")
	public int Add(Formlyb form) throws Exception;
	@SuppressWarnings("unchecked")
	public List Bro(Formlyb form) throws Exception;
	
	@SuppressWarnings("unchecked")
	public int Update(Formlyb form) throws Exception;
	public int Del(Formlyb form) throws Exception;
	@SuppressWarnings("unchecked")
	public List Jump(Formlyb form) throws Exception;
	public List Bro1(Formlyb form) throws Exception;
	@SuppressWarnings("unchecked")
	public List zxdt(Formlyb form) throws Exception;
	@SuppressWarnings("unchecked")
	public List xnzph(Formlyb form) throws Exception;
	@SuppressWarnings("unchecked")
	public List xwzph(Formlyb form) throws Exception;
	@SuppressWarnings("unchecked")
	public List jyzc(Formlyb form) throws Exception;
	@SuppressWarnings("unchecked")
	public List jyzd(Formlyb form) throws Exception;
}