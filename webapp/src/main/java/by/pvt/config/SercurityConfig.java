package by.pvt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SercurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/register").permitAll()
			.and()
		.formLogin()
			.loginPage("/login")
			.usernameParameter("login")
			.passwordParameter("password")
			.and().csrf()
			.and().exceptionHandling()
			.accessDeniedPage("/access_denied");
	}
}
