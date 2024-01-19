package br.com.gssilva.personallibraryapi.dto.perfil;

import br.com.gssilva.personallibraryapi.model.Perfil;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.constraints.NotBlank;
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
public class PerfilFormDto {
    @NotBlank(message = "Tipo deve ser preenchido")
    private String tipo;

    @NotBlank(message = "Descrição deve ser preenchido")
    private String descricao;

    public Perfil criarPerfil() {
        log.info("Criando objeto Perfil a partir do DTO");

        Perfil perfil = new Perfil();

        perfil.setTipo(tipo);
        perfil.setDescricao(descricao);

        return perfil;
    }

    public void alterarPerfil(Perfil perfil) {
        log.info("Alterando, caso o usuário tenha realizado mudanças, o objeto Perfil");

        if(StringUtils.isNotBlank(tipo)){
            perfil.setTipo(tipo);
        }

        if(StringUtils.isNotBlank(descricao)){
            perfil.setDescricao(descricao);
        }
    }
}
