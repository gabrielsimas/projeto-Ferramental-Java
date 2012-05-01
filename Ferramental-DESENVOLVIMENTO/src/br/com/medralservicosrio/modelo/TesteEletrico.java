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
 * Classe de Persistência Teste Elétrico
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

@Entity(name="TesteEletrico")
@Table(name="teste_eletrico")
public class TesteEletrico implements Serializable{

	private static final long serialVersionUID = -4871170313114842828L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id",unique=true)
	private Integer	id;
	
	@Column(name="idproduto")
	private Integer	idProduto;
	
	@Column(name="idrastre")
	private Integer	idRastreamento;
	
	@Temporal(TemporalType.DATE)
	@Column(name="data_ent")
	private Date	dataEntrada;
	
	public TesteEletrico() {
		
	}

	public TesteEletrico(Integer id, Integer idProduto, Integer idRastreamento,
			Date dataEntrada) {
		this.id = id;
		this.idProduto = idProduto;
		this.idRastreamento = idRastreamento;
		this.dataEntrada = dataEntrada;
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

	public Integer getIdRastreamento() {
		return idRastreamento;
	}

	public void setIdRastreamento(Integer idRastreamento) {
		this.idRastreamento = idRastreamento;
	}

	public Date getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	@Override
	public boolean equals(Object obj) {
		
		if (obj instanceof TesteEletrico){
			TesteEletrico testeEletrico = (TesteEletrico) obj;
			
			if (testeEletrico.getId() != null){
				return this.id.equals(this.id);
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
		
		return this.id + ", " + this.idProduto + ", " + this.idRastreamento;  
	}
}
