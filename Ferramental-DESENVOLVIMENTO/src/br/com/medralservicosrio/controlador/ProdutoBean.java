/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.medralservicosrio.controlador;

import java.sql.SQLException;

import javax.faces.bean.ManagedBean;

import br.com.medralservicosrio.modelo.Produto;


/**
 *
 * @author Eliane
 */

@ManagedBean
public class ProdutoBean {
    
    private Produto produto = new Produto();
    //private ProdutoDAO produtodao = new ProdutoDAO();
    
    public Produto getproduto(){
        return produto; 
    }  
    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    
    public String efetuaLogin() throws SQLException{
        boolean loginvalido = produto.validar(produto);
        if (loginvalido){
            return "/paginas/index?faces-redirect=true";
        }
        else{
           produto = new Produto();
            return "login";
        }
    }
}
