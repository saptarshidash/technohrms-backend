package com.saptarshi.technohrms.dto;

import com.saptarshi.technohrms.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveRequestDto {

    private int id;

    private Employee employee;

    private String leaveName;

    private Date startDate;

    private Date endDate;

    private String reason;

    private String status;
}
