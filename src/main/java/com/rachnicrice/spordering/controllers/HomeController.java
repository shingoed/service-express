package com.rachnicrice.spordering.controllers;

import com.rachnicrice.spordering.models.ApplicationUser;
import com.rachnicrice.spordering.models.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.ArrayList;

@Controller
public class HomeController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    PasswordEncoder encoder;

    @GetMapping("/")
    public String getHome(Model m,Principal p) {
        //Check is the user is logged in
        //If they are pass their username to the template
        if (p != null) {
            m.addAttribute("username", p.getName());
        }
        return "home";
    }

    @GetMapping("/login")
    public String getLogin(){
        return "login";
    }


    @GetMapping("/signup")
    public String getSignUp(){
        return "signup";
    }

    @PostMapping("/signup")
    public RedirectView signup(String username, String password, String email, String phone, String firstName, String lastName) {
        if (applicationUserRepository.findByUsername(username) == null) {
            System.out.println(username);
            System.out.println(password);
            ApplicationUser newUser = new ApplicationUser(username, encoder.encode(password),email,phone,firstName,lastName);
            applicationUserRepository.save(newUser);

//            auto-login when people sign up
            Authentication authentication = new UsernamePasswordAuthenticationToken(newUser, null, new ArrayList<>());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            return new RedirectView("/products");
        } else {
            return new RedirectView("/signup?taken=true");
//************ TO DO:  have some kind of msg to pop up when they try to sign up with same name*******
        }
    }
}



