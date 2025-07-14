package com.example.SpringbootwithDB.config;

import com.example.SpringbootwithDB.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SpringConfiguration {
    @Autowired
    private CustomUserDetailsService customerUserDetailsService;

   @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.
                csrf((CsrfConfigurer<HttpSecurity>csrf)->csrf.disable())
                .authorizeHttpRequests(auth ->{
                  //  auth.requestMatchers(HttpMethod.POST,"/employee").hasRole("ADMIN");
                  //  auth.requestMatchers(HttpMethod.PUT,"/employee").hasRole("ADMIN");
                   // auth.requestMatchers(HttpMethod.DELETE,"/employee").hasRole("ADMIN");
                  //  auth.requestMatchers(HttpMethod.GET,"/**").hasAnyRole("ADMIN","USER");
                    auth.requestMatchers(HttpMethod.POST,"/api/auth/register","/api/auth/login").permitAll();
                    auth.requestMatchers(HttpMethod.GET,"/employeeDTO").hasAnyRole("ADMIN","USER");
                    auth.anyRequest().authenticated();
                })
                .userDetailsService(customerUserDetailsService)
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }
   /* @Bean
    UserDetailsService userDetails(){
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();

        UserDetails sri = User.builder()
                .username("sri")
                .password(passwordEncoder().encode("sri@5064"))
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(admin,sri);

    }*/


}
