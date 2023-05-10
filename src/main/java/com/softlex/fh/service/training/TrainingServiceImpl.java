package com.softlex.fh.service.training;

import com.softlex.fh.dto.request.CreateTrainingRequest;
import com.softlex.fh.dto.training.TrainingDto;
import com.softlex.fh.entity.program.Program;
import com.softlex.fh.entity.program.ProgramRepository;
import com.softlex.fh.entity.training.Training;
import com.softlex.fh.entity.training.TrainingRepository;

import java.util.Optional;
import javax.persistence.EntityNotFoundException;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TrainingServiceImpl implements TrainingService {

    private TrainingMapper trainingMapper;
    private TrainingRepository trainingRepository;
    private ProgramRepository programRepository;

    @Override
    public TrainingDto getTraining(Long trainingId) {
        Optional<Training> trainingOptional = trainingRepository.findById(trainingId);
        Training training = trainingOptional.orElseThrow(() -> new EntityNotFoundException("Wrong training id"));
        return trainingMapper.toDto(training);
    }

    @Override
    public TrainingDto createTraining(CreateTrainingRequest createTrainingRequest) {
        Optional<Program> programOptional = programRepository.findById(createTrainingRequest.getProgramId());
        Program program = programOptional.orElseThrow(() -> new EntityNotFoundException("Wrong program id"));
        Training training = trainingMapper.toEntity(createTrainingRequest, program);
        Training savedTraining = trainingRepository.save(training);
        return trainingMapper.toDto(savedTraining);
    }
}
