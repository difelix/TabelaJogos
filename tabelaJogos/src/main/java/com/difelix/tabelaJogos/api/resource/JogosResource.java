package com.difelix.tabelaJogos.api.resource;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.difelix.tabelaJogos.api.dto.JogosDto;
import com.difelix.tabelaJogos.model.entity.Campeonato;
import com.difelix.tabelaJogos.model.entity.Jogos;
import com.difelix.tabelaJogos.model.entity.Times;
import com.difelix.tabelaJogos.service.CampeonatoService;
import com.difelix.tabelaJogos.service.JogosService;
import com.difelix.tabelaJogos.service.TimesService;
import com.difelix.tabelaJogos.service.exception.RegraNegocioException;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/jogos")
@RequiredArgsConstructor
public class JogosResource {
	
	private final JogosService jogosService;
	private final CampeonatoService campeonatoService;
	private final TimesService timesService;
	
	@PostMapping
	public ResponseEntity salvarJogo(@RequestBody JogosDto dto) {
		try {
			Jogos jogo = converter(dto);
			jogo = jogosService.criarJogo(jogo);
			return new ResponseEntity(jogo, HttpStatus.CREATED);
		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}	
	}
	
	private Jogos converter(JogosDto dto) {
		Jogos jogos = new Jogos();
		
		jogos.setDescricao(dto.getDescricao());
		jogos.setDataJogo(dto.getDataJogo());
		jogos.setLocalJogo(dto.getLocalJogo());
		jogos.setQntdeGolsAdversario(dto.getQntdeGolsAdversario());
		jogos.setQntdeGolsTime(dto.getQntdeGolsTime());
		jogos.setRodada(dto.getRodada());
		
		if (dto.getIdCampeonato() != null) {
			Optional<Campeonato> campeonato = campeonatoService.getCampeonatoPorId(dto.getIdCampeonato());
			if (campeonato.isPresent()) {
				jogos.setCampeonato(campeonato.get());
			} else {
				throw new RegraNegocioException("Campeonato informado não consta no sistema");
			}
		}
		
		if (dto.getIdTime() != null) {
			Optional<Times> timeCasa = timesService.getTimePorId(dto.getIdTime());
			if (timeCasa.isPresent()) {
				jogos.setTimes(timeCasa.get());
			} else {
				throw new RegraNegocioException("Time informado não consta no sistema");
			}
		}
		
		if (dto.getIdAdversario() != null) {
			if (timesService.existeTimeSalvo(dto.getIdAdversario())) {
				jogos.setIdAdversario(dto.getIdAdversario());
			} else {
				throw new RegraNegocioException("Time adversário informado não consta no sistema");
			}
		}
		
		return jogos;
	}

}
