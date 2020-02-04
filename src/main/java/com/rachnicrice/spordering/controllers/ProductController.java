package com.rachnicrice.spordering.controllers;


import com.rachnicrice.spordering.models.*;
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
import java.sql.Timestamp;

@Controller
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    LineItemRepository lineItemRepository;


    @GetMapping("/products")
    public String showPage(Principal p, Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "item_id") String sortBy) {
//        PageRequest pagereq = PageRequest.of(page,4, Sort.by(sortBy).ascending());

        if(p != null) {
            System.out.println(p.getName()+" is logged in!");
            model.addAttribute("username", p.getName());
        } else {
            System.out.println("nobody is logged in");
        }

        model.addAttribute("data", productRepository.findAll());

        model.addAttribute("currentPage",page);
        return "products";
    }


    @PostMapping("/mycart")
    public RedirectView addCart(Model model, Principal p) {

        Timestamp createdAt = new Timestamp(System.currentTimeMillis());

//        if(applicationUserRepository.findByUsername(p.getName()).getOrders().isEmpty()){ // grab user's order if its empty create a new order.
//            Order order = new Order(applicationUserRepository.findByUsername(p.getName()),createdAt,false);
//
//        }else {
//            if(applicationUserRepository.findByUsername(p.getName()).getOrders().){
//
//            }else {
//
//            }
//        }


        return new RedirectView("/mycart");
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

    @GetMapping("/profile")

    public String getProfile(Model model, Principal p){
        if(p != null) {
            System.out.println(p.getName()+" is logged in!");
            model.addAttribute("username", p.getName());
        } else {
            System.out.println("nobody is logged in");
        }
        return "profile";
    }


}
