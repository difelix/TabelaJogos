package tabelaJogos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "times")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Times {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_time")
    private long idTime;

    @Column(name = "nome", nullable = false, length = 30)
    private String nome;

    @Column(name = "data_criacao", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataCriacao;

    @Column(name = "cidade_sede", nullable = false, length = 20)
    private String cidadeSede;

    @Column(name = "federacao_afiliado", nullable = false, length = 20)
    private String federacaoAfiliado;

    @Column(name = "estadio", length = 50)
    private String estadio;

    @Column(name = "descricao", length = 300)
    private String descricao;
}
