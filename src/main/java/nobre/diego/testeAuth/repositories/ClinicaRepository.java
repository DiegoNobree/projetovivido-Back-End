package nobre.diego.testeAuth.repositories;

import nobre.diego.testeAuth.domain.Clinica;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClinicaRepository extends JpaRepository<Clinica, Long> {
}
