package com.difelix.tabelaJogos.service;

import java.util.List;

import com.difelix.tabelaJogos.model.entity.Campeonato;

public interface CampeonatoService {

	Campeonato criarCampeonato(Campeonato campeonato);
	
	Campeonato atualizarDadosCampeonato(Campeonato campeonato);
	
	void apagarCampeonato(Campeonato campeonato);
	
	List<Campeonato> filtrarCampeonato(Campeonato campeonato);
	
	void validarCampeonato(Campeonato campeonato);
}
