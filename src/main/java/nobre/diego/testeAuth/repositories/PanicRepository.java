package nobre.diego.testeAuth.repositories;

import nobre.diego.testeAuth.domain.PanicButton;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PanicRepository extends JpaRepository<PanicButton, Long> {
}
