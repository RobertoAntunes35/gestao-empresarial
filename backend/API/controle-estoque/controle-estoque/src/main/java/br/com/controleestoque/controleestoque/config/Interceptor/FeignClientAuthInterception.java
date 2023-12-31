package br.com.controleestoque.controleestoque.config.Interceptor;

 
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import br.com.controleestoque.controleestoque.config.Exception.ValidationException;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;

public class FeignClientAuthInterception implements RequestInterceptor{
    private static final String AUTHORIZATION = "Authorization";

    
    
    @Override
    public void apply(RequestTemplate template) {
        var currentRequest = getCurrentRequest();
        template.header(AUTHORIZATION, currentRequest.getHeader(AUTHORIZATION));
    }
    
    private HttpServletRequest getCurrentRequest() {
        try {
            return 
            ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ValidationException("The current request could not be processed");
        }
    }
}
