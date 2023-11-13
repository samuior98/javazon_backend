package com.generation.javazon.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.javazon.model.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>
{

	Cliente findByUsername(String username);

}
