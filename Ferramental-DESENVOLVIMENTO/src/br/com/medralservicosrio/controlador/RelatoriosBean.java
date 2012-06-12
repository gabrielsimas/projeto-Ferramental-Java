package br.com.medralservicosrio.controlador;

import java.io.InputStream;
import java.sql.Connection;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperRunManager;
import br.com.medralservicosrio.dao.RelatorioDao;
import br.com.medralservicosrio.relatorios.RelatorioAdmIndividual;



/*
 * Desenvolvido por: Marcos Vinicius Cabral Annnunciação Pinho
 * 
 * 
 * Classe que faz interação com as paginas jsp
 * 
 */


@ManagedBean(name="relatoriosBean")
public class RelatoriosBean {

	
	private RelatorioAdmIndividual individual;
	private List<RelatorioAdmIndividual> dados;
	
	//public Integer idIndividual2 = idIndividual;
	
	public RelatoriosBean(){
		individual = new RelatorioAdmIndividual();
	}
	
	
	public void imprimirRelatorioAdmIndividual() throws Exception{
		
		List<RelatorioAdmIndividual> dados = new RelatorioDao().listaRelatorioAdmIndividual(individual.getIdFuncionario());
		
		
		
		FacesContext context = FacesContext.getCurrentInstance();
		InputStream relatorio = context.getExternalContext().getResourceAsStream("/relAdmIndividual.jasper");
		
		
		//DataSouceRelAdmIndividual ds = new DataSouceRelAdmIndividual(dados);
		
		//Map<String, String> parametros = new HashMap<String, String>();
		
		
		//Gerar o relatorio;
		byte[] arquivo = null;
		
		try {
			
			HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
			//arquivo = JasperRunManager.runReportToPdf(relatorio, null,(Connection) ds);
			//String s = "attachment;filename=Produtos.pdf";
			String s = "inline;filename=Relatorio.pdf";
			
			
			response.setHeader("Content-disposition", s);
			response.setContentLength(arquivo.length);
			
			
			ServletOutputStream out = response.getOutputStream();
			out.write(arquivo);
			out.flush();
			out.close();
			
			
		} catch (Exception e) {

			e.printStackTrace();
		
		}
		
		context.responseComplete();
	}


	public void listarRelatorioAdmIndividual(){
		
		
		
		//FacesContext fc = FacesContext.getCurrentInstance();
		
		//String mensagem = "";
		
		
		
		
		try {
			
			dados = new RelatorioDao().listaRelatorioAdmIndividual(individual.getIdFuncionario());

			
		
			
			
			
		} catch (Exception e) {
			//mensagem = "Erro: "+e.getMessage();
		}
		
		
		
		
	}
	
	
	
	
	public RelatorioAdmIndividual getIndividual() {
		return individual;
	}


	public void setIndividual(RelatorioAdmIndividual individual) {
		this.individual = individual;
	}


	public List<RelatorioAdmIndividual> getDados() {
		return dados;
	}


	public void setDados(List<RelatorioAdmIndividual> dados) {
		this.dados = dados;
	}


	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
