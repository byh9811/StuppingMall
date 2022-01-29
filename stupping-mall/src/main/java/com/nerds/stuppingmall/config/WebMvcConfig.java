package com.nerds.stuppingmall.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.nerds.stuppingmall.filter.HeaderFilter;
import com.nerds.stuppingmall.interceptor.JwtTokenInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(jwtTokenInterceptor())
				.addPathPatterns("/member/findAll");
	}
	
	@Bean
	public FilterRegistrationBean<HeaderFilter> getFilterRegistrationBean() {
		FilterRegistrationBean<HeaderFilter> registrationBean = new FilterRegistrationBean<>(createHeaderFilter());
		registrationBean.setOrder(Integer.MIN_VALUE);
		registrationBean.addUrlPatterns("/*");
		return registrationBean;
	}
	
	@Bean
	public HeaderFilter createHeaderFilter() {
		return new HeaderFilter();
	}

	private JwtTokenInterceptor jwtTokenInterceptor() {
		return new JwtTokenInterceptor();
	}
}
