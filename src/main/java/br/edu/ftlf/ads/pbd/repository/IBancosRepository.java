package br.edu.ftlf.ads.pbd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.edu.ftlf.ads.pbd.bean.Banco;

public interface IBancosRepository extends JpaRepository<Banco, Short> {

	@Override
	@Query("select b from Banco b order by b.nome")
	List<Banco> findAll();
	
	@Query("select b from Banco b where lower(b.nome) = lower(:nome)")
	Banco exists(@Param("nome") String nome);

	@Query("select b from Banco b where b.ativo = true order by b.nome")
	List<Banco> findEnabled();
	
}
