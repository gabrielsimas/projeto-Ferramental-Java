package br.com.medralservicosrio.relatorios;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public class RelatorioGerencialSucata {

	private String matricula;
	private String nome;
	private String produto;
	private Double valor;
	private BigInteger rastreabilidade;
	private Date data;
	
	public RelatorioGerencialSucata() {
	}

	public RelatorioGerencialSucata(Date data, String matricula, String nome, String produto, BigDecimal valor, BigInteger rastreabilidade) {
		this.data = data;
		this.matricula = matricula;
		this.nome = nome;
		this.produto = produto;
		this.valor = valor.doubleValue();
		this.rastreabilidade = rastreabilidade;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor.doubleValue();
	}

	public BigInteger getRastreabilidade() {
		return rastreabilidade;
	}

	public void setRastreabilidade(BigInteger rastreabilidade) {
		this.rastreabilidade = rastreabilidade;
	}
	
}
