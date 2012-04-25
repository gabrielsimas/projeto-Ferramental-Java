/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.medralservicosrio.modelo;

/**
 *
 * @author Eliane
 */
public class Produto {
    private int idProduto;
    private String produto;
    private Double valor;
    private int durabilidade;
    
    public Produto(){
        
    }

    public Produto(int idProduto, String produto, Double valor, int durabilidade) {
        this.idProduto = idProduto;
        this.produto = produto;
        this.valor = valor;
        this.durabilidade = durabilidade;
    }

    public int getDurabilidade() {
        return durabilidade;
    }

    public void setDurabilidade(int durabilidade) {
        this.durabilidade = durabilidade;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public boolean validar(Produto produto) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
   
}
