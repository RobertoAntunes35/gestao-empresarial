package br.com.controleestoque.controleestoque.modules.jwt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor


public class JwtResponse {
    private Integer id;
    private String name; 
    private String email;

    public static JwtResponse getUser(Claims jwtClaims) {
            try {
                return new ObjectMapper().convertValue(jwtClaims.get("authUser"), JwtResponse.class);
            } catch(Exception e) {
                e.printStackTrace();
                return null;
            }
    }
}
