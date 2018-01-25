package com.venti.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MailConfig {
    private static final String PROPERTIES_DEFAULT = "mail.properties";
    private static String host;
    private static Integer port;
    private static String userName;
    private static String passWord;
    private static String emailForm;
    private static String timeout;
    private static String personal;
    private static Properties properties;

    static {
        init();
    }

    private static void init() {
        properties = new Properties();
        try {
            InputStream inputStream = MailConfig.class.getClassLoader().getResourceAsStream(PROPERTIES_DEFAULT);
            properties.load(inputStream);
            inputStream.close();
            host = properties.getProperty("mail.smtp.host");
            port = Integer.parseInt(properties.getProperty("mail.smtp.port"));
            userName = properties.getProperty("mailUsername");
            passWord = properties.getProperty("mailPassword");
            emailForm = properties.getProperty("mailFrom");
            timeout = properties.getProperty("mail.smtp.timeout");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Properties getProperties() {
        return properties;
    }
}
