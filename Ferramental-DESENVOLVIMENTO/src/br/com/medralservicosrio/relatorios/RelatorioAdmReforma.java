package br.com.medralservicosrio.relatorios;

import java.util.Date;

public class RelatorioAdmReforma {

	private String produto;
	private String funcionario;
	private String rastreamento;
	private Double valor;
	private Date data;
	
	public RelatorioAdmReforma() {
	}

	/**
	 * @return the produto
	 */
	public String getProduto() {
		return produto;
	}

	/**
	 * @param produto the produto to set
	 */
	public void setProduto(String produto) {
		this.produto = produto;
	}

	/**
	 * @return the funcionario
	 */
	public String getFuncionario() {
		return funcionario;
	}

	/**
	 * @param funcionario the funcionario to set
	 */
	public void setFuncionario(String funcionario) {
		this.funcionario = funcionario;
	}

	/**
	 * @return the rastreamento
	 */
	public String getRastreamento() {
		return rastreamento;
	}

	/**
	 * @param rastreamento the rastreamento to set
	 */
	public void setRastreamento(String rastreamento) {
		this.rastreamento = rastreamento;
	}

	/**
	 * @return the valor
	 */
	public Double getValor() {
		return valor;
	}

	/**
	 * @param valor the valor to set
	 */
	public void setValor(Double valor) {
		this.valor = valor;
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
	
}
