package nobre.diego.testeAuth.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
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
    @JoinColumn(name = "users_id")
    @NotNull
    private User users;

    @NotNull
    private String descricao;

    @NotNull
    @Column(name = "new_timestamp")
    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "user_funcionario_id")
    private User userFuncionario;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private EmployeeType type;

    @Column(name = "view_boolean")
    private Boolean view = false;

    @Column(name = "string_view")
    private String stringView;

    @Column(name = "string_title")
    private String stringTitle;
}
