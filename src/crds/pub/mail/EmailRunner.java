package crds.pub.mail;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
/***
 * 
 * @specification :用来发送邮件的 Runnable, javaMailSender 每次发送邮件都会从线程池中取一个线程, 然后进行发邮件操作
 * @version : 1.0
 * @author : maoxk
 * @date : 2009-7-9 上午09:36:29
 * @email : maoxk1984@gmail.com
 */
public class EmailRunner implements Runnable {
	static Logger logger = Logger.getLogger(EmailRunner.class);

	protected SimpleMailMessage simpleMailMessage;
	protected MimeMessage mimeMessage;
	protected JavaMailSender javaMailSender;
	protected ExecutorService executorService;
	protected List<SimpleMailMessage> smList;
	protected List<MimeMessage> mmList;
	/**
	 * @specification :添加附件或者是图片
	 * @param :MimeMessageHelper helper,将图片、附件设置到helper中
	 * @param :Map map,key=附件名称,value=本地附件路径  
	 * @param :boolean isAttachment,ture说明添加的是附件,flase为图片
	 * @return :void
	 * @exception :MessagingException
	 */
	@SuppressWarnings("unchecked")
	protected void addAttachmentOrImg(MimeMessageHelper helper, Map map, boolean isAttachment) throws MessagingException {
		for (Iterator it = map.entrySet().iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			String key = (String) entry.getKey();
			String value = (String) entry.getValue();
			if (key != null && !"".equals(key) && value != null
					&& !"".equals(value)) {
				FileSystemResource file = new FileSystemResource(
						new File(value));
				if (!file.exists())
					continue;
				if (isAttachment) {//附件
					helper.addAttachment(key, file);
				} else {//页面嵌套图片
					helper.addInline(key, file);
				}
			}
		}
	}
	
	/**
	 * @specification :附送邮件,该方法将在线程池中的线程中执行
	 * @param :NAN
	 * @return :void
	 * @exception :NAN
	 */
	public void run() {
		//添加了可以发送多风邮件的设置，smList为邮件集合，单封邮件可以只放入simpleMailMessage。
		try{
			if(smList!=null && smList.size()>0){
				for(int i = 0 ; i< smList.size();i++){
					this.simpleMailMessage = smList.get(i);
					try {
						javaMailSender.send(simpleMailMessage);
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						logger.error("EmailRunner.run():"+e.getMessage());
					}//修正发送邮件问题
				}
			}else if(mmList!=null && mmList.size()>0){
				for(int i = 0 ; i< mmList.size();i++){
					this.mimeMessage = mmList.get(i);
					try {
						javaMailSender.send(mimeMessage);
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						logger.error("EmailRunner.run():"+e.getMessage());
					}//修正发送邮件问题
				}
			}else{
				if (simpleMailMessage != null) {//发送一般邮件
					try {
						javaMailSender.send(this.simpleMailMessage);
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else if (mimeMessage != null) {//发送复杂邮件
					try {
						javaMailSender.send(this.mimeMessage);
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		} catch(MailException me){
			logger.info("邮件发送失败,错误原因:"+me.getMessage());
		}
	}
}
