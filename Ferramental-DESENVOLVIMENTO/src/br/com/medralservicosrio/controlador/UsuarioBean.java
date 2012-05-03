package br.com.medralservicosrio.controlador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.medralservicosrio.dao.UsuarioDAO;
import br.com.medralservicosrio.modelo.Usuario;

@ManagedBean
@SessionScoped
public class UsuarioBean implements Serializable{
	
	private static final long serialVersionUID = 1249564889274195799L;
	
	private Usuario usuario;
	private UsuarioDAO dao;
	private List<Usuario> usuarios;
	
	
	public UsuarioBean() {
		usuario = new Usuario();
		dao = new UsuarioDAO();
		usuarios = new ArrayList<Usuario>();
	}

	
	public String autenticar(){
		
		return dao.autenticar(usuario.getLogin(), usuario.getSenha()) ? "principal" : "";
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
