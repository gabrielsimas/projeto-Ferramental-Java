/**
 * 
 */
package br.com.medralservicosrio.relatorios;

import java.util.Date;

/**
 * Classe que é um espelho do relatorio, os campos Estaticos são referentes a busca
 * @author Felipe Tavares
 *
 */
public class RelatorioGerencialCompras {

	
	private Integer idProduto;
	private String produto;
	private Integer qtd;
	private Integer numNota;
	private Integer qtdEstoque;
	private Integer qtdFuncionando;
	private Integer qtdReformado;
	private Integer qtdTeste;
	private Integer qtdVeiculo;
	private Date data;
	private Double valor;
	private Double valorUnitario;
		
	public RelatorioGerencialCompras() {
		produto = "";
		data = new Date();
		this.numNota = 0;
		this.qtd = 0;
		this.qtdEstoque = 0;
		this.qtdFuncionando = 0;
		this.qtdReformado = 0;
		this.qtdTeste = 0;
		this.qtdVeiculo = 0;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public void setValor(Double valor) {
		this.valor = valor;
		this.valorUnitario = valor;
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

	/**
	 * @return the valor
	 */
	public Double getValor() {
		
		return valor * qtd;
	}
	

	public Double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public Integer getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}

}