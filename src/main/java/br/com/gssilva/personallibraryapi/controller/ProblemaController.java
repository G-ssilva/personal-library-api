package br.com.gssilva.personallibraryapi.controller;

import br.com.gssilva.personallibraryapi.dto.CriarProblemaDto;
import br.com.gssilva.personallibraryapi.dto.ProblemaDto;
import br.com.gssilva.personallibraryapi.model.Problema;
import br.com.gssilva.personallibraryapi.service.ProblemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("api/problema")
public class ProblemaController {

    @Autowired
    private ProblemaService problemaService;

    @PostMapping
    public ResponseEntity<CriarProblemaDto> cadastrar(@RequestBody CriarProblemaDto dados, UriComponentsBuilder uriBuilder){
        Problema problema = dados.criarProblema();
        problemaService.vincularUsuarioSeExiste(problema, dados.getUsuarioId());

        problemaService.cadastrar(problema);

        URI uri = uriBuilder.path("api/usuario/{id}").buildAndExpand(problema.getId()).toUri();
        return ResponseEntity.created(uri).body(dados);
    }

    @GetMapping("{id}")
    public Optional<ProblemaDto> listarPorId(@PathVariable long id){
        Optional<Problema> problema = problemaService.listarPorId(id);

        if(problema.isEmpty()){
            throw new RuntimeException("Id do problema não existe na base de dados");
        }

        return problema.map(ProblemaDto::new);
    }
}
