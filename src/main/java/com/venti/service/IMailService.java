package com.venti.service;

public interface IMailService {
    void sendSimpleMail(String to, String subject, String content);
    void sendHtmlMail(String to, String subject, String content);
    void sendAttachmentsMail(String to, String subject, String content, String filePath);
    void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId);
    void sendverifyMail(String to, String token);
}
