package br.com.gssilva.personallibraryapi.controller;

import br.com.gssilva.personallibraryapi.dto.problema.ProblemaFormDto;
import br.com.gssilva.personallibraryapi.dto.problema.ProblemaDto;
import br.com.gssilva.personallibraryapi.model.Problema;
import br.com.gssilva.personallibraryapi.service.ProblemaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/problema")
public class ProblemaController {

    @Autowired
    private ProblemaService problemaService;

    @PostMapping
    public ResponseEntity<ProblemaFormDto> cadastrar(@RequestBody @Valid ProblemaFormDto dados, UriComponentsBuilder uriBuilder){
        Problema problema = dados.criarProblema();
        problemaService.vincularUsuarioSeExiste(problema, dados.getUsuarioId());

        problemaService.persistir(problema);

        URI uri = uriBuilder.path("api/usuario/{id}").buildAndExpand(problema.getId()).toUri();
        return ResponseEntity.created(uri).body(dados);
    }

    @GetMapping("{id}")
    public ProblemaDto listarPorId(@PathVariable long id){
        return new ProblemaDto(problemaService.listarPorId(id));
    }

    @GetMapping("listar")
    public List<ProblemaDto> listarTodos(){
        return problemaService.retornarListaProblemas();
    }

    @PutMapping("{id}")
    public ResponseEntity<ProblemaDto> alterar(@RequestBody ProblemaFormDto dados, @PathVariable long id, UriComponentsBuilder uriBuilder){
        Problema problema = problemaService.problemaReferencia(id);
        dados.alterarProblema(problema);
        problemaService.alterarUsuarioSeInformado(problema, dados.getUsuarioId());
        problemaService.persistir(problema);

        ProblemaDto problemaDto = new ProblemaDto(problema);

        URI uri = uriBuilder.path("api/problema/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).body(problemaDto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable long id){
        problemaService.deletarPorId(id);
    }
}
