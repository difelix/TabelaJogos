package com.difelix.tabelaJogos.service;

import com.difelix.tabelaJogos.model.entity.Usuario;

public interface UsuarioService {
	
	Usuario autenticarUsuario(String email, String password);
	
	Usuario cadastrarNovoUsuario(Usuario usuario);
	
	void validarEmail(String email);
	
	void validarNickname(String nickname);

}
