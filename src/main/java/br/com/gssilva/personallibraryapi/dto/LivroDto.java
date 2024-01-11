package br.com.gssilva.personallibraryapi.dto;

import br.com.gssilva.personallibraryapi.model.Livro;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LivroDto {
    private long id;
    private String titulo;
    private String autor;
    private long numeroPaginas;
    private String idioma;
    private String editora;
    private LocalDateTime dataPublicacao;
    private long usuarioId;

    public LivroDto(Livro livro) {
        this.id = livro.getId();
        this.titulo = livro.getTitulo();
        this.autor = livro.getAutor();
        this.numeroPaginas = livro.getNumeroPaginas();
        this.idioma = livro.getIdioma();
        this.editora = livro.getEditora();
        this.dataPublicacao = livro.getDataPublicacao();
        this.usuarioId = livro.getUsuarioId().getId();
    }
}
