/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.medralservicosrio.controlador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.medralservicosrio.modelo.Fornecedor;


/**
 *
 * @author Eliane
 */

@ManagedBean
@SessionScoped
public class FornecedorBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	private Fornecedor fornecedor;
	private List fornecedores;
	
	
	/**
	 * Construtor da Classe
	 */
	public FornecedorBean() {
		fornecedor = new Fornecedor();
		fornecedores = new ArrayList<Fornecedor>();
		
	}
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	public List getFornecedores() {
		return fornecedores;
	}
	public void setFornecedores(List fornecedores) {
		this.fornecedores = fornecedores;
	}
	
	
	
	/* Versão Inicial */
    /*private Fornecedor fornecedor = new Fornecedor();
    
    public Fornecedor getfornecedor(){
        return fornecedor; 
    }  
    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
    
    public String efetuaLogin() throws SQLException{
    	
        boolean loginvalido = fornecedor.validar(fornecedor);
        
        if (loginvalido){
            return "/paginas/index?faces-redirect=true";
        } else {
            fornecedor = new Fornecedor();
            return "login";
            
        }
    }*/
}
