package com.example.lab12.Config;

import com.example.lab12.Service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ConfigSecurity {

    private final MyUserDetailsService myUserDetailsService;


    @Bean
    public DaoAuthenticationProvider daoauthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        //User name
        daoAuthenticationProvider.setUserDetailsService(myUserDetailsService);
        //Password
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());

        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authenticationProvider(daoauthenticationProvider())
                .authorizeHttpRequests()
                //register
                .requestMatchers("/api/v1/auth/register").permitAll()
                //get all users
                .requestMatchers("/api/v1/auth/get").hasAuthority("ADMIN")
                //Update user
                .requestMatchers("/api/v1/auth/update/{user_id}").hasAuthority("USER")
                //Delete user
                .requestMatchers("/api/v1/auth/delete/{user_id}").hasAuthority("ADMIN")
                //get all blogs
                .requestMatchers("/api/v1/blog/get").hasAuthority("ADMIN")
                //get my blogs                           //add blog                //update blog
                .requestMatchers("/api/v1/blog/get-my", "/api/v1/blog/add" , "/api/v1/blog/update/{auth_id}/{blog_id}").hasAuthority("USER")
                //delete blog
                .requestMatchers("/api/v1/blog/delete/{auth_id}/{blog_id}").hasAnyAuthority("ADMIN","USER")
                .requestMatchers("/api/v1/blog/get-blog-byId/{auth_id}/{blog_id}" ).hasAuthority("USER")
                .requestMatchers("/api/v1/blog/get-blog-byT/{auth_id}/{title}").permitAll()
                .anyRequest().authenticated()
                .and()
                .logout().logoutUrl("/api/v1/auth/logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .httpBasic();

        return http.build();
    }


}
