package com.softlex.fh.service.user;

import com.softlex.fh.dto.request.LoginRequest;
import com.softlex.fh.dto.request.RegistrationRequest;
import com.softlex.fh.dto.response.TokenResponse;
import com.softlex.fh.dto.response.UserInfoResponse;
import com.softlex.fh.dto.user.UserPrincipal;
import java.io.IOException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {

  TokenResponse registerUser(RegistrationRequest registrationRequest) throws IOException;

  TokenResponse login(LoginRequest loginRequest);

  UserPrincipal loadUserByUsername(String email) throws UsernameNotFoundException;

  UserInfoResponse getUserDetails(Long userId);

}
