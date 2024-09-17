package nobre.diego.testeAuth.dtos.Users;

public record GetDataUserDTO(Long id, String name, String login, String pass,String adress, Long phone, Long cep) {
}
