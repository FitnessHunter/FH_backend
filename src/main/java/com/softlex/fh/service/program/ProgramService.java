package com.softlex.fh.service.program;

import com.softlex.fh.dto.program.ProgramDto;
import com.softlex.fh.dto.request.CreateProgramRequest;

public interface ProgramService {

  ProgramDto getProgram(Long programId);

  ProgramDto createProgram(CreateProgramRequest createProgramRequest);

}
