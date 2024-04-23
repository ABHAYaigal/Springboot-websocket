package com.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.User;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       
    	http
		.authorizeHttpRequests(authConfig -> {
			//authConfig.requestMatchers("/index", "/login", "/login-error", "/style1.css").permitAll();
			authConfig.requestMatchers("/login", "/login-error").permitAll();
			authConfig.anyRequest().authenticated();
		})
		.sessionManagement(session -> session
				.maximumSessions(1)
				.maxSessionsPreventsLogin(true)
				.expiredUrl("/login?expired"))
		.formLogin(login -> login
				.loginPage("/login")
				.failureUrl("/login-error"))
		.logout(logout -> logout
				.logoutSuccessUrl("/login")
				.clearAuthentication(true)
				.deleteCookies("JSESSIONID"));
	return http.build();
	}
   
    
  @Bean
  public InMemoryUserDetailsManager userDetailsService() {
      UserDetails user1 = User.withDefaultPasswordEncoder()
              .username("Abhay")
              .password("password")
              .roles("USER")
              .build();

      UserDetails user2 = User.withDefaultPasswordEncoder()
              .username("Madhav")
              .password("password")
              .roles("USER")
              .build();
      
      UserDetails user3 = User.withDefaultPasswordEncoder()
              .username("Raj")
              .password("password")
              .roles("USER")
              .build();


      return new InMemoryUserDetailsManager(user1, user2, user3);
  }
    
}
