package com.difelix.tabelaJogos.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.difelix.tabelaJogos.model.entity.Usuario;
import com.difelix.tabelaJogos.model.repository.UsuarioRepository;
import com.difelix.tabelaJogos.service.exception.RegraNegocioException;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class UsuarioServiceTest {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	UsuarioService usuarioService;
	
	@Test(expected = Test.None.class)
	public void validarEmailQueNaoExiste() {
		// cenario
		usuarioRepository.deleteAll();
		
		// execução
		usuarioService.validarEmail("diego@email.com");
		
	}
	
	@Test(expected = RegraNegocioException.class)
	public void acusarErroEmailJaCadastrado() {
		// cenario
		Usuario usuario = Usuario.builder().name("Diego").nickname("di").password("teste").
				email("diego@email.com").build();
		usuarioRepository.save(usuario);
		
		// execução
		usuarioService.validarEmail("diego@email.com");
	}
	
	@Test(expected = Test.None.class)
	public void validarNicknameQueNaoExiste() {
		// cenário
		usuarioRepository.deleteAll();
		
		// execução
		usuarioService.validarNickname("di");
	}
	
	@Test(expected = RegraNegocioException.class)
	public void acusarErroNicknameQueJaExiste() {
		// cenario
		Usuario usuario = Usuario.builder().name("Diego").nickname("di").email("diego@email.com").
				password("teste").build();
		usuarioRepository.save(usuario);
		
		// execucao
		usuarioService.validarNickname("di");
	}

}
