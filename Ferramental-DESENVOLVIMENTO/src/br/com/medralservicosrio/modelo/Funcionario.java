package br.com.medralservicosrio.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Classe de Persistência Funcionário
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
@Entity(name="Funcionario")
@Table(name="funcionarios")
/*@NamedQueries(
		
		{
			@NamedQuery(name="funcionario.listar",query="SELECT f FROM Funcionario f"),
			@NamedQuery(name="funcionario.listarPorMatricula",
					query="SELECT f FROM Funcionario f WHERE f.matricula = :p1"),
			@NamedQuery(name="funcionario.listarPorId",
					query="SELECT f FROM Funcionario f WHERE f.id = :p1")
		}
		)*/
public class Funcionario implements Serializable{
    
	private static final long serialVersionUID = 2940772491105983478L;

	
	/*@Column(name="id")
    private Integer id;*/
	
	@Id
	/*@GeneratedValue(strategy=GenerationType.AUTO)*/
	@Column(name="chapa", nullable=false, unique=true)
    private Integer matricula;
	
	@Column(name="nome", length=100, nullable=false)
    private String nome;
	
	@Column(name="setor", length=100, nullable = false)
    private String setor;
	
	@Column(name="funcao", length=100,nullable=false)
    private String funcao;
	
	@Column(name="custo",length=100,nullable=false)
    private String custo;
    
	/**
	 * @param id
	 * @param matricula
	 * @param nome
	 * @param setor
	 * @param funcao
	 * @param custo
	 */
	public Funcionario(Integer matricula, String nome,
			String setor, String funcao, String custo) {
		
		this.matricula = matricula;
		this.nome = nome;
		this.setor = setor;
		this.funcao = funcao;
		this.custo = custo;
	}
	
	public Funcionario() {
	}


	/**
	 * @return the id
	 */
	/*public Integer getId() {
		return id;
	}


	*//**
	 * @param id the id to set
	 *//*
	public void setId(Integer id) {
		this.id = id;
	}
*/

	/**
	 * @return the matricula
	 */
	public Integer getMatricula() {
		return matricula;
	}


	/**
	 * @param matricula the matricula to set
	 */
	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
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
	 * @return the setor
	 */
	public String getSetor() {
		return setor;
	}

	/**
	 * @param setor the setor to set
	 */
	public void setSetor(String setor) {
		this.setor = setor;
	}


	/**
	 * @return the funcao
	 */
	public String getFuncao() {
		return funcao;
	}


	/**
	 * @param funcao the funcao to set
	 */
	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}


	/**
	 * @return the custo
	 */
	public String getCusto() {
		return custo;
	}


	/**
	 * @param custo the custo to set
	 */
	public void setCusto(String custo) {
		this.custo = custo;
	}
	
	/**
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Usuario){
			Usuario usuario = (Usuario) obj;
			if (usuario.getIdUsuario() != null){
				return usuario.getIdUsuario().equals(this.id);
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
		
		return this.id + ", " + this.nome;
	}

	**/
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Funcionario){
			Funcionario funcionario = (Funcionario) obj;
			if (funcionario.getMatricula() != null){
				return funcionario.getMatricula().equals(this.matricula);
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		return this.matricula != null ? this.matricula.hashCode() : 0;
	}

	@Override
	public String toString() {
		return this.matricula + ", " + this.nome;
	}
}
