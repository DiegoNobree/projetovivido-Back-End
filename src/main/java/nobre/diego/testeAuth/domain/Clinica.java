package nobre.diego.testeAuth.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "clinica")
@Table(name = "clinica")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Clinica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String descricao;

    public Clinica(String name, String descricao) {
    }
}
