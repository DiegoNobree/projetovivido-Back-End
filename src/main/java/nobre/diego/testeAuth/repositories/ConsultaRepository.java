package nobre.diego.testeAuth.repositories;

import nobre.diego.testeAuth.domain.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
}
