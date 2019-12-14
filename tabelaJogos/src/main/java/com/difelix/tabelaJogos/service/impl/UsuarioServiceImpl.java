package com.difelix.tabelaJogos.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.difelix.tabelaJogos.model.entity.Usuario;
import com.difelix.tabelaJogos.model.repository.UsuarioRepository;
import com.difelix.tabelaJogos.service.UsuarioService;
import com.difelix.tabelaJogos.service.exception.ErroAutenticacaoException;
import com.difelix.tabelaJogos.service.exception.RegraNegocioException;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	private UsuarioRepository usuarioRepository;

	@Autowired
	public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
		super();
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public Usuario autenticarUsuarioPeloEmail(String email, String password) {
		Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
		
		if (!usuario.isPresent()) {
			throw new ErroAutenticacaoException("Email informado não foi encontrado!");
		}
		
		if (!usuario.get().getPassword().equals(password)) {
			throw new ErroAutenticacaoException("Senha inválida");
		}
		
		return usuario.get();
	}
	
	@Override
	public Usuario autenticarUsuarioPeloNickname(String nickname, String password) {
		Optional<Usuario> usuario = usuarioRepository.findByNickname(nickname);
		
		if (!usuario.isPresent()) {
			throw new ErroAutenticacaoException("Nickname informado não foi encontrado!");
		}
		
		if (!usuario.get().getPassword().equals(password)) {
			throw new ErroAutenticacaoException("Senha inválida");
		}
		
		return usuario.get();
	}

	@Override
	public void validarEmail(String email) {
		boolean existsEmail = usuarioRepository.existsByEmail(email);
		if (existsEmail) {
			throw new RegraNegocioException("Email informado já foi cadastrado no sistema.");
		}
		
	}

	@Override
	public void validarNickname(String nickname) {
		boolean exists = usuarioRepository.existsByNickname(nickname);
		if (exists) {
			throw new RegraNegocioException("Nickname informado já foi utilizado no sistema");
		}
		
	}
	
	@Override
	@Transactional
	public Usuario cadastrarNovoUsuarioComEmail(Usuario usuario) {
		if (usuario.getPassword().length() > 8) {
			throw new RegraNegocioException("Senha não pode conter mais que 8 caracteres");
		}
		
		if (usuario.getNickname().length() > 10) {
			throw new RegraNegocioException("Nickname não pode conter mais que 10 caracteres");
		}
		
		validarEmail(usuario.getEmail());
		
		return usuarioRepository.save(usuario);
	}

	@Override
	public Usuario cadastrarNovoUsuarioComNickname(Usuario usuario) {
		if (usuario.getPassword().length() > 8) {
			throw new RegraNegocioException("Senha não pode conter mais que 8 caracteres");
		}
		
		if (usuario.getNickname().length() > 10) {
			throw new RegraNegocioException("Nickname não pode conter mais que 10 caracteres");
		}
		
		validarNickname(usuario.getNickname());
		
		return usuarioRepository.save(usuario);
	}

}
