package com.example.demo.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="loja")
public class Loja {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;	
	private String nome;
	
	public Loja() {
		
	}	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Loja [id=" + id + ", nome=" + nome + "]";
	}

	public Loja(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
	
	public Loja(String nome) {
		super();
		this.nome = nome;
	}

	
	
}
