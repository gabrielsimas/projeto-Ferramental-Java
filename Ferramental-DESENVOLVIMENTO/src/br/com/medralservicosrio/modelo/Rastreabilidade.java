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
 * Classe de Persistência Rastreabilidade
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
    @Column(name="data",nullable=false)
	private Date data;

	public Rastreabilidade() {
		// TODO Auto-generated constructor stub
	}

	public Rastreabilidade(Integer id, Integer idProduto,
			Integer idRastreabilidade, Date data) {
		this.id = id;
		this.idProduto = idProduto;
		this.idRastreabilidade = idRastreabilidade;
		this.data = data;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}

	public Integer getIdRastreabilidade() {
		return idRastreabilidade;
	}

	public void setIdRastreabilidade(Integer idRastreabilidade) {
		this.idRastreabilidade = idRastreabilidade;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@Override
	public boolean equals(Object obj) {
		
		if (obj instanceof Rastreabilidade){
			Rastreabilidade rastreabilidade = (Rastreabilidade) obj;
			
			if (rastreabilidade.getId() != null){
				return rastreabilidade.getId().equals(this.id);
			}
		}
		
		return false;
	}

	@Override
	public int hashCode() {
		
		return super.hashCode();
	}

	@Override
	public String toString() {
		
		return super.toString();
	}
}
