package nobre.diego.testeAuth.controllers;

import jakarta.validation.Valid;
import nobre.diego.testeAuth.config.TokenService;
import nobre.diego.testeAuth.domain.User;
import nobre.diego.testeAuth.domain.UserRole;
import nobre.diego.testeAuth.dtos.*;
import nobre.diego.testeAuth.repositories.UserRepository;
import nobre.diego.testeAuth.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

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
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var user = (User) auth.getPrincipal();
        var token = tokenService.generateToken(user);

        return ResponseEntity.ok(new LoginResponseDTO(token, user.getName(), user.getRole()));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){
        if(this.userRepository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.name(),data.login(), encryptedPassword, data.cep(),
                data.adress(), data.phone(), UserRole.CLIENTE);

        this.userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/edit")
    public EditResponseDTO editUser (@RequestBody @Valid EditUserDTO dto, Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        return userService.updateUser(user.getLogin(), dto);
    }
}
