package crds.basis.dwr.dao;

import java.util.List;

public interface IDAOSublibThreeLevel {
	
	public List  findDepartment() throws Exception;
	public List findSpeciality(String dep_code ) throws Exception;

}
