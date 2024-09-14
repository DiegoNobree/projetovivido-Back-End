package nobre.diego.testeAuth.dtos.Users;

import nobre.diego.testeAuth.domain.UserRole;

public record LoginResponseDTO(String token, String name, UserRole role) {
}
