package com.example.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.enums.Role;
import com.example.services.UserDetailsServiceImpl;

@EnableWebSecurity
public class WebSecurityConfig {
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/login").permitAll()
			.and()
			.authorizeRequests().antMatchers("/admin/**").hasAuthority(Role.ADMIN.toString())
			.and()
			.formLogin( form -> form.loginPage("/login")
									.usernameParameter("email")
									.loginProcessingUrl("/login")
									.failureUrl("/login?error"))
			.authorizeRequests().anyRequest().authenticated();
        return http.build();
    }
	
	@Bean
	protected PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	protected UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl();
	}

}
