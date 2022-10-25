package net.tecgurus.gamestore.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import net.tecgurus.gamestore.service.UserService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserService userService;
	
	@Bean
	public PasswordEncoder passswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setPasswordEncoder(this.passswordEncoder());
		auth.setUserDetailsService(this.userService);
		return auth;
	}
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.authenticationProvider(this.authenticationProvider());
		
		//JDBC Authentication
		/*auth
			.jdbcAuthentication()
			.dataSource(dataSource)
			.passwordEncoder(null)
			.usersByUsernameQuery("SELECT name, password FROM users")
			.authoritiesByUsernameQuery("SELECT * FROM authorities WHERE user_id = ?");*/
		
		
		//On memory authentication
		/*auth.
			inMemoryAuthentication()
			.passwordEncoder(null)
			.withUser("user@user.user").password("123").roles("USER")
			.and()
			.withUser("admin@admin.admin").password("123").roles("ADMIN");*/
		
	}
	
	
}
