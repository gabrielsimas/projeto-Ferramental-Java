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

import br.com.medralservicosrio.dao.FuncionarioDAO;
import br.com.medralservicosrio.dao.MovimentacaoDAO;
import br.com.medralservicosrio.dao.MovimentacaoIndividualDAO;
import br.com.medralservicosrio.generics.GenericBean;
import br.com.medralservicosrio.modelo.Funcionario;
import br.com.medralservicosrio.modelo.MovimentacaoIndividual;
import br.com.medralservicosrio.modelo.Produto;

@ManagedBean(name="funcionarioBean")
@SessionScoped
public class FuncionarioBean extends GenericBean implements Serializable{

	private static final long serialVersionUID = 4577172355127206863L;
	private boolean atualizacao;
	private String textoBotao = "Cadastrar";
	private Funcionario funcionario;
	private List<Funcionario> funcionarios;
	private FuncionarioDAO dao; 
	private MovimentacaoIndividualDAO mdao;
	
	public FuncionarioBean() {
		dao = new FuncionarioDAO(Funcionario.class);
		funcionario = new Funcionario();
		funcionarios = new ArrayList<Funcionario>();
		mdao = new MovimentacaoIndividualDAO(MovimentacaoIndividual.class);
	}
	
	public void cadastrar(){
		
		FacesContext fc = FacesContext.getCurrentInstance();
		
		try {
			System.out.println(funcionario.getMatricula()+ "\n" +funcionario.getNome()+ "\n" + funcionario.getSetor()+ "\n" + funcionario.getCusto());
			
			//Verifica se a chapa cadastrada já existe
			
			//Salva o registro
			//Verifica se é alteração ou cadastro
			if (atualizacao){
				dao.atualizar(funcionario);
				atualizacao = false;
				textoBotao = "Cadastrar";
				funcionario = new Funcionario();
				this.getFuncionarios();
				
			} else {
				
				//Verifica se a matricula já não existe
				Funcionario funcionarioDesconhecido = (Funcionario) dao.listarPorId(Funcionario.class, funcionario.getMatricula());
				
				if (funcionarioDesconhecido != null){
					fc.addMessage(null, new FacesMessage(getMENSAGEM_ERRO(),null,"A Chapa informada pertence ao funcionario " +
							funcionarioDesconhecido.getNome() + ", digite outra!"));
				} else {
				
					dao.criar(funcionario);
					atualizacao = false;
					funcionario = new Funcionario();
					fc.addMessage(null, new FacesMessage(getMENSAGEM_ERRO(),null,"Funcionário cadastrado com sucesso!!"));
				}
				
			}
			
			limpar();
			
		} catch (Exception e){
			fc.addMessage(null, new FacesMessage(this.getMENSAGEM_FATAL(),null,e.getMessage()));
		}
	}
	
	public void atualizar(ActionEvent evento){
		
		FacesContext fc = FacesContext.getCurrentInstance();
		
		try{
			
			//UIParameter f = (UIParameter) evento.getComponent().findComponent("funcionario");
			//Funcionario funcionarioAlvo = (Funcionario) f.getValue();
			
			//Atualiza o registro
			dao.atualizar(funcionario);
			
			//Infoma que houve sucesso
			fc.addMessage(null, new FacesMessage(getMENSAGEM_INFO(),null,"Funcion&aacute;rio atualizado com sucesso!"));
			
			//Inicia um novo objeto
			funcionario = new Funcionario();
			
			
		} catch (Exception e){
			fc.addMessage(null, new FacesMessage(this.getMENSAGEM_FATAL(),null,"Erro: " + e.getMessage()));
		}
	}
		
	public void excluir(ActionEvent evento) {
		
		FacesContext fc = FacesContext.getCurrentInstance();
		
		try{
		
			UIParameter f = (UIParameter) evento.getComponent().findComponent("idFuncionario");
			Integer matriculaFuncionario = (Integer) f.getValue();
			
			//Obtendo a Loja para ser excluída
			Funcionario funcionarioExcluir = (Funcionario) dao.listarPorId(Funcionario.class, matriculaFuncionario);
									
			//Verifica se ele tem algum registro na Tabela de Movimentação
			//Se tiver, não faz a exclusao
			Integer m = mdao.localizaPorChapa(matriculaFuncionario).size();
			
			if (m > 0){
				fc.addMessage(null, new FacesMessage(getMENSAGEM_ERRO(),null,"Não é possível excluir " +
						funcionarioExcluir.getNome() + " pois existem movimentacoes em seu nome"));
				funcionarioExcluir = null;
				
			} else {
				//Funcionario funcionarioExcluir = (Funcionario) dao.listarPorId(Funcionario.class,matriculaFuncionario);
				//Apaga o Funcionário
				dao.apagar(funcionarioExcluir);
				
				//Imprime a mensagem
				fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,null,"Funcionário " + funcionarioExcluir.getNome() + " excluido com sucesso!"));
				
				//Zera os objetos utilizados
				funcionarioExcluir = null;
				funcionario = new Funcionario();
			}
			
		} catch (Exception ex) {
			fc.addMessage(null, new FacesMessage(this.getMENSAGEM_FATAL(),null,"Erro: " + ex.getMessage()));
		} 
	}
	
	@Override
	public void limpar() {
		FacesContext fc = FacesContext.getCurrentInstance();
		
		if (funcionario != null){
			funcionario = null;
			funcionario = new Funcionario();
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

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	@SuppressWarnings("unchecked")
	public List<Funcionario> getFuncionarios() {
		
		if (funcionarios.isEmpty() || atualizacao != false){
			funcionarios = dao.listarTudo(Funcionario.class);
			atualizacao = false;
		}
				
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public FuncionarioDAO getDao() {
		return dao;
	}

	public void setDao(FuncionarioDAO dao) {
		this.dao = dao;
	}

	public MovimentacaoIndividualDAO getMdao() {
		return mdao;
	}

	public void setMdao(MovimentacaoIndividualDAO mdao) {
		this.mdao = mdao;
	}
}
