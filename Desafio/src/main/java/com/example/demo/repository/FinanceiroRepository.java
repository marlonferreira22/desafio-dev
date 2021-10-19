package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Financeiro;

public interface FinanceiroRepository extends JpaRepository<Financeiro,Integer>{

	List<Financeiro> findAllByLojaId(Integer lojaId);

}
