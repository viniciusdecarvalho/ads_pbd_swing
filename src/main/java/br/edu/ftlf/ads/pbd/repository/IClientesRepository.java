package br.edu.ftlf.ads.pbd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.edu.ftlf.ads.pbd.bean.Cliente;

public interface IClientesRepository extends JpaRepository<Cliente, Integer> {

	@Query("select c from Cliente c order by c.razaoSocial")
	List<Cliente> findAll();
	
	@Query("select c from Cliente c where c.cpfCnpj = :cpfCnpj")
	Cliente exists(@Param("cpfCnpj") String cpfCnpj);
	
	@Query("select c from Cliente c where c.ativo = true order by c.cpfCnpj")
	List<Cliente> findEnabled();

}
