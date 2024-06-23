package com.restapi.react.reactapi.basic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
public class BasicAuthSecurityConfig {

    // by jus returning http.buil() it means we have turned off
    // all of our security and now going to config one by one follow things
    // Filter chain
    // auth all request
    // basic auth
    // disabling csrf
    // stateless rest api so there are no sessions in there

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // enabled auth on all requests
        return http
                .authorizeHttpRequests(
                        auth -> 
                        auth
                        // Next Section Step adding permission to all OPTION req
                        // VERY IMP antrequestmatcher is now requestMatchers
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .anyRequest().authenticated())
                // enabling basic auth matlb it enables pass access on api
                .httpBasic(Customizer.withDefaults())

                // now creating api stateless and disbaling csrf

                .sessionManagement(
                        session -> session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS))

                .csrf(csrf -> csrf.disable()).build();
    }
}
