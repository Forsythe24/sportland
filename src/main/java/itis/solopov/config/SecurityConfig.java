package itis.solopov.config;

import itis.solopov.filter.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
//@ComponentScan("itis.solopov.security")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.csrf().disable()
//                .formLogin()
//                .loginPage("/login")
//                .loginProcessingUrl("/login/process")
//                .usernameParameter("email")
//                .passwordParameter("password")
//                .defaultSuccessUrl("/user", true)
//                .failureUrl("/login?error=true")
//                .and()
//                .logout()
//                .logoutSuccessUrl("/login")
//                .and()
//                .exceptionHandling();
//
//        return httpSecurity.authorizeRequests()
//                .antMatchers("/sign_up", "/login").anonymous()
//                .antMatchers("/user").hasAnyRole("USER", "ADMIN")
//                .antMatchers("/admin/**").hasRole("ADMIN")
//                .and().build();

        return httpSecurity.httpBasic().disable()
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests(auth -> {
                            auth
                                    .antMatchers("api/auth/login", "api/auth/token").permitAll()
                                    .antMatchers("/index", "api/auth/sign_up", "api/user", "/login", "/instructors").anonymous()
                                    .antMatchers("/profile").hasAnyRole("USER", "ADMIN")
                                    .antMatchers("/admin/**").hasRole("ADMIN")
                                    .and().addFilterAfter(jwtFilter, UsernamePasswordAuthenticationFilter.class);
                        }
                )
                .build();
    }
}
