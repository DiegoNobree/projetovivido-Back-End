package nobre.diego.testeAuth.repositories;

import nobre.diego.testeAuth.domain.EmployeeType;
import nobre.diego.testeAuth.domain.Guidance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GuidanceRepository extends JpaRepository<Guidance, Long> {
    @Query("SELECT a FROM Guidance a WHERE a.type = :type")
    List<Guidance> listGuidance(@Param("type")EmployeeType employeeType);
}
