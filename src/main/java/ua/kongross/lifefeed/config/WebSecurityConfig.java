package ua.kongross.lifefeed.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizeRequests ->
                authorizeRequests
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/v3/api-docs",
                                "/login", "/sign_up").permitAll()
                        .anyRequest().authenticated()
        );

        http.csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }
}
