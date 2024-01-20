package br.com.gssilva.personallibraryapi.controller;

import br.com.gssilva.personallibraryapi.dto.usuario.UsuarioDto;
import br.com.gssilva.personallibraryapi.dto.usuario.UsuarioFormDto;
import br.com.gssilva.personallibraryapi.dto.usuario.UsuarioSignInDto;
import br.com.gssilva.personallibraryapi.infra.security.TokenService;
import br.com.gssilva.personallibraryapi.model.Usuario;
import br.com.gssilva.personallibraryapi.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TokenService tokenService;

    @PostMapping()
    public ResponseEntity<UsuarioFormDto> criar(@RequestBody @Valid UsuarioFormDto dados, UriComponentsBuilder uriBuilder){
        Usuario usuario = dados.criarUsuario();
        usuarioService.criptografarSenha(usuario);
        usuarioService.vincularPerfilSeExiste(usuario, dados.getPerfilId());

        usuarioService.persistir(usuario);

        URI uri = uriBuilder.path("api/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(dados);
    }

    @GetMapping("{id}")
    public UsuarioDto listarPorId(@PathVariable long id){
        return new UsuarioDto(usuarioService.listarPorId(id));
    }

    @GetMapping("listar")
    public List<UsuarioDto> listarTodos(){
        return usuarioService.retornarListaUsuarios();
    }

    @PutMapping("{id}")
    public ResponseEntity<UsuarioDto> alterar(@RequestBody UsuarioFormDto dados, @PathVariable long id, UriComponentsBuilder uriBuilder){
        Usuario usuario = usuarioService.usuarioReferencia(id);
        dados.alterarUsuario(usuario);
        usuarioService.alterarPerfilSeInformado(usuario, dados.getPerfilId());
        usuarioService.persistir(usuario);

        UsuarioDto usuarioDto = new UsuarioDto(usuario);

        URI uri = uriBuilder.path("api/usuario/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).body(usuarioDto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable long id){
        usuarioService.deletarPorId(id);
    }

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("auth/login")
    public ResponseEntity acessar(@RequestBody @Valid UsuarioSignInDto dados){
        UsernamePasswordAuthenticationToken loginSenha = new UsernamePasswordAuthenticationToken(dados.getLogin(), dados.getSenha());
        Authentication auth = this.authenticationManager.authenticate(loginSenha);

        String token = tokenService.gerarToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok().body(token);
    }

}
