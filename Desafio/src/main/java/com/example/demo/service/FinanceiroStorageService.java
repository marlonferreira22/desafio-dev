package com.example.demo.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Doc;
import com.example.demo.model.Financeiro;
import com.example.demo.repository.DocRepository;
import com.example.demo.repository.FinanceiroRepository;

@Service
public class FinanceiroStorageService {
  @Autowired
  private DocRepository docRepository;
  @Autowired
  private FinanceiroRepository financeiroRepository;
  
  public Financeiro saveFinanceiro(Financeiro fin) {
	  return financeiroRepository.save(fin);
  }
  
  public List<Financeiro> getFinancas(){
	  
	  return financeiroRepository.findAll().stream().filter(distinctByKey(p -> p.getLoja().getNome())).collect(Collectors.toList());
  }
  
  /*public Optional<Financeiro> getFinancasPorNome(String nome){
	  return financeiroRepository.findAllByLoja(nome);
  }*/

  public List<Financeiro> getFinancasByLojaId(Integer lojaId) {
	return financeiroRepository.findAllByLojaId(lojaId);
  }
  
  public static <T> Predicate<T> distinctByKey(
		    Function<? super T, ?> keyExtractor) {
		  
		    Map<Object, Boolean> seen = new ConcurrentHashMap<>(); 
		    return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null; 
		}
  
  /*public Doc saveFile(MultipartFile file) {
	  String docname = file.getOriginalFilename();
	  try {
		  Doc doc = new Doc(docname,file.getContentType(),file.getBytes());
		  return docRepository.save(doc);
	  }
	  catch(Exception e) {
		  e.printStackTrace();
	  }
	  return null;
  }
  public Optional<Doc> getFile(Integer fileId) {
	  return docRepository.findById(fileId);
  }
  public List<Doc> getFiles(){
	  return docRepository.findAll();
  }*/
}
