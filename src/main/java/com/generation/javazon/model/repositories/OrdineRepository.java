package com.generation.javazon.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.javazon.model.entity.Ordine;

public interface OrdineRepository extends JpaRepository<Ordine, Integer>
{

}
