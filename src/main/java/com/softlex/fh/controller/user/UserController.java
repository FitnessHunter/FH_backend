package com.softlex.fh.controller.user;

import com.softlex.fh.dto.response.UserInfoResponse;
import com.softlex.fh.service.security.SecurityAssistant;
import com.softlex.fh.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserController {

  private UserService userService;
  private SecurityAssistant securityAssistant;

  @GetMapping
  public UserInfoResponse getCurrentUserInfo(){
    Long userId = securityAssistant.getCurrentUserId();
    return userService.getUserDetails(userId);
  }

}
