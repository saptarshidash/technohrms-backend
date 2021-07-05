package com.saptarshi.technohrms.exchanges.training;

import com.saptarshi.technohrms.dto.TrainingEmployeeDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAssignedTrainingResponse {

    private List<TrainingEmployeeDto> trainingEmployeeDtoList;
}
