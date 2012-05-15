/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.medralservicosrio.dao;

import br.com.medralservicosrio.generics.DAO;
import br.com.medralservicosrio.modelo.Produto;

/**
 *
 * @author Luis Gabriel Nascimento Simas
 */

public class ProdutoDAO extends DAO<Produto, Integer> {

	public ProdutoDAO(Class<Produto> entidade) {
		super(entidade);
		// TODO Auto-generated constructor stub
	}
	
	//TODO:Adicionar outras consultas aqui.

    /*private Connection connection;
    
    public boolean validar(Produto produto) throws SQLException {
        boolean valido = false;
        connection = ConnectionFactory.getConnection();
        CallableStatement cs = this.connection.prepareCall("{call validar_login(?,?)}");

        valido  = cs.executeQuery().first();
        return valido;
    }*/
}
