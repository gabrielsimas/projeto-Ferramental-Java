package br.com.medralservicosrio.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import br.com.medralservicosrio.generics.DAO;
import br.com.medralservicosrio.modelo.MovimentacaoIndividual;
import br.com.medralservicosrio.modelo.Produto;

public class MovimentacaoIndividualDAO extends
		DAO<MovimentacaoIndividual, Integer> {

	// Localiza por Chapa
	public List<MovimentacaoIndividual> localizaPorChapa(Integer chapa) {

		String query = "SELECT m FROM MovimentacaoIndividual m WHERE m.matrFuncionario = :p1";
		List<MovimentacaoIndividual> resultado = new ArrayList<MovimentacaoIndividual>();

		try {

			Query consulta = getSessao().createQuery(query);
			consulta.setInteger("p1", chapa);

			resultado = consulta.list();

			if (resultado != null) {
				return resultado;
			}

		} catch (Exception excecao) {
			System.out.println(excecao.getMessage());
		}

		return null;
	}

	public boolean create(MovimentacaoIndividual movimentacaoIndividual) {
		
		return true;

	}
	
	
	/*public List<Produto> localizaPorId(Integer chapa) {

		String query = "SELECT m FROM MovimentacaoIndividual m WHERE m.matrFuncionario = :p1";
		List<MovimentacaoIndividual> resultado = new ArrayList<MovimentacaoIndividual>();

		try {

			Query consulta = getSessao().createQuery(query);
			consulta.setInteger("p1", chapa);

			resultado = consulta.list();

			if (resultado != null) {
				return resultado;
			}

		} catch (Exception excecao) {
			System.out.println(excecao.getMessage());
		}

		return null;
	}*/
}
