package com.difelix.tabelaJogos.model.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "jogos")
@Data
@Builder
public class Jogos {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "id_adversario")
	private long idAdversario;
	
	@Column(name = "rodada")
	private int rodada;
	
	@Column(name = "qntde_gols_time")
	private int qntdeGolsTime;
	
	@Column(name = "qntde_gols_adversario")
	private int qntdeGolsAdversario;
	
	@Column(name = "dataJogo")
	private LocalDate dataJogo;
	
	@Column(name = "localJogo")
	@Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
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
