package br.com.medralservicosrio.controlador;

import java.io.Serializable;

import br.com.medralservicosrio.dao.MovimentacaoDAO;
import br.com.medralservicosrio.modelo.Movimentacao;

public class MovimentacaoBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4801581874776742107L;
	
	private Movimentacao movimentacao;
	private MovimentacaoDAO movimentacaoDAO;
	
	
	public MovimentacaoBean() {
		movimentacao = new Movimentacao();
		movimentacaoDAO = new MovimentacaoDAO();
				
	}


	public Movimentacao getMovimentacao() {
		return movimentacao;
	}


	public void setMovimentacao(Movimentacao movimentacao) {
		this.movimentacao = movimentacao;
	}


	public MovimentacaoDAO getMovimentacaoDAO() {
		return movimentacaoDAO;
	}


	public void setMovimentacaoDAO(MovimentacaoDAO movimentacaoDAO) {
		this.movimentacaoDAO = movimentacaoDAO;
	}
	
	public void verificar(){
		//verificar dados
	}
	
	
	public void gravar(){
		//gravar dados
	}
		
	public void rastrear(){
		//rastrear dados
	}
	
	
	public void listarVeiculos(){
		//rastrear dados
	}
	
	
	

}
