package br.com.medralservicosrio.controlador;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import br.com.medralservicosrio.conexao.ConnectionFactory;
import br.com.medralservicosrio.util.ReportUtils;

@WebServlet(name = "GerarRelatorio", urlPatterns = {"/relatorio.do"})
public class GerarRelatorio extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        
        response.setContentType("text/html;charset=ISO-8859-1");
        //response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
                     
        String cmd = request.getParameter("cmd");
      
        OutputStream outStream = null;
        Connection con = ConnectionFactory.getConnection();
        out.print("ggg");
        out.print(cmd);
        if(cmd.equals("relReciboSolicitacaoEpi")){

            out.print("teste2");
            // obtém o relatório compilado
            InputStream inputStream = getClass().getResourceAsStream("/relReciboSolicitacaoEpi.jasper" );
            
            // preenche o mapa de parâmetros
            Map<String, Object> parametros = new HashMap<String, Object>();
            parametros.put( "vazio", "vazio" );

            try {
                // gera o relatório e atribui o OutputStream gerado
                outStream = ReportUtils.createPDFReport(inputStream,parametros,con,response);

            } catch ( JRException exc ) {
                exc.printStackTrace();
            } finally {

                // se não aconteceu nenhum problema, fecha o output stream
                if ( outStream != null ) {
                    outStream.close();
                }

            }
        }
        
        
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    	/**
    	try {
            processRequest(request, response);
            
        } catch (ServletException ex){
        //} catch (ServletException | IOException | SQLException ex) {
            Logger.getLogger(GerarRelatorio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex){
        	Logger.getLogger(GerarRelatorio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex){
        	Logger.getLogger(GerarRelatorio.class.getName()).log(Level.SEVERE, null, ex);
        }
        **/
    	doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       try {
            processRequest(request, response);
            
       } catch (ServletException ex){
    	   
               Logger.getLogger(GerarRelatorio.class.getName()).log(Level.SEVERE, null, ex);
       } catch (IOException ex){
           	Logger.getLogger(GerarRelatorio.class.getName()).log(Level.SEVERE, null, ex);
       } catch (SQLException ex){
           	Logger.getLogger(GerarRelatorio.class.getName()).log(Level.SEVERE, null, ex);
       }
       
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
