package com.example.notification_service.services;


import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class MailNotificationService {


    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();


        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        message.setFrom("springtestnotification@gmail.com");
        mailSender.send(message);
    }
}
