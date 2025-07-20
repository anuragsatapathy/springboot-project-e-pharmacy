package com.epharm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epharm.entity.Products;
import com.epharm.entity.UserCarts;
import com.epharm.repository.UserCartsRepository;

@Service
public class UserCartsService {

	@Autowired
	private UserCartsRepository userCartsRepository;
	@Autowired
	private ProductsService productsService;
	
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
	
		public boolean removeProduct(Integer userid,Integer productid) {
		
		UserCarts userCarts =userCartsRepository.findByUseridAndProductid(userid, productid);
		
		if( userCarts != null) {
			 
			 if(userCarts.getProductquantity() == 1) {
				 userCartsRepository.delete(userCarts);
			 }
			 else {
				 userCarts.setProductquantity( userCarts.getProductquantity()-1); 
				 userCartsRepository.save(userCarts); 
			 }
		}
		
		return true;
	}
	
	public Integer getQuantity(Integer userid, Integer productid) {
		
		UserCarts usercarts = userCartsRepository.findByUseridAndProductid(userid, productid);
		if(usercarts != null) {
			return usercarts.getProductquantity();
		}
		else {
			return 0;
		}
		
	}
	
	public List<ProductList> getAllCartsProducts(Integer userid){
		
		List<UserCarts> usercarts = userCartsRepository.findAllByUserid(userid);
		
		List<ProductList> list = new ArrayList<>();
		for(UserCarts k : usercarts) {
			ProductList productlist = new ProductList();
			Products product = productsService.getProductsById(k.getProductid());
			productlist.setId(k.getProductid());
			productlist.setName(product.getName());
			productlist.setPrice(product.getPrice());
			productlist.setProductquantity(k.getProductquantity());
			list.add(productlist);
		}
		return list;
		
	}
	
	@Transactional
	public boolean deleteCart(Integer userid) {
		
		userCartsRepository.deleteAllByUserid(userid);
		return true;
	}
}
