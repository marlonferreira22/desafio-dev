package com.example.demo;

import java.math.BigDecimal;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UsuarioRepositoryTest {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Test
	public void cadastraUsuario() {
		
		Usuario usuario = new Usuario("Marlon", "12345678909", new BigDecimal("100"));
		this.usuarioRepository.save(usuario);
		
		Assertions.assertThat(usuario.getId()).isNotNull();
		Assertions.assertThat(usuario.getNome()).isEqualTo("Marlon");
	}
	
	@Test
	public void deletaLoja() {
		Usuario usuario = new Usuario("Marlon", "12345678909", new BigDecimal("100"));
		this.usuarioRepository.save(usuario);
		usuarioRepository.delete(usuario);
		
		Assertions.assertThat(usuarioRepository.findById(usuario.getId())).isNotNull();
	}
	
	@Test
	public void atualizaLoja() {
		Usuario usuario = new Usuario("Marlon", "12345678909", new BigDecimal("100"));
		this.usuarioRepository.save(usuario);
		usuario.setNome("Marlon222");
		this.usuarioRepository.save(usuario);
		Optional<Usuario> usuarioObj = this.usuarioRepository.findById(usuario.getId());
		
		Assertions.assertThat(usuarioObj.get().getNome()).isEqualTo("Marlon222");
	}
	
	@Test
	public void pesquisaUsuarioPorCpf() {
		Usuario usuario = new Usuario("Marlon", "12345678909", new BigDecimal("100"));
		Usuario usuario2 = new Usuario("Marlon222", "06943219852", new BigDecimal("200"));
		this.usuarioRepository.save(usuario);
		this.usuarioRepository.save(usuario2);
		Usuario usuarioObj = usuarioRepository.findByCpf("06943219852");
		
		Assertions.assertThat(usuarioObj.getCpf()).isEqualTo("06943219852");
	}
}
