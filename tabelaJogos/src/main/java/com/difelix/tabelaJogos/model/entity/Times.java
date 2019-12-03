package com.difelix.tabelaJogos.model.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import lombok.Builder;
import lombok.Data;


@Entity
@Table(name = "times")
@Data
@Builder
public class Times {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "data_criacao")
	@Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
	private LocalDate dataCriacao;
	
	@Column(name = "cidade_sede")
	private String cidadeSede;
	
	@Column(name = "federacao_afiliado")
	private String federacaoAfiliado;
	
	@Column(name = "estadio")
	private String estadio;
	
	@Column(name = "descricao")
	private String descricao;
	
}
