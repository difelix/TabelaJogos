package com.difelix.tabelaJogos.service;

import java.util.List;

import com.difelix.tabelaJogos.model.entity.Times;

public interface TimesService {
	
	Times criarNovoTime(Times times);
	
	Times atualizarDadosTime(Times times);
	
	void apagarTime(Times times);
	
	List<Times> filtrarTime(Times times);
}
