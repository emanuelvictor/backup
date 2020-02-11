package br.com.emanuelvictor.authorization_server;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.security.Principal;

@Configuration
@SpringBootApplication
@EnableAutoConfiguration
@Import({/*ResourceServerConfiguration.class,*/ OAuth2ServerConfiguration.class, WebSecutityConfiguration.class})
//@Controller
public class Application  extends WebMvcConfigurerAdapter {

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }

//    @RequestMapping("/user")
//    @ResponseBody
//    public Principal user(Principal user) {
//        return user;
//    }
//
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/login").setViewName("login");
//    }
//
//    @Bean
//    InternalResourceViewResolver internalResourceViewResolver () {
//        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//        viewResolver.setPrefix("/WEB-INF/jsp/");
//        viewResolver.setSuffix(".jsp");
//        return viewResolver;
//    }

}
