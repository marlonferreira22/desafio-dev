package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;

@Service
public class UsuarioStorageService {

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
  
}
