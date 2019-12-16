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

import com.difelix.tabelaJogos.model.entity.Campeonato;
import com.difelix.tabelaJogos.model.repository.CampeonatoRepository;
import com.difelix.tabelaJogos.service.CampeonatoService;
import com.difelix.tabelaJogos.service.exception.RegraNegocioException;

@Service
public class CampeonatoServiceImpl implements CampeonatoService {
	
	@Autowired
	private CampeonatoRepository campeonatoRepository;

	@Override
	@Transactional
	public Campeonato criarCampeonato(Campeonato campeonato) {
		validarCampeonato(campeonato);
		return campeonatoRepository.save(campeonato);
	}

	@Override
	@Transactional
	public Campeonato atualizarDadosCampeonato(Campeonato campeonato) {
		Objects.requireNonNull(campeonato.getId());
		validarCampeonato(campeonato);
		
		return campeonatoRepository.save(campeonato);
	}

	@Override
	@Transactional
	public void apagarCampeonato(Campeonato campeonato) {
		Objects.requireNonNull(campeonato.getId());
		campeonatoRepository.delete(campeonato);
	}

	@Transactional(readOnly = true)
	public List<Campeonato> filtrarCampeonato(Campeonato campeonato) {
		Example example = Example.of(campeonato,
				ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING));
		
		return campeonatoRepository.findAll(example);
	}

	@Override
	public void validarCampeonato(Campeonato campeonato) {
		
		if (campeonato.getNome() == null || campeonato.getNome().length() > 20) {
			throw new RegraNegocioException("Campo Nome não pode ser nulo nem possuir mais de 20 caracteres");
		}
		
		if (campeonato.getOrganizador() != null && campeonato.getOrganizador().length() > 30) {
			throw new RegraNegocioException("Campo Organizador não pode possuir mais de 30 caracteres");
		}
		
		if (campeonato.getTemporada() == null || campeonato.getTemporada().length() > 20) {
			throw new RegraNegocioException("Campo Temporada não pode ser nulo nem possuir mais de 20 caracteres");
		}
		
		if (campeonato.getQntdeTimes() == null || campeonato.getQntdeTimes() <= 1) {
			throw new RegraNegocioException("Campeonato não pode ser nulo e precisa ter mais de 2 times participantes");
		}
		
		if (campeonato.getReturno() == null) {
			throw new RegraNegocioException("Campo Returno não pode ser nulo");
		}
		
		if (campeonato.getUsuario() == null || campeonato.getUsuario().getId() == null) {
			throw new RegraNegocioException("É necessário vincular um usuário válido");		
		}		
	}

	@Override
	public Optional<Campeonato> getCampeonatoPorId(Long id) {
		return campeonatoRepository.findById(id);
	}

}
