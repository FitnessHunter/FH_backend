package com.softlex.fh.service.user;

import com.softlex.fh.dto.request.LoginRequest;
import com.softlex.fh.dto.request.RegistrationRequest;
import com.softlex.fh.dto.response.TokenResponse;
import com.softlex.fh.dto.user.UserDto;
import java.io.IOException;

public interface UserService {

  TokenResponse registerUser(RegistrationRequest registrationRequest) throws IOException;

  TokenResponse login(LoginRequest loginRequest);

  UserDto getUserDetails(Long userId);

}
