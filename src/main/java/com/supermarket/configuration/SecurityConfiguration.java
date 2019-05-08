package com.supermarket.configuration;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private DataSource dataSource;

	@Value("${spring.queries.users-query}")
	private String usersQuery;

	@Value("${spring.queries.roles-query}")
	private String rolesQuery;

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.
			jdbcAuthentication()
				.usersByUsernameQuery(usersQuery)
				.authoritiesByUsernameQuery(rolesQuery)
				.dataSource(dataSource)
				.passwordEncoder(bCryptPasswordEncoder);
	}

	protected void configure(HttpSecurity http) throws Exception {	
		http.
			authorizeRequests()
				.antMatchers("/").permitAll()
				.antMatchers("/login").permitAll()
				.antMatchers("/register").permitAll()
				.antMatchers("/webjars/**", "/error**").permitAll()
				.antMatchers("/admin/**").hasAuthority("ADMIN").anyRequest().authenticated()
				.antMatchers("/user/**").hasAuthority("USER").anyRequest().authenticated()
				.and().formLogin()
				.loginPage("/login").successHandler(new AuthenticationSuccessHandler() {
				    @Override
				    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
				    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
						
				    }
				})
				.failureUrl("/login?error=true")
				.usernameParameter("email")
				.passwordParameter("password")
				.and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/").and().exceptionHandling()
				.accessDeniedPage("/access-denied");
	}

	public void configure(WebSecurity web) throws Exception {
		web
			.ignoring()
				.antMatchers("/resources/**", "/static/**", "/css/**", "/js/**");
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}