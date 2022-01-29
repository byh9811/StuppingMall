package com.nerds.stuppingmall.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.nerds.stuppingmall.filter.CustomAuthenticationFilter;
import com.nerds.stuppingmall.handler.CustomLoginSuccessHandler;
import com.nerds.stuppingmall.provider.CustomAuthenticationProvider;
import com.nerds.stuppingmall.service.AuthenticationService;
import com.nerds.stuppingmall.service.MemberService;

import lombok.RequiredArgsConstructor;

@Configuration		// Configure Bean 명시
@EnableWebSecurity	// spring security config 명시
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {	// 필요한 메서드 구현하여 설정
	private final AuthenticationService authenticationService;

	// resource/static 기준으로 권한 없이 접근할 수 있게 함
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/js/**", "/css/**", "/img/**", "/lib/**");
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {	// 각 http request에 대한 보안 설정
		http.csrf().disable().authorizeRequests()
				.antMatchers("/admin/**").hasRole("ADMIN")
				.antMatchers("/**").permitAll()
			.and()
				.exceptionHandling().accessDeniedPage("/forbidden")
			.and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
				.formLogin().disable()
			.addFilterBefore(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		
//		http.authorizeRequests()
//				.antMatchers("/admin/**").hasRole("ADMIN")
//				.antMatchers("/member/**").hasRole("MEMBER")
//				.antMatchers("/seller/**").hasRole("SELLER")
//				.antMatchers("/**").permitAll()
//			.and()
//				.formLogin()
//				.loginPage("/login")
//				.defaultSuccessUrl("/")
//				.usernameParameter("userId")
//				.failureUrl("/passwordFindPage")
//			.and()
//				.logout()
//				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//				.logoutSuccessUrl("/")
//				.invalidateHttpSession(true)
//			.and()
//				.exceptionHandling().accessDeniedPage("/forbidden");
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Bean
	public CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
		CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManager());
		customAuthenticationFilter.setFilterProcessesUrl("/user/login");
		customAuthenticationFilter.setAuthenticationSuccessHandler(customLoginSuccessHandler());
		customAuthenticationFilter.afterPropertiesSet();
		
		return customAuthenticationFilter;
	}
	
	@Bean
	public CustomLoginSuccessHandler customLoginSuccessHandler() {
		return new CustomLoginSuccessHandler();
	}
	
	@Bean
	public CustomAuthenticationProvider customAuthenticationProvider() {
		return new CustomAuthenticationProvider(authenticationService, bCryptPasswordEncoder());
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) {
		authenticationManagerBuilder.authenticationProvider(customAuthenticationProvider());
	}
}
