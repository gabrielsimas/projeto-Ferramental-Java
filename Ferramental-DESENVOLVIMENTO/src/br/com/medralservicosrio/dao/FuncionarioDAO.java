/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.medralservicosrio.dao;

import br.com.medralservicosrio.generics.DAO;
import br.com.medralservicosrio.modelo.Funcionario;

/**
 *
 * @author Luis Gabriel Nascimento Simas
 */
public class FuncionarioDAO extends DAO<Funcionario,Integer> {

    /*private Connection connection;
    
    public boolean validar(Funcionario funcionario) throws SQLException {
        boolean valido = false;
        connection = ConnectionFactory.getConnection();
        CallableStatement cs = this.connection.prepareCall("{call validar_login(?,?)}");

        valido  = cs.executeQuery().first();
        return valido;
    }*/
}
