package com.difelix.tabelaJogos.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.difelix.tabelaJogos.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
