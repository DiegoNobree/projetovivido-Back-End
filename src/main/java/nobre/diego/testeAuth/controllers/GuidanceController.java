package nobre.diego.testeAuth.controllers;

import nobre.diego.testeAuth.domain.EmployeeType;
import nobre.diego.testeAuth.domain.User;
import nobre.diego.testeAuth.dtos.Guidance.CreateGuidanceDTO;
import nobre.diego.testeAuth.dtos.Guidance.GetResponseGuidanceDTO;
import nobre.diego.testeAuth.services.GuidanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/guidance")
public class GuidanceController {

    @Autowired
    private GuidanceService guidanceService;

    @GetMapping("/form")
    public List<String> getEnum() {
        return Arrays.stream(EmployeeType.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @PostMapping("/create")
    public ResponseEntity createGuidance (@RequestBody CreateGuidanceDTO dto, Authentication authentication) {
        guidanceService.createOrientation(dto, authentication);
        return ResponseEntity.ok().body("Orientação salva com sucesso, acompanhe o processo.");
    }

    @GetMapping("/filter/guidance")
    public List<GetResponseGuidanceDTO> getGuidance (Authentication authentication) {
        return guidanceService.getGuidanceBy(authentication);
    }

    @PutMapping("/callback")
    public ResponseEntity<String> callbackGuidance (@RequestBody CreateGuidanceDTO dto, Authentication authentication) {
        guidanceService.putGuidanceFun(dto.id(), dto.descricao(), authentication);
        return ResponseEntity.ok().body("Orientação lançada com sucesso");
    }

    @GetMapping("/userfilter")
    public List<GetResponseGuidanceDTO> getGuidanceByUser(Authentication authentication) {
        return guidanceService.getGuidanceUser(authentication);
    }

    @PutMapping("/edit")
    public ResponseEntity<String> editGuidanceUser (@RequestBody CreateGuidanceDTO dto, Authentication authentication) {
        guidanceService.editGuidanceUser(dto);
        return ResponseEntity.ok().body("Orientação editada com sucesso");
    }

    @DeleteMapping("/deleteByUser")
    public ResponseEntity<String> editGuidanceUser (@RequestBody CreateGuidanceDTO dto) {
        guidanceService.deleteGuidanceByUser(dto);
        return ResponseEntity.ok().body("Orientação deletada com sucesso");
    }
}
