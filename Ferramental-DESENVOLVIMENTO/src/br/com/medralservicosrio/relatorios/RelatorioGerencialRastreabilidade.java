package br.com.medralservicosrio.relatorios;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RelatorioGerencialRastreabilidade {

	private Integer idRastreabilidade;
	private String produto;
	private Date data;
	private Integer tempoDeUso;
	private List listaDetalhes;
	
	public RelatorioGerencialRastreabilidade() {
		listaDetalhes = new ArrayList();
	}

	/**
	 * @return the idRastreabilidade
	 */
	public Integer getIdRastreabilidade() {
		return idRastreabilidade;
	}

	/**
	 * @param idRastreabilidade the idRastreabilidade to set
	 */
	public void setIdRastreabilidade(Integer idRastreabilidade) {
		this.idRastreabilidade = idRastreabilidade;
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
	 * @return the listaDetalhes
	 */
	public List getListaDetalhes() {
		return listaDetalhes;
	}

	/**
	 * @param listaDetalhes the listaDetalhes to set
	 */
	public void setListaDetalhes(List listaDetalhes) {
		this.listaDetalhes = listaDetalhes;
	}
	/**
	 * @return the tempoDeUso
	 */
	public Integer getTempoDeUso() {
		return tempoDeUso;
	}

	/**
	 * @param tempoDeUso the tempoDeUso to set
	 */
	public void setTempoDeUso(Integer tempoDeUso) {
		this.tempoDeUso = tempoDeUso;
	}
}
