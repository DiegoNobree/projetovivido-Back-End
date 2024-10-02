package nobre.diego.testeAuth.services;

import nobre.diego.testeAuth.domain.Guardians;
import nobre.diego.testeAuth.domain.User;
import nobre.diego.testeAuth.dtos.Guardians.CreateGuardianDTO;
import nobre.diego.testeAuth.dtos.Users.EditResponseDTO;
import nobre.diego.testeAuth.dtos.Guardians.GetResponseGuardiansDTO;
import nobre.diego.testeAuth.repositories.GuardianRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GuardianService {

    @Autowired
    private GuardianRepository guardianRepository;

    public void createGuardian (User user, CreateGuardianDTO dto) {

        int guardiansCount = guardianRepository.countByUser(user);

        if (guardiansCount >= 5) {
            throw new RuntimeException("Número máximo de guardiões atingido");
        }

        Guardians guardian = new Guardians();
        guardian.setUser(user);
        guardian.setName(dto.name());
        String stringNumber = formatedNumber(dto.phone());
        guardian.setPhoneNumber(stringNumber);
        guardianRepository.save(guardian);
    }

    private String formatedNumber (Long phoneNumber) {
        String phoneString = String.valueOf(phoneNumber);
        return "+55" + phoneString;
    }

    public List<GetResponseGuardiansDTO> editGuardian(EditResponseDTO dto) {
        Optional<Guardians> guardiansOptional = guardianRepository.findById(dto.id());
        Guardians guardian = new Guardians();
        if (guardiansOptional.isPresent()) {
            guardian = guardiansOptional.get();
            if (dto.name() != null) {
                guardian.setName(dto.name());
            }
            if (dto.phone() != null) {
                String formatedNumber = formatedNumber(dto.phone());
                guardian.setPhoneNumber(formatedNumber);
            }
            guardianRepository.save(guardian);

        }
        return convertToDTO(guardian);
    }

    private List<GetResponseGuardiansDTO> convertToDTO(Guardians guardian) {
        return List.of(new GetResponseGuardiansDTO(guardian.getId(), guardian.getName(), guardian.getPhoneNumber()));
    }
}
