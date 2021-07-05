package com.saptarshi.technohrms.exchanges.training;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddTrainingRequest {

    private String name;

    private String description;
}
