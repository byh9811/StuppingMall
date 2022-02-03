package com.nerds.stuppingmall.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.nerds.stuppingmall.service.CustomUserDetailsService;
import com.nerds.stuppingmall.service.MemberService;

@Configuration		// Configure Bean 명시
@EnableWebSecurity	// spring security config 명시
public class SecurityConfig extends WebSecurityConfigurerAdapter {	// 필요한 메서드 구현하여 설정
	
	@Autowired
	CustomUserDetailsService customUserDetailsService;
//	
//	@Override
//	public void configure(WebSecurity web) throws Exception {	// resource/static 기준으로 권한 없이 접근할 수 있게 함
//		web.ignoring().antMatchers("/js/**", "/css/**", "/img/**", "/lib/**");
//	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {	// 각 http request에 대한 보안 설정
		http.authorizeRequests()
				.antMatchers("/admin/**").hasRole("ADMIN")
				.antMatchers("/customer/**").hasRole("CUSTOMER")
				.antMatchers("/supplier/**").hasRole("SUPPLIER")
				.antMatchers("/**").permitAll()
			.and()
				.formLogin()
				.loginPage("/login")
				.defaultSuccessUrl("/")
				.usernameParameter("id")
			.and()
				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/")
				.invalidateHttpSession(true)
			.and()
				.exceptionHandling().accessDeniedPage("/forbidden");
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
	}
}
