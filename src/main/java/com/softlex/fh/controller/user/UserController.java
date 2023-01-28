package com.softlex.fh.controller.user;

import com.softlex.fh.dto.response.UserInfoResponse;
import com.softlex.fh.service.security.SecurityAssistant;
import com.softlex.fh.service.user.UserService;
import com.softlex.fh.web.BaseControllerAdvice;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserController extends BaseControllerAdvice {

  private UserService userService;

  @GetMapping
  public UserInfoResponse getCurrentUserInfo(){
    Long userId = getCurrentUserId();
    return userService.getUserDetails(userId);
  }

}
