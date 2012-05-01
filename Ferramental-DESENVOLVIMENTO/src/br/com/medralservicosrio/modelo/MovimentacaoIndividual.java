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
 * Classe de Persistência para Movimentação Individual
 * 
 * @author 		Luis Gabriel Nascimento Simas
 * @category 	Classe Persistente
 * @since		01/05/2012	
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

@Entity(name="MovimentacaoIndividual")
@Table(name="individual")
public class MovimentacaoIndividual implements Serializable{
	
	private static final long serialVersionUID = 8120093169295899838L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id",unique=true)
	private Integer id;
	
	@Column(name="chapa")
	private Integer matrFuncionario;
	
	@Column(name="idproduto")
	private Integer idProduto;
	
	@Column(name="rastreabilidade")
	private Integer idRastreabilidade;
	
	@Temporal(TemporalType.DATE)
	@Column(name="data")
	private Date	data;
	
	@Column(name="qntd")
	private Integer quantidade;
	
	@Column(name="status",length=30)
	private String	status;
	
	@Column(name="tipo_req",length=30)
	private String 	tipo_req;
	
	public MovimentacaoIndividual() {
		
	}
			
	public MovimentacaoIndividual(Integer id, Integer matrFuncionario,
			Integer idProduto, Integer idRastreabilidade, Date data,
			Integer quantidade, String status, String tipo_req) {
		this.id = id;
		this.matrFuncionario = matrFuncionario;
		this.idProduto = idProduto;
		this.idRastreabilidade = idRastreabilidade;
		this.data = data;
		this.quantidade = quantidade;
		this.status = status;
		this.tipo_req = tipo_req;
	}
	

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}



	public Integer getMatrFuncionario() {
		return matrFuncionario;
	}



	public void setMatrFuncionario(Integer matrFuncionario) {
		this.matrFuncionario = matrFuncionario;
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



	public Integer getQuantidade() {
		return quantidade;
	}



	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public String getTipo_req() {
		return tipo_req;
	}



	public void setTipo_req(String tipo_req) {
		this.tipo_req = tipo_req;
	}



	@Override
	public boolean equals(Object obj) {
		if (obj instanceof MovimentacaoIndividual){
			MovimentacaoIndividual movimentacaoIndividual = (MovimentacaoIndividual) obj;
			
			if(movimentacaoIndividual.getId() != null){
				return movimentacaoIndividual.getId().equals(this.id);
			}
			
		}
		
		return false;
		
	}

	@Override
	public int hashCode() {
		
		return this.id != null ? this.id.hashCode(): 0;
	}

	@Override
	public String toString() {
		
		return this.id + ", " + this.matrFuncionario + ", " + this.idRastreabilidade;
	}
}
