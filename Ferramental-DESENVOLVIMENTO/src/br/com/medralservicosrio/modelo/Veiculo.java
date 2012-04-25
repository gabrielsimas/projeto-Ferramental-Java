/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.medralservicosrio.modelo;

/**
 *
 * @author Eliane
 */

public class Veiculo {
    private int idVeiculo;
    private String placa;
    private String marca;
    private String modelo;
    private String setor;
    private int matrEncarregado;
    private boolean status;
    
    public Veiculo(){
        
    }

    public Veiculo(int idVeiculo, String placa, String marca, String modelo, String setor, int matrEncarregado, boolean status) {
        this.idVeiculo = idVeiculo;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.setor = setor;
        this.matrEncarregado = matrEncarregado;
        this.status = status;
    }

    public int getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(int idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getMatrEncarregado() {
        return matrEncarregado;
    }

    public void setMatrEncarregado(int matrEncarregado) {
        this.matrEncarregado = matrEncarregado;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean validar(Veiculo veiculo) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    
    
    
    
    
}
