package com.example.employeeoffice.configuration;

import com.example.employeeoffice.security.UserDetailsServiceImpl;
import com.example.employeeoffice.security.security_util_a.AuthorityList;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
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
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers(AuthorityList.USER_LIST).hasRole(AuthorityList.USER)
                                .requestMatchers(AuthorityList.ADMIN_LIST).hasRole(AuthorityList.ADMIN)
                                .requestMatchers(AuthorityList.MANAGER_LIST).hasRole(AuthorityList.MANAGER)
                                .requestMatchers(AuthorityList.GUEST_LIST).hasRole(AuthorityList.GUEST)
                                .anyRequest().authenticated())

                .httpBasic(Customizer.withDefaults())  // Использование базовой аутентификации
                .logout(logoutPage -> logoutPage.logoutSuccessUrl("/"))
                .formLogin(Customizer.withDefaults());
        return http.build();
    }

}
