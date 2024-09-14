package nobre.diego.testeAuth.dtos.Guidance;

import nobre.diego.testeAuth.domain.EmployeeType;

public record CreateGuidanceDTO(String descricao, EmployeeType type) {
}
