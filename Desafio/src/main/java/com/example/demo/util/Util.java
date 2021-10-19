package com.example.demo.util;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.example.demo.model.TipoOperacao;

public class Util {
	
	public BigDecimal convertStringToBigDecimal(String valor) {
		
		BigDecimal valorConvertido = new BigDecimal(valor);
		BigDecimal divisor = new BigDecimal("100.00");
		return valorConvertido.divide(divisor);
	}
	
	public Date convertStringToDate(String data) throws ParseException{
		
		SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");
		return new Date(formato.parse(data).getTime());
		
	}
	
	public Time convertStringToHours(String hora) throws ParseException{
		
		SimpleDateFormat formato = new SimpleDateFormat("HHmmss");		
		return new Time(formato.parse(hora).getTime());
	}
	
	public TipoOperacao convetStringToTipoOperacao(String tipo) {
		
		TipoOperacao result;
		
		switch (tipo) {
		case "1":
			result = TipoOperacao.DEBITO;
			break;
		case "2":
			result = TipoOperacao.BOLETO;
			break;
		case "3":
			result = TipoOperacao.FINANCIAMENTO;
			break;
		case "4":
			result = TipoOperacao.CREDITO;
			break;
		case "5":
			result = TipoOperacao.EMPRESTIMO;
			break;
		case "6":
			result = TipoOperacao.VENDAS;
			break;
		case "7":
			result = TipoOperacao.TED;
			break;
		case "8":
			result = TipoOperacao.DOC;
			break;
		case "9":
			result = TipoOperacao.ALUGUEL;
			break;
		default:
			result = TipoOperacao.DEBITO;
			break;
		}
		
		return result;
	}

}
