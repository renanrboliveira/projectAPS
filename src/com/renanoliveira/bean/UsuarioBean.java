/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.renanoliveira.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import com.renanoliveira.entity.Usuario;
import com.renanoliveira.fachada.Fachada;

/**
 * 
 * @author renanaoliveira
 */
@ManagedBean
@SessionScoped
public class UsuarioBean {

	private Fachada fachada;
	private Usuario usuario;
	private List<Usuario> usuarios;

	public UsuarioBean() {

		fachada = new Fachada();
		usuario = new Usuario();
		usuarios = new ArrayList<Usuario>();
		usuarios.addAll(fachada.buscarTodosUsuarios());

		
	}

	

	public void novo() {

		fachada.criarUsuario(usuario);

		FacesContext context = FacesContext.getCurrentInstance();

		context.addMessage(null, new FacesMessage("Cadastrado com Sucesso",
				"Ola!" + usuario.getNome()));

		usuario = new Usuario();

	}

	public List<Usuario> listar() {

		usuarios.addAll(fachada.buscarTodosUsuarios());

		return fachada.buscarTodosUsuarios();

	}

	public String remover() {

		System.out.println(this.usuario.getNome());

		fachada.removerUsuario(this.usuario);

		return null;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void adicionarMessage(String mensagem) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				mensagem, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
}
