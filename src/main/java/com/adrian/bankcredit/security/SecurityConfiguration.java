package com.adrian.bankcredit.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder bc;
	
	@Autowired
	DataSource dataSource;
	
	@Value("${spring.queries.users-query}")
	private String usersQuery;
	
	@Value("${spring.queries.roles-query}")
	private String rolesQuery;
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.jdbcAuthentication()
		.usersByUsernameQuery(usersQuery)
		.authoritiesByUsernameQuery(rolesQuery)
		.dataSource(dataSource)
		.passwordEncoder(bc);
	}
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().anyRequest().permitAll();
    }
	
	/*@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
				.antMatchers(HttpMethod.GET, "/bank/proposal/{id:\\d+}").hasAnyAuthority("USER","EMPLOYEE","ADMIN")
				.antMatchers(HttpMethod.GET, "/bank/proposal").hasAnyAuthority("EMPLOYEE","ADMIN")
				.antMatchers(HttpMethod.GET, "/bank/proposal/notchecked").hasAnyAuthority("EMPLOYEE","ADMIN")
				.antMatchers(HttpMethod.GET, "/bank/proposal/notverified").hasAnyAuthority("EMPLOYEE","ADMIN")
				.antMatchers(HttpMethod.GET, "/bank/proposal/verified").hasAnyAuthority("EMPLOYEE","ADMIN")
				.antMatchers(HttpMethod.POST, "/bank/proposal").hasAnyAuthority("USER","EMPLOYEE","ADMIN")
				.antMatchers(HttpMethod.PUT, "/bank/proposal/{id:\\d+}").hasAnyAuthority("EMPLOYEE","ADMIN")
				.antMatchers(HttpMethod.PUT, "/bank/proposal/{id:\\d+}/check").hasAnyAuthority("EMPLOYEE","ADMIN")
				.antMatchers(HttpMethod.PUT, "/bank/proposal/{id:\\d+}/verify").hasAnyAuthority("ADMIN")
				.antMatchers(HttpMethod.DELETE, "/bank/proposal/{id:\\d+}").hasAnyAuthority("EMPLOYEE","ADMIN")
				.antMatchers(HttpMethod.POST, "bank/consumer").permitAll()
				.antMatchers(HttpMethod.POST, "/bank/creditdetails").hasAnyAuthority("ADMIN","EMPLOYEE")
				.antMatchers(HttpMethod.PUT, "/bank/creditcard/{id:\\d+}/usemoney").hasAuthority("ADMIN")
				.antMatchers(HttpMethod.PUT, "/bank/creditcard/{id:\\d+}/addmoney").hasAuthority("ADMIN")
				.antMatchers(HttpMethod.PUT, "/bank/bankresources/{id:\\d+}/usemoney").hasAuthority("ADMIN")
				.antMatchers(HttpMethod.PUT, "/bank/bankresources/{id:\\d+}/addmoney").hasAuthority("ADMIN")
				
				.anyRequest().authenticated()
				.and().formLogin()
				//.loginPage("/login")
				.failureUrl("/login?error=true")
				.defaultSuccessUrl("/").usernameParameter("login")
				.passwordParameter("password")
				.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/")
				.and().exceptionHandling().accessDeniedPage("/denied");
	}*/

	@Override
	public void configure(WebSecurity web) throws Exception {
		// TODO Auto-generated method stub
		web.ignoring()
				.antMatchers("/resources/**", "/statics/**", "/css/**", "/js/**", "/images/**", "/incl/**");
		
	}
}