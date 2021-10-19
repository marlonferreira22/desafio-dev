package com.example.demo.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;	
	private String nome;
	private String cpf;
	private BigDecimal saldo;
	
	public Usuario() {
		
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", saldo=" + saldo + "]";
	}

	public Usuario(Integer id, String nome, String cpf, BigDecimal saldo) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.saldo = saldo;
	}
	
	public Usuario(String nome, String cpf, BigDecimal saldo) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.saldo = saldo;
	}

	
	
}
