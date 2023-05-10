package com.softlex.fh.dto.user;

import com.fasterxml.jackson.annotation.JsonView;
import com.softlex.fh.commons.Views;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

  @JsonView(Views.UserInfo.class)
  private Long id;

  @JsonView(Views.UserInfo.class)
  private String email;

  private String password;
  @JsonView(Views.UserInfo.class)
  private String firstName;
  @JsonView(Views.UserInfo.class)
  private String lastName;
  @JsonView(Views.UserInfo.class)
  private byte[] imageBytes;
}
