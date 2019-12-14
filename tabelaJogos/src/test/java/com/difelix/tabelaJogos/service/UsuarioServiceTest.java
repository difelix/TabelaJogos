package com.difelix.tabelaJogos.service;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.difelix.tabelaJogos.model.entity.Usuario;
import com.difelix.tabelaJogos.model.repository.UsuarioRepository;
import com.difelix.tabelaJogos.service.exception.ErroAutenticacaoException;
import com.difelix.tabelaJogos.service.exception.RegraNegocioException;
import com.difelix.tabelaJogos.service.impl.UsuarioServiceImpl;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class UsuarioServiceTest {
	
	@MockBean
	UsuarioRepository usuarioRepository;
	
	@SpyBean
	UsuarioServiceImpl usuarioService;
	
	private final String NOME = "Diego";
	private final String PASSWORD = "teste";
	private final String NICKNAME = "di";
	private final long ID = 1L;
	private final String EMAIL = "diego@email.com";
	
	@Test(expected = Test.None.class)
	public void validarEmailQueNaoExiste() {
		// cenario
		Mockito.when(usuarioRepository.existsByEmail(Mockito.anyString())).thenReturn(false);
		
		// execução
		usuarioService.validarEmail(EMAIL);	
	}
	
	@Test(expected = RegraNegocioException.class)
	public void acusarErroEmailJaCadastrado() {
		// cenario
		Mockito.when(usuarioRepository.existsByEmail(Mockito.anyString())).thenReturn(true);
		
		// execução
		usuarioService.validarEmail(EMAIL);
	}
	
	@Test(expected = Test.None.class)
	public void validarNicknameQueNaoExiste() {
		// cenario
		Mockito.when(usuarioRepository.existsByNickname(Mockito.anyString())).thenReturn(false);
		
		// execução
		usuarioService.validarNickname(NICKNAME);
	}
	
	@Test(expected = RegraNegocioException.class)
	public void acusarErroNicknameQueJaExiste() {
		// cenario
		Mockito.when(usuarioRepository.existsByNickname(Mockito.anyString())).thenReturn(true);
		
		// execucao
		usuarioService.validarNickname(NICKNAME);
	}
	
	@Test(expected = Test.None.class)
	public void autenticarUsuarioPeloEmailComSucesso() {
		// cenario
		Usuario usuario = criarUsuario(EMAIL, PASSWORD, NICKNAME, NOME, ID);
		
		// execucao
		Mockito.when(usuarioRepository.findByEmail(EMAIL)).thenReturn(Optional.of(usuario));
		usuarioService.autenticarUsuarioPeloEmail(EMAIL, PASSWORD);
	}
	
	@Test
	public void autenticarUsuarioComEmailInexistente() {
		Mockito.when(usuarioRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.empty());
		
		Throwable exception = Assertions.catchThrowable(() -> usuarioService.autenticarUsuarioPeloEmail(EMAIL, PASSWORD));
		Assertions.assertThat(exception).isInstanceOf(ErroAutenticacaoException.class).hasMessage("Email informado não foi encontrado!");
	}
	
	@Test
	public void autenticarUsuarioEmailComSenhaIncorreta() {
		Usuario usuario = criarUsuario(EMAIL, PASSWORD, NICKNAME, NOME, ID);
		Mockito.when(usuarioRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(usuario));
		
		Throwable exception = Assertions.catchThrowable(() -> usuarioService.autenticarUsuarioPeloEmail(EMAIL, "12345"));
		Assertions.assertThat(exception).isInstanceOf(ErroAutenticacaoException.class).hasMessage("Senha inválida");
	}
	
	@Test(expected = Test.None.class)
	public void autenticarUsuarioPeloNicknameComSucesso() {
		// cenario
		Usuario usuario = criarUsuario(EMAIL, PASSWORD, NICKNAME, NOME, ID);
		
		// execucao
		Mockito.when(usuarioRepository.findByNickname(NICKNAME)).thenReturn(Optional.of(usuario));
		usuarioService.autenticarUsuarioPeloNickname(NICKNAME, PASSWORD);
	}
	
	@Test
	public void autenticarUsuarioComNicknameInexistente() {
		Mockito.when(usuarioRepository.findByNickname(Mockito.anyString())).thenReturn(Optional.empty());
		
		Throwable exception = Assertions.catchThrowable(() -> usuarioService.autenticarUsuarioPeloNickname(NICKNAME, PASSWORD));
		Assertions.assertThat(exception).isInstanceOf(ErroAutenticacaoException.class).hasMessage("Nickname informado não foi encontrado!");
	}
	
	@Test
	public void autenticarUsuarioNicknameComSenhaIncorreta() {
		Usuario usuario = criarUsuario(EMAIL, PASSWORD, NICKNAME, NOME, ID);
		Mockito.when(usuarioRepository.findByNickname(Mockito.anyString())).thenReturn(Optional.of(usuario));
		
		Throwable exception = Assertions.catchThrowable(() -> usuarioService.autenticarUsuarioPeloNickname(NICKNAME, "12345"));
		Assertions.assertThat(exception).isInstanceOf(ErroAutenticacaoException.class).hasMessage("Senha inválida");
	}
	
	@Test(expected = Test.None.class)
	public void salvarUsuarioComEmailSucesso() {
		Usuario usuarioASerSalvo = criarUsuario(EMAIL, PASSWORD, NICKNAME, NOME, ID);
		
		Mockito.doNothing().when(usuarioService).validarEmail(Mockito.anyString());
		Mockito.when(usuarioRepository.save(Mockito.any(Usuario.class))).thenReturn(usuarioASerSalvo);
		
		Usuario usuarioSalvo = usuarioService.cadastrarNovoUsuarioComEmail(usuarioASerSalvo);
		
		Assertions.assertThat(usuarioSalvo).isNotNull();
		Assertions.assertThat(usuarioSalvo.getId()).isEqualTo(usuarioASerSalvo.getId());
		Assertions.assertThat(usuarioSalvo.getName()).isEqualTo(usuarioASerSalvo.getName());
		Assertions.assertThat(usuarioSalvo.getEmail()).isEqualTo(usuarioASerSalvo.getEmail());
		Assertions.assertThat(usuarioSalvo.getPassword()).isEqualTo(usuarioASerSalvo.getPassword());
		Assertions.assertThat(usuarioSalvo.getNickname()).isEqualTo(usuarioASerSalvo.getNickname());
	}
	
	@Test(expected = Test.None.class)
	public void salvarUsuarioComNicknameSucesso() {
		Usuario usuarioASerSalvo = criarUsuario(EMAIL, PASSWORD, NICKNAME, NOME, ID);
		
		Mockito.doNothing().when(usuarioService).validarNickname(Mockito.anyString());
		Mockito.when(usuarioRepository.save(Mockito.any(Usuario.class))).thenReturn(usuarioASerSalvo);
		
		Usuario usuarioSalvo = usuarioService.cadastrarNovoUsuarioComNickname(usuarioASerSalvo);
		
		Assertions.assertThat(usuarioSalvo).isNotNull();
		Assertions.assertThat(usuarioSalvo.getId()).isEqualTo(usuarioASerSalvo.getId());
		Assertions.assertThat(usuarioSalvo.getName()).isEqualTo(usuarioASerSalvo.getName());
		Assertions.assertThat(usuarioSalvo.getEmail()).isEqualTo(usuarioASerSalvo.getEmail());
		Assertions.assertThat(usuarioSalvo.getPassword()).isEqualTo(usuarioASerSalvo.getPassword());
		Assertions.assertThat(usuarioSalvo.getNickname()).isEqualTo(usuarioASerSalvo.getNickname());
	}
	
	@Test(expected = RegraNegocioException.class)
	public void naoSalvarUsuarioComEmailErrado() {
		Usuario usuario = Usuario.builder().email(EMAIL).build();
		Mockito.doThrow(RegraNegocioException.class).when(usuarioService).validarEmail(EMAIL);
		
		usuarioService.cadastrarNovoUsuarioComEmail(usuario);
		
		Mockito.verify(usuarioRepository, Mockito.never()).save(usuario);
	}
	
	@Test(expected = RegraNegocioException.class)
	public void naoSalvarUsuarioComNicknameErrado() {
		Usuario usuario = Usuario.builder().nickname(NICKNAME).build();
		Mockito.doThrow(RegraNegocioException.class).when(usuarioService).validarNickname(NICKNAME);
		
		usuarioService.cadastrarNovoUsuarioComNickname(usuario);
		
		Mockito.verify(usuarioRepository, Mockito.never()).save(usuario);
	}
	
	public static Usuario criarUsuario(String email, String password, String nickname, String name, long id) {
		return Usuario.builder().
				name(name).
				email(email).
				password(password).
				nickname(nickname).
				id(id).
				build();
	}

}
