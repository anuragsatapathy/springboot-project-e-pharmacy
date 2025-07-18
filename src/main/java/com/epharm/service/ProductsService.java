package com.epharm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epharm.entity.Products;
import com.epharm.repository.ProductsRepository;

@Service
public class ProductsService {

	@Autowired
	private ProductsRepository productsRepository;
	

	public boolean createProduct(Products product) {

		productsRepository.save(product);
		return true;

	}
	
	public List<Products> ListProduct(){
		List<Products> products = productsRepository.findAll();
		
		return products;
		
	}
	
	public boolean updateProduct(Products product) {
		productsRepository.save(product);
		return true;
		
	}
	
	public boolean deleteProduct(Integer id) {
		productsRepository.deleteById(id);
		return true;
	}
	
	public Products getProductsById(Integer productid) {
		
		Optional<Products> product =  productsRepository.findById(productid);
	
		if(product.isPresent()) {
			return product.get();
		}
		else {
			return null;
		}
		
	}

}
