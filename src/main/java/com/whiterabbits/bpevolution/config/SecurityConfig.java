package com.whiterabbits.bpevolution.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;

import javax.sql.DataSource;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    public void globalConfig(AuthenticationManagerBuilder auth, DataSource dataSource) throws Exception{
        //creation des user et de leur role en mémoire
        //Add password storage format, for plain text, add {noop}
        //auth.inMemoryAuthentication().withUser("admin").password("{noop}123").roles("ADMIN", "USER");
        //auth.inMemoryAuthentication().withUser("user").password("{noop}123").roles("USER");

        //utilisation du user et roles en BDD pour vérifier accès
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select username as principal, password as credentials, true from users where username = ?")
                .authoritiesByUsernameQuery("select user_username as principal, roles_name as role from users_role where user_username = ?")
                .rolePrefix("ROLE_")
                .passwordEncoder(new BCryptPasswordEncoder());

        System.out.println();
    }

    @Override
    protected  void configure(HttpSecurity http) throws Exception{
        http.csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(new Http403ForbiddenEntryPoint() {
                })
                .and()
                .formLogin()
                .loginProcessingUrl("/login")
                .and()
                .logout()
                .logoutUrl("/logout")
                .invalidateHttpSession(true)
                .and()
                .authorizeRequests()
                .antMatchers("/favicon.ico").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/logout").permitAll()
                .antMatchers("/h2-console").permitAll()
                .antMatchers("/App/**").authenticated()
                    .anyRequest().permitAll()
                .antMatchers("/Bp/**").authenticated()
                    .anyRequest().permitAll()
                .antMatchers("/css/**","/js/**","/images/**").permitAll().anyRequest().authenticated();

    }


}
