package br.edu.ftlf.ads.pbd.bean;

import org.springframework.stereotype.Component;

@Component
public class UserInfo {

	private Usuario usuario;
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public boolean isLogged() {
		return usuario != null;
	}
	
	public void logout() {
		usuario = null;
	}
	
}
