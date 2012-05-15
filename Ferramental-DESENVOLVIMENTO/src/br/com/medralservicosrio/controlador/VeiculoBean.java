package br.com.medralservicosrio.controlador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import br.com.medralservicosrio.dao.VeiculoDAO;
import br.com.medralservicosrio.generics.GenericBean;
import br.com.medralservicosrio.modelo.Produto;
import br.com.medralservicosrio.modelo.Veiculo;

public class VeiculoBean extends GenericBean implements Serializable{

	private static final long serialVersionUID = -976678547432366788L;
	private boolean atualizacao;
	private String textoBotao = "Cadastrar";
	private Veiculo veiculo;
	private List<Veiculo> veiculos;
	private VeiculoDAO dao;
	
	public VeiculoBean() {
		veiculo = new Veiculo();
		veiculos = new ArrayList<Veiculo>();
		dao = new VeiculoDAO(Veiculo.class);
	}

	@Override
	public void cadastrar() {
	
		FacesContext fc = FacesContext.getCurrentInstance();

		try {

			//Salva o registro
			//Verifica se é alteração ou cadastro
			if (atualizacao){
				dao.atualizar(veiculo);
				atualizacao = false;
				textoBotao = "Cadastrar";
				veiculo = new Veiculo();
				fc.addMessage(null, new FacesMessage(getMENSAGEM_INFO(),null,"Veiculo atualizado com sucesso!!"));

			} else {

				dao.criar(veiculo);
				atualizacao = false;
				textoBotao = "Cadastrar";
				veiculo = new Veiculo();
				fc.addMessage(null, new FacesMessage(getMENSAGEM_INFO(),null,"Veiculo cadastrado com sucesso!!"));
			}
			
		} catch (Exception e){
			fc.addMessage(null, new FacesMessage(this.getMENSAGEM_FATAL(),null,e.getMessage()));
		}
		
		
	}

	@Override
	public void atualizar(ActionEvent evento) {
		
		FacesContext fc = FacesContext.getCurrentInstance();
		
		try{
			
			dao.atualizar(veiculo);
			
			fc.addMessage(null, new FacesMessage(getMENSAGEM_INFO(),null,"Veiculo cadastrado com sucesso!!"));
			
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
	
}
