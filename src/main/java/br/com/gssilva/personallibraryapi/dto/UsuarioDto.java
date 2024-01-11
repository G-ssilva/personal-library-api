package br.com.gssilva.personallibraryapi.dto;

import br.com.gssilva.personallibraryapi.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDto {
    private String login;
    private String senha;
    private long perfilId;

    public UsuarioDto(Usuario usuario) {
        this.login = usuario.getLogin();
        this.senha = usuario.getSenha();
        this.perfilId = usuario.getPerfilId().getId();
    }
}
