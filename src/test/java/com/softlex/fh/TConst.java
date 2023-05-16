package com.softlex.fh;

import com.softlex.fh.dto.program.ProgramDto;
import com.softlex.fh.dto.request.CreateProgramRequest;
import com.softlex.fh.dto.user.UserDto;
import com.softlex.fh.entity.program.Program;
import com.softlex.fh.entity.user.User;

public class TConst {

    public static final String DEFAULT_USER_EMAIL = "testmail@mail.com";
    public static final User DEFAULT_USER = User.builder()
            .id(1L)
            .firstName("FName")
            .lastName("LName")
            .email(DEFAULT_USER_EMAIL)
            .password("password")
            .build();
    public static final UserDto DEFAULT_USER_DTO = UserDto.builder()
            .id(1L)
            .firstName("FName")
            .lastName("LName")
            .email(DEFAULT_USER_EMAIL)
            .password("password")
            .build();
    public static final User DEFAULT_USER_SPORTSMEN = User.builder()
            .id(2L)
            .firstName("FName sportsmen")
            .lastName("LName sportsmen")
            .email("sportsmen@mail.com")
            .password("password")
            .build();
    public static final Program DEFAULT_PROGRAM = Program.builder()
            .programName("Program name")
            .programDescription("Program description")
            .id(1L)
            .owner(DEFAULT_USER)
            .sportsman(DEFAULT_USER_SPORTSMEN)
            .build();
    public static final UserDto DEFAULT_USER_SPORTSMEN_DTO = UserDto.builder()
            .id(2L)
            .firstName("FName sportsmen")
            .lastName("LName sportsmen")
            .email("sportsmen@mail.com")
            .password("password")
            .build();
    public static final ProgramDto DEFAULT_PROGRAM_DTO = ProgramDto.builder()
            .programName("Program name")
            .programDescription("Program description")
            .id(1L)
            .owner(DEFAULT_USER_DTO)
            .sportsman(DEFAULT_USER_SPORTSMEN_DTO)
            .build();
    public static final CreateProgramRequest DEFAULT_CREATE_PROGRAM_REQUEST = new CreateProgramRequest()
            .setOwnerId(1L)
            .setProgramName("Program name")
            .setProgramDescription("Program description")
            .setSportsmanId(2L);
}
