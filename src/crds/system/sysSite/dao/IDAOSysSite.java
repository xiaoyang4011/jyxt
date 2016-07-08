package crds.system.sysSite.dao;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import crds.system.sysSite.web.form.PointRulerForm;
import crds.system.sysSite.web.form.BaseParasForm;
import crds.system.sysSite.web.form.ResourceTypeForm;

public interface IDAOSysSite {
	public Map<String, Object> getMap4SC() throws Exception; 
	public Map<String,Object>  getMap4PR() throws Exception;
	public List getList4SC() throws Exception;
	public List getList4PR() throws Exception;
	public boolean updateConfige(String sql,List list) throws Exception;
	public boolean insertConfige(String sql,List list) throws Exception;
	public List getList4RT() throws Exception;
	public boolean updateResourceType(ArrayList<String> list) throws Exception;
	 //获取当前的可用IP段数据
	public List getList4IPSections() throws Exception;
	//将IPSectionForm中的IP段址追加到数据库
	public boolean insertIPIntoTable(String sql4From,String sql4To) throws Exception;
	//删除一条IP段数据
	public int del_SelectIP(String delIPID) throws Exception;
	//获取IP段数据
	public String get_CurIPRange(String fromIP,String toIP) throws Exception;
	//修改IP段数据
	public boolean updateIPSection(String sql,List list) throws Exception;
}
