package br.com.medralservicosrio.relatorios;

import java.util.Date;


public class RelatorioAdmVales {

	private String matricula;
	private String nome;
	private String setor;
	private String produto;
	private String status;
	private String dividido;
	private Double valor;
	private Date data;
	
	public RelatorioAdmVales() {
	}

	/**
	 * @return the matricula
	 */
	public String getMatricula() {
		return matricula;
	}

	/**
	 * @param matricula the matricula to set
	 */
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the setor
	 */
	public String getSetor() {
		return setor;
	}

	/**
	 * @param setor the setor to set
	 */
	public void setSetor(String setor) {
		this.setor = setor;
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
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the dividido
	 */
	public String getDividido() {
		return dividido;
	}

	/**
	 * @param dividido the dividido to set
	 */
	public void setDividido(String dividido) {
		this.dividido = dividido;
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
