package br.com.gssilva.personallibraryapi.controller;

import br.com.gssilva.personallibraryapi.dto.anotacao.AnotacaoDto;
import br.com.gssilva.personallibraryapi.dto.anotacao.AnotacaoFormDto;
import br.com.gssilva.personallibraryapi.model.Anotacao;
import br.com.gssilva.personallibraryapi.service.AnotacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api/anotacao")
public class AnotacaoController {

    @Autowired
    private AnotacaoService anotacaoService;

    @PostMapping
    public ResponseEntity<AnotacaoFormDto> cadastrar(@RequestBody AnotacaoFormDto dados, UriComponentsBuilder uriBuilder){
        Anotacao anotacao = dados.criarAnotacao();
        anotacaoService.vincularLivroSeExiste(anotacao, dados.getLivroId());

        anotacaoService.cadastrar(anotacao);

        URI uri = uriBuilder.path("api/perfil/{id}").buildAndExpand(anotacao.getId()).toUri();
        return ResponseEntity.created(uri).body(dados);
    }

    @GetMapping("{id}")
    public AnotacaoDto listarPorId(@PathVariable long id){
        return new AnotacaoDto(anotacaoService.listarPorId(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<AnotacaoDto> alterar(@RequestBody AnotacaoFormDto dados, @PathVariable long id, UriComponentsBuilder uriBuilder){
        Anotacao anotacao = anotacaoService.anotacaoReferencia(id);
        dados.alterarAnotacao(anotacao);
        anotacaoService.alterarLivroSeInformado(anotacao, dados.getLivroId());
        anotacaoService.cadastrar(anotacao);

        AnotacaoDto anotacaoDto = new AnotacaoDto(anotacao);

        URI uri = uriBuilder.path("api/anotacao/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).body(anotacaoDto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable long id){
        anotacaoService.deletarPorId(id);
    }
}
