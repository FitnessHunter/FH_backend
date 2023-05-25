package com.softlex.fh.controller.program;

import com.softlex.fh.dto.program.ProgramDto;
import com.softlex.fh.dto.request.CreateProgramRequest;
import com.softlex.fh.service.program.ProgramService;
import com.softlex.fh.web.BaseControllerAdvice;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class ProgramController extends BaseControllerAdvice {

  private ProgramService programService;

  @GetMapping("/program/{programId}")
  public ProgramDto getProgram(@PathVariable Long programId) {
    return programService.getProgram(programId);
  }

  @PutMapping("/program")
  @ResponseStatus(HttpStatus.CREATED)
  public ProgramDto createProgram(@RequestBody CreateProgramRequest createProgramRequest) {
    createProgramRequest.setOwnerId(getCurrentUserId());
    return programService.createProgram(createProgramRequest);
  }

}
