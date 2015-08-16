package br.edu.ftlf.ads.pbd.repository;

import java.util.List;

import br.edu.ftlf.ads.pbd.bean.Banco;
import br.edu.ftlf.ads.pbd.bean.CentroCusto;
import br.edu.ftlf.ads.pbd.bean.Cliente;
import br.edu.ftlf.ads.pbd.bean.FormasPagamento;
import br.edu.ftlf.ads.pbd.bean.Fornecedor;
import br.edu.ftlf.ads.pbd.bean.Usuario;

public class Stores {

	private static final Store INSTANCE = new Store();
	
	private Stores(){}
	
	private static Store getInstance() {
		return INSTANCE;
	}
	
	public static List<Banco> getBancos() {
		return getInstance().getBancos();
	}
	
	public static List<CentroCusto> getCentroCustos() {
		return getInstance().getCentroCustos();
	}
	
	public List<FormasPagamento> getFormaPagamentos() {
		return getInstance().getFormaPagamentos();
	}

	public List<Cliente> getClientes() {
		return getInstance().getClientes();
	}

	public List<Fornecedor> getFornecedores() {
		return getInstance().getFornecedores();
	}
	
	public List<Usuario> getUsuarios() {
		return getInstance().getUsuarios();
	}
	
}
