package br.com.gssilva.personallibraryapi.dto.perfil;

import br.com.gssilva.personallibraryapi.model.Perfil;
import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CriarPerfilDto {
    private String tipo;
    private String descricao;

    public Perfil criarPerfil() {
        if(StringUtils.isBlank(tipo) || StringUtils.isBlank(descricao)){
            throw new RuntimeException("Tipo e/ou descrição do perfil não informado");
        }

        Perfil perfil = new Perfil();

        perfil.setTipo(tipo);
        perfil.setDescricao(descricao);

        return perfil;
    }
}
