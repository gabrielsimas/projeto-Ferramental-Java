package br.com.medralservicosrio.relatorios;

import java.util.Date;

public class RelatorioAdmMovimentacao {

	private Integer tipo;
	private String chapa;
	private String funcionario;
	private Date data;
	private String motivo;
	private String produto;
	private String status;
	private String rastreamento;
	private String destino;
	private Integer quantidade;
	private String usuario;
	
	public RelatorioAdmMovimentacao() {
	}

	/**
	 * @return the tipo
	 */
	public Integer getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the chapa
	 */
	public String getChapa() {
		return chapa;
	}

	/**
	 * @param chapa the chapa to set
	 */
	public void setChapa(String chapa) {
		this.chapa = chapa;
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
	 * @return the motivo
	 */
	public String getMotivo() {
		return motivo;
	}

	/**
	 * @param motivo the motivo to set
	 */
	public void setMotivo(String motivo) {
		this.motivo = motivo;
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
	 * @return the destino
	 */
	public String getDestino() {
		return destino;
	}

	/**
	 * @param destino the destino to set
	 */
	public void setDestino(String destino) {
		this.destino = destino;
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

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
}
