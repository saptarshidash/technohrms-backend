package com.saptarshi.technohrms.exchanges.training;

import com.saptarshi.technohrms.entity.Employee;
import com.saptarshi.technohrms.entity.Training;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTrainingRequest {

    private Training training;

    private Employee employee;

    private int rating;

    private Date startDate;

    private Date endDate;

    private boolean completionStatus;
}
