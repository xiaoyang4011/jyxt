package crds.pub.crdsService;
import org.apache.log4j.Logger;
import org.springframework.remoting.jaxrpc.ServletEndpointSupport;
public class CrdsService extends ServletEndpointSupport implements ICrdsService{
	static Logger logger = Logger.getLogger(CrdsService.class);
	  public String hello(String name)
	  {
		logger.info("webServiceCall!"+name);
	    String xml="test";
//	    IMYDAO myDAO=(IMYDAO)getApplicationContext().getBean("myDAO");
//	    List list=myDAO.query();
//	    Iterator it = list.iterator();
//	    logger.info(list.size());
//	try{
//	      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//	      DocumentBuilder db = dbf.newDocumentBuilder();
//	      Document doc=db.newDocument();
//
//	      Element users=doc.createElement("users");
//	      users.setAttribute("content","user");
//
//	      while (it.hasNext()) {
//	        Map userMap = (Map) it.next();
//	        Element user=doc.createElement("user");
//	        Element username=doc.createElement("username");
//	        username.appendChild(doc.createTextNode((String)userMap.get("USERNAME")));
//	        Element password=doc.createElement("password");
//	        password.appendChild(doc.createTextNode((String)userMap.get("PASSWORD")));
//	        user.appendChild(username);
//	        user.appendChild(password);
//	        users.appendChild(user);
//	      }
//	      doc.appendChild(users);
//	      TransformerFactory tff= TransformerFactory.newInstance();
//	      Transformer tf= tff.newTransformer();
//	      tf.setOutputProperty("encoding","gb2312");
//	      ByteArrayOutputStream bos = new ByteArrayOutputStream();
//	      tf.transform(new DOMSource(doc), new StreamResult(bos));
//
//	      xml=bos.toString();
//	      logger.info("service="+xml);
//
//	    }catch(Exception e){}
	    return xml;

	  }

	}