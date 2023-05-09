package com.softlex.fh.controller.program;

import com.softlex.fh.dto.request.CreateProgramRequest;
import com.softlex.fh.entity.program.Program;
import com.softlex.fh.service.program.ProgramService;
import com.softlex.fh.web.BaseControllerAdvice;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class ProgramController extends BaseControllerAdvice {

  private ProgramService programService;

  @GetMapping("/program/{programId}")
  public Program getProgram(@PathVariable Long programId) {
    return programService.getProgram(programId);
  }

  @PostMapping("/program")
  public Program createProgram(@RequestBody CreateProgramRequest createProgramRequest) {
    createProgramRequest.setOwnerId(getCurrentUserId());
    return programService.createProgram(createProgramRequest);
  }

}
