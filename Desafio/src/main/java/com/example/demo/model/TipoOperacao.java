package com.example.demo.model;

public enum TipoOperacao {
	
	DEBITO("Débito"),
	BOLETO("Boleto"),
	FINANCIAMENTO("Financiamento"),
	CREDITO("Crédito"),
	EMPRESTIMO("Recebimento Empréstimo"),
	VENDAS("Vendas"),
	TED("Recebimento TED"),
	DOC("Recebimento DOC"),
	ALUGUEL("Aluguel");
	
	private final String tipoOperacao;

    TipoOperacao(String tipo) {
        this.tipoOperacao = tipo;
    }

    public String getTipoOperacao() {
        return this.tipoOperacao;
    }

}
