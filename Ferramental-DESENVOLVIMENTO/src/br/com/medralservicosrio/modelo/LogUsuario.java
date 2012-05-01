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
 * Classe de Persistência Log de Usuários
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
@Entity(name="LogUsuario")
@Table(name="logusers")
public class LogUsuario implements Serializable{
	
	private static final long serialVersionUID = -4537138438196941941L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id",unique=true,nullable=false)
	private Integer id;
	
	@Column(name="iduser",nullable=false)
	private Integer idUsuario;
	
	@Column(name="iprastre",length=20,nullable=false)
	private String	ip;
	
	@Column(name="session",length=20,nullable=false)
	private String	sessao;
	
	@Temporal(TemporalType.TIME)
	@Column(name="datain",nullable=false)
	private Date	dataEntrada;
	
	@Temporal(TemporalType.TIME)
	@Column(name="dataativ",nullable=false)
	private Date	dataAtividade;
	
	public LogUsuario() {
		
	}
	
	public LogUsuario(Integer id, Integer idUsuario, String ip, String sessao,
			Date dataEntrada, Date dataAtividade) {
		this.id = id;
		this.idUsuario = idUsuario;
		this.ip = ip;
		this.sessao = sessao;
		this.dataEntrada = dataEntrada;
		this.dataAtividade = dataAtividade;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getSessao() {
		return sessao;
	}

	public void setSessao(String sessao) {
		this.sessao = sessao;
	}

	public Date getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public Date getDataAtividade() {
		return dataAtividade;
	}

	public void setDataAtividade(Date dataAtividade) {
		this.dataAtividade = dataAtividade;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof LogUsuario){
			LogUsuario logUsuario = (LogUsuario) obj;
			
			if (logUsuario.getId() != null){
				return logUsuario.getId().equals(this.id);
			}
		}
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return this.id != null ? this.id.hashCode() : 0;
	}

	@Override
	public String toString() {
		return this.id + ", " + this.idUsuario + ", " + this.ip;
	}
}
