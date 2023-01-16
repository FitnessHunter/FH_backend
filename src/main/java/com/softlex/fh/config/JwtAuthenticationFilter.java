package com.softlex.fh.config;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.softlex.fh.service.token.JwtService;
import com.softlex.fh.service.token.CustomUserDetailsService;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  public static final String BEARER_PREFIX = "Bearer ";
  private CustomUserDetailsService userDetailsService;
  private JwtService jwtService;


  @Override
  protected void doFilterInternal(HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    String authHeader = request.getHeader("Authorization");
    if (authHeader != null && !authHeader.isBlank() && authHeader.startsWith(BEARER_PREFIX)) {
      String jwt = authHeader.substring(BEARER_PREFIX.length());
      if (jwt == null || jwt.isBlank()) {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JWT Token in Bearer Header");
      } else {
        try {
          String email = jwtService.validateTokenAndRetrieveSubject(jwt);
          UserDetails userDetails = userDetailsService.loadUserByUsername(email);
          UsernamePasswordAuthenticationToken authToken =
              new UsernamePasswordAuthenticationToken(email, userDetails.getPassword(), userDetails.getAuthorities());
          if (SecurityContextHolder.getContext().getAuthentication() == null) {
            SecurityContextHolder.getContext().setAuthentication(authToken);
          }
        } catch (JWTVerificationException exc) {
          response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JWT Token");
        }
      }
    }
    filterChain.doFilter(request, response);
  }

}
