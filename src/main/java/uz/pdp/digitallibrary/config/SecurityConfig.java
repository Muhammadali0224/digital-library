package uz.pdp.digitallibrary.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import uz.pdp.digitallibrary.service.AuthService;
import uz.pdp.digitallibrary.security.SecurityFilter;

@EnableWebSecurity
@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final AuthService authService;
    private final SecurityFilter securityFilter;

    public SecurityConfig(@Lazy AuthService authService, @Lazy SecurityFilter securityFilter) {
        this.authService = authService;
        this.securityFilter = securityFilter;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        //disable csrf
        http.csrf(AbstractHttpConfigurer::disable);

        http.userDetailsService(authService);

        http.authorizeHttpRequests(conf-> conf
                        .requestMatchers( "/api/auth/**",
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html/**",
                                "/swagger-resources/**",
                                "/webjars/**")
                        .permitAll()
                        .anyRequest()
                        .authenticated()
                );

        http.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();


    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }





}
