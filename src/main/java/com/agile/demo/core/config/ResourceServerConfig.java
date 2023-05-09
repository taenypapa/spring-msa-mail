package com.agile.demo.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	String [] matchers = {
			"/swagger-ui/**",
			"/swagger-ui.html",
			"/swagger-resources/**",
			"/v3/api-docs/**",
			"/v2/api-docs/**",
			"/tokens",
			"/webjars/**",
			"/h2-console/**",
			"/**/swagger-ui/**",
			"/**/swagger-ui.html",
			"/**/swagger-resources/**",
			"/**/v3/api-docs/**",
			"/**/v2/api-docs/**",
			"/**/api-docs/**",
			"/**/webjars/**",
			"/management/documents/files/downloads/**"
	};

	@Autowired
	private Environment environment;

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.resourceId("mail");
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		// h2 데이터를 확인하기위해 h2-console url의 권한을 permitAll으로 바꾸어 줍니다.

		http.authorizeRequests()
				.antMatchers(matchers).permitAll()
				.antMatchers("/api/**").authenticated();

		http.cors().disable();

		http.csrf().disable()
				.headers().disable();
	}
}