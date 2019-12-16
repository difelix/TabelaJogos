package com.difelix.tabelaJogos.service;

import java.util.Optional;

import com.difelix.tabelaJogos.model.entity.TimeCampeonato;

public interface TimeCampeonatoService {
	
	TimeCampeonato salvarTimeCampeonato(TimeCampeonato tc);
	
	TimeCampeonato atualizarTimeCampeonato(TimeCampeonato tc);
	
	void apagarTimeCampeonato(TimeCampeonato tc);
	
	TimeCampeonato validarTimeCampeonato(TimeCampeonato tc);
	
	Optional<TimeCampeonato> getTimeCampeonatoPorId(Long id);

}
