package br.com.controleestoque.controleestoque.config.Interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Bean
    public AuthInterception authInterception() {
        return new AuthInterception();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterception());
    }
}
