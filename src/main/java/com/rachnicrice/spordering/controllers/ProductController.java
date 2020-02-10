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
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.LinkedList;
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

    @Autowired
    PasswordEncoder encoder;

    @GetMapping("/products")
    public String showPage(Principal p, Model model) {

        if(p != null) {
            System.out.println(p.getName()+" is logged in!");
            model.addAttribute("username", p.getName());
        } else {
            System.out.println("nobody is logged in");
        }

        // pass in isAdmin object
        model.addAttribute("isAuthorized", applicationUserRepository.findByUsername(p.getName()).getAdmin());
        System.out.println("IS ADMIN"+applicationUserRepository.findByUsername(p.getName()).getAdmin());

        model.addAttribute("data", productRepository.findAll());
        return "products";
    }

    @PostMapping("/mycart")
    public RedirectView addCart(Model model, Principal p, Long item_id, int quantity) {

        if (p != null) {
            model.addAttribute("username", p.getName());
        }

        Timestamp createdAt = new Timestamp(System.currentTimeMillis());
        ApplicationUser loggedInUser = applicationUserRepository.findByUsername(p.getName());
        List<Order> userOrders = loggedInUser.getOrders();

        // Initialize as as true (case that does not have any unsubmitted orders), set to false if one is found in the loop
        boolean onlySubmittedOrders = true;
        if (userOrders.size() > 0) {
            for (Order order : userOrders) {
                if (order.getSubmitted()==false) {
                    onlySubmittedOrders = false;
                }
            }
        }

        if (userOrders.size() <= 0 || onlySubmittedOrders) {
            Order order = new Order(loggedInUser, createdAt,false);
            orderRepository.save(order);
            //change hard coded 1 and 10 to path variables
            //What does the above note mean???

            LineItem cartItem = new LineItem(order, productRepository.getOne(item_id), quantity);// create new cart item with order, product, and quantity
            lineItemRepository.save(cartItem);
        } else {
            for (Order order : userOrders) {
                //Removed redundant code (order was re-declared as unsubmitted order? Why?
                if (order.getSubmitted()==false) {
                    List<LineItem> lineItems = order.getItemsInThisOrder();

                    if (lineItems.isEmpty()) {
                        LineItem cartItem = new LineItem(order, productRepository.getOne(item_id), quantity);// create new cart item with order, product, and quantity
                        lineItemRepository.save(cartItem);
                    } else {

                        for (LineItem lineItem : lineItems) {
                            if (item_id.equals(lineItem.getProduct().getItem_id())) {
                                int prevQty = lineItem.getQuantity();
                                lineItem.setQuantity(prevQty + quantity);
                                lineItemRepository.save(lineItem);
                            } else {
                                LineItem cartItem = new LineItem(order, productRepository.getOne(item_id), quantity);// create new cart item with order, product, and quantity
                                lineItemRepository.save(cartItem);
                            }
                        }
                    }
                }
            }

        }

        return new RedirectView("/products");
    }


    @PostMapping("/save")
    public RedirectView save(Product product, Principal p) {
        if(applicationUserRepository.findByUsername(p.getName()).getAdmin()) {
            productRepository.save(product);
        }

        return new RedirectView("/products");
    }

    @GetMapping("/delete")
    public RedirectView delete(Long id, Principal p) {

        // iterating through each item in LineItem and comparing the foreign key with the id and deleting items in LineItem prior to deleting product.
        if(applicationUserRepository.findByUsername(p.getName()).getAdmin()) {
            for (LineItem item : lineItemRepository.findAll()) {
                if (item.getProduct().getId() == id) {
                    lineItemRepository.deleteById(item.getId());
                }
            }
            productRepository.deleteById(id);
        }
        return new RedirectView("/products");
    }

    @GetMapping("/findOne")
    @ResponseBody
    public Product findOne(Long id) {
        return productRepository.getOne(id);

    }

    //Deleted /populateDatabase route since data already exists in Heroku database, route is no longer needed.
}
