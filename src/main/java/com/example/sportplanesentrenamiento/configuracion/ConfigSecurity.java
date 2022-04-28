
package com.example.sportplanesentrenamiento.configuracion;

import com.example.sportplanesentrenamiento.servicios.ProfesorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(1)
public class ConfigSecurity extends WebSecurityConfigurerAdapter {

    @Autowired//vamos a usar este usuarioservice para poder buscar en la bd algun usuario por nombre de usuario
    private ProfesorServiceImpl profesorServiceImpl;

    @Autowired//configuracion del manejador de seguridad de spring security
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(profesorServiceImpl)//el servicio que debemos utilizar para autentificar un usuario
                .passwordEncoder(new BCryptPasswordEncoder());//una vez que encuentre ese usuario, cual el es encoder que va a utilizar para comparar nuestras contraseñas encriptadas
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/css/*", "/js/*", "/img/*", "/assets/**", "uploads/*" ,"/**").permitAll()
                .and().formLogin()
                .loginPage("/login") // Que formulario esta mi login
                .loginProcessingUrl("/logincheck")
                .usernameParameter("username") // Como viajan los datos del logueo
                .passwordParameter("password")// Como viajan los datos del logueo
                .defaultSuccessUrl("/bienvenido") // A que URL viaja
                .permitAll()
                .and().logout() // Aca configuro la salida
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .permitAll().and().csrf().disable();
    }


}
