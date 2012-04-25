/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.medralservicosrio.controlador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.medralservicosrio.modelo.Usuario;


/**
 *
 * @author Marco Aurélio
 */
@ManagedBean
@SessionScoped
public class UsuarioBean implements Serializable{

	private static final long serialVersionUID = -1098189646846200974L;
	
	private Usuario usuario;
	private List<Usuario> usuarios;
		
	public UsuarioBean() {
		usuario = new Usuario();
		usuarios = new ArrayList<Usuario>();
	}
	
	public String efetuaLogin(){
		return "../paginas/menu";
	}
	
	public UsuarioBean(Usuario usuario, List<Usuario> usuarios) {
		this.usuario = usuario;
		this.usuarios = usuarios;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
    
    /*private static final long serialVersionUID = 7870614247259159826L;
	private Usuario usuario = new Usuario();
    private UsuarioDAO usuariodao = new UsuarioDAO();
    
    public Usuario getUsuario(){
        return usuario; 
    }  
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public String efetuaLogin() throws SQLException{
        
        boolean loginvalido = usuariodao.validar(usuario);
        if (loginvalido) {
            return "/paginas/principal?faces-redirect=true";
        }
        else{
            usuario = new  Usuario();
            return "login";
        }
    }*/
}
