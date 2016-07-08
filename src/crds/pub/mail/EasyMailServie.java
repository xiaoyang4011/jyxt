package crds.pub.mail;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.mail.javamail.JavaMailSenderImpl;
/***
 * 
 * @specification :用来发送 mail 的 service
 * @version : 1.0
 * @author : maoxk
 * @date : 2009-7-9 上午09:33:10
 * @email : maoxk1984@gmail.com
 */
public class EasyMailServie {
	static Logger logger = Logger.getLogger(EasyMailServie.class);

	// 注入MailSender
	private JavaMailSenderImpl javaMailSender;
	// 注入线程池
	private EasyMailExecutorPool easyMailExecutorPool;

	public void setEasyMailExecutorPool(EasyMailExecutorPool easyMailExecutorPool) {
		this.easyMailExecutorPool = easyMailExecutorPool;
	}

	public void setJavaMailSender(JavaMailSenderImpl javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	/**
	 * @specification :发送邮件
	 * @param :EmailEntity email,邮件类
	 * @return :void
	 * @exception :Exception
	 */
	public void sendMessage(EmailEntity email) throws Exception {
//		javaMailSender.setPassword(javaMailSender.getPassword());//解密发邮件的邮箱密码
		boolean success = false;
		if (email.getFrom() != null && !"".equals(email.getFrom())) {// 使用传递过来的发件人
			// 需要根据不同的邮箱类型,设置javaMailSender属性
			initJavaMailSender(email);
		} else {
			email.setFrom(javaMailSender.getUsername());// 使用配置文件中的发件人时
		}
		String username = javaMailSender.getUsername();
		if (username == null){
			logger.error("用户名为空");
			throw new Exception("J000006");
		}else{
			success = true;
		}
		if (success) {
			//在原来的基础上添加同时发送不同文本、不同邮件地址的邮件。
			/***
			 * 根据邮件文本来区别，如果只需发送一个有机、或者一份邮件多收件人。
			 * 可以把文本放入text即可。 
			 */
			if(email.getMul_text()!=null && email.getMul_text().length>0){
				if (email.getAttachment().size() > 0 || email.getImg().size() > 0) {//复杂邮件发送
					email.generateMul_mimeMailMessage(javaMailSender,easyMailExecutorPool.getService());
				} else {
					email.generateMul_SimpleMailMessage(javaMailSender,easyMailExecutorPool.getService());
				}
			}else{
				if (email.getAttachment().size() > 0 || email.getImg().size() > 0) {//复杂邮件发送
					email.generateMimeMailMessage(javaMailSender,easyMailExecutorPool.getService());
				} else {//简单邮件发送
					email.generateSimpleMailMessage(javaMailSender,easyMailExecutorPool.getService());
				}
			}
			email.send();
//			javaMailSender.setPassword(Encryption.encode(javaMailSender.getPassword()));
		} 
	}
	public boolean sendEmail(String[] emailTo,String[] emailCc,String[] emailBcc,String content,String subject,String fileName,String filePath)throws Exception{
		EmailEntity mail = new EmailEntity(emailTo,content);
		mail.setCc(emailCc);
		mail.setBcc(emailBcc);
		mail.setSubject(subject);//主题
		Map<String,String> map = new HashMap<String,String>();//邮件附件
		if(fileName!=null&& !"".equals(fileName)){
			map.put(fileName, filePath);
		}
		mail.setAttachment(map);
		try{
			sendMessage(mail);
			logger.info("----邮件发送完毕 !--");
		}catch(Exception e){
			logger.info("----邮件发送错误！"+e.getMessage());
		}
		return true;
	}
	
	/**
	 * @specification :初始化JavaMailSenderImpl,设置host,port,username,password值
	 * @param :EmailEntity email,邮件类
	 * @return :void
	 * @exception :NAN
	 */
	private void initJavaMailSender(EmailEntity email) {
		String username = email.getFrom();
		String password = email.getPassword();
		if(username != null ){
			int end = username.indexOf("@");
			//当用户名给的是xx@xx.dd的方式时,需要把@后面截取掉,只留名称
			if(end != -1){
				username = username.substring(0,end);
			}
		}
		this.javaMailSender.setUsername(username);
		this.javaMailSender.setPassword(password);
		int port = email.getPort();
		if (port != -1) {
			this.javaMailSender.setPort(port);
		}
		String host = email.getHost();
		if (host != null && !"".equals(host)) {
			this.javaMailSender.setHost(host);
		}

	}
}
