package br.com.orangetalents.forum.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {


    /*
        This method is responsible for the authorization configuration
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/topics").permitAll()
                .antMatchers(HttpMethod.GET, "/topicos/*").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable();
    }


    /*
        This method is responsible for configuring authentication
     */
    @Override
    public void configure(WebSecurity web) throws Exception {

    }


    /*
        This method is responsible for the configuration of statistical resources (js, css, images, etc.)
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

    }


}
