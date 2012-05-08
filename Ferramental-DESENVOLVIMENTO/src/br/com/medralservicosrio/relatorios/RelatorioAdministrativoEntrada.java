package br.com.medralservicosrio.relatorios;

import java.util.Date;

import br.com.medralservicosrio.modelo.Produto;

public class RelatorioAdministrativoEntrada {

	private Integer numNota;
	private Integer quantidade;
	private String fornecedor;
	private Date data;
	private Produto produto;
	
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
	 * @return the fornecedor
	 */
	public String getFornecedor() {
		return fornecedor;
	}
	/**
	 * @param fornecedor the fornecedor to set
	 */
	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
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
	
}
