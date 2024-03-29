//package com.leticoin.webapp.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    public void configure(WebSecurity web){
//        web.ignoring().antMatchers("/css/**");
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        String[] staticResources = {
//                "/css/**"
//        };
//
//        http
//                .authorizeRequests()
//                    .antMatchers("/login").permitAll()
//                    .anyRequest().authenticated()
//                    .and()
//
//                .formLogin()
//                    .loginPage("/login").permitAll()
//                    .defaultSuccessUrl("/", true)
//                    .failureUrl("/login?error=true")
//                    .permitAll();
//    }
//}
