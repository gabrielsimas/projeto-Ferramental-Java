/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.medralservicosrio.dao;

import br.com.medralservicosrio.generics.DAO;
import br.com.medralservicosrio.modelo.Veiculo;

/**
 *
 * @author Luis Gabriel Nascimento Simas
 */

public class VeiculoDAO extends DAO<Veiculo,Integer>{

	//TODO: Adicionar outros m�todos de tratamento, adequando as NamedQueries existentes na Classe
	//TODO: M�todo Listar por Placa
	//TODO: M�todo Itens de Movimenta��o
	
	
	
    /*private Connection connection;
    
    public boolean validar(Veiculo veiculo) throws SQLException {
        boolean valido = false;
        connection = ConnectionFactory.getConnection();
        CallableStatement cs = this.connection.prepareCall("{call validar_login(?,?)}");

        valido  = cs.executeQuery().first();
        return valido;
    }*/
}
