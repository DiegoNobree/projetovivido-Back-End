package nobre.diego.testeAuth.services;

import nobre.diego.testeAuth.domain.Guardians;
import nobre.diego.testeAuth.domain.User;
import nobre.diego.testeAuth.dtos.CreateGuardianDTO;
import nobre.diego.testeAuth.repositories.GuardianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
