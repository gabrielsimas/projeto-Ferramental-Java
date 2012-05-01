/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.medralservicosrio.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Classe de Persistência Produto
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
@Entity(name="Produto")
@Table(name="produtos")
/*@NamedQueries(
		{
			@NamedQuery(name="produto.listar",query="FROM Produto p"),
			@NamedQuery(name="produto.listarPorId",query="SELECT p FROM Produto p WHERE idProduto = :p1")
		}
)*/

public class Produto {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idproduto",unique=true,nullable=false)
    private Integer idProduto;
	
	@Column(name="produto",length=63,nullable=false)
    private String produto;
	
	@Column(name="valor",nullable=false)
    private Double valor;
	
	@Column(name="durabilidade",nullable=false)
    private Integer durabilidade;
	
	/*
	@ManyToOne
	@JoinColumn(name="idEstoque")
	Estoque estoque;
	*/
    
    public Produto(){
        
    }

	public Produto(Integer idProduto, String produto, Double valor,
			Integer durabilidade) {
		this.idProduto = idProduto;
		this.produto = produto;
		this.valor = valor;
		this.durabilidade = durabilidade;
	}


	public Integer getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Integer getDurabilidade() {
		return durabilidade;
	}

	public void setDurabilidade(Integer durabilidade) {
		this.durabilidade = durabilidade;
	}

	@Override
	public boolean equals(Object obj) {
		
		if (obj instanceof Produto){
			Produto produto = (Produto) obj;
			
			if (produto.getIdProduto() != null){
				return produto.getIdProduto().equals(this.idProduto);
			}
		}
		
		return false;
	}

	@Override
	public int hashCode() {
		return this.idProduto != null ? this.idProduto.hashCode(): 0;
	}

	@Override
	public String toString() {
		return this.idProduto + ", " + this.produto + ", " + this.durabilidade;
	}
}
