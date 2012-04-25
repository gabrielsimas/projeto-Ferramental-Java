/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.medralservicosrio.modelo;

import java.util.Date;

/**
 *
 * @author Eliane
 */

public class Rastreabilidade {
    private int idRastreabilidade;
    private int idProduto;
    private Date data; 
    
    public Rastreabilidade(){
        
    }
    
    public Rastreabilidade(int idRastreabilidade, int idProduto, Date data) {
        this.idRastreabilidade = idRastreabilidade;
        this.idProduto = idProduto;
        this.data = data;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getIdRastreabilidade() {
        return idRastreabilidade;
    }

    public void setIdRastreabilidade(int idRastreabilidade) {
        this.idRastreabilidade = idRastreabilidade;
    }

    public boolean validar(Rastreabilidade rastreabilidade) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
 
}
