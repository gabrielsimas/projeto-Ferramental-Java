package br.com.medralservicosrio.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Classe de Persistência Movimentação
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
@Entity(name="Movimentacao")
@Table(name="moviment")
public class Movimentacao implements Serializable{
	
	private static final long serialVersionUID = 1266041412099519347L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idmove",nullable=false,unique=true)
	private Integer	id;
	
	@Column(name="chapa",nullable=false)
	private Integer	matriculaFuncionario;
	
	@Column(name="idproduto",nullable=false)
	private Integer	idProduto;
	
	@Column(name="rastre",nullable=false)
	private Integer	idRastreamento;
	
	@Column(name="usuario")
	private String	usuario;
	private Integer quantidade;
	private Date 	data;
	private String 	motivo;
	private String 	status;
	private Integer tipoMovimento;
	private String 	caminho;
	private String 	placa;
	
	public Movimentacao() {
		
	}

	public Movimentacao(Integer id, Integer matriculaFuncionario,
			Integer idProduto, Integer idRastreamento, String usuario,
			Integer quantidade, Date data, String motivo, String status,
			Integer tipoMovimento, String caminho, String placa) {
		this.id = id;
		this.matriculaFuncionario = matriculaFuncionario;
		this.idProduto = idProduto;
		this.idRastreamento = idRastreamento;
		this.usuario = usuario;
		this.quantidade = quantidade;
		this.data = data;
		this.motivo = motivo;
		this.status = status;
		this.tipoMovimento = tipoMovimento;
		this.caminho = caminho;
		this.placa = placa;
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

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getTipoMovimento() {
		return tipoMovimento;
	}

	public void setTipoMovimento(Integer tipoMovimento) {
		this.tipoMovimento = tipoMovimento;
	}

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Movimentacao){
			Movimentacao movimentacao = (Movimentacao) obj;
			
			if (movimentacao.getId() != null){
				return movimentacao.getId().equals(this.id);
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
		return this.id + ", " + this.idProduto + ", " + this.idRastreamento + ", " + this.matriculaFuncionario + ", " + this.placa;
	}
	
}
