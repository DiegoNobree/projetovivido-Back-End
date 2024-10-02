package nobre.diego.testeAuth.controllers;

import jakarta.validation.Valid;
import nobre.diego.testeAuth.domain.Guardians;
import nobre.diego.testeAuth.domain.User;
import nobre.diego.testeAuth.dtos.Guardians.CreateGuardianDTO;
import nobre.diego.testeAuth.dtos.Users.EditResponseDTO;
import nobre.diego.testeAuth.dtos.Guardians.GetResponseGuardiansDTO;
import nobre.diego.testeAuth.repositories.GuardianRepository;
import nobre.diego.testeAuth.services.GuardianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/guardian")
public class GuardianController {


    @Autowired
    private GuardianService guardianService;

    @Autowired
    private GuardianRepository guardianRepository;

    @PostMapping("/create")
    public ResponseEntity createGuardian (@RequestBody @Valid CreateGuardianDTO dto, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        if (dto.phone() == null) {
            return ResponseEntity.badRequest().body("Informe um telefone");
        }
        if (dto.name() == null || dto.name().isEmpty()) {
            return ResponseEntity.badRequest().body("Informe um nome");
        }
        guardianService.createGuardian(user, dto);
        return ResponseEntity.ok().body("Guardião salvo com sucesso");
    }

    @GetMapping("/get-guardians")
    public List<GetResponseGuardiansDTO> getGuardians (Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        List<Guardians> guardiansDTOS = guardianRepository.getGuardians(user);
        if (guardiansDTOS.isEmpty()) {
            throw new RuntimeException("Nenhum Guardião para esse usuário");
        }
        return guardiansDTOS.stream()
                .map(guardians -> new GetResponseGuardiansDTO(
                        guardians.getId(),
                        guardians.getName(),
                        guardians.getPhoneNumber()
                ))
                .collect(Collectors.toList());
    }

    @PutMapping("/edit/guardian")
    public List<GetResponseGuardiansDTO> editGuardian (@RequestBody EditResponseDTO dto) {
        return guardianService.editGuardian(dto);
    }

    @DeleteMapping("/delete/guardian")
    public ResponseEntity deleteGuardian (@RequestBody EditResponseDTO dto) {
        Optional<Guardians> guardiansOptional = guardianRepository.findById(dto.id());
        if (guardiansOptional.isPresent()) {
            Guardians guardian = guardiansOptional.get();
            guardianRepository.delete(guardian);
        }
        return ResponseEntity.ok().body("Guardião deletado com sucesso");
    }
}
