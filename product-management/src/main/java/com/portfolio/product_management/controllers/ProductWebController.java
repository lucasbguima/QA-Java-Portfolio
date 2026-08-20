package com.portfolio.product_management.controllers;

import com.portfolio.product_management.models.Product;
import com.portfolio.product_management.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductWebController {

    @Autowired
    private ProductService service;

    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("products", service.getAllProducts());
        return "products";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new Product());
        return "product-form";
    }

    @PostMapping
    public String saveProduct(@ModelAttribute("product") Product product) {
        service.saveProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        service.getProductById(id).ifPresent(product -> model.addAttribute("product", product));
        return "product-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        service.deleteProduct(id);
        return "redirect:/products";
    }
}