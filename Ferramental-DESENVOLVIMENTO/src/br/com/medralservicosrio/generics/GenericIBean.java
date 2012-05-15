package br.com.medralservicosrio.generics;

import javax.faces.event.ActionEvent;

public interface GenericIBean {
	
	void cadastrar();
	void atualizar(ActionEvent evento);
	void excluir(ActionEvent evento);
	void limpar();
	String voltar();
	

}
