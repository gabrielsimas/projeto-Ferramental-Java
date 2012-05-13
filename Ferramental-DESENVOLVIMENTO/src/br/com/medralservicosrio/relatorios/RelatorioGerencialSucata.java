package br.com.medralservicosrio.relatorios;

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

	public RelatorioGerencialSucata(Date data, String matricula, String nome, String produto, Double valor, BigInteger rastreabilidade) {
		this.data = data;
		this.matricula = matricula;
		this.nome = nome;
		this.produto = produto;
		this.valor = valor;
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

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public BigInteger getRastreabilidade() {
		return rastreabilidade;
	}

	public void setRastreabilidade(BigInteger rastreabilidade) {
		this.rastreabilidade = rastreabilidade;
	}
	
}
