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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    PasswordEncoder encoder;

    @GetMapping("/")
    public String getHome(Model model, Principal p) {
        if(p != null) {
            System.out.println(p.getName()+" is logged in!");
            model.addAttribute("username", p.getName());
        } else {
            System.out.println("nobody is logged in");
        }
        return "home";
        // make sure add in the username attribute, p.getname on every route so the logged in user can see a different nav bar.
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
            System.out.println(email);
            System.out.println(lastName);
            System.out.println(firstName);
            ApplicationUser newUser = new ApplicationUser(username, encoder.encode(password),email,phone,firstName,lastName);
            applicationUserRepository.save(newUser);
            System.out.println(newUser.toString());
//            auto-login when people sign up
            Authentication authentication = new UsernamePasswordAuthenticationToken(newUser, null, new ArrayList<>());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            return new RedirectView("/products");
        } else {
            return new RedirectView("/signup?taken=true");
        }
    }

    @GetMapping("/about-us")
    public String getAboutUs(Model model, Principal p){
        if(p != null) {
            System.out.println(p.getName()+" is logged in!");
            model.addAttribute("username", p.getName());
        } else {
            System.out.println("nobody is logged in");
        }
        return "about-us";
    }

    @GetMapping("/contactus")
    public String getContactUs(Model model, Principal p){
        if(p != null) {
            System.out.println(p.getName()+" is logged in!");
            model.addAttribute("username", p.getName());
        } else {
            System.out.println("nobody is logged in");
        }
        return "contactus";
    }

    @GetMapping("/profile")
    public String getProfile(Model model, Principal p){
        if(p != null) {
            System.out.println(p.getName()+" is logged in!");
            System.out.println("USER DATA"+applicationUserRepository.findByUsername(p.getName()).toString());
//            List<ApplicationUser> user  = (List<ApplicationUser>) applicationUserRepository.findByUsername(p.getName());
            model.addAttribute("user", applicationUserRepository.findByUsername(p.getName()));

        } else {
            System.out.println("nobody is logged in");
        }
        return "profile";
    }


}



