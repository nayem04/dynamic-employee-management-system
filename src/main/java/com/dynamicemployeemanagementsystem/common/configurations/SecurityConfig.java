package com.dynamicemployeemanagementsystem.common.configurations;

import com.dynamicemployeemanagementsystem.common.filters.JwtAuthenticationFilter;
import com.dynamicemployeemanagementsystem.common.routing.Router;
import com.dynamicemployeemanagementsystem.common.utilities.PasswordUtil;
import com.dynamicemployeemanagementsystem.domain.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final UserService userService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(UserService userService, JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.userService = userService;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        /*httpSecurity.csrf(AbstractHttpConfigurer::disable);

        // All request should be authenticated
        httpSecurity.authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                authorizationManagerRequestMatcherRegistry.anyRequest().authenticated());

        httpSecurity.formLogin(Customizer.withDefaults()); // For browser (Enable Form Login with default settings)
        httpSecurity.httpBasic(Customizer.withDefaults()); // For RestApi (Enable basic login from client like postman with default settings)
        // If I remove form login http basic give popup for browser, and we can give credential on that popup for every request

        *//* Stateless session (Create a new session for every request and by doing this you have to pass credential for every request)
        For browser it is a problem because we can't pass credential for the api (for every request)
        But in postman, can send credential for every request *//*
        httpSecurity.sessionManagement(httpSecuritySessionManagementConfigurer ->
                httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return httpSecurity.build();*/

        //All the above code is done here by using builder pattern
        return httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                        authorizationManagerRequestMatcherRegistry
                                .requestMatchers(HttpMethod.GET, "/api/users/*").hasAnyRole("ADMIN", "EMPLOYEE")
                                .requestMatchers(HttpMethod.POST, "/api/users").hasAnyRole("ADMIN", "EMPLOYEE")
                                .requestMatchers(HttpMethod.PATCH, "/api/users/*").hasAnyRole("ADMIN", "EMPLOYEE")
                                .requestMatchers(HttpMethod.GET, "/api/employees/*").hasAnyRole("ADMIN", "EMPLOYEE")
                                .requestMatchers(HttpMethod.POST, Router.LOGIN).permitAll()
                                .anyRequest().hasRole("ADMIN"))
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(httpSecuritySessionManagementConfigurer ->
                        httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    // Database authentication
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(PasswordUtil.getBCryptPasswordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userService);
        return daoAuthenticationProvider;
    }

    //In memory authentication
    /*@Bean
    public UserDetailsService userDetailsService() {
        UserDetails user1 = User
                .withDefaultPasswordEncoder()
                .username("john")
                .password("123123")
                .roles("USER")
                .build();

        UserDetails user2 = User
                .withDefaultPasswordEncoder()
                .username("doe")
                .password("123456")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user1, user2);
    }*/

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
