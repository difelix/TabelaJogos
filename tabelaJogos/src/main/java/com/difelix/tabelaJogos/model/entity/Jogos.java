package com.difelix.tabelaJogos.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "jogos", schema = "tabelajogos")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Jogos {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "id_adversario")
	private Long idAdversario;
	
	@Column(name = "rodada")
	private Integer rodada;
	
	@Column(name = "qntde_gols_time")
	private Integer qntdeGolsTime;
	
	@Column(name = "qntde_gols_adversario")
	private Integer qntdeGolsAdversario;
	
	@Column(name = "dataJogo")
	private String dataJogo;
	
	@Column(name = "localJogo")
	private String localJogo;
	
	@Column(name = "descricao")
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name = "id_time")
	private Times times;
	
	@ManyToOne
	@JoinColumn(name = "id_campeonato")
	private Campeonato campeonato;
}
