package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Doc;
import com.example.demo.model.Financeiro;
import com.example.demo.model.Loja;
import com.example.demo.model.Usuario;

public interface LojaRepository extends JpaRepository<Loja,Integer>{

	Loja findByNome(String name);
	
}
