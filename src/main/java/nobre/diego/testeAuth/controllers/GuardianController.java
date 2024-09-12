package nobre.diego.testeAuth.controllers;

import jakarta.validation.Valid;
import nobre.diego.testeAuth.domain.User;
import nobre.diego.testeAuth.dtos.CreateGuardianDTO;
import nobre.diego.testeAuth.services.GuardianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/guardian")
public class GuardianController {


    @Autowired
    private GuardianService guardianService;

    @PostMapping("/create")
    public ResponseEntity createGuardian (@RequestBody @Valid CreateGuardianDTO dto, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        guardianService.createGuardian(user, dto);
        return ResponseEntity.ok().body("Guardião salvo com sucesso");
    }
}
