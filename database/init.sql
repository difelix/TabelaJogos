CREATE USER adminTabelaJogos with encrypted password 'gol';

-- criação do banco de dados tabelaJogos
CREATE DATABASE tabelaJogos OWNER adminTabelaJogos;
GRANT ALL PRIVILEGES ON DATABASE tabelaJogos TO adminTabelaJogos;

USE tabelaJogos;

-- guarda dados relacionados ao usuário do sistema
CREATE TABLE usuario (
   id_user bigserial PRIMARY KEY,
   name VARCHAR(30) NOT NULL,
   nickname VARCHAR(10) NOT NULL, -- nome fantasia do usuario
   email VARCHAR(50) NOT NULL,
   password VARCHAR(8) NOT NULL
);

CREATE UNIQUE INDEX id_user_idx ON usuario(id_user);

CREATE TABLE campeonato (
   id_campeonato bigserial PRIMARY KEY,
   nome VARCHAR(20) NOT NULL,
   organizador VARCHAR(30), -- nome do organizador ou da associação que organiza o campeonato
   temporada VARCHAR(20) NOT NULL, -- ano da realização do campeonato. Ex: 2019/2020
   qntde_times integer NOT NULL,
   returno boolean NOT NULL, -- define se o campeonato será de turno unico(false) ou de turno e returno(true)
   descricao VARCHAR(100),
   id_user bigint references usuario -- FK de usuário
);

CREATE UNIQUE INDEX id_campeonato_idx ON campeonato(id_campeonato);

CREATE TABLE times (
   id_time bigserial PRIMARY KEY,
   nome VARCHAR(30) NOT NULL,
   data_criacao date NOT NULL,
   cidade_sede VARCHAR(20) NOT NULL, -- cidade onde time atua e manda seus jogos
   federacao_afiliado VARCHAR(20) NOT NULL, -- federação a qual o time está filiado. Ex: Federação Paulista de Futebol
   estadio VARCHAR(50), -- nome do estádio onde time joga
   descricao VARCHAR(100)
);

CREATE UNIQUE INDEX id_time_idx ON times(id_time);

CREATE TABLE timeCampeonato (
   id_timecampeonato bigserial PRIMARY KEY,
   id_campeonato bigint references campeonato,
   id_time bigint references times
);

CREATE UNIQUE INDEX id_timecampeonato_idx ON timeCampeonato(id_timecampeonato);

CREATE TABLE jogador (
   id_jogador bigserial PRIMARY KEY,
   nome VARCHAR(30) NOT NULL,
   nascimento date NOT NULL,
   nacionalidade VARCHAR(30) NOT NULL,
   posicao VARCHAR(15) NOT NULL, -- posição aonde o jogador atua em campo. Ex: atacante, meio campo, zagueiro, etc
   descricao VARCHAR(100),
   id_time bigint references times
);

CREATE UNIQUE INDEX id_jogador_idx ON jogador(id_jogador);

CREATE TABLE jogos (
   id_jogo bigserial PRIMARY KEY,
   id_adversario bigint NOT NULL, -- id do time visitante que enfrenta o time da casa
   rodada integer NOT NULL, -- número da rodada do campeonato
   qntde_gols_time integer NOT NULL,
   qntde_gols_adversario integer NOT NULL,
   dataJogo date NOT NULL, -- data da realização do jogo
   localJogo VARCHAR(50) NOT NULL, -- local onde o jogo foi realizado
   descricao VARCHAR(100),
   id_time bigint references times, -- id do time mandante do jogo
   id_campeonato bigint references campeonato -- id do campeonato  ao qual pertence o jogo
);

CREATE UNIQUE INDEX id_jogo_idx ON jogos(id_jogo);
   