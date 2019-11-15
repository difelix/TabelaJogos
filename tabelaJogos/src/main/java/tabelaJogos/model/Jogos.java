package tabelaJogos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "jogos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Jogos {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_jogo")
    private long idJogo;

    @Column(name = "id_adversario", nullable = false)
    private long idAdversario;

    @Column(name = "rodada", nullable = false)
    private int rodada;

    @Column(name = "qntde_gols_time", nullable = false)
    private int qntdeGolsTime;

    @Column(name = "qntde_gols_adversario", nullable = false)
    private int qntdeGolsAdversario;

    @Column(name = "dataJogo", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataJogo;

    @Column(name = "localJogo", nullable = false, length = 50)
    private String localJogo;

    @Column(name = "descricao", length = 300)
    private String descricao;

    @JoinColumn(name = "id_time")
    @ManyToMany
    private Times times;

    @JoinColumn(name = "id_campeonato")
    @OneToMany
    private Campeonato campeonato;
}
