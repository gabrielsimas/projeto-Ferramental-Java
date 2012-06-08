package br.com.medralservicosrio.controlador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;


import br.com.medralservicosrio.dao.FuncionarioDAO;
import br.com.medralservicosrio.dao.VeiculoDAO;
import br.com.medralservicosrio.generics.GenericBean;
import br.com.medralservicosrio.modelo.Funcionario;
import br.com.medralservicosrio.modelo.Veiculo;

@ManagedBean
@SessionScoped
public class VeiculoBean extends GenericBean implements Serializable{

	private static final long serialVersionUID = -976678547432366788L;
	private boolean atualizacao;
	private String textoBotao = "Cadastrar";
	private Veiculo veiculo;
	private List<Veiculo> veiculos;
	private VeiculoDAO dao;
	
	private boolean botaoConfirmar = true;
	private FuncionarioDAO funcionarioDAO;
	private List<Funcionario> funcionarios;
	private Funcionario funcionario;
	private Map<String,String> listagemFuncionarios;
	
	
	public VeiculoBean() {
		//dao = new VeiculoDAO(Veiculo.class);
		dao = new VeiculoDAO();
		veiculo = new Veiculo();
		veiculos = new ArrayList<Veiculo>();
		
		//funcionarioDAO = new FuncionarioDAO(Funcionario.class);
		funcionarioDAO = new FuncionarioDAO();
		funcionario = new Funcionario();
		
		/*funcionarios = new ArrayList<Funcionario>();*/
	/*	listagemFuncionarios = new HashMap<String, String>();*/
	}
	
