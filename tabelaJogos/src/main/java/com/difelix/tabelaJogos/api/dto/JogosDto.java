package com.difelix.tabelaJogos.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JogosDto {
	
	private Long id;
	private Long idAdversario;
	private Integer rodada;
	private Integer qntdeGolsTime;
	private Integer qntdeGolsAdversario;
	private String dataJogo;
	private String localJogo;
	private String descricao;
	private Long idTime;
	private Long idCampeonato;
}
