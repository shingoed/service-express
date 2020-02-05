package com.rachnicrice.spordering.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.security.Principal;

@Controller
public class EmailController {

    @Autowired
    JavaMailSender jSender;

    @PostMapping("/submit")
    public RedirectView sendOrder (Principal p, Model m) {

        MimeMessage message = jSender.createMimeMessage();

        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setTo("Rachnicrice@gmail.com");
            helper.setSubject("test");
            helper.setText("You got more mail!!");

            FileSystemResource file = new FileSystemResource(new File("src/main/resources/rachael.png"));
            helper.addAttachment("Invoice.png", file);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        jSender.send(message);
        return new RedirectView("/mycart?submitted=true");

    }
}
