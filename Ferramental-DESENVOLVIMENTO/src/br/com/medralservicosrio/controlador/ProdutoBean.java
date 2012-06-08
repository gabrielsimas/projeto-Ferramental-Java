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

import br.com.medralservicosrio.dao.ProdutoDAO;
import br.com.medralservicosrio.generics.GenericBean;
import br.com.medralservicosrio.modelo.Produto;

@ManagedBean
@SessionScoped
public class ProdutoBean extends GenericBean implements Serializable{

	private static final long serialVersionUID = -3949946652394939040L;
	
	private boolean atualizacao;
	private String textoBotao = "Cadastrar";
	private Produto produto;
	private List<Produto> produtos;
	private ProdutoDAO dao;
	
	public ProdutoBean() {
		produto = new Produto();
		produtos = new ArrayList<Produto>();
		//dao = new ProdutoDAO(Produto.class);
		dao = new ProdutoDAO();
	}

	@Override
	public void cadastrar() {
		
		FacesContext fc = FacesContext.getCurrentInstance();

		try {

			//Salva o registro
			//Verifica se é alteração ou cadastro
			if (atualizacao){
				dao.atualizar(produto);
				atualizacao = false;
				textoBotao = "Cadastrar";
				produto = new Produto();
				
			} else {

				dao.criar(produto);
				atualizacao = false;
				textoBotao = "Cadastrar";
				produto = new Produto();
				fc.addMessage(null, new FacesMessage(getMENSAGEM_INFO(),null,"Produto cadastrado com sucesso!!"));
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
			
			dao.atualizar(produto);
			
			fc.addMessage(null, new FacesMessage(getMENSAGEM_INFO(),null,"Produto cadastrado com sucesso!!"));
			
		} catch(Exception ex) {
			fc.addMessage(null, new FacesMessage(getMENSAGEM_FATAL(),null,ex.getMessage()));
		}
	}

	@Override
	public void excluir(ActionEvent evento) {
		FacesContext fc = FacesContext.getCurrentInstance();

		try{

			UIParameter f = (UIParameter) evento.getComponent().findComponent("idProduto");
			Integer idProduto = (Integer) f.getValue();

			//Obtendo o id do Produto via Parametro
			Produto produtoExcluir = (Produto) dao.listarPorId(Produto.class, idProduto);

			//Produto produtoExcluir = (Produto) dao.listarPorId(Produto.class,matriculaFuncionario);
			//Apaga o Funcionário
			dao.apagar(produtoExcluir);

			//Imprime a mensagem
			fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,null,"Produto " + produtoExcluir.getProduto() + " excluido com sucesso!"));

			//Zera os objetos utilizados
			produtoExcluir = null;
			produto = new Produto();
			
		} catch (Exception ex) {
			fc.addMessage(null, new FacesMessage(this.getMENSAGEM_FATAL(),null,"Erro: " + ex.getMessage()));
		}
		
	}
	
	@Override
	public void limpar() {
		
		FacesContext fc = FacesContext.getCurrentInstance();
		
		if (produto != null){
			produto = null;
			produto = new Produto();
			fc.addMessage(null, new FacesMessage(getMENSAGEM_INFO(),null,"Limpeza efetuada com sucesso!"));
			
		} else {
			
			fc.addMessage(null, new FacesMessage(getMENSAGEM_INFO(),null,"Não há o que limpar!"));
		}
		
	}
	
	@Override
	public String voltar() {
		
		return super.voltar();
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

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getProdutos() {
		
		if (produtos.isEmpty() || atualizacao != false){
			produtos = dao.listarTudo(Produto.class);
			atualizacao = false;
		}
		
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public ProdutoDAO getDao() {
		return dao;
	}

	public void setDao(ProdutoDAO dao) {
		this.dao = dao;
	}
}
