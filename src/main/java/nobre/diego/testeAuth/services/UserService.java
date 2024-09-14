package nobre.diego.testeAuth.services;

import nobre.diego.testeAuth.domain.User;
import nobre.diego.testeAuth.dtos.Users.EditResponseDTO;
import nobre.diego.testeAuth.dtos.Users.EditUserDTO;
import nobre.diego.testeAuth.dtos.Users.GetResponseDTO;
import nobre.diego.testeAuth.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
            if (dto.oldpass().equals(user.getViewpassword())){
                if (dto.password().equals(dto.passcompare())) {
                    String encryptedPassword = new BCryptPasswordEncoder().encode(dto.password());
                    user.setViewpassword(dto.password());
                    user.setPassword(encryptedPassword);
                } else {
                    throw  new RuntimeException("Senhas novas não são iguais.");
                }
            } else {
                throw  new RuntimeException("A senha antiga que você forneceu, está errada.");
            }

        }
        if (dto.phone() != null) {
            user.setPhoneNumber(dto.phone());
        }

        userRepository.save(user);
        return new EditResponseDTO(user.getId(), user.getName(), user.getViewpassword() , user.getCep(), user.getAdress(), user.getPhoneNumber());
    }

    public List<GetResponseDTO> getall () {
        List<User> userList = userRepository.findAll();
        List<GetResponseDTO> dtoList  = new ArrayList<>();

        for (User user : userList) {
            GetResponseDTO dto = new GetResponseDTO(
                    user.getId(),
                    user.getName(),
                    user.getLogin(),
                    user.getRole()
            );
            dtoList.add(dto);
        }
        return dtoList;
    }
}
