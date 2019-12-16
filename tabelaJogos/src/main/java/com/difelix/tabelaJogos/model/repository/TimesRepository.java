package com.difelix.tabelaJogos.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.difelix.tabelaJogos.model.entity.Times;

public interface TimesRepository extends JpaRepository<Times, Long>{
	
	boolean existsByNome(String nome);
	
	boolean existsById(Long id);
}
