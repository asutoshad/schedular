package com.schedular.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.schedular.service.impl.CustomOAuth2UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	

    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    
    @Autowired
    private CustomOAuth2UserService customOAuth2UserService;

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//            .csrf(csrf -> csrf.disable())
//            .authorizeHttpRequests(auth -> auth
//                .requestMatchers("/", "/login", "/signup",
//                                 "/saveuser", "/save", "/tt",
//                                 "/api/public/**","/api/auth/**").permitAll()
//                .anyRequest().authenticated()
//            )
//            .oauth2Login(oauth -> 
//                oauth.loginPage("/login")
//                .userInfoEndpoint(userInfo -> userInfo
//                        .userService(customOAuth2UserService) // use injected bean
//                        )
//                .defaultSuccessUrl("/dash", true)
//            )
//            .httpBasic(basic -> {})
//            .sessionManagement(session ->
//                session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
//            );
//
//        return http.build();
//    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())  // REST API, so CSRF can be disabled
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**", "/api/public/**").permitAll()
                .anyRequest().authenticated()
            )
            .sessionManagement(session -> 
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // REST API
            )
            .httpBasic(); // optional, can remove if you use JWT

        return http.build();
    }



    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
    
//    @Bean
//    public CustomOAuth2UserService customOAuth2UserService() {
//        return customOAuth2UserService;
//    }
    
    
    
}
