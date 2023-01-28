package com.softlex.fh.web;

import com.softlex.fh.exception.DBConflictException;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

  @ExceptionHandler({DBConflictException.class})
  public void handleDatabaseConflictException(HttpServletResponse response, DBConflictException ex,
      HttpServletRequest request) throws IOException {
    log.info("A database conflict has occurred.");
    log.debug("Request url: {}", request.getRequestURL());
    response.sendError(HttpStatus.CONFLICT.value());
  }
}
