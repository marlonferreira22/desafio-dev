package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Loja;
import com.example.demo.repository.LojaRepository;

@Service
public class LojaStorageService {

  @Autowired
  private LojaRepository lojaRepository;
  
  public Loja saveLoja(Loja loja) {
	  return lojaRepository.save(loja);
  }
  
  public List<Loja> getLojas(){
	  return lojaRepository.findAll();
  }
  
  public Optional<Loja> getLojaById(Integer id) {
	  return lojaRepository.findById(id);
  }
  
  public Loja getLojaByNome(String name) {
	  return lojaRepository.findByNome(name);
  }
  
}
