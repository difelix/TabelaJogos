package tabelaJogos.model;

import javax.persistence.*;

@Entity
@Table(name = "campeonato")
public class Campeonato {

    @Id
    @GeneratedValue
    @Column(name = "id_campeonato")
    private long idCampeonato;

    @Column(name = "nome")
    private String nome;

    @Column(name = "organizador")
    private String organizador;

    @Column(name = "temporada")
    private String temporada;

    @Column(name = "qntde_times")
    private int qntdeTimes;

    @Column(name = "returno")
    private boolean returno;

    @Column(name = "descricao")
    private String descricao;

    public Campeonato() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
