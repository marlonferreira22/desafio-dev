package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Doc;
import com.example.demo.model.Financeiro;

public interface FinanceiroRepository extends JpaRepository<Financeiro,Integer>{

	Optional<Financeiro> findAllByLoja(String nome);

	List<Financeiro> findAllByLojaId(Integer lojaId);

}
