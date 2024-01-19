package br.com.gssilva.personallibraryapi.dto.usuario;

import br.com.gssilva.personallibraryapi.exceptions.RegraNegocioException;
import br.com.gssilva.personallibraryapi.model.Usuario;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.java.Log;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Log
public class UsuarioFormDto {
    @NotBlank(message = "Login deve ser preenchido")
    private String login;

    @NotBlank(message = "Senha deve ser preenchido")
    private String senha;

    @NotNull(message = "Perfil deve ser informado")
    private Long perfilId;

    public Usuario criarUsuario() {
        log.info("Criando objeto Usuário a partir do DTO");

        Usuario usuario = new Usuario();

        usuario.setLogin(login);
        usuario.setSenha(senha);

        return usuario;
    }

    public void alterarUsuario(Usuario usuario) {
        log.info("Alterando, caso o usuário tenha realizado mudanças, o objeto Usuário");

        if(StringUtils.isNotBlank(login)){
            throw new RegraNegocioException("Não é possível alterar o login");
        }

        if(StringUtils.isNotBlank(senha)){
            usuario.setSenha(senha);
        }
    }
}
