/**
 * product name :CRDS(Credit Risk Decision System)
 */
package crds.pub.util;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;


public class DataImport {
	private static Logger logger = Logger.getLogger(DataImport.class);
	/**
	 * @param request
	 * @param systemType 系统类型,B-,F-
	 * @return int -1-失败,0-找不到文件,1-成功导入
	 */
	@SuppressWarnings("unchecked")
	public static int systemDataImport(HttpServletRequest request,String systemType) {
		//系统类型检测
		if(null == systemType || systemType.trim().length()==0 ||(!(systemType = systemType.trim().toUpperCase()).equals("B") && !systemType.equals("F"))) {
			logger.error("系统类型配置错误,系统类型必须配置为:B或F.");
			return -1;
		}
		//开始解析xml文件,获取相应的数据信息
		String xmlFilePath = "..\\webapps\\system\\crds_system_menu.xml";//request.getSession().getServletContext().getRealPath("/")+"WEB-INF/config/pub/crds_system_menu.xml";
		try{
			File xmlFile = new File(xmlFilePath);
			if (xmlFile.exists()&& xmlFile.isFile()) {//文件是否存在,并且是文件
				//删除旧数据
				CommonMethod.deleteData();
				//读取文件并获取根节点
				SAXReader reader = new SAXReader();
				Document document= reader.read(xmlFile);
				Element root = document.getRootElement();
				//权限SQL插入语句列表
				List<Node> operationList = root.selectNodes("operation/insert");
				if (operationList != null && !operationList.isEmpty()) {
					for (Node node : operationList) {
						String sql = node.getText();
						if(null != sql && (sql=sql.trim()).length() > 0) {
							CommonMethod.dataImport(sql);
						}
					}
				}
				//角色对应的权限列表
				String[] roles = new String[]{"R00","R01","R02","R03","R04","R05","R06","R07","R10","R11"};
				String roleRightsPath = "roleRights/"+systemType+"/";
				String insert = "insert into system_role_operation_info (ROLE_ID, OPERATION_ID, COMPONENT_ID, IS_DISPLAY) ";
				for (int i = 0; i < roles.length; i++) {
					String rights = root.selectSingleNode(roleRightsPath+roles[i]).getText();
					if(null != rights && (rights = rights.trim()).length()!= 0){
						String[] rightsList = rights.split(",");
						for (String str : rightsList) {
							String sql = insert+" values('"+roles[i]+"','"+str+"','','')";
							CommonMethod.dataImport(sql);
						}
					}
				}
				xmlFile.delete();//删除源文件
				/*文件拷贝
				File out = new File("..\\webapps\\internal\\crds_system_menu.xml");
				try {
					if(!out.exists())//目标文件不存在则创建
						out.createNewFile();
					FileCopyUtils.copy(xmlFile, out);//将源文件拷贝到目标文件中,如果目标文件存在则会覆盖.
					xmlFile.delete();//删除源文件
				} catch (IOException e) {
					logger.info("文件拷贝错误:"+xmlFile.getPath()+", errorMessage:"+e.getMessage());
				}*/
				logger.info("已将xml文件:"+xmlFilePath+"的数据更新到数据库中.");
				return 1;
			} else {
				return 0;
			}
		} catch (DocumentException de){
			logger.error("xml文件读取错误:"+xmlFilePath);
			return -1;
		}
	}
}