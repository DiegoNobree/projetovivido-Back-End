package nobre.diego.testeAuth.dtos.Users;

import nobre.diego.testeAuth.domain.UserRole;

public record GetDataUserDTO(Long id, String name, String login, String pass, String adress, Long phone, Long cep, UserRole role) {
}
