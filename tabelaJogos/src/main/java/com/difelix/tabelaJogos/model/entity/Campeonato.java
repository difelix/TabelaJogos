package com.difelix.tabelaJogos.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.difelix.tabelaJogos.model.enums.TipoCampeonato;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "campeonato", schema = "tabelajogos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Campeonato {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "organizador")
	private String organizador;
	
	@Column(name = "temporada")
	private String temporada;
	
	@Column(name = "qntde_times")
	private Integer qntdeTimes;
	
	@Column(name = "returno")
	@Enumerated(EnumType.STRING)
	private TipoCampeonato returno;
	
	@Column(name = "descricao")
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

}
