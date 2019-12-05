package com.difelix.tabelaJogos.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.difelix.tabelaJogos.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	boolean existsByEmail(String email);
	
	boolean existsByNickname(String nickname);
	
	Optional<Usuario> findByEmail(String email);
	
	Optional<Usuario> findByNickname(String nickname);

}
