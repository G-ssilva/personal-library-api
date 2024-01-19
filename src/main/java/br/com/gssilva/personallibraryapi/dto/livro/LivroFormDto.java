package br.com.gssilva.personallibraryapi.dto.livro;

import br.com.gssilva.personallibraryapi.model.Livro;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.java.Log;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Log
public class LivroFormDto {
    @NotBlank(message = "Título deve ser preenchido")
    private String titulo;

    @NotBlank(message = "Autor deve ser preenchido")
    private String autor;

    @NotNull(message = "Número de páginas deve ser preenchido")
    private Long numeroPaginas;

    @NotBlank(message = "Idioma deve ser preenchido")
    private String idioma;

    @NotBlank(message = "Editora deve ser preenchido")
    private String editora;

    @NotNull(message = "Data de publicação deve ser preenchido")
    private LocalDate dataPublicacao;

    @NotNull(message = "Usuário deve ser informado")
    private Long usuarioId;

    public Livro criarLivro() {
        log.info("Criando objeto Livro a partir do DTO");

        Livro livro = new Livro();
        livro.setTitulo(titulo);
        livro.setAutor(autor);
        livro.setNumeroPaginas(numeroPaginas);
        livro.setIdioma(idioma);
        livro.setEditora(editora);
        livro.setDataPublicacao(dataPublicacao);

        return livro;
    }

    public void alterarLivro(Livro livro) {
        log.info("Alterando, caso o usuário tenha realizado mudanças, o objeto Livro");

        if(StringUtils.isNotBlank(titulo)){
            livro.setTitulo(titulo);
        }

        if(StringUtils.isNotBlank(autor)){
            livro.setAutor(autor);
        }

        if(numeroPaginas != null){
            livro.setNumeroPaginas(numeroPaginas);
        }

        if(StringUtils.isNotBlank(idioma)){
            livro.setIdioma(idioma);
        }

        if(StringUtils.isNotBlank(editora)){
            livro.setEditora(editora);
        }

        if(dataPublicacao != null){
            livro.setDataPublicacao(dataPublicacao);
        }
    }
}
