package com.difelix.tabelaJogos.model.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "jogador")
@Data
@Builder
public class Jogador {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "nascimento")
	@Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
	private LocalDate nascimento;
	
	@Column(name = "nacionalidade")
	private String nacionalidade;
	
	@Column(name = "posicao")
	private String posicao;
	
	@Column(name = "descricao")
	private String descricao;
	
	@OneToOne
	@JoinColumn(name = "id_time")
	private Times times;

}
