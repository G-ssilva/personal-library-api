package br.com.gssilva.personallibraryapi.dto.usuario;

import br.com.gssilva.personallibraryapi.model.Usuario;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioFormDto {
    @NotBlank(message = "Login deve ser preenchido")
    private String login;

    @NotBlank(message = "Senha deve ser preenchido")
    private String senha;

    @NotNull(message = "Perfil deve ser informado")
    private Long perfilId;

    public Usuario criarUsuario() {
        Usuario usuario = new Usuario();

        usuario.setLogin(login);
        usuario.setSenha(senha);

        return usuario;
    }

    public void alterarUsuario(Usuario usuario) {
        if(StringUtils.isNotBlank(login)){
            throw new RuntimeException("Não é possível alterar o usuário");
        }

        if(StringUtils.isNotBlank(senha)){
            usuario.setSenha(senha);
        }
    }
}
