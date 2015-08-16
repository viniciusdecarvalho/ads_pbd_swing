package br.edu.ftlf.ads.pbd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.edu.ftlf.ads.pbd.bean.Fornecedor;

public interface IFornecedoresRepository extends JpaRepository<Fornecedor, Integer> {

	@Query("select f from Fornecedor f order by c.razaoSocial")
	List<Fornecedor> findAll();
	
	@Query("select f from Fornecedor f where f.cpfCnpj = :cpfCnpj")
	Fornecedor exists(@Param("cpfCnpj") String cpfCnpj);
	
	@Query("select f from Fornecedor f where f.ativo = true order by f.cpfCnpj")
	List<Fornecedor> findEnabled();

}
