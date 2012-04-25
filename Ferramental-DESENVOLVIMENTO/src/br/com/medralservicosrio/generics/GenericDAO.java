package br.com.medralservicosrio.generics;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO<E, ID extends Serializable> {
	
	void criar(E entidade);
	void atualizar(E entidade);
	void apagar(E entidade);
	//List listarTudo(E entidade);
	List listarTudo(Class<E> entidade,String namedQuery);
	E listarPorId(Class<E> entidade, ID key);
	
	
}
