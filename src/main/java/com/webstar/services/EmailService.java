package com.webstar.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements IEmailService
{
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendMail(String toEmail, String subject, String message, String fromEmail)
    {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(toEmail);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        mailMessage.setFrom(fromEmail);
        javaMailSender.send(mailMessage);

    }

}
