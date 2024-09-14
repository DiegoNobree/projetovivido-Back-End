package nobre.diego.testeAuth.dtos.Users;

import nobre.diego.testeAuth.domain.UserRole;

public record GetResponseDTO(Long id, String name, String login, UserRole role) {

}
