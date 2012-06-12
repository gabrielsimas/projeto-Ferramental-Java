package br.com.medralservicosrio.controlador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.com.medralservicosrio.dao.MovimentacaoIndividualDAO;
import br.com.medralservicosrio.dao.UsuarioDAO;
import br.com.medralservicosrio.modelo.MovimentacaoIndividual;
import br.com.medralservicosrio.modelo.Produto;

public class MovimentacaoIndividualBean implements Serializable{

	
	
	private static final long serialVersionUID = 1L;
	
	private MovimentacaoIndividual movimentacaoIndividual;
	private List<Produto>  listaProduto;
	private MovimentacaoIndividualDAO  movimentacaoIndividualDAO;
	
	
	public MovimentacaoIndividualBean() {
	      movimentacaoIndividual = new MovimentacaoIndividual();
	      listaProduto = new ArrayList<Produto>();
	      movimentacaoIndividualDAO = new MovimentacaoIndividualDAO();
	}


	


	public MovimentacaoIndividual getMovimentacaoIndividual() {
		return movimentacaoIndividual;
	}


	
	public void setMovimentacaoIndividual(
			MovimentacaoIndividual movimentacaoIndividual) {
		this.movimentacaoIndividual = movimentacaoIndividual;
	}
//	


	public List<Produto> getListaProduto() {
		return listaProduto;
	}





	public void setListaProduto(List<Produto> listaProduto) {
		this.listaProduto = listaProduto;
	}





	public MovimentacaoIndividualDAO getMovimentacaoIndividualDAO() {
		return movimentacaoIndividualDAO;
	}


	public void setMovimentacaoIndividualDAO(
			MovimentacaoIndividualDAO movimentacaoIndividualDAO) {
		this.movimentacaoIndividualDAO = movimentacaoIndividualDAO;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	public void rastrear(int quantidade){
		//rastrea produto
		
		
	}
	
	public void inserirQuantidade(Integer quantidade){
		// verifica quantidade disponivel no estoque
		//if(true)
		 //listaProduto.add();
		//else
			
	}
	
	
	public String gravar(){
		FacesContext fc = FacesContext.getCurrentInstance();
		
				
		fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,null, "Usuário ou senha inválidos!"));
		return new MovimentacaoIndividualDAO().create(movimentacaoIndividual) ? "principal?faces-redirect=true" :"";
	}
	
	
	
	
	
	
	
	
	
	
	

}
