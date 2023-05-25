package com.softlex.fh.controller.attempt;

import com.softlex.fh.dto.attempt.AttemptDto;
import com.softlex.fh.dto.request.CreateAttemptRequest;
import com.softlex.fh.service.attempt.AttemptService;
import com.softlex.fh.web.BaseControllerAdvice;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class AttemptController extends BaseControllerAdvice {

  private AttemptService attemptService;

  @GetMapping("/attempt/{attemptId}")
  public AttemptDto getProgram(@PathVariable Long attemptId) {
    return attemptService.getAttempt(attemptId);
  }

  @PutMapping("/attempt")
  @ResponseStatus(HttpStatus.CREATED)
  public AttemptDto createProgram(@RequestBody CreateAttemptRequest createAttemptRequest) {
    return attemptService.createAttempt(createAttemptRequest);
  }
}
