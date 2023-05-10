package com.softlex.fh.config;

import com.softlex.fh.service.token.CustomUserDetailsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class CustomAuthProvider implements AuthenticationProvider {

  private CustomUserDetailsService customUserDetailsService;
  private PasswordEncoder passwordEncoder;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {

    log.info("In CustomAuthProvider.authenticate(): ");

    // Get the User from UserDetailsService
    String providedUsername = authentication.getPrincipal().toString();
    UserDetails user = customUserDetailsService.loadUserByUsername(providedUsername);
    log.info(
        "User Details from UserService based on username-" + providedUsername + " : " + user);

    String providedPassword = authentication.getCredentials().toString();
    String correctPassword = user.getPassword();

    if (!passwordEncoder.matches(providedPassword, correctPassword)) {
      throw new RuntimeException("Incorrect Credentials");
    }

    Authentication authenticationResult =
        new UsernamePasswordAuthenticationToken(user, authentication.getCredentials(),
            user.getAuthorities());
    return authenticationResult;
  }

  @Override
  public boolean supports(Class<?> authentication) {
   return authentication.equals(UsernamePasswordAuthenticationToken.class);
  }

}
