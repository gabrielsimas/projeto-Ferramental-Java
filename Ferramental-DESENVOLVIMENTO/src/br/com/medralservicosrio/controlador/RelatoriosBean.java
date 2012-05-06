package br.com.medralservicosrio.controlador;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;

import net.sf.jasperreports.engine.JRException;
import br.com.medralservicosrio.dao.RelatorioDao;
import br.com.medralservicosrio.modelo.Produto;
import br.com.medralservicosrio.relatorios.RelatorioDeProdutosPorFuncionario;
import br.com.medralservicosrio.relatorios.RelatorioGerencialRastreabilidade;
import br.com.medralservicosrio.relatorios.RelatorioGerencialRastreabilidadeSubReport;
import br.com.medralservicosrio.util.ReportUtils;
/**
 * Classe responsavel de gerar todos os relatorios em pdf do sistema.
 * @author Felipe Tavares
 *
 */
@ManagedBean
@ViewScoped
public class RelatoriosBean{

	private RelatorioDao relatorioDao = null;
	private List listaRelatorio = null;
	private String produto;
	private String funcionario;
	private String rastreabilidade;
	private String placa;
	private Date dataInicial;
	private Date dataFinal;
	private String matricula;
	private Integer numNota;
	private String numNotaS;

	public RelatoriosBean() {
		relatorioDao = new RelatorioDao();
		listaRelatorio = new ArrayList();
		produto = "";
		funcionario = "";
		rastreabilidade = "";
		placa = "";
		dataInicial = null;
		dataFinal = null;
		numNotaS = "";
	}


