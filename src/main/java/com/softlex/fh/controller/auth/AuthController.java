package com.softlex.fh.controller.auth;

import com.softlex.fh.dto.request.LoginRequest;
import com.softlex.fh.dto.request.RegistrationRequest;
import com.softlex.fh.dto.response.TokenResponse;
import com.softlex.fh.service.user.UserService;
import java.io.IOException;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {

  private UserService userService;

  @PostMapping(value = "/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public TokenResponse registerHandler(RegistrationRequest registrationRequest) throws IOException {
    return userService.registerUser(registrationRequest);
  }

  @PostMapping("/login")
  public TokenResponse loginHandler(@RequestBody LoginRequest loginRequest) {
    return userService.login(loginRequest);
  }
}
