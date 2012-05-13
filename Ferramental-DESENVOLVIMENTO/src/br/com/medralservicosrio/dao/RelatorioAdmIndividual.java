package br.com.medralservicosrio.dao;

import java.util.Date;

public class RelatorioAdmIndividual {

	private Integer idProduto;
	private Integer idIndividual;
	private Integer idFuncionario;
	private Integer rastreabilidade;
	private Integer quantidade;
	private Integer movimentacao;
	private Integer durabilidadeProduto;
	private String produto;
	private String status;
	private String tipoRequisicao;
	private String nome;
	private Date dataEntrega;
	private Integer utilizado;
	
	public RelatorioAdmIndividual() {
	}

	public Integer getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}

	public Integer getIdIndividual() {
		return idIndividual;
	}

	public void setIdIndividual(Integer idIndividual) {
		this.idIndividual = idIndividual;
	}

	public Integer getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(Integer idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public Integer getRastreabilidade() {
		return rastreabilidade;
	}

	public void setRastreabilidade(Integer rastreabilidade) {
		this.rastreabilidade = rastreabilidade;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Integer getMovimentacao() {
		return movimentacao;
	}

	public void setMovimentacao(Integer movimentacao) {
		this.movimentacao = movimentacao;
	}

	public Integer getDurabilidadeProduto() {
		return durabilidadeProduto;
	}

	public void setDurabilidadeProduto(Integer durabilidadeProduto) {
		this.durabilidadeProduto = durabilidadeProduto;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = "off".equalsIgnoreCase(status) ?"Novo":"Reutilizado" ;
	}

	public String getTipoRequisicao() {
		return tipoRequisicao;
	}

	public void setTipoRequisicao(String tipoRequisicao) {
		this.tipoRequisicao = "off".equalsIgnoreCase(tipoRequisicao)? "Troca" : "1º Entrega";
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public Integer getUtilizado() {
		return utilizado;
	}

	public void setUtilizado(Integer utilizado) {
		this.utilizado = utilizado;
	}
	
	
}
