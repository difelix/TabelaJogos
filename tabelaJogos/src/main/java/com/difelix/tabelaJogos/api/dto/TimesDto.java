package com.difelix.tabelaJogos.api.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TimesDto {
	
	private String nome;
	private String cidadeSede;
	private String federacaoAfiliado;
	private String estadio;
	private String descricao;
	private String dataCriacao;
}
