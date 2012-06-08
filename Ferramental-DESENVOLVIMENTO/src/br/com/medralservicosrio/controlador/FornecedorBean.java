package br.com.medralservicosrio.controlador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.hibernate.QueryException;

import br.com.medralservicosrio.dao.FornecedorDAO;
import br.com.medralservicosrio.dao.MovimentacaoDAO;
import br.com.medralservicosrio.dao.MovimentacaoIndividualDAO;
import br.com.medralservicosrio.generics.GenericBean;
import br.com.medralservicosrio.modelo.Fornecedor;
import br.com.medralservicosrio.modelo.Funcionario;
import br.com.medralservicosrio.modelo.MovimentacaoIndividual;
import br.com.medralservicosrio.modelo.Produto;

@ManagedBean(name="fornecedorBean")
@SessionScoped
public class FornecedorBean extends GenericBean implements Serializable{
	
	private static final long serialVersionUID = 6298337963317124483L;
	private boolean atualizacao;
	private String textoBotao = "Cadastrar";
	private Fornecedor fornecedor;
	private List<Fornecedor> fornecedores;
	private FornecedorDAO dao;
	
	public FornecedorBean() {
		//dao = new FornecedorDAO(Fornecedor.class);
		dao = new FornecedorDAO();
		fornecedor = new Fornecedor();
		fornecedores = new ArrayList<Fornecedor>();
		this.textoBotao = "Cadastrar";
		this.atualizacao = true;
	}
		

	@Override
	public void cadastrar() {

		FacesContext fc = FacesContext.getCurrentInstance();

		try {

			//Salva o registro
			//Verifica se é alteração ou cadastro
			if (atualizacao){
				dao.atualizar(fornecedor);
				atualizacao = false;
				textoBotao = "Cadastrar";
				fornecedor = new Fornecedor();

			} else {

				dao.criar(fornecedor);
				atualizacao = false;
				textoBotao = "Cadastrar";
				fornecedor = new Fornecedor();
				fc.addMessage(null, new FacesMessage(getMENSAGEM_INFO(),null,"Fornecedor cadastrado com sucesso!!"));
			}
			
			limpar();

		} catch (Exception e){
			fc.addMessage(null, new FacesMessage(this.getMENSAGEM_FATAL(),null,e.getMessage()));
		}		
	}

	@Override
	public void atualizar(ActionEvent evento) {
		
		FacesContext fc = FacesContext.getCurrentInstance();
		
		try{
			
			dao.atualizar(fornecedor);
			fc.addMessage(null, new FacesMessage(getMENSAGEM_INFO(),null,"Fornecedor atualizado com sucesso!!"));
			fornecedor = new Fornecedor();
			
		} catch (Exception e){
			fc.addMessage(null, new FacesMessage(this.getMENSAGEM_FATAL(),null,"Erro: " + e.getMessage()));
		}
	}
	
	@Override
	public void excluir(ActionEvent evento) {
		
		FacesContext fc = FacesContext.getCurrentInstance();
		try{

			UIParameter f = (UIParameter) evento.getComponent().findComponent("idFornecedor");
			Integer idFornecedor = (Integer) f.getValue();

			//Obtendo a Loja para ser excluída
			Fornecedor fornecedorExcluir = (Fornecedor) dao.listarPorId(Fornecedor.class, idFornecedor);
			dao.apagar(fornecedorExcluir);     //Fornecedor fornecedorExcluir = (Fornecedor) dao.listarPorId(Fornecedor.class,matriculaFuncionario);  Apaga o Funcionário

			//Imprime a mensagem
			fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,null,"Fornecedor " + fornecedorExcluir.getNome() + " excluido com sucesso!"));

			//Zera os objetos utilizados
			fornecedorExcluir = null;
			fornecedor = new Fornecedor();
			
		} catch (Exception ex) {
			fc.addMessage(null, new FacesMessage(this.getMENSAGEM_FATAL(),null,"Erro: " + ex.getMessage()));
		}
	}
	
	public void limpar(){
		
		FacesContext fc = FacesContext.getCurrentInstance();
		
		if (fornecedor != null){
			fornecedor = null;
			fornecedor = new Fornecedor();
			fc.addMessage(null, new FacesMessage(getMENSAGEM_INFO(),null,"Limpeza efetuada com sucesso!"));
			
		} else {
			
			fc.addMessage(null, new FacesMessage(getMENSAGEM_INFO(),null,"Não há o que limpar!"));
		}
	}
	
   public String voltar(){
		
		/*FacesContext.getCurrentInstance().getExternalContext().invalidateSession();*/
		return "principal?faces-redirect=true";
	}

	public boolean isAtualizacao() {
		return atualizacao;
	}

	public void setAtualizacao(boolean atualizacao) {
		this.atualizacao = atualizacao;
	}

	public String getTextoBotao() {
		return textoBotao;
	}

	public void setTextoBotao(String textoBotao) {
		this.textoBotao = textoBotao;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public List<Fornecedor> getFornecedores() {
			
		if (fornecedores.isEmpty() || atualizacao != false){
			fornecedores = dao.listarTudo(Fornecedor.class);
			atualizacao = false;
		}
		
		return fornecedores;
	}

	public void setFornecedores(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}

	public FornecedorDAO getDao() {
		return dao;
	}

	public void setDao(FornecedorDAO dao) {
		this.dao = dao;
	}

}
