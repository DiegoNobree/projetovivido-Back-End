package nobre.diego.testeAuth.dtos;

import nobre.diego.testeAuth.domain.User;
import nobre.diego.testeAuth.domain.UserRole;

public record LoginResponseDTO(String token, String name, UserRole role) {
}
