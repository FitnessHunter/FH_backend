package com.softlex.fh.service.token;

import com.softlex.fh.dto.user.UserPrincipal;
import com.softlex.fh.entity.user.User;
import com.softlex.fh.entity.user.UserRepository;
import java.util.Collections;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

  private UserRepository userRepository;

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
}
