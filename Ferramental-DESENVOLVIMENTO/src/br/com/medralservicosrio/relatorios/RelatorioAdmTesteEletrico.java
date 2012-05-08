package br.com.medralservicosrio.relatorios;

import java.util.Date;

public class RelatorioAdmTesteEletrico {

	private String produto;
	private Integer rastreabilidade;
	private Double valor;
	private Date data;
	private String matricula;
	private String funcionario;
	
	public RelatorioAdmTesteEletrico() {
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
	 * @return the rastreabilidade
	 */
	public Integer getRastreabilidade() {
		return rastreabilidade;
	}

	/**
	 * @param rastreabilidade the rastreabilidade to set
	 */
	public void setRastreabilidade(Integer rastreabilidade) {
		this.rastreabilidade = rastreabilidade;
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
	
}
