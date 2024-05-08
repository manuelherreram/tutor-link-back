package com.proyecto.tutorlink.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()  // Deshabilitar CSRF
                .authorizeRequests()
                .antMatchers("/h2-console/**").permitAll()  // Permitir todos los requests a la consola H2
                .antMatchers("/api/**").permitAll()  // Permitir todos los requests a los endpoints API
                .anyRequest().authenticated()  // Requiere autenticación para cualquier otro request
                .and()
                .headers().frameOptions().disable();  // Permitir frames para la consola H2
    }
}
