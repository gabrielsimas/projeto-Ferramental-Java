/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.medralservicosrio.controlador;

import java.sql.SQLException;

import javax.faces.bean.ManagedBean;

import br.com.medralservicosrio.modelo.Veiculo;


/**
 *
 * @author Eliane
 */

@ManagedBean

public class VeiculoBean {
    
    private Veiculo veiculo = new Veiculo();
    //private VeiculoDAO veiculodao = new VeiculoDAO();
    
    public Veiculo getVeiculo(){
        return veiculo; 
    }  
    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
    
    public String efetuaLogin() throws SQLException{
        boolean loginvalido = veiculo.validar(veiculo);
        if (loginvalido){
            return "/paginas/index?faces-redirect=true";
        }
        else{
            veiculo = new Veiculo();
            return "login";
        }
    }
}
