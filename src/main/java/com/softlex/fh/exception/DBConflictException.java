package com.softlex.fh.exception;

public class DBConflictException extends RuntimeException {

  private static final long serialVersionUID = 1948948970699319949L;

  public DBConflictException(String message) {
    super(message);
  }
}
