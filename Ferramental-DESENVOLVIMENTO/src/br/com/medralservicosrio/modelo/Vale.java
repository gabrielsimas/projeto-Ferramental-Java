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
 * Classe de Persistência Vale
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

@Entity(name="Vale")
@Table(name="vales")
public class Vale implements Serializable{
	
	private static final long serialVersionUID = -747855394142120793L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id",unique=true)
	private Integer id;
	
	@Column(name="chapa")
	private Integer matriculaFuncionario;
	
	@Column(name="idproduto")
	private Integer idProduto;
	
	@Column(name="rastre")
	private Integer idRastreamento;
	
	@Temporal(TemporalType.DATE)
	@Column(name="data")
	private Date 	data;
	
	@Column(name="uso")
	private Integer uso; //O tempo de uso do produto
	
	@Column(name="valor_vale")
	private Double 	valorVale;
	
	@Column(name="dividir")
	private Integer parcelaVale;
	
	@Column(name="variante",length=30)
	private String	variante; //PAGO, ABONADO ou NULO
	
	@Column(name="obs")
	private String  observacao;
	
	public Vale() {
		
	}

	public Vale(Integer id, Integer matriculaFuncionario, Integer idProduto,
			Integer idRastreamento, Date data, Integer uso, Double valorVale,
			Integer parcelaVale, String variante, String observacao) {
		this.id = id;
		this.matriculaFuncionario = matriculaFuncionario;
		this.idProduto = idProduto;
		this.idRastreamento = idRastreamento;
		this.data = data;
		this.uso = uso;
		this.valorVale = valorVale;
		this.parcelaVale = parcelaVale;
		this.variante = variante;
		this.observacao = observacao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMatriculaFuncionario() {
		return matriculaFuncionario;
	}

	public void setMatriculaFuncionario(Integer matriculaFuncionario) {
		this.matriculaFuncionario = matriculaFuncionario;
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

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Integer getUso() {
		return uso;
	}

	public void setUso(Integer uso) {
		this.uso = uso;
	}

	public Double getValorVale() {
		return valorVale;
	}

	public void setValorVale(Double valorVale) {
		this.valorVale = valorVale;
	}

	public Integer getParcelaVale() {
		return parcelaVale;
	}

	public void setParcelaVale(Integer parcelaVale) {
		this.parcelaVale = parcelaVale;
	}

	public String getVariante() {
		return variante;
	}

	public void setVariante(String variante) {
		this.variante = variante;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	@Override
	public boolean equals(Object obj) {
		
		if (obj instanceof Vale){
			Vale vale = (Vale) obj;
			
			if (vale.getId()!= null){
				return vale.getId().equals(this.id);
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
		
		return this.id + ", " + this.matriculaFuncionario;
	}
}
