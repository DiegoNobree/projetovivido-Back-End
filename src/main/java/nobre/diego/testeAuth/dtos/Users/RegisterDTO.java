package nobre.diego.testeAuth.dtos.Users;

public record RegisterDTO(String name,String login, String password,Long cep, String adress,
                          Long phone) {
}
