package br.com.medralservicosrio.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.Collection;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;


public class ReportUtils {
    public static OutputStream createPDFReport(InputStream inputStream,Map<String, Object> parametros,Connection conexao, HttpServletResponse response ) throws JRException, IOException {

    // configura o content type do response
    response.reset();
    response.setHeader("Window-target","blank");
    response.setContentType("application/pdf");

    // obtÃ©m o OutputStream para escrever o relatÃ³rio

    OutputStream out = response.getOutputStream();


    /*
     * Cria um JasperPrint, que Ã© a versÃ£o preenchida do relatÃ³rio,
     * usando uma conexÃ£o.
     */

    JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros, conexao );

    // Exporta em PDF, escrevendo os dados no output stream do response.

    JRExporter exporter = new JRPdfExporter();

    exporter.setParameter(JRExporterParameter.JASPER_PRINT,jasperPrint);

    exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,out);

    // gera o relatÃ³rio

    exporter.exportReport();

    // retorna o OutputStream

    return out;
    }

    /**
	 * Gera o relatório em PDF e exporta para o response.
	 * @param reportPath Caminho do Relatório
	 * @param fileName Nome do arquivo que será exibido para download
	 * @param data Dados a serem exibidos
	 * @param params Parâmetros para o relatório
	 * @param request Objeto Request
	 * @param response Objeto response
	 * @return Bytes do Arquivo PDF gerado
	 * @throws JRException Erro ao gerar relatório
	 * @throws IOException Erro ao escrever response
	 */
	public static void gerarPdf(String reportPath, String fileName, Collection data, Map params, HttpServletRequest request, HttpServletResponse response) throws JRException, IOException {
		InputStream is = request.getSession().getServletContext().getResourceAsStream(reportPath);
		
		response.setContentType("application/pdf");
		response.setHeader("Content-disposition","attachment; filename=\""+ fileName+ ".pdf\"");  

		JasperPrint print = JasperFillManager.fillReport(is, params,  new JRBeanCollectionDataSource(data));
		JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
		
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}
	
	/**
	 * Gera o relatório em PDF e exporta para o response.
	 * @param reportPath Caminho do Relatório
	 * @param fileName Nome do arquivo que será exibido para download
	 * @param data Dados a serem exibidos
	 * @param params Parâmetros para o relatório
	 * @param context Instância do FacesContext
	 * @return Bytes do Arquivo PDF gerado
	 * @throws JRException Erro ao gerar relatório
	 * @throws IOException Erro ao escrever response
	 */
	public static void gerarPdf(String reportPath, String fileName, Collection data, Map params, FacesContext context) throws JRException, IOException {
		
		HttpServletRequest request   = (HttpServletRequest) context.getExternalContext().getRequest();		
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
		
		InputStream is = request.getSession().getServletContext().getResourceAsStream(reportPath);
		
		response.setContentType("application/pdf");
		response.setHeader("Content-disposition","attachment; filename=\""+ fileName+ ".pdf\"");  

		JasperPrint print = JasperFillManager.fillReport(is, params,  new JRBeanCollectionDataSource(data));
		JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
		
		response.getOutputStream().flush();
		response.getOutputStream().close();
		
		context.renderResponse();
		context.responseComplete(); 
	}
	
	/**
	 * Gera o relatório em PDF e retorna os bytes.
	 * @param reportPath Caminho Relatório
	 * @param data Dados a serem exibidos
	 * @param params Parâmetros para o relatório
	 * @param request Objeto Request
	 * @return Bytes do Arquivo PDF gerado
	 * @throws JRException Erro ao gerar relatório
	 * @throws IOException Erro ao escrever response
	 */
	public static byte[] gerarPdf(String reportPath, Collection data, Map params, HttpServletRequest request) throws JRException, IOException {
		InputStream is = request.getSession().getServletContext().getResourceAsStream(reportPath);
		
		JasperPrint print = JasperFillManager.fillReport(is, params,  new JRBeanCollectionDataSource(data));
		
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		JasperExportManager.exportReportToPdfStream(print, os);
		
		return os.toByteArray();
	}
}
