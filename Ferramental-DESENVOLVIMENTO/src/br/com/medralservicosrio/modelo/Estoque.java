package br.com.medralservicosrio.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 
 * @author Gabriel
 *
 */
@Entity(name="Estoque")
@Table(name="estoque")
@NamedQueries({
	@NamedQuery(name="",query="")
})
public class Estoque implements Serializable{
	
	private static final long serialVersionUID = -4350667559400826807L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Integer idEstoque;
	
	/*@Column(name="idproduto",nullable=false)
	private Integer idProduto;*/
	
	@Column(name="qntd",nullable=false)
	private Integer quantidade;
	
	@Column(name="minimo",nullable=false)
	private Integer minimo;
	
	@OneToMany(mappedBy="idProduto")
	List<Produto> produtos;
	
		
	public Estoque() {
		
	}
	
	public Estoque(Integer id,Integer quantidade,
			Integer minimo) {
		this.idEstoque = id;
		//this.idProduto = idProduto;
		this.quantidade = quantidade;
		this.minimo = minimo;
	}
	
	
	public Integer getId() {
		return idEstoque;
	}
	public void setId(Integer id) {
		this.idEstoque = id;
	}
	
	/*public Integer getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}*/
	
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public Integer getMinimo() {
		return minimo;
	}
	public void setMinimo(Integer minimo) {
		this.minimo = minimo;
	}

	@Override
	public boolean equals(Object obj) {
		
		if (obj instanceof Estoque){
			Estoque estoque = (Estoque) obj;
			
			if (estoque.getId() != null){
				return estoque.getId().equals(this.idEstoque);
			}
		}
		
		return false;
	}

	@Override
	public int hashCode() {

		return this.idEstoque != null ? this.idEstoque.hashCode() : 0; 
	}

	@Override
	public String toString() {
		
		return this.idEstoque.toString();
	}
}
