package com.tonde.maisonchapback.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import static com.tonde.maisonchapback.models.roles.Permission.*;
import static com.tonde.maisonchapback.models.roles.Role.ADMIN;
import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain apiSecurity(HttpSecurity http) throws Exception {

        http
                .csrf(
                        AbstractHttpConfigurer::disable
                )
                .authorizeHttpRequests(
                        (auth) -> auth
                                .requestMatchers(
                                        "/api/v1/auth/***",
                                        "/v2/api-docs",
                                        "/v3/api-docs",
                                        "/v3/api-docs/**",
                                        "/swagger-resources",
                                        "/swagger-resources/**",
                                        "/configuration/ui",
                                        "/configuration/security",
                                        "/swagger-ui/**",
                                        "/webjars/**",
                                        "/swagger-ui.html",
                                        "/actuator/**",
                                        "/api/houses/**",
                                        "/api/search/**"

                                )
                                .permitAll()
                                .requestMatchers(GET, "/api/v1/management/**")
                                .hasAnyAuthority(ADMIN_READ.name(), PROPRIO_READ.name())
                                .requestMatchers(POST, "/api/v1/management/**")
                                .hasAnyAuthority(ADMIN_CREATE.name(), PROPRIO_CREATE.name())
                                .requestMatchers(PUT, "/api/v1/management/**")
                                .hasAnyAuthority(ADMIN_UPDATE.name(), PROPRIO_UPDATE.name())
                                .requestMatchers(DELETE, "/api/v1/management/**")
                                .hasAnyAuthority(ADMIN_DELETE.name(), PROPRIO_DELETE.name())

                                .requestMatchers("/api/v1/admin/**").hasAnyRole(ADMIN.name())

                                .requestMatchers(GET, "/api/v1/admin/**")
                                .hasAuthority(ADMIN_READ.name())
                                .requestMatchers(POST, "/api/v1/admin/**")
                                .hasAuthority(ADMIN_CREATE.name())
                                .requestMatchers(PUT, "/api/v1/admin/**")
                                .hasAuthority(ADMIN_UPDATE.name())
                                .requestMatchers(DELETE, "/api/v1/admin/**")
                                .hasAuthority(ADMIN_DELETE.name())
                                .anyRequest().authenticated()


                ).sessionManagement(
                        (
                                httpSecuritySessionManagementConfigurer
                                        ->
                                        httpSecuritySessionManagementConfigurer
                                                .sessionCreationPolicy(
                                                        SessionCreationPolicy
                                                                .STATELESS
                                                )
                        )
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(
                        jwtAuthFilter,
                        UsernamePasswordAuthenticationFilter.class
                )
                .logout(
                        logout -> logout
                                .logoutUrl("/api/v1/auth/logout")
                                .addLogoutHandler(logoutHandler)
                                .logoutSuccessHandler(
                                        (
                                                httpServletRequest,
                                                httpServletResponse,
                                                authentication
                                        )
                                                -> SecurityContextHolder
                                                .clearContext())
                );



        return http.build();

    }

}
