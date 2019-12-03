package com.difelix.tabelaJogos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.difelix.tabelaJogos.model.entity.Usuario;
import com.difelix.tabelaJogos.model.repository.UsuarioRepository;
import com.difelix.tabelaJogos.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	private UsuarioRepository usuarioRepository;

	@Autowired
	public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
		super();
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public Usuario autenticarUsuario(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario cadastrarNovoUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void validarEmail(String email) {
		// TODO Auto-generated method stub
		
	}

}
