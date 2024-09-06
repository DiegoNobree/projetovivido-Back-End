package nobre.diego.testeAuth.dtos;
import nobre.diego.testeAuth.domain.UserRole;

public record RegisterDTO(Long cpf,String name,String login, String password, UserRole role) {
}
