package br.com.medralservicosrio.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Classe de Persistência Fornecedor
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
@Entity
@Table(name="fornecedor")
public class Fornecedor implements Serializable{
	
	private static final long serialVersionUID = 734997863351096959L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id",unique=true,nullable=false)
    private Integer id;
	
	@Column(name="nome",length=100,nullable=false)
    private String nome;
	
	@Column(name="cnpj", length=30,nullable=false)
    private String cnpj;
	
	@Column(name="email", length=100,nullable=false)
    private String email;
	
	@Column(name="telefone",length=100,nullable=false)
    private String telefone;
	
	@Column(name="endereco",length=100,nullable=false)
    private String endereco;

	/**
	 * @param id
	 * @param nome
	 * @param cnpj
	 * @param email
	 * @param telefone
	 * @param endereco
	 */
	public Fornecedor(Integer id, String nome, String cnpj, String email,
			String telefone, String endereco) {
		this.id = id;
		this.nome = nome;
		this.cnpj = cnpj;
		this.email = email;
		this.telefone = telefone;
		this.endereco = endereco;
	}

	public Fornecedor() {
		
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the cnpj
	 */
	public String getCnpj() {
		return cnpj;
	}

	/**
	 * @param cnpj the cnpj to set
	 */
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the telefone
	 */
	public String getTelefone() {
		return telefone;
	}

	/**
	 * @param telefone the telefone to set
	 */
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	/**
	 * @return the endereco
	 */
	public String getEndereco() {
		return endereco;
	}

	/**
	 * @param endereco the endereco to set
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Fornecedor){
			Fornecedor fornecedor = (Fornecedor) obj;
			if (fornecedor.getId() != null){
				return fornecedor.getId().equals(this.id);
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
		return this.id + ", " + this.cnpj + ", " + this.nome;
	}        
}
