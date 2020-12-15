package com.example.okta;

import com.okta.spring.boot.oauth.Okta;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class OktaApplication {

	public static void main(String[] args) {
		SpringApplication.run(OktaApplication.class, args);
	}

}

@EnableWebSecurity
class OktaSecurity extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
				.anyRequest().authenticated()
				.and()
				.oauth2ResourceServer().jwt();

		Okta.configureResourceServer401ResponseBody(http);
	}
}

@RestController
class OktaController {
	@GetMapping("/")
	public String securedApiHello() {
		return "I'm a secured API";
	}
}