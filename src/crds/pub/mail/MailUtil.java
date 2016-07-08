package crds.pub.mail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

import crds.pub.util.Constant;


/**
 * @specification :常规JavaMail 邮件发送实用类
 * @version : 1.0
 * @author : liuxx
 * @date : Oct 27, 2008 10:22:46 AM
 * @email : liuxx.adam@gmail.com
 */
public final class MailUtil {
	static Logger logger = Logger.getLogger(MailUtil.class);
	// 邮件发送者地址
	private static final String SenderEmailAddr = "lg_test@163.com";

	// 邮件发送者邮箱用户
	private static final String SMTPUserName = "lg_test";

	// 邮件发送者邮箱密码
	private static final String SMTPPassword = "lg_test123";

	// 邮件发送者邮箱SMTP服务器
	private static final String SMTPServerName = "smtp.163.com";

	// 传输类型
	private static final String TransportType = "smtp";

	// 属性
	private static Properties props;

	/**
	 * @specification :私有构造函数，防止外界新建本实用类的实例，因为直接使用MailUtil.sendMail发送邮件即可
	 * @param :NAN
	 * @exception :NAN
	 */
	private MailUtil() {

	}
	

	/**
	 * @specification :静态构造器
	 */
	static {
		MailUtil.props = new Properties();
		// 存储发送邮件服务器的信息
		MailUtil.props.put("mail.smtp.host", MailUtil.SMTPServerName);
		// 同时通过验证
		MailUtil.props.put("mail.smtp.auth", "true");
	}
	
	/**
	 * @specification :发送邮件
	 * @param emailAddr:收信人邮件地址
	 * @param mailTitle:邮件标题
	 * @param mailConcept:邮件内容
	 * @return :void
	 * @exception :NAN
	 */
	public static void sendMail(String emailAddr, String mailTitle,
			String mailConcept) {
		// 根据属性新建一个邮件会话，null参数是一种Authenticator(验证程序) 对象
		Session s = Session.getInstance(MailUtil.props, null);

		// 设置调试标志,要查看经过邮件服务器邮件命令，可以用该方法
		s.setDebug(false);

		// 由邮件会话新建一个消息对象
		Message message = new MimeMessage(s);

		try {
			// 设置发件人
			Address from = new InternetAddress(MailUtil.SenderEmailAddr);
			message.setFrom(from);

			// 设置收件人
			Address to = new InternetAddress(emailAddr);
			message.setRecipient(Message.RecipientType.TO, to);

			// 设置主题
			message.setSubject(mailTitle);
			// 设置信件内容
			message.setText(mailConcept);
			// 设置发信时间
			message.setSentDate(new Date());
			// 存储邮件信息
			message.saveChanges();

			Transport transport = s.getTransport(MailUtil.TransportType);
			// 要填入你的用户名和密码；
			transport.connect(MailUtil.SMTPServerName,MailUtil.SMTPUserName,
					MailUtil.SMTPPassword);

			// 发送邮件,其中第二个参数是所有已设好的收件人地址
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();

			logger.info("发送邮件,邮件地址:" + emailAddr + " 标题:" + mailTitle
					+ " 内容:" + mailConcept + "成功!");
		} catch (Exception e) {
			logger.error("发送邮件,邮件地址:" + emailAddr + " 标题:" + mailTitle
					+ " 内容:" + mailConcept + "失败! 原因是" + e.getMessage());
		}
	}
	/**
	 * 根据链接urlPath读取某个地址的文件内容
	 * @param urlPath
	 * @param readCharsetName 读取链接路径文件的编码,编码要符合java.nio.charset.Charset的标准,否则会抛出UnsupportedEncodingException异常
	 * @return
	 * @throws IOException
	 */
	public static String getEmailContent(String urlPath,String readCharsetName){
		readCharsetName=Constant.trimEmptyDefault(readCharsetName, "UTF-8");
		String sCurrentLine = null;
		StringBuffer bfString = new StringBuffer();
		// 通过urlPath建立链接，并readCharsetName的编码形式获取对应文件内容
		URL newURL;
		try {
			newURL = new URL(urlPath);
			HttpURLConnection urlConnection = (HttpURLConnection) newURL.openConnection();
			urlConnection.connect();
			InputStream urlStream = urlConnection.getInputStream();
			BufferedReader bfReader = new BufferedReader(new InputStreamReader(urlStream, readCharsetName));
			while ((sCurrentLine = bfReader.readLine()) != null)
				bfString.append(sCurrentLine).append("\n");
			urlStream.close();
			bfReader.close();
		} catch (MalformedURLException e) {
			logger.info("获取URL="+urlPath+"的内容时,不能进行URL连接,请检查内部防火墙的设置."+e.getMessage());
		} catch (IOException e) {
			logger.info("获取URL="+urlPath+"的内容时,不能读取URL相应的内容信息,请检查内部防火墙的设置."+e.getMessage());
		}
		return bfString.toString();
	}
}
