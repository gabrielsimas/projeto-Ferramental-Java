package br.com.medralservicosrio.modelo;

import java.util.Date;

/**
 *
 * @author Eliane
 */

public class MotoSerra {
    private int idMotoSerra;
    private int idProduto;
    private String gru;
    private int serie;
    private int nota;
    private Date data;
    private boolean status;
    
    public MotoSerra(){
        
    }

    public MotoSerra(int idMotoSerra, int idProduto, String gru, int serie, int nota, Date data, boolean status) {
        this.idMotoSerra = idMotoSerra;
        this.idProduto = idProduto;
        this.gru = gru;
        this.serie = serie;
        this.nota = nota;
        this.data = data;
        this.status = status;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getGru() {
        return gru;
    }

    public void setGru(String gru) {
        this.gru = gru;
    }

    public int getIdMotoSerra() {
        return idMotoSerra;
    }

    public void setIdMotoSerra(int idMotoSerra) {
        this.idMotoSerra = idMotoSerra;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public int getSerie() {
        return serie;
    }

    public void setSerie(int serie) {
        this.serie = serie;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean validar(MotoSerra motoserra) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    
    
    
    
    
}
