package ua.kongross.lifefeed.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ua.kongross.lifefeed.service.UserService;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests(requests -> requests
                        .antMatchers("/").permitAll())
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/feed")
                        .permitAll())
                .logout(logout -> logout
                        .logoutSuccessUrl("/")
                        .permitAll())
                .userDetailsService(userDetailsService)
                .headers().frameOptions().sameOrigin();

        return http.build();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
