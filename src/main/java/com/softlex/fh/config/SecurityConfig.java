package com.softlex.fh.config;


import com.softlex.fh.service.token.CustomUserDetailsService;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private JwtAuthenticationFilter filter;
  private CustomUserDetailsService uds;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable()
        .httpBasic().disable()
        .cors()
        .and()
        .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
            authorizationManagerRequestMatcherRegistry
                .antMatchers("/auth/**").permitAll()
                .antMatchers("/api/user/**").hasRole("USER"))
        .userDetailsService(uds)
        .exceptionHandling()
        .authenticationEntryPoint(
            (request, response, authException) ->
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized")
        )
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  @Bean
  protected AuthenticationManager authenticationManager() throws Exception {
    return super.authenticationManager();
  }

}
