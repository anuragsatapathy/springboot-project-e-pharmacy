package com.epharm.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epharm.entity.UserCarts;
import com.epharm.repository.UserCartsRepository;

@Service
public class UserCartsService {

	@Autowired
	private UserCartsRepository userCartsRepository;
	
	public boolean addProduct(Integer userid,Integer productid) {
		
		UserCarts userCarts =userCartsRepository.findByUseridAndProductid(userid, productid);
		
		if( userCarts != null) {
			
			 userCarts.setProductquantity( userCarts.getProductquantity()+1); 
			 userCartsRepository.save(userCarts);
		}
		else {
			 UserCarts  userCart = new UserCarts();
			 userCart.setUserid(userid);
			 userCart.setProductid(productid);
			 userCart.setProductquantity(1);
			 userCartsRepository.save(userCart);
		}
		return true;
	}
}
