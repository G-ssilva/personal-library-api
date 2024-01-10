package br.com.gssilva.personallibraryapi.controller;

import br.com.gssilva.personallibraryapi.dto.CriarPerfilDto;
import br.com.gssilva.personallibraryapi.dto.PerfilDto;
import br.com.gssilva.personallibraryapi.model.Perfil;
import br.com.gssilva.personallibraryapi.service.PerfilService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("api/perfil")
public class PerfilController {

    @Autowired
    private PerfilService perfilService;

    @PostMapping
    @Transactional
    public ResponseEntity<CriarPerfilDto> cadastrar(@RequestBody CriarPerfilDto dados, UriComponentsBuilder uriBuilder){
        Perfil perfil = dados.criarPerfil();
        perfilService.cadastrar(perfil);

        URI uri = uriBuilder.path("api/perfil/{id}").buildAndExpand(perfil.getId()).toUri();
        return ResponseEntity.created(uri).body(dados);
    }

    @GetMapping("{id}")
    public Optional<PerfilDto> listarPorId(@PathVariable long id){
        Optional<Perfil> perfil = perfilService.listarPorId(id);

        if(perfil.isEmpty()){
            throw new RuntimeException("Id do perfil não existe na base de dados");
        }

        return perfil.map(PerfilDto::new);
    }


}
