package com.difelix.tabelaJogos.api.resource;

import java.util.List;
import java.util.Optional;

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

import com.difelix.tabelaJogos.api.dto.JogadorDto;
import com.difelix.tabelaJogos.model.entity.Jogador;
import com.difelix.tabelaJogos.model.entity.Times;
import com.difelix.tabelaJogos.model.enums.JogadorPosicao;
import com.difelix.tabelaJogos.service.JogadorService;
import com.difelix.tabelaJogos.service.TimesService;
import com.difelix.tabelaJogos.service.exception.RegraNegocioException;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/jogadores")
@RequiredArgsConstructor
public class JogadorResource {

	private final JogadorService jogadorService;
	private final TimesService timesService;
	
	@PostMapping
	public ResponseEntity criarJogador(@RequestBody JogadorDto dto) {
		try {
		    Jogador jogador = converter(dto);
		    jogador = jogadorService.criarNovoJogador(jogador);
		    return new ResponseEntity(jogador, HttpStatus.CREATED);
		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("{id}")
	public ResponseEntity atualizarDadosJogador(@PathVariable("id") Long id, @RequestBody JogadorDto dto) {
	    return jogadorService.getJogadorPorId(id).map(entity -> {
	    	try {
	    	    Jogador jogador = converter(dto);
	    	    jogador.setId(entity.getId());
	    	    jogador = jogadorService.atualizarDadosJogador(jogador);
	    	    return new ResponseEntity(jogador, HttpStatus.OK);
	    	} catch (RegraNegocioException e) {
	    		return ResponseEntity.badRequest().body(e.getMessage());
	    	}
	    }).orElseGet( () -> 
	    new ResponseEntity("Jogador informado não consta no sistema", HttpStatus.BAD_REQUEST));
		
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity apagarJogador(@PathVariable("id") Long id) {
		return jogadorService.getJogadorPorId(id).map(entity -> {
			jogadorService.apagarJogador(entity);
			return new ResponseEntity(HttpStatus.NO_CONTENT);		
		}).orElseGet( () -> 
		new ResponseEntity("Jogador informado não consta no sistema", HttpStatus.BAD_REQUEST));
	}
	
	@GetMapping
	public ResponseEntity filtrarJogador(@RequestParam(name = "nome", required = false) String nome,
			 @RequestParam(name = "posicao", required = false) String posicao,
			 @RequestParam(name = "idTime", required = true) Long idTime) {
		
		Jogador jogador = new Jogador();
		jogador.setNome(nome);
		
		if (posicao != null) {
			jogador.setPosicao(JogadorPosicao.valueOf(posicao));
		}
		
		Optional<Times> time = timesService.getTimePorId(idTime);
		if (time.isPresent()) {
			jogador.setTimes(time.get());
		} else {
			return ResponseEntity.badRequest().body("Time informado não consta no sistema");
		}
		
		List<Jogador> jogadoresFiltrados = jogadorService.filtrarJogador(jogador);
		return new ResponseEntity(jogadoresFiltrados, HttpStatus.OK);	
	}
	
	public Jogador converter(JogadorDto dto) {
		Jogador jogador = new Jogador();
		
		jogador.setNome(dto.getNome());
		jogador.setNascimento(dto.getNascimento());
		jogador.setNacionalidade(dto.getNacionalidade());
		
		if (dto.getDescricao() != null) {
			jogador.setDescricao(dto.getDescricao());
		}
		
		if (dto.getPosicao() != null) {
			jogador.setPosicao(JogadorPosicao.valueOf(dto.getPosicao()));
		}
		
		if (dto.getIdTime() != null) {
			Times time = timesService.getTimePorId(dto.getIdTime())
					.orElseThrow( () -> new RegraNegocioException("Time informado não consta no sistema"));
			jogador.setTimes(time);
		}
		
		return jogador;
	}
}
