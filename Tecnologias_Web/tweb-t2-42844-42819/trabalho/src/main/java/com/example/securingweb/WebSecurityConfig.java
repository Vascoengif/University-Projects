package com.example.securingweb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
    
    @Override
	protected void configure(HttpSecurity http) throws Exception {
		http
                                .csrf().disable()
                                .authorizeRequests()
                                .antMatchers("/", "/css/*","/newClientRegist", "/getClassif", "/addNewEvent", "/MainPage", "/getSubscriptions", "/registerParticipant", "/getParticipantsList", "/searchEvents", "/addNewTimeOfRun","/images/*", "/javascript/*").permitAll()
                                        .antMatchers("redirect:/MainPage_Athlete").hasRole("USER")
                                        .antMatchers("redirect:/MainPage_Admin").hasRole("ADMIN")
				.and()
			.formLogin()
				.loginPage("/login")
                                .defaultSuccessUrl("/role-controller", true)
				.permitAll()
				.and()
			.logout()
				.permitAll();
	}

}