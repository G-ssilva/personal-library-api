package br.com.gssilva.personallibraryapi.controller;

import br.com.gssilva.personallibraryapi.dto.anotacao.AnotacaoDto;
import br.com.gssilva.personallibraryapi.dto.anotacao.CriarAnotacaoDto;
import br.com.gssilva.personallibraryapi.model.Anotacao;
import br.com.gssilva.personallibraryapi.service.AnotacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("api/anotacao")
public class AnotacaoController {

    @Autowired
    private AnotacaoService anotacaoService;

    @PostMapping
    public ResponseEntity<CriarAnotacaoDto> cadastrar(@RequestBody CriarAnotacaoDto dados, UriComponentsBuilder uriBuilder){
        Anotacao anotacao = dados.criarAnotacao();
        anotacaoService.vincularLivroSeExiste(anotacao, dados.getLivroId());

        anotacaoService.cadastrar(anotacao);

        URI uri = uriBuilder.path("api/perfil/{id}").buildAndExpand(anotacao.getId()).toUri();
        return ResponseEntity.created(uri).body(dados);
    }

    @GetMapping("{id}")
    public Optional<AnotacaoDto> listarPorId(@PathVariable long id){
        Optional<Anotacao> anotacao = anotacaoService.listarPorId(id);

        if(anotacao.isEmpty()){
            throw new RuntimeException("Id da anotação não existe na base de dados");
        }

        return anotacao.map(AnotacaoDto::new);
    }
}
