package br.com.controleestoque.controleestoque.config.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// import lombok.Data;

// @Data
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class AuthException extends RuntimeException {
    public AuthException(String message) {
        super(message);
    }
}
