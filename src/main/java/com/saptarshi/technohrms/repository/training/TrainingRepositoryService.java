package com.saptarshi.technohrms.repository.training;

import com.saptarshi.technohrms.exchanges.training.*;

public interface TrainingRepositoryService {

    AddTrainingResponse addTraining(AddTrainingRequest request);

    GetTrainingResponse getAllTraining();

    AssignTrainingResponse assignTraining(AssignTrainingRequest request);

    GetAssignedTrainingResponse getAllAssignedTraining();

    GetAssignedTrainingResponse getAllAssignedTrainingByEmployee(Integer id);

    UpdateTrainingResponse updateAssignedTraining(Integer id,UpdateTrainingRequest request);

    GetAssignedTrainingResponse getAllAssignedTrainingByTraining(Integer id);
}
