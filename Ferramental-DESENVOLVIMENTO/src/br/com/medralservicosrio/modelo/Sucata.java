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
 * Classe de Persistência Sucata
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

@Entity(name="Sucata")
@Table(name="sucata")
public class Sucata implements Serializable{
	
	private static final long serialVersionUID = 1343797148568699524L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id",unique=true)
	private Integer 	id;
	
	@Column(name="idproduto")
	private Integer 	idProduto;
	
	@Column(name="idrastre")
	private Integer 	idRastreabilidade;
	
	@Column(name="chapa")
	private Integer 	matriculaFuncionario;
	
	@Temporal(TemporalType.DATE)
	@Column(name="data_exclu")
	private Date		dataExclusao;
	
	@Column(name="obs")
	private String 		observacao;
	
	public Sucata() {
	
	}

	public Sucata(Integer id, Integer idProduto, Integer idRastreabilidade,
			Integer matriculaFuncionario, Date dataExclusao, String observacao) {
		this.id = id;
		this.idProduto = idProduto;
		this.idRastreabilidade = idRastreabilidade;
		this.matriculaFuncionario = matriculaFuncionario;
		this.dataExclusao = dataExclusao;
		this.observacao = observacao;
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

	public Integer getMatriculaFuncionario() {
		return matriculaFuncionario;
	}

	public void setMatriculaFuncionario(Integer matriculaFuncionario) {
		this.matriculaFuncionario = matriculaFuncionario;
	}

	public Date getDataExclusao() {
		return dataExclusao;
	}

	public void setDataExclusao(Date dataExclusao) {
		this.dataExclusao = dataExclusao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	@Override
	public boolean equals(Object obj) {
		
		if (obj instanceof Sucata){
			Sucata sucata = (Sucata) obj;
			
			if (sucata.getId() != null){
				return sucata.getId().equals(this.id);
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
		
		return this.id + ", " + this.idProduto + ", " + this.idRastreabilidade;
		
	}
	
}
