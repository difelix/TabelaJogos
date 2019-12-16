package com.difelix.tabelaJogos.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.difelix.tabelaJogos.model.enums.JogadorPosicao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "jogador", schema = "tabelajogos")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Jogador {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "nascimento")
	private String nascimento;
	
	@Column(name = "nacionalidade")
	private String nacionalidade;
	
	@Column(name = "posicao")
	@Enumerated(EnumType.STRING)
	private JogadorPosicao posicao;
	
	@Column(name = "descricao")
	private String descricao;
	
	@OneToOne
	@JoinColumn(name = "id_time")
	private Times times;

}
