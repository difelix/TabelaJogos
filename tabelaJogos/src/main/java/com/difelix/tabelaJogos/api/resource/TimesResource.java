package com.difelix.tabelaJogos.api.resource;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.difelix.tabelaJogos.api.dto.TimesDto;
import com.difelix.tabelaJogos.model.entity.Times;
import com.difelix.tabelaJogos.service.TimesService;
import com.difelix.tabelaJogos.service.exception.RegraNegocioException;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/times")
@RequiredArgsConstructor
public class TimesResource {

	private final TimesService timesService;
	
	@PostMapping
	public ResponseEntity salvarTime(@RequestBody TimesDto dto) {
		try {
		    Times times = converter(dto);
		    times = timesService.criarNovoTime(times);
		    return new ResponseEntity(times, HttpStatus.CREATED);
		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("{id}")
	public ResponseEntity editarTime(@PathVariable("id") Long id, @RequestBody TimesDto dto) {
		return timesService.getTimePorId(id).map(entity -> {
			try {
				Times times = converter(dto);
				times.setId(entity.getId());
				Times timeAtualizado = timesService.atualizarDadosTime(times);
				return new ResponseEntity(timeAtualizado, HttpStatus.OK);
			} catch (RegraNegocioException e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
		}).orElseGet( () -> new ResponseEntity("Time informado não consta no sistema", HttpStatus.BAD_REQUEST));	
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity apagarTime(@PathVariable("id") Long id) {
		return timesService.getTimePorId(id).map(entity -> {
			timesService.apagarTime(entity);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}).orElseGet( () -> 
		new ResponseEntity("Time informado não consta no sistema", HttpStatus.BAD_REQUEST));
	}
	
	@GetMapping
	public ResponseEntity filtrarTime(
			@RequestParam(name = "nome", required = false) String nome,
			@RequestParam(name = "cidadeSede", required = false) String cidadeSede,
			@RequestParam(name = "federacaoAfiliado", required = false) String federacaoAfiliado,
			@RequestParam(name = "estadio", required = false) String estadio) {
		
		Times time = Times.builder()
				.nome(nome)
				.cidadeSede(cidadeSede)
				.federacaoAfiliado(federacaoAfiliado)
				.estadio(estadio).build();
		List<Times> listaTimes = timesService.filtrarTime(time);
		
		return new ResponseEntity(listaTimes, HttpStatus.OK);
	}
	
	private Times converter(TimesDto dto) {
		Times times = new Times();
		
		times.setNome(dto.getNome());
		times.setDataCriacao(dto.getDataCriacao());
		times.setCidadeSede(dto.getCidadeSede());
		times.setFederacaoAfiliado(dto.getFederacaoAfiliado());
		
		if (dto.getDescricao() != null) {
			times.setDescricao(dto.getDescricao());
		}
		
		if (dto.getEstadio() != null) {
		    times.setEstadio(dto.getEstadio());
		}
		
		return times;
	}
}
