package com.softlex.fh.config;


import com.softlex.fh.service.token.CustomUserDetailsService;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private static final String[] AUTH_WHITELIST = {
      "/authenticate",
      "/swagger-resources/**",
      "/swagger-ui/**",
      "/v3/api-docs",
      "/webjars/**",
      "/auth/**"
  };
  private static final List<String> allowedOrigins = List.of("http://localhost:3000");
  private final Logger log = LogManager.getLogger(SecurityConfig.class);
  private JwtAuthenticationFilter filter;
  private CustomUserDetailsService uds;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable()
        .httpBasic().disable()
        .cors()
        .configurationSource(request -> {
          CorsConfiguration corsConfiguration = new CorsConfiguration();
          corsConfiguration.setAllowedOriginPatterns(allowedOrigins);
          corsConfiguration.setAllowedHeaders(List.of("*"));
          corsConfiguration.setAllowedMethods(
              List.of("GET", "POST", "PUT", "DELETE", "PUT", "OPTIONS", "PATCH", "DELETE"));
          corsConfiguration.setAllowCredentials(true);
          corsConfiguration.applyPermitDefaultValues();
          return corsConfiguration;
        })
        .and()
        .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
            authorizationManagerRequestMatcherRegistry
                .antMatchers(AUTH_WHITELIST).permitAll()
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
