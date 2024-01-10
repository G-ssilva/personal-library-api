package br.com.gssilva.personallibraryapi.controller;

import br.com.gssilva.personallibraryapi.dto.CriarUsuarioDto;
import br.com.gssilva.personallibraryapi.dto.UsuarioDto;
import br.com.gssilva.personallibraryapi.model.Usuario;
import br.com.gssilva.personallibraryapi.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<CriarUsuarioDto> criar(@RequestBody CriarUsuarioDto dados, UriComponentsBuilder uriBuilder){
        Usuario usuario = dados.criarUsuario();
        usuarioService.vincularIdSeExiste(usuario, dados.getPerfilId());

        usuarioService.cadastrar(usuario);

        URI uri = uriBuilder.path("api/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(dados);
    }

    @GetMapping("{id}")
    public Optional<UsuarioDto> listarPorId(@PathVariable long id){
        Optional<Usuario> usuario = usuarioService.listarPorId(id);

        if(usuario.isEmpty()){
            throw new RuntimeException("Id do usuário não existe na base de dados");
        }

        return usuario.map(UsuarioDto::new);
    }

}
