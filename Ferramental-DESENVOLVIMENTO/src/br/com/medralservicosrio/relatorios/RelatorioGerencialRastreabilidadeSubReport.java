package br.com.medralservicosrio.relatorios;

import java.util.Date;

public class RelatorioGerencialRastreabilidadeSubReport {

	private String nomeFuncionario;
	private String status;
	private String status2;
	private Date data;
	private String TUPF;

	public RelatorioGerencialRastreabilidadeSubReport() {
	}
	
	
	
	/**
	 * @return the nomeFuncionario
	 */
	public String getNomeFuncionario() {
		return nomeFuncionario;
	}
	/**
	 * @param nomeFuncionario the nomeFuncionario to set
	 */
	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the status2
	 */
	public String getStatus2() {
		return status2;
	}
	/**
	 * @param status2 the status2 to set
	 */
	public void setStatus2(String status2) {
		this.status2 = status2;
	}
	/**
	 * @return the data
	 */
	public Date getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(Date data) {
		this.data = data;
	}
	/**
	 * @return the tUPF
	 */
	public String getTUPF() {
		return TUPF;
	}
	/**
	 * @param tUPF the tUPF to set
	 */
	public void setTUPF(String tUPF) {
		TUPF = tUPF;
	}
	
}
