package ua.com.owu.service;

import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;

public interface MailService {
    void sendSimpleMessage(String email, String subject, String text) throws MessagingException;
    void sendMessageWithAttachment(String email, String subject, String text, MultipartFile file) throws MessagingException;
}
