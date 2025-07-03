package com.epharm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class AppConfig {
    @Bean
    PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	
    	 http
         .csrf(csrf -> csrf
             .ignoringRequestMatchers("/h2-console/**") // allow POSTs in H2 console
             .disable()
         )
         .headers(headers -> headers
             .frameOptions(frame -> frame.disable()) // allow iframe (for H2)
         )
         .authorizeHttpRequests(requests -> requests
             // Public routes
             .requestMatchers("/login", "/register", "/css/**", "/js/**", "/h2-console/**").permitAll()

             // View-only access for users and admins
             .requestMatchers("/productlist").hasAnyRole("USER", "ADMIN")

             // Restricted access for admins only
             .requestMatchers("/createproduct", "/updateproducts", "/deleteproducts/**").hasRole("ADMIN")

             // Anything else requires authentication
             .anyRequest().authenticated()
         )
         .formLogin(login -> login
             .loginPage("/login")
             .loginProcessingUrl("/login")
             .defaultSuccessUrl("/productlist", true)
             .failureUrl("/login?error=true")
             .permitAll()
         )
         .logout(logout -> logout
             .logoutUrl("/logout")
             .logoutSuccessUrl("/login?logout=true")
             .permitAll()
         );

     return http.build();
    	
    }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
		
    	return configuration.getAuthenticationManager();
    	
    	
    }
    
}