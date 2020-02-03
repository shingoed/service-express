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

    @GetMapping("/products")
    public String showPage(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "item_id") String sortBy) {
       // PageRequest pagereq = PageRequest.of(page,4, Sort.by(sortBy).ascending());

        model.addAttribute("data", productRepository.findAll());

        model.addAttribute("currentPage",page);
        return "products";
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
