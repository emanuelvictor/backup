package br.com.emanuelvictor.authorization_server;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

/**
 * Created by emanuelvictor on 21/06/15.
 */
//@Configuration
//@EnableResourceServer
public class ResourceServerConfiguration //extends ResourceServerConfigurerAdapter
{

    // Configura a permissão das requisições
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//
//        http.authorizeRequests()
//                .antMatchers("/", "/bower_components/**").permitAll()
//                .anyRequest().authenticated()
//                .and().formLogin().loginPage("/login").permitAll();
//    }


}
