package nobre.diego.testeAuth.dtos.Guidance;

import nobre.diego.testeAuth.domain.EmployeeType;

import java.time.LocalDateTime;
import java.time.LocalTime;

public record GetResponseGuidanceDTO(Long id, String name, String adress, Long phone,String title, String decricao, String nameFun, EmployeeType type, LocalDateTime time, String callback) {

}
