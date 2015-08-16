package br.edu.ftlf.ads.pbd.repository;

import java.util.ArrayList;

import org.jdesktop.observablecollections.ObservableCollections;
import org.jdesktop.observablecollections.ObservableList;

import br.edu.ftlf.ads.pbd.bean.Banco;
import br.edu.ftlf.ads.pbd.bean.CentroCusto;
import br.edu.ftlf.ads.pbd.bean.Cliente;
import br.edu.ftlf.ads.pbd.bean.FormasPagamento;
import br.edu.ftlf.ads.pbd.bean.Fornecedor;
import br.edu.ftlf.ads.pbd.bean.Usuario;

public final class Store {

	private ObservableList<Banco> bancos;
	private ObservableList<CentroCusto> centroCustos;
	private ObservableList<FormasPagamento> formaPagamentos;
	private ObservableList<Cliente> clientes;
	private ObservableList<Fornecedor> fornecedores;
	private ObservableList<Usuario> usuarios;
	
	public Store() {}	

	public ObservableList<Banco> getBancos() {
		if (bancos == null) {			
			bancos = ObservableCollections.observableList(new ArrayList<Banco>());			
		}
		return bancos;
	}

	public ObservableList<CentroCusto> getCentroCustos() {
		if (centroCustos == null) {
			centroCustos = ObservableCollections.observableList(new ArrayList<CentroCusto>());
		}
		return centroCustos;
	}

	public ObservableList<FormasPagamento> getFormaPagamentos() {
		if (formaPagamentos == null) {
			formaPagamentos = ObservableCollections.observableList(new ArrayList<FormasPagamento>());
		}
		return formaPagamentos;
	}

	public ObservableList<Cliente> getClientes() {
		if (clientes == null) {
			clientes = ObservableCollections.observableList(new ArrayList<Cliente>());
		}
		return clientes;
	}

	public ObservableList<Fornecedor> getFornecedores() {
		if (fornecedores == null) {
			fornecedores = ObservableCollections.observableList(new ArrayList<Fornecedor>());
		}
		return fornecedores;
	}
	
	public ObservableList<Usuario> getUsuarios() {
		if (usuarios == null) {
			usuarios = ObservableCollections.observableList(new ArrayList<Usuario>());
		}
		return usuarios;
	}
	
}
