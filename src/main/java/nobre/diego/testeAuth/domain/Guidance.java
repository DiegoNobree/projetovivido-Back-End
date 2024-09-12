package nobre.diego.testeAuth.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Guidance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "users")
    @NotNull
    private User users;

    @NotNull
    private String descricao;

    @NotNull
    private LocalTime timestamp;

    @ManyToOne
    @JoinColumn(name = "user_funcionario")
    private User userFuncionario;

    private EmployeeType type;
}
