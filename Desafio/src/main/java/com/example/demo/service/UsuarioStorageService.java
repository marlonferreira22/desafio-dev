package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Doc;
import com.example.demo.model.Financeiro;
import com.example.demo.model.Usuario;
import com.example.demo.repository.DocRepository;
import com.example.demo.repository.FinanceiroRepository;
import com.example.demo.repository.UsuarioRepository;

@Service
public class UsuarioStorageService {
  @Autowired
  private DocRepository docRepository;
  @Autowired
  private FinanceiroRepository financeiroRepository;
  @Autowired
  private UsuarioRepository usuarioRepository;
  
  public Usuario saveUsuario(Usuario user) {
	  return usuarioRepository.save(user);
  }
  
  public List<Usuario> getUsuarios(){
	  return usuarioRepository.findAll();
  }
  
  public Usuario getUsuario(String cpf) {
	  return usuarioRepository.findByCpf(cpf);
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
