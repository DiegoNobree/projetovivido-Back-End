package nobre.diego.testeAuth.controllers;

import jakarta.validation.Valid;
import nobre.diego.testeAuth.config.TokenService;
import nobre.diego.testeAuth.domain.EmployeeType;
import nobre.diego.testeAuth.domain.User;
import nobre.diego.testeAuth.domain.UserRole;
import nobre.diego.testeAuth.dtos.Users.*;
import nobre.diego.testeAuth.repositories.UserRepository;
import nobre.diego.testeAuth.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("home")
public class UserController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserService userService;



    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){

        if (data.login().isEmpty() || data.password().isEmpty()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Preencha todos os campos");
        }
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword).isAuthenticated() ?
                this.authenticationManager.authenticate(usernamePassword) : null;

        if (auth == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Login ou senha inválidos");
        }

        var user = (User) auth.getPrincipal();
        var token = tokenService.generateToken(user);

        return ResponseEntity.ok(new LoginResponseDTO(user.getId() ,token, user.getName(), user.getRole()));
    }

    @GetMapping("/get-data")
    public ResponseEntity getData (Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(new GetDataUserDTO(user.getId(), user.getName(), user.getLogin(),
                user.getViewpassword(), user.getAdress(), user.getCep(), user.getPhoneNumber(), user.getRole()));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){
        if(this.userRepository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().body("Login já cadastrado");
        if (data.password().isEmpty() || data.name().isEmpty() || data.login().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nome, login e senha devem ser preenchidos");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.name(),data.login(), encryptedPassword, null,
                null, null, UserRole.CLIENTE, null);
        newUser.setViewpassword(data.password());

        this.userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/continue/register")
    public ResponseEntity cotinueUser (@RequestBody @Valid ContinueRegisterDTO dto,Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        if (dto.cep() == null || dto.phone() == null || dto.rua().isEmpty() || dto.bairro().isEmpty() ||
                dto.cidade().isEmpty() || dto.estado().isEmpty() || dto.comple().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Todos os campos devem ser preenchidos");
        }

        String rua = dto.rua();
        String bairro = dto.bairro();
        String cidade = dto.cidade();
        String estado = dto.estado();
        String comple = dto.comple();

        String adressFormated = "Rua " + rua + ", " + bairro + ", " + cidade + ", "  + estado +
                ". " + comple;

        user.setCep(dto.cep());
        user.setPhoneNumber(dto.phone());
        user.setAdress(adressFormated);

        userRepository.save(user);
        return ResponseEntity.ok(new GetDataUserDTO(user.getId(), user.getName(), user.getLogin(),
                user.getViewpassword(), user.getAdress(), user.getCep(), user.getPhoneNumber(), user.getRole()));
    }

    @PutMapping("/edit")
    public EditResponseDTO editUser (@RequestBody @Valid EditUserDTO dto, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        if (dto.name().isEmpty() || dto.cep() == null || dto.adress().isEmpty() || dto.phone() == null || dto.password().isEmpty()) {
            throw new IllegalArgumentException("Nenhum campo foi preenchido");
        }

        return userService.updateUser(user.getLogin(), dto);
    }

    @DeleteMapping("/adm-delete")
    public ResponseEntity deleteUserADM (@RequestBody @Valid DeleteUserDTO dto) {
         User user = userRepository.findById(dto.id());
         userRepository.delete(user);
         return ResponseEntity.ok().body("Usário Deletado");
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteUser (Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        userRepository.delete(user);
        return ResponseEntity.ok().body("Usário Deletado");
    }

    //método que apenas usuários funcionários irão acessar
    @GetMapping("/getall")
    public List<GetResponseDTO> getall () {
        return userService.getall();
    }

    @PostMapping("/teste")
    public void teste () {
        System.out.println("TESTE PORRAAA");
    }

}
