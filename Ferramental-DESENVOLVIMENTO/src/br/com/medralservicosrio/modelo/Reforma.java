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
 * Classe de Persistência Reforma
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

@Entity(name="Reforma")
@Table(name="reforma")
public class Reforma  implements Serializable{
	
	private static final long serialVersionUID = 2932443978352978595L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id",unique=true,nullable=true)
	private Integer id;
	
	@Column(name="idproduto",nullable=false)
	private Integer idProduto;
	
	@Column(name="idrastre",nullable=true)
	private Integer idRatreamento;
	
	@Column(name="chapa",nullable=false)
	private Integer matriculaFuncionario;
	
	@Temporal(TemporalType.DATE)
	@Column(name="data_ent")
	private Date 	dataEntrada;
	
	@Column(name="comentarios",nullable=true)
	private String	comentario;
	
	@Column(name="custo_reforma",nullable=false)
	private Double	custo;
	
	@Column(name="fornecedor",nullable=false)
	private String 	fornecedor;
	
	public Reforma() {
		
	}


	public Reforma(Integer id, Integer idProduto, Integer idRatreamento,
			Integer matriculaFuncionario, Date dataEntrada, String comentario,
			Double custo, String fornecedor) {
		this.id = id;
		this.idProduto = idProduto;
		this.idRatreamento = idRatreamento;
		this.matriculaFuncionario = matriculaFuncionario;
		this.dataEntrada = dataEntrada;
		this.comentario = comentario;
		this.custo = custo;
		this.fornecedor = fornecedor;
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


	public Integer getIdRatreamento() {
		return idRatreamento;
	}


	public void setIdRatreamento(Integer idRatreamento) {
		this.idRatreamento = idRatreamento;
	}


	public Integer getMatriculaFuncionario() {
		return matriculaFuncionario;
	}


	public void setMatriculaFuncionario(Integer matriculaFuncionario) {
		this.matriculaFuncionario = matriculaFuncionario;
	}


	public Date getDataEntrada() {
		return dataEntrada;
	}


	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}


	public String getComentario() {
		return comentario;
	}


	public void setComentario(String comentario) {
		this.comentario = comentario;
	}


	public Double getCusto() {
		return custo;
	}


	public void setCusto(Double custo) {
		this.custo = custo;
	}


	public String getFornecedor() {
		return fornecedor;
	}


	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}

	@Override
	public boolean equals(Object obj) {
		
		if (obj instanceof Reforma){
			Reforma reforma = (Reforma) obj;
			if (reforma.getId() != null){
				return reforma.getId().equals(this.id);
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
		
		return this.id + ", " + this.idProduto + ", " + this.idRatreamento;
		
	}
	
}
