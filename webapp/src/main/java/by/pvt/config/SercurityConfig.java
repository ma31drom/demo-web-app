package by.pvt.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Configuration
public class SercurityConfig extends WebSecurityConfigurerAdapter implements InitializingBean {

	@Autowired
	private SpringTemplateEngine engine;

	@Autowired
	private UserDetailsService service;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/")
		.permitAll()
		.antMatchers("/login")
		.permitAll()
		.antMatchers("/register")
		.permitAll()
		.and()
		.formLogin()
		.loginPage("/login")
		.usernameParameter("login")
		.passwordParameter("password")
		.and()
		.logout()
		.logoutSuccessUrl("/")
		.and()
		.csrf()
		.and()
		.rememberMe()
		.rememberMeParameter("rememberme")
		.userDetailsService(service)
		.and()
	    .exceptionHandling()
	    .accessDeniedPage("/access_denied")	    ;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		engine.addDialect(new SpringSecurityDialect());

	}
}
