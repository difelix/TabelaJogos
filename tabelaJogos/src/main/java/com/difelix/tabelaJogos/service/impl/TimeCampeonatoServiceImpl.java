package com.difelix.tabelaJogos.service.impl;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.difelix.tabelaJogos.model.entity.TimeCampeonato;
import com.difelix.tabelaJogos.model.repository.TimeCampeonatoRepository;
import com.difelix.tabelaJogos.service.TimeCampeonatoService;
import com.difelix.tabelaJogos.service.exception.RegraNegocioException;

@Service
public class TimeCampeonatoServiceImpl implements TimeCampeonatoService {
	
	@Autowired
	private TimeCampeonatoRepository tcRepository;

	@Override
	public TimeCampeonato salvarTimeCampeonato(TimeCampeonato tc) {
		validarTimeCampeonato(tc);
		return tcRepository.save(tc);
	}

	@Override
	public TimeCampeonato atualizarTimeCampeonato(TimeCampeonato tc) {
		Objects.requireNonNull(tc.getId());
		validarTimeCampeonato(tc);
		
		return tcRepository.save(tc);
	}

	@Override
	public void apagarTimeCampeonato(TimeCampeonato tc) {
		Objects.requireNonNull(tc.getId());
		tcRepository.delete(tc);
	}

	@Override
	public TimeCampeonato validarTimeCampeonato(TimeCampeonato tc) {
		TimeCampeonato tcam = new TimeCampeonato();
		
		if (tc.getTimes() == null || tc.getTimes().getId() == null) {
			throw new RegraNegocioException("Time não pode ser nulo");
		}
		
		if (tc.getCampeonato() == null || tc.getCampeonato().getId() == null) {
			throw new RegraNegocioException("Campeonato não pode ser nulo");
		}
		
		return tcam;
	}

	@Override
	public Optional<TimeCampeonato> getTimeCampeonatoPorId(Long id) {
		return tcRepository.findById(id);
	}
	
}