	/**
	 * Metodo responsavel por buscar os dados do relatorio de materiais
	 * @param event
	 * @throws IOException 
	 */
	public void gerarRelatorioGerencialDeMateriais(ActionEvent event) throws IOException{
		
		try {
			listaRelatorio = new ArrayList();
			listaRelatorio = relatorioDao.gerarRelatorioGerencialDeMateriais(produto);
			if(listaRelatorio.isEmpty()){
				FacesContext context = FacesContext.getCurrentInstance();  
		        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção" , "Sua Busca não retornou nenhum resultado"));
			}
	
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo Responsavel por Gerar o pdf do relatorio
	 * @param event
	 */
	public void imprimirRelatorioGerencialDeMateriais(ActionEvent event){
		
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request   = (HttpServletRequest) context.getExternalContext().getRequest();		
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();

		try {
			
			Map<String, Object> params = new HashMap<String,Object>();
			params.put("produto", produto);
			params.put("logo", new ImageIcon (context.getExternalContext().getResource("/imagem/medral.JPG")).getImage());
			
			String fileName = "relatorioGerencialDeMateriais";
			ReportUtils.gerarPdf("/reports/relGerencialMaterial.jasper", fileName, listaRelatorio, params, request, response);
			

		} catch (JRException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		} finally{
			context.renderResponse();
			context.responseComplete(); 
		}
		
	}
	
	/**
	 * Metodo responsavel por gerar o relatorio de compras
	 * @param event
	 */
	public void gerarRelatorioGerencialDeCompras(ActionEvent event){
		
		try {
			
			listaRelatorio = new ArrayList();
			listaRelatorio = relatorioDao.gerarRelatorioGerencialDeCompras(numNotaS, produto, dataInicial, dataFinal);
			if(listaRelatorio.isEmpty()){
				FacesContext context = FacesContext.getCurrentInstance();  
		        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção" , "Sua Busca não retornou nenhum resultado"));
		        numNota = null;
			}
	
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @param event
	 */
	public void imprimirRelatorioGerencialDeCompras(ActionEvent event){

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request   = (HttpServletRequest) context.getExternalContext().getRequest();		
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();

		try {
			
				// parametros do relatorio
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("produto", produto);
			params.put("dataInicial", dataInicial);
			params.put("dataFinal", dataFinal);
			params.put("logo", new ImageIcon (context.getExternalContext().getResource("/imagem/medral.JPG")).getImage());
			
			String fileName = "relGerencialCompras"; 
			ReportUtils.gerarPdf("/reports/relGerencialCompras.jasper", fileName, listaRelatorio, params, request, response);

		} catch (JRException e) {
			
			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		} finally{
			context.renderResponse();
			context.responseComplete(); 
		}
	}
	
	
	/**
	 * Metodo responsavel por gerar o relatorio de rastreabilidade
	 * @param event
	 */
	public void gerarRelatorioGerencialRastreabilidade(ActionEvent event){

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request   = (HttpServletRequest) context.getExternalContext().getRequest();		
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
		
		

		try {
			
			//trocar pelo dao
			Collection listaRastreabilidade = new ArrayList();
			for(int i = 1 ; i <= 150 ; i++){
				RelatorioGerencialRastreabilidade rastreabilidade = new RelatorioGerencialRastreabilidade();
				RelatorioGerencialRastreabilidadeSubReport subReport = new RelatorioGerencialRastreabilidadeSubReport();
				rastreabilidade.setIdRastreabilidade(i);
				rastreabilidade.setProduto("produto_"+i);
				rastreabilidade.setTempoDeUso((int)(Math.random() * 300));
				
				for (int j = 0; j < 1 + (int)(Math.random() * 5); j++) {
					
					subReport.setNomeFuncionario("funcionario_"+ i);
					subReport.setStatus("teste status 1");
					subReport.setStatus2("teste status 2");
					subReport.setTUPF(new Double(Math.random() * 400).toString());
					rastreabilidade.getListaDetalhes().add(subReport);
					
				}
				
				listaRastreabilidade.add(rastreabilidade);
			}

			// parametros do relatorio
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("SUBREPORT_DIR", request.getServletContext().getRealPath("/reports") + "/");
			params.put("logo", new ImageIcon(context.getExternalContext().getResource("/imagem/medral.JPG")).getImage());
			
			String fileName = "relRastreabilidadeDeProdutos"; 
			ReportUtils.gerarPdf("/reports/relRastreabilidadeDeProdutos.jasper", fileName, listaRastreabilidade, params, request, response);

		} catch (JRException e) {
			
			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		} finally{
			context.renderResponse();
			context.responseComplete();
			
			
			
		}
	}
	
	public void gerarRelatorioProdutosPorFuncionarios(ActionEvent event){
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();

		try {

			//trocar pelo dao
			Collection lista = new ArrayList();
			for(int i = 1 ; i <= 10; i++){
				
				RelatorioDeProdutosPorFuncionario relatorio = new RelatorioDeProdutosPorFuncionario();
				Produto p = new Produto();
				p.setProduto("produto_"+i);
				p.setValor(10.00);
				relatorio.setProduto(p);
				relatorio.setQuantidade((int)(Math.random() * 5));

				lista.add(relatorio);
			}

			// parametros do relatorio
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("logo", new ImageIcon(context.getExternalContext().getResource("/imagem/medral.JPG")).getImage());
			params.put("matricula_placa", "000004");
			params.put("funcionario_encarregado", "Felipe Tavaares dos Santos Pinto");
			params.put("funcao", "Desenvolvedor Web");
			params.put("setor", "Desenvolvimento");
			
			String fileName = "relProdutosPorFuncionarios"; 
			ReportUtils.gerarPdf("/reports/relProdutosPorFuncionarios.jasper", fileName, lista, params, request, response);

		} catch (JRException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		} finally{
			context.renderResponse();
			context.responseComplete(); 
		}
	}
	/**
	 * @return the relatorioDao
	 */
	public RelatorioDao getRelatorioDao() {
		return relatorioDao;
	}


	/**
	 * @param relatorioDao the relatorioDao to set
	 */
	public void setRelatorioDao(RelatorioDao relatorioDao) {
		this.relatorioDao = relatorioDao;
	}

	/**
	 * @return the produto
	 */
	public String getProduto() {
		return produto;
	}


	/**
	 * @param produto the produto to set
	 */
	public void setProduto(String produto) {
		this.produto = produto;
	}


	/**
	 * @return the funcionario
	 */
	public String getFuncionario() {
		return funcionario;
	}


	/**
	 * @param funcionario the funcionario to set
	 */
	public void setFuncionario(String funcionario) {
		this.funcionario = funcionario;
	}


	/**
	 * @return the rastreabilidade
	 */
	public String getRastreabilidade() {
		return rastreabilidade;
	}


	/**
	 * @param rastreabilidade the rastreabilidade to set
	 */
	public void setRastreabilidade(String rastreabilidade) {
		this.rastreabilidade = rastreabilidade;
	}


	/**
	 * @return the placa
	 */
	public String getPlaca() {
		return placa;
	}


	/**
	 * @param placa the placa to set
	 */
	public void setPlaca(String placa) {
		this.placa = placa;
	}


	/**
	 * @return the dataInicial
	 */
	public Date getDataInicial() {
		return dataInicial;
	}


	/**
	 * @param dataInicial the dataInicial to set
	 */
	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}


	/**
	 * @return the dataFinal
	 */
	public Date getDataFinal() {
		return dataFinal;
	}


	/**
	 * @param dataFinal the dataFinal to set
	 */
	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}


	/**
	 * @return the matricula
	 */
	public String getMatricula() {
		return matricula;
	}


	/**
	 * @param matricula the matricula to set
	 */
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}


	/**
	 * @return the listaRelatorio
	 * @throws SQLException 
	 */
	public List getListaRelatorio() throws SQLException {
		return listaRelatorio;
	}


	/**
	 * @param listaRelatorio the listaRelatorio to set
	 */
	public void setListaRelatorio(List listaRelatorio) {
		this.listaRelatorio = listaRelatorio;
	}


	/**
	 * @return the numNota
	 */
	public Integer getNumNota() {
		return numNota;
	}


	/**
	 * @param numNota the numNota to set
	 */
	public void setNumNota(Integer numNota) {
		this.numNota = numNota;
	}


	/**
	 * @return the numNotaS
	 */
	public String getNumNotaS() {
		return numNotaS;
	}


	/**
	 * @param numNotaS the numNotaS to set
	 */
	public void setNumNotaS(String numNotaS) {
		this.numNotaS = numNotaS;
	}

}
