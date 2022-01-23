package com.lkats.sample.BaseEdge.config;

import com.lkats.sample.BaseEdge.component.RestAuthenticationEntryPoint;
import com.lkats.sample.BaseEdge.component.SuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class AuthConfig extends WebSecurityConfigurerAdapter {

    private final SuccessHandler successHandler;
    private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    public AuthConfig(RestAuthenticationEntryPoint restAuthenticationEntryPoint, SuccessHandler successHandler) {
        this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
        this.successHandler = successHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .and()

                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().csrfTokenRepository(new CookieCsrfTokenRepository())

                .and()
                .exceptionHandling()
                .authenticationEntryPoint(restAuthenticationEntryPoint)

                .and()

                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers("/landing").hasRole("USER")

                .and()
                .formLogin()
                .successHandler(successHandler)
                .failureHandler(new SimpleUrlAuthenticationFailureHandler())

                .and()
                .logout()
                .permitAll();
    }

    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin").password("admin").roles("USER");
    }
}
