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

import com.difelix.tabelaJogos.api.dto.CampeonatoDto;
import com.difelix.tabelaJogos.model.entity.Campeonato;
import com.difelix.tabelaJogos.model.entity.Usuario;
import com.difelix.tabelaJogos.model.enums.TipoCampeonato;
import com.difelix.tabelaJogos.service.CampeonatoService;
import com.difelix.tabelaJogos.service.UsuarioService;
import com.difelix.tabelaJogos.service.exception.RegraNegocioException;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/campeonatos")
@RequiredArgsConstructor
public class CampeonatoResource {

	private final CampeonatoService campeonatoService;
	private final UsuarioService usuarioService;
	
	@PostMapping("/salvar")
	public ResponseEntity salvarCampeonato(@RequestBody CampeonatoDto dto) {
		try {
		    Campeonato campeonato = converterDto(dto);
		    campeonato = campeonatoService.criarCampeonato(campeonato);
		    return new ResponseEntity(campeonato, HttpStatus.CREATED);
		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("{id}")
	public ResponseEntity atualizarCampeonato(@PathVariable("id") Long id, @RequestBody CampeonatoDto dto) {
		return campeonatoService.getCampeonatoPorId(id).map(entity -> {
			try {
			    Campeonato campeonato = converterDto(dto);
			    campeonato.setId(entity.getId());
			    campeonato = campeonatoService.atualizarDadosCampeonato(campeonato);
			    return new ResponseEntity(campeonato, HttpStatus.OK);
			} catch (RegraNegocioException e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
		}).orElseGet( () -> 
		new ResponseEntity("Campeonato informado não foi encontrado no sistema (Resource)", HttpStatus.BAD_REQUEST));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity deletarCampeonato(@PathVariable("id") Long id) {
		return campeonatoService.getCampeonatoPorId(id).map(entity -> {
			campeonatoService.apagarCampeonato(entity);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}).orElseGet( () -> 
		new ResponseEntity("Campeonato informado não foi encontrado no sistema", HttpStatus.BAD_REQUEST));	
	}
	
	@GetMapping
	public ResponseEntity filtrarCampeonato(
			@RequestParam(name = "nome", required = false) String nome,
			@RequestParam(name = "organizador", required = false) String organizador,
			@RequestParam(name = "temporada", required = false) String temporada,
			@RequestParam("idUsuario") Long idUsuario) {
		
		Campeonato campeonatoFilter = new Campeonato();
		campeonatoFilter.setNome(nome);
		campeonatoFilter.setOrganizador(organizador);
		campeonatoFilter.setTemporada(temporada);
		
		Optional<Usuario> usuarioBusca = usuarioService.getUsuarioPorId(idUsuario);
		if (!usuarioBusca.isPresent()) {
			return new ResponseEntity("Usuário buscado não existe no sistema (Resources)", HttpStatus.BAD_REQUEST);
		} else {
			campeonatoFilter.setUsuario(usuarioBusca.get());
		}
		
		List<Campeonato> listaCampeonatos = campeonatoService.filtrarCampeonato(campeonatoFilter);
		return new ResponseEntity(listaCampeonatos, HttpStatus.OK);	
	}
	
	private Campeonato converterDto(CampeonatoDto dto) {
		Campeonato campeonato = new Campeonato();
		
		campeonato.setNome(dto.getNome());		
		campeonato.setQntdeTimes(dto.getQntdeTimes());
		campeonato.setTemporada(dto.getTemporada());
		
		if (dto.getOrganizador() != null) {
			campeonato.setOrganizador(dto.getOrganizador());
		}
		
		if (dto.getDescricao() != null) {
			campeonato.setDescricao(dto.getDescricao());
		}
	
		if (dto.getReturno() != null) {
			campeonato.setReturno(TipoCampeonato.valueOf(dto.getReturno()));
		}
		
		if (dto.getIdUsuario() != null) {
			Optional<Usuario> usuario = usuarioService.getUsuarioPorId(dto.getIdUsuario());
			if (usuario.isPresent()) {
				campeonato.setUsuario(usuario.get());
			} else {
				throw new RegraNegocioException("Não foi encontrado usuário com este id (Converter)");
			}
		}
		
		return campeonato;
	}
}
