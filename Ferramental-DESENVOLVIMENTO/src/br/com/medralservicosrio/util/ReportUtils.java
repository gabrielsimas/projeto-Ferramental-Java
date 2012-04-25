package br.com.medralservicosrio.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;


public class ReportUtils {
    public static OutputStream createPDFReport(InputStream inputStream,Map<String, Object> parametros,Connection conexao, HttpServletResponse response ) throws JRException, IOException {

    // configura o content type do response
    response.reset();
    response.setHeader("Window-target","blank");
    response.setContentType("application/pdf");

    // obtém o OutputStream para escrever o relatório

    OutputStream out = response.getOutputStream();


    /*
     * Cria um JasperPrint, que é a versão preenchida do relatório,
     * usando uma conexão.
     */

    JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros, conexao );

    // Exporta em PDF, escrevendo os dados no output stream do response.

    JRExporter exporter = new JRPdfExporter();

    exporter.setParameter(JRExporterParameter.JASPER_PRINT,jasperPrint);

    exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,out);

    // gera o relatório

    exporter.exportReport();

    // retorna o OutputStream

    return out;
    }

}
