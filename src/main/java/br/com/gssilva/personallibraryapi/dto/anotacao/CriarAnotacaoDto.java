package br.com.gssilva.personallibraryapi.dto.anotacao;

import br.com.gssilva.personallibraryapi.model.Anotacao;
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
            throw new RuntimeException("Título e/ou descrição não preenchido");
        }

        if(livroId == null){
            throw new RuntimeException("ID do livro não preenchido");
        }

        Anotacao anotacao = new Anotacao();
        anotacao.setTitulo(titulo);
        anotacao.setDescricao(descricao);
        anotacao.setDataHora(dataHora);

        if(dataHora == null){
            anotacao.setDataHora(LocalDateTime.now());
        }

        return anotacao;
    }

    public void alterarAnotacao(Anotacao anotacao) {
        if(StringUtils.isNotBlank(titulo)){
            anotacao.setTitulo(titulo);
        }

        if(StringUtils.isNotBlank(descricao)){
            anotacao.setDescricao(descricao);
        }

        if(dataHora != null){
            anotacao.setDataHora(dataHora);
        }
    }
}
