package nobre.diego.testeAuth.dtos.Users;

public record EditUserDTO(String name,String oldpass, String password, String passcompare,Long cep, String adress,
                          Long phone) {}
