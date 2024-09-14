package nobre.diego.testeAuth.services;

import nobre.diego.testeAuth.domain.Guardians;
import nobre.diego.testeAuth.domain.User;
import nobre.diego.testeAuth.dtos.CreateGuardianDTO;
import nobre.diego.testeAuth.dtos.EditResponseDTO;
import nobre.diego.testeAuth.dtos.GetResponseGuardiansDTO;
import nobre.diego.testeAuth.repositories.GuardianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GuardianService {

    @Autowired
    private GuardianRepository guardianRepository;

    public void createGuardian (User user, CreateGuardianDTO dto) {

        int guardiansCount = guardianRepository.countByUser(user);

        if (guardiansCount == 5) {
            throw new IllegalStateException("Usuário já possui número máximo de 6 guardiões");
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
