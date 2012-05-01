/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.medralservicosrio.modelo;

/**
 * Classe de Persist�ncia Estoque
 * 
 * @author 		Luis Gabriel Nascimento Simas
 * @category 	Classe Persistente
 * @since		12/04/2012	
 * 
 * Padr�o de Classe JavaBean contendo:
 * Serializa��o
 * Construtor Vazio
 * Construtor com par�metro
 * Getters
 * Setters
 * Override de M�todos toString, hashCode e equals
 * 
 *  Este padr�o serve para qualquer projeto Java at� EJB
 */

public class Veiculo {
    private Integer idVeiculo;
    private String placa;
    private String marca;
    private String modelo;
    private String setor; //TODO: Criar uma Tabela Prim�ria para receber o c�digo dos setores
    private Integer matriculaFuncionario;
    private boolean status;
    
    public Veiculo(){
    	
    }
    
    
}
