package com.example.StudentMaterialServer.config;

import com.example.StudentMaterialServer.jwtFilter.Jwtfilter;
import com.example.StudentMaterialServer.services.Userservicee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private Userservicee userService;

    @Autowired
    private Jwtfilter jwtfilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
        ;
    }


    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


//    @Override
//    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
//    public AuthenticationManager authenticationManagerBean() throws Exception{
//        return super.authenticationManagerBean();
//    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().disable();
        http.csrf().disable().authorizeRequests().antMatchers("/refreshToken","/authenticate", "/sign-up")
                .permitAll().antMatchers(HttpMethod.OPTIONS,"/**")
                .permitAll().anyRequest().authenticated().and().exceptionHandling().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authenticationProvider(authenticationProvider());
        http.addFilterBefore(jwtfilter, UsernamePasswordAuthenticationFilter.class);
    }



}
