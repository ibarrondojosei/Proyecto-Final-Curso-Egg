//
//package com.example.sportplanesentrenamiento.web;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter{
//    
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//        auth.inMemoryAuthentication()
//                .withUser("admin@aa")
//                .password("{noop}123")
//                .roles("ADMIN", "USER")
//                .and()
//                .withUser("user@aa")
//                .password("{noop}123")
//                .roles("USER");
//    }
//    
//    @Override
//    protected void configure(HttpSecurity http) throws Exception{
//        http.authorizeHttpRequests()
//            .antMatchers("/crear/**","/editar/**", "/eliminar/")
//                .hasRole("ADMIN")
//            .antMatchers("/")
//                .hasAnyRole("ADMIN", "USER")
//            .and()
//                .formLogin()
//                .loginPage("/login")
//            .and()
//                .exceptionHandling().accessDeniedPage("/errores/403")
//            ;
//    }
//}
