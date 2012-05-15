package br.com.medralservicosrio.dao;

import br.com.medralservicosrio.generics.DAO;
import br.com.medralservicosrio.modelo.EntradaViaNota;

public class EntradaViaNotaDAO extends DAO<EntradaViaNota, Integer> {

	public EntradaViaNotaDAO(Class<EntradaViaNota> entidade) {
		super(entidade);
		// TODO Auto-generated constructor stub
	}
	
	//TODO: Gerar Calculo da Linha dos itens para subtotal (quantidade x Preço)
	
}
