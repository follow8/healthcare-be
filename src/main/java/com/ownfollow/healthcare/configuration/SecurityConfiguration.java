package com.ownfollow.healthcare.configuration;

import com.ownfollow.healthcare.security.JwtAuthenticationFilter;
import com.ownfollow.healthcare.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.zalando.problem.spring.web.advice.security.SecurityProblemSupport;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import(SecurityProblemSupport.class)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  private final UserDetailsService userDetailsService;
  private final SecurityProblemSupport problemSupport;
  private final JwtTokenProvider jwtTokenProvider;

  public SecurityConfiguration(@Qualifier("applicationUserDetailsService") UserDetailsService userDetailsService,
                               JwtTokenProvider jwtTokenProvider,
                               SecurityProblemSupport problemSupport) {
    this.userDetailsService = userDetailsService;
    this.jwtTokenProvider = jwtTokenProvider;
    this.problemSupport = problemSupport;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth
            .userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder());
  }

  @Override
  public void configure(WebSecurity web) {
    web.ignoring()
            .antMatchers("/h2-console/**");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
            .csrf().disable()
            .addFilterBefore(corsFilter(), UsernamePasswordAuthenticationFilter.class)
            .exceptionHandling()
            .authenticationEntryPoint(problemSupport)
            .accessDeniedHandler(problemSupport)
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .antMatchers("/api/authenticate").permitAll()
            .antMatchers("/api/**").authenticated();

    http.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
            UsernamePasswordAuthenticationFilter.class);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  private CorsFilter corsFilter() {
    UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
    configSource.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
    return new CorsFilter(configSource);
  }
}
