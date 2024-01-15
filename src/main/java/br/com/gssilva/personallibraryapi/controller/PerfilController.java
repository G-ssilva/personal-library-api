package br.com.gssilva.personallibraryapi.controller;

import br.com.gssilva.personallibraryapi.dto.perfil.PerfilFormDto;
import br.com.gssilva.personallibraryapi.dto.perfil.PerfilDto;
import br.com.gssilva.personallibraryapi.model.Perfil;
import br.com.gssilva.personallibraryapi.service.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api/perfil")
public class PerfilController {

    @Autowired
    private PerfilService perfilService;

    @PostMapping
    public ResponseEntity<PerfilFormDto> cadastrar(@RequestBody PerfilFormDto dados, UriComponentsBuilder uriBuilder){
        Perfil perfil = dados.criarPerfil();
        perfilService.cadastrar(perfil);

        URI uri = uriBuilder.path("api/perfil/{id}").buildAndExpand(perfil.getId()).toUri();
        return ResponseEntity.created(uri).body(dados);
    }

    @GetMapping("{id}")
    public PerfilDto listarPorId(@PathVariable long id){
        return new PerfilDto(perfilService.listarPorId(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<PerfilDto> alterar(@RequestBody PerfilFormDto dados, @PathVariable long id, UriComponentsBuilder uriBuilder){
        Perfil perfil = perfilService.perfilReferencia(id);
        dados.alterarPerfil(perfil);
        perfilService.cadastrar(perfil);

        PerfilDto perfilDto = new PerfilDto(perfil);

        URI uri = uriBuilder.path("api/perfil/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).body(perfilDto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable long id){
        perfilService.deletarPorId(id);
    }

}
