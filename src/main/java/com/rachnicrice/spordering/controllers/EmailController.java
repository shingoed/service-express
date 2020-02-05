package com.rachnicrice.spordering.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class EmailController {

    @Autowired
    JavaMailSender jSender;

    @PostMapping("/submit")
    public RedirectView sendOrder (Principal p, Model m) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("rachnicrice@gmail.com");
        message.setSubject("test email");
        message.setText("Yaaaay! You got an email!!");
        jSender.send(message);

        return new RedirectView("/mycart?submitted=true");

    }
}
