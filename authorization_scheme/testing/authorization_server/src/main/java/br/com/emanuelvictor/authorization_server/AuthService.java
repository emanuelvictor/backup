package br.com.emanuelvictor.authorization_server;

import java.security.Principal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@SpringBootApplication
@Import({OAuth2ServerConfig.class, AuthServerSecurityConfiguration.class})
public class AuthService  extends WebMvcConfigurerAdapter {
	
	public static void main(final String[] args) {
		SpringApplication.run(AuthService.class, args);
	}

	@RequestMapping("/user")
	@ResponseBody
	public Principal user(Principal user) {
		return user;
	}

	@RequestMapping("/")
	@ResponseBody
	public String logged() {
		return "You are logged in authorization server";
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("login");
	}
}
