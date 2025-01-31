package com.example.notification_service.services;


import com.example.core.UserEnrolledEvent;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Properties;

@Service
public class MailNotificationService {

    private final Logger logger = LoggerFactory.getLogger(MailNotificationService.class);

    private final JavaMailSender mailSender;

    private final TemplateEngine templateEngine;

    public MailNotificationService(JavaMailSender mailSender,  TemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }



    public void sendSimpleEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();


        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        message.setFrom("springtestnotification@gmail.com");
        mailSender.send(message);
    }

    public void sendEnrollmentOnCourseEmail(UserEnrolledEvent event) throws MessagingException {

        Context context = new Context();

        String htmlContent = templateEngine.process("user-enrolled-email", context);

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(event.getEmail());
        helper.setSubject("You have successfully enroled for course");
        helper.setText(htmlContent, true);

        mailSender.send(message);
        logger.info("Successfully send email about enrollment to {}", event.getEmail());



    }
}
