package br.com.medralservicosrio.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Classe de Persist�ncia para Entradas no Estoque via Nota Fiscal
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

@Entity(name="Entrada")
@Table(name="entradas_via_nota")
/*
@NamedQueries(
		{
			@NamedQuery(name="entrada.listar",query="SELECT e FROM Entrada e"),
			@NamedQuery(name="entrada.calculaLinha",query="SELECT (e.quantidade * e.valor) FROM Entrada e"),
		}
		)
*/
public class EntradaViaNota implements Serializable{
	
	private static final long serialVersionUID = 4737383045805984990L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id",nullable=false,unique=true)
	private Integer id;
	
	@Column(name="idforn",nullable=false)
	private Integer idFornecedor; 		//Sumir� com o tempo
	
	@Column(name="nota",nullable=false)	//Sumir� com o tempo
	private Integer idNotaFiscal;
	
	@Temporal(TemporalType.DATE)
	@Column(name="data",nullable=false)
	private Date	data;
	
	@Column(name="quantidade",nullable=false)
	private Integer quantidade;
	
	@Column(name="valor",nullable=false)
	private Double	valor;
	
	private Double 	subtotal; 			//N�o � campo persistente
	
	/* Para o futuro
	private NotaFiscal notaFiscal;	
	 */
	
	public EntradaViaNota() {
		
	}

	public EntradaViaNota(Integer id, Integer idFornecedor,
			Integer idNotaFiscal, Date data, Integer quantidade, Double valor) {
		this.id = id;
		this.idFornecedor = idFornecedor;
		this.idNotaFiscal = idNotaFiscal;
		this.data = data;
		this.quantidade = quantidade;
		this.valor = valor;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdFornecedor() {
		return idFornecedor;
	}

	public void setIdFornecedor(Integer idFornecedor) {
		this.idFornecedor = idFornecedor;
	}

	public Integer getIdNotaFiscal() {
		return idNotaFiscal;
	}

	public void setIdNotaFiscal(Integer idNotaFiscal) {
		this.idNotaFiscal = idNotaFiscal;
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

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = getValor() * getQuantidade();
	}

	@Override
	public boolean equals(Object obj) {
		
		if (obj instanceof EntradaViaNota){
			EntradaViaNota entrada = (EntradaViaNota) obj; 
			if (entrada.getId() != null){
				return entrada.getId().equals(this.id);
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
		
		return this.id + ", " + this.idNotaFiscal;
	}
}
