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
import br.com.medralservicosrio.dao.RastreabilidadeDAO;
import br.com.medralservicosrio.generics.GenericBean;
import br.com.medralservicosrio.modelo.Produto;
import br.com.medralservicosrio.modelo.Rastreabilidade;

/**
 * 
 * @author Gabriel
 *
 */
@ManagedBean
@SessionScoped
public class RastreabilidadeBean extends GenericBean implements Serializable{

	
	private static final long serialVersionUID = -4037912994889490379L;
	
	private boolean atualizacao;
	private String textoBotao = "Cadastrar";
	private boolean botaoConfirmar = true;
	
	private Rastreabilidade rastreabilidade;
	private RastreabilidadeDAO dao;
	private List<Rastreabilidade> rastreabilidades;
	
	private List<Produto> produtos;
	private ProdutoDAO produtoDAO;
	
	public RastreabilidadeBean() {
		dao = new RastreabilidadeDAO(Rastreabilidade.class);
		rastreabilidade = new Rastreabilidade();
		rastreabilidades = new ArrayList<Rastreabilidade>();
		
		produtos = new ArrayList<Produto>();
		
	}

	@Override
	public void cadastrar() {
		
		FacesContext fc = FacesContext.getCurrentInstance();

		try {

			//Salva o registro
			//Verifica se é alteração ou cadastro
			if (atualizacao){
				//Adiciona a matricula do funcionario ao Rastreabilidade
				dao.atualizar(rastreabilidade);
				atualizacao = false;
				textoBotao = "Cadastrar";
				rastreabilidade = new Rastreabilidade();
				fc.addMessage(null, new FacesMessage(getMENSAGEM_INFO(),null,"Rastreabilidade atualizada com sucesso!!"));

			} else {
				//Adiciona a matricula do funcionario ao Rastreabilidade
				dao.criar(rastreabilidade);
				atualizacao = false;
				textoBotao = "Cadastrar";
				rastreabilidade = new Rastreabilidade();
				fc.addMessage(null, new FacesMessage(getMENSAGEM_INFO(),null,"Rastreabilidade cadastrada com sucesso!!"));
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
			
			dao.atualizar(rastreabilidade);
			
			fc.addMessage(null, new FacesMessage(getMENSAGEM_INFO(),null,"Rastreabilidade cadastrada com sucesso!!"));
			
		} catch(Exception ex) {
			fc.addMessage(null, new FacesMessage(getMENSAGEM_FATAL(),null,ex.getMessage()));
		}
		
	}

	@Override
	public void excluir(ActionEvent evento) {
		FacesContext fc = FacesContext.getCurrentInstance();

		try{

			UIParameter f = (UIParameter) evento.getComponent().findComponent("id");
			Integer id = (Integer) f.getValue();

			//Obtendo o id do Rastreabilidade via Parametro
			Rastreabilidade rastreabilidadeExcluir = (Rastreabilidade) dao.listarPorId(Rastreabilidade.class, id);

			//Rastreabilidade rastreabilidadeExcluir = (Rastreabilidade) dao.listarPorId(Rastreabilidade.class,matriculaRastreabilidade);
			//Apaga o Funcionário
			dao.apagar(rastreabilidadeExcluir);

			//Imprime a mensagem
			fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,null,"Rastreabilidade " + rastreabilidadeExcluir.getIdRastreabilidade() + " excluido com sucesso!"));

			//Zera os objetos utilizados
			rastreabilidadeExcluir = null;
			rastreabilidade = new Rastreabilidade();
			
		} catch (Exception ex) {
			fc.addMessage(null, new FacesMessage(this.getMENSAGEM_FATAL(),null,"Erro: " + ex.getMessage()));
		}

		
	}

	@Override
	public void limpar() {
		// TODO Auto-generated method stub
		
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

	public boolean isBotaoConfirmar() {
		return botaoConfirmar;
	}

	public void setBotaoConfirmar(boolean botaoConfirmar) {
		this.botaoConfirmar = botaoConfirmar;
	}

	public Rastreabilidade getRastreabilidade() {
		return rastreabilidade;
	}

	public void setRastreabilidade(Rastreabilidade rastreabilidade) {
		this.rastreabilidade = rastreabilidade;
	}

	public RastreabilidadeDAO getDao() {
		return dao;
	}

	public void setDao(RastreabilidadeDAO dao) {
		this.dao = dao;
	}

	public List<Rastreabilidade> getRastreabilidades() {
		
		if (rastreabilidades.isEmpty() || atualizacao == true){
			rastreabilidades.clear();
			rastreabilidades = dao.listarTudo(Rastreabilidade.class);
		}
		
		return rastreabilidades;
	}

	public void setRastreabilidades(List<Rastreabilidade> rastreabilidades) {
		this.rastreabilidades = rastreabilidades;
	}

	public List<Produto> getProdutos() {
		
		produtos.clear();
		
		produtos = produtoDAO.listarTudo(Produto.class);
		
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public ProdutoDAO getProdutoDAO() {
		return produtoDAO;
	}

	public void setProdutoDAO(ProdutoDAO produtoDAO) {
		this.produtoDAO = produtoDAO;
	}
	
}
