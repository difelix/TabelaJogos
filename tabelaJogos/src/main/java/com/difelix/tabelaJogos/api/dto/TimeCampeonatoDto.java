package com.difelix.tabelaJogos.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TimeCampeonatoDto {
	
	private Long id;
	private Long idCampeonato;
	private Long idTime;
}
