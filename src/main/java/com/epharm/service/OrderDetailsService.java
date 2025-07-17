package com.epharm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epharm.entity.OrderDetails;
import com.epharm.repository.OrderDetailsRepository;

@Service
public class OrderDetailsService {

	@Autowired
	private OrderDetailsRepository orderDetailsRepository;
	
	public boolean insertOrder(OrderDetails orderdetails) {
		
		orderDetailsRepository.save(orderdetails);
		return true;
	}
}
