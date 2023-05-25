package com.softlex.fh.service.attempt;

import com.softlex.fh.dto.attempt.AttemptDto;
import com.softlex.fh.dto.request.CreateAttemptRequest;

public interface AttemptService {

  AttemptDto getAttempt(Long attemptId);

  AttemptDto createAttempt(CreateAttemptRequest createAttemptRequest);
}
