package com.difelix.tabelaJogos.service;

import java.util.List;

import com.difelix.tabelaJogos.model.entity.Jogos;

public interface JogosService {
	
	Jogos criarJogo(Jogos jogo);
	
	Jogos atualizarDadosJogo(Jogos jogo);
	
	void apagarJogo(Jogos jogo);
	
	List<Jogos> filtrarJogo(Jogos jogo);
	
	void validarJogo(Jogos jogo);

}
