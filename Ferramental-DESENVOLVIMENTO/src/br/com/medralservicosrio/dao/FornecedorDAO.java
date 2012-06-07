/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.medralservicosrio.dao;

import org.hibernate.Query;

import br.com.medralservicosrio.generics.DAO;
import br.com.medralservicosrio.modelo.Fornecedor;

/**
 *
 * @author Luis Gabriel Nascimento Simas
 */
public class FornecedorDAO extends DAO<Fornecedor, Integer>{

	//Localiza o Fornecedor pelo CNPJ
	public Fornecedor localizaPorCNPJ(String cnpj){
		
		String query = "SELECT f FROM Fornecedor f WHERE f.cnpj = :p1";
		
		try{
			Query consulta = getSessao().createQuery(query);
			consulta.setString("p1", cnpj);
			
			Fornecedor resultado = (Fornecedor) consulta.uniqueResult();
			
			return resultado;
		}catch (Exception ex){
			System.out.println(ex.getMessage());
			return null;
		}
	}

}
