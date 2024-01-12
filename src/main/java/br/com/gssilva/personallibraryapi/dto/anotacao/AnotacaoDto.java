package br.com.gssilva.personallibraryapi.dto.anotacao;

import br.com.gssilva.personallibraryapi.model.Anotacao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnotacaoDto {
    private long id;
    private String titulo;
    private String descricao;
    private LocalDateTime dataHora;
    private long livroId;

    public AnotacaoDto(Anotacao anotacao) {
        this.id = anotacao.getId();
        this.titulo = anotacao.getTitulo();
        this.descricao = anotacao.getDescricao();
        this.dataHora = anotacao.getDataHora();
        this.livroId = anotacao.getLivroId().getId();
    }
}
