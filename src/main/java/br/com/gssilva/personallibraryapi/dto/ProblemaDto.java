package br.com.gssilva.personallibraryapi.dto;

import br.com.gssilva.personallibraryapi.model.Problema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProblemaDto {
    private long id;
    private String titulo;
    private String descricao;
    private LocalDateTime dataHora;
    private String solucao;
    private long usuarioId;

    public ProblemaDto(Problema problema){
        this.id = problema.getId();
        this.titulo = problema.getTitulo();
        this.descricao = problema.getDescricao();
        this.dataHora = problema.getDataHora();
        this.solucao = problema.getSolucao();
        this.usuarioId = problema.getUsuarioId().getId();
    }
}
