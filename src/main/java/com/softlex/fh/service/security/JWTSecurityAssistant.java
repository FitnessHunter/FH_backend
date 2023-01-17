package com.softlex.fh.service.security;

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
      Long userId = ((Integer) authentication.getPrincipal()).longValue();
      return userId;
    } else {
      return null;
    }

  }
}
