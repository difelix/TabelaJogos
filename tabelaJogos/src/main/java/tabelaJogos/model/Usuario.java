package tabelaJogos.model;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_user")
    private long idUser;

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Column(name = "nickname", nullable = false, length = 10)
    private String nickname;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "password", nullable = false, length = 8)
    private String password;
}
