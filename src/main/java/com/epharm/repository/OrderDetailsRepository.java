package com.epharm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epharm.entity.OrderDetails;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails,Integer>{

}
