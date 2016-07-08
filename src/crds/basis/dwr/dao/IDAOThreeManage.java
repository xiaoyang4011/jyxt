package crds.basis.dwr.dao;

import java.util.List;

public interface IDAOThreeManage {
    @SuppressWarnings("unchecked")
	public List  findDepartment() throws Exception;
    @SuppressWarnings("unchecked")
	public List findSpeciality(String dep_code ) throws Exception;
    @SuppressWarnings("unchecked")
	public List findClass(String specCode) throws Exception ;

}
