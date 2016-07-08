package crds.pub.mail;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

/***
 * 
 * @specification :该类是对邮件的抽象，邮件有哪些属性，这个类就有哪些属性
 * @version : 1.0
 * @author : maoxk
 * @date : 2009-7-9 上午09:35:40
 * @email : maoxk1984@gmail.com
 */
public class EmailEntity extends EmailRunner {

	private String from;//发件人
	private String password;//发件人邮箱密码
	private String[] to;//收件人
	private String[] cc;//抄送人
	private String[] bcc;//密送人
	private String subject;//标题

	private String text;//内容
	//多文本，可以同时发送多封邮件
	private String[] mul_text;

	private String host;// 发件人邮箱服务器host
	private int port = -1;// 发件人邮箱服务器端口

	//邮件附件,key=附件名称,value=本地附件地址     
	@SuppressWarnings("unchecked")
	private Map attachment = new HashMap();

	//邮件图片,嵌入到html内容的的<img src='cid:identifier1234'>中,key=identifier1234,value=本地图片地址  
	@SuppressWarnings("unchecked")
	private Map img = new HashMap();

	/**
	 * @specification :使用配置文件中的发送地址时的构造函数
	 * @param :String[] to,收件人
	 * @param :String text,主题
	 * @exception :NAN
	 */
	public EmailEntity(String[] to, String text) {
		super();
		this.to = to;
		this.text = text;
	}

	public EmailEntity(String[] to, String[] mul_text) {
		super();
		this.to = to;
		this.mul_text = mul_text;
	}

	/**
	 * @specification :指定发件人时的构造函数
	 * @param :String from,发件人邮箱
	 * @param :String password,发件人邮箱密码
	 * @param :String[] to,收件人
	 * @param :String text,主题
	 * @exception :NAN
	 */
	public EmailEntity(String from, String password, String[] to, String text) {
		super();
		this.from = from;
		this.password = password;
		this.to = to;
		this.text = text;
	}

	public String getPassword() {
		return password;
	}

	/**
	 * @specification :构造简单文本邮件simpleMailMessage、存在多个文本邮件和不同的收件人。 
	 * @param :JavaMailSender javaMailSender,spring邮件发送
	 * @param :ExecutorService executorService,邮件发送线程执行对象
	 * @return :void
	 * @exception :NAN
	 */
	public void generateMul_SimpleMailMessage(JavaMailSender javaMailSender, ExecutorService executorService) {
		if (mul_text != null && mul_text.length > 0) {
			smList = new ArrayList<SimpleMailMessage>();
			super.javaMailSender = javaMailSender;
			super.executorService = executorService;
			for (int i = 0; i < mul_text.length; i++) {
				simpleMailMessage = new SimpleMailMessage();
				simpleMailMessage.setText(mul_text[i]);
				simpleMailMessage.setFrom(from);
				simpleMailMessage.setTo(to[i]);
				simpleMailMessage.setSubject(subject);
				simpleMailMessage.setSentDate(new Date());
				smList.add(simpleMailMessage);
			}
		}
	}

	public void generateMul_mimeMailMessage(JavaMailSender javaMailSender, ExecutorService executorService) throws MessagingException {
		if (mul_text != null && mul_text.length > 0) {
			mmList = new ArrayList<MimeMessage>();
			super.javaMailSender = javaMailSender;
			super.executorService = executorService;
			for (int i = 0; i < mul_text.length; i++) {
				mimeMessage = super.javaMailSender.createMimeMessage();
				//转码,控制中文乱码问题
				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				helper.setFrom(from);
				helper.setTo(to[i]);
				helper.setSubject(subject);
				//这里的text是html格式的, 可以使用模板引擎来生成html模板, velocity或者freemarker都可以做到
				helper.setText(mul_text[i], true);
				super.addAttachmentOrImg(helper, attachment, true);
				super.addAttachmentOrImg(helper, img, false);
				helper.setSentDate(new Date());
				mmList.add(mimeMessage);
			}
		}
	}
	
	/**
	 * @specification :构造简单文本邮件simpleMailMessage  
	 * @param :JavaMailSender javaMailSender,spring邮件发送
	 * @param :ExecutorService executorService,邮件发送线程执行对象
	 * @return :void
	 * @exception :NAN
	 */
	public void generateSimpleMailMessage(JavaMailSender javaMailSender, ExecutorService executorService) {
		super.javaMailSender = javaMailSender;
		super.executorService = executorService;
		simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom(from);
		simpleMailMessage.setTo(to);
		if (cc != null && cc.length > 0) {
			simpleMailMessage.setCc(cc);
		}
		if (bcc != null && bcc.length > 0) {//密送人
			simpleMailMessage.setBcc(bcc);
		}
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(text);
		simpleMailMessage.setSentDate(new Date());
	}

	/**
	 * @specification :构造复杂邮件mimeMessage，可以添加附近，图片，等等  
	 * @param :JavaMailSender javaMailSender,spring邮件发送
	 * @param :ExecutorService executorService,邮件发送线程执行对象
	 * @return :void
	 * @exception :NAN
	 */
	public void generateMimeMailMessage(JavaMailSender javaMailSender, ExecutorService executorService) throws MessagingException {
		super.javaMailSender = javaMailSender;
		super.executorService = executorService;
		mimeMessage = super.javaMailSender.createMimeMessage();
		//转码,控制中文乱码问题
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
		helper.setFrom(from);
		helper.setTo(to);//收件人
		if (cc != null && cc.length > 0) {//抄送人
			helper.setCc(cc);
		}
		if (bcc != null && bcc.length > 0) {//密送人
			helper.setBcc(bcc);
		}
		helper.setSubject(subject);
		//这里的text是html格式的, 可以使用模板引擎来生成html模板, velocity或者freemarker都可以做到
		helper.setText(text, true);
		super.addAttachmentOrImg(helper, attachment, true);
		super.addAttachmentOrImg(helper, img, false);
		helper.setSentDate(new Date());
	}

	/**
	 * @specification :发送邮件方法, 在这个方法调用之前必须调用 generateMimeMailMessage 或者 generateSimpleMailMessage
	 * @param :NAN
	 * @return :void
	 * @exception :NAN
	 */
	public void send() {
		if (super.javaMailSender != null && super.executorService != null) {
			try {
				super.executorService.execute(this);
				Thread.sleep(3000);//修正发送邮件问题
			} catch (InterruptedException e) {
			}
		}
	}

	public void setCc(String[] cc) {
		this.cc = cc;
	}
	public String[] getCc() {
		return this.cc;
	}
	public void setBcc(String[] bcc) {
		this.bcc = bcc;
	}
	public String[] getBcc() {
		return this.bcc;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@SuppressWarnings("unchecked")
	public void setAttachment(Map attachment) {
		this.attachment = attachment;
	}

	@SuppressWarnings("unchecked")
	public void setImg(Map img) {
		this.img = img;
	}

	@SuppressWarnings("unchecked")
	public Map getAttachment() {
		return attachment;
	}

	@SuppressWarnings("unchecked")
	public Map getImg() {
		return img;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String[] getMul_text() {
		return mul_text;
	}

	public void setMul_text(String[] mul_text) {
		this.mul_text = mul_text;
	}

}
