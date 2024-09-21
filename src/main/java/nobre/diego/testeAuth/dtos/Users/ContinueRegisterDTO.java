package nobre.diego.testeAuth.dtos.Users;

public record ContinueRegisterDTO(Long cep, Long phone, String adress,
                                  String rua, String bairro, String cidade, String estado,
                                  String comple) {
}
