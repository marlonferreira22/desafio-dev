package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Doc;
import com.example.demo.model.Financeiro;
import com.example.demo.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer>{
	
	public Usuario findByCpf(String cpf);

}
