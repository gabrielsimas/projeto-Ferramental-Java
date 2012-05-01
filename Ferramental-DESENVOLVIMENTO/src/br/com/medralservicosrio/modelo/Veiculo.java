/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.medralservicosrio.modelo;

/**
 * Classe de Persistência Estoque
 * 
 * @author 		Luis Gabriel Nascimento Simas
 * @category 	Classe Persistente
 * @since		12/04/2012	
 * 
 * Padrão de Classe JavaBean contendo:
 * Serialização
 * Construtor Vazio
 * Construtor com parâmetro
 * Getters
 * Setters
 * Override de Métodos toString, hashCode e equals
 * 
 *  Este padrão serve para qualquer projeto Java até EJB
 */

public class Veiculo {
    private Integer idVeiculo;
    private String placa;
    private String marca;
    private String modelo;
    private String setor; //TODO: Criar uma Tabela Primária para receber o código dos setores
    private Integer matriculaFuncionario;
    private boolean status;
    
    public Veiculo(){
    	
    }
    
    
}
