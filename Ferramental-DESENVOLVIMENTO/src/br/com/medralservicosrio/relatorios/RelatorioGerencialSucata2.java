package br.com.medralservicosrio.relatorios;

import java.math.BigDecimal;

public class RelatorioGerencialSucata2 {

	private String produto;
	private BigDecimal valor;
	public String getProduto() {
		return produto;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	public RelatorioGerencialSucata2() {
	}
	

	public RelatorioGerencialSucata2(String produto, BigDecimal valor) {
		this.produto = produto;
		this.valor = valor;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
}
