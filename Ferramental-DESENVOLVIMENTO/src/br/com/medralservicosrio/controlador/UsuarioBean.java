package br.com.medralservicosrio.controlador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;

import br.com.medralservicosrio.dao.UsuarioDAO;
import br.com.medralservicosrio.modelo.Usuario;

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

	
	public String Autenticar(){
		
				
		return dao.autenticar(usuario.getLogin(), usuario.getSenha()) ? "menu" : "erro";
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
