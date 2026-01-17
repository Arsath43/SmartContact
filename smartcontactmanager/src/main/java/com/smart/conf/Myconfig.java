package com.smart.conf;

//import java.beans.Customizer;
import org.springframework.security.config.Customizer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class Myconfig {

	@Bean
	public UserDetailsService userdetailservice() {
		return new UserDetailServiceImp1();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
		
	}
	
	 @Bean
	    public AuthenticationProvider authenticationProvider() {
	        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(userdetailservice());
//	        authProvider.setUserDetailsService(userdetailservice());
	        authProvider.setPasswordEncoder(passwordEncoder());
	        return authProvider;
	    }
	 
//	 @Override
	 protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		 auth.authenticationProvider(authenticationProvider());
		 
	 }
	 
//@Override
//	 protected void configure(HttpSecurity http) throws Exception{
//			http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN")
//			.antMatchers("/user/**").hasRole("USER")
//			 .antMatchers("/**").permitAll().and().formLogin().and().csrf().disable();
//	 }
//	 @Override
//	 protected void configure(HttpSecurity http) throws Exception {
//	     http
//	         .authorizeRequests()
//	             .antMatchers("/admin/**").hasRole("ADMIN")
//	             .antMatchers("/user/**").hasRole("USER")
//	             .antMatchers("/**").permitAll()
//	         .and()
//	         .formLogin()
//	         .and()
//	         .csrf().disable();
//	 }
	 
	 @Bean
	 public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	     http
	         .authorizeHttpRequests(auth -> auth
	             .requestMatchers("/admin/**").hasRole("ADMIN")
	             .requestMatchers("/user/**").hasRole("USER")
	             .anyRequest().permitAll()
	         )
	         .formLogin(form -> form.loginPage("/signin")
	        		 .loginProcessingUrl("/dologin")
	        		 .defaultSuccessUrl("/user/index")
//	        		 .failureUrl("/login-fail")
	                 .permitAll()
)	                 
	         .logout(logout -> logout
	                 .logoutUrl("/logout")
	                 .logoutSuccessUrl("/signin?logout")
	             )
	         .csrf(csrf -> csrf.disable());

	     return http.build();
	 }
}
