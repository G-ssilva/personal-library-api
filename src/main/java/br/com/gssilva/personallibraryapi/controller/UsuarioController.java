package br.com.gssilva.personallibraryapi.controller;

import br.com.gssilva.personallibraryapi.dto.usuario.CriarUsuarioDto;
import br.com.gssilva.personallibraryapi.dto.usuario.UsuarioDto;
import br.com.gssilva.personallibraryapi.model.Usuario;
import br.com.gssilva.personallibraryapi.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<CriarUsuarioDto> criar(@RequestBody CriarUsuarioDto dados, UriComponentsBuilder uriBuilder){
        Usuario usuario = dados.criarUsuario();
        usuarioService.vincularPerfilSeExiste(usuario, dados.getPerfilId());

        usuarioService.cadastrar(usuario);

        URI uri = uriBuilder.path("api/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(dados);
    }

    @GetMapping("{id}")
    public UsuarioDto listarPorId(@PathVariable long id){
        return new UsuarioDto(usuarioService.listarPorId(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<UsuarioDto> alterar(@RequestBody CriarUsuarioDto dados, @PathVariable long id, UriComponentsBuilder uriBuilder){
        Usuario usuario = usuarioService.usuarioReferencia(id);
        dados.alterarUsuario(usuario);
        usuarioService.alterarPerfilSeInformado(usuario, dados.getPerfilId());
        usuarioService.cadastrar(usuario);

        UsuarioDto usuarioDto = new UsuarioDto(usuario);

        URI uri = uriBuilder.path("api/usuario/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).body(usuarioDto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable long id){
        usuarioService.deletarPorId(id);
    }

}
