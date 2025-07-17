package com.epharm.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.epharm.entity.OrderDetails;
import com.epharm.entity.Products;
import com.epharm.service.OrderDetailsService;
import com.epharm.service.OrderPlacedDetails;
import com.epharm.service.ProductList;
import com.epharm.service.ProductsService;
import com.epharm.service.UserCartsService;
import com.epharm.service.UserService;

@Controller
public class OrdersController {

	@Autowired
	private UserService userService;
	@Autowired
	private UserCartsService userCartsService;
	@Autowired
	private OrderDetailsService orderDetailsService;
	
	@PostMapping("/place-order")
	public String placeOrder(@RequestBody OrderPlacedDetails orderplaceddetails, Principal principal) {
		
		String email = principal.getName();
		Integer userid = userService.getUserId(email);
		
		String productsdetails = "";
		List<ProductList> list = userCartsService.getAllCartsProducts(userid);
		productsdetails = list.toString();
		double totalprice = 0;
		for(ProductList k : list) {
			
			totalprice = totalprice + (k.getProductquantity()*k.getPrice());
			
		}
		
		OrderDetails orderdetails = new OrderDetails();
		orderdetails.setUserid(userid);
		orderdetails.setTotalprice(totalprice);
		orderdetails.setPaymentmethod(orderplaceddetails.getPaymentmethod());
		orderdetails.setDeliveryaddress(orderplaceddetails.getAddressdetails());
		orderdetails.setProductdetails(productsdetails);
		
		orderDetailsService.insertOrder(orderdetails);
			)
	}
}
