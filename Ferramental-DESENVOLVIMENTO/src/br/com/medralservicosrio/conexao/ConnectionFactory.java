/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.medralservicosrio.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Marco Aurélio
 */

public class ConnectionFactory {
    
    private static final String URL     = "jdbc:mysql://mysql.medralservicosrio.com.br:3306/medralservicosrio_com_br_9";
    private static final String DRIVER  = "org.gjt.mm.mysql.Driver";
    private static final String USUARIO = "medralservic9";
    private static final String SENHA   = "fera2012medr@l";
	public static Object res;
	public static Object ps;
    
    public static Connection getConnection() throws SQLException {
    	
        try {
            Class.forName(DRIVER);
            System.out.println("Conectando ao banco...");
            
            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());
            
            return DriverManager.getConnection(URL, USUARIO, SENHA);
            
        } catch (ClassNotFoundException e) {
            throw new SQLException(e.getMessage());
        }
    }

	public static Connection getAbrirConexao() {
		// TODO Auto-generated method stub
		return null;
	}
}