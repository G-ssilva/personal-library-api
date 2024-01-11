package br.com.gssilva.personallibraryapi.controller;

import br.com.gssilva.personallibraryapi.dto.CriarLivroDto;
import br.com.gssilva.personallibraryapi.dto.LivroDto;
import br.com.gssilva.personallibraryapi.model.Livro;
import br.com.gssilva.personallibraryapi.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

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
    public Optional<LivroDto> listarPorId(@PathVariable long id){
        Optional<Livro> livro = livroService.listarPorId(id);

        if(livro.isEmpty()){
            throw new RuntimeException("Id do livro não existe na base de dados");
        }

        return livro.map(LivroDto::new);
    }
}
