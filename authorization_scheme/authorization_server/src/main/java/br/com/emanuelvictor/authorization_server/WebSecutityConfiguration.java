package br.com.emanuelvictor.authorization_server;

import br.com.emanuelvictor.authorization_server.entity.user.UserAccount;
import br.com.emanuelvictor.authorization_server.service.ClientService;
import br.com.emanuelvictor.authorization_server.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 * Created by emanuelvictor on 21/06/15.
 */
@Configuration
//@EnableWebSecurity
//@EnableWebMvcSecurity
public class WebSecutityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserAccountService userAccountService;

    @Override
    protected void configure( AuthenticationManagerBuilder auth ) throws Exception
    {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setUserDetailsService( this.userAccountService );

        provider.setPasswordEncoder( new ShaPasswordEncoder() );

        auth.authenticationProvider( provider );
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception
    {
        return super.authenticationManagerBean();
    }

    @Bean
    @Primary
    public TokenStore tokenStore()
    {
        TokenStore tokenStore = new InMemoryTokenStore();
        return tokenStore;
    }

    @Bean
    public DefaultTokenServices defaultTokenServices()
    {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setSupportRefreshToken( true );
        defaultTokenServices.setTokenStore( this.tokenStore() );
        return defaultTokenServices;
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {

        http
                .formLogin().loginPage("/login").permitAll()
                .and()
                .requestMatchers().antMatchers("/login", "/oauth/authorize", "/oauth/confirm_access", "/webapp")
                .and()
                .authorizeRequests().anyRequest().authenticated();

    }
}
