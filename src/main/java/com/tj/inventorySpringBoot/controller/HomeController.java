package com.tj.inventorySpringBoot.controller;

import com.tj.inventorySpringBoot.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @Autowired
    ProductServiceImpl productServiceImpl;

    @GetMapping("/")
    public String home() {


        return "index";
    }
}
