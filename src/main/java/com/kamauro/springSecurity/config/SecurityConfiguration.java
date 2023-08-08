package com.kamauro.springSecurity.config;

import java.net.PasswordAuthentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import com.kamauro.springSecurity.model.PerfilTipo;
import com.kamauro.springSecurity.service.UsuarioService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    // private static final String ADMIN = PerfilTipo.ADMIN.getDesc();
    // private static final String MEDICO = PerfilTipo.MEDICO.getDesc();
    // private static final String PACIENTE = PerfilTipo.PACIENTE.getDesc();

    @Autowired
    private UsuarioService serviceUsuario;


    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                    .authorizeHttpRequests((authorize) -> authorize
                    .requestMatchers("/css/**", "/image/**", "/js/**").permitAll()
                    .requestMatchers("/", "/home").permitAll()

                    .requestMatchers("/u/**").hasAuthority("ADMIN")

                    .requestMatchers("/medicos/**").hasAuthority("MEDICO")

                    .anyRequest().authenticated()
                    
                    ).formLogin((formLogin) -> formLogin 
                        .loginPage("/login")
                        .defaultSuccessUrl("/", true)
                        .failureUrl("/login-error")
                        .permitAll()
                    ).logout((logout) -> logout
                        .logoutSuccessUrl("/")
                        .deleteCookies("JSESSIONID")
                    ).exceptionHandling((acessoNegado) -> acessoNegado 
                        .accessDeniedPage("/acesso-negado")
                    );
                        
               
                // .requestMatchers("/u/novo/cadastro", "/u/cadastro/realizado", "/u/cadastro/paciente/salvar").permitAll()
                // .requestMatchers("/u/confirmacao/cadastro").permitAll()
                // .requestMatchers("/u/p/**").permitAll()

                // acessos privados admin
                // .requestMatchers("/u/editar/senha", "/u/confirmar/senha").hasAnyAuthority(PACIENTE, MEDICO)
                

                // acessos privados medicos
                // .requestMatchers("/medicos/especialidade/titulo/*").hasAnyAuthority(PACIENTE, MEDICO)
                // .requestMatchers("/medicos/dados", "/medicos/salvar", "/medicos/editar").hasAnyAuthority(MEDICO, ADMIN)
                // .requestMatchers("/medicos/**").hasAuthority(MEDICO)

                // acessos privados pacientes
                // .requestMatchers("/pacientes/**").hasAuthority(PACIENTE)
                
                // acessos privados especialidades
                // .requestMatchers("/especialidades/datatables/server/medico/*").hasAnyAuthority(MEDICO, ADMIN)
                // .requestMatchers("/especialidades/titulo").hasAnyAuthority(MEDICO, ADMIN, PACIENTE)
                // .requestMatchers("/especialidades/**").hasAuthority(ADMIN)

                
                      
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // @Bean
    // public PasswordAuthentication authenticationManager (HttpSecurity http, 
    //                                                      PasswordEncoder passwordEncoder,
    //                                                      UsuarioService userDetailsService) throws Exception {
    //     return http.getSharedObject(AuthenticationManagerBuilder.class)
    //                .userDetailsService(userDetailsService)
    //                .passwordEncoder(passwordEncoder)
    //                .and()
    //                .build();
    // }

    // @Bean
    // public SessionAuthenticationStrategy sessionAuthStrategy() {
    //     return new RegisterSessionAuthenticationStrategy(sessionRegistry());
    // }

    // @Bean
    // public SessionRegistry sessionRegistry() {
    //     return new SessionRegistryImpl();
    // }

    // @Bean
    // public ServletListenerRegistrationBean<?> servletListenerRegistrationBean() {
    //     return new ServletListenerRegistrationBean<>(new HttpSessionEventPublisher());
    // }
    
}
