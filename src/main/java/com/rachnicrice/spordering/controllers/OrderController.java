package com.rachnicrice.spordering.controllers;

import com.rachnicrice.spordering.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.sql.Timestamp;

import java.security.Principal;
import java.util.List;

@Controller
public class OrderController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    LineItemRepository lineItemRepository;


    @GetMapping("/mycart")
    public String showCart(Model model, Principal p, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "id") String sortBy) {
//        PageRequest pagereq = PageRequest.of(page,4, Sort.by(sortBy).ascending());

        System.out.println("you made it!!");

        if(p != null) {
            System.out.println(p.getName()+" is logged in!");
            model.addAttribute("username", p.getName());
        } else {
            System.out.println("nobody is logged in");
        }

        ApplicationUser loggedInUser = applicationUserRepository.findByUsername(p.getName());
        List<Order> userOrders = loggedInUser.getOrders();
        System.out.println("user orders -----> " + userOrders);
        for (Order order : userOrders) {
            System.out.println("submitted values for orders" + order.getSubmitted());
        }



        Timestamp createdAt = new Timestamp(System.currentTimeMillis());

//        applicationUserRepository.findByUsername(p.getName()),createdAt,false);

//        System.out.println(applicationUserRepository.findByUsername(p.getName()).getOrders()); // return []

        // Ellen - turn this into a query that returns from orderRepo.
//        model.addAttribute("data", productRepository.findAll());

        model.addAttribute("currentPage",page);

        return "mycart";
    }
}
