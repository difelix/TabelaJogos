CREATE USER adminTabelaJogos with encrypted password 'gol';

-- criação do banco de dados tabelaJogos
CREATE DATABASE tabelaJogos OWNER adminTabelaJogos;
GRANT ALL PRIVILEGES ON DATABASE tabelaJogos TO adminTabelaJogos;

USE tabelaJogos;

CREATE SCHEMA tabelaJogos;

-- guarda dados relacionados ao usuário do sistema
CREATE TABLE tabelaJogos.usuario (
   id bigserial PRIMARY KEY,
   name VARCHAR(30) NOT NULL,
   nickname VARCHAR(10) NOT NULL, -- nome fantasia do usuario
   email VARCHAR(50) NOT NULL,
   password VARCHAR(8) NOT NULL,
   data_criacao date DEFAULT NOW()
);

CREATE UNIQUE INDEX id_user_idx ON tabelaJogos.usuario(id);

CREATE TABLE tabelaJogos.campeonato (
   id bigserial PRIMARY KEY,
   nome VARCHAR(20) NOT NULL,
   organizador VARCHAR(30), -- nome do organizador ou da associação que organiza o campeonato
   temporada VARCHAR(20) NOT NULL, -- ano da realização do campeonato. Ex: 2019/2020
   qntde_times integer NOT NULL,
   returno VARCHAR(20) CHECK (returno IN ('TURNO', 'RETURNO')) NOT NULL, -- define se o campeonato será de turno unico(false) ou de turno e returno(true)
   descricao VARCHAR(100),
   id_usuario bigint references tabelaJogos.usuario (id) -- FK de usuário
);

CREATE UNIQUE INDEX id_campeonato_idx ON tabelaJogos.campeonato(id);

CREATE TABLE tabelaJogos.times (
   id bigserial PRIMARY KEY,
   nome VARCHAR(30) NOT NULL,
   data_criacao date NOT NULL,
   cidade_sede VARCHAR(20) NOT NULL, -- cidade onde time atua e manda seus jogos
   federacao_afiliado VARCHAR(20) NOT NULL, -- federação a qual o time está filiado. Ex: Federação Paulista de Futebol
   estadio VARCHAR(50), -- nome do estádio onde time joga
   descricao VARCHAR(100)
);

CREATE UNIQUE INDEX id_time_idx ON tabelaJogos.times(id);

CREATE TABLE tabelaJogos.timeCampeonato (
   id bigserial PRIMARY KEY,
   id_campeonato bigint references tabelaJogos.campeonato (id),
   id_time bigint references tabelaJogos.times (id)
);

CREATE UNIQUE INDEX id_timecampeonato_idx ON tabelaJogos.timeCampeonato(id);

CREATE TABLE tabelaJogos.jogador (
   id bigserial PRIMARY KEY,
   nome VARCHAR(30) NOT NULL,
   nascimento date NOT NULL,
   nacionalidade VARCHAR(30) NOT NULL,
   posicao VARCHAR(15) NOT NULL, -- posição aonde o jogador atua em campo. Ex: atacante, meio campo, zagueiro, etc
   descricao VARCHAR(100),
   id_time bigint references tabelaJogos.times (id)
);

CREATE UNIQUE INDEX id_jogador_idx ON tabelaJogos.jogador(id);

CREATE TABLE tabelaJogos.jogos (
   id bigserial PRIMARY KEY,
   id_adversario bigint NOT NULL, -- id do time visitante que enfrenta o time da casa
   rodada integer NOT NULL, -- número da rodada do campeonato
   qntde_gols_time integer NOT NULL,
   qntde_gols_adversario integer NOT NULL,
   dataJogo date NOT NULL, -- data da realização do jogo
   localJogo VARCHAR(50) NOT NULL, -- local onde o jogo foi realizado
   descricao VARCHAR(100),
   id_time bigint references tabelaJogos.times (id), -- id do time mandante do jogo
   id_campeonato bigint references tabelaJogos.campeonato (id) -- id do campeonato  ao qual pertence o jogo
);

CREATE UNIQUE INDEX id_jogo_idx ON tabelaJogos.jogos(id);
   