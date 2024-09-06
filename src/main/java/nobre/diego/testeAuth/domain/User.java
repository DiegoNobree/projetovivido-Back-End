package nobre.diego.testeAuth.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "users")
@Entity(name = "users")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String login;

    @NotNull
    private String password;

    @NotNull
    private UserRole role;

    @ManyToOne
    Clinica clinica;

    public User(String name,String login, String password, UserRole role){
        this.login = login;
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public User(Long id, String name, String login, String encryptedPassword, UserRole role) {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_PACIENTE"));
        if(this.role == UserRole.PROFESSOR) return List.of(new SimpleGrantedAuthority("ROLE_PROFESSOR"), new SimpleGrantedAuthority("ROLE_PACIENTE"));
        if(this.role == UserRole.ESTAGIARIO) return List.of(new SimpleGrantedAuthority("ROLE_ESTAGIARIO"), new SimpleGrantedAuthority("ROLE_PACIENTE"));
        else return List.of(new SimpleGrantedAuthority("ROLE_PACIENTE"));
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
