package com.softlex.fh.service.program;

import com.softlex.fh.dto.request.CreateProgramRequest;
import com.softlex.fh.entity.program.Program;
import com.softlex.fh.entity.program.ProgramRepository;
import com.softlex.fh.entity.user.User;
import com.softlex.fh.entity.user.UserRepository;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProgramServiceImpl implements ProgramService {

  private ProgramMapper programMapper;
  private ProgramRepository programRepository;
  private UserRepository userRepository;

  @Override
  public Program getProgram(Long programId) {
    Optional<Program> programOptional = programRepository.findById(programId);
    if (programOptional.isPresent()) {
      return programOptional.get();
    } else {
      throw new EntityNotFoundException("Wrong program id");
    }
  }

  @Override
  public Program createProgram(CreateProgramRequest createProgramRequest) {
    Optional<User> ownerOptional = userRepository.findById(createProgramRequest.getOwnerId());
    Optional<User> sportsmanOptional = userRepository.findById(createProgramRequest.getSportsmanId());
    if (ownerOptional.isPresent() && sportsmanOptional.isPresent()) {
      User owner = ownerOptional.get();
      User sportsman = sportsmanOptional.get();
      Program program = programMapper.toEntity(createProgramRequest, owner, sportsman);
      return programRepository.save(program);
    } else {
      throw new EntityNotFoundException("Wrong ownerId or sportsmanId");
    }
  }
}
