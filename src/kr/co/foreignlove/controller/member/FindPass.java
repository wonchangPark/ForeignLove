package kr.co.foreignlove.controller.member;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class FindPass {
//	static final String FROM = "foreign.love.2020@gmail.com";
//    static final String FROMNAME = "foreign.love";
//    static final String TO = "songe08@gmail.com";
// 
//    static final String SMTP_USERNAME = "foreign.love.2020@gmail.com";
//    static final String SMTP_PASSWORD = "****";
//    
//    static final String HOST = "smtp.live.com";
//    static final int PORT = 25;
//    
//    static final String SUBJECT = "���� ����";
//    
//    static final String BODY = String.join(
//        System.getProperty("line.separator"),
//        "<h1>���� ����</h1>",
//        "<p>�� ������ �Ƹ��ٿ� ����� ���� �Ƹ��ٿ� �����Դϴ�!</p>."
//    );
 
    public static void main(String[] args) throws Exception {
    	final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
    	//�̸��� ��ü�����ϱ�
    	Properties props = System.getProperties();
    	props.setProperty("mail.smtp.host", "smtp.gmail.com");
    	props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
    	props.setProperty("mail.smtp.socketFactory.fallback", "false");
    	props.setProperty("mail.smtp.port", "465");
    	props.setProperty("mail.smtp.socketFactory.port", "465");
    	props.put("mail.smtp.auth", "true");
    	props.put("mail.debug", "true");
    	props.put("mail.store.protocol", "pop3");
    	props.put("mail.transport.protocol", "smtp");
    	final String username = "foreign.love.2020@gmail.com";//
    	final String password = "moosong980208";

    	try{
    	 Session session = Session.getDefaultInstance(props, 
    	 new Authenticator(){
    	 protected PasswordAuthentication getPasswordAuthentication() {
    	 return new PasswordAuthentication(username, password);
    	}});

    	//�޼��� ����
    	Message msg = new MimeMessage(session);

    	//�����»�� �޴»�� ����
    	msg.setFrom(new InternetAddress("foreign.love.2020@gmail.com"));
    	msg.setRecipients(Message.RecipientType.TO, 
    						InternetAddress.parse("songe08@gmail.com",false));
    	msg.setSubject("�����Դϴ�");
    	msg.setText("�n�����Դϴ�");
    	msg.setSentDate(new Date());
    	Transport.send(msg);
    	System.out.println("�߽ż���!");

    	}catch (MessagingException error){ 
    		System.out.println("������ �߻��߽��ϴ�: " + error);
    	}

    }
}