	@Override
	public void cadastrar() {
	
		FacesContext fc = FacesContext.getCurrentInstance();
		try {
			
			System.out.println(veiculo.getPlaca()+ "\n" +veiculo.getMarca()+ "\n" + veiculo.getModelo()+ "\n" + veiculo.getSetor());
			
			//Verifica se a placa cadastrada já existe
			
			//Salva o registro
			//Verifica se é alteração ou cadastro
			if (atualizacao){
				dao.atualizar(veiculo);
				atualizacao = false;
				textoBotao = "Cadastrar";
				veiculo = new Veiculo();
				veiculo.setMatriculaFuncionario(funcionario.getMatricula());				
				
			} else {
				//Adiciona a matricula do funcionario ao Veiculo
				veiculo.setMatriculaFuncionario(funcionario.getMatricula());
				dao.criar(veiculo);
				atualizacao = false;
				textoBotao = "Cadastrar";
				veiculo = new Veiculo();
				fc.addMessage(null, new FacesMessage(getMENSAGEM_INFO(),null,"Veiculo cadastrado com sucesso!!"));
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
			
			dao.atualizar(veiculo);
			
			fc.addMessage(null, new FacesMessage(getMENSAGEM_INFO(),null,"Veiculo atualizado com sucesso!!"));
			
		} catch(Exception ex) {
			fc.addMessage(null, new FacesMessage(getMENSAGEM_FATAL(),null,ex.getMessage()));
		}		
	}

	@Override
	public void excluir(ActionEvent evento) {
		FacesContext fc = FacesContext.getCurrentInstance();

		try{

			UIParameter f = (UIParameter) evento.getComponent().findComponent("idVeiculo");
			Integer idVeiculo = (Integer) f.getValue();

			//Obtendo o id do Veiculo via Parametro
			Veiculo veiculoExcluir = (Veiculo) dao.listarPorId(Veiculo.class, idVeiculo);

			//Veiculo veiculoExcluir = (Veiculo) dao.listarPorId(Veiculo.class,matriculaFuncionario);
			//Apaga o Funcionário
			dao.apagar(veiculoExcluir);

			//Imprime a mensagem
			fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,null,"Veiculo " + veiculoExcluir.getPlaca() + " excluido com sucesso!"));

			//Zera os objetos utilizados
			veiculoExcluir = null;
			veiculo = new Veiculo();
			
		} catch (Exception ex) {
			fc.addMessage(null, new FacesMessage(this.getMENSAGEM_FATAL(),null,"Erro: " + ex.getMessage()));
		}
		
	}

	@Override
	public void limpar() {
		
		FacesContext fc = FacesContext.getCurrentInstance();
		
		if (veiculo != null){
			veiculo = null;
			veiculo = new Veiculo();
			fc.addMessage(null, new FacesMessage(getMENSAGEM_INFO(),null,"Limpeza efetuada com sucesso!"));
			
		} else {
			
			fc.addMessage(null, new FacesMessage(getMENSAGEM_INFO(),null,"Não há o que limpar!"));
		}
	}
	
	public void localizaFuncionario(ActionEvent evento){
		
		FacesContext fc = FacesContext.getCurrentInstance();
		
		UIParameter componente = (UIParameter) evento.getComponent().findComponent("chapa");
		Integer chapa = (Integer) componente.getValue();
		
		try {
			
			//Localiza o Funcionario
			funcionario = (Funcionario) funcionarioDAO.listarPorId(Funcionario.class, chapa);
			
			if (funcionario == null){
				fc.addMessage(null, new FacesMessage(getMENSAGEM_ERRO(),null,"Chapa Nao encontrada"));
				veiculo = new Veiculo();
				botaoConfirmar = true;
				
			} else {
				
				veiculo.setMatriculaFuncionario(chapa);
				botaoConfirmar = false;
			}
			
		} catch (Exception ex){
			fc.addMessage(null, new FacesMessage(getMENSAGEM_FATAL(),null,"Erro: " + ex.getMessage()));
			funcionario = null;
		}
	}
	
	/**
	 * Gera os valores para a Combo cmbListagemFuncionarios
	 * @return
	 *//*
	public Map<String,String> getListagemFuncionarios() {
		
		try {
			
			//Limpa a listagem
			this.listagemFuncionarios.clear();
			
			for (Object obj: funcionarioDAO.listarTudo(Funcionario.class)){
				funcionario = (Funcionario) obj;
				
				listagemFuncionarios.put(funcionario.getMatricula().toString(), funcionario.getNome());
			}
			
			//Popula a mesma com uma iteração de objetos
			
			
		} catch (Exception ex){
			
		}
		
		return listagemFuncionarios;
	}*/
	
	/*
	public void localizaFuncionarioPorId(ActionEvent evento){
		
		FacesContext fc = FacesContext.getCurrentInstance();
		
		try {
			UIParameter f = (UIParameter) evento.getComponent().findComponent("matriculaFuncionario");
			Integer chapa = (Integer) f.getValue();

			//Obtendo o id do Veiculo via Parametro
			//Veiculo veiculoExcluir = (Veiculo) dao.listarPorId(Veiculo.class, idVeiculo);
			this.funcionario = (Funcionario) funcionarioDAO.listarPorId(Funcionario.class, chapa);
			
			fc.addMessage(null, new FacesMessage(getMENSAGEM_INFO(),null,"Limpeza efetuada com sucesso!"));
		} catch (Exception ex){
			fc.addMessage(null, new FacesMessage(this.getMENSAGEM_FATAL(),null,"Erro: " + ex.getMessage()));
		}
		
	}
	*/
	
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

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public List<Veiculo> getVeiculos() {
		
		if (veiculos.isEmpty() || atualizacao == true){
			veiculos.clear();
			veiculos = dao.listarTudo(Veiculo.class);
		}
		
		return veiculos;
	}

	public void setVeiculos(List<Veiculo> veiculos) {
		this.veiculos = veiculos;
	}

	public VeiculoDAO getDao() {
		return dao;
	}

	public void setDao(VeiculoDAO dao) {
		this.dao = dao;
	}

	public FuncionarioDAO getFuncionarioDAO() {
		return funcionarioDAO;
	}

	public void setFuncionarioDAO(FuncionarioDAO funcionarioDAO) {
		this.funcionarioDAO = funcionarioDAO;
	}

	public List<Funcionario> getFuncionarios() {
		
		funcionarios.clear();
		
		funcionarios = funcionarioDAO.listarTudo(Funcionario.class);
		
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}
	
	public void setListagemFuncionarios(Map<String,String> listagemFuncionarios) {
		this.listagemFuncionarios = listagemFuncionarios;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public boolean isBotaoConfirmar() {
		return botaoConfirmar;
	}

	public void setBotaoConfirmar(boolean botaConfirmar) {
		this.botaoConfirmar = botaConfirmar;
	}
}
