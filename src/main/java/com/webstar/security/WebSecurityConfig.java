package com.webstar.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;


import com.webstar.services.UserService;
/*
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{

    @Autowired
    UserService userService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        final String[] PUBLIC_MATCHERS = {
                "/css/**",
                "/js/**",
                "/img/**",
                "/",
                "/register",
                "/about",
                "/contactus"

        };
        http
            .authorizeRequests()
            .antMatchers(PUBLIC_MATCHERS)
            .permitAll()
            
            .anyRequest()
            .authenticated()
            .and()
            
            .formLogin()
            .loginPage("/login")
            .defaultSuccessUrl("/myhome")
            .permitAll()
            .and()
            
             .logout()
            .permitAll();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {

        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }

}
*/