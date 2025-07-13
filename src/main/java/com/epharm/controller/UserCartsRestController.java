package com.epharm.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.epharm.entity.UserCarts;
import com.epharm.service.UserCartsService;
import com.epharm.service.UserService;

@RestController
public class UserCartsRestController {

@Autowired
private UserCartsService userCartsService;

@Autowired
private UserService userService;

	@PostMapping("/addproducttocart")
	public boolean addProduct(@RequestBody UserCarts userCartsDetails,Principal principal) {
		String email=principal.getName();
		Integer userid=userService.getUserId(email);
		return userCartsService.addProduct(userid,userCartsDetails.getProductid());
		
	}
	
	@PostMapping("/removeproductfromcart")
	public boolean removeProduct(@RequestBody UserCarts userCartsDetails,Principal principal) {
		String email=principal.getName();
		Integer userid=userService.getUserId(email);
		return userCartsService.removeProduct(userid,userCartsDetails.getProductid());
		
	}
}
