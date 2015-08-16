package br.edu.ftlf.ads.pbd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.edu.ftlf.ads.pbd.bean.CentroCusto;

public interface ICentroCustoRepository extends JpaRepository<CentroCusto, Short> {

	@Override
	@Query("select c from CentroCusto c order by c.nome")
	List<CentroCusto> findAll();
	
	@Query("select c from CentroCusto c where lower(c.nome) = lower(:nome)")
	CentroCusto exists(@Param("nome") String nome);

	@Query("select c from CentroCusto c where c.ativo = true order by c.nome")
	List<CentroCusto> findEnabled();
}
