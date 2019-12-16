package com.difelix.tabelaJogos.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.difelix.tabelaJogos.model.entity.Jogador;
import com.difelix.tabelaJogos.model.repository.JogadorRepository;
import com.difelix.tabelaJogos.service.JogadorService;
import com.difelix.tabelaJogos.service.exception.RegraNegocioException;

@Service
public class JogadorServiceImpl implements JogadorService {
	
	@Autowired
	private JogadorRepository jogadorRepository;

	@Override
	@Transactional
	public Jogador criarNovoJogador(Jogador jogador) {
		validarJogador(jogador);
		return jogadorRepository.save(jogador);
	}

	@Override
	@Transactional
	public Jogador atualizarDadosJogador(Jogador jogador) {
		Objects.requireNonNull(jogador.getId());
		validarJogador(jogador);
		
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

	@Override
	public void validarJogador(Jogador jogador) {
		if (jogador.getNome() == null || jogador.getNome().length() > 30) {
			throw new RegraNegocioException("Campo Nome não pode ser nulo e nem ter mais que 30 caracteres");
		}
		
		if (jogador.getNascimento() == null) {
			throw new RegraNegocioException("Campo Nascimento não pode ser nulo");
		}
		
		if (jogador.getNacionalidade() == null || jogador.getNacionalidade().length() > 30) {
			throw new RegraNegocioException("Campo Nacionalidade não pode ser nulo e nem ter mais que 30 caracteres");
		}
		
		if (jogador.getPosicao() == null) {
			throw new RegraNegocioException("Campo Posição não pode ser nulo");
		}
		
		if (jogador.getTimes() == null || jogador.getTimes().getId() == null) {
			throw new RegraNegocioException("Time informado não consta no sistema");
		}	
	}

	@Override
	public Optional<Jogador> getJogadorPorId(Long id) {
		return jogadorRepository.findById(id);
	}
	
	

}
