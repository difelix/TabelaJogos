package com.difelix.tabelaJogos.api.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.difelix.tabelaJogos.api.dto.TimeCampeonatoDto;
import com.difelix.tabelaJogos.model.entity.Campeonato;
import com.difelix.tabelaJogos.model.entity.TimeCampeonato;
import com.difelix.tabelaJogos.model.entity.Times;
import com.difelix.tabelaJogos.service.CampeonatoService;
import com.difelix.tabelaJogos.service.TimeCampeonatoService;
import com.difelix.tabelaJogos.service.TimesService;
import com.difelix.tabelaJogos.service.exception.RegraNegocioException;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/timecampeonato")
@RequiredArgsConstructor
public class TimeCampeonatoResource {

	private final TimeCampeonatoService tcService;
	private final TimesService timesService;
	private final CampeonatoService campeonatoService;
	
	@PostMapping
	public ResponseEntity salvar(@RequestBody TimeCampeonatoDto dto) {
		try {
		    TimeCampeonato tc = converter(dto);
		    tc = tcService.salvarTimeCampeonato(tc);
		    return new ResponseEntity(tc, HttpStatus.CREATED);
		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("{id}")
	public ResponseEntity atualizar (@PathVariable("id") Long id, @RequestBody TimeCampeonatoDto dto) {
		return tcService.getTimeCampeonatoPorId(id).map(entity -> {
			try {
				TimeCampeonato tc = converter(dto);
				tc.setId(entity.getId());
				tc = tcService.atualizarTimeCampeonato(tc);
				return new ResponseEntity(tc, HttpStatus.OK);
			} catch (RegraNegocioException e) {
				return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
			}
		}).orElseGet(() -> 
		new ResponseEntity("TimeCampeonato informado não consta no sistema", HttpStatus.BAD_REQUEST));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity apagar(@PathVariable("id") Long id) {
		return tcService.getTimeCampeonatoPorId(id).map(entity -> {
			tcService.apagarTimeCampeonato(entity);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}).orElseGet(() ->
		new ResponseEntity("TimeCampeonato informado não consta no sistema", HttpStatus.BAD_REQUEST));
	}
	
	private TimeCampeonato converter(TimeCampeonatoDto dto) {
		TimeCampeonato tc = new TimeCampeonato();
		
		Times time = timesService.
				getTimePorId(dto.getIdTime())
				.orElseThrow(() -> new RegraNegocioException("Time informado não consta no sistema"));
		tc.setTimes(time);
		
		Campeonato campeonato = campeonatoService
				.getCampeonatoPorId(dto.getIdCampeonato())
				.orElseThrow(() -> new RegraNegocioException("Campeonato informado não consta no sistema"));
		tc.setCampeonato(campeonato);
		
		return tc;
	}
}
