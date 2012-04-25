/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.medralservicosrio.controlador;

import java.sql.SQLException;

import javax.faces.bean.ManagedBean;

import br.com.medralservicosrio.modelo.Rastreabilidade;


/**
 *
 * @author Eliane
 */

@ManagedBean

public class RastreabilidadeBean {
    
    private Rastreabilidade rastreabilidade = new Rastreabilidade();
    //private RastreabilidadeDAO rastreabilidadedao = new RastreabilidadeDAO();
    
    public Rastreabilidade getrastreabilidade(){
        return rastreabilidade; 
    }  
    public void setRastreabilidade(Rastreabilidade rastreabilidade) {
        this.rastreabilidade = rastreabilidade;
    }
    
    public String efetuaLogin() throws SQLException{
        boolean loginvalido = rastreabilidade.validar(rastreabilidade);
        if (loginvalido){
            return "/paginas/index?faces-redirect=true";
        }
        else{
            rastreabilidade = new Rastreabilidade();
            return "login";
        }
    }
}
