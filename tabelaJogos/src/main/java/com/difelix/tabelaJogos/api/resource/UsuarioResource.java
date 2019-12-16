package com.difelix.tabelaJogos.api.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.difelix.tabelaJogos.api.dto.UsuarioDto;
import com.difelix.tabelaJogos.model.entity.Usuario;
import com.difelix.tabelaJogos.service.UsuarioService;
import com.difelix.tabelaJogos.service.exception.ErroAutenticacaoException;
import com.difelix.tabelaJogos.service.exception.RegraNegocioException;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/usuario")
@RequiredArgsConstructor
public class UsuarioResource {
	
	private final UsuarioService usuarioService;
	
	@PostMapping("/salvarEmail")
	public ResponseEntity salvarUsuarioComEmail(@RequestBody UsuarioDto usuarioDto) {
		Usuario usuario = Usuario.builder()
				.email(usuarioDto.getEmail())
				.password(usuarioDto.getPassword())
				.name(usuarioDto.getName())
				.nickname(usuarioDto.getNickname())
				.build();
		
		try {
			Usuario usuarioSalvo = usuarioService.cadastrarNovoUsuarioComEmail(usuario);
			return new ResponseEntity(usuarioSalvo, HttpStatus.CREATED);
		} catch(RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PostMapping("/salvarNickname")
	public ResponseEntity salvarUsuarioComNickname(@RequestBody UsuarioDto usuarioDto) {
		Usuario usuario = Usuario.builder()
				.name(usuarioDto.getName())
				.email(usuarioDto.getEmail())
				.nickname(usuarioDto.getNickname())
				.password(usuarioDto.getPassword())
				.build();
		
		try {
			Usuario usuarioSalvo = usuarioService.cadastrarNovoUsuarioComNickname(usuario);
			return new ResponseEntity(usuarioSalvo, HttpStatus.CREATED);
		} catch(RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PostMapping("/autenticarEmail")
	public ResponseEntity autenticarComEmail(@RequestBody UsuarioDto usuarioDto) {
		try {
			Usuario usuarioAutenticado = usuarioService
					.autenticarUsuarioPeloEmail(usuarioDto.getEmail(), usuarioDto.getPassword());
			return new ResponseEntity(usuarioAutenticado, HttpStatus.OK);
		} catch(ErroAutenticacaoException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PostMapping("/autenticarNickname")
	public ResponseEntity autenticarComNickname(@RequestBody UsuarioDto usuarioDto) {
		try {
			Usuario usuarioAutenticado = usuarioService
					.autenticarUsuarioPeloNickname(usuarioDto.getNickname(), usuarioDto.getPassword());
			return new ResponseEntity(usuarioAutenticado, HttpStatus.OK);
		} catch(ErroAutenticacaoException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
