package br.com.gssilva.personallibraryapi.infra.security;

import br.com.gssilva.personallibraryapi.service.UsuarioService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioService usuarioService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = this.recuperarToken(request);

        if(token != null){
            String login = tokenService.validarToken(token);
            UserDetails usuario = usuarioService.findByLogin(login);

            UsernamePasswordAuthenticationToken autenticacao =
                    new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(autenticacao);
        }

        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");

        if(authHeader == null) {
            return null;
        }

        return authHeader.replace("Bearer ", "");
    }
}
