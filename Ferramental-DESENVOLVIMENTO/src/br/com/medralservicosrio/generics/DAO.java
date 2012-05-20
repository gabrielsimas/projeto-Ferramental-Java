package br.com.medralservicosrio.generics;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.medralservicosrio.util.HibernateUtil;

/**
 * 
 * @author Gabriel
 *
 * @param <E>
 * @param <ID>
 */
//public interface LivroDAO extends GenericDAO<Livro, Integer> {
public abstract class DAO<E, ID extends Serializable> implements GenericDAO<E, ID> {
	
	private Class<E> entidade;
	Session sessao;
	Transaction transacao;
	Query consulta;
	
	/**
	 * 
	 */
	public DAO(Class<E> entidade) {
		this.entidade = entidade;
		conexaoInicial();
	}
		
		
	@Override
	public void criar(E entidade)  {
		
		try {
			conexaoInicial();
			sessao.save(entidade);
			transacao.commit();
		} catch (HibernateException ex){
			System.err.println("Erro ao Gravar Registro de " + entidade.getClass().toString()
					+ "efetuando o Rollback!!" 
					+ "\nErro reportado: " + ex.getMessage() );
				ex.printStackTrace();
			transacao.rollback();
		}
	}

	@Override
	public void atualizar(E entidade)  {
		try {
			conexaoInicial();
			sessao.merge(entidade);
			transacao.commit();
		} catch (HibernateException ex) {
			System.err.println("Erro ao Gravar Registro de " + entidade.getClass().toString()
					+ "efetuando o Rollback!!" 
					+ "\nErro reportado: " + ex.getMessage() );
				ex.printStackTrace();
			transacao.rollback();
		} finally {
			sessao.close();
		}
	}

	@Override
	public void apagar(E entidade)  {
		try {
			conexaoInicial();
			sessao.delete(entidade);
			transacao.commit();
		} catch (HibernateException ex) {
			System.err.println("Erro ao Apagar Registro de " + entidade.getClass().toString()
					+ "efetuando o Rollback!!" 
					+ "\nErro reportado: " + ex.getMessage() );
				ex.printStackTrace();
			transacao.rollback();
		} finally {
			sessao.close();
		}
	}

	@Override
	public List listarTudo(Class<E> entidade)  {
		//Inicia uma Lista para os resultados
		List resultado = null;
		
		try {
			conexaoInicial();
			//Pega o nome de forma recursiva o nome da Classe
			consulta = sessao.createQuery("FROM "
					+ entidade.getName());
			resultado = consulta.list();
		} catch (HibernateException ex) {
			System.err.println("Erro ao Listar todos os registro de " + entidade.getSimpleName()
					+ "efetuando o Rollback!!" 
					+ "\nErro reportado: " + ex.getMessage() );
				ex.printStackTrace();
			transacao.rollback();
		} finally {
			sessao.close();
		}
		
		return resultado;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E listarPorId(Class<E> entidade, ID key)  {
		Object objeto = null;
		
		try {
			conexaoInicial();
			objeto = sessao.get(entidade, key);
		} catch (HibernateException ex) {
			System.err.println("Erro ao buscar o id " + key +" todos os registro de " + entidade.getClass().toString()
					+ "efetuando o Rollback!!" 
					+ "\nErro reportado: " + ex.getMessage());
				ex.printStackTrace();
			transacao.rollback();
		} finally {	
			sessao.close();
		}
		
		return (E) objeto;
		
	}
	
	public void conexaoInicial() throws HibernateException{
		sessao = HibernateUtil.getSessionFactory().openSession();
		
		//if (sessao.isOpen()){
		//	System.out.println("[DAO] - Sessao Aberta!");
		//	if (sessao.isConnected()){
		//		System.out.println("[DAO] - Sessao Esta conectada!");
		//		sessao.getTransaction();
		//		System.out.println("Pegando a transação já aberta!!");
		//	}
		//} else {
		//	System.out.println("[DAO] Iniciando transação - nenhuma existente");
			transacao = sessao.beginTransaction();
		//}
		
	}
	
	public void manusearExcecao(HibernateException e) throws RuntimeException{
		transacao.rollback();
		throw new RuntimeException(e);
	}

	public Session getSessao() {
		return sessao;
	}

	public void setSessao(Session sessao) {
		this.sessao = sessao;
	}

	public Transaction getTransacao() {
		return transacao;
	}

	public void setTransacao(Transaction transacao) {
		this.transacao = transacao;
	}

	public Query getConsulta() {
		return consulta;
	}

	public void setConsulta(Query consulta) {
		this.consulta = consulta;
	}


	public Class<E> getEntidade() {
		return entidade;
	}


	public void setEntidade(Class<E> entidade) {
		this.entidade = entidade;
	}
	
	/*Valores antigos porque era interface
	public void save(Livro livro);
	public Livro getLivro(Integer id);
	public List<Livro> list();
	public void remove(Livro livro);
	public void update(Livro livro);
	*/
}
