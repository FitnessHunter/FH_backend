package com.softlex.fh.service.user;

import com.softlex.fh.dto.request.RegistrationRequest;
import com.softlex.fh.dto.user.UserDto;
import com.softlex.fh.entity.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

  User toEntity(RegistrationRequest registrationRequest, byte[] imageBytes);

  UserDto toDto(User user);

}
