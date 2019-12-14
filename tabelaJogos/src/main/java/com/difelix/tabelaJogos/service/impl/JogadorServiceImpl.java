package com.difelix.tabelaJogos.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.difelix.tabelaJogos.model.entity.Jogador;
import com.difelix.tabelaJogos.model.repository.JogadorRepository;
import com.difelix.tabelaJogos.service.JogadorService;

@Service
public class JogadorServiceImpl implements JogadorService {
	
	@Autowired
	private JogadorRepository jogadorRepository;

	@Override
	@Transactional
	public Jogador criarNovoJogador(Jogador jogador) {
		return jogadorRepository.save(jogador);
	}

	@Override
	@Transactional
	public Jogador atualizarDadosJogador(Jogador jogador) {
		Objects.requireNonNull(jogador.getId());
		return jogadorRepository.save(jogador);
	}

	@Override
	@Transactional
	public void apagarJogador(Jogador jogador) {
		Objects.requireNonNull(jogador.getId());
		jogadorRepository.delete(jogador);	
	}

	@Override
	@Transactional(readOnly = true)
	public List<Jogador> filtrarJogador(Jogador jogador) {
		Example example = Example.of(jogador, 
				ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING));
		return jogadorRepository.findAll(example);
	}

}
