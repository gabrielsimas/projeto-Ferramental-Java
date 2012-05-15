/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.medralservicosrio.dao;

import org.hibernate.Query;

import br.com.medralservicosrio.generics.DAO;
import br.com.medralservicosrio.modelo.Usuario;

/**
 *
 * @author Luis Gabriel Nascimento Simas
 */

public class UsuarioDAO extends DAO<Usuario,Integer> {

		
	public UsuarioDAO(Class<Usuario> entidade) {
		super(entidade);
	
	}
	//*TODO: Adicionar Métodos de autenticação para o Usuário - DONE

public boolean autenticar(String usuario, String senha){
	   
	   String query = "SELECT u FROM Usuario u WHERE u.login = :p1 AND u.senha = :p2";
	   
	   
	   
	   try{
		   
		   Query consulta = getSessao().createQuery(query);
		   
		   //Recebe os parametros
		   consulta.setString("p1", usuario);
		   consulta.setString("p2", senha);
		   
		   /*Usuario user = new Usuario();*/
		   
		   //Verifica o resultado
		   Usuario user = (Usuario) consulta.uniqueResult();
			
		   if (user != null){
			   return true;
		   }
		   
		   
	   } catch (Exception ex){
		   System.out.println(ex.getMessage());
	   }
	   
	   return false;
   }
}