package com.rachnicrice.spordering.controllers;


import com.rachnicrice.spordering.models.Product;
import com.rachnicrice.spordering.models.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/")
    public String homePage(Model model) {
        // make sure add in the username attribute, p.getname on every route so the logged in user can see a different nav bar.

        return "home";
    }

    @GetMapping("/login")
    public String getLogin(Model m, Principal p) {
//        ApplicationUser user = applicationUserRepository.findByUsername(p.getName());
//        m.addAttribute("user", user);
        return "login";
    }
    @GetMapping("/signup")
    public String getSignup() {
        return "signup";
    }



    @GetMapping("/product")
    public String showPage(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "id") String sortBy) {
        PageRequest pagereq = PageRequest.of(page,4, Sort.by(sortBy).ascending());

        model.addAttribute("data", productRepository.findAll(pagereq));

        model.addAttribute("currentPage",page);
        return "product";
    }

    @GetMapping("/mycart")
    public String showCart(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "id") String sortBy) {
        PageRequest pagereq = PageRequest.of(page,4, Sort.by(sortBy).ascending());

        model.addAttribute("data", productRepository.findAll(pagereq));

        model.addAttribute("currentPage",page);
        return "mycart";
    }


    @PostMapping("/save")
    public RedirectView save(Product product) {
        productRepository.save(product);

        return new RedirectView("/product");
    }

    @GetMapping("/delete")
    public RedirectView delete(Long id) {
        productRepository.deleteById(id);

        return new RedirectView("/product");
    }

    @GetMapping("/findOne")
    @ResponseBody
    public Product findOne(Long id) {
        return productRepository.getOne(id);

    }
}
