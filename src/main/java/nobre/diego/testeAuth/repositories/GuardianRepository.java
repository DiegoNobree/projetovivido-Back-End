package nobre.diego.testeAuth.repositories;

import nobre.diego.testeAuth.domain.Guardians;
import nobre.diego.testeAuth.domain.User;
import nobre.diego.testeAuth.dtos.GetResponseGuardiansDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GuardianRepository extends JpaRepository<Guardians, Long> {

    @Query("SELECT COUNT(g) FROM Guardians g WHERE g.user = :user")
    int countByUser(@Param("user")User user);

    @Query("SELECT a FROM Guardians a WHERE a.user = :user")
    List<Guardians> getGuardians(@Param("user") User user);

}
