/**
 * 
 */
package br.com.medralservicosrio.relatorios;

import java.util.Date;

import br.com.medralservicosrio.modelo.Produto;

/**
 * Classe que é um espelho do relatorio, os campos Estaticos são referentes a busca
 * @author Felipe Tavares
 * TODO Converte para hibernate, esta em JDBC 
 */
public class RelatorioGerencialCompras {

	private Produto produto;
	private Integer qtd;
	private Integer numNota;
	private Integer qtdEstoque;
	private Integer qtdFuncionando;
	private Integer qtdReformado;
	private Integer qtdTeste;
	private Integer qtdVeiculo;
	private Date data;
		
	public RelatorioGerencialCompras() {
		produto = new Produto();
		data = new Date();
		this.numNota = 0;
		this.qtd = 0;
		this.qtdEstoque = 0;
		this.qtdFuncionando = 0;
		this.qtdReformado = 0;
		this.qtdTeste = 0;
		this.qtdVeiculo = 0;
	}

	/**
	 * @return the produto
	 */
	public Produto getProduto() {
		return produto;
	}



	/**
	 * @param produto the produto to set
	 */
	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	/**
	 * @return the qtdEstoque
	 */
	public Integer getQtdEstoque() {
		return qtdEstoque;
	}

	/**
	 * @param qtdEstoque the qtdEstoque to set
	 */
	public void setQtdEstoque(Integer qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}

	/**
	 * @return the qtdFuncionando
	 */
	public Integer getQtdFuncionando() {
		return qtdFuncionando;
	}

	/**
	 * @param qtdFuncionando the qtdFuncionando to set
	 */
	public void setQtdFuncionando(Integer qtdFuncionando) {
		this.qtdFuncionando = qtdFuncionando;
	}

	/**
	 * @return the qtdReformado
	 */
	public Integer getQtdReformado() {
		return qtdReformado;
	}

	/**
	 * @param qtdReformado the qtdReformado to set
	 */
	public void setQtdReformado(Integer qtdReformado) {
		this.qtdReformado = qtdReformado;
	}

	/**
	 * @return the qtdTeste
	 */
	public Integer getQtdTeste() {
		return qtdTeste;
	}

	/**
	 * @param qtdTeste the qtdTeste to set
	 */
	public void setQtdTeste(Integer qtdTeste) {
		this.qtdTeste = qtdTeste;
	}

	/**
	 * @return the qtdVeiculo
	 */
	public Integer getQtdVeiculo() {
		return qtdVeiculo;
	}

	/**
	 * @param qtdVeiculo the qtdVeiculo to set
	 */
	public void setQtdVeiculo(Integer qtdVeiculo) {
		this.qtdVeiculo = qtdVeiculo;
	}

	/**
	 * @return the data
	 */
	public Date getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Date data) {
		this.data = data;
	}

	/**
	 * @return the qtd
	 */
	public Integer getQtd() {
		return qtd;
	}

	/**
	 * @param qtd the qtd to set
	 */
	public void setQtd(Integer qtd) {
		this.qtd = qtd;
	}

	/**
	 * @return the numNota
	 */
	public Integer getNumNota() {
		return numNota;
	}

	/**
	 * @param numNota the numNota to set
	 */
	public void setNumNota(Integer numNota) {
		this.numNota = numNota;
	}
	
}