package br.com.gssilva.personallibraryapi.infra.security;

import br.com.gssilva.personallibraryapi.model.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
@Log
public class TokenService {

    @Value("${api.security.token.secret}")
    public String SECRET;

    @Value("${api.security.token.min.expires}")
    public Long MIN_EXPIRES;

    public String gerarToken(Usuario usuario){
        try {
            log.info("Gerando token para o usuário " + usuario.getLogin());
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            return JWT.create()
                    .withIssuer("personal-library")
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(gerarDataExpiracao())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token JWT", exception);
        }
    }

    public String validarToken(String token){
        try {
            log.info("Validando token do usuário");
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            return JWT.require(algorithm)
                    .withIssuer("personal-library")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            log.info("Token inválido ou não foi possível realizar a validação");
            return "";
        }
    }

    private Instant gerarDataExpiracao(){
        if (MIN_EXPIRES == null) {
            throw new RuntimeException("Tempo de expiração do token está nulo ou inválido");
        }
        return LocalDateTime.now().plusMinutes(MIN_EXPIRES).toInstant(ZoneOffset.of("-03:00"));
    }
}
