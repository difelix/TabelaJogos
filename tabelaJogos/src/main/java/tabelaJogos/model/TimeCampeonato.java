package tabelaJogos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name = "timeCampeonato")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeCampeonato {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_timecampeonato")
    private long idTimeCampeonato;

    @JoinColumn(name = "id_campeonato")
    @OneToOne
    private Campeonato campeonato;

    @JoinColumn(name = "id_time")
    @OneToOne
    private Times times;
}
