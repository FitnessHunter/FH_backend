package com.softlex.fh.dto.response;

import lombok.Data;

@Data
public class UserInfoResponse {

  private Long id;

  private String email;
  private String firstName;
  private String lastName;
  private byte[] image;
}
