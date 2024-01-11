package br.com.gssilva.personallibraryapi.dto;

import br.com.gssilva.personallibraryapi.model.Problema;
import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CriarProblemaDto {
    private String titulo;
    private String descricao;
    private String solucao;
    private Long usuarioId;

    public Problema criarProblema() {
        if(StringUtils.isBlank(titulo) || StringUtils.isBlank(descricao)){
            throw new RuntimeException("Titulo e/ou descricao não preenchido");
        }

        if(usuarioId == null){
            throw new RuntimeException("Id do usuário não informado");
        }

        Problema problema = new Problema();

        problema.setTitulo(titulo);
        problema.setDescricao(descricao);
        problema.setSolucao("");
        if(StringUtils.isNotBlank(solucao)){
            problema.setSolucao(solucao);
        }

        return problema;
    }
}
