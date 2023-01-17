package com.softlex.fh.service.user;

import com.softlex.fh.dto.request.RegistrationRequest;
import com.softlex.fh.dto.response.UserInfoResponse;
import com.softlex.fh.entity.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

  User toEntity(RegistrationRequest registrationRequest, byte[] imageBytes);

  @Mapping(source = "imageBytes", target = "image")
  UserInfoResponse toUserInfoResponse(User user);

}
