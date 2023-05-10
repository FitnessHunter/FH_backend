package com.softlex.fh.service.program;

import com.softlex.fh.dto.program.ProgramDto;
import com.softlex.fh.dto.request.CreateProgramRequest;
import com.softlex.fh.entity.program.Program;

public interface ProgramService {

  ProgramDto getProgram(Long programId);

  ProgramDto createProgram(CreateProgramRequest createProgramRequest);

}
