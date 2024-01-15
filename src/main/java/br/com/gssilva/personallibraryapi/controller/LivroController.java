package br.com.gssilva.personallibraryapi.controller;

import br.com.gssilva.personallibraryapi.dto.livro.CriarLivroDto;
import br.com.gssilva.personallibraryapi.dto.livro.LivroDto;
import br.com.gssilva.personallibraryapi.model.Livro;
import br.com.gssilva.personallibraryapi.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api/livro")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @PostMapping
    public ResponseEntity<CriarLivroDto> cadastrar(@RequestBody CriarLivroDto dados, UriComponentsBuilder uriBuilder){
        Livro livro = dados.criarLivro();
        livroService.vincularUsuarioSeExiste(livro, dados.getUsuarioId());

        livroService.cadastrar(livro);

        URI uri = uriBuilder.path("api/livro/{id}").buildAndExpand(livro.getId()).toUri();
        return ResponseEntity.created(uri).body(dados);
    }

    @GetMapping("{id}")
    public LivroDto listarPorId(@PathVariable long id){
        return new LivroDto(livroService.listarPorId(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<LivroDto> alterar(@RequestBody CriarLivroDto dados, @PathVariable long id, UriComponentsBuilder uriBuilder){
        Livro livro = livroService.livroReferencia(id);
        dados.alterarLivro(livro);
        livroService.alterarUsuarioSeInformado(livro, dados.getUsuarioId());
        livroService.cadastrar(livro);

        LivroDto livroDto = new LivroDto(livro);

        URI uri = uriBuilder.path("api/livro/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).body(livroDto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable long id){
        livroService.deletarPorId(id);
    }
}
