package br.com.gssilva.personallibraryapi.dto.anotacao;

import br.com.gssilva.personallibraryapi.model.Anotacao;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnotacaoFormDto {
    @NotBlank(message = "Título deve ser preenchido")
    private String titulo;

    @NotBlank(message = "Descrição deve ser preenchido")
    private String descricao;

    private LocalDateTime dataHora;

    @NotNull(message = "Livro deve ser informado")
    private Long livroId;

    public Anotacao criarAnotacao() {
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
