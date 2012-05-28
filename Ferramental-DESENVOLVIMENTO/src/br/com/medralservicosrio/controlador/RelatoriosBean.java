package br.com.medralservicosrio.controlador;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
import br.com.medralservicosrio.modelo.Funcionario;
import br.com.medralservicosrio.relatorios.RelatorioAdmReforma;
import br.com.medralservicosrio.relatorios.RelatorioAdmTesteEletrico;
import br.com.medralservicosrio.relatorios.RelatorioAdmVales;
import br.com.medralservicosrio.relatorios.RelatorioAdministrativoEntrada;
import br.com.medralservicosrio.relatorios.RelatorioGerencialCompras;
import br.com.medralservicosrio.relatorios.RelatorioGerencialProdutosPorFuncionarios;
import br.com.medralservicosrio.relatorios.RelatorioGerencialRastreabilidade;
import br.com.medralservicosrio.relatorios.RelatorioGerencialSucata;
import br.com.medralservicosrio.util.ReportUtils;
/**
 * Classe responsavel de gerar todos os relatorios em pdf do sistema.
 * @author Felipe Tavares
 *
 */
@ManagedBean
@ViewScoped
@SuppressWarnings("rawtypes")
public class RelatoriosBean{

	// PROPIEDADES USADAS PARA RECURAR VALORES EM TODAS AS TELAS DE RELATORIO
	// E USADO APENAS UMA LISTA E UM MAP PARA TODAS.
	private RelatorioDao relatorioDao = null;
	private List listaRelatorio = null;
	private Funcionario func;
	private Map calculoTotais;
	private String produto;
	private String funcionario;
	private String rastreabilidade;
	private String placa;
	private Date dataInicial;
	private Date dataFinal;
	private String matricula;
	private String numNota;
	private String setor;
	
	public RelatoriosBean() {
		relatorioDao = new RelatorioDao(null);
		listaRelatorio = new ArrayList();
		calculoTotais = new HashMap();
		func = new Funcionario();
		produto = "";
		funcionario = "";
		rastreabilidade = "";
		placa = "";
		dataInicial = null;
		dataFinal = null;
		numNota = "";
		setor = "";
	}


