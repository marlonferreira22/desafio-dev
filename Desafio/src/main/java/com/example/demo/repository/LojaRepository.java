package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Loja;

public interface LojaRepository extends JpaRepository<Loja,Integer>{

	Loja findByNome(String name);
	
}
