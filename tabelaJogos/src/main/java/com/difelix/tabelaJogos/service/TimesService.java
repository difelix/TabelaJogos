package com.difelix.tabelaJogos.service;

import java.util.List;
import java.util.Optional;

import com.difelix.tabelaJogos.model.entity.Times;

public interface TimesService {
	
	Times criarNovoTime(Times times);
	
	Times atualizarDadosTime(Times times);
	
	void apagarTime(Times times);
	
	List<Times> filtrarTime(Times times);
	
	Optional<Times> getTimePorId(Long id);
	
	void validarTime(Times times);
	
	boolean existeTimeSalvo(Long id);
}
