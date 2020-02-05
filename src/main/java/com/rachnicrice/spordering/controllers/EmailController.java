package com.rachnicrice.spordering.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class EmailController {

    @PostMapping("/submit")
    public RedirectView sendOrder (Principal p, Model m) {

        return new RedirectView("/mycart?submitted=true");
    }
}
