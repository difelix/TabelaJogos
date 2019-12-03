package com.difelix.tabelaJogos.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "timeCampeonato")
@Data
@Builder
public class TimeCampeonato {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToOne
	@JoinColumn(name = "id_campeonato")
	private Campeonato campeonato;
	
	@OneToOne
	@JoinColumn(name = "id_time")
	private Times times;
}
