package br.com.controleestoque.controleestoque.modules.jwt.service;

import org.springframework.stereotype.Service;

import br.com.controleestoque.controleestoque.config.Exception.AuthException;
import br.com.controleestoque.controleestoque.modules.jwt.dto.JwtResponse;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import static org.springframework.util.ObjectUtils.isEmpty;

import org.springframework.beans.factory.annotation.Value;

@Service
public class JwtService {
    @Value("${app-config.secrets.api-secret}")
    private String apiSecret;

    public void validateAuthorization(String token) {
        var acessToken = extractToken(token);
        try {
            var claims = Jwts
                        .parserBuilder()
                        .setSigningKey(Keys.hmacShaKeyFor(apiSecret.getBytes()))
                        .build()
                        .parseClaimsJws(acessToken)
                        .getBody();
            var user = JwtResponse.getUser(claims);
            if (isEmpty(user) || isEmpty(user.getId())) {
                throw new AuthException("O usuario nao e valido.");
            }


        } catch (Exception e) {
            e.printStackTrace();
            throw new AuthException("Erro ao processar o token encaminhado.");

        }
    }

    private String extractToken(String token) {
        if(isEmpty(token)) {
            throw new AuthException("Autenticação Falhou");
        }
        return token;
    }
}
