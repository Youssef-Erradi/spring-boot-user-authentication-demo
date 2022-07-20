package com.example.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.enums.Role;
import com.example.services.UserDetailsServiceImpl;

@Configuration
public class WebSecurityConfig {
	
	private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	private final UserDetailsService userDetailsService = new UserDetailsServiceImpl();
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/login").permitAll()
			.and()
			.authorizeRequests().antMatchers("/admin/**").hasRole(Role.ADMIN.toString())
			.and()
			.formLogin( form -> form.loginPage("/login")
									.usernameParameter("email")
									.loginProcessingUrl("/login")
									.defaultSuccessUrl("/")
									.failureUrl("/login?error")	)
			.authorizeRequests().anyRequest().hasRole(Role.USER.toString());
		
		http.authenticationProvider(authenticationProvider());
		
        return http.build();
    }
	
	@Bean
	protected DaoAuthenticationProvider authenticationProvider(){
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setPasswordEncoder(passwordEncoder);
		authenticationProvider.setUserDetailsService(userDetailsService);
		return authenticationProvider;
	}


}
