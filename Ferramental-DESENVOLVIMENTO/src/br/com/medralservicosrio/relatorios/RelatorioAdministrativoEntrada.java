package br.com.medralservicosrio.relatorios;

import java.util.Date;

public class RelatorioAdministrativoEntrada {

	private Integer idFornecedor;
	private Integer numNota;
	private Integer quantidade;
	private Date data;
	private String produto;
	private Double valor;
	private Double total;
	
	public RelatorioAdministrativoEntrada() {
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

	/**
	 * @return the quantidade
	 */
	public Integer getQuantidade() {
		return quantidade;
	}
	/**
	 * @param quantidade the quantidade to set
	 */
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Integer getIdFornecedor() {
		return idFornecedor;
	}

	public void setIdFornecedor(Integer idFornecedor) {
		this.idFornecedor = idFornecedor;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}
}
