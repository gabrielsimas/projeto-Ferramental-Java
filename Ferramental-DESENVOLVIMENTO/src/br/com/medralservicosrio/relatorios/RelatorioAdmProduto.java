package br.com.medralservicosrio.relatorios;

import java.util.Date;

/**
 * Classe usada para o relatorio Individual e Veiculo
 * @author Felipe Tavares
 */
public class RelatorioAdmProduto {

	private String produto;
	private String status;
	private String movimentacao;
	private Integer durabilidade;
	private Integer utilizado;
	private Integer rastreamento;
	private Integer quantidade;
	private Date dataEntrada;

	public RelatorioAdmProduto() {
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
	 * @return the movimentacao
	 */
	public String getMovimentacao() {
		return movimentacao;
	}

	/**
	 * @param movimentacao the movimentacao to set
	 */
	public void setMovimentacao(String movimentacao) {
		this.movimentacao = movimentacao;
	}

	/**
	 * @return the dataEntrada
	 */
	public Date getDataEntrada() {
		return dataEntrada;
	}

	/**
	 * @param dataEntrada the dataEntrada to set
	 */
	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	/**
	 * @return the durabilidade
	 */
	public Integer getDurabilidade() {
		return durabilidade;
	}

	/**
	 * @param durabilidade the durabilidade to set
	 */
	public void setDurabilidade(Integer durabilidade) {
		this.durabilidade = durabilidade;
	}

	/**
	 * @return the utilizado
	 */
	public Integer getUtilizado() {
		return utilizado;
	}

	/**
	 * @param utilizado the utilizado to set
	 */
	public void setUtilizado(Integer utilizado) {
		this.utilizado = utilizado;
	}

	/**
	 * @return the rastreamento
	 */
	public Integer getRastreamento() {
		return rastreamento;
	}

	/**
	 * @param rastreamento the rastreamento to set
	 */
	public void setRastreamento(Integer rastreamento) {
		this.rastreamento = rastreamento;
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
