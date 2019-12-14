package com.difelix.tabelaJogos.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "times")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Times {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "data_criacao")	
	private Date dataCriacao;
	
	@Column(name = "cidade_sede")
	private String cidadeSede;
	
	@Column(name = "federacao_afiliado")
	private String federacaoAfiliado;
	
	@Column(name = "estadio")
	private String estadio;
	
	@Column(name = "descricao")
	private String descricao;
	
}
