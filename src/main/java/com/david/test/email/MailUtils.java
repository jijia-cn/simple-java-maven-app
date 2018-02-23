package com.david.test.email;

import java.io.File;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
/**
 * 邮件服务工具类
 * @author jijia
 *
 */
public class MailUtils {

	private static String server;
	private static String host;
	private static String port;
	private static String userName;
	private static String password;
	private static String logger_base_path;
	
	private static Properties properties = null;

    static {
        if (null == properties) {
            properties = new Properties();
            InputStreamReader reader = null;
            try {
                reader = new InputStreamReader(MailUtils.class.getClassLoader().getResourceAsStream("email.properties"), "utf-8");
                properties.load(reader);
                reader.close();
                loadConfig();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    reader = null;
                }
            }
        }
    }
    
    /**
     * load email concerned configuration
     */
    private static void loadConfig(){
    	server = properties.getProperty("server");
    	host = properties.getProperty("host", "smtp.126.com");
    	port = properties.getProperty("port", "25");
    	userName = properties.getProperty("userName");
    	password = properties.getProperty("password");
    	logger_base_path = properties.getProperty("logger_base_path");
    }
	
	/**
	 * send email to many recipients
	 * @param toMailAddresses
	 * @param title
	 * @param mailContent
	 */
	public static void sendMail(String[] toMailAddresses, String title, String mailContent) {
		Properties props = new Properties();
		// 使用smtp：简单邮件传输协议
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.auth", "true");

		Session session = Session.getInstance(props);

		try {
			Address addresses[] = new Address[toMailAddresses.length];
			for(int i=0;i<toMailAddresses.length;i++){
				addresses[i] = new InternetAddress(toMailAddresses[i]);
			}
			
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(server));
			message.setRecipients(Message.RecipientType.TO, addresses);
			message.setSubject(title);

			// 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
			Multipart multipart = new MimeMultipart();
			// 设置邮件的文本内容
			BodyPart contentPart = new MimeBodyPart();
			contentPart.setContent(mailContent, "text/html;charset=gbk");
			multipart.addBodyPart(contentPart);
			// 添加附件
			File file = new File(logger_base_path);

			for (String fileName : file.list()) {
				if (fileName.contains("properties")) {
					File cFile = new File(logger_base_path + "\\" + fileName);
					BodyPart messageBodyPart = new MimeBodyPart();
					DataSource source = new FileDataSource(cFile.getPath());
					// 添加附件的内容
					messageBodyPart.setDataHandler(new DataHandler(source));
					messageBodyPart.setFileName(MimeUtility.encodeText(cFile.getName()));
					multipart.addBodyPart(messageBodyPart);
				}
			}

			message.setContent(multipart);
			message.saveChanges();
			// 发送邮件
			Transport transport = session.getTransport("smtp");
			transport.connect(userName, password);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		sendMail(new String[]{"857332533@qq.com"}, "server运行基本信息提醒", "<a>html 元素</a>：<b>邮件内容</b>");
	}
}
