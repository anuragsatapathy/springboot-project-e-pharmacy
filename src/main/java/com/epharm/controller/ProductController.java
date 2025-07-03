package com.epharm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.epharm.entity.Products;
import com.epharm.service.ProductsService;

@Controller
public class ProductController {
	
	@Autowired
	private ProductsService productsService;

	@GetMapping("/productlist")
	public String product(Model m) {
		List<Products> product = productsService.ListProduct();
		m.addAttribute("Products", product);
		return "productlist";
		
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/createproduct")
	public String createProduct(Model m) {
		
		m.addAttribute("products", new Products());
		return "createproduct";
		
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/createproduct")
	public String createProductpost(Products product) {
		productsService.createProduct(product);
		
		return "redirect:/productlist";
		
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/updateproducts")
	public String updateProduct(Products product) {
		productsService.updateProduct(product);
		return "redirect:/productlist";
		
		
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/deleteproducts/{id}")
	public String deleteProduct(@PathVariable Integer id) {
		productsService.deleteProduct(id);
		return "redirect:/productlist";
		
	}
	
	
}
