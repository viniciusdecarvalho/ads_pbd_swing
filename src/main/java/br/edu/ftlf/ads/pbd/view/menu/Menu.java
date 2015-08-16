package br.edu.ftlf.ads.pbd.view.menu;

import javax.swing.Icon;

public class Menu {

	private String nome;
	private Icon icone;
	private Menu menuPai;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Icon getIcone() {
		return icone;
	}

	public void setIcone(Icon icone) {
		this.icone = icone;
	}

	public Menu getMenuPai() {
		return menuPai;
	}

	public void setMenuPai(Menu menuPai) {
		this.menuPai = menuPai;
	}

}
