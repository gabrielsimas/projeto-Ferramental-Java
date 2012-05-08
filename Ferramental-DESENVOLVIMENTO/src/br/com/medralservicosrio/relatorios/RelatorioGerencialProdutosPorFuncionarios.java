package br.com.medralservicosrio.relatorios;

import br.com.medralservicosrio.modelo.Produto;

public class RelatorioGerencialProdutosPorFuncionarios {
	
	private Produto produto;
	private Integer quantidade;

	public RelatorioGerencialProdutosPorFuncionarios() {
	
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
