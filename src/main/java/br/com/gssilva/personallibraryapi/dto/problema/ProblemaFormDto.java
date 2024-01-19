package br.com.gssilva.personallibraryapi.dto.problema;

import br.com.gssilva.personallibraryapi.model.Problema;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.java.Log;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Log
public class ProblemaFormDto {
    @NotBlank(message = "Título deve ser preenchido")
    private String titulo;

    @NotBlank(message = "Descrição deve ser preenchido")
    private String descricao;

    private LocalDateTime dataHora;
    private String solucao;

    @NotNull(message = "Usuário deve ser informado")
    private Long usuarioId;

    public Problema criarProblema() {
        log.info("Criando objeto Problema a partir do DTO");

        Problema problema = new Problema();

        problema.setTitulo(titulo);
        problema.setDescricao(descricao);
        problema.setDataHora(dataHora);

        if(dataHora == null){
            problema.setDataHora(LocalDateTime.now());
        }

        problema.setSolucao("");

        if(StringUtils.isNotBlank(solucao)){
            problema.setSolucao(solucao);
        }


        return problema;
    }

    public void alterarProblema(Problema problema) {
        log.info("Alterando, caso o usuário tenha realizado mudanças, o objeto Problema");

        if(StringUtils.isNotBlank(titulo)){
            problema.setTitulo(titulo);
        }

        if(StringUtils.isNotBlank(descricao)){
            problema.setDescricao(descricao);
        }

        if(dataHora != null){
            problema.setDataHora(dataHora);
        }

        if(StringUtils.isNotBlank(solucao)){
            problema.setSolucao(solucao);
        }
    }
}
