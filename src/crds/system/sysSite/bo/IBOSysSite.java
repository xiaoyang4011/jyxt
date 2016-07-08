package crds.system.sysSite.bo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import crds.system.sysSite.web.form.IPSectionForm;
import crds.system.sysSite.web.form.PointRulerForm;
import crds.system.sysSite.web.form.BaseParasForm;
import crds.system.sysSite.web.form.ResourceTypeForm;

public interface IBOSysSite {
	// 获取SourceConfiguration表数据
	public boolean getMap4SC(BaseParasForm baseParasForm) throws Exception;

	// 获取积分规则表PointRuler数据
	public boolean getMap4PR(PointRulerForm pointRulerForm) throws Exception;

	// 获取获取资源类型表数据
	public boolean getList4RT(ResourceTypeForm resourceTypeForm)
			throws Exception;

	// 修改SourceConfiguration
	public boolean updateSiteConfige(BaseParasForm baseParasForm)
			throws Exception;

	// 修改积分规则表PointRuler
	public boolean updatePointRuler(PointRulerForm pointRulerForm)
			throws Exception;

	// 修改获取资源类型
	public boolean updateResourceType(ResourceTypeForm resourceTypeForm)
			throws Exception;

	// 获取BaseParasForm接口对象的各个字段数据
	public BaseParasForm getFormBin4SourceConfig() throws Exception;

	// 获取PointRulerForm接口对象的各个字段数据
	public PointRulerForm getFormBin4Point_Ruler() throws Exception;
	//获取当前的可用IP段数据
	public List getList4IPSections() throws Exception; 
    //设置并重新获取当前合法IP段址的列表 
	public List set_get_IPSectionList(IPSectionForm IPSForm) throws Exception;
	 //删除一条IP段数据
	public boolean del_SelectIP(String delIPID) throws Exception;
	 //更新一条IP段数据
	public boolean updateIPIntoDB(IPSectionForm IPSForm) throws Exception;
	//将IP地址转化为字符串的方法
	public String convert(String ipAddress) throws Exception;
	//验证IP地址是否在给定的范围内容的方法
	public boolean isIPBetweenRange(String checkedIP,String fromIP,String toIP) throws Exception;
	//验证所给IP地址是否有效的接口方法
	public boolean valid4IPAddress(String ipAddress) throws Exception;
}