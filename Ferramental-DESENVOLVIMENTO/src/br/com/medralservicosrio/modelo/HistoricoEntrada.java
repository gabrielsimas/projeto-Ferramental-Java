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
 * Classe de Persistência HistoricoEntrada
 * 
 * Esta Classe registra os Logs de Entrada no Estoque
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
@Entity(name="HistoricoEntrada")
@Table(name="historico_de_entradas")
public class HistoricoEntrada implements Serializable{

	private static final long serialVersionUID = 4402590072074100345L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id",unique=true,nullable=false)
	private Integer id;
	
	@Column(name="idproduto",nullable=false)
	private Integer idProduto;
	
	@Column(name="chapa",nullable=false)
	private Integer chapa;
	
	@Column(name="idrastre",nullable=false)
	private Integer idRastreamento;
	
	@Column(name="idforn",nullable=false)
	private Integer idFornecedor;
	
	@Column(name="num_nota",nullable=false)
	private Integer numeroNotaFiscal;
	
	@Temporal(TemporalType.DATE)
	@Column(name="data",nullable=false)
	private Date data;
	
	@Column(name="qntd",nullable=false)
	private Integer quantidade;
	
	@Column(name="motivo_ent",nullable=false,length=100)
	private String motivoEntrada;
	
	public HistoricoEntrada() {
		// TODO Auto-generated constructor stub
	}

	public HistoricoEntrada(Integer id, Integer idProduto, Integer chapa,
			Integer idRastreamento, Integer idFornecedor,
			Integer numeroNotaFiscal, Date data, Integer quantidade,
			String motivoEntrada) {
		this.id = id;
		this.idProduto = idProduto;
		this.chapa = chapa;
		this.idRastreamento = idRastreamento;
		this.idFornecedor = idFornecedor;
		this.numeroNotaFiscal = numeroNotaFiscal;
		this.data = data;
		this.quantidade = quantidade;
		this.motivoEntrada = motivoEntrada;
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

	public Integer getChapa() {
		return chapa;
	}

	public void setChapa(Integer chapa) {
		this.chapa = chapa;
	}

	public Integer getIdRastreamento() {
		return idRastreamento;
	}

	public void setIdRastreamento(Integer idRastreamento) {
		this.idRastreamento = idRastreamento;
	}

	public Integer getIdFornecedor() {
		return idFornecedor;
	}

	public void setIdFornecedor(Integer idFornecedor) {
		this.idFornecedor = idFornecedor;
	}

	public Integer getNumeroNotaFiscal() {
		return numeroNotaFiscal;
	}

	public void setNumeroNotaFiscal(Integer numeroNotaFiscal) {
		this.numeroNotaFiscal = numeroNotaFiscal;
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

	public String getMotivoEntrada() {
		return motivoEntrada;
	}

	public void setMotivoEntrada(String motivoEntrada) {
		this.motivoEntrada = motivoEntrada;
	}

	@Override
	public boolean equals(Object obj) {
		
		if (obj instanceof HistoricoEntrada){
			HistoricoEntrada historicoEntrada = (HistoricoEntrada) obj;
			
			if (historicoEntrada.getId() != null){
				return historicoEntrada.getId().equals(this.id);
			}
		}
		
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return this.id != null ? this.id.hashCode() : 0;
	}

	@Override
	public String toString() {
		return this.id + ", " + this.chapa + ", " + this.idRastreamento + ", " + this.numeroNotaFiscal;
	}
}
