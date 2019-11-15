package tabelaJogos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name = "campeonato")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Campeonato {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_campeonato")
    private long idCampeonato;

    @Column(name = "nome", nullable = false, length = 20)
    private String nome;

    @Column(name = "organizador", length = 30)
    private String organizador;

    @Column(name = "temporada", nullable = false, length = 20)
    private String temporada;

    @Column(name = "qntde_times", nullable = false)
    private int qntdeTimes;

    @Column(name = "returno", nullable = false)
    private boolean returno;

    @Column(name = "descricao", length = 300)
    private String descricao;

    @OneToMany
    @JoinColumn(name = "id_user")
    private Usuario usuario;
}
