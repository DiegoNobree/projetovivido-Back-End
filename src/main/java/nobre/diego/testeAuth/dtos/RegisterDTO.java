package nobre.diego.testeAuth.dtos;
import nobre.diego.testeAuth.domain.UserRole;

public record RegisterDTO(String name,String login, String password,Long cep, String adress,
                          Long phone) {
}
