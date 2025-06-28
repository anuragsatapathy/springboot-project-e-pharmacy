package com.epharm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epharm.entity.Products;

public interface ProductsRepository extends JpaRepository<Products,Integer>{

}