	/**
	 * Gera a tela de relatorio gerencial de materiais
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
			}else{
				
				List<RelatorioGerencialCompras> lista = listaRelatorio;
				Map<String,Double> calculoTotaisTemp = new HashMap<String,Double>();
				
				Double valorTeste = new Double("0");
				Double valorReforma = new Double("0");
				Double valorEstoque = new Double("0");
				Double valorFuncionario = new Double("0");
				Double valorVeiculo = new Double("0");
				Double vlrTemp = new Double("0");
				
				for(RelatorioGerencialCompras compra : lista){
					
					vlrTemp = compra.getValorUnitario() * compra.getQtdTeste();
					valorTeste =  vlrTemp  + valorTeste;
					
					vlrTemp = compra.getValorUnitario() * compra.getQtdReformado();
					valorReforma =  vlrTemp + valorReforma;
					
					vlrTemp = compra.getValorUnitario() * compra.getQtdEstoque();
					valorEstoque =  vlrTemp + valorEstoque;
					
					vlrTemp = compra.getValorUnitario() * compra.getQtdFuncionando();
					valorFuncionario =  vlrTemp + valorFuncionario;
					
					vlrTemp = compra.getValorUnitario() * compra.getQtdVeiculo();
					valorVeiculo =  vlrTemp + valorVeiculo;
				}
				
				calculoTotaisTemp.put("valorTotalTeste", valorTeste);
				calculoTotaisTemp.put("valorTotalReforma", valorReforma);
				calculoTotaisTemp.put("valorTotalEstoque", valorEstoque);
				calculoTotaisTemp.put("valorTotalFuncionario", valorFuncionario);
				calculoTotaisTemp.put("valorTotalVeiculo", valorVeiculo);
				
				calculoTotais = calculoTotaisTemp;
				
			}
	
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	
	/**
	 * Imprime o relatorio em PDF / XLS 
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
	 * Gera a tela de relatorio gerencial de compras
	 * @param event
	 */
	public void gerarRelatorioGerencialDeCompras(ActionEvent event){
		
		try {
			
			listaRelatorio = new ArrayList();
			listaRelatorio = relatorioDao.gerarRelatorioGerencialDeCompras(numNota, produto, dataInicial, dataFinal);
			if(listaRelatorio.isEmpty()){
				FacesContext context = FacesContext.getCurrentInstance();  
		        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção" , "Sua Busca não retornou nenhum resultado"));
		        numNota = null;
			}else{
				
				List<RelatorioGerencialCompras> lista = listaRelatorio;
				Map<String, Double> calculoTotaisTemp = new HashMap<String, Double>();
				Double valorTotal = new Double("0");
				Double vlrTemp = new Double("0");
				
				for(RelatorioGerencialCompras compra : lista){
					vlrTemp = compra.getValorUnitario() * compra.getQtd();
					valorTotal =  vlrTemp  + valorTotal;
				}
				calculoTotaisTemp.put("valorTotal", valorTotal);
				calculoTotais = calculoTotaisTemp;
			}
	
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
	
	

	/**
	 * Imprime o relatorio gerencial de compras.
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
	 * Gera a tela de relatorio gerencial de sucata
	 * @param event
	 * TODO funcionando na tela, mas busca somente pelo funcionario, adicionar busca pelo carro
	 */
	public void gerarRelatorioGerencialDeSucata(ActionEvent event){
		
		try {
			
			listaRelatorio = new ArrayList();
			listaRelatorio = relatorioDao.gerarRelatorioGerencialDeSucataHibernate(funcionario, produto, dataInicial, dataFinal);
			
			if(listaRelatorio.isEmpty()){
				FacesContext context = FacesContext.getCurrentInstance();  
		        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção" , "Sua Busca não retornou nenhum resultado"));
		        numNota = null;
			}else{
				
				List<RelatorioGerencialSucata> lista = listaRelatorio;
				Map<String, Double> total = new HashMap<String, Double>();
				Double valorTotal = new Double("0");
				Double vlrTemp = new Double("0");
				
				for(RelatorioGerencialSucata compra : lista){
					vlrTemp = compra.getValor();
					valorTotal =  vlrTemp  + valorTotal;
				}
				total.put("valorTotal", valorTotal);
				calculoTotais = total;
			}
	
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
	
	/**
	 * Imprimir relatorio de sucatas.
	 * @param event
	 */
	public void imprimirRelatorioGerencialDeSucata(ActionEvent event){
		
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request   = (HttpServletRequest) context.getExternalContext().getRequest();		
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();

		try {
			
				// parametros do relatorio
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("funcionario", funcionario);
			params.put("produto", produto);
			params.put("dataInicial", dataInicial);
			params.put("dataFinal", dataFinal);
			params.put("logo", new ImageIcon (context.getExternalContext().getResource("/imagem/medral.JPG")).getImage());
			
			String fileName = "relGerencialSucata"; 
			ReportUtils.gerarPdf("/reports/relItensSucateados.jasper", fileName, listaRelatorio, params, request, response);
			//ReportUtils.gerarXls("/reports/relItensSucateados.jasper", fileName, listaRelatorio, params, request, response);

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
	 * Metodo responsavel por gerar a tela de rastreabilidade
	 * @param event
	 * TODO gera a primeira parte da tela, tem que colocar uma linha expansivel para mostrar a lista de detalhes.
	 */
	public void gerarRelatorioGerencialRastreabilidade(ActionEvent event){

		try {

			listaRelatorio = new ArrayList();
			listaRelatorio = relatorioDao.gerarRelatorioGerencialRastreabilidade(rastreabilidade, produto);

			if(listaRelatorio.isEmpty()){
				FacesContext context = FacesContext.getCurrentInstance();  
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção" , "Sua Busca não retornou nenhum resultado"));
			}else{

				List<RelatorioGerencialRastreabilidade> listaRelatorioTemp = listaRelatorio;
				List listaDetalhes = new ArrayList();
				for (RelatorioGerencialRastreabilidade rastreio : listaRelatorioTemp ) {
					
					listaDetalhes = relatorioDao.gerarRelatorioGerencialRastreabilidadeDealhes(rastreio.getIdRastreabilidade());
					rastreio.setListaDetalhes(listaDetalhes);
					
				}
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

	}
	
	/**
	 * Imprimir relatorio Gerencial Rastreabilidde
	 * @param event
	 */
	public void imprimirRelatorioGerencialRastreabilidade(ActionEvent event){
		
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request   = (HttpServletRequest) context.getExternalContext().getRequest();		
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();

		try {
			
			// parametros do relatorio
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("SUBREPORT_DIR", request.getServletContext().getRealPath("/reports/"));
			params.put("logo", new ImageIcon(context.getExternalContext().getResource("/imagem/medral.JPG")).getImage());
			
			String fileName = "relRastreabilidadeDeProdutos"; 
			ReportUtils.gerarPdf("/reports/relRastreabilidadeDeProdutos.jasper", fileName, listaRelatorio, params, context);

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
	 * Gera a tela de produto por funcionario
	 * @param event
	 * TODO BUSCAS SIMPLES;
	 */
	public void gerarRelatorioGerencialProdutosPorFuncionarios(ActionEvent event){


		try {

			listaRelatorio = new ArrayList();
				
			  //BUSCAS FULL SCAM SOMENTE COM O ID DO FUNCIONARIO SEGUNDO AS REGRAS ABAIXO
			  // Chapa diferente de null pocura no individual
			  // placa diferente de null procura em veiculos
			  // se os dois estiverem preenchidos, procuro o funcionario se eu não achar procuro o veiculo e recupero a chapa para procurar o funcionario novamente.
			  // O produto vem de individual ou de veiculos
			
			if(listaRelatorio.isEmpty()){
				
				FacesContext context = FacesContext.getCurrentInstance();  
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção" , "Sua Busca não retornou nenhum resultado"));
				
			}else{

				List<RelatorioGerencialProdutosPorFuncionarios> lista = listaRelatorio;
				Map<String, Double> total = new HashMap<String, Double>();
				Double valorTotal = new Double("0");
				Double vlrTemp = new Double("0");

				for(RelatorioGerencialProdutosPorFuncionarios produto : lista){
					vlrTemp = produto.getProduto().getValor();
					valorTotal =  vlrTemp  + valorTotal;
				}
				total.put("valorTotal", valorTotal);
				calculoTotais = total;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}



	}
	
	/**
	 * Imprimir relarorio de produtos por funcionarios.
	 * @param event
	 */
	public void imprimirRelatorioGerencialProdutosPorFuncionarios(ActionEvent event){
		
		FacesContext context = FacesContext.getCurrentInstance();

		try {
			
			Map<String, Object> params = new HashMap<String,Object>();
			params.put("matricula", matricula);
			params.put("placa", placa);
			//TODO recuperar o nome do funcionario usando o dão do hibernate, pela matricula, se não achar procurar o responsavel pelo carro usando a placa
			//params.put("funcionario_encarregado", listaRelatorio.get(0));
			params.put("logo", new ImageIcon (context.getExternalContext().getResource("/imagem/medral.JPG")).getImage());
			
			String fileName = "relProdutosPorFuncionarios"; 
			ReportUtils.gerarPdf("/reports/relProdutosPorFuncionarios.jasper", fileName, listaRelatorio, params, context);
			
		} catch (JRException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Gera a tela de relatorio administrativo de entrada de notas
	 * @param event
	 * 
	 */
	public void gerarRelatorioAdministrativoEntrada(ActionEvent event)  {
		
		try {
			listaRelatorio = new ArrayList();
			listaRelatorio = relatorioDao.gerarRelAdministrativoEntrada(numNota, dataInicial );
			FacesContext context = FacesContext.getCurrentInstance();  
			
			if(listaRelatorio.isEmpty()){
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção" , "Sua Busca não retornou nenhum resultado"));
			}else{

				List<RelatorioAdministrativoEntrada> lista = listaRelatorio;
				if(numNota.isEmpty()){
					context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção" , "Para Imprimir o Número da Nota é Obrigatório"));
				}
				
				Map<String, Double> total = new HashMap<String, Double>();
				Double valorTotal = new Double("0");
				Double vlrTemp = new Double("0");

				for(RelatorioAdministrativoEntrada entrada : lista){
					vlrTemp = entrada.getTotal();
					valorTotal =  vlrTemp  + valorTotal;
				}
				total.put("valorTotal", valorTotal);
				calculoTotais = total;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}




	}
	
	/**
	 * Imprime o relatorio administrativo de entrada
	 * TODO buscar o fornecedor pelo id para imprimir no relatorio.
	 * @param event
	 */
	public void imprimirRelatorioAdministrativoEntrada(ActionEvent event)  {
		
		FacesContext context = FacesContext.getCurrentInstance();

		try {
			
			//Fornecedor f = (Fornecedor) new FornecedorDAO().getSessao().getNamedQuery("").setInteger(":id", lista.get(0).getIdFornecedor()).uniqueResult();
			Map<String, Object> params = new HashMap<String,Object>();
			params.put("fornecedor", "");
			params.put("endereco", "");
			params.put("nota", "");
			params.put("cnpj", "");
			params.put("telefone", "");
			params.put("data", new Date());
			params.put("logo", new ImageIcon(context.getExternalContext().getResource("/imagem/medral.JPG")).getImage());
			
			String fileName = "relatorioNotaFiscalEntrada"; 
			ReportUtils.gerarPdf("/reports/relAdmNotaFiscalEntrada.jasper", fileName, listaRelatorio, params, context);
			
		} catch (JRException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	/**
	 * TODO contem ação na tela, verificar no php
	 * Gera a tela de relatorio administrativo individual
	 * @param event
	 * 
	 */
	public void gerarRelatorioAdministrativoIndividual(ActionEvent event) {

		try {

			listaRelatorio = new ArrayList();
			listaRelatorio = relatorioDao.gerarRelAdministrativoIndividual(matricula);

			if(listaRelatorio.isEmpty()){
				FacesContext context = FacesContext.getCurrentInstance();  
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção" , "Sua Busca não retornou nenhum resultado"));
			}else{

			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	/**
	 * Imprime o relatorio administrativo individual
	 * @param event
	 */
	public void imprimirRelatorioAdministrativoIndividual(ActionEvent event) {
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		try {

			Map<String, Object> params = new HashMap<String, Object>();
			params.put("funcionario", funcionario);
			params.put("logo", new ImageIcon(context.getExternalContext().getResource("/imagem/medral.JPG")).getImage());
			
			String fileName = "relatorioAdmIndividual";
			ReportUtils.gerarPdf("/report/relAdmIndividual.jasper",fileName, listaRelatorio, params, context);

		} catch (JRException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		}
		
	}
	
	/**
	 * Gera a tela de relatorio administrativo de veiculos
	 * @param event
	 */
	public void gerarRelatorioAdministrativoVeiculos(ActionEvent event) {

		try {

			listaRelatorio = new ArrayList();
			listaRelatorio = relatorioDao.gerarRelAdministrativoVeiculos(placa);

			if(listaRelatorio.isEmpty()){
				FacesContext context = FacesContext.getCurrentInstance();  
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção" , "Sua Busca não retornou nenhum resultado"));
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	
	/**
	 * Imprime relatorio de veiculos
	 * @param event
	 */
	public void imprimirRelatorioAdministrativoVeiculos(ActionEvent event) {
		
		FacesContext context = FacesContext.getCurrentInstance();

		try {
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("placa", placa);
			params.put("nomeFuncionario", funcionario);
			params.put("logo", new ImageIcon(context.getExternalContext().getResource("/imagem/medral.JPG")).getImage());
			
			String fileName = "relAdmistrativoDeVeiculos";
			ReportUtils.gerarPdf("/report/relAdmVeiculos.jasper", fileName, listaRelatorio, params, context);
			
		} catch (JRException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		}
		
	}
	
	/**
	 * Gera a tela de relatorio adm de movimentação
	 * @param event
	 * @throws SQLException
	 */
	public void gerarRelatorioAdministrativoMovimentacao(ActionEvent event) throws SQLException {

		listaRelatorio = new ArrayList();
		listaRelatorio = relatorioDao.gerarRelAdministrativoMovimentacao(matricula, placa, rastreabilidade, produto);
		
		if(listaRelatorio.isEmpty()){
			FacesContext context = FacesContext.getCurrentInstance();  
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção" , "Sua Busca não retornou nenhum resultado"));
		}else{

		}
		
		
	}

	/**
	 * Gerar relatorio administrativo de movimentação
	 * @param event
	 * @throws SQLException
	 */
	public void imprimirRelatorioAdministrativoMovimentacao(ActionEvent event)  {
		
		FacesContext context = FacesContext.getCurrentInstance();

		try {
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("logo", new ImageIcon(context.getExternalContext().getResource("/imagem/medral.JPG")).getImage());
			
			String fileName = "relAdmistrativoDeMovimentacao";
			ReportUtils.gerarPdf("/report/relAdmMovi.jasper", fileName, listaRelatorio, params, context);
		
		} catch (JRException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		} 
	}
	
	/**
	 * TODO Contem Ação
	 * Gera a tela para o relatorio de Teste Eletrico
	 * @param event
	 */
	public void gerarRelatorioAdministrativoTesteEletrico(ActionEvent event) {
		
		try {

			listaRelatorio = new ArrayList();
			listaRelatorio = relatorioDao.gerarRelAdministrativoTesteEletrico(produto , rastreabilidade);

			if(listaRelatorio.isEmpty()){
				FacesContext context = FacesContext.getCurrentInstance();  
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção" , "Sua Busca não retornou nenhum resultado"));
			}else{

				List<RelatorioAdmTesteEletrico> lista = listaRelatorio;
				Map<String, Double> total = new HashMap<String, Double>();
				Double valorTotal = new Double("0");
				Double vlrTemp = new Double("0");

				for(RelatorioAdmTesteEletrico testeEletrico : lista){
					vlrTemp = testeEletrico.getValor();
					valorTotal =  vlrTemp  + valorTotal;
				}
				total.put("valorTotal", valorTotal);
				calculoTotais = total;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	
	/**
	 * Imprime o relatorio administrativo Teste Eletrico
	 * @param event
	 */
	public void imprimirRelatorioAdministrativoTesteEletrico(ActionEvent event) {
		
		FacesContext context = FacesContext.getCurrentInstance();

		try {
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("logo", new ImageIcon(context.getExternalContext().getResource("/imagem/medral.JPG")).getImage());
			
			String fileName = "relAdmistrativoTesteEletrico";
			ReportUtils.gerarPdf("/report/relAdmTesteEletrico.jasper", fileName, listaRelatorio, params, context);
		
		} catch (JRException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		} 
		
	}
	
	/**
	 * Gera a tela para o relatorio de Vales
	 * @param event
	 */
	public void gerarRelatorioAdministrativoVales(ActionEvent event) {
		
		try {

			listaRelatorio = new ArrayList();
			listaRelatorio = relatorioDao.gerarRelAdministrativoVales(matricula, setor, dataInicial, dataFinal);

			if(listaRelatorio.isEmpty()){
				FacesContext context = FacesContext.getCurrentInstance();  
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção" , "Sua Busca não retornou nenhum resultado"));
			}else{

				List<RelatorioAdmVales> lista = listaRelatorio;
				Map<String, Double> total = new HashMap<String, Double>();
				Double valorTotal = new Double("0");
				Double vlrTemp = new Double("0");

				for(RelatorioAdmVales vale : lista){
					vlrTemp = vale.getValor();
					valorTotal =  vlrTemp  + valorTotal;
				}
				total.put("valorTotal", valorTotal);
				calculoTotais = total;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		
	}
	
	/**
	 * Imprime o relatorio administrativo de vales
	 * @param event
	 */
	public void imprimirRelatorioAdministrativoVales(ActionEvent event) {
		
		FacesContext context = FacesContext.getCurrentInstance();

		try {
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("logo", new ImageIcon(context.getExternalContext().getResource("/imagem/medral.JPG")).getImage());
			
			String fileName = "relAdmistrativoVales";
			ReportUtils.gerarPdf("/report/relAdmVales.jasper", fileName, listaRelatorio, params, context);
		
		} catch (JRException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		} 
	}
	
	/**
	 * Gera a tela para o relatorio de Reforma
	 * @param event
	 */
	public void gerarRelatorioAdministrativoReforma(ActionEvent event) {
		
		try {

			listaRelatorio = new ArrayList();
			listaRelatorio = relatorioDao.gerarRelAdministrativoReforma(dataInicial, produto);

			if(listaRelatorio.isEmpty()){
				FacesContext context = FacesContext.getCurrentInstance();  
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção" , "Sua Busca não retornou nenhum resultado"));
			}else{

				List<RelatorioAdmReforma> lista = listaRelatorio;
				Map<String, Double> total = new HashMap<String, Double>();
				Double valorTotal = new Double("0");
				Double vlrTemp = new Double("0");

				for(RelatorioAdmReforma reforma : lista){
					vlrTemp = reforma.getValor();
					valorTotal =  vlrTemp  + valorTotal;
				}
				total.put("valorTotal", valorTotal);
				calculoTotais = total;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	
	/**
	 * Imprime o relatorio de Reforma
	 * @param event
	 */
	public void imprimirRelatorioAdministrativoReforma(ActionEvent event) {
		
		FacesContext context = FacesContext.getCurrentInstance();

		try {
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("logo", new ImageIcon(context.getExternalContext().getResource("/imagem/medral.JPG")).getImage());
			
			String fileName = "relAdmistrativoProdutosAguadandoRefoma";
			ReportUtils.gerarPdf("/report/relAdmReforma.jasper", fileName, listaRelatorio, params, context);
		
		} catch (JRException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
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
	
	public String getNumNota() {
		return numNota;
	}


	public void setNumNota(String numNota) {
		this.numNota = numNota;
	}


	/**
	 * @return the calculoTotais
	 */
	public Map getCalculoTotais() {
		return calculoTotais;
	}


	/**
	 * @param calculoTotais the calculoTotais to set
	 */
	public void setCalculoTotais(Map calculoTotais) {
		this.calculoTotais = calculoTotais;
	}


	/**
	 * @return the func
	 */
	public Funcionario getFunc() {
		return func;
	}

	/**
	 * @param func the func to set
	 */
	public void setFunc(Funcionario func) {
		this.func = func;
	}


	/**
	 * @return the setor
	 */
	public String getSetor() {
		return setor;
	}


	/**
	 * @param setor the setor to set
	 */
	public void setSetor(String setor) {
		this.setor = setor;
	}
}
