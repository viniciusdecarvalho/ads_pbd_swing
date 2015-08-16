package br.edu.ftlf.ads.pbd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.edu.ftlf.ads.pbd.bean.FormasPagamento;

public interface IFormasPagamentoRepository extends JpaRepository<FormasPagamento, Short> {

	@Override
	@Query("select f from FormasPagamento f order by c.nome")
	List<FormasPagamento> findAll();
	
	@Query("select f from FormasPagamento f where lower(f.nome) = lower(:nome)")
	FormasPagamento exists(@Param("nome") String nome);

	@Query("select f from FormasPagamento f where f.ativo = true order by f.nome")
	List<FormasPagamento> findEnabled();
}
