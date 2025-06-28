package com.epharm.service;

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

}
