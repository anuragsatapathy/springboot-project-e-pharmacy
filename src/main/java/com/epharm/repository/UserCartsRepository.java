package com.epharm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epharm.entity.UserCarts;

public interface UserCartsRepository extends JpaRepository<UserCarts,Integer>{

   UserCarts findByUseridAndProductid(Integer userid,Integer productid);
}
