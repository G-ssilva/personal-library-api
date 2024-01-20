package br.com.gssilva.personallibraryapi.controller;

import br.com.gssilva.personallibraryapi.dto.TokenDto;
import br.com.gssilva.personallibraryapi.dto.usuario.UsuarioFormDto;
import br.com.gssilva.personallibraryapi.dto.usuario.UsuarioSignInDto;
import br.com.gssilva.personallibraryapi.infra.security.TokenService;
import br.com.gssilva.personallibraryapi.model.Usuario;
import br.com.gssilva.personallibraryapi.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("cadastrar")
    public ResponseEntity<UsuarioFormDto> cadastrar(@RequestBody @Valid UsuarioFormDto dados, UriComponentsBuilder uriBuilder){
        Usuario usuario = dados.criarUsuario();
        usuarioService.criptografarSenha(usuario);
        usuarioService.vincularPerfilSeExiste(usuario, dados.getPerfilId());

        usuarioService.persistir(usuario);

        URI uri = uriBuilder.path("api/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(dados);
    }

    @PostMapping("login")
    public TokenDto login(@RequestBody @Valid UsuarioSignInDto dados){
        UsernamePasswordAuthenticationToken loginSenha = new UsernamePasswordAuthenticationToken(dados.getLogin(), dados.getSenha());
        Authentication auth = this.authenticationManager.authenticate(loginSenha);

        String token = tokenService.gerarToken((Usuario) auth.getPrincipal());

        return new TokenDto(token);
    }
}
