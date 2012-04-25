package br.com.medralservicosrio.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Classe de Persistência Usuário
 * 
 * @author 		Luis Gabriel Nascimento Simas
 * @category 	Classe Persistente
 * @since		11/04/2012	
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
@Table(name="usuarios")
@NamedQueries(
		{
			@NamedQuery(name="usuario.listar",query="SELECT u FROM Usuario AS u"),
			@NamedQuery(name="usuario.listarPorId",query="SELECT u FROM Usuario AS u " +
					" WHERE id = :p1"),
			@NamedQuery(name="usuario.autenticar",
						query="SELECT u FROM Usuario AS u WHERE id = :p1 AND senha = :p2")
		})
public class Usuario implements Serializable{
	
	private static final long serialVersionUID = 186592017755553602L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="iduser")
    private Integer id;
	
	@Column(name="ativacao", nullable=true)
    private Integer idAtivicao;
	
	@Column(name="nome", nullable=false, length=100)
    private String nome;
	
	@Column(name="login",length=20,nullable=true)
    private String login;
	
	@Column(name="senha",length=20,nullable=true)
    private String senha;
	
	@Column(name="email",length=20,nullable=true)
    private String email;
	
	@Column(name="nivel",nullable=true)
    private Integer nivel;
	
	@Column(name="ativacao",nullable=true)
    private Integer ativacao;

	/**
	 * Construtor com parâmetros
	 * 
	 * @param idUsuario
	 * @param idAtivicao
	 * @param nome
	 * @param login
	 * @param senha
	 * @param email
	 * @param nivel
	 * @param ativacao
	 */
	public Usuario(Integer idUsuario, Integer idAtivicao, String nome,
			String login, String senha, String email, Integer nivel,
			Integer ativacao) {
		this.id = idUsuario;
		this.idAtivicao = idAtivicao;
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.email = email;
		this.nivel = nivel;
		this.ativacao = ativacao;
	}

	/**
	 *	Construtor vazio 
	 */
	public Usuario() {
		
	}

	/**
	 * @return the idUsuario
	 */
	public Integer getIdUsuario() {
		return id;
	}

	/**
	 * @param idUsuario the idUsuario to set
	 */
	public void setIdUsuario(Integer idUsuario) {
		this.id = idUsuario;
	}

	/**
	 * @return the idAtivicao
	 */
	public Integer getIdAtivicao() {
		return idAtivicao;
	}

	/**
	 * @param idAtivicao the idAtivicao to set
	 */
	public void setIdAtivicao(Integer idAtivicao) {
		this.idAtivicao = idAtivicao;
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
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return the senha
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * @param senha the senha to set
	 */
	public void setSenha(String senha) {
		this.senha = senha;
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
	 * @return the nivel
	 */
	public Integer getNivel() {
		return nivel;
	}

	/**
	 * @param nivel the nivel to set
	 */
	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}

	/**
	 * @return the ativacao
	 */
	public Integer getAtivacao() {
		return ativacao;
	}

	/**
	 * @param ativacao the ativacao to set
	 */
	public void setAtivacao(Integer ativacao) {
		this.ativacao = ativacao;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		
		return this.id != null ? this.id.hashCode() : 0;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		
		return this.id + ", " + this.nome;
	}
}
