package br.com.medralservicosrio.controlador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.com.medralservicosrio.dao.FuncionarioDAO;
import br.com.medralservicosrio.modelo.Funcionario;

public class FuncionarioBean implements Serializable{

	private static final long serialVersionUID = -951830423425499345L;
	
	
	private Funcionario funcionario;
	private List<Funcionario> funcionarios;
	private FuncionarioDAO dao;
	
	public FuncionarioBean() {
		funcionario = new Funcionario();
		funcionarios = new ArrayList<Funcionario>();
	}
	
	public void cadastraFuncionario(){
		
		FacesContext fc = FacesContext.getCurrentInstance();
		
		try {
			//Salva o registro
			dao.criar(funcionario);
			
			//Emite mensagem
			fc.addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO,"","Cadastro do Funcionário " + funcionario.getNome()+ " efetuado com sucesso!"));
		} catch (Exception e){
			
		}
		
		
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	@SuppressWarnings("unchecked")
	public List<Funcionario> getFuncionarios() {
		
		funcionarios = dao.listarTudo(Funcionario.class);
		
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
}
