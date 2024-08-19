package com.user.user.infra.security;


import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.user.user.model.Usuario;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String gerarToken(Usuario usuario){
    try{
        Algorithm algoritmo = Algorithm.HMAC256(secret);

        String authorities = usuario.getAuthorities().stream()
                .map(grantedAuthority -> grantedAuthority.getAuthority())
                .collect(Collectors.joining(","));

        return JWT.create()
                    .withIssuer("FoodMicroservice")
                    .withExpiresAt(dataExpiracao())
                    .withClaim("roles", authorities)
                    .withClaim("tipo", usuario.getTipo().toString())
                    .withClaim("id", usuario.getId())
                    .withClaim("email", usuario.getEmail())
                    .withSubject(usuario.getUsername())
                    .sign(algoritmo);
                
    } catch (JWTCreationException exception) {
        throw new RuntimeException("Erro ao gerar Token", exception);
    }   
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }


}