package com.softlex.fh.service.security;

import com.softlex.fh.dto.user.UserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class JWTSecurityAssistant implements SecurityAssistant {

  @Override
  public Long getCurrentUserId() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null) {
      @SuppressWarnings("unchecked")
      Long userId = ((UserPrincipal) authentication.getPrincipal()).getId();
      return userId;
    } else {
      return null;
    }

  }
}
