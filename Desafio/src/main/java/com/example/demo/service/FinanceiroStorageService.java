package com.example.demo.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Financeiro;
import com.example.demo.repository.FinanceiroRepository;

@Service
public class FinanceiroStorageService {
	
  @Autowired
  private FinanceiroRepository financeiroRepository;
  
  public Financeiro saveFinanceiro(Financeiro fin) {
	  return financeiroRepository.save(fin);
  }
  
  public List<Financeiro> getFinancas(){
	  
	  // Faz um Distinct pelo nome da loja para que não sejam enviadas para o frontend lojas duplicadas
	  return financeiroRepository.findAll().stream().filter(distinctByKey(p -> p.getLoja().getNome())).collect(Collectors.toList());
  }
  
  public List<Financeiro> getFinancasByLojaId(Integer lojaId) {
	return financeiroRepository.findAllByLojaId(lojaId);
  }
  
  public static <T> Predicate<T> distinctByKey(
		    Function<? super T, ?> keyExtractor) {
		  
		    Map<Object, Boolean> seen = new ConcurrentHashMap<>(); 
		    return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null; 
		}
}
