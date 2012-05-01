package br.com.medralservicosrio.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Classe de Persist�ncia Rastreabilidade
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
@Entity(name="Ratreabilidade")
@Table(name="rastreabilidade")
public class Rastreabilidade implements Serializable{
    
	private static final long serialVersionUID = 7672780524025769055L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id",unique=true,nullable=true)
	private Integer id;
	
	@Column(name="idproduto")
    private Integer idProduto;
	
	@Column(name="idrastre")
	private Integer idRastreabilidade;
    
    @Temporal(TemporalType.DATE)
	private Date data;

	

}
