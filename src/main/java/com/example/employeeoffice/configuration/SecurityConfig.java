package com.example.employeeoffice.configuration;

import com.example.employeeoffice.security.UserDetailsServiceImpl;
import com.example.employeeoffice.security.security_util_a.AuthorityList;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.web.access.BearerTokenAccessDeniedHandler;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.interfaces.RSAPublicKey;
import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final RsaKeyProperties rsaKeyProperties;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider getAuthenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsServiceImpl);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    //    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        HttpSecurity httpSecurity = http
//                .cors(AbstractHttpConfigurer::disable)
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(auth ->
//                        auth
//                                .requestMatchers(AuthorityList.USER_LIST).hasRole(AuthorityList.USER)
//                                .requestMatchers(AuthorityList.ADMIN_LIST).hasRole(AuthorityList.ADMIN)
//                                .requestMatchers(AuthorityList.MANAGER_LIST).hasRole(AuthorityList.MANAGER)
//                                .requestMatchers(AuthorityList.GUEST_LIST).hasRole(AuthorityList.GUEST))
//                .sessionManagement(sessionManagement ->
//                        sessionManagement
//                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
//                .exceptionHandling(
//                        (ex) -> ex.authenticationEntryPoint(new BearerTokenAuthenticationEntryPoint())
//                                //Обработка исключений для управления точками входа аутентификации и обработчиками доступа.
//                                .accessDeniedHandler(new BearerTokenAccessDeniedHandler()))
//                .httpBasic(Customizer.withDefaults())
//                .formLogin(Customizer.withDefaults());
//        return httpSecurity.build();
//    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/token").permitAll()
                        .requestMatchers("/secure").hasAuthority("SCOPE_read")
                        .requestMatchers(AuthorityList.USER_LIST).hasRole(AuthorityList.USER)
                        .requestMatchers(AuthorityList.ADMIN_LIST).hasRole(AuthorityList.ADMIN)
                        .requestMatchers(AuthorityList.MANAGER_LIST).hasRole(AuthorityList.MANAGER)
                        .requestMatchers(AuthorityList.GUEST_LIST).hasRole(AuthorityList.GUEST)
                        .anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint(new BearerTokenAuthenticationEntryPoint())
                        .accessDeniedHandler(new BearerTokenAccessDeniedHandler()))
                .httpBasic(withDefaults())
                .formLogin(withDefaults());
        return http.build();
    }



    @Order(Ordered.HIGHEST_PRECEDENCE)
    @Bean

    public SecurityFilterChain tokenSecurityFilterChain(HttpSecurity http) throws Exception {
        return http
                //ограничивает эту конфигурацию только запросами на конкретный путь, что упрощает защиту специфических функций.
                .securityMatcher(new AntPathRequestMatcher("/token"))
                //убеждается, что только аутентифицированные пользователи могут выполнять операции с токенами.
                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())

                //Конфигурация создания сессий (SessionCreationPolicy.STATELESS указывает, что сервер не будет создавать или использовать сессию).
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                //CSRF: Настройка CSRF (Cross-Site Request Forgery) защиты.
                // В REST API обычно CSRF защита отключается, поскольку аутентификация основана на токенах.
                .csrf(AbstractHttpConfigurer::disable)
                .exceptionHandling(ex -> {
                    ex.authenticationEntryPoint(new BearerTokenAuthenticationEntryPoint());
                    ex.accessDeniedHandler(new BearerTokenAccessDeniedHandler());
                })
                .httpBasic(withDefaults())
                .build();
    }

    @Bean
    public JwtDecoder jwtDecoder() throws IOException, GeneralSecurityException {
        return NimbusJwtDecoder.withPublicKey((RSAPublicKey) rsaKeyProperties.getPublicKey()).build();
    }

    @Bean
    public JwtEncoder jwtEncoder() throws IOException, GeneralSecurityException {
        JWK jwk = new RSAKey.Builder((RSAPublicKey) rsaKeyProperties.getPublicKey())
                .privateKey(rsaKeyProperties.getPrivateKey())
                .build();
        return new NimbusJwtEncoder(new ImmutableJWKSet<>(new JWKSet(jwk)));
    }


    // определяет, какие источники, заголовки и методы разрешены для кросс-доменных запросов.
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        //Allowed Origins: Список источников, которые могут отправлять кросс-доменные запросы
//        configuration.setAllowedOrigins(List.of("https://localhost:8080"));
//        //Список заголовков, которые разрешено использовать при кросс-доменных запросах. List.of("*") означает, что разрешены все заголовки.
//        configuration.setAllowedHeaders(List.of("*"));
//        //Список HTTP методов, которые разрешено использовать при кросс-доменных запросах. В данном случае только метод GET.
//        configuration.setAllowedMethods(List.of("GET"));
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        // Вызов source.registerCorsConfiguration("/**", configuration) регистрирует созданную конфигурацию CORS для всех путей в приложении (/**).
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // Allow any origin
        configuration.setAllowedOrigins(List.of("*"));
        // Allow any header
        configuration.setAllowedHeaders(List.of("*"));
        // Allow any method
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        // Allow credentials
        configuration.setAllowCredentials(true);
        // Expose headers
        configuration.setExposedHeaders(List.of("Authorization", "Link", "X-Total-Count"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
