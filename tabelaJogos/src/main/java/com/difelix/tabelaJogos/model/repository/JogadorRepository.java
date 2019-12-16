package com.difelix.tabelaJogos.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.difelix.tabelaJogos.model.entity.Jogador;

public interface JogadorRepository extends JpaRepository<Jogador, Long>{
	

}
