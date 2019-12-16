package com.difelix.tabelaJogos.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CampeonatoDto {
	
	private Long id;
	private String nome;
	private String organizador;
	private String temporada;
	private Integer qntdeTimes;
	private String returno;
	private String descricao;
	private Long idUsuario;
}
