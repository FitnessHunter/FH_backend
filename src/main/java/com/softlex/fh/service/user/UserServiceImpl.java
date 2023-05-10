package com.softlex.fh.service.user;

import com.softlex.fh.dto.request.LoginRequest;
import com.softlex.fh.dto.request.RegistrationRequest;
import com.softlex.fh.dto.response.TokenResponse;
import com.softlex.fh.dto.user.UserDto;
import com.softlex.fh.entity.user.User;
import com.softlex.fh.entity.user.UserRepository;
import com.softlex.fh.exception.DBConflictException;
import com.softlex.fh.service.token.JwtService;
import java.io.IOException;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

  private PasswordEncoder passwordEncoder;
  private UserRepository userRepository;
  private JwtService jwtUtil;
  private UserMapper userMapper;

  @Override
  public TokenResponse registerUser(RegistrationRequest registrationRequest) throws IOException {
    String email = registrationRequest.getEmail();
    if (userRepository.existsUserByEmail(email)) {
      throw new DBConflictException(String.format("User with email %s already exists", email));
    }
    String encodedPass = passwordEncoder.encode(registrationRequest.getPassword());
    registrationRequest.setPassword(encodedPass);
    MultipartFile image = registrationRequest.getImage();
    byte[] bytes = null;
    if (image != null) {
      bytes = image.getBytes();
    }
    User user = userMapper.toEntity(registrationRequest, bytes);
    User savedUser = userRepository.save(user);
    String token = jwtUtil.generateToken(savedUser);
    return new TokenResponse(token);
  }

  @Override
  public TokenResponse login(LoginRequest loginRequest) {
    Optional<User> userOptional = userRepository.findByEmail(loginRequest.getEmail());
    User user = userOptional.orElseThrow(
        () -> new AuthenticationCredentialsNotFoundException("Invalid Login Credentials"));
    String token = jwtUtil.generateToken(user);
    return new TokenResponse(token);
  }

  @Override
  public UserDto getUserDetails(Long userId) {
    Optional<User> userOptional = userRepository.findById(userId);
    User user = userOptional.orElseThrow(
        () -> new SecurityException(String.format("User with id %d not found", userId)));
    return userMapper.toDto(user);
  }
}
