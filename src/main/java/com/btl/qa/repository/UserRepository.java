package com.btl.qa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.btl.qa.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	User findOneByUsername(String username);
	User findOneById(Long id);
	List<User> findAll();
}
