package br.com.controleestoque.controleestoque.config.Interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import br.com.controleestoque.controleestoque.config.Exception.AuthException;
import br.com.controleestoque.controleestoque.modules.jwt.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import static org.springframework.util.ObjectUtils.isEmpty;


public class AuthInterception implements HandlerInterceptor {

    private static final String AUTHORIZATION = "Authorization";
    @Autowired
    private JwtService jwtService;

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler)
            throws Exception {
        if (HttpMethod.OPTIONS.name().equals(request.getMethod())) {
            if (isOption(request))
                return true;
        }
            var authorization = request.getHeader(AUTHORIZATION);
            validateToken(authorization);
            var acessToken = authorization.split(" ")[1];
            jwtService.validateAuthorization(acessToken);
            return true;
    }

    private boolean isOption(HttpServletRequest request) {
        return HttpMethod.OPTIONS.name().equals(request.getMethod());
    }

    private void validateToken(String token) {
        if(isEmpty(token)) {
            throw new AuthException("Erro ao realizar acesso ao token.");
        }
    }

}
