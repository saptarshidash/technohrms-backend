package com.saptarshi.technohrms.repository.training;

import com.saptarshi.technohrms.dto.TrainingDto;
import com.saptarshi.technohrms.dto.TrainingEmployeeDto;
import com.saptarshi.technohrms.entity.Employee;
import com.saptarshi.technohrms.entity.Training;
import com.saptarshi.technohrms.entity.TrainingEmployee;
import com.saptarshi.technohrms.exchanges.training.*;
import com.saptarshi.technohrms.repository.employee.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.*;

@Service
public class TrainingRepositoryServiceImpl implements TrainingRepositoryService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TrainingRepository trainingRepository;

    @Autowired
    private TrainingEmployeeRepository trainingEmployeeRepository;

    @Override
    public AddTrainingResponse addTraining(AddTrainingRequest request) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);

        Training training = mapper.map(request, Training.class);
        if(trainingRepository.existsByName(request.getName())){
            AddTrainingResponse response = new AddTrainingResponse(
                    request.getName(),
                    "Training already exist"
            );

            return response;
        }
        training.setCompleted(0);
        training.setOnGoingParticipant(0);
        training.setTotalParticipation(0);
        training = trainingRepository.save(training);

        AddTrainingResponse response = mapper.map(training, AddTrainingResponse.class);
        response.setMessage("Training added successfully");
        return response;
    }

    @Override
    public GetTrainingResponse getAllTraining() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);

        List<Training> trainingList = trainingRepository.findAll();

        List<TrainingDto> trainingDtoList = new ArrayList<>();

        for(Training training: trainingList){
            trainingDtoList.add(mapper.map(training, TrainingDto.class));
        }

        GetTrainingResponse response = new GetTrainingResponse(trainingDtoList);
        return response;
    }

    @Override
    public AssignTrainingResponse assignTraining(AssignTrainingRequest request) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);

        Training training = trainingRepository.getById(request.getTraining().getId());
        training.setTotalParticipation(training.getTotalParticipation()+1);
        training.setOnGoingParticipant(training.getOnGoingParticipant() + 1);

        TrainingEmployee trainingEmployee = mapper.map(request, TrainingEmployee.class);

        if(trainingEmployeeRepository.existsByEmployeeAndTraining(request.getEmployee(), request.getTraining())){
            AssignTrainingResponse response = new AssignTrainingResponse(
                    null,
                    "Training already exist for this employee"
            );
            return response;
        }
        trainingEmployee.setRating(0);
        trainingEmployee.setCompletionStatus(false);
        trainingEmployee = trainingEmployeeRepository.save(trainingEmployee);
        trainingRepository.save(training);

        AssignTrainingResponse response = mapper.map(trainingEmployee, AssignTrainingResponse.class);
        response.setMessage("Training assigned successfully");
        return response;
    }

    @Override
    public GetAssignedTrainingResponse getAllAssignedTraining() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);

        List<TrainingEmployee> trainingEmployeeList =  trainingEmployeeRepository.findAll();

        List<TrainingEmployeeDto> trainingEmployeeDtoList  = new ArrayList<>();

        for(TrainingEmployee trainingEmployee: trainingEmployeeList){
            trainingEmployeeDtoList.add(mapper.map(trainingEmployee, TrainingEmployeeDto.class));
        }

        return new GetAssignedTrainingResponse(trainingEmployeeDtoList);
    }

    @Override
    public GetAssignedTrainingResponse getAllAssignedTrainingByEmployee(Integer id) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);

        Employee employee = employeeRepository.findEmployeeById(id).get();

        List<TrainingEmployee> trainingEmployeeList = trainingEmployeeRepository.findAllByEmployee(employee);
        List<TrainingEmployeeDto> trainingEmployeeDtoList = new ArrayList<>();

        for(TrainingEmployee trainingEmployee: trainingEmployeeList){
            trainingEmployeeDtoList.add(mapper.map(trainingEmployee, TrainingEmployeeDto.class));
        }

        return new GetAssignedTrainingResponse(trainingEmployeeDtoList);
    }

    @Override
    public UpdateTrainingResponse updateAssignedTraining(Integer id,UpdateTrainingRequest request) {

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);

        TrainingEmployee trainingEmployee = trainingEmployeeRepository.getById(id);

        Training training = trainingRepository.getById(request.getTraining().getId());

        if (!trainingEmployee.isCompletionStatus() && request.isCompletionStatus()){
            training.setOnGoingParticipant(training.getOnGoingParticipant() - 1);
            training.setCompleted(training.getCompleted() + 1);
            trainingEmployee.setRating(request.getRating());
            trainingEmployee.setCompletionStatus(true);
        }

        trainingEmployee.setStartDate(request.getStartDate());
        trainingEmployee.setEndDate(request.getEndDate());


        trainingEmployeeRepository.save(trainingEmployee);
        trainingRepository.save(training);

        return new UpdateTrainingResponse("Training updated successfully");

    }

    @Override
    public GetAssignedTrainingResponse getAllAssignedTrainingByTraining(Integer id) {

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);

        Training training = trainingRepository.findById(id).get();

        List<TrainingEmployee> trainingEmployeeList = trainingEmployeeRepository.findAllByTraining(training);
        List<TrainingEmployeeDto> trainingEmployeeDtoList = new ArrayList<>();

        for(TrainingEmployee trainingEmployee: trainingEmployeeList){
            trainingEmployeeDtoList.add(mapper.map(trainingEmployee, TrainingEmployeeDto.class));
        }

        return new GetAssignedTrainingResponse(trainingEmployeeDtoList);
    }


}
