package com.epharm.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.epharm.entity.Products;

public interface ProductsRepository extends JpaRepository<Products,Integer>{

	
}
