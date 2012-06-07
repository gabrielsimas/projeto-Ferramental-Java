package br.com.medralservicosrio.controlador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.medralservicosrio.dao.UsuarioDAO;
import br.com.medralservicosrio.modelo.Usuario;

@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable{
	
	private static final long serialVersionUID = 1249564889274195799L;
	
	private Usuario usuario;
	private UsuarioDAO dao;
	private List<Usuario> usuarios;
	
	public UsuarioBean() {
		usuario = new Usuario();
		//dao = new UsuarioDAO();
		usuarios = new ArrayList<Usuario>();
		
	}

	
	public String autenticar(){
		FacesContext fc = FacesContext.getCurrentInstance();
		
				
		fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,null, "Usuário ou senha inválidos!"));
		return new UsuarioDAO().autenticar(usuario.getLogin(), usuario.getSenha()) ? "principal?faces-redirect=true" :"";
	}
	
	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public UsuarioDAO getDao() {
		return dao;
	}


	public void setDao(UsuarioDAO dao) {
		this.dao = dao;
	}


	public List<Usuario> getUsuarios() {
		return usuarios;
	}


	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
}
