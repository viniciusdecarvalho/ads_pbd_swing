package br.edu.ftlf.ads.pbd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.edu.ftlf.ads.pbd.bean.Usuario;

public interface IUsuariosRepository extends JpaRepository<Usuario, Short> {

	@Query("select u from Usuario u where u.login = :login and senha = :senha")
	Usuario login(@Param("login")String login, @Param("senha")String senha);
	
	@Query("select u from Usuario u where u.login = :login")
	Usuario findByLogin(@Param("login") String login);
}
