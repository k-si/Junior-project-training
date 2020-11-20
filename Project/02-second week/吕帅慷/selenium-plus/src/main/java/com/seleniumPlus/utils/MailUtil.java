package com.seleniumPlus.utils;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * 邮件工具类
 */
public class MailUtil {




    public static void send163MailWithHTML(String html) {
        try {
            // 1.基础参数配置
            Properties prop = new Properties();
            prop.put("mail.transport.protocol", "smtp");
            prop.put("mail.smtp.host", "smtp.163.com");
            prop.put("mail.smtp.auth", "true");

            // 2.获取会话session对象
            Session session = Session.getInstance(prop, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(ReadPropUtil.getPropertyValue("sender"), ReadPropUtil.getPropertyValue("auth_code"));
                }
            });

            // 3.填充邮件内容
            Address[] list = new Address[Integer.parseInt(ReadPropUtil.getPropertyValue("to_mail_num"))];
            String[] values = ReadPropUtil.getPropSplitValues("to_mail");
            for (int i = 0; i < values.length; i++)
                list[i] = new InternetAddress(values[i]); // 获取地址数组

            // 邮件内容的集合
            MimeMessage message = new MimeMessage(session);
            MimeMultipart multipart = new MimeMultipart();
            //HTML格式
            MimeBodyPart bodyPart = new MimeBodyPart();
            bodyPart.setContent(html,"text/html;charset=UTF-8");
            multipart.addBodyPart(bodyPart);

            message.setFrom(new InternetAddress(ReadPropUtil.getPropertyValue("sender"))); // 设置发件人
            message.addRecipients(MimeMessage.RecipientType.TO, list); // 设置收件人
            message.setSubject(ReadPropUtil.getPropertyValue("subject")); // 设置主题
            message.setContent(multipart);

            // 4. 发送
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送163邮箱
     */
    public static void send163MailWithAccessory() {
        try {
            // 1.基础参数配置
            Properties prop = new Properties();
            prop.put("mail.transport.protocol", "smtp");
            prop.put("mail.smtp.host", "smtp.163.com");
            prop.put("mail.smtp.auth", "true");

            // 服务器ssl
//            final String smtpPort = "465";
//            prop.setProperty("mail.smtp.port", smtpPort);
//            prop.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//            prop.setProperty("mail.smtp.socketFactory.fallback", "false");
//            prop.setProperty("mail.smtp.socketFactory.port", smtpPort);

            // 2.获取会话session对象
            Session session = Session.getInstance(prop, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(ReadPropUtil.getPropertyValue("sender"), ReadPropUtil.getPropertyValue("auth_code"));
                }
            });

            // 3.填充邮件内容及附件
            Address[] list = new Address[Integer.parseInt(ReadPropUtil.getPropertyValue("to_mail_num"))];
            String[] values = ReadPropUtil.getPropSplitValues("to_mail");
            for (int i = 0; i < values.length; i++)
                list[i] = new InternetAddress(values[i]); // 获取地址数组

            // 邮件内容的集合
            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(ReadPropUtil.getPropertyValue("sender"))); // 设置发件人
            message.addRecipients(MimeMessage.RecipientType.TO, list); // 设置收件人
            message.setSubject(ReadPropUtil.getPropertyValue("subject")); // 设置主题
            // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
            Multipart multipart = new MimeMultipart();

            // 添加邮件正文
            BodyPart contentPart = new MimeBodyPart();
            contentPart.setText(ReadPropUtil.getPropertyValue("mail_msg"));
            multipart.addBodyPart(contentPart);

            // 添加附件的内容
            BodyPart attachmentBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(ReadPropUtil.getPropertyValue("zip_output_path"));
            attachmentBodyPart.setDataHandler(new DataHandler(source));
            attachmentBodyPart.setFileName(MimeUtility.encodeWord("report.zip")); // 设置附件名称
            multipart.addBodyPart(attachmentBodyPart);
            message.setContent(multipart); // 将multipart对象放到message中

            // 4. 发送
            Transport.send(message);
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
