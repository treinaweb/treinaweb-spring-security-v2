package br.com.treinaweb.twprojects.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .authorizeHttpRequests(customizer -> customizer
                .anyRequest().authenticated()
            )
            .formLogin(customizer -> customizer
                .loginPage("/auth/login")
                .defaultSuccessUrl("/")
                .permitAll()
            )
            .logout(customizer -> customizer
                .logoutRequestMatcher(new AntPathRequestMatcher(
                    "/auth/logout", "GET"
                ))
                .logoutSuccessUrl("/auth/login")
            )
            .build();
    }

    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
            .requestMatchers("/webjars/**");
    }
    
}
