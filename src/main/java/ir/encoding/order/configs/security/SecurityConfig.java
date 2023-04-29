package ir.encoding.order.configs.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	@Autowired
	private LoggerFilter loggerFilter;
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {		
		http.addFilterAt(jwtAuthenticationFilter, BasicAuthenticationFilter.class);
		http.addFilterBefore(loggerFilter, JwtAuthenticationFilter.class);
					
		http.authorizeHttpRequests((authorize) -> authorize				
		.requestMatchers("/tokens/*").permitAll()
		.requestMatchers("/").permitAll()					
		.anyRequest().authenticated());		
		
		return http.build();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}	
	
	@Bean
	ObjectMapper objectMapper() {
		return JsonMapper.builder()
			     .findAndAddModules()
			     .build();		
	}
	
}
