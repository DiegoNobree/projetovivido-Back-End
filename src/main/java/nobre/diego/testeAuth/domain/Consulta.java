package nobre.diego.testeAuth.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "consulta")
@Table(name = "consulta")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Date diaDaConsulta;

    @NotNull
    @OneToMany(mappedBy = "login")
    private List<User> usuarioProfessor;

    @NotNull
    @OneToOne
    private User usuarioEstagiario;

    @NotNull
    private Date horaInicio;

    @NotNull
    private Date horaFim;

    @NotNull
    @ManyToOne
    private Clinica clinica;

    @OneToMany(mappedBy = "login")
    private List<User> usuarioPaciente;

    private String info;
}
