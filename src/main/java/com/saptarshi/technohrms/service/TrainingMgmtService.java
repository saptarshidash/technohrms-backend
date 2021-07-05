package com.saptarshi.technohrms.service;

import com.saptarshi.technohrms.exchanges.training.*;
import com.saptarshi.technohrms.repository.training.TrainingRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainingMgmtService {

    @Autowired
    private TrainingRepositoryService repositoryService;

    public AddTrainingResponse addTraining(AddTrainingRequest request){
        return repositoryService.addTraining(request);
    }

    public GetTrainingResponse getAllTraining(){
        return repositoryService.getAllTraining();
    }

    public AssignTrainingResponse assignTraining(AssignTrainingRequest request){
        return repositoryService.assignTraining(request);
    }

    public GetAssignedTrainingResponse getAllAssignedTraining(){
        return repositoryService.getAllAssignedTraining();
    }

    public GetAssignedTrainingResponse getAllAssignedTrainingByEmployee(Integer id){
        return repositoryService.getAllAssignedTrainingByEmployee(id);
    }

    public UpdateTrainingResponse updateAssignedTraining(Integer id, UpdateTrainingRequest request){
        return repositoryService.updateAssignedTraining(id, request);
    }

    public GetAssignedTrainingResponse getAllAssignedTrainingByTraining(Integer id){
        return repositoryService.getAllAssignedTrainingByTraining(id);
    }

}
