package com.difelix.tabelaJogos.model.repository;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.difelix.tabelaJogos.model.entity.Usuario;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class UsuarioRepositoryTest {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Test
	public void deveRealizarValidacaoEmailQueExiste() {
		// cenário
		Usuario usuario = Usuario.builder().name("Diego").email("diego@email.com").
				nickname("di").password("teste").build();
		usuarioRepository.save(usuario);
		
		// execução
		boolean exists = usuarioRepository.existsByEmail("diego@email.com");
		
		// validação
		Assertions.assertThat(exists).isTrue();		
	}
	
	@Test
	public void deveRealizarValidacaoEmailQueNaoExiste() {
		usuarioRepository.deleteAll();
		
		boolean exists = usuarioRepository.existsByEmail("diego@email.com");
		
		Assertions.assertThat(exists).isFalse();
	}
	
	@Test
	public void realizarValidacaoNicknameQueExiste() {
		// cenário
		Usuario usuario = Usuario.builder().name("Diego").nickname("di").email("diego@email.com").
				password("teste").build();
		usuarioRepository.save(usuario);
		
		// execução
		boolean exists = usuarioRepository.existsByNickname("di");
		
		// validação
		Assertions.assertThat(exists).isTrue();
	}
	
	@Test
	public void realizarValidacaoNicknameQueNaoExiste() {
		// cenario
		usuarioRepository.deleteAll();
		
		// execução
		boolean exists = usuarioRepository.existsByNickname("di");
		
		// validação
		Assertions.assertThat(exists).isFalse();
	}

}
