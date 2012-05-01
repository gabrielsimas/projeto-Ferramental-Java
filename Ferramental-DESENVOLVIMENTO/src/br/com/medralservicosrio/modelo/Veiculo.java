package br.com.medralservicosrio.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Classe de Persistência Veiculo
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
@Entity(name="Veiculo")
@Table(name="cadcicle")
public class Veiculo implements Serializable{
	
	private static final long serialVersionUID = -7123870554661472770L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id",unique=true,nullable=false)
    private Integer id;
	
	@Column(name="placa",length=8,nullable=false)
    private String placa;
	
	@Column(name="marca",length=30,nullable=false)
    private String marca;
	
	@Column(name="modelo",length=30,nullable=false)
    private String modelo;
	
	@Column(name="setor",length=30,nullable=false)
    private String setor; //TODO: Criar uma Tabela Primária para receber o código dos setores
	
	@Column(name="chapa_enc",nullable=false)
    private Integer matriculaFuncionario;
	
	@Column(name="status",length=30,nullable=false)
    private String status;
    
    public Veiculo(){
    	
    }

	public Veiculo(Integer id, String placa, String marca, String modelo,
			String setor, Integer matriculaFuncionario, String status) {
		this.id = id;
		this.placa = placa;
		this.marca = marca;
		this.modelo = modelo;
		this.setor = setor;
		this.matriculaFuncionario = matriculaFuncionario;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getSetor() {
		return setor;
	}

	public void setSetor(String setor) {
		this.setor = setor;
	}

	public Integer getMatriculaFuncionario() {
		return matriculaFuncionario;
	}

	public void setMatriculaFuncionario(Integer matriculaFuncionario) {
		this.matriculaFuncionario = matriculaFuncionario;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Veiculo){
			Veiculo veiculo = (Veiculo) obj;
			
			if ( veiculo.getId() != null){
				return veiculo.getId().equals(this.id);
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
		
		return this.id + ", " + this.placa + ", " + this.marca + ", " + this.modelo;
	}    
}
