package com.epharm.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.epharm.service.ProductList;
import com.epharm.service.ProductsService;
import com.epharm.service.UserCartsService;
import com.epharm.service.UserService;

@Controller
public class UserCartsWebController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private UserCartsService userCartsService;
	
	
	@GetMapping("/carts")
	public String getUserCarts(Principal principal,Model m) {
		
		String email = principal.getName();
		Integer userid = userService.getUserId(email);
		List<ProductList> list = userCartsService.getAllCartsProducts(userid);
		m.addAttribute("Products", list);
		return "usercarts";
	}
}
