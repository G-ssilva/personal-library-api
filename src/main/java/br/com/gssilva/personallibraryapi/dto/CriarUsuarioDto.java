package br.com.gssilva.personallibraryapi.dto;

import br.com.gssilva.personallibraryapi.model.Usuario;
import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CriarUsuarioDto {
    private String login;
    private String senha;
    private long perfilId;

    public Usuario criarUsuario() {
        if(StringUtils.isBlank(login) || StringUtils.isBlank(senha)){
            throw new RuntimeException("Usuário e/ou senha não preenchido");
        }

        Usuario usuario = new Usuario();

        usuario.setLogin(login);
        usuario.setSenha(senha);

        return usuario;
    }
}
