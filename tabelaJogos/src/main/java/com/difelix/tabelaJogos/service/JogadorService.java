package com.difelix.tabelaJogos.service;

import java.util.List;
import java.util.Optional;

import com.difelix.tabelaJogos.model.entity.Jogador;

public interface JogadorService {
	
	Jogador criarNovoJogador(Jogador jogador);
	
	Jogador atualizarDadosJogador(Jogador jogador);
	
	void apagarJogador(Jogador jogador);
	
	List<Jogador> filtrarJogador(Jogador jogador);
	
	void validarJogador(Jogador jogador);
	
	Optional<Jogador> getJogadorPorId(Long id);
}
