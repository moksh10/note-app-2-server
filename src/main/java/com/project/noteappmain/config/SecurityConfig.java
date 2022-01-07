package com.project.noteappmain.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.project.noteappmain.filters.JwtRequestFilter;
import com.project.noteappmain.services.UserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
    @Autowired
    public BCryptPasswordEncoder passwordEncoder;
    
	
    @Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
    
    @Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}

	@Override
    protected void configure(HttpSecurity security) throws Exception
    {
		
    	security.cors().and().csrf().disable().authorizeRequests()
    	.antMatchers("/authFailure").permitAll()
    	.antMatchers(HttpMethod.POST,"/auth").permitAll()
    	.antMatchers(HttpMethod.POST,"/noteuser").permitAll()
    	.antMatchers(HttpMethod.GET,"/noteuser/allUsers").permitAll()
    	.antMatchers("/noteuser").authenticated()
    	.antMatchers("/notes").authenticated()
    	.antMatchers("/notes/*").authenticated()
    	.antMatchers("/isAuth").authenticated()
    	.antMatchers("/logoutUser").authenticated()
        .and().formLogin().and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    	security.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    	security.exceptionHandling()
        .authenticationEntryPoint(
            (request, response, ex) -> {
                response.sendRedirect("/authFailure");
            }
        );
    }
}