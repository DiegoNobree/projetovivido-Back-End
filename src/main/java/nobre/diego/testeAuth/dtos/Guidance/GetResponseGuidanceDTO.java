package nobre.diego.testeAuth.dtos.Guidance;

import nobre.diego.testeAuth.domain.EmployeeType;

public record GetResponseGuidanceDTO(Long id, String name, String adress, Long phone, String decricao,String nameFun, EmployeeType type) {

}
