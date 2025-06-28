package com.epharm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.epharm.entity.Products;
import com.epharm.service.ProductsService;

@Controller
public class ProductController {
	
	@Autowired
	private ProductsService productsService;

	@GetMapping("/productlist")
	public String product() {
		
		return "productlist";
		
	}
	
	@GetMapping("/createproduct")
	public String createProduct(Model m) {
		
		m.addAttribute("products", new Products());
		return "createproduct";
		
	}
	
	@PostMapping("/createproduct")
	public String createProductpost(Products product) {
		productsService.createProduct(product);
		
		return "redirect:/productlist";
		
	}
}
