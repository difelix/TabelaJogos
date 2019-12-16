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

import com.difelix.tabelaJogos.model.entity.Times;
import com.difelix.tabelaJogos.model.repository.TimesRepository;
import com.difelix.tabelaJogos.service.TimesService;
import com.difelix.tabelaJogos.service.exception.RegraNegocioException;

@Service
public class TimesServiceImpl implements TimesService {
	
	@Autowired
	private TimesRepository timesRepository;

	@Override
	@Transactional
	public Times criarNovoTime(Times times) {
		validarTime(times);
		return timesRepository.save(times);
	}

	@Override
	@Transactional
	public Times atualizarDadosTime(Times times) {
		Objects.requireNonNull(times.getId());
		validarTime(times);
		
		return timesRepository.save(times);
	}

	@Override
	@Transactional
	public void apagarTime(Times times) {
		Objects.requireNonNull(times.getId());
		timesRepository.delete(times);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Times> filtrarTime(Times times) {
		Example example = Example.of(times, 
				ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING));
		return timesRepository.findAll(example);
	}

	@Override
	public Optional<Times> getTimePorId(Long id) {
		return timesRepository.findById(id);
	}

	@Override
	public void validarTime(Times times) {
		if (times.getNome() == null || times.getNome().length() > 30) {
			throw new RegraNegocioException("Campo Nome não pode ser nulo nem ter mais de 30 caracteres");
		}
		
		boolean existNome = timesRepository.existsByNome(times.getNome());
		if (existNome) {
			throw new RegraNegocioException("Já existe cadastrado no sistema time com nome informado");
		}
		
		if (times.getDataCriacao() == null) {
			throw new RegraNegocioException("Campo Data Criação não pode ser nulo");
		}
		
		if (times.getCidadeSede() == null || times.getCidadeSede().length() > 20) {
			throw new RegraNegocioException("Campo Cidade Sede não pode ser nulo nem ter mais de 20 caracteres");
		}
		
		if (times.getFederacaoAfiliado() == null || times.getFederacaoAfiliado().length() > 50) {
			throw new RegraNegocioException("Campo Federação Afiliado não pode ser nulo nem ter mais de 50 caracteres");
		}
		
		if (times.getEstadio() != null && times.getEstadio().length() > 50) {
			throw new RegraNegocioException("Campo Estádio não pode ter mais que 50 caracteres");
		}
		
		if (times.getDescricao() != null && times.getDescricao().length() > 100) {
			throw new RegraNegocioException("Campo descrição não pode ter mais que 100 caracteres");
		}
	}

	@Override
	public boolean existeTimeSalvo(Long id) {
		return timesRepository.existsById(id);
	}

}
