package com.example.clientsservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailingService {
    public static final String subject = "balance";
    public static final String text = "your balance is 0";

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(final String toAddress, String surname) {
        SimpleMailMessage message = new SimpleMailMessage() ;
        message.setTo(toAddress);
        message.setSubject(subject);
        message.setText(surname + ", " + text);
        javaMailSender.send(message);
    }
}
