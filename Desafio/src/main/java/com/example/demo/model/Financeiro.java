package com.example.demo.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="financeiro")
public class Financeiro {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;	
	private String tipo;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date data;
	private BigDecimal valor;
	private String cartao;
	@DateTimeFormat(pattern = "HH:mm:ss")
	private Time hora;
	/*private String loja;*/
	
	@ManyToOne
	@JoinColumn(name="usuario_id")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name="loja_id")
	private Loja loja;
	
	public Financeiro() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getCartao() {
		return cartao;
	}

	public void setCartao(String cartao) {
		this.cartao = cartao;
	}

	public Time getHora() {
		return hora;
	}

	public void setHora(Time hora) {
		this.hora = hora;
	}

	public Loja getLoja() {
		return loja;
	}

	public void setLoja(Loja loja) {
		this.loja = loja;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Financeiro [id=" + id + ", tipo=" + tipo + ", data=" + data + ", valor=" + valor + ", cartao=" + cartao
				+ ", hora=" + hora + ", loja=" + loja + ", usuario=" + usuario + "]";
	}

	public Financeiro(Integer id, String tipo, Date data, BigDecimal valor, String cartao, Time hora, Loja loja,
			Usuario usuario) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.data = data;
		this.valor = valor;
		this.cartao = cartao;
		this.hora = hora;
		this.loja = loja;
		this.usuario = usuario;
	}	
	
	
}
