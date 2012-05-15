/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.medralservicosrio.dao;

import br.com.medralservicosrio.generics.DAO;
import br.com.medralservicosrio.modelo.Rastreabilidade;

/**
 *
 * @author Luis Gabriel Nascimento Simas
 */
public class RastreabilidadeDAO extends DAO<Rastreabilidade,Integer> {

	public RastreabilidadeDAO(Class<Rastreabilidade> entidade) {
		super(entidade);
		// TODO Auto-generated constructor stub
	}

   /* private Connection connection;
    
    public boolean validar(Rastreabilidade rastreabilidade) throws SQLException {
        boolean valido = false;
        connection = ConnectionFactory.getConnection();
        CallableStatement cs = this.connection.prepareCall("{call validar_login(?,?)}");

        valido  = cs.executeQuery().first();
        return valido;
    }*/
}
