package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Doc;
import com.example.demo.model.Financeiro;
import com.example.demo.model.Loja;
import com.example.demo.model.Usuario;
import com.example.demo.repository.DocRepository;
import com.example.demo.repository.FinanceiroRepository;
import com.example.demo.repository.LojaRepository;
import com.example.demo.repository.UsuarioRepository;

@Service
public class LojaStorageService {
  @Autowired
  private DocRepository docRepository;
  @Autowired
  private FinanceiroRepository financeiroRepository;
  @Autowired
  private UsuarioRepository usuarioRepository;
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
