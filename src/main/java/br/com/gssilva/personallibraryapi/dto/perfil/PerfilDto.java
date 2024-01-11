package br.com.gssilva.personallibraryapi.dto.perfil;

import br.com.gssilva.personallibraryapi.model.Perfil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PerfilDto {
    private long id;
    private String tipo;
    private String descricao;

    public PerfilDto(Perfil perfil){
        this.id = perfil.getId();
        this.tipo = perfil.getTipo();
        this.descricao = perfil.getDescricao();
    }
}
