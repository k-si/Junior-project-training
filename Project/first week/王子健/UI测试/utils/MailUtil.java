import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Properties;

public class MailUtil {

    public static final String filePath="conf/config.properties";
    private String path;
    private String sender = ReadProperties.getPropertyValue("sendmail");
    private String[] tomail;
    private String auth_code="cmqeidvfwtatihgb";
    Properties prop = new Properties();

    public MailUtil(String path) throws IOException, GeneralSecurityException {

        this.path = path;
        prop.setProperty("mail.host", "smtp.qq.com");
        prop.setProperty("mail.transport.protocol", "smtp");
        prop.setProperty("mail.smtp.auth", "true");
        prop.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        prop.setProperty("mail.smtp.port", "465");
        prop.setProperty("mail.smtp.socketFactory.port", "465");

        try {
            this.tomail=ReadProperties.getPropertyValue("tomail").split(",");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void sendMessage () {
        Session session = Session.getInstance(prop, new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // TODO Auto-generated method stub
                return new PasswordAuthentication(sender,auth_code);
            }

        });

        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(sender));
            InternetAddress[] ia = new InternetAddress[tomail.length];
            for(int i = 0; i < ia.length; i++) {
                ia[i] = new InternetAddress(tomail[i]);
            }
            message.addRecipients(Message.RecipientType.TO, ia);
            //主题
            message.setSubject("主题");
            BodyPart messageBodyPart = new MimeBodyPart();
            // 消息
            messageBodyPart.setText("测试报告");
            // 创建多重消息
            Multipart multipart = new MimeMultipart();
            // 设置文本消息部分
            multipart.addBodyPart(messageBodyPart);
            // 附件部分
            messageBodyPart = new MimeBodyPart();
            //设置要发送附件的文件路径
            String filename = path;
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            //messageBodyPart.setFileName(filename);
            //处理附件名称中文（附带文件路径）乱码问题
            messageBodyPart.setFileName(MimeUtility.encodeText(filename));
            multipart.addBodyPart(messageBodyPart);
            // 发送完整消息
            message.setContent(multipart);
            Transport.send(message);
        } catch (MessagingException | UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}