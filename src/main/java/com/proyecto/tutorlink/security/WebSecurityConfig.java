package com.proyecto.tutorlink.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfigurationSource;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and() // Habilita CORS
                .csrf().disable() // Deshabilita CSRF
                .authorizeRequests()
                .antMatchers("/h2-console/**", "/swagger-ui.html", "/v2/api-docs", "/swagger-resources/**", "/webjars/**").permitAll() // Permite acceso a Swagger UI y H2 console

                .antMatchers("/api/teachers/admin").hasRole("ADMIN") // Requiere rol ADMIN para acceder a /api/teachers/admin

                .antMatchers("/api/teachers/**").permitAll() // Permite acceso a todos los endpoints de /api/teachers
                .antMatchers("/api/users/**").permitAll() // Permite acceso a todos los endpoints de /api/users
                .anyRequest().authenticated() // Requiere autenticación para cualquier otro request
                .and()
                .headers().frameOptions().disable(); // Permite frames para la consola H2
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedMethod("GET");
        configuration.addAllowedMethod("POST");
        configuration.addAllowedMethod("PUT");
        configuration.addAllowedMethod("DELETE");
        configuration.addAllowedMethod("OPTIONS");
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(false);
        configuration.setAllowedOriginPatterns(Arrays.asList("http://*", "https://*"));
        // Usa patrones para permitir cualquier origen seguro

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
