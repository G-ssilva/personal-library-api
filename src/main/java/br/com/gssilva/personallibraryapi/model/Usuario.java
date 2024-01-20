package br.com.gssilva.personallibraryapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Usuario implements UserDetails {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Getter @Setter
    @NotNull
    @Column(unique = true)
    private String login;

    @Getter @Setter
    @NotNull
    private String senha;

    @Getter @Setter
    @NotNull
    @JoinColumn(name = "perfil_id")
    @ManyToOne
    private Perfil perfilId;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return switch (this.perfilId.getTipo()) {
            case "ADMIN" -> List.of(
                    new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("ROLE_DONO"),
                    new SimpleGrantedAuthority("ROLE_USUARIO_TESTE"),
                    new SimpleGrantedAuthority("ROLE_VISITANTE")
            );
            case "DONO" -> List.of(
                    new SimpleGrantedAuthority("ROLE_DONO"),
                    new SimpleGrantedAuthority("ROLE_USUARIO_TESTE"),
                    new SimpleGrantedAuthority("ROLE_VISITANTE")
            );
            case "USUARIO_TESTE" -> List.of(
                    new SimpleGrantedAuthority("ROLE_USUARIO_TESTE"),
                    new SimpleGrantedAuthority("ROLE_VISITANTE")
            );
            default -> List.of(new SimpleGrantedAuthority("ROLE_VISITANTE"));
        };
    }

    @Override
    public String getPassword() {
        return senha;
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
