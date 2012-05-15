package br.com.medralservicosrio.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import br.com.medralservicosrio.generics.DAO;
import br.com.medralservicosrio.modelo.MovimentacaoIndividual;

public class MovimentacaoIndividualDAO extends DAO<MovimentacaoIndividual, Integer> {

	public MovimentacaoIndividualDAO(Class<MovimentacaoIndividual> entidade) {
		super(entidade);
	}
	
	//Localiza por Chapa
	public List<MovimentacaoIndividual> localizaPorChapa(Integer chapa){
		
		String query = "SELECT m FROM MovimentacaoIndividual m WHERE m.matrFuncionario = :p1";
		List<MovimentacaoIndividual> resultado = new ArrayList<MovimentacaoIndividual>();
		
		try{
			
			Query consulta = getSessao().createQuery(query);
			consulta.setInteger("p1", chapa);
			
			resultado = consulta.list();
			
			if (resultado != null){
				return resultado;
			}
			
		} catch (Exception excecao){
			System.out.println(excecao.getMessage());
		}
		
		return null;
	}
}
