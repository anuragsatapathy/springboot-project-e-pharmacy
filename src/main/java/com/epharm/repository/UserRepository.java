package com.epharm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epharm.entity.User;

public interface UserRepository extends JpaRepository<User,Integer> {

	User findByEmail(String email);
}
