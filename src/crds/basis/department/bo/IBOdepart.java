package crds.basis.department.bo;

import java.util.List;

import crds.basis.department.web.FormDepart;

public interface IBOdepart {
	@SuppressWarnings("unchecked")
	public List DepartmentInfo() throws Exception;
	public boolean addDepart(FormDepart form)throws Exception;
	public boolean checkExists(FormDepart form) throws Exception;
	@SuppressWarnings("unchecked")
	public List updateJump(FormDepart form) throws Exception;
	public boolean updatedepart(FormDepart form) throws Exception;
	public boolean delete(FormDepart form) throws Exception;
}
