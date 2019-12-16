package com.difelix.tabelaJogos.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;

import com.difelix.tabelaJogos.model.entity.Jogos;
import com.difelix.tabelaJogos.model.repository.JogosRepository;
import com.difelix.tabelaJogos.service.JogosService;
import com.difelix.tabelaJogos.service.exception.RegraNegocioException;

@Service
public class JogosServiceImpl implements JogosService {
	
	@Autowired
	private JogosRepository jogosRepository;

	@Override
	public Jogos criarJogo(Jogos jogo) {
		validarJogo(jogo);
		return jogosRepository.save(jogo);
	}

	@Override
	public Jogos atualizarDadosJogo(Jogos jogo) {
		Objects.requireNonNull(jogo.getId());
		validarJogo(jogo);
		
		return jogosRepository.save(jogo);
	}

	@Override
	public void apagarJogo(Jogos jogo) {
		Objects.requireNonNull(jogo.getId());
		jogosRepository.delete(jogo);
	}

	@Override
	public List<Jogos> filtrarJogo(Jogos jogo) {
		Example<Jogos> example = Example
				.of(jogo, ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING));
		return jogosRepository.findAll(example);
	}

	@Override
	public void validarJogo(Jogos jogo) {
		
		if (jogo.getIdAdversario() == null) {
			throw new RegraNegocioException("Campo Adversário não pode ser nulo");
		}
		
		if (jogo.getRodada() == null) {
			throw new RegraNegocioException("Campo Rodada não pode ser nulo");
		}
		
		if (jogo.getQntdeGolsTime() == null) {
			throw new RegraNegocioException("Campo Gols Time não pode ser nulo");
		}
		
		if (jogo.getQntdeGolsAdversario() == null) {
			throw new RegraNegocioException("Campo Gols Adversário não pode ser nulo");
		}
		
		if (jogo.getDataJogo() == null) {
			throw new RegraNegocioException("Campo Data Jogo não pode ser nulo");
		}
		
		if (jogo.getLocalJogo() == null || jogo.getLocalJogo().length() > 50) {
			throw new RegraNegocioException("Campo Local Jogo não pode ser nulo e nem ter mais de 50 caracteres");
		}
		
		if (jogo.getDescricao() != null && jogo.getDescricao().length() > 100) {
			throw new RegraNegocioException("Campo Descrição não pode ter mais que 100 caracteres");
		}
		
		if (jogo.getCampeonato() == null || jogo.getCampeonato().getId() == null) {
			throw new RegraNegocioException("Campeonato precisa estar cadastrado no sistema");
		}
		
		if (jogo.getTimes() == null || jogo.getTimes().getId() == null) {
			throw new RegraNegocioException("Time precisa estar cadastrado no sistema");
		}
	}
	
	

}
