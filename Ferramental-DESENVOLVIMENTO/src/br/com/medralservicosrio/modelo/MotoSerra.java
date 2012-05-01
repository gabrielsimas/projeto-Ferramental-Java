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
 * Classe de Persistência MotoSerra
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
@Entity(name="Motoserra")
@Table(name="registro_moto_serra")
public class MotoSerra implements Serializable{

	private static final long serialVersionUID = 5848936204570582780L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id",nullable=false,unique=true)
    private Integer id;
	
	@Column(name="idProduto",nullable=false)
    private Integer idProduto;
	
	@Column(name="gru",length=60,nullable=false)
    private String gru;
	
	@Column(name="serie",length=20,nullable=false)
    private Integer serie;
	
	@Column(name="nota",length=60,nullable=false)
    private String nota;
	
	@Temporal(TemporalType.DATE)
	@Column(name="data")
    private Date data;
	
	@Column(name="status")
    private String status;

	public MotoSerra() {
		
	}

	public MotoSerra(Integer id, Integer idProduto, String gru, Integer serie,
			String nota, Date data, String status) {
		this.id = id;
		this.idProduto = idProduto;
		this.gru = gru;
		this.serie = serie;
		this.nota = nota;
		this.data = data;
		this.status = status;
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

	public String getGru() {
		return gru;
	}

	public void setGru(String gru) {
		this.gru = gru;
	}

	public Integer getSerie() {
		return serie;
	}

	public void setSerie(Integer serie) {
		this.serie = serie;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public boolean equals(Object obj) {
		
		if (obj instanceof MotoSerra){
			MotoSerra motoserra = (MotoSerra) obj;
			if (motoserra.getId() != null){
				return motoserra.getId().equals(this.id);
			}
		}
	
		
		return false;
	}

	@Override
	public int hashCode() {
		
		return this.id != null ? this.id.hashCode() : 0;
	}

	@Override
	public String toString() {
		
		return this.id + ", " + this.gru;
	}
	
}