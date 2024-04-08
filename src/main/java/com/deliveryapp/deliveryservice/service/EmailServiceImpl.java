package com.deliveryapp.deliveryservice.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService{
    private final JavaMailSender sender;

    @Override
    public void send(String to, String subject, String text) {
        log.info("------Start building message: {}", subject);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(text);
        sender.send(mailMessage);
        log.info("----{SUCCESS} Message send");
    }
}
