package com.softlex.fh.service.program;

import com.softlex.fh.dto.program.ProgramDto;
import com.softlex.fh.dto.request.CreateProgramRequest;
import com.softlex.fh.entity.program.Program;
import com.softlex.fh.entity.program.ProgramRepository;
import com.softlex.fh.entity.user.User;
import com.softlex.fh.entity.user.UserRepository;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class ProgramServiceImpl implements ProgramService {

  private ProgramMapper programMapper;
  private ProgramRepository programRepository;
  private UserRepository userRepository;

  @Override
  public ProgramDto getProgram(Long programId) {
    log.debug("Get program with id {}", programId);
    Optional<Program> programOptional = programRepository.findById(programId);
    Program program = programOptional.orElseThrow(
        () -> new EntityNotFoundException("Wrong program id"));
    ProgramDto dto = programMapper.toDto(program);
    log.debug("Return program {}", dto);
    return dto;
  }

  @Override
  public ProgramDto createProgram(CreateProgramRequest createProgramRequest) {
    log.debug("Create program {}", createProgramRequest);
    Optional<User> ownerOptional = userRepository.findById(createProgramRequest.getOwnerId());
    Optional<User> sportsmanOptional = userRepository.findById(
        createProgramRequest.getSportsmanId());
    User owner = ownerOptional.orElseThrow(() -> new EntityNotFoundException("Wrong owner id"));
    User sportsman = sportsmanOptional.orElseThrow(
        () -> new EntityNotFoundException("Wrong sportsman id"));
    Program program = programMapper.toEntity(createProgramRequest, owner, sportsman);
    Program savedProgram = programRepository.save(program);
    ProgramDto dto = programMapper.toDto(savedProgram);
    log.debug("Return created program {}", dto);
    return dto;
  }
}
