package br.com.gssilva.personallibraryapi.dto.usuario;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioSignInDto {
    @NotBlank(message = "Login deve ser informado")
    private String login;
    @NotBlank(message = "Senha deve ser informada")
    private String senha;

}