package com.difelix.tabelaJogos.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JogadorDto {
	
	private Long id;
	private String nome;
	private String nascimento;
	private String nacionalidade;
	private String posicao;
	private String descricao;
	private Long idTime;
}
