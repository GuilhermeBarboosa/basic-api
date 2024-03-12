package br.com.guilherme.basic.core.config;

import br.com.guilherme.basic.api.service.JwtService;
import br.com.guilherme.basic.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
public class JwtFilter {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Bean
    public OncePerRequestFilter init() {
        return new JwtAuthFilter(jwtService, userService);
    }
}
