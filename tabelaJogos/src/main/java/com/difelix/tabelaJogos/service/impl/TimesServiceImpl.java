package com.difelix.tabelaJogos.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.difelix.tabelaJogos.model.entity.Times;
import com.difelix.tabelaJogos.model.repository.TimesRepository;
import com.difelix.tabelaJogos.service.TimesService;

@Service
public class TimesServiceImpl implements TimesService {
	
	@Autowired
	private TimesRepository timesRepository;

	@Override
	@Transactional
	public Times criarNovoTime(Times times) {
		return timesRepository.save(times);
	}

	@Override
	@Transactional
	public Times atualizarDadosTime(Times times) {
		Objects.requireNonNull(times.getId());
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

}
