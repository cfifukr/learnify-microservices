package com.example.notification_service.controllers;


import com.example.notification_service.services.MailNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/mail")
public class MailController {

    @Autowired
    private MailNotificationService mailService;

    @GetMapping("/sendMail")
    public String sendMail(@RequestParam String to, @RequestParam String subject, @RequestParam String text) {
        mailService.sendSimpleEmail(to, subject, text);
        return "Email sent successfully!";
    }
}
