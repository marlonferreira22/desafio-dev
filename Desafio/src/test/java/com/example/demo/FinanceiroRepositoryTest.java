package com.example.demo;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.model.Financeiro;
import com.example.demo.model.Loja;
import com.example.demo.model.TipoOperacao;
import com.example.demo.model.Usuario;
import com.example.demo.repository.FinanceiroRepository;
import com.example.demo.repository.LojaRepository;
import com.example.demo.repository.UsuarioRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class FinanceiroRepositoryTest {
	
	@Autowired
	private FinanceiroRepository financeiroRepository;
	@Autowired
	private LojaRepository lojaRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void cadastraFinancas() {
		
		Loja loja = new Loja("Loja 1");
		loja = this.lojaRepository.save(loja);
		
		Usuario usuario = new Usuario("Marlon", "12345678909", new BigDecimal("100"));
		usuario = this.usuarioRepository.save(usuario);
		
		Date data = new Date(2021,07,05);
		Time hora = new Time(12, 34, 56);
		TipoOperacao tp = null;
		
		Financeiro fin = new Financeiro(tp.ALUGUEL,data,new BigDecimal("120"), "123***456",hora,loja,usuario);
		fin = this.financeiroRepository.save(fin);
		
		Assertions.assertThat(fin.getId()).isNotNull();
		Assertions.assertThat(fin.getCartao()).isEqualTo("123***456");
	}
	
	@Test
	public void deletaFinancas() {
		
		Loja loja = new Loja("Loja 1");
		loja = this.lojaRepository.save(loja);
		
		Usuario usuario = new Usuario("Marlon", "12345678909", new BigDecimal("100"));
		usuario = this.usuarioRepository.save(usuario);
		
		Date data = new Date(2021,07,05);
		Time hora = new Time(12, 34, 56);
		TipoOperacao tp = null;
		
		Financeiro fin = new Financeiro(tp.ALUGUEL,data,new BigDecimal("120"), "123***456",hora,loja,usuario);
		this.financeiroRepository.save(fin);		
		financeiroRepository.delete(fin);
		
		Assertions.assertThat(financeiroRepository.findById(fin.getId())).isNotNull();
	}
	
	@Test
	public void atualizaFinancas() {
		
		Loja loja = new Loja("Loja 1");
		loja = this.lojaRepository.save(loja);
		
		Usuario usuario = new Usuario("Marlon", "12345678909", new BigDecimal("100"));
		usuario = this.usuarioRepository.save(usuario);
		
		Date data = new Date(2021,07,05);
		Time hora = new Time(12, 34, 56);
		TipoOperacao tp = null;
		
		Financeiro fin = new Financeiro(tp.ALUGUEL,data,new BigDecimal("120"), "123***456",hora,loja,usuario);
		this.financeiroRepository.save(fin);
		
		fin.setCartao("987***654");
		this.financeiroRepository.save(fin);
		Optional<Financeiro> finObj = this.financeiroRepository.findById(fin.getId());
		
		Assertions.assertThat(finObj.get().getCartao()).isEqualTo("987***654");
	}
	
	@Test
	public void pesquisaPeloIdDaLoja() {
		
		Loja loja = new Loja("Loja 1");
		loja = this.lojaRepository.save(loja);
		
		Usuario usuario = new Usuario("Marlon", "12345678909", new BigDecimal("100"));
		usuario = this.usuarioRepository.save(usuario);
		
		Date data = new Date(2021,07,05);
		Time hora = new Time(12, 34, 56);
		TipoOperacao tp = null;
		
		Financeiro fin = new Financeiro(tp.ALUGUEL,data,new BigDecimal("120"), "123***456",hora,loja,usuario);
		Financeiro fin2 = new Financeiro(tp.ALUGUEL,data,new BigDecimal("240"), "987***654",hora,loja,usuario);
		this.financeiroRepository.save(fin);
		this.financeiroRepository.save(fin2);
		
		List<Financeiro> finObj = financeiroRepository.findAllByLojaId(loja.getId());
				
		Assertions.assertThat(finObj.size()).isEqualTo(2);
	}
	
	/*@Test
	public void pesquisaPeloNomeDaLoja() {
		
		Loja loja = new Loja("Loja 1");
		loja = this.lojaRepository.save(loja);
		
		Usuario usuario = new Usuario("Marlon", "12345678909", new BigDecimal("100"));
		usuario = this.usuarioRepository.save(usuario);
		
		Date data = new Date(2021,07,05);
		Time hora = new Time(12, 34, 56);
		TipoOperacao tp = null;
		
		Financeiro fin = new Financeiro(tp.ALUGUEL,data,new BigDecimal("120"), "123***456",hora,loja,usuario);
		Financeiro fin2 = new Financeiro(tp.ALUGUEL,data,new BigDecimal("240"), "987***654",hora,loja,usuario);
		this.financeiroRepository.save(fin);
		this.financeiroRepository.save(fin2);
		
		Optional<Financeiro> finObj = financeiroRepository.findAllByLoja(loja.getNome());
				
		Assertions.assertThat(finObj.get().getLoja().getNome()).isEqualTo("Loja 1");
	}*/
	
	
}
