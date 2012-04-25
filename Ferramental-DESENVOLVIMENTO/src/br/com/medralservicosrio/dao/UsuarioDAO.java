/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.medralservicosrio.dao;

import br.com.medralservicosrio.generics.DAO;
import br.com.medralservicosrio.modelo.Usuario;

/**
 *
 * @author Luis Gabriel Nascimento Simas
 */

public class UsuarioDAO extends DAO<Usuario,Integer> {

	/*TODO: Adicionar Métodos de autenticação para o Usuário
	 * 
	 */
	
   /* private Connection connection;
    
    *//**
     * 
     * @param usuario
     * @return valido
     * @throws SQLException
     *//*
    public boolean validar(Usuario usuario) throws SQLException {
        boolean valido = false;
        connection = ConnectionFactory.getConnection();
        
        CallableStatement cs = this.connection.prepareCall("{call validar_login(?,?)}");

        cs.setString(1, usuario.getLogin().trim());
        cs.setString(2, usuario.getSenha().trim());
        valido  = cs.executeQuery().first();
        return valido;
    }*/
}