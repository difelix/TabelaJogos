package com.difelix.tabelaJogos.model.repository;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.difelix.tabelaJogos.model.entity.Usuario;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UsuarioRepositoryTest {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	TestEntityManager entityManager;
	
	@Test
	public void deveRealizarValidacaoEmailQueExiste() {
		// cenário
		Usuario usuario = criarUsuario();
		entityManager.persist(usuario);
		
		// execução
		boolean exists = usuarioRepository.existsByEmail("diego@email.com");
		
		// validação
		Assertions.assertThat(exists).isTrue();		
	}
	
	@Test
	public void deveRealizarValidacaoEmailQueNaoExiste() {
		boolean exists = usuarioRepository.existsByEmail("diego@email.com");
		
		Assertions.assertThat(exists).isFalse();
	}
	
	@Test
	public void realizarValidacaoNicknameQueExiste() {
		// cenário
		Usuario usuario = criarUsuario();
		entityManager.persist(usuario);
		
		// execução
		boolean exists = usuarioRepository.existsByNickname("di");
		
		// validação
		Assertions.assertThat(exists).isTrue();
	}
	
	@Test
	public void realizarValidacaoNicknameQueNaoExiste() {
		// execução
		boolean exists = usuarioRepository.existsByNickname("di");
		
		// validação
		Assertions.assertThat(exists).isFalse();
	}
	
	@Test
	public void verificarSeCadastroDeUsuarioFoiRealizado() {
		// cenario
		Usuario usuario = criarUsuario();
		
		// execucao
		Usuario usuarioFoiSalvo = usuarioRepository.save(usuario);
		
		// validacao
		Assertions.assertThat(usuarioFoiSalvo.getId()).isNotNull();		
	}
	
	@Test
	public void buscarUsuarioPorEmailExistente() {
		// cenario
		Usuario usuario = criarUsuario();
		entityManager.persist(usuario);
		
		// execucao
		Optional<Usuario> usuarioBuscado = usuarioRepository.findByEmail("diego@email.com");
		
		// validacao
		Assertions.assertThat(usuarioBuscado.isPresent()).isTrue();
	}
	
	@Test
	public void buscarUsuarioPorEmailInexistente() {
		// execucao
		Optional<Usuario> usuarioBuscado = usuarioRepository.findByEmail("diego@email.com");
		
		// validacao
		Assertions.assertThat(usuarioBuscado.isPresent()).isFalse();
	}
	
	@Test
	public void buscarUsuarioPorNicknameExistente() {
		// cenario
		Usuario usuario = criarUsuario();
		entityManager.persist(usuario);
		
		// execucao
		Optional<Usuario> usuarioBuscado = usuarioRepository.findByNickname("di");
		
		// validacao
		Assertions.assertThat(usuarioBuscado.isPresent()).isTrue();
	}
	
	@Test
	public void buscarUsuarioPorNicknameInexistente() {
		// execucao
		Optional<Usuario> usuarioBuscado = usuarioRepository.findByNickname("di");
		
		// validacao
		Assertions.assertThat(usuarioBuscado.isPresent()).isFalse();
	}
	
	public static Usuario criarUsuario() {
		return Usuario.builder().
				name("Diego").
				email("diego@email.com").
				password("teste").
				nickname("di").
				build();
	}

}
