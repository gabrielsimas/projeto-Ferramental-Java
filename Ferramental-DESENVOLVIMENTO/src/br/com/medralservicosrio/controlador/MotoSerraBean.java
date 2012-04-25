/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.medralservicosrio.controlador;

import java.sql.SQLException;

import javax.faces.bean.ManagedBean;

import br.com.medralservicosrio.modelo.MotoSerra;


/**
 *
 * @author Eliane
 */

@ManagedBean
public class MotoSerraBean {
    
    private MotoSerra motoserra = new MotoSerra();
    //private MotoSerraDAO motoserradao = new MotoSerraDAO();
    
    public MotoSerra getMotoSerra(){
        return motoserra; 
    }  
    public void setMotoSerra(MotoSerra funcionario) {
        this.motoserra = funcionario;
    }
    
    public String efetuaLogin() throws SQLException{
        boolean loginvalido = motoserra.validar(motoserra);
        if (loginvalido){
            return "/paginas/index?faces-redirect=true";
        }
        else{
            motoserra = new MotoSerra();
            return "login";
        }
    }
}
