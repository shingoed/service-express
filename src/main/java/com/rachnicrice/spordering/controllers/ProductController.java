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
import java.util.List;

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
        System.out.println("FIDN ALL PRODUCT"+productRepository.findAll().toString());
        model.addAttribute("currentPage",page);
        return "products";
    }

    @PostMapping("/mycart")
    public RedirectView addCart(Model model, Principal p, int quantity, Product product) {
        System.out.println(quantity);
        System.out.println("PRODUCT ID"+ productRepository);


        Timestamp createdAt = new Timestamp(System.currentTimeMillis());
        List<Order> userOrders = applicationUserRepository.findByUsername(p.getName()).getOrders();

        // Test if the user has an order object in the DB
        if (!userOrders.isEmpty()) { // if user ever made an  order.
//            boolean areAllOrdersSubmitted = true;
            for (Order order : userOrders) {
                if(order.getSubmitted() == false) {
                    System.out.println("made it into at least one order false");
                    LineItem cartItem = new LineItem(order, productRepository.getOne((long) 1), quantity);// create new cart item with order, product, and quantity
                    lineItemRepository.save(cartItem);
//                    areAllOrdersSubmitted = false;
                }
            }
            // remember to go back here and implement the case where order submitted = true;


        } else {
            //If no orders exist, make one
            System.out.println("made it into no orders ever");
            Order order = new Order(applicationUserRepository.findByUsername(p.getName()), createdAt,false);
            orderRepository.save(order);

            LineItem cartItem = new LineItem(order, productRepository.getOne((long) 1), quantity);// create new cart item with order, product, and quantity
            lineItemRepository.save(cartItem);
        }

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
}
