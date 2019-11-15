package tabelaJogos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "jogador")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Jogador {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_jogador")
    private long idJogador;

    @Column(name = "nome", nullable = false, length = 30)
    private String nome;

    @Column(name = "nascimento", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date nascimento;

    @Column(name = "nacionalidade", nullable = false, length = 30)
    private String nacionalidade;

    @Column(name = "posicao", nullable = false, length = 15)
    private String posicao;

    @Column(name = "descricao", length = 300)
    private String descricao;

    @JoinColumn(name = "id_time")
    @ManyToOne
    private Times times;
}
