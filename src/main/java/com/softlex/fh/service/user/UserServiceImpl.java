package com.softlex.fh.service.user;

import com.softlex.fh.dto.request.LoginRequest;
import com.softlex.fh.dto.request.RegistrationRequest;
import com.softlex.fh.dto.response.TokenResponse;
import com.softlex.fh.dto.response.UserInfoResponse;
import com.softlex.fh.dto.user.UserPrincipal;
import com.softlex.fh.entity.user.User;
import com.softlex.fh.entity.user.UserRepository;
import com.softlex.fh.exception.DBConflictException;
import com.softlex.fh.service.token.JwtService;
import java.io.IOException;
import java.util.Collections;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
  private AuthenticationManager authManager;

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
    try {
      Optional<User> userOptional = userRepository.findByEmail(loginRequest.getEmail());
      if (userOptional.isPresent()) {
        User user = userOptional.get();
        String token = jwtUtil.generateToken(user);
        return new TokenResponse(token);
      } else {
        throw new AuthenticationCredentialsNotFoundException("Invalid Login Credentials");
      }
    } catch (AuthenticationException authExc) {
      throw new RuntimeException("Invalid Login Credentials");
    }
  }

  @Override
  public UserPrincipal loadUserByUsername(String email) throws UsernameNotFoundException {
    Optional<User> userRes = userRepository.findByEmail(email);
    if (userRes.isEmpty()) {
      throw new UsernameNotFoundException("Could not findUser with email = " + email);
    }
    User user = userRes.get();
    return new UserPrincipal(
        user.getId(),
        email,
        user.getPassword(),
        Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
  }

  @Override
  public UserInfoResponse getUserDetails(Long userId) {
    Optional<User> userOptional = userRepository.findById(userId);
    if (userOptional.isPresent()) {
      User user = userOptional.get();
      UserInfoResponse userDetails = userMapper.toUserInfoResponse(user);
      return userDetails;
    } else {
      throw new SecurityException(String.format("User with id %d not found", userId));
    }
  }
}
