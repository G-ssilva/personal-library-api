package br.com.gssilva.personallibraryapi.dto.livro;

import br.com.gssilva.personallibraryapi.model.Livro;
import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CriarLivroDto {
    private String titulo;
    private String autor;
    private Long numeroPaginas;
    private String idioma;
    private String editora;
    private LocalDate dataPublicacao;
    private Long usuarioId;

    public Livro criarLivro() {
        if(StringUtils.isBlank(titulo) || StringUtils.isBlank(autor)
                || StringUtils.isBlank(idioma) || StringUtils.isBlank(editora)){
            throw new RuntimeException("Título, autor, idioma e/ou editora do livro não informado");
        }

        if(dataPublicacao == null){
            throw new RuntimeException("Data de publicação não informada");
        }

        if(numeroPaginas == null || usuarioId == null){
            throw new RuntimeException("Número de páginas e/ou id do usuário não informado");
        }

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
