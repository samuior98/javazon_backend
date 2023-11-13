package com.generation.javazon.auth.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.javazon.auth.model.UserInDb;

public interface UserRepository extends JpaRepository<UserInDb, String>
{

	UserInDb findByUsername(String username);

}
