package com.softlex.fh.config;

import com.softlex.fh.dto.user.UserPrincipal;
import com.softlex.fh.service.token.CustomUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CustomAuthProvider implements AuthenticationProvider {

  private CustomUserDetailsService customUserDetailsService;
  private PasswordEncoder passwordEncoder;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {

    System.out.println("\nIn CustomAuthProvider.authenticate(): ");

    // Get the User from UserDetailsService
    String providedUsername = authentication.getPrincipal().toString();
    UserDetails user = customUserDetailsService.loadUserByUsername(providedUsername);
    System.out.println("User Details from UserService based on username-" + providedUsername + " : " + user);

    String providedPassword = authentication.getCredentials().toString();
    String correctPassword = user.getPassword();

    System.out.println("Provided Password - " + providedPassword + " Correct Password: " + correctPassword);

    // Authenticate
    // If Passwords don't match throw and exception
    if (!passwordEncoder.matches(providedPassword, correctPassword)) {
      throw new RuntimeException("Incorrect Credentials");
    }

    System.out.println("Passwords Match....\n");

    // return Authentication Object
    Authentication authenticationResult =
        new UsernamePasswordAuthenticationToken(user, authentication.getCredentials(), user.getAuthorities());
    return authenticationResult;
  }

  @Override
  public boolean supports(Class<?> authentication) {
    System.out.println("\nIn CustomAuthProvider.supports(): ");
    System.out.println("Checking whether CustomAuthProvider supports Authentication type\n");
    return authentication.equals(UsernamePasswordAuthenticationToken.class);
  }

}
