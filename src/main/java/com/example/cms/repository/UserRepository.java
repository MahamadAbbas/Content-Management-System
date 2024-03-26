package com.example.cms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cms.model.User;

//@Repository // bez we wont create a object of these 
public interface UserRepository extends JpaRepository<User, Integer>{

	 boolean existsByEmail(String email);
	 
	 Optional<User> findByEmail(String email);

}
