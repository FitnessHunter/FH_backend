package com.softlex.fh.controller;

import com.softlex.fh.dto.request.LoginRequest;
import com.softlex.fh.entity.user.User;
import com.softlex.fh.entity.user.UserRepository;
import com.softlex.fh.service.token.JwtService;
import java.util.Collections;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {

  private UserRepository userRepository;
  private JwtService jwtUtil;
  private AuthenticationManager authManager;
  private PasswordEncoder passwordEncoder;

  @PostMapping("/register")
  public Map<String, Object> registerHandler(@RequestBody User user) {
    String encodedPass = passwordEncoder.encode(user.getPassword());
    user.setPassword(encodedPass);
    user = userRepository.save(user);
    String token = jwtUtil.generateToken(user.getEmail());
    return Collections.singletonMap("jwt-token", token);
  }

  @PostMapping("/login")
  public Map<String, Object> loginHandler(@RequestBody LoginRequest body) {
    try {
      UsernamePasswordAuthenticationToken authInputToken =
          new UsernamePasswordAuthenticationToken(body.getEmail(), body.getPassword());

      authManager.authenticate(authInputToken);

      String token = jwtUtil.generateToken(body.getEmail());

      return Collections.singletonMap("jwt-token", token);
    } catch (AuthenticationException authExc) {
      throw new RuntimeException("Invalid Login Credentials");
    }
  }
}
