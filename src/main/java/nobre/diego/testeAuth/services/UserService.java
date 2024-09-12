package nobre.diego.testeAuth.services;

import nobre.diego.testeAuth.domain.User;
import nobre.diego.testeAuth.dtos.EditResponseDTO;
import nobre.diego.testeAuth.dtos.EditUserDTO;
import nobre.diego.testeAuth.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public EditResponseDTO updateUser(String login, EditUserDTO dto) throws UsernameNotFoundException {
        User user = (User) userRepository.findByLogin(login);
        if (user == null) {throw new UsernameNotFoundException("User not Found");}

        if (dto.name() != null) {
            user.setName(dto.name());
        }
        if (dto.cep() != null) {
            user.setCep(dto.cep());
        }
        if (dto.adress() != null) {
            user.setAdress(dto.adress());
        }
        if (dto.password() != null) {
            String encryptedPassword = new BCryptPasswordEncoder().encode(dto.password());
            user.setPassword(encryptedPassword);
        }
        if (dto.phone() != null) {
            user.setPhoneNumber(dto.phone());
        }

        userRepository.save(user);

        return new EditResponseDTO(user.getId(), dto.name(), dto.password(), dto.cep(), dto.adress(), dto.phone());
    }
}
