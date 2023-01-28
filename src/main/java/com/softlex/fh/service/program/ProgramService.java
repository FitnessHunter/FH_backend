package com.softlex.fh.service.program;

import com.softlex.fh.dto.request.CreateProgramRequest;
import com.softlex.fh.entity.program.Program;

public interface ProgramService {

  Program getProgram(Long programId);

  Program createProgram(CreateProgramRequest createProgramRequest);

}
