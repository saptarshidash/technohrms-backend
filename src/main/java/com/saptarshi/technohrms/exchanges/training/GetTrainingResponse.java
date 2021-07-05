package com.saptarshi.technohrms.exchanges.training;

import com.saptarshi.technohrms.dto.TrainingDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetTrainingResponse {

    private List<TrainingDto> trainingDtoList;
}
