package nobre.diego.testeAuth.dtos;

import nobre.diego.testeAuth.domain.UserRole;

import javax.management.relation.Role;

public record GetResponseDTO(Long id, String name, String login, UserRole role) {

}
