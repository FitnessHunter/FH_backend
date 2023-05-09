package com.softlex.fh.config;

import static com.softlex.fh.service.token.JwtService.JWT_CLAIM_EMAIL;
import static com.softlex.fh.service.token.JwtService.JWT_CLAIM_ID;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.softlex.fh.service.token.CustomUserDetailsService;
import com.softlex.fh.service.token.JwtService;
import java.io.IOException;
import java.util.Map;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  public static final String BEARER_PREFIX = "Bearer ";
  private CustomUserDetailsService customUserDetailsService;
  private JwtService jwtService;


  @Override
  protected void doFilterInternal(HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    String authHeader = request.getHeader("Authorization");
    if (authHeader != null && !authHeader.isBlank() && authHeader.startsWith(BEARER_PREFIX)) {
      String jwt = authHeader.substring(BEARER_PREFIX.length());
      if (jwt.isBlank()) {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JWT Token in Bearer Header");
      } else {
        try {
          Map<String, Object> userInfoMap = jwtService.validateTokenAndRetrieveSubject(jwt);
          UserDetails userDetails = customUserDetailsService.loadUserByUsername((String) userInfoMap.get(JWT_CLAIM_EMAIL));
          UsernamePasswordAuthenticationToken authToken =
              new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
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
