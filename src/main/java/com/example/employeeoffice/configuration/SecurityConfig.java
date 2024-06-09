package com.example.employeeoffice.configuration;


import com.example.employeeoffice.security.UserDetailsServiceImpl;
import com.example.employeeoffice.security.security_util_a.AuthorityRoleList;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static com.example.employeeoffice.security.security_util_a.AuthorityRoleList.*;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig implements WebMvcConfigurer {
    private final UserDetailsServiceImpl userDetailsService;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider getAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                //.cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()

                        .requestMatchers(USER_LIST).hasRole(USER)
                        .requestMatchers(ADMIN_LIST).hasRole(ADMIN)
                        .requestMatchers(MANAGER_LIST).hasRole(MANAGER)
                        .requestMatchers(GUEST_LIST).hasRole(GUEST)
                        .anyRequest().authenticated())
//                .sessionManagement(sessionManagement ->
//                        sessionManagement
//                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                //.formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults());
        return http.build();

    }
//    @Bean
//    public FilterRegistrationBean<ClearCookiesFilter> clearCookiesFilter(){
//        FilterRegistrationBean<ClearCookiesFilter> registrationBean = new FilterRegistrationBean<>();
//        registrationBean.setFilter(new ClearCookiesFilter());
//        registrationBean.addUrlPatterns("/swagger-ui.html");
//        return registrationBean;
//    }
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/swagger-ui.html")
//                .addResourceLocations("classpath:/static/");
//    }
}

//        http
//                .cors(withDefaults())
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/auth/**").permitAll()
//                        .requestMatchers("/swagger-ui/**").permitAll()
//                        .requestMatchers("/v3/api-docs/**").permitAll()
//                        .requestMatchers("/swagger-resources/**").permitAll()
//                        .requestMatchers("/swagger-ui.html").permitAll()
//                        .requestMatchers("/webjars/**").permitAll()
//                        .requestMatchers("/favicon.ico").permitAll()
//                        .requestMatchers(HttpMethod.GET, "/api/**").permitAll()
//                        //.requestMatchers("/token").permitAll()
//                        .requestMatchers(AuthorityList.USER_LIST).hasRole("USER")
////                        .requestMatchers(AuthorityList.ADMIN_LIST).hasRole("ADMIN")
////                        .requestMatchers(AuthorityList.MANAGER_LIST).hasRole("MANAGER")
////                        .requestMatchers(AuthorityList.GUEST_LIST).hasRole("GUEST")
//                        .anyRequest().authenticated())
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
//                .exceptionHandling(ex -> ex
//                        .authenticationEntryPoint(new BearerTokenAuthenticationEntryPoint())
//                        .accessDeniedHandler(new BearerTokenAccessDeniedHandler()))
//                .httpBasic(withDefaults());
//                //.formLogin(withDefaults());
//        return http.build();
    //}

//    @Order(Ordered.HIGHEST_PRECEDENCE)
//    @Bean
//    public SecurityFilterChain tokenSecurityFilterChain(HttpSecurity http) throws Exception {
//        return http
//                .securityMatcher(new AntPathRequestMatcher("/token"))
//                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .csrf(AbstractHttpConfigurer::disable)
//                .exceptionHandling(ex -> {
//                    ex.authenticationEntryPoint(new BearerTokenAuthenticationEntryPoint());
//                    ex.accessDeniedHandler(new BearerTokenAccessDeniedHandler());
//                })
//                .httpBasic(withDefaults())
//                .build();
//    }
//
//    @Bean
//    public JwtDecoder jwtDecoder() throws IOException, GeneralSecurityException {
//        RSAPublicKey publicKey = (RSAPublicKey) rsaKeyProperties.getPublicKey();
//        if (publicKey == null) {
//            throw new IllegalArgumentException("Public key must not be null");
//        }
//        return NimbusJwtDecoder.withPublicKey(publicKey).build();
//    }
//
//    @Bean
//    public JwtEncoder jwtEncoder() throws IOException, GeneralSecurityException {
//        RSAPublicKey publicKey = (RSAPublicKey) rsaKeyProperties.getPublicKey();
//        RSAPrivateKey privateKey = rsaKeyProperties.getPrivateKey();
//        if (publicKey == null || privateKey == null) {
//            throw new IllegalArgumentException("Public and private keys must not be null");
//        }
//        JWK jwk = new RSAKey.Builder(publicKey)
//                .privateKey(privateKey)
//                .build();
//        return new NimbusJwtEncoder(new ImmutableJWKSet<>(new JWKSet(jwk)));
//    }
//
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(List.of("*"));
//        configuration.setAllowedHeaders(List.of("*"));
//        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//        configuration.setAllowCredentials(true);
//        configuration.setExposedHeaders(List.of("Authorization", "Link", "X-Total-Count"));
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }

