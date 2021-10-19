package com.example.demo;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.model.Loja;
import com.example.demo.repository.LojaRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class LojaRepositoryTest {
	
	@Autowired
	private LojaRepository lojaRepository;	
	
	@Test
	public void cadastraLoja() {
		
		Loja loja = new Loja("Loja 1");
		this.lojaRepository.save(loja);
		
		Assertions.assertThat(loja.getId()).isNotNull();
		Assertions.assertThat(loja.getNome()).isEqualTo("Loja 1");
	}
	
	@Test
	public void deletaLoja() {
		Loja loja = new Loja("Loja 1");
		this.lojaRepository.save(loja);
		lojaRepository.delete(loja);
		
		Assertions.assertThat(lojaRepository.findById(loja.getId())).isNotNull();
	}
	
	@Test
	public void atualizaLoja() {
		Loja loja = new Loja("Loja 1");
		this.lojaRepository.save(loja);
		loja.setNome("Loja 2");
		this.lojaRepository.save(loja);
		Optional<Loja> lojaObj = this.lojaRepository.findById(loja.getId());
		
		Assertions.assertThat(lojaObj.get().getNome()).isEqualTo("Loja 2");
	}
	
	@Test
	public void pesquisaLojaPorNome() {
		Loja loja = new Loja("Loja 1");
		Loja loja2 = new Loja("Loja 2");
		this.lojaRepository.save(loja);
		this.lojaRepository.save(loja2);
		Loja lojaObj = lojaRepository.findByNome("Loja 2");
		
		Assertions.assertThat(lojaObj.getNome()).isEqualTo("Loja 2");
	}
}
