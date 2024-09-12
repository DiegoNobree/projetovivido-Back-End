package nobre.diego.testeAuth.repositories;

import nobre.diego.testeAuth.domain.Guardians;
import nobre.diego.testeAuth.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GuardianRepository extends JpaRepository<Guardians, Long> {

    @Query("SELECT COUNT(g) FROM Guardians g WHERE g.user = :user")
    int countByUser(@Param("user")User user);

}
