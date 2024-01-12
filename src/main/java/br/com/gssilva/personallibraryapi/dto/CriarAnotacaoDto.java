package br.com.gssilva.personallibraryapi.dto;

import br.com.gssilva.personallibraryapi.model.Anotacao;
import br.com.gssilva.personallibraryapi.model.Livro;
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
public class CriarAnotacaoDto {
    private String titulo;
    private String descricao;
    private LocalDateTime dataHora;
    private Long livroId;

    public Anotacao criarAnotacao() {
        if(StringUtils.isBlank(titulo) || StringUtils.isBlank(descricao)){
            throw new RuntimeException("Usuário e/ou senha não preenchido");
        }

        if(livroId == null){
            throw new RuntimeException("ID do livro preenchido");
        }

        Anotacao anotacao = new Anotacao();
        anotacao.setTitulo(titulo);
        anotacao.setDescricao(descricao);

        if(dataHora != null){
            anotacao.setDataHora(dataHora);
        }

        return anotacao;
    }
}
