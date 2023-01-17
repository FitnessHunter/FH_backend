package com.softlex.fh.web;

import com.softlex.fh.service.security.SecurityAssistant;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
@Slf4j
public class BaseControllerAdvice {

  @Setter(onMethod = @__({@Autowired}))
  private SecurityAssistant securityAssistant;


  protected Long getCurrentUserId() {
    return securityAssistant != null ? securityAssistant.getCurrentUserId() : null;
  }

}
