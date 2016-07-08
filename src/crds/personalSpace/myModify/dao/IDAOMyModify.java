package crds.personalSpace.myModify.dao;

import java.util.List;

import crds.personalSpace.myModify.web.form.FormMyModify;
public interface IDAOMyModify {
	@SuppressWarnings("unchecked")
	public List myLoad(FormMyModify form1)throws Exception;
	@SuppressWarnings("unchecked")
	public List myLoad1(FormMyModify form1)throws Exception;

}
