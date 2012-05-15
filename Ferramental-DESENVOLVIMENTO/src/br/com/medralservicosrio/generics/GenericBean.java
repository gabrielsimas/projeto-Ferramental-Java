package br.com.medralservicosrio.generics;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;

public abstract class GenericBean implements GenericIBean, Serializable{

	private static final long serialVersionUID = 4362332597312211129L;
	private final Severity MENSAGEM_ERRO 		= FacesMessage.SEVERITY_ERROR;
	private final Severity MENSAGEM_FATAL 		= FacesMessage.SEVERITY_FATAL;
	private final Severity MENSAGEM_INFO 		= FacesMessage.SEVERITY_INFO;
	private final Severity MENSAGEM_ADVERTENCIA = FacesMessage.SEVERITY_WARN;
	
	@Override
	public String voltar() {
		
		return "principal?faces-redirect=true";
	}
		
	public Severity getMENSAGEM_ERRO() {
		return MENSAGEM_ERRO;
	}
	public Severity getMENSAGEM_FATAL() {
		return MENSAGEM_FATAL;
	}
	public Severity getMENSAGEM_INFO() {
		return MENSAGEM_INFO;
	}
	public Severity getMENSAGEM_ADVERTENCIA() {
		return MENSAGEM_ADVERTENCIA;
	}
	
}
