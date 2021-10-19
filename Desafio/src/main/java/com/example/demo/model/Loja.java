package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name="loja")
public class Loja {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;	
	private String nome;
	
	public Loja() {
		
	}	
	
	@ApiModelProperty(notes = "Identificador da loja")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	@ApiModelProperty(notes = "Nome da loja")
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
