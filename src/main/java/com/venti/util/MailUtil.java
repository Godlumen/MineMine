package com.venti.util;

import com.venti.config.MailConfig;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * 邮件工具类
 */
public class MailUtil {
    private static Properties properties = MailConfig.getProperties();
    private static Session session = getSession();

    private static Session getSession() {
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(properties.getProperty("mailUsername"), properties.getProperty("mailPassword"));
            }
        };
        return Session.getInstance(properties, auth);
    }

    /**
     * 发送邮件
     * @param message
     */
    private static void sendMail(MimeMessage message) {
        try {
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将邮件信息整合，并调用发送
     * @param subject
     * @param context
     * @param toAddresses
     */
    public static void sendMail(String subject, String context, String... toAddresses) {
        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(properties.getProperty("mailFrom")));
            message.setSubject(subject);
            message.setContent(context, "text/html;charset=utf-8");
            for (String toAddress : toAddresses) {
                message.addRecipients(Message.RecipientType.TO, toAddress);
            }
            sendMail(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
